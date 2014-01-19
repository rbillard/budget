package fr.rbillard.budget.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler( ConstraintViolationException.class )
	public @ResponseBody Map<String, String> constraintViolation( HttpServletResponse response, ConstraintViolationException e ) {
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		
		Map<String, String> violations = new HashMap<String, String>( e.getConstraintViolations().size() );
		
		for ( ConstraintViolation cv : e.getConstraintViolations() ) {
			violations.put( cv.getPropertyPath().toString(), cv.getMessage() );
		}
		
		return violations;
		
	}

}
