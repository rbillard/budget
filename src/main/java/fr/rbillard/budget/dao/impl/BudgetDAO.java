package fr.rbillard.budget.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IBudgetDAO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class BudgetDAO extends GenericHibernateDAO<Budget, Long> implements IBudgetDAO {

	@Override
	protected Class<Budget> getPersistentClass() {
		return Budget.class;
	}

	@Override
	public List<Budget> findByUser( Long userId ) {

		return findByCriteria( 
			newDetachedCriteria()
				.createCriteria( Budget.PROP_USER )
					.add( Restrictions.eq( User.PROP_ID, userId ) )
		);
		
	}

	@Override
	public List<Budget> findNotAssociatedToPeriod( Long periodId, Long userId ) {
		
		StringBuilder queryString = getBeginningQueryStringFindBudgetFromUser()
			.append( " where budget." ).append( Budget.PROP_ID ).append( " not in ( ")
				.append( " select l." ).append( PeriodBudget.PROP_ID ).append( "." ).append( PeriodBudgetId.PROP_BUDGET ).append( "." ).append( Budget.PROP_ID )
				.append( " from " ).append( PeriodBudget.class.getName() ).append( " l " )
				.append( " inner join l." ).append( PeriodBudget.PROP_ID ).append( "." ).append( PeriodBudgetId.PROP_PERIOD ).append( " period ")
				.append( " with period." ).append( Period.PROP_ID ).append( " = :periodId ")
			.append( " )");
			
		return findBudgets( periodId, userId, queryString );
		
	}

	@SuppressWarnings( "unchecked" )
	private List<Budget> findBudgets( Long periodId, Long userId, StringBuilder queryString ) {
		
		return getCurrentSession()
			.createQuery( queryString.toString() )
			.setLong( "periodId", periodId )
			.setLong( "userId", userId )
			.list();
		
	}

	private StringBuilder getBeginningQueryStringFindBudgetFromUser() {
		
		return new StringBuilder()
			.append( " select budget " )
			.append( " from " ).append( Budget.class.getName() ).append( " budget " )
			.append( " inner join budget." ).append( Budget.PROP_USER ).append( " user " )
			.append( " with user." ).append( User.PROP_ID ).append( " = :userId " );
		
	}

}
