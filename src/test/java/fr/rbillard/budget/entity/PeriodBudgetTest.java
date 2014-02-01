package fr.rbillard.budget.entity;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class PeriodBudgetTest {

	@Test
	public void testGetConsumedAmount() throws Exception {
		
		// given
		PeriodBudget periodBudget = getPeriodBudget();
		
		// when
		BigDecimal consumedAmount = periodBudget.getConsumedAmount();
		
		// then
		assertEquals( BigDecimal.valueOf( 16 ), consumedAmount );

	}

	@Test
	public void testGetRemainingAmount() throws Exception {

		// given
		PeriodBudget periodBudget = getPeriodBudget();
		
		// when
		BigDecimal remainingAmount = periodBudget.getRemainingAmount();
		
		// then
		assertEquals( BigDecimal.valueOf( -6 ), remainingAmount );

	}
	
	private PeriodBudget getPeriodBudget() {
		
		PeriodBudget periodBudget = new PeriodBudget();
		periodBudget.setAmount( BigDecimal.TEN );
		
		for ( int i = 0; i < 2; i++ ) {
			Operation operation = new Operation();
			operation.setAmount( BigDecimal.valueOf( 8 ) );
			periodBudget.addOperation( operation );
		}
		
		return periodBudget;
		
	}

}
