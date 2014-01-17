package fr.rbillard.budget.dto;

public class PeriodFullDTO extends PeriodDTO {
	
	private static final long serialVersionUID = 1L;

	
	private final String dateFin;
	private final String fini;
	
	
	public PeriodFullDTO( Period period ) {
		super( period );
		this.dateFin = period.getDateFin();
		this.fini = period.getFini();
	}

	public String getDateFin() {
		return dateFin;
	}
	
	public String getFini() {
		return fini;
	}

}
