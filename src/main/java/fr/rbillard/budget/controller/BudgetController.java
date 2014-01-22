package fr.rbillard.budget.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.service.IBudgetService;

@Controller
@RequestMapping( value = "/budget" )
public class BudgetController extends AbstractController {
	
	@Autowired
	private IBudgetService budgetService;
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	public @ResponseBody List<BudgetDTO> getBudgets() {
		List<Budget> budgets = budgetService.findByUser( getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody BudgetDTO getBudget( @PathVariable( value = "id" ) Long id ) {
		Budget budget = budgetService.getEntity( id );
		return new BudgetDTO( budget );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public @ResponseBody List<BudgetDTO> deleteBudget( @PathVariable( value = "id" ) Long id ) {
		
		budgetService.delete( id, getConnectedUserId() );
		
		// TODO factoriser
		List<Budget> budgets = budgetService.findByUser( getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/period/{periodId}", method = RequestMethod.GET )
	public @ResponseBody List<BudgetDTO> findBudgetsNotAssociatedToPeriod( @PathVariable( value = "periodId" ) Long periodId ) {
		
		List<Budget> budgets = budgetService.findNotAssociatedToPeriod( periodId, getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
		
	}
	
	
	// FULL REST
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody BudgetDTO createBudget( @RequestBody Budget budget ) {
		budget.setUser( getConnectedUser() );
		budgetService.create( budget );
		return new BudgetDTO( budget );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	public @ResponseBody BudgetDTO updateBudget( @RequestBody Budget budget ) {
		budget.setUser( getConnectedUser() );
		budgetService.update( budget );
		return new BudgetDTO( budget );
	}

}
