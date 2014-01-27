// SERVICES
var periodServices = angular.module( 'periodServices', ['ngResource'] );

periodServices.factory( 'PeriodCreateSrv', function( $resource ) {
	return $resource( '/budget/period/create', {}, {
		query: { method:'GET' }
	});
});

periodServices.factory( 'PeriodListSrv', function( $resource ) {
	return $resource( '/budget/period/list', {}, {
		query: { method:'GET', isArray:true }
	});
});

periodServices.factory( 'PeriodDetailSrv', function( $resource ) {
	return $resource( '/budget/period/:periodId', {}, {
		query: { method:'GET' }
	});
});

periodServices.factory( 'PeriodDeleteSrv', function( $resource ) {
	return $resource( '/budget/period/:periodId', {}, {
		query: { method:'DELETE', isArray:true }
	});
});


// CONTROLLERS
var periodControllers = angular.module( 'periodControllers', [] );

periodControllers.controller( 'PeriodCreateCtrl', function ( $scope, $http, PeriodCreateSrv ) {
	
	$scope.period = PeriodCreateSrv.query();
	
	$scope.createOrUpdatePeriod = function() {
		
		$http.post( '/budget/period', $scope.period, headers )
	        .success( function ( data ) {
	        	window.location = "/budget/#/period/" + data.id;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
    }
	
});

periodControllers.controller( 'PeriodListCtrl', function ( $scope, PeriodListSrv, PeriodDeleteSrv ) {
	
	$scope.periods = PeriodListSrv.query();
	$scope.orderProp = 'startDate';
	
	$scope.delete = function( period ) {
		$scope.periods = PeriodDeleteSrv.query({ periodId: period.id });
	};
	
});

// TODO supprimer $location si inutile
periodControllers.controller( 'PeriodDetailCtrl', function ( $scope, $routeParams, $http, $location, PeriodDetailSrv, BudgetSelectSrv, OperationDeleteSrv, PeriodBudgetDeleteSrv ) {
	
	$scope.showBudgetsAssociated = false;
	$scope.showBudgetsNotAssociated = false;
	
	$scope.period = PeriodDetailSrv.query(
		{ periodId: $routeParams.periodId },
		function( data ) {
			// TODO factoriser avec addBudget
			$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
			$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
		}
	);
	$scope.orderOperation = "date";
	
	$scope.createOrUpdatePeriod = function() {
		
		$http.put( '/budget/period', $scope.period, headers )
	        .success( function ( data ) {
	        	console.log( data );
//	        	$location.path( "/budget/#/period/" + data.id );
	        	window.location = "/budget/#/period/" + data.id;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
		
	};
	
	// add budget
	
	$scope.messageAddBudget = { "periodId": $routeParams.periodId };
	
	$scope.addBudget = function() {
		
		// FIXME directement l'id dans budgetId
		$scope.messageAddBudget.budgetId = $scope.messageAddBudget.budgetId.id; 

		$http.post( '/budget/period-budget', $scope.messageAddBudget, headers )
	        .success( function ( data ) {
	        	$scope.period.typeBudgets = data;
	        	// TODO factoriser avec get period
	        	$scope.showBudgetsAssociated = data.budgetsAssociated.length > 0;
				$scope.showBudgetsNotAssociated = data.budgetsNotAssociated.length > 0;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errorsAddBudget = data;
	        });
		
	};
	
	// create operation
	
	$scope.messageCreateOperation = { "periodId": $routeParams.periodId };
	
	$scope.createOperation = function() {
		
		// FIXME directement l'id dans budgetId
		$scope.messageCreateOperation.budgetId = $scope.messageCreateOperation.budgetId.id; 
		
		$http.post( '/budget/operation', $scope.messageCreateOperation, headers )
	        .success( function ( data ) {
	        	$scope.period.operations.push( data );
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errorsCreateOperation = data;
	        });
		
	};
	
	$scope.deleteOperation = function( operation ) {
		OperationDeleteSrv.query(
			{ operationId: operation.id },
			function() {
				var index = $scope.period.operations.indexOf( operation )
				$scope.period.operations.splice( index, 1 );
			}
		);
	};
	
	// dissociate budget
	
	$scope.dissociateBudget = function( budget ) {
		
		PeriodBudgetDeleteSrv.query(
			{
				periodId: $scope.period.id,
				budgetId: budget.id
			},
			function( data ) {
				console.log(data);
				$scope.period = data;
				// TODO factoriser avec get period
				$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
				$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
				
			}
		);
	};
	
})
.directive( 'hboTabs', function() {
    return {
        restrict: 'A',
        link: function(scope, elm, attrs) {
            var jqueryElm = $(elm[0]);
            $(jqueryElm).tabs()
        }
    };
});