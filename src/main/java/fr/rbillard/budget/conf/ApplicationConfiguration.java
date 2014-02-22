package fr.rbillard.budget.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.rbillard.springhibernate.controller.config.DefaultAppConfiguration;

@Configuration
@ComponentScan( basePackages = {
    "fr.rbillard.budget.controller",        
    "fr.rbillard.budget.service",
    "fr.rbillard.budget.dao"
})
@PropertySource({ "classpath:/string.properties" })
@EnableTransactionManagement
public class ApplicationConfiguration extends DefaultAppConfiguration {
	
}