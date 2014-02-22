package fr.rbillard.budget.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.budget.service.IOperationService;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.springhibernate.domain.exception.FunctionalException;

public class PeriodBudgetServiceTest extends AbstractTest {
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Autowired
	private IOperationService operationService;
	
	@Test
	public void testGetEntity() {
		
		// given
		User user = newUser( "rbillard" );
		Period period = newPeriod( user );
		Budget budget = newBudget( user );
		PeriodBudget periodBudget = newPeriodBudget( period, budget );
		
		// when
		PeriodBudget loadedPeriodBudget = periodBudgetService.getEntity( user.getId(), period.getId(), budget.getId() );
		
		// then
		assertEquals( periodBudget, loadedPeriodBudget );
		assertEquals( period, loadedPeriodBudget.getId().getPeriod() );
		assertEquals( budget, loadedPeriodBudget.getId().getBudget() );
		
	}
	
	@Test
	public void testFindAssociatedToPeriod() {
		
		// given
		User user = newUserWithOperation();
		Period period = user.getPeriods().get( 0 );
		Budget budget = user.getBudgets().get( 0 );
		
		// when
		List<PeriodBudget> periodsBudgets = periodBudgetService.findAssociatedToPeriod( period.getId(), user.getId() );
		PeriodBudget periodBudget = periodsBudgets.get( 0 );
		
		// then
		assertEquals( 1, periodsBudgets.size() );
		assertEquals( period, periodBudget.getId().getPeriod() );
		assertEquals( budget, periodBudget.getId().getBudget() );

	}

	@Test
	public void testAssociatePeriodBudget() throws Exception {
		
		// given
		User user = newUser();
		Period period = newPeriod( user );
		Budget budget = newBudget( user );
		BigDecimal amount = BigDecimal.TEN;
		MessageAssociatePeriodBudget message = new MessageAssociatePeriodBudget()
			.setUserId( user.getId() )
			.setPeriodId( period.getId() )
			.setBudgetId( budget.getId() )
			.setAmount( amount );
		
		// when
		PeriodBudget createdPeriodBudget = periodBudgetService.associatePeriodBudget( message );
		PeriodBudget loadedPeriodBudget = periodBudgetService.getEntity( user.getId(), period.getId(), budget.getId() );
		
		// then
		assertEquals( amount, createdPeriodBudget.getAmount() );
		assertEquals( period, createdPeriodBudget.getId().getPeriod() );
		assertEquals( budget, createdPeriodBudget.getId().getBudget() );
		assertEquals( loadedPeriodBudget, createdPeriodBudget );
		
	}
	
	@Test( expected = FunctionalException.class )
	public void testAssociatePeriodBudget_Incompatible() throws Exception {
		
		// given
		User user1 = newUser( "user1" );
		Period period = newPeriod( user1 );
		
		User user2 = newUser( "user2" );
		Budget budget = newBudget( user2 );
		
		MessageAssociatePeriodBudget message = new MessageAssociatePeriodBudget()
			.setUserId( user1.getId() )
			.setPeriodId( period.getId() )
			.setBudgetId( budget.getId() )
			.setAmount( BigDecimal.TEN );
		
		// when
		periodBudgetService.associatePeriodBudget( message );
		
	}
	
	@Test( expected = FunctionalException.class )
	public void testAssociatePeriodBudget_AlreadyAssociated() throws Exception {
		
		// givne
		User user = newUser();
		Period period = newPeriod( user );
		Budget budget = newBudget( user );
		BigDecimal amount = BigDecimal.TEN;
		MessageAssociatePeriodBudget message = new MessageAssociatePeriodBudget()
			.setUserId( user.getId() )
			.setPeriodId( period.getId() )
			.setBudgetId( budget.getId() )
			.setAmount( amount );
		
		periodBudgetService.associatePeriodBudget( message );
		
		// when
		periodBudgetService.associatePeriodBudget( message );
		
		// then exception
				
	}

	@Test
	public void testDissociate() throws Exception {
		
		// given
		User user = newUserWithOperation();
		Period period = user.getPeriods().get( 0 );
		Budget budget = user.getBudgets().get( 0 );
		Operation operation = period.getlBudgets().get( 0 ).getOperations().get( 0 );
		
		// when
		periodBudgetService.dissociate( period.getId(), budget.getId(), user.getId() );
		PeriodBudget loadedPeriodBudget = periodBudgetService.getEntity( user.getId(), period.getId(), budget.getId() );
		Operation loadedOperation = operationService.getEntity( operation.getId() );
		
		// then
		assertNull( loadedPeriodBudget );
		assertNull( loadedOperation );

	}

}
