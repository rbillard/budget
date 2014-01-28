package fr.rbillard.budget.controller;

import java.text.ParseException;

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
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.message.MessageCreateOperation;
import fr.rbillard.budget.service.IOperationService;

@Controller
@RequestMapping( value = "/operation" )
public class OperationController extends AbstractController {
	
	@Autowired
	private IOperationService operationService;
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	@Transactional
	public @ResponseBody PeriodDTO createOperation( @RequestBody MessageCreateOperation message ) throws ParseException {
		message.setUserId( getConnectedUserId() );
		operationService.create( message );
		return getPeriodDTO( message.getPeriodId() );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	@Transactional
	public @ResponseBody PeriodDTO deleteOperation( @PathVariable( value = "id" ) Long id ) {
		
		Operation operation = operationService.getEntity( id );
		Period period = operation.getPeriodBudget().getId().getPeriod();
		
		operationService.delete( id, getConnectedUserId() );
		
		return new PeriodDTO( period, getTypeBudgets( period.getId() ) );
		
	}
	

}
