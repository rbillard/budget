package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.PeriodBudget;

public class BudgetDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String label;
	private String color;
	private BigDecimal amount;
	private BigDecimal consumedAmount;
	private BigDecimal remainingAmount;
	private List<OperationDTO> operations;

	public BudgetDTO() {
		
	}
	public BudgetDTO( Budget budget ) {
		this.id = budget.getId();
		this.label = budget.getLabel();
		this.color = budget.getColor();
		this.amount = null;
	}
	public BudgetDTO( PeriodBudget periodBudget ) {
		this.id = periodBudget.getId().getBudget().getId();
		this.label = periodBudget.getId().getBudget().getLabel();
		this.color = periodBudget.getId().getBudget().getColor();
		this.amount = periodBudget.getAmount();
		this.consumedAmount = periodBudget.getConsumedAmount();
		this.remainingAmount = periodBudget.getRemainingAmount();
		this.operations = OperationDTO.listOperation2ListOperationDTO( periodBudget.getOperations() );
	}
	
	
	public Long getId() {
		return id;
	}
	public BudgetDTO setId( Long id ) {
		this.id = id;
		return this;
	}
	
	
	@NotBlank
	public String getLabel() {
		return label;
	}
	public BudgetDTO setLabel( String label ) {
		this.label = label;
		return this;
	}
	
	
	@NotBlank
	public String getColor() {
		return color;
	}
	public BudgetDTO setColor( String color ) {
		this.color = color;
		return this;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public BudgetDTO setAmount( BigDecimal amount ) {
		this.amount = amount;
		return this;
	}
	
	
	public BigDecimal getConsumedAmount() {
		return consumedAmount;
	}
	public BudgetDTO setConsumedAmount( BigDecimal consumedAmount ) {
		this.consumedAmount = consumedAmount;
		return this;
	}
	
	
	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}
	public BudgetDTO setRemainingAmount( BigDecimal remainingAmount ) {
		this.remainingAmount = remainingAmount;
		return this;
	}
	
	
	public List<OperationDTO> getOperations() {
		if ( operations == null ) {
			operations = new ArrayList<>();
		}
		return operations;
	}
	public BudgetDTO setOperations( List<OperationDTO> operations ) {
		this.operations = operations;
		return this;
	}
	

	public static List<BudgetDTO> listBudgets2ListBudgetsDTO( List<Budget> budgets ) {
		
		List<BudgetDTO> budgetsDTO = new ArrayList<>( budgets.size() );
		
		for ( Budget budget : budgets ) {
			budgetsDTO.add( new BudgetDTO( budget ) );
		}

		return budgetsDTO;
		
	}
	
	public static List<BudgetDTO> listPeriodsBudgets2ListBudgetsDTO( List<PeriodBudget> periodsBudgets ) {

		List<BudgetDTO> budgetsDTO = new ArrayList<>( periodsBudgets.size() );
		
		for ( PeriodBudget periodBudget : periodsBudgets ) {
			budgetsDTO.add( new BudgetDTO( periodBudget ) );
		}
		
		return budgetsDTO;
		
	}

}
