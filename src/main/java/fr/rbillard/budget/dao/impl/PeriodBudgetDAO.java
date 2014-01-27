package fr.rbillard.budget.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IPeriodBudgetDAO;
import fr.rbillard.budget.entity.Budget;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.PeriodBudget;
import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class PeriodBudgetDAO extends GenericHibernateDAO<PeriodBudget, PeriodBudgetId> implements IPeriodBudgetDAO {

	@Override
	protected Class<PeriodBudget> getPersistentClass() {
		return PeriodBudget.class;
	}

	@Override
	public PeriodBudget getEntity( Long userId, Long periodId, Long budgetId ) {
		
		String queryString = getQueryStringPeriodBudgetFromPeriodAndUser()
			.append( " inner join periodBudget." ).append( PeriodBudget.PROP_ID ).append( "." ).append( PeriodBudgetId.PROP_BUDGET ).append( " budget " )
			.append( " with budget." ).append( Budget.PROP_ID ).append( " = :budgetId " )
			.append( " inner join budget." ).append( Budget.PROP_USER ).append( " user2 " )
			.append( " with user2." ).append( User.PROP_ID ).append( " = :userId " )
			.toString();
		
		return ( PeriodBudget ) getCurrentSession().createQuery( queryString )
			.setLong( "periodId", periodId )
			.setLong( "budgetId", budgetId )
			.setLong( "userId", userId )
			.uniqueResult();
		
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List<PeriodBudget> findAssociatedToPeriod( Long periodId, Long userId ) {
		
		String queryString = getQueryStringPeriodBudgetFromPeriodAndUser().toString();
		
		return getCurrentSession().createQuery( queryString )
			.setLong( "periodId", periodId )
			.setLong( "userId", userId )
			.list();
		
	}
	
	private StringBuilder getQueryStringPeriodBudgetFromPeriodAndUser() {
		
		return new StringBuilder()
			.append( " select periodBudget " )
			.append( " from " ).append( PeriodBudget.class.getName() ).append( " periodBudget" )
			.append( " inner join periodBudget." ).append( PeriodBudget.PROP_ID ).append( "." ).append( PeriodBudgetId.PROP_PERIOD ).append( " period " )
			.append( " with period." ).append( Period.PROP_ID ).append( " = :periodId " )
			.append( " inner join period." ).append( Period.PROP_USER ).append( " user1 " )
			.append( " with user1." ).append( User.PROP_ID ).append( " = :userId " );
		
	}

}
