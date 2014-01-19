package fr.rbillard.budget.service;

import java.util.List;

import fr.rbillard.budget.entity.Period;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IPeriodService extends IGenericService<Period, Long> {

	List<Period> findByUser( Long userId );

	void delete( Long id, Long userId );

}
