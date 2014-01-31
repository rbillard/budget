package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	
	
	@NotNull
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	@NotBlank
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	@NotBlank
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}
	
	
	@NotBlank
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate( String endDate ) {
		this.endDate = endDate;
	}
	
	@AssertTrue( message = "La date de début doit être antérieur à la date de fin." )
	private boolean isStartDateBeforeEndDate() {
		// TODO
		return true;
	}
	
	
	public static List<PeriodLightDTO> listPeriods2ListPeriodsLightDTO( List<Period> periods ) {
		
		List<PeriodLightDTO> periodsDTO = new ArrayList<PeriodLightDTO>( periods.size() );
		
		for ( Period period : periods ) {
			periodsDTO.add( new PeriodLightDTO( period ) );
		}
		
		return periodsDTO;
		
	}
	

}