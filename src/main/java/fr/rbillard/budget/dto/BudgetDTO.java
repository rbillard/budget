package fr.rbillard.budget.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.PeriodBudget;

public class BudgetDTO {
	
	private final Long id;
	private final String label;
	private final BigDecimal amount;

	public BudgetDTO( Budget budget ) {
		this.id = budget.getId();
		this.label = budget.getLabel();
		this.amount = null;
	}
	public BudgetDTO( PeriodBudget periodBudget ) {
		this.id = periodBudget.getId().getBudget().getId();
		this.label = periodBudget.getId().getBudget().getLabel();
		this.amount = periodBudget.getAmount();
	}
	
	
	public Long getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public BigDecimal getAmount() {
		return amount;
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
