package fr.rbillard.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IPeriodBudgetDAO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.service.impl.GenericService;

@Service
public class PeriodBudgetService extends GenericService<PeriodBudget, PeriodBudgetId, IPeriodBudgetDAO> implements IPeriodBudgetService {

	@Autowired
	private IPeriodBudgetDAO dao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPeriodService periodService;
	
	@Autowired
	private IBudgetService budgetService;
	
	@Override
	public IPeriodBudgetDAO getDao() {
		return dao;
	}

	@Override
	public void setDao( IPeriodBudgetDAO dao ) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public PeriodBudget associatePeriodBudget( MessageAssociatePeriodBudget message ) {

		// TODO assert valid
		
		Period period = periodService.getEntity( message.getPeriodId() );
		User user = userService.getEntity( message.getUserId() );
		Budget budget = budgetService.getEntity( message.getBudgetId() );
		
		if ( ! user.equals( period.getUser() ) || ! user.equals( budget.getUser() ) ) {
			// TODO exception
		}
		
		// TODO check period and budget already associated
		
		PeriodBudget periodBudget = new PeriodBudget( period, budget, message.getAmount() );
		period.addlBudget( periodBudget );
		budget.addlBudget( periodBudget );
		create( periodBudget );
		
		return periodBudget;
		
	}

}
