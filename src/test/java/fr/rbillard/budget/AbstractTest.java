package fr.rbillard.budget;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.rbillard.budget.conf.BudgetTestConfiguration;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IOperationService;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.budget.service.IUserService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { BudgetTestConfiguration.class } )
public abstract class AbstractTest {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBudgetService budgetService;
	
	@Autowired
	private IPeriodService periodService;
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Autowired
	private IOperationService operationService;
	
	protected User newUser() {
		return newUser( "rbillard" );
	}
	
	protected User newUser( String login ) {
		
		User user = new User();
		user.setLogin( login );
		user.setPassword( "password" );
		userService.create( user );
		
		return user;
		
	}
	
	protected User newUserWithOperation() {
		
		User user = newUser();
		Period period = newPeriod( user );
		Budget budget = newBudget( user );
		PeriodBudget periodBudget = newPeriodBudget( period, budget );
		newOperation( periodBudget );
		
		return user;
		
	}
	
	protected Budget newBudget( User user ) {
		
		Budget budget = new Budget();
		budget.setUser( user );
		budget.setLabel( "Lable budget" );
		user.addBudget( budget );
		budgetService.create( budget );
		
		return budget;
		
	}
	
	protected Period newPeriod( User user ) {
		
		Period period = new Period();
		period.setUser( user );
		period.setLabel( "Label period" );
		period.setStartDate( new Date() );
		period.setEndDate( new Date() );
		user.addPeriod( period );
		periodService.create( period );
		
		return period;
	}
	
	protected PeriodBudget newPeriodBudget( Period period, Budget budget ) {
		
		PeriodBudget periodBudget = new PeriodBudget( period, budget, BigDecimal.TEN );
		period.addlBudget( periodBudget );
		budget.addlBudget( periodBudget );
		periodBudgetService.create( periodBudget );
		
		return periodBudget;
		
	}
	
	protected Operation newOperation( PeriodBudget periodBudget ) {
		
		Operation operation = new Operation();
		operation.setAmount( BigDecimal.TEN );
		operation.setDate( new Date() );
		operation.setLabel( "Achat matériel" );
		operation.setPeriodBudget( periodBudget );
		
		periodBudget.addOperation( operation );
		
		operationService.create( operation );
		
		return operation;
		
	}

}
