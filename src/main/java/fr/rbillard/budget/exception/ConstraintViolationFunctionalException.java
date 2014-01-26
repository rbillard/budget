package fr.rbillard.budget.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

// TODO remonter
public class ConstraintViolationFunctionalException extends Exception {

private static final long serialVersionUID = 1L;
	
	private final Set<? extends ConstraintViolation<?>> constraintViolations;

	public ConstraintViolationFunctionalException( Set<? extends ConstraintViolation<?>> constraintViolations ) {
		this.constraintViolations = constraintViolations;
	} 

	public Set<? extends ConstraintViolation<?>> getConstraintViolations() {
		return constraintViolations;
	}
	
}
