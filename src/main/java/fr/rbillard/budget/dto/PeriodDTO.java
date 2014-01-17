package fr.rbillard.budget.dto;

import java.io.Serializable;

public class PeriodDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private final Long id;
	private final String label;
	private final String dateDebut;
	private final String img[];
	
	
	public PeriodDTO( Period period ) {
		this.id = period.getId();
		this.label = period.getLabel();
		this.dateDebut = period.getDateDebut();
		this.img = period.getImg();
	}


	public Long getId() {
		return id;
	}

	
	public String getLabel() {
		return label;
	}


	public String getDateDebut() {
		return dateDebut;
	}
	

	public String[] getImg() {
		return img;
	}
	
	
}
