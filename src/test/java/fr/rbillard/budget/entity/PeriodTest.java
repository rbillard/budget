package fr.rbillard.budget.entity;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class PeriodTest {
	
	@Test
	public void testGetTotalAmount() throws Exception {
		
		// given
		Period period = getPeriodWithBudgets();
		
		// when
		BigDecimal totalAmount = period.getTotalAmount();
		
		// then
		assertEquals( BigDecimal.valueOf( 20 ), totalAmount );

	}

	@Test
	public void testGetTotalConsumedAmount() throws Exception {
		
		// given
		Period period = getPeriodWithBudgets();
		
		// when
		BigDecimal totalConsumedAmount = period.getTotalConsumedAmount();
		
		// then
		assertEquals( BigDecimal.valueOf( 8 ), totalConsumedAmount );
				
	}

	@Test
	public void testGetRemainingAmount() throws Exception {
		
		// given
		Period period = getPeriodWithBudgets();
		
		// when
		BigDecimal totalRemainingAmount = period.getRemainingAmount();
		
		// then
		assertEquals( BigDecimal.valueOf( 12 ), totalRemainingAmount );

	}
	
	private Period getPeriodWithBudgets() {
		
		Period period = new Period();

		PeriodBudget periodBudget1 = new PeriodBudget();
		periodBudget1.setAmount( BigDecimal.TEN );
		Operation operation = new Operation();
		operation.setAmount( BigDecimal.valueOf( 8 ) );
		periodBudget1.addOperation( operation );
		
		PeriodBudget periodBudget2 = new PeriodBudget();
		periodBudget2.setAmount( BigDecimal.TEN );
		
		period.addlBudget( periodBudget1 );
		period.addlBudget( periodBudget2 );
		
		return period;
		
	}

}
