package fr.rbillard.budget.dao;

import java.util.List;

import fr.rbillard.budget.entity.Period;
import fr.rbillard.springhibernate.domain.dao.IGenericDAO;

public interface IPeriodDAO extends IGenericDAO<Period, Long> {

	List<Period> findByUser( Long userId );

}
