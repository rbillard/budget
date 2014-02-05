package fr.rbillard.budget.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.exception.NoSuchEntityException;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

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
	public void testFindByUser() throws Exception {
		
		// when
		List<Budget> budgets = budgetService.findByUser( user1.getId() );
		
		// then
		assertEquals( 3, budgets.size() );
		assertTrue( budgets.contains( budget1 ) );
		assertTrue( budgets.contains( budget2 ) );
		assertTrue( budgets.contains( budget3 ) );

	}

	@Test
	public void testDelete() throws Exception {
		
		// when
		Long budgetId = budget1.getId();
		budgetService.delete( budgetId, user1.getId() );
		Budget loadedBudget = budgetService.getEntity( budgetId );

		// then
		assertNull( loadedBudget );
		
	}

	@Test
	public void testUpdate() throws Exception {
		
		// given
		BudgetDTO dto = getDefaultBudgetDTO()
			.setId( budget1.getId() );
		
		// when
		Budget modifiedBudget = budgetService.update( dto, user1.getId() );
		
		// then
		assertEquals( dto.getId(), modifiedBudget.getId() );
		assertEquals( dto.getLabel(), modifiedBudget.getLabel() );

	}

	@Test
	public void testCreate() throws Exception {
		
		// when
		BudgetDTO dto = getDefaultBudgetDTO();
		Long userId = user1.getId();
		Budget createdBudget = budgetService.create( dto, userId );
		List<Budget> budgets = budgetService.findByUser( userId );
		
		// then
		assertNotNull( createdBudget.getId() );
		assertEquals( dto.getLabel(), createdBudget.getLabel() );
		assertEquals( 4, budgets.size() );
		assertTrue( budgets.contains( createdBudget ) );

	}
	
	@Test( expected = ConstraintViolationFunctionalException.class )
	public void testCreate_ConstraintViolation() throws Exception {
		
		// when
		budgetService.create( new BudgetDTO(), null );
		
		// then exception
		
	}
	
	private BudgetDTO getDefaultBudgetDTO() {
		return new BudgetDTO()
			.setLabel( "Budget DTO" );
	}

	@Test
	public void testGetBudget() throws Exception {
		
		// when
		Budget reloadedBudget = budgetService.getBudget( budget1.getId(), user1.getId() );
		
		// then
		assertEquals( budget1, reloadedBudget );

	}
	
	@Test( expected = NoSuchEntityException.class )
	public void testGetBudget_NoSuchEntityException() throws Exception {
		
		// given
		User user1 = newUser( "user1" );
		Budget budget = newBudget( user1 );
		User user2 = newUser( "user2" );
		
		// when
		budgetService.getBudget( budget.getId(), user2.getId() );
		
		// then exception
		
	}

}
