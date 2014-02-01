package fr.rbillard.budget.service;

import java.util.List;

import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IBudgetService extends IGenericService<Budget, Long> {

	List<Budget> findByUser( Long userId );

	void delete( Long id, Long userId );

	List<Budget> findNotAssociatedToPeriod( Long periodId, Long userId );

	Budget update( BudgetDTO dto, Long userId ) throws ConstraintViolationFunctionalException;

	Budget create( BudgetDTO dto, Long userId ) throws ConstraintViolationFunctionalException;

}
