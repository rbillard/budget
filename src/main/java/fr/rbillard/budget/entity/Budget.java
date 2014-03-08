package fr.rbillard.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_BUDGET" )
public class Budget extends AbstractEntity<Long> {

	
	private static final long serialVersionUID = 1L;


	public static final String PROP_ID = "id";
	public static final String PROP_USER = "user";
	public static final String PROP_L_PERIOD = "lPeriod";
	
	
	private Long id;
	private String label;
	private String color;
	private List<PeriodBudget> lPeriod;
	private User user;
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public Budget/*void*/ setId( Long id ) {
		this.id = id;
		return this;
	}
	
	
	@NotEmpty
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	@NotEmpty
	public String getColor() {
		return color;
	}
	public void setColor( String color ) {
		this.color = color;
	}
	
	
	@OneToMany( mappedBy = "id.budget", cascade = CascadeType.REMOVE )
	public List<PeriodBudget> getlPeriod() {
		if ( lPeriod == null ) {
			lPeriod = new ArrayList<>();
		}
		return lPeriod;
	}
	public void setlPeriod( List<PeriodBudget> lPeriod ) {
		this.lPeriod = lPeriod;
	}
	public void addlBudget( PeriodBudget lPeriod ) {
		getlPeriod().add( lPeriod );
	}
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "T_USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser( User user ) {
		this.user = user;
	}
	

}
