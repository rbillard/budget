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
periodControllers.controller( 'PeriodDetailCtrl', function ( $scope, $routeParams, $http, $location, PeriodDetailSrv, BudgetSelectSrv ) {

	$scope.period = PeriodDetailSrv.query({ periodId: $routeParams.periodId });
	
	$scope.budgetsSelect = BudgetSelectSrv.query({ periodId: $routeParams.periodId });
	
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
	
	$scope.message = { "periodId": $routeParams.periodId };
	
	$scope.addBudget = function() {
		console.log('added');
		// FIXME directement l'id dans budgetId
		$scope.message.budgetId = $scope.message.budgetId.id; 

		console.log( $scope.message );
		
		$http.post( '/budget/period-budget', $scope.message, headers )
	        .success( function ( data ) {
	        	$scope.budgetsSelect = data;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data; // TODO
	        });
		
	};
	
});