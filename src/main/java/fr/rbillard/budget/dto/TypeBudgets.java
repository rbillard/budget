package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.util.List;

public class TypeBudgets implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private final List<BudgetDTO> budgetsNotAssociated;
	private final List<BudgetDTO> budgetsAssociated;
	
	
	public TypeBudgets( List<BudgetDTO> budgetsNotAssociated, List<BudgetDTO> budgetsAssociated ) {
		this.budgetsNotAssociated = budgetsNotAssociated;
		this.budgetsAssociated = budgetsAssociated;
	}
	
	
	public List<BudgetDTO> getBudgetsNotAssociated() {
		return budgetsNotAssociated;
	}
	public List<BudgetDTO> getBudgetsAssociated() {
		return budgetsAssociated;
	}

	
}
