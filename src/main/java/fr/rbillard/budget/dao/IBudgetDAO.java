package fr.rbillard.budget.dao;

import java.util.List;

import fr.rbillard.budget.entity.Budget;
import fr.rbillard.springhibernate.domain.dao.IGenericDAO;

public interface IBudgetDAO extends IGenericDAO<Budget, Long> {

	List<Budget> findByUser( Long userId );

	List<Budget> findNotAssociatedToPeriod( Long periodId, Long userId );

	Budget getBudget( Long id, Long userId );

}
