package fr.rbillard.budget.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan( basePackages = { "fr.rbillard.budget" } )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
    	
    	http
    		.authorizeRequests()
    			.antMatchers("/resources/**").permitAll()
    			.anyRequest().permitAll();
    	
    }
    
}