package fr.rbillard.budget.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import fr.rbillard.springhibernate.controller.config.DefaultAppConfiguration;

@Configuration
@ComponentScan( basePackages = {
    "fr.rbillard.budget.controller",        
    "fr.rbillard.budget.service",
    "fr.rbillard.budget.dao"
})
@EnableTransactionManagement
public class ApplicationConfiguration extends DefaultAppConfiguration {
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
	
}