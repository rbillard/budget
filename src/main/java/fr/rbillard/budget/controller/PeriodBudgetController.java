package fr.rbillard.budget.controller;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.dto.PeriodDTO;
import fr.rbillard.budget.dto.TypeBudgets;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.budget.service.IPeriodBudgetService;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.budget.utils.ValidatorUtils;

@Controller
@RequestMapping( value = "period-budget" )
public class PeriodBudgetController extends AbstractController {
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;
	
	@Autowired
	private IPeriodService periodService;
	
	@Autowired
	private Validator validator;

	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody TypeBudgets associatePeriodBudget( @RequestBody MessageAssociatePeriodBudget message ) throws fr.rbillard.budget.exception.ConstraintViolationFunctionalException {
		
		message.setUserId( getConnectedUserId() );
		
		ValidatorUtils.assertValid( validator, message );
		
		periodBudgetService.associatePeriodBudget( message );
	
		return getTypeBudgets( message.getPeriodId() );
		
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{periodId}/{budgetId}", method = RequestMethod.DELETE )
	@Transactional
	public @ResponseBody PeriodDTO deletePeriodBudget( @PathVariable( value = "periodId" ) Long periodId, @PathVariable( value = "budgetId" ) Long budgetId ) {
		
		periodBudgetService.dissociate( periodId, budgetId, getConnectedUserId() );
		
		// TODO factoriser avec PeriodController GET
		Period period = periodService.getEntity( periodId );
		return new PeriodDTO( period, getTypeBudgets( periodId ) );
		
	}

}
