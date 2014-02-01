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

periodControllers.controller( 'PeriodCreateCtrl', function ( $scope, $http, $filter ) {
	
	$scope.period = {};
	
	$scope.createOrUpdatePeriod = function() {
		
		// TODO find a way to do it automatically
		$scope.period.startDate = $filter('date')($scope.period.startDate, dateFormat);
		$scope.period.endDate = $filter('date')($scope.period.endDate, dateFormat);
		
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
	
	$scope.deletePeriod = function( period ) {
		
		var confirmDeletePeriod = confirm("Voulez-vous supprimer la période ?");
		
		if ( confirmDeletePeriod ) {
			$scope.periods = PeriodDeleteSrv.query({ periodId: period.id });
		}
		
	};
	
});

periodControllers.controller( 'PeriodDetailCtrl', function ( $scope, $routeParams, $http, $filter, PeriodDetailSrv, BudgetSelectSrv, OperationDeleteSrv, PeriodBudgetDeleteSrv ) {
	
	$scope.orderOperation = "date";
	$scope.orderBudget = "label";

	$scope.showBudgetsAssociated = false;
	$scope.showBudgetsNotAssociated = false;
	
	PeriodDetailSrv.query(
		{ periodId: $routeParams.periodId },
		function( data ) {
			setScope( $scope, data );
		}
	);
	
	// associate budget
	
	$scope.messageAssociateBudget = { "periodId": $routeParams.periodId };
	$scope.selectedAssociateBudget = {};
	
	$scope.associateBudget = function() {
		
		// TODO directement l'id dans budgetId
		if ( $scope.selectedAssociateBudget.budget != undefined ) {
			$scope.messageAssociateBudget.budgetId = $scope.selectedAssociateBudget.budget.id;
		}

		$http.post( '/budget/period-budget', $scope.messageAssociateBudget, headers )
	        .success( function ( data ) {
	        	$scope.messageAssociateBudget = { "periodId": $routeParams.periodId };
				setScope( $scope, data );
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errorsAssociateBudget = data;
	        });
		
	};
	
	// create operation
	
	$scope.messageCreateOperation = { "periodId": $routeParams.periodId };
	$scope.selectedBugdetCreateOperation = {};
	
	$scope.createOperation = function() {
		
		// TODO directement l'id dans budgetId
		if ( $scope.selectedBugdetCreateOperation.budget != undefined ) {
			$scope.messageCreateOperation.budgetId = $scope.selectedBugdetCreateOperation.budget.id;
		}
		
		// TODO find a way to do it automatically
		$scope.messageCreateOperation.date = $filter('date')($scope.messageCreateOperation.date, dateFormat);
		
		$http.post( '/budget/operation', $scope.messageCreateOperation, headers )
	        .success( function ( data ) {
	        	$scope.messageCreateOperation = { "periodId": $routeParams.periodId };
	        	setScope( $scope, data );
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errorsCreateOperation = data;
	        });
		
	};
	
	$scope.deleteOperation = function( operation ) {
		
		var confirmDeleteOperation = confirm( "Voulez-vous supprimer l'opération ?" );
		
		if ( confirmDeleteOperation ) {
			OperationDeleteSrv.query(
				{ operationId: operation.id },
				function( data ) {
					setScope( $scope, data );
				}
			);
		}
		
	};
	
	// dissociate budget
	
	$scope.dissociateBudget = function( budget ) {
		
		var confirmDissociateBudget = confirm( "Voulez-vous dissocier la période du budget et supprimer les opérations ?");
		
		if ( confirmDissociateBudget ) {
			PeriodBudgetDeleteSrv.query(
				{
					periodId: $scope.period.id,
					budgetId: budget.id
				},
				function( data ) {
					setScope( $scope, data );
				}
			);
		}
	};
	
});

function setScope( scope, data ) {
	
	scope.period = data;
	scope.showBudgetsAssociated = data.typeBudgets.budgetsAssociated.length > 0;
	scope.showBudgetsNotAssociated = data.typeBudgets.budgetsNotAssociated.length > 0;
	
	var nbOperations = 0;
	$.each( data.typeBudgets.budgetsAssociated, function() {
		$.each( this.operations, function() {
			nbOperations++;
		});
	});
	scope.nbOperations = nbOperations;
	
}
