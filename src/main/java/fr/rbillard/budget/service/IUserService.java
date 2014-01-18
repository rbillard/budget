package fr.rbillard.budget.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.rbillard.budget.entity.User;
import fr.rbillard.springhibernate.domain.service.IGenericService;

public interface IUserService extends IGenericService<User, Long>, UserDetailsService {

}
