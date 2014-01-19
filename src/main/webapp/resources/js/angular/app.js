var budgetApp = angular.module( 'budgetApp', [
  'ngRoute',
  'periodControllers',
  'budgetFilters',
  'periodServices'
]);
 
budgetApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/period/list', {
        templateUrl: 'resources/partials/period/period-list.html',
        controller: 'PeriodListCtrl'
      }).
      when('/period/create', {
    	  templateUrl: 'resources/partials/period/period-wrapper-form-create.html',
    	  controller: 'PeriodCreateCtrl'
      }).
      when('/period/:periodId', {
        templateUrl: 'resources/partials/period/period-detail.html',
        controller: 'PeriodDetailCtrl'
      }).
      when('/period/update/:periodId', {
    	  templateUrl: 'resources/partials/period/period-form.html',
    	  controller: 'PeriodDetailCtrl'
      }).
      otherwise({
        redirectTo: '/period/list'
      });
  }]);