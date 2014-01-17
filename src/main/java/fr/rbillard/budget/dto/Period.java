package fr.rbillard.budget.dto;

public class Period {
	
	private Long id;
	private String label;
	private String dateDebut;
	private String dateFin;
	private String[] img;
	private String fini;
	
	
	public Period( Long id, String label, String dateDebut, String dateFin, String img[], String fini ) {
		this.id = id;
		this.label = label;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.img = img;
		this.fini = fini;
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
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut( String dateDebut ) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin( String dateFin ) {
		this.dateFin = dateFin;
	}
	public String[] getImg() {
		return img;
	}
	public void setImg( String[] img ) {
		this.img = img;
	}
	public String getFini() {
		return fini;
	}
	public void setFini( String fini ) {
		this.fini = fini;
	}

}
