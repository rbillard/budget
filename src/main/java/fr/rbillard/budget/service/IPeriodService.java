package fr.rbillard.budget.service;

import java.text.ParseException;
import java.util.List;

import fr.rbillard.budget.dto.PeriodLightDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IPeriodService extends IGenericService<Period, Long> {

	List<Period> findByUser( Long userId );

	void delete( Long id, Long ownerId );

	Period update( PeriodLightDTO dto, Long ownerId ) throws ParseException, ConstraintViolationFunctionalException;
	
	Period create( PeriodLightDTO dto, Long ownerId ) throws ParseException, ConstraintViolationFunctionalException;

}
