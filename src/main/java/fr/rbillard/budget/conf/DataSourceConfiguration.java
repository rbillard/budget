package fr.rbillard.budget.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import fr.rbillard.springhibernate.domain.conf.DataSourceConfigProvider;
import fr.rbillard.springhibernate.domain.conf.DefaultDataSourceConfiguration;

@Configuration
@ComponentScan(basePackages = {
        "fr.rbillard.budget.controller",        
        "fr.rbillard.budget.service",
        "fr.rbillard.budget.dao"
})
@PropertySource({ "classpath:/database.properties" })
public class DataSourceConfiguration extends DefaultDataSourceConfiguration {

	@Autowired
	private Environment environment;
	
	@Override
	protected String[] getSessionFactoryPackagesToScan() {
		return new String[] { "fr.rbillard.budget.entity" };
	}

	@Override
	protected DataSourceConfigProvider getDataSourceConfigProvider() {
		
		return new DataSourceConfigProvider() {
			
			@Override
			public DataSource dataSource() {
				
				BasicDataSource dataSource = new BasicDataSource();
		        dataSource.setDriverClassName( environment.getProperty( "driverClassName" ) );
		        dataSource.setUrl( environment.getProperty( "url" ) );
		        dataSource.setUsername( environment.getProperty( "dbUsername" ) );
		        dataSource.setPassword( environment.getProperty( "password" ) );
		        return dataSource;
		        
			}
			
			@Override
			public Properties buildHibernateProperties() {
				
				Properties properties = new Properties();
				properties.setProperty( "hibernate.dialect", environment.getProperty( "hibernate.dialect" ) );
				properties.setProperty( "hibernate.show_sql", environment.getProperty( "hibernate.show_sql" ) );
				return properties;
				
			}
			
		};
	}

}
