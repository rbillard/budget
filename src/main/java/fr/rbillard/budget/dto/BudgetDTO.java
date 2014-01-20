package fr.rbillard.budget.dto;

import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.entity.Budget;

public class BudgetDTO {
	
	private final Long id;
	private final String label;

	public BudgetDTO( Budget budget ) {
		this.id = budget.getId();
		this.label = budget.getLabel();
	}
	
	
	public Long getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}

	public static List<BudgetDTO> listBudgets2ListBudgetsDTO( List<Budget> budgets ) {
		
		List<BudgetDTO> budgetsDTO = new ArrayList<BudgetDTO>( budgets.size() );
		
		for ( Budget budget : budgets ) {
			budgetsDTO.add( new BudgetDTO( budget ) );
		}

		return budgetsDTO;
		
	}

}
