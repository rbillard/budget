// FILTERS
angular.module( 'appFilters', [] ).filter( 'checkFini', function() {
  return function( value ) {
    return value == "Oui" ? '\u2713' : '\u2718';
  };
});