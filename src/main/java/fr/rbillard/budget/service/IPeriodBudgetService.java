package fr.rbillard.budget.service;

import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IPeriodBudgetService extends IGenericService<PeriodBudget, PeriodBudgetId> {

	PeriodBudget associatePeriodBudget( MessageAssociatePeriodBudget message );

}
