package fr.rbillard.budget.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.dto.TypeBudgets;
import fr.rbillard.budget.message.MessageAssociatePeriodBudget;
import fr.rbillard.budget.service.IPeriodBudgetService;

@Controller
@RequestMapping( value = "period-budget" )
public class PeriodBudgetController extends AbstractController {
	
	@Autowired
	private IPeriodBudgetService periodBudgetService;

	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody TypeBudgets associatePeriodBudget( @RequestBody MessageAssociatePeriodBudget message ) {
		
		message.setUserId( getConnectedUserId() );
		
		periodBudgetService.associatePeriodBudget( message );
	
		return getTypeBudgets( message.getPeriodId() );
		
	}

}
