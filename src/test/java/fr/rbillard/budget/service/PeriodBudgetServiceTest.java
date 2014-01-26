package fr.rbillard.budget.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;

public class PeriodBudgetServiceTest extends AbstractTest {
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Test
	public void a() {
		// TODO
		assertTrue( false );
	}
	
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

}
