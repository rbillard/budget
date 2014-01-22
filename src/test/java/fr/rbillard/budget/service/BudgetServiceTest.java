package fr.rbillard.budget.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;

public class BudgetServiceTest extends AbstractTest {
	
	@Autowired
	private IBudgetService budgetService;
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Test
	public void todo() {
		// TODO
		assertTrue( false );
	}
	
	@Test
	public void testFindNotAssociateToPeriod() {
		
		// given
		User user1 = newUser( "rbillard" );
		Period period1 = newPeriod( user1 );
		Budget budget1 = newBudget( user1 );
		Budget budget2 = newBudget( user1 );
		Budget budget3 = newBudget( user1 );
		Period period2 = newPeriod( user1 );
		
		User user2 = newUser( "chebika" );
		Budget budget4 = newBudget( user2 );
		
		PeriodBudget periodBudget1 = new PeriodBudget( period1, budget2, BigDecimal.TEN );
		periodBudgetService.create( periodBudget1 );
		PeriodBudget periodBudget2 = new PeriodBudget( period2, budget3, BigDecimal.TEN );
		periodBudgetService.create( periodBudget2 );
		
		// when
		List<Budget> budgets = budgetService.findNotAssociatedToPeriod( period1.getId(), user1.getId() );
		
		// then
		assertEquals( 2, budgets.size() );
		assertTrue( budgets.contains( budget1 ) );
		assertTrue( budgets.contains( budget3 ) );
		assertFalse( "budget2 already added", budgets.contains( budget2 ) );
		assertFalse( "budget4 owned by another user", budgets.contains( budget4 ) );
		
	}

}
