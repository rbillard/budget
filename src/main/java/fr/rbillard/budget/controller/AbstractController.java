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
import fr.rbillard.budget.dto.PeriodFullDTO;
import fr.rbillard.budget.dto.TypeBudgetsDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.budget.service.IPeriodService;

@PropertySource({ "classpath:/application.properties" })
public abstract class AbstractController {
	
	public static final String APPLICATION_JSON = "application/json";
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private IPeriodService periodService;
	
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
	
	protected PeriodFullDTO getPeriodDTO( Long periodId ) {
		Period period = periodService.getEntity( periodId );
		return new PeriodFullDTO( period, getTypeBudgets( periodId ) );
	}
	
	protected TypeBudgetsDTO getTypeBudgets( Long periodId ) {
		
		List<Budget> budgetsNotAssociated = budgetService.findNotAssociatedToPeriod( periodId, getConnectedUserId() );
		List<PeriodBudget> budgetsAssociated = periodBudgetService.findAssociatedToPeriod( periodId, getConnectedUserId() );
		
		return new TypeBudgetsDTO(
			listBudgets2ListBudgetsDTO( budgetsNotAssociated ),
			listPeriodsBudgets2ListBudgetsDTO( budgetsAssociated )
		);
		
	}
	
	
	// getters
	
	protected IPeriodService getPeriodService() {
		return periodService;
	}
	protected IBudgetService getBudgetService() {
		return budgetService;
	}
	protected IPeriodBudgetService getPeriodBudgetService() {
		return periodBudgetService;
	}
	
}
