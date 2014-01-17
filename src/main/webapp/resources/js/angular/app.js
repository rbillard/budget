var budgetApp = angular.module('budgetApp', [
  'ngRoute',
  'budgetControllers',
  'budgetFilters',
  'budgetServices'
]);
 
budgetApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/period/periods', {
        templateUrl: 'resources/partials/period-list.html',
        controller: 'BudgetListCtrl'
      }).
      when('/period/:periodId', {
        templateUrl: 'resources/partials/period-detail.html',
        controller: 'BudgetDetailCtrl'
      }).
      otherwise({
        redirectTo: '/period/periods'
      });
  }]);