package fr.rbillard.budget.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.User;

@Transactional
public class OperationDAOTest extends AbstractTest {

	@Autowired
	private IOperationDAO dao;
	
	@Test
	public void testGetOperation() {
		
		// given
		User user = newUserWithOperation();
		Operation operation = user.getBudgets().get( 0 ).getlPeriod().get( 0 ).getOperations().get( 0 );
		
		// when
		Operation loadedOperation = dao.getOperation( operation.getId(), user.getId() );
		
		// then
		assertEquals( operation, loadedOperation );
		
	}
	
}
