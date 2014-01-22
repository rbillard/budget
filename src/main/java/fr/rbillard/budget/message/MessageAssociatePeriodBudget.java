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
	public void setUserId( Long userId ) {
		this.userId = userId;
	}


	@NotNull
	public Long getPeriodId() {
		return periodId;
	}
	public void setPeriodId( Long periodId ) {
		this.periodId = periodId;
	}


	@NotNull
	public Long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId( Long budgetId ) {
		this.budgetId = budgetId;
	}


	@NotNull
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	
}
