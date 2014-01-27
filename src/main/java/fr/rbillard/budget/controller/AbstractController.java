package fr.rbillard.budget.controller;

import static fr.rbillard.budget.dto.BudgetDTO.listBudgets2ListBudgetsDTO;
import static fr.rbillard.budget.dto.BudgetDTO.listPeriodsBudgets2ListBudgetsDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import fr.rbillard.budget.auth.SimpleUserDetails;
import fr.rbillard.budget.dto.TypeBudgets;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IPeriodBudgetService;

@PropertySource({ "classpath:/application.properties" })
public abstract class AbstractController {
	
	// FIXME trouver solution pour mettre en conf
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String APPLICATION_JSON = "application/json";
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private IBudgetService budgetService;
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	protected Environment getEnvironment() {
		return environment;
	}
	
	@ModelAttribute( "context" )
	public String context() {
		return environment.getProperty( "context" );
	}
	
	protected User getConnectedUser() {
		return ( (SimpleUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getUser();
	}
	protected Long getConnectedUserId() {
		return getConnectedUser().getId();
	}
	
	protected TypeBudgets getTypeBudgets( Long periodId ) {
		
		List<Budget> budgetsNotAssociated = budgetService.findNotAssociatedToPeriod( periodId, getConnectedUserId() );
		List<PeriodBudget> budgetsAssociated = periodBudgetService.findAssociatedToPeriod( periodId, getConnectedUserId() );
		
		return new TypeBudgets(
			listBudgets2ListBudgetsDTO( budgetsNotAssociated ),
			listPeriodsBudgets2ListBudgetsDTO( budgetsAssociated )
		);
		
	}

}
