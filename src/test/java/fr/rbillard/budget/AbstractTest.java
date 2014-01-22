package fr.rbillard.budget;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.rbillard.budget.conf.BudgetTestConfiguration;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
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
	
	protected User newUser( String login ) {
		
		User user = new User();
		user.setLogin( login );
		user.setPassword( "password" );
		userService.create( user );
		
		return user;
		
	}
	
	protected Budget newBudget( User user ) {
		
		Budget budget = new Budget();
		budget.setUser( user );
		budget.setLabel( "Lable budget" );
		budgetService.create( budget );
		
		return budget;
		
	}
	
	protected Period newPeriod( User user ) {
		
		Period period = new Period();
		period.setUser( user );
		period.setLabel( "Label period" );
		period.setStartDate( new Date() );
		period.setEndDate( new Date() );
		periodService.create( period );
		
		return period;
	}

}
