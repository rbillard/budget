package fr.rbillard.budget.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.rbillard.budget.dto.PeriodFullDTO;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.exception.FunctionalException;

@RestController
@RequestMapping( value = "period-budget" )
public class PeriodBudgetController extends AbstractController {
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	@Transactional
	public PeriodFullDTO associatePeriodBudget( @RequestBody MessageAssociatePeriodBudget message ) throws ConstraintViolationFunctionalException, FunctionalException {
		
		message.setUserId( getConnectedUserId() );
		getPeriodBudgetService().associatePeriodBudget( message );
		return getPeriodDTO( message.getPeriodId() );
		
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{periodId}/{budgetId}", method = RequestMethod.DELETE )
	@Transactional
	public PeriodFullDTO deletePeriodBudget( @PathVariable( value = "periodId" ) Long periodId, @PathVariable( value = "budgetId" ) Long budgetId ) {
		getPeriodBudgetService().dissociate( periodId, budgetId, getConnectedUserId() );
		return getPeriodDTO( periodId );
	}

}
