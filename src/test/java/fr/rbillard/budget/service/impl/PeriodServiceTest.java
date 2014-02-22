package fr.rbillard.budget.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.rbillard.budget.AbstractTest;
import fr.rbillard.budget.dto.PeriodLightDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;

public class PeriodServiceTest extends AbstractTest {

	@Autowired
	private IPeriodService periodService;
	
	@Test
	public void testFindByUser() {
		
		// given
		User user1 = newUserWithPeriod( "user1" );
		Period period1 = user1.getPeriods().get( 0 );
		User user2 = newUserWithPeriod( "user2" );
		Period period2 = user2.getPeriods().get( 0 );
		
		// when
		List<Period> periods = periodService.findByUser( user1.getId() );
		
		// then
		assertEquals( 1, periods.size() );
		assertTrue( periods.contains( period1 ) );
		assertFalse( periods.contains( period2 ) );
		
	}

	@Test
	public void testDelete() throws Exception {
		
		// given
		User user = newUserWithPeriod();
		Period period = user.getPeriods().get( 0 );
		
		// when
		periodService.delete( period.getId(), user.getId() );
		Period loadedPeriod = periodService.getEntity( period.getId() );
		
		// then
		assertNull( loadedPeriod );
		
	}

	@Test
	public void testUpdate() throws Exception {
		
		// given
		User user = newUserWithPeriod();
		Period period = user.getPeriods().get( 0 );
		PeriodLightDTO dto = getDefaultPeriodLightDTO()
			.setId( period.getId() );
		
		// when
		Period modifiedPeriod = periodService.update( dto, user.getId() );
		
		// then
		assertEquals( dto.getId(), modifiedPeriod.getId() );
		assertPeriod( user, dto, modifiedPeriod );
		
	}

	private void assertPeriod( User user, PeriodLightDTO dto, Period period ) {
		
		assertEquals( dto.getLabel(), period.getLabel() );
		assertEquals( dto.getStartDate(), period.getStartDate() );
		assertEquals( dto.getEndDate(), period.getEndDate() );
		assertEquals( user, period.getUser() );
		
	}

	@Test
	public void testCreate() throws Exception {
		
		// given
		User user = newUser();
		PeriodLightDTO dto = getDefaultPeriodLightDTO();
		
		// when
		Period createdPeriod = periodService.create( dto, user.getId() );
		List<Period> periods = periodService.findByUser( user.getId() );
		
		// then
		assertNotNull( createdPeriod.getId() );
		assertPeriod( user, dto, createdPeriod );
		assertEquals( 1, periods.size() );
		assertEquals( periods.get( 0 ), createdPeriod );
		
	}
	
	@Test( expected = ConstraintViolationFunctionalException.class )
	public void testCreate_ConstraintViolation() throws Exception {
		
		// when
		periodService.create( new PeriodLightDTO(), null );
		
		// then exception
		
	}
	
	private PeriodLightDTO getDefaultPeriodLightDTO() throws ParseException {
		
		DateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		
		return new PeriodLightDTO()
			.setLabel( "Period DTO" )
			.setStartDate( df.parse( "01/01/2014" ) )
			.setEndDate( df.parse( "23/03/2014" ) );
	}
	
}
