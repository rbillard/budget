package fr.rbillard.budget.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IPeriodDAO;
import fr.rbillard.budget.dto.PeriodLightDTO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.budget.service.IUserService;
import fr.rbillard.springhibernate.domain.exception.ConstraintViolationFunctionalException;
import fr.rbillard.springhibernate.domain.service.impl.GenericService;

@Service
public class PeriodService extends GenericService<Period, Long, IPeriodDAO> implements IPeriodService {

	@Autowired
	private IPeriodDAO dao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired IBudgetService budgetService;
	
	@Override
	public IPeriodDAO getDao() {
		return dao;
	}

	@Override
	public void setDao( IPeriodDAO dao ) {
		this.dao = dao;
	}

	@Override
	@Transactional( readOnly = true )
	public List<Period> findByUser( Long userId ) {
		return dao.findByUser( userId );
	}

	@Override
	@Transactional
	public void delete( Long id, Long ownerId ) {
		
		// TODO dao.getPeriod( id, userId ); instead of :
		User user = userService.getEntity( ownerId );
		Period period = getEntity( id );
		user.removePeriod( period );
		
		dao.delete( period );
		
	}

	@Override
	@Transactional
	public Period update( PeriodLightDTO dto, Long ownerId ) throws ParseException, ConstraintViolationFunctionalException {
		
		assertValid( dto );
		
		// TODO get by id and user id
		Period period = getEntity( dto.getId() );
		period.setLabel( dto.getLabel() );
		period.setStartDate( dto.getStartDate() );
		period.setEndDate( dto.getEndDate() );
		
		update( period );

		return period;
		
	}
	
	@Override
	@Transactional
	public Period create( PeriodLightDTO dto, Long ownerId ) throws ParseException, ConstraintViolationFunctionalException {
		
		assertValid( dto );
		
		User user = userService.getEntity( ownerId );
		Period period = new Period();
		period.setLabel( dto.getLabel() );
		period.setStartDate( dto.getStartDate() );
		period.setEndDate( dto.getEndDate() );
		period.setUser( user );
		user.addPeriod( period );
		
		create( period );
		
		return period; 

	}

}
