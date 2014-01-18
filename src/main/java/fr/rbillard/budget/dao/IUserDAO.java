package fr.rbillard.budget.dao;

import org.springframework.security.core.userdetails.UserDetails;

import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.dao.IGenericDAO;

public interface IUserDAO extends IGenericDAO<User, Long> {

	UserDetails loadUserByUsername( String username );

}
