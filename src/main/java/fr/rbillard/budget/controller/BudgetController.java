package fr.rbillard.budget.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.rbillard.budget.dto.BudgetDTO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.exception.NoSuchEntityException;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

@RestController
@RequestMapping( value = "/budget" )
public class BudgetController extends AbstractController {
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	public List<BudgetDTO> getBudgets() {
		return getBudgetsFromConnectedUser();
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public BudgetDTO getBudget( @PathVariable( value = "id" ) Long id ) throws NoSuchEntityException {
		Budget budget = getBudgetService().getBudget( id, getConnectedUserId() );
		return new BudgetDTO( budget );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public List<BudgetDTO> deleteBudget( @PathVariable( value = "id" ) Long id ) throws NoSuchEntityException {
		getBudgetService().delete( id, getConnectedUserId() );
		return getBudgetsFromConnectedUser();
	}

	private List<BudgetDTO> getBudgetsFromConnectedUser() {
		List<Budget> budgets = getBudgetService().findByUser( getConnectedUserId() );
		return BudgetDTO.listBudgets2ListBudgetsDTO( budgets );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public BudgetDTO createBudget( @RequestBody BudgetDTO dto ) throws ConstraintViolationFunctionalException {
		Budget budget = getBudgetService().create( dto, getConnectedUserId() );
		return new BudgetDTO( budget );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	public BudgetDTO updateBudget( @RequestBody BudgetDTO budget ) throws ConstraintViolationFunctionalException, NoSuchEntityException {
		getBudgetService().update( budget, getConnectedUserId() );
		return budget;
	}

}
