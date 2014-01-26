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

import fr.rbillard.budget.dto.OperationDTO;
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
	public @ResponseBody List<OperationDTO> createOperation( @RequestBody MessageCreateOperation message ) {
		
		message.setUserId( getConnectedUserId() );
		
		operationService.create( message );
		
		return null;
		
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public @ResponseBody void deleteOperation( @PathVariable( value = "id" ) Long id ) {
		
		operationService.delete( id, getConnectedUserId() );
		
	}
	

}
