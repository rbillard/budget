package fr.rbillard.budget.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import fr.rbillard.springhibernate.domain.conf.DataSourceConfigProvider;
import fr.rbillard.springhibernate.domain.conf.DefaultDataSourceConfiguration;

@Configuration
@ComponentScan(basePackages = {
	"fr.rbillard.budget.controller",        
	"fr.rbillard.budget.service",
	"fr.rbillard.budget.dao"
})
public class BudgetTestConfiguration extends DefaultDataSourceConfiguration {

	@Override
	protected String[] getSessionFactoryPackagesToScan() {
		return new String[] { "fr.rbillard.budget.entity" };
	}

	@Override
	protected DataSourceConfigProvider getDataSourceConfigProvider() {

		return new DataSourceConfigProvider() {

			public DataSource dataSource() {
				
				return new EmbeddedDatabaseBuilder()
		            .setType( EmbeddedDatabaseType.HSQL )
//		            .addScript( "classpath:/import.sql" )
		            .build();
				
			}

			public Properties buildHibernateProperties() {
				
				Properties pp = new Properties();
				pp.setProperty( "hibernate.dialect", "org.hibernate.dialect.HSQLDialect" );
				pp.setProperty( "hibernate.show_sql", "true" );
				pp.setProperty( "hibernate.hbm2ddl.auto", "create" );
				return pp;
				
			}
			
		};
	}

}
