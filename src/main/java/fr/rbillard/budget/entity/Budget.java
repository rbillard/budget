package fr.rbillard.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_BUDGET" )
public class Budget extends AbstractEntity<Long> {

	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String label;
	private List<PeriodBudget> lPeriod;
	private List<Operation> operations;
	
	
	@Id
	@NotNull
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	@NotNull
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	@OneToMany( mappedBy = "id.budget" )
	public List<PeriodBudget> getlPeriod() {
		if ( lPeriod == null ) {
			lPeriod = new ArrayList<PeriodBudget>();
		}
		return lPeriod;
	}
	public void setlPeriod( List<PeriodBudget> lPeriod ) {
		this.lPeriod = lPeriod;
	}
	public void addlBudget( PeriodBudget lPeriod ) {
		getlPeriod().add( lPeriod );
	}
	
	
	@OneToMany( mappedBy = "budget" )
	@Cascade( CascadeType.ALL )
	public List<Operation> getOperations() {
		if ( operations == null ) {
			operations = new ArrayList<Operation>();
		}
		return operations;
	}
	public void setOperations( List<Operation> operations ) {
		this.operations = operations;
	}
	public void addOperation( Operation operation ) {
		getOperations().add( operation );
	}

}
