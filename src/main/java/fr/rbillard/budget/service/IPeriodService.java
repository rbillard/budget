package fr.rbillard.budget.service;

import java.text.ParseException;
import java.util.List;

import fr.rbillard.budget.dto.PeriodDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IPeriodService extends IGenericService<Period, Long> {

	List<Period> findByUser( Long userId );

	void delete( Long id, Long userId );

	Period update( PeriodDTO dto, Long connectedUserId ) throws ParseException;
	
	Period create( PeriodDTO dto, Long userId ) throws ParseException;

}
