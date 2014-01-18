package fr.rbillard.budget.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_OPERATION" )
public class Operation extends AbstractEntity<Long> {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private Date date;
	private String label;
	private BigDecimal amount;
	private Budget budget;
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
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
	@JoinColumn(name = "T_BUDGET_ID")
	public Budget getBudget() {
		return budget;
	}
	public void setBudget( Budget budget ) {
		this.budget = budget;
	}
	

}
