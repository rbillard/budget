package fr.rbillard.budget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.dao.IPeriodDAO;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.budget.entity.User;
import fr.rbillard.budget.service.IBudgetService;
import fr.rbillard.budget.service.IPeriodService;
import fr.rbillard.budget.service.IUserService;
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
	public void delete( Long id, Long userId ) {
		
		// TODO dao.getPeriod( id, userId ); instead of :
		User user = userService.getEntity( userId );
		Period period = getEntity( id );
		user.removePeriod( period );
		
		dao.delete( period );
		
	}

}
