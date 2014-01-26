package fr.rbillard.budget.service;

import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.message.MessageCreateOperation;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IOperationService extends IGenericService<Operation, Long> {

	Operation create( MessageCreateOperation message );

	void delete( Long id, Long userId );

}
