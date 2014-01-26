// SERVICES
var operationServices = angular.module( 'operationServices', ['ngResource'] );

operationServices.factory( 'OperationDeleteSrv', function( $resource ) {
	return $resource( '/budget/operation/:operationId', {}, {
		query: { method:'DELETE' }
	});
});