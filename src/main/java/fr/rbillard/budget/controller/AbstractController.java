package fr.rbillard.budget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import fr.rbillard.budget.auth.SimpleUserDetails;
import fr.rbillard.budget.entity.User;

@PropertySource({ "classpath:/application.properties" })
public abstract class AbstractController {
	
	// FIXME trouver solution pour mettre en conf
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String APPLICATION_JSON = "application/json";
	
	@Autowired
	private Environment environment;
	
	protected Environment getEnvironment() {
		return environment;
	}
	
	@ModelAttribute( "context" )
	public String context() {
		return environment.getProperty( "context" );
	}
	
	protected User getConnectedUser() {
		return ( (SimpleUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getUser();
	}
	protected Long getConnectedUserId() {
		return getConnectedUser().getId();
	}

}
