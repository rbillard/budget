package fr.rbillard.budget.dao.impl;

import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IOperationDAO;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class OperationDAO extends GenericHibernateDAO<Operation, Long> implements IOperationDAO {

	@Override
	protected Class<Operation> getPersistentClass() {
		return Operation.class;
	}

	@Override
	public Operation getOperation( Long id, Long userId ) {
		
		String queryString = new StringBuilder()
			.append( " select operation ")
			.append( " from ").append( Operation.class.getName() ).append( " operation " )
			.append( " inner join operation." ).append( Operation.PROP_PERIOD_BUDGET ).append( "." ).append( PeriodBudget.PROP_ID ).append( "." ).append( PeriodBudgetId.PROP_PERIOD ).append( " period " )
			.append( " inner join period." ).append( Period.PROP_USER ).append( " user ")
			.append( " with user." ).append( User.PROP_ID ).append( " = :userId ")
			.append( " where operation." ).append( Operation.PROP_ID ).append( " = :operationId " )
			.toString();
		
		return ( Operation ) getCurrentSession().createQuery( queryString )
			.setLong( "operationId", id )
			.setLong( "userId", userId )
			.uniqueResult();
		
	}

}
