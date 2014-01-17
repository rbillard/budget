package fr.rbillard.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import fr.rbillard.budget.dto.Period;
import fr.rbillard.budget.dto.PeriodDTO;
import fr.rbillard.budget.dto.PeriodFullDTO;

@Controller
@PropertySource({ "classpath:/application.properties" })
public class HomeController {
	
	private static Period[] periods;
	
	static {
		periods = new Period[] {
			new Period( 0L, "Période 0", "17/01/2014", "24/01/2014", new String[] { "/budget/resources/img/1.png", "/budget/resources/img/4.png" }, "Non" ),
			new Period( 1L, "Période 1", "16/01/2014", "18/01/2014", new String[] { "/budget/resources/img/2.png", "/budget/resources/img/5.png" }, "Non" ),
			new Period( 2L, "Période 2", "01/01/2015", "31/01/2015", new String[] { "/budget/resources/img/3.png", "/budget/resources/img/6.png" }, "Oui" )	
		};
	}
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "angular";
	}
	
	@RequestMapping( value = "/period/periods", method = RequestMethod.GET )
	public @ResponseBody List<PeriodDTO> getPeriods() {
		
		return Lists.newArrayList(
			new PeriodDTO( periods[ 0 ] ),
			new PeriodDTO( periods[ 1 ] ),
			new PeriodDTO( periods[ 2 ] )
		);
		
	}
	
	@RequestMapping( value = "/period/{id}", method = RequestMethod.GET )
	public @ResponseBody PeriodFullDTO getPeriod( @PathVariable( value = "id" ) Long id ) {
		return new PeriodFullDTO( periods[ id.intValue() ] );
	}
	
	@ModelAttribute( "context" )
	public String context() {
		return environment.getProperty( "context" );
	}

}
