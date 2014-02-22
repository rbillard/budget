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

import fr.rbillard.budget.dto.PeriodFullDTO;
import fr.rbillard.budget.dto.PeriodLightDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

@Controller
@RequestMapping( value = "/period" )
public class PeriodController extends AbstractController {
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody List<PeriodLightDTO> getPeriods() {
		return getPeriodsFromConnectedUser();
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody PeriodFullDTO getPeriod( @PathVariable( value = "id" ) Long id ) {
		return getPeriodDTO( id );
	}
	
	@Produces( APPLICATION_JSON ) 
	@RequestMapping( value = "/{id}/light", method = RequestMethod.GET )
	@Transactional( readOnly = true )
	public @ResponseBody PeriodLightDTO getPeriodLight( @PathVariable( value = "id" ) Long id ) {
		return new PeriodLightDTO( getPeriodService().getEntity( id ) );
	}
	
	@Produces( APPLICATION_JSON )
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	@Transactional
	public @ResponseBody List<PeriodLightDTO> deletePeriod( @PathVariable( value = "id" ) Long id ) {
		getPeriodService().delete( id, getConnectedUserId() );
		return getPeriodsFromConnectedUser();
	}
	
	// FULL REST
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.POST )
	public @ResponseBody PeriodFullDTO createPeriod( @RequestBody PeriodLightDTO dto ) throws ParseException, ConstraintViolationFunctionalException {
		Period period = getPeriodService().create( dto, getConnectedUserId() );
		return new PeriodFullDTO( period, getTypeBudgets( period.getId() ) );
	}
	
	@Consumes( APPLICATION_JSON )
	@Produces( APPLICATION_JSON )
	@RequestMapping( method = RequestMethod.PUT )
	@Transactional
	public @ResponseBody PeriodFullDTO updatePeriod( @RequestBody PeriodLightDTO dto ) throws ParseException, ConstraintViolationFunctionalException {
		getPeriodService().update( dto, getConnectedUserId() );
		return getPeriodDTO( dto.getId() );
	}
	
	private List<PeriodLightDTO> getPeriodsFromConnectedUser() {
		List<Period> periods = getPeriodService().findByUser( getConnectedUserId() );
		return PeriodLightDTO.listPeriods2ListPeriodsLightDTO( periods );
	}
	
}
