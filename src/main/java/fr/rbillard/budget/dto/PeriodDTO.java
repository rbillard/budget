package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.controller.AbstractController;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.utils.DateUtils;

public class PeriodDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private final Long id;
	private final String label;
	private final String startDate;
	private final String endDate;
	private final TypeBudgets typeBudgets;
	
	
	public PeriodDTO( Period period, TypeBudgets typeBudgets ) {
		this.id = period.getId();
		this.label = period.getLabel();
		this.startDate = DateUtils.dateToString( period.getStartDate(), AbstractController.FORMAT_DATE );
		this.endDate = DateUtils.dateToString( period.getEndDate(), AbstractController.FORMAT_DATE );
		this.typeBudgets = typeBudgets;
	}
	public PeriodDTO( Period period ) {
		this( period, null );
	}


	public Long getId() {
		return id;
	}

	
	public String getLabel() {
		return label;
	}


	public String getStartDate() {
		return startDate;
	}
	

	public String getEndDate() {
		return endDate;
	}
	

	public TypeBudgets getTypeBudgets() {
		return typeBudgets;
	}


	public static List<PeriodDTO> listPeriods2ListPeriodsDTO( List<Period> periods ) {
		
		List<PeriodDTO> periodsDTO = new ArrayList<PeriodDTO>( periods.size() );
		
		for ( Period period : periods ) {
			periodsDTO.add( new PeriodDTO( period ) );
		}
		
		return periodsDTO;
		
	}
	
}
