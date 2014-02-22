package fr.rbillard.budget.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.message.MessageCreateOperation;
import fr.rbillard.budget.service.IOperationService;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

public class OperationServiceTest extends AbstractTest {
	
	@Autowired
	private IOperationService operationService;
	
	@Test
	public void testCreate() throws Exception {
		
		// given
		DateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		User user = newUserWithOperation();
		Period period = user.getPeriods().get( 0 );
		Budget budget = user.getBudgets().get( 0 );
		Date date = df.parse( "01/01/2014" );
		String label = "New operation";
		BigDecimal amount = BigDecimal.TEN;
		MessageCreateOperation message = new MessageCreateOperation()
			.setUserId( user.getId() )
			.setPeriodId( period.getId() )
			.setBudgetId( budget.getId() )
			.setDate( date )
			.setLabel( label )
			.setAmount( amount );
		
		// when
		Operation operation = operationService.create( message );
		
		// then
		assertNotNull( operation.getId() );
		assertEquals( period, operation.getPeriodBudget().getId().getPeriod() );
		assertEquals( budget, operation.getPeriodBudget().getId().getBudget() );
		assertEquals( date, operation.getDate() );
		assertEquals( label, operation.getLabel() );
		assertEquals( amount, operation.getAmount() );
		
	}
	
	@Test( expected = ConstraintViolationFunctionalException.class )
	public void testCreate_ConstraintViolation() throws Exception {
		
		// when
		operationService.create( new MessageCreateOperation() );
		
		// then exception
		
	}

	@Test
	public void testDelete() throws Exception {
		
		// given
		User user = newUserWithOperation();
		Operation operation = user.getPeriods().get( 0 ).getlBudgets().get( 0 ).getOperations().get( 0 );
		
		// when
		operationService.delete( operation.getId(), user.getId() );
		Operation reloadedOperation = operationService.getEntity( operation.getId() );
		
		// then
		assertNull( reloadedOperation );

	}

	@Test
	public void testGetOperation() throws Exception {
		
		// given
		User user = newUserWithOperation();
		Operation operation = user.getBudgets().get( 0 ).getlPeriod().get( 0 ).getOperations().get( 0 );
		
		// when
		Operation loadedOperation = operationService.getOperation( operation.getId(), user.getId() );
		
		// then
		assertEquals( operation, loadedOperation );
		
	}

}
