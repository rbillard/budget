// SERVICES
var periodServices = angular.module( 'periodServices', ['ngResource'] );

periodServices.factory( 'PeriodLightSrv', function( $resource ) {
	return $resource( '/budget/period/:periodId/light', {}, {
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

periodControllers.controller( 'PeriodCreateCtrl', function ( $scope, $http ) {
	
	$scope.period = {};
	
	$scope.createOrUpdatePeriod = function() {
		
		console.log($scope.period);
		
		// TODO factoriser avec update
		$http.post( '/budget/period', $scope.period, headers )
	        .success( function ( data ) {
	        	window.location = "/budget/#/period/" + data.id;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
    }
	
});

periodControllers.controller( 'PeriodUpdateCtrl', function ( $scope, $http, $routeParams, PeriodLightSrv ) {
	
	$scope.period = PeriodLightSrv.query({ periodId: $routeParams.periodId }, function(data) {console.log(data);});
	
	$scope.createOrUpdatePeriod = function() {
		
		console.log($scope.period);
		
		// TODO factoriser avec create
		$http.put( '/budget/period', $scope.period, headers )
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
	
	$scope.orderOperation = "date";

	$scope.showBudgetsAssociated = false;
	$scope.showBudgetsNotAssociated = false;
	
	PeriodDetailSrv.query(
		{ periodId: $routeParams.periodId },
		function( data ) {
			// TODO factoriser avec addBudget
			$scope.period = data;
			$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
			$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
		}
	);
	
	// add budget
	
	$scope.messageAddBudget = { "periodId": $routeParams.periodId };
	
	$scope.addBudget = function() {
		
		// FIXME directement l'id dans budgetId
		$scope.messageAddBudget.budgetId = $scope.messageAddBudget.budgetId.id; 

		$http.post( '/budget/period-budget', $scope.messageAddBudget, headers )
	        .success( function ( data ) {
				// TODO factoriser avec addBudget
				$scope.period = data;
				$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
				$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
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
	        	// TODO factoriser avec addBudget
				$scope.period = data;
				$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
				$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errorsCreateOperation = data;
	        });
		
	};
	
	$scope.deleteOperation = function( operation ) {
		OperationDeleteSrv.query(
			{ operationId: operation.id },
			function( data ) {
				// TODO factoriser avec addBudget
				$scope.period = data;
				$scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
				$scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
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