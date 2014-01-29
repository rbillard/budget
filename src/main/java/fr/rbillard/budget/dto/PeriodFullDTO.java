package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;

public final class PeriodFullDTO extends PeriodLightDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private TypeBudgets typeBudgets;
	private List<OperationDTO> operations;
	private BigDecimal totalAmount;
	private BigDecimal totalConsumedAmount;
	private BigDecimal remainingAmount;
	
	
	public PeriodFullDTO() {
		
	}
	public PeriodFullDTO( Period period, TypeBudgets typeBudgets ) {
		super( period );
		this.totalAmount = period.getTotalAmount();
		this.totalConsumedAmount = period.getTotalConsumedAmount();
		this.remainingAmount = period.getRemainingAmount();

		this.typeBudgets = typeBudgets;
		
		this.operations = new ArrayList<OperationDTO>();
		for ( PeriodBudget periodBudget : period.getlBudgets() ) {
			for ( Operation operation : periodBudget.getOperations() ) {
				operations.add( new OperationDTO( operation ) );
			}
		}
		
	}
	public PeriodFullDTO( Period period ) {
		this( period, null );
	}


	public TypeBudgets getTypeBudgets() {
		return typeBudgets;
	}
	public void setTypeBudgets( TypeBudgets typeBudgets ) {
		this.typeBudgets = typeBudgets;
	}
	
	
	public List<OperationDTO> getOperations() {
		return operations;
	}
	public void setOperations( List<OperationDTO> operations ) {
		this.operations = operations;
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
	
	
	public static List<PeriodFullDTO> listPeriods2ListPeriodsDTO( List<Period> periods ) {
		
		List<PeriodFullDTO> periodsDTO = new ArrayList<PeriodFullDTO>( periods.size() );
		
		for ( Period period : periods ) {
			periodsDTO.add( new PeriodFullDTO( period ) );
		}
		
		return periodsDTO;
		
	}
	
}
