package fr.rbillard.budget.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.rbillard.budget.dao.IPeriodDAO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class PeriodDAO extends GenericHibernateDAO<Period, Long> implements IPeriodDAO {

	@Override
	protected Class<Period> getPersistentClass() {
		return Period.class;
	}

	@Override
	public List<Period> findByUser( Long userId ) {
		
		return findByCriteria( 
			newDetachedCriteria()
				.createCriteria( Period.PROP_USER )
					.add( Restrictions.eq( User.PROP_ID, userId ) )
		);
		
	}

}
