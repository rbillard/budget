package fr.rbillard.budget.dao;

import java.util.List;

import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.springhibernate.domain.dao.IGenericDAO;

public interface IPeriodBudgetDAO extends IGenericDAO<PeriodBudget, PeriodBudgetId> {

	PeriodBudget getEntity( Long userId, Long periodId, Long budgetId );

	List<PeriodBudget> findAssociatedToPeriod( Long periodId, Long userId );

}
