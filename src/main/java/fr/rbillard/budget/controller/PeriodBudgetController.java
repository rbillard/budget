package fr.rbillard.budget.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.dto.PeriodDTO;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;

@Controller
@RequestMapping( value = "period-budget" )
public class PeriodBudgetController extends AbstractController {
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	@Transactional
	public @ResponseBody PeriodDTO associatePeriodBudget( @RequestBody MessageAssociatePeriodBudget message ) throws fr.rbillard.budget.exception.ConstraintViolationFunctionalException {
		
		message.setUserId( getConnectedUserId() );
		
		// TODO dans services
//		ValidatorUtils.assertValid( validator, message );
		
		getPeriodBudgetService().associatePeriodBudget( message );
		return getPeriodDTO( message.getPeriodId() );
		
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{periodId}/{budgetId}", method = RequestMethod.DELETE )
	@Transactional
	public @ResponseBody PeriodDTO deletePeriodBudget( @PathVariable( value = "periodId" ) Long periodId, @PathVariable( value = "budgetId" ) Long budgetId ) {
		getPeriodBudgetService().dissociate( periodId, budgetId, getConnectedUserId() );
		return getPeriodDTO( periodId );
	}

}
