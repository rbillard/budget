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

import fr.rbillard.budget.dto.PeriodDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.service.IPeriodService;

@Controller
@RequestMapping( value = "/period" )
public class PeriodController extends AbstractController {
	
	@Autowired
	private IPeriodService periodService;
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	public @ResponseBody List<PeriodDTO> getPeriods() {
		List<Period> periods = periodService.findByUser( getConnectedUserId() );
		return PeriodDTO.listPeriods2ListPeriodsDTO( periods );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/create", method = RequestMethod.GET )
	public @ResponseBody Period newPeriod() {
		return new Period();
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public @ResponseBody PeriodDTO getPeriod( @PathVariable( value = "id" ) Long id ) {
		Period period = periodService.getEntity( id );
		return new PeriodDTO( period );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public @ResponseBody List<PeriodDTO> deletePeriod( @PathVariable( value = "id" ) Long id ) {
		
		periodService.delete( id, getConnectedUserId() );
		
		// TODO factoriser
		List<Period> periods = periodService.findByUser( getConnectedUserId() );
		return PeriodDTO.listPeriods2ListPeriodsDTO( periods );
	}
	
	// FULL REST
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody PeriodDTO createPeriod( @RequestBody Period period ) {
		period.setUser( getConnectedUser() );
		periodService.create( period );
		return new PeriodDTO( period );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	public @ResponseBody PeriodDTO updatePeriod( @RequestBody Period period ) {
		period.setUser( getConnectedUser() );
		periodService.update( period );
		return new PeriodDTO( period );
	}
	
}
