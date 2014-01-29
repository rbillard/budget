// SERVICES
var budgetServices = angular.module( 'budgetServices', ['ngResource'] );

budgetServices.factory( 'BudgetDetailSrv', function( $resource ) {
	return $resource( '/budget/budget/:budgetId', {}, {
		query: { method:'GET' }
	});
});

budgetServices.factory( 'BudgetListSrv', function( $resource ) {
	return $resource( '/budget/budget/list', {}, {
		query: { method:'GET', isArray:true }
	});
});

budgetServices.factory( 'BudgetDeleteSrv', function( $resource ) {
	return $resource( '/budget/budget/:budgetId', {}, {
		query: { method:'DELETE', isArray:true }
	});
});

budgetServices.factory( 'BudgetSelectSrv', function( $resource ) {
	return $resource( '/budget/budget/period/:periodId', {}, {
		query: { method:'GET', isArray:true }
	});
});

// CONTROLLERS
var budgetControllers = angular.module( 'budgetControllers', [] );

budgetControllers.controller( 'BudgetCreateCtrl', function ( $scope, $http ) {
	
	$scope.budget = {};
	
	$scope.submit = "Enregistrer"; // TODO constants i18n
	
	$scope.createOrUpdateBudget = function() {
		
		$http.post( '/budget/budget', $scope.budget, headers )
	        .success( function ( data ) {
	        	window.location = "/budget/#/budget/" + data.id;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
    }
	
});

budgetControllers.controller( 'BudgetDetailCtrl', function ( $scope, $routeParams, $http, BudgetDetailSrv ) {

	$scope.budget = BudgetDetailSrv.query({ budgetId: $routeParams.budgetId });
	
	$scope.submit = "Modifier"; // TODO constants i18n
	
	$scope.createOrUpdateBudget = function() {
		
		$http.put( '/budget/budget', $scope.budget, headers )
	        .success( function ( data ) {
	        	window.location = "/budget/#/budget/" + data.id;
	        })
	        .error( function( data, status, headers, config ) {
	            $scope.errors = data;
	        });
		
	};
	
});

budgetControllers.controller( 'BudgetListCtrl', function ( $scope, BudgetListSrv, BudgetDeleteSrv ) {
	
	$scope.budgets = BudgetListSrv.query();
	$scope.orderProp = 'label';
	
	$scope.deleteBudget = function( budget ) {
		
		var confirmDeleteBudget = confirm("Voulez-vous supprimer le budget ?");
		
		if ( confirmDeleteBudget ) {
			$scope.budgets = BudgetDeleteSrv.query({ budgetId: budget.id });
		}
	};
	
});
