package fr.rbillard.budget.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import fr.rbillard.budget.auth.SimpleUserDetails;
import fr.rbillard.budget.dao.IUserDAO;
import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.impl.GenericHibernateDAO;

@Repository
public class UserDAO extends GenericHibernateDAO<User, Long> implements IUserDAO {

	@Override
	protected Class<User> getPersistentClass() {
		return User.class;
	}

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		
		User user = ( User ) uniqueResultByCriteria(
			newDetachedCriteria()
				.add( Restrictions.eq( User.PROP_LOGIN, username ) )
		);
		
		if ( user == null ) {
			throw new UsernameNotFoundException( username );
		}
		
		return new SimpleUserDetails( user );
		
	}

}
