package fr.rbillard.budget.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IBudgetDAO;
import fr.rbillard.budget.entity.Budget;
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

}
