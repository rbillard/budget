package fr.rbillard.budget.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

@Controller
@RequestMapping( value = "/budget" )
public class BudgetController extends AbstractController {
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	public @ResponseBody List<BudgetDTO> getBudgets() {
		List<Budget> budgets = getBudgetService().findByUser( getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody BudgetDTO getBudget( @PathVariable( value = "id" ) Long id ) {
		Budget budget = getBudgetService().getEntity( id );
		return new BudgetDTO( budget );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public @ResponseBody List<BudgetDTO> deleteBudget( @PathVariable( value = "id" ) Long id ) {
		
		getBudgetService().delete( id, getConnectedUserId() );
		
		// TODO factoriser
		List<Budget> budgets = getBudgetService().findByUser( getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
	}
	
	// FULL REST
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody BudgetDTO createBudget( @RequestBody BudgetDTO dto ) throws ConstraintViolationFunctionalException {
		Budget budget = getBudgetService().create( dto, getConnectedUserId() );
		return new BudgetDTO( budget );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	public @ResponseBody BudgetDTO updateBudget( @RequestBody BudgetDTO budget ) throws ConstraintViolationFunctionalException {
		getBudgetService().update( budget, getConnectedUserId() );
		return budget;
	}

}
