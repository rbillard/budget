package fr.rbillard.budget.service;

import java.util.List;

import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IPeriodBudgetService extends IGenericService<PeriodBudget, PeriodBudgetId> {

	PeriodBudget associatePeriodBudget( MessageAssociatePeriodBudget message ) throws ConstraintViolationFunctionalException;

	PeriodBudget getEntity( Long userId, Long periodId, Long budgetId );

	List<PeriodBudget> findAssociatedToPeriod( Long periodId, Long userId );

	void dissociate( Long periodId, Long budgetId, Long userId );

}
