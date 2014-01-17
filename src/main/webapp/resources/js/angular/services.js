var budgetServices = angular.module('budgetServices', ['ngResource']);
 
budgetServices.factory('BudgetSrv', ['$resource',
  function($resource){
    return $resource('/budget/period/:periodId', {}, {
      query: {method:'GET', params:{periodId:'periods'}, isArray:true}
    });
  }]);