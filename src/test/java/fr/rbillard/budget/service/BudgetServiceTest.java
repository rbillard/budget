package fr.rbillard.budget.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;

public class BudgetServiceTest extends AbstractTest {
	
	@Autowired
	private IBudgetService budgetService;
	
	@Autowired
	private IUserService userService;
	
	private User user1;
	private User user2;

	private Period period1;
	private Period period2;

	private Budget budget1;
	private Budget budget2;
	private Budget budget3;
	private Budget budget4;
	
	
	@Before
	public void setUp() {

		user1 = newUser( "rbillard" );
		user2 = newUser( "chebika" );
		
		period1 = newPeriod( user1 );
		budget1 = newBudget( user1 );
		budget2 = newBudget( user1 );
		budget3 = newBudget( user1 );
		period2 = newPeriod( user1 );
		
		budget4 = newBudget( user2 );
		
		newPeriodBudget( period1, budget2 );
		newPeriodBudget( period2, budget3 );
		
	}
	
	@After
	public void tearDown() {

		userService.delete( user1 );
		userService.delete( user2 );
		
	}
	
	@Test
	public void todo() {
		// TODO
		assertTrue( false );
	}
	
	@Test
	public void testFindNotAssociatedToPeriod() {
		
		// when
		List<Budget> budgets = budgetService.findNotAssociatedToPeriod( period1.getId(), user1.getId() );
		
		// then
		assertEquals( 2, budgets.size() );
		assertTrue( budgets.contains( budget1 ) );
		assertTrue( budgets.contains( budget3 ) );
		assertFalse( "budget2 already added", budgets.contains( budget2 ) );
		assertFalse( "budget4 owned by another user", budgets.contains( budget4 ) );
		
	}
	
	@Test
	public void testFindAssociatedToPeriod() {
		
		// when
		List<Budget> budgets = budgetService.findAssociatedToPeriod( period1.getId(), user1.getId() );
		assertEquals( 1, budgets.size() );
		assertTrue( budgets.contains( budget2 ) );
		
	}
	

}
