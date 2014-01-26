package fr.rbillard.budget.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.rbillard.budget.exception.ConstraintViolationFunctionalException;

// TODO remonter
@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler( ConstraintViolationException.class )
	public @ResponseBody Map<String, String> constraintViolation( HttpServletResponse response, ConstraintViolationException e ) {
		return getViolations( response, e.getConstraintViolations() );
	}
	
	@ExceptionHandler( ConstraintViolationFunctionalException.class )
	public @ResponseBody Map<String, String> constraintViolationBudget( HttpServletResponse response, ConstraintViolationFunctionalException e ) {
		return getViolations( response, e.getConstraintViolations() );
	}
	
	private Map<String, String> getViolations( HttpServletResponse response, Set<? extends ConstraintViolation<?>> constraintsViolations ) {
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		
		Map<String, String> violations = new HashMap<String, String>( constraintsViolations.size() );
		
		for ( ConstraintViolation cv : constraintsViolations ) {
			violations.put( cv.getPropertyPath().toString(), cv.getMessage() );
		}
		
		return violations;
		
	}

}
