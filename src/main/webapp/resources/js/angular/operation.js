// SERVICES
var operationServices = angular.module( 'operationServices', ['ngResource'] );

operationServices.factory( 'OperationDeleteSrv', function( $resource ) {
	return $resource( '/budget/operation/:operationId', {}, {
		query: { method:'DELETE' }
	});
});

// FILTERS
angular.module( 'operationFilters', [] ).filter( 'filterByBudget', function() {
	
	return function( operations, scope ) {
		
		if ( scope.budgetFilter != undefined ) {
			var selectedOperations = [];
			$.each( operations, function() {
				if ( this.budgetId == scope.budgetFilter.id ) {
					selectedOperations.push( this );
				}
			});
			return selectedOperations;
		} else {
			return operations;
		}

	};
	
});