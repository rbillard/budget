package fr.rbillard.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IBudgetDAO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IUserService;
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
	public void delete( Long id, Long userId ) {

		User user = userService.getEntity( userId );
		Budget budget = getEntity( id );
		user.removeBudget( budget );
		
		dao.delete( budget );
		
	}

	@Override
	@Transactional( readOnly = true )
	public List<Budget> findNotAssociatedToPeriod( Long periodId, Long userId ) {
		return dao.findNotAssociatedToPeriod( periodId, userId );
	}

}
