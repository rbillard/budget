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
		
		var config = {headers: {'Content-Type': 'application/json; charset=UTF-8'}};
		
		$http.post('/budget/period', $scope.period, config)
	        .success(function (data) {
	        	console.log( "success" );
	        	console.log( data );
	        })
	        .error(function(data, status, headers, config) {
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

periodControllers.controller( 'PeriodDetailCtrl', function ( $scope, $routeParams, $http, PeriodDetailSrv ) {
	
	$scope.period = PeriodDetailSrv.get({ periodId: $routeParams.periodId });
	
	$scope.createOrUpdatePeriod = function() {

		console.log( $scope.period );
		// TODO factoriser
		var config = {headers: {'Content-Type': 'application/json; charset=UTF-8'}};
		$http.put('/budget/period', $scope.period, config)
	        .success(function (data) {
	        	console.log( "success" );
	        	console.log( data );
	        })
	        .error(function(data, status, headers, config) {
	            $scope.errors = data;
	        });
		
	};
});