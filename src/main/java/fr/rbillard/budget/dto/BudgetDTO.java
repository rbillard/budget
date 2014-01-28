package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.PeriodBudget;

public class BudgetDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String label;
	private BigDecimal amount;
	private BigDecimal consumedAmount;
	private BigDecimal remainingAmount;
	private List<OperationDTO> operations;

	public BudgetDTO() {
		
	}
	public BudgetDTO( Budget budget ) {
		this.id = budget.getId();
		this.label = budget.getLabel();
		this.amount = null;
	}
	public BudgetDTO( PeriodBudget periodBudget ) {
		this.id = periodBudget.getId().getBudget().getId();
		this.label = periodBudget.getId().getBudget().getLabel();
		this.amount = periodBudget.getAmount();
		this.consumedAmount = periodBudget.getConsumedAmount();
		this.remainingAmount = periodBudget.getRemainingAmount();
		this.operations = OperationDTO.listOperation2ListOperationDTO( periodBudget.getOperations() );
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
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	
	
	public BigDecimal getConsumedAmount() {
		return consumedAmount;
	}
	public void setConsumedAmount( BigDecimal consumedAmount ) {
		this.consumedAmount = consumedAmount;
	}
	
	
	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount( BigDecimal remainingAmount ) {
		this.remainingAmount = remainingAmount;
	}
	
	
	public List<OperationDTO> getOperations() {
		if ( operations == null ) {
			operations = new ArrayList<OperationDTO>();
		}
		return operations;
	}
	public void setOperations( List<OperationDTO> operations ) {
		this.operations = operations;
	}
	

	public static List<BudgetDTO> listBudgets2ListBudgetsDTO( List<Budget> budgets ) {
		
		List<BudgetDTO> budgetsDTO = new ArrayList<BudgetDTO>( budgets.size() );
		
		for ( Budget budget : budgets ) {
			budgetsDTO.add( new BudgetDTO( budget ) );
		}

		return budgetsDTO;
		
	}
	
	public static List<BudgetDTO> listPeriodsBudgets2ListBudgetsDTO( List<PeriodBudget> periodsBudgets ) {

		List<BudgetDTO> budgetsDTO = new ArrayList<BudgetDTO>( periodsBudgets.size() );
		
		for ( PeriodBudget periodBudget : periodsBudgets ) {
			budgetsDTO.add( new BudgetDTO( periodBudget ) );
		}
		
		return budgetsDTO;
		
	}

}
