package fr.rbillard.budget.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import fr.rbillard.budget.exception.ConstraintViolationFunctionalException;

// TODO remonter
public final class ValidatorUtils {
	
	public static <T> void assertValid( Validator validator, T bean) throws ConstraintViolationFunctionalException {
		
		Set<ConstraintViolation<T>> violations = validator.validate( bean );
		if (violations.size() > 0) {
			throw new ConstraintViolationFunctionalException( violations );
		}
		
	}

}
