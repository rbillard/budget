package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import fr.rbillard.budget.entity.Period;

public final class PeriodFullDTO extends PeriodLightDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private TypeBudgetsDTO typeBudgets;
	private BigDecimal totalAmount;
	private BigDecimal totalConsumedAmount;
	private BigDecimal remainingAmount;
	
	
	public PeriodFullDTO() {
		
	}
	public PeriodFullDTO( Period period, TypeBudgetsDTO typeBudgets ) {
		super( period );
		this.totalAmount = period.getTotalAmount();
		this.totalConsumedAmount = period.getTotalConsumedAmount();
		this.remainingAmount = period.getRemainingAmount();
		this.typeBudgets = typeBudgets;
	}


	public TypeBudgetsDTO getTypeBudgets() {
		return typeBudgets;
	}
	public void setTypeBudgets( TypeBudgetsDTO typeBudgets ) {
		this.typeBudgets = typeBudgets;
	}
	
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount( BigDecimal totalAmount ) {
		this.totalAmount = totalAmount;
	}
	
	
	public BigDecimal getTotalConsumedAmount() {
		return totalConsumedAmount;
	}
	public void setTotalConsumedAmount( BigDecimal totalConsumedAmount ) {
		this.totalConsumedAmount = totalConsumedAmount;
	}
	
	
	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount( BigDecimal remainingAmount ) {
		this.remainingAmount = remainingAmount;
	}
	
	
}
