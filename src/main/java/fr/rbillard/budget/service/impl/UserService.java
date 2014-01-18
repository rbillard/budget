package fr.rbillard.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IUserDAO;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.service.impl.GenericService;

@Service
public class UserService extends GenericService<User, Long, IUserDAO> implements IUserService {

	@Autowired
	private IUserDAO dao;
	
	@Override
	public IUserDAO getDao() {
		return dao;
	}

	@Override
	public void setDao( IUserDAO dao ) {
		this.dao = dao;
	}

	@Override
	@Transactional( readOnly = true )
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		return dao.loadUserByUsername( username );
	}

}
