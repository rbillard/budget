package fr.rbillard.budget.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import fr.rbillard.budget.entity.Period;

public class PeriodLightDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String label;
	private Date startDate;
	private Date endDate;
	
	
	public PeriodLightDTO() {

	}
	public PeriodLightDTO( Period period ) {
		this.id = period.getId();
		this.label = period.getLabel();
		this.startDate = period.getStartDate();
		this.endDate = period.getEndDate();
	}
	
	
	public Long getId() {
		return id;
	}
	public PeriodLightDTO setId( Long id ) {
		this.id = id;
		return this;
	}
	
	
	@NotBlank
	public String getLabel() {
		return label;
	}
	public PeriodLightDTO setLabel( String label ) {
		this.label = label;
		return this;
	}
	
	
	@NotNull
	public Date getStartDate() {
		return startDate;
	}
	public PeriodLightDTO setStartDate( Date startDate ) {
		this.startDate = startDate;
		return this;
	}
	
	
	@NotNull
	public Date getEndDate() {
		return endDate;
	}
	public PeriodLightDTO setEndDate( Date endDate ) {
		this.endDate = endDate;
		return this;
	}
	
	
	public static List<PeriodLightDTO> listPeriods2ListPeriodsLightDTO( List<Period> periods ) {
		
		List<PeriodLightDTO> periodsDTO = new ArrayList<>( periods.size() );
		
		for ( Period period : periods ) {
			periodsDTO.add( new PeriodLightDTO( period ) );
		}
		
		return periodsDTO;
		
	}
	

}
