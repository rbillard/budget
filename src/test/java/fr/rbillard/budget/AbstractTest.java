package fr.rbillard.budget;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private String defaultLogin = "rbillard";
	
	@After
	public void tearDown() throws Exception {
		clearDatabase();
	}
	
	@SuppressWarnings( "unchecked" )
	public void clearDatabase() throws Exception {

		TransactionTemplate txTemplate = new TransactionTemplate( transactionManager );              
		txTemplate.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRES_NEW );
		// création d'une transaction
		txTemplate.execute( new TransactionCallback<Object>() {
			
		    public Object doInTransaction( TransactionStatus status ) {
		    	
		    	Session session = sessionFactory.getCurrentSession();
		    	
		    	// récupération des noms des tables du schéma
		    	List<String> tablesName = session.createSQLQuery(
	    			new StringBuilder()
	    				.append( " select table_name " )
	    				.append( " from INFORMATION_SCHEMA.system_tables " )
	    				.append( " where table_type = 'TABLE' and table_schem = 'PUBLIC' " )
	    				.toString()
	    		).list();
		    		
		    	// désactivation des contraintes d'intégrités
	    		session.createSQLQuery( "SET DATABASE REFERENTIAL INTEGRITY FALSE" ).executeUpdate();
	    		
	    		for ( String tableName : tablesName ) {
	    			session.createSQLQuery( "DELETE FROM " + tableName ).executeUpdate();
	    		}

	    		// réactivation des contraintes d'intégrités
	    		session.createSQLQuery( "SET DATABASE REFERENTIAL INTEGRITY TRUE" ).executeUpdate();
	    		
		    	return null;
		    	
		    }
		    
		});
		
	}

	protected User newUser() {
		return newUser( defaultLogin );
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
	
	protected User newUserWithPeriod() {
		return newUserWithPeriod( defaultLogin );
	}
	protected User newUserWithPeriod( String login ) {
		
		User user = newUser( login );
		newPeriod( user );
		
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
