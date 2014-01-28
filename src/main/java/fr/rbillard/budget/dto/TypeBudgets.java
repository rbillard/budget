package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.util.List;

public class TypeBudgets implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private List<BudgetDTO> budgetsNotAssociated;
	private List<BudgetDTO> budgetsAssociated;
	
	
	public TypeBudgets() {
		System.out.println();
	}
	public TypeBudgets( List<BudgetDTO> budgetsNotAssociated, List<BudgetDTO> budgetsAssociated ) {
		this.budgetsNotAssociated = budgetsNotAssociated;
		this.budgetsAssociated = budgetsAssociated;
	}
	
	
	public List<BudgetDTO> getBudgetsNotAssociated() {
		return budgetsNotAssociated;
	}
	public void setBudgetsNotAssociated( List<BudgetDTO> budgetsNotAssociated ) {
		this.budgetsNotAssociated = budgetsNotAssociated;
	}
	
	
	public List<BudgetDTO> getBudgetsAssociated() {
		return budgetsAssociated;
	}
	public void setBudgetsAssociated( List<BudgetDTO> budgetsAssociated ) {
		this.budgetsAssociated = budgetsAssociated;
	}
	
}
