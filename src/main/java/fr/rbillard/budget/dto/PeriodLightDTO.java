package fr.rbillard.budget.dto;

import java.io.Serializable;

import fr.rbillard.budget.app.AppConstants;
import fr.rbillard.budget.entity.Period;
import fr.rbillard.utils.DateUtils;

public class PeriodLightDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String label;
	private String startDate;
	private String endDate;
	
	
	public PeriodLightDTO() {

	}
	public PeriodLightDTO( Period period ) {
		this.id = period.getId();
		this.label = period.getLabel();
		this.startDate = DateUtils.dateToString( period.getStartDate(), AppConstants.FORMAT_DATE );
		this.endDate = DateUtils.dateToString( period.getEndDate(), AppConstants.FORMAT_DATE );
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}
	
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate( String endDate ) {
		this.endDate = endDate;
	}
	

}
