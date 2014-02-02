// SERVICES
var periodBudgetServices = angular.module( 'periodBudgetServices', ['ngResource'] );

periodBudgetServices.factory( 'PeriodBudgetDeleteSrv', function( $resource ) {
	return $resource( context + '/period-budget/:periodId/:budgetId', {}, {
		query: { method:'DELETE' }
	});
});