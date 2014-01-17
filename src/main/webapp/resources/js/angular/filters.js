angular.module('budgetFilters', []).filter('checkFini', function() {
  return function(value) {
    return value == "Oui" ? '\u2713' : '\u2718';
  };
});