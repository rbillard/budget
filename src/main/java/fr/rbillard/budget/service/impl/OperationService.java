package fr.rbillard.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IOperationDAO;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.message.MessageCreateOperation;
import fr.rbillard.budget.service.IOperationService;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.service.impl.GenericService;

@Service
public class OperationService extends GenericService<Operation, Long, IOperationDAO> implements IOperationService {

	@Autowired
	private IOperationDAO dao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Override
	public IOperationDAO getDao() {
		return dao;
	}

	@Override
	public void setDao( IOperationDAO dao ) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public Operation create( MessageCreateOperation message ) {
		
		// TODO assert valid message
		
		PeriodBudget periodBudget = periodBudgetService.getEntity( message.getUserId(), message.getPeriodId(), message.getBudgetId() );
		
		Operation operation = new Operation();
		operation.setPeriodBudget( periodBudget );
		operation.setAmount( message.getAmount() );
		operation.setDate( message.getDate() );
		operation.setLabel( message.getLabel() );
		
		periodBudget.addOperation( operation );
		
		create( operation );

		return operation;
		
	}

	@Override
	@Transactional
	public void delete( Long id, Long userId ) {
		
		Operation operation = dao.getOperation( id, userId );
		
		operation.getPeriodBudget().removeOperation( operation );
		
		dao.delete( operation );
		
	}

}
