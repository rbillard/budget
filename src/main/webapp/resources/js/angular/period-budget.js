// SERVICES
var periodBudgetServices = angular.module( 'periodBudgetServices', ['ngResource'] );

periodBudgetServices.factory( 'PeriodBudgetDeleteSrv', function( $resource ) {
	return $resource( '/budget/period-budget/:periodId/:budgetId', {}, {
		query: { method:'DELETE' }
	});
});