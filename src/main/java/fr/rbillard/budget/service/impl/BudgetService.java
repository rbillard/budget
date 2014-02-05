package fr.rbillard.budget.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IBudgetDAO;
import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.exception.NoSuchEntityException;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.service.impl.GenericService;

@Service
public class BudgetService extends GenericService<Budget, Long, IBudgetDAO> implements IBudgetService {

	@Autowired
	private IBudgetDAO dao;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public IBudgetDAO getDao() {
		return dao;
	}

	@Override
	public void setDao( IBudgetDAO dao ) {
		this.dao = dao;
	}

	@Override
	@Transactional( readOnly = true )
	public List<Budget> findByUser( Long userId ) {
		return dao.findByUser( userId );
	}

	@Override
	@Transactional
	public void delete( Long id, Long userId ) throws NoSuchEntityException {

		Budget budget = getBudget( id, userId );
		User user = userService.getEntity( userId );
		user.removeBudget( budget );
		
		dao.delete( budget );
		
	}

	@Override
	@Transactional( readOnly = true )
	public List<Budget> findNotAssociatedToPeriod( Long periodId, Long userId ) {
		return dao.findNotAssociatedToPeriod( periodId, userId );
	}

	@Override
	@Transactional
	public Budget update( BudgetDTO dto, Long userId ) throws ConstraintViolationFunctionalException, NoSuchEntityException {
		
		assertValid( dto );
		
		Budget budget = getBudget( dto.getId(), userId );
		budget.setLabel( dto.getLabel() );
		
		update( budget );

		return budget;

	}

	@Override
	@Transactional
	public Budget create( BudgetDTO dto, Long userId ) throws ConstraintViolationFunctionalException {
		
		assertValid( dto );
		
		User user = userService.getEntity( userId );
		Budget budget = new Budget();
		budget.setLabel( dto.getLabel() );
		budget.setUser( user );
		user.addBudget( budget );
		
		create( budget );
		
		return budget;
		
	}

	@Override
	@Transactional( readOnly = true )
	public Budget getBudget( Long id, Long userId ) throws NoSuchEntityException {
		
		Budget budget = dao.getBudget( id, userId );
		if ( budget == null ) {
			throw new NoSuchEntityException( "Le budget d'id " + id + " n'existe pas pour l'utilisateur d'id " + userId );
		}
		return budget;
		
	}

}
