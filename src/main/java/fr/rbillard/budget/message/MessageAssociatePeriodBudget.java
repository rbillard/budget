package fr.rbillard.budget.message;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class MessageAssociatePeriodBudget implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private Long userId;
	private Long periodId;
	private Long budgetId;
	private BigDecimal amount;
	
	
	@NotNull
	public Long getUserId() {
		return userId;
	}
	public MessageAssociatePeriodBudget setUserId( Long userId ) {
		this.userId = userId;
		return this;
	}


	@NotNull
	public Long getPeriodId() {
		return periodId;
	}
	public MessageAssociatePeriodBudget setPeriodId( Long periodId ) {
		this.periodId = periodId;
		return this;
	}


	@NotNull
	public Long getBudgetId() {
		return budgetId;
	}
	public MessageAssociatePeriodBudget setBudgetId( Long budgetId ) {
		this.budgetId = budgetId;
		return this;
	}


	@NotNull
	public BigDecimal getAmount() {
		return amount;
	}
	public MessageAssociatePeriodBudget setAmount( BigDecimal amount ) {
		this.amount = amount;
		return this;
	}
	
}
