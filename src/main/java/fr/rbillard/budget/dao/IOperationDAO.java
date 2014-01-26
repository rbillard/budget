package fr.rbillard.budget.dao;

import fr.rbillard.budget.entity.Operation;
import fr.rbillard.springhibernate.domain.dao.IGenericDAO;

public interface IOperationDAO extends IGenericDAO<Operation, Long> {

	Operation getOperation( Long id, Long userId );

}
