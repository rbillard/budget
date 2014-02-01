package fr.rbillard.budget.message;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class MessageCreateOperation implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private Long userId;
	private Long periodId;
	private Long budgetId;
	private BigDecimal amount;
	private String date; // TODO date dateFormat
	private String label;
	
	
	@NotNull
	public Long getUserId() {
		return userId;
	}
	public MessageCreateOperation setUserId( Long userId ) {
		this.userId = userId;
		return this;
	}
	
	
	@NotNull
	public Long getPeriodId() {
		return periodId;
	}
	public MessageCreateOperation setPeriodId( Long periodId ) {
		this.periodId = periodId;
		return this;
	}
	
	
	@NotNull
	public Long getBudgetId() {
		return budgetId;
	}
	public MessageCreateOperation setBudgetId( Long budgetId ) {
		this.budgetId = budgetId;
		return this;
	}
	
	
	@NotNull
	public BigDecimal getAmount() {
		return amount;
	}
	public MessageCreateOperation setAmount( BigDecimal amount ) {
		this.amount = amount;
		return this;
	}
	
	
	@NotNull
	public String getDate() {
		return date;
	}
	public MessageCreateOperation setDate( String date ) {
		this.date = date;
		return this;
	}
	
	
	@NotNull
	public String getLabel() {
		return label;
	}
	public MessageCreateOperation setLabel( String label ) {
		this.label = label;
		return this;
	}
	

}
