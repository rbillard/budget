package fr.rbillard.budget.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer /*DefaultAppInitializer*/ {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ SecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{ ApplicationConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[]{ "/" };
	}
	
}