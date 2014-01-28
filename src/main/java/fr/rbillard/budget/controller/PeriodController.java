package fr.rbillard.budget.controller;

import java.text.ParseException;
import java.util.List;

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
import fr.rbillard.budget.entity.Period;

@Controller
@RequestMapping( value = "/period" )
public class PeriodController extends AbstractController {
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody List<PeriodDTO> getPeriods() {
		List<Period> periods = getPeriodService().findByUser( getConnectedUserId() );
		return PeriodDTO.listPeriods2ListPeriodsDTO( periods );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody PeriodDTO getPeriod( @PathVariable( value = "id" ) Long id ) {
		return getPeriodDTO( id );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}/light", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody PeriodDTO getPeriodLight( @PathVariable( value = "id" ) Long id ) {
		return new PeriodDTO( getPeriodService().getEntity( id ) );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	@Transactional
	public @ResponseBody List<PeriodDTO> deletePeriod( @PathVariable( value = "id" ) Long id ) {
		
		getPeriodService().delete( id, getConnectedUserId() );
		
		// TODO factoriser
		List<Period> periods = getPeriodService().findByUser( getConnectedUserId() );
		return PeriodDTO.listPeriods2ListPeriodsDTO( periods );
	}
	
	// FULL REST
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody PeriodDTO createPeriod( @RequestBody PeriodDTO dto ) throws ParseException {
		Period period = getPeriodService().create( dto, getConnectedUserId() );
		return new PeriodDTO( period );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	public @ResponseBody PeriodDTO updatePeriod( @RequestBody PeriodDTO dto ) throws ParseException {
		getPeriodService().update( dto, getConnectedUserId() );
		return dto;
	}
	
}
