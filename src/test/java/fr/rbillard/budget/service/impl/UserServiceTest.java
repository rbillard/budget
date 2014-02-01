package fr.rbillard.budget.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IUserService;

public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testLoadUserByUsername() throws Exception {

		// given
		String login = "login";
		String password = "password";
		
		User user = new User();
		user.setLogin( login );
		user.setPassword( password );
		userService.create( user );
		
		// when
		UserDetails userDetails = userService.loadUserByUsername( login );
		
		// then
		assertNotNull( userDetails );
		assertEquals( login, userDetails.getUsername() );
		assertEquals( password, userDetails.getPassword() );
		
	}
	
	@Test( expected = UsernameNotFoundException.class )
	public void testLoadUserByUsername_UsernameNotFoundException() throws Exception {
		
		// when
		userService.loadUserByUsername( "fakeLogin" );
		
		// then exception
		
	}

}
