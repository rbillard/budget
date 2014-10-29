package fr.rbillard.budget.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_OPERATION" )
@Audited
@AuditTable( "T_HISTO_OPERATION" )
public class Operation extends AbstractEntity<Long> {
	
	
	private static final long serialVersionUID = 1L;


	public static final String PROP_ID = "id";
	public static final String PROP_PERIOD_BUDGET = "periodBudget";
	
	
	private Long id;
	private Date date;
	private String label;
	private BigDecimal amount;
	private PeriodBudget periodBudget;
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public Operation setId( Long id ) {
		this.id = id;
		return this;
	}
	
	
	@NotNull
	public Date getDate() {
		return date;
	}
	public void setDate( Date date ) {
		this.date = date;
	}
	
	
	@NotNull
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	@NotNull
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	
	
	@NotNull
	@ManyToOne
	@JoinColumns(value = { @JoinColumn( name = "T_BUDGET_ID" ), @JoinColumn( name = "T_PERIOD_ID" ) }  )
	public PeriodBudget getPeriodBudget() {
		return periodBudget;
	}
	public void setPeriodBudget( PeriodBudget periodBudget ) {
		this.periodBudget = periodBudget;
	}
	

}
