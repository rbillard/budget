var budgetApp = angular.module( 'budgetApp', [
	'ngRoute',
	'periodControllers',
	'budgetControllers',
	'periodServices',
	'budgetServices',
	'operationServices',
	'periodBudgetServices',
	'appFilters',
	'operationFilters',
	'ui.bootstrap'
]);

budgetApp.config( function( $routeProvider, datepickerConfig, datepickerPopupConfig ) {
	
	$routeProvider.

		// PERIOD
		when('/period/create', {
			templateUrl: 'resources/partials/period/period-wrapper-form-create.html',
			controller: 'PeriodCreateCtrl'
		}).
		when('/period/update/:periodId', {
			templateUrl: 'resources/partials/period/period-wrapper-form-update.html',
			controller: 'PeriodDetailCtrl'
		}).
		when('/period/list', {
			templateUrl: 'resources/partials/period/period-list.html',
			controller: 'PeriodListCtrl'
		}).
		when('/period/:periodId', {
			templateUrl: 'resources/partials/period/period-detail.html',
			controller: 'PeriodDetailCtrl'
		}).
	
		// BUDGET
		when('/budget/create', {
			templateUrl: 'resources/partials/budget/budget-wrapper-form-create.html',
			controller: 'BudgetCreateCtrl'
		}).
		when('/budget/update/:budgetId', {
			templateUrl: 'resources/partials/budget/budget-wrapper-form-update.html',
			controller: 'BudgetDetailCtrl'
		}).
		when('/budget/list', {
			templateUrl: 'resources/partials/budget/budget-list.html',
			controller: 'BudgetListCtrl'
		}).
		when('/budget/:budgetId', {
			templateUrl: 'resources/partials/budget/budget-detail.html',
			controller: 'BudgetDetailCtrl'
		}).
	
		// OTHERWISE
		otherwise({
			redirectTo: '/period/list'
		});
	
	  
	// DATEPICKER
	datepickerConfig.showWeeks = false;
	datepickerPopupConfig.showButtonBar = false;

})
.run( function( $rootScope ) {
	
	// GLOBAL
	$rootScope.dateFormat = dateFormat;
	
});

angular.module('appFilters', []).filter('formatDate', function() {
	return function( date ) {
		return new Date( date ).toString( dateFormat );
	};
});