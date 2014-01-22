package fr.rbillard.budget.dao.impl;

import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IPeriodBudgetDAO;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class PeriodBudgetDAO extends GenericHibernateDAO<PeriodBudget, PeriodBudgetId> implements IPeriodBudgetDAO {

	@Override
	protected Class<PeriodBudget> getPersistentClass() {
		return PeriodBudget.class;
	}

}
