package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.app.AppConstants;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.utils.DateUtils;

public class PeriodDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String label;
	private String startDate;
	private String endDate;
	private TypeBudgets typeBudgets;
	private List<OperationDTO> operations;
	private BigDecimal totalAmount;
	private BigDecimal totalConsumedAmount;
	private BigDecimal remainingAmount;
	
	
	public PeriodDTO() {
		System.out.println();
	}
	public PeriodDTO( Period period, TypeBudgets typeBudgets ) {
		
		this.id = period.getId();
		this.label = period.getLabel();
		this.startDate = DateUtils.dateToString( period.getStartDate(), AppConstants.FORMAT_DATE );
		this.endDate = DateUtils.dateToString( period.getEndDate(), AppConstants.FORMAT_DATE );
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
	public PeriodDTO( Period period ) {
		this( period, null );
	}


	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}

	
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}


	public String getStartDate() {
		return startDate;
	}
	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}
	

	public String getEndDate() {
		return endDate;
	}
	public void setEndDate( String endDate ) {
		this.endDate = endDate;
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
	
	
	public static List<PeriodDTO> listPeriods2ListPeriodsDTO( List<Period> periods ) {
		
		List<PeriodDTO> periodsDTO = new ArrayList<PeriodDTO>( periods.size() );
		
		for ( Period period : periods ) {
			periodsDTO.add( new PeriodDTO( period ) );
		}
		
		return periodsDTO;
		
	}
	
}
