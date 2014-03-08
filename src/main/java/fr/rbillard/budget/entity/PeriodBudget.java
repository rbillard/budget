package fr.rbillard.budget.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import fr.rbillard.budget.entity.PeriodBudget.PeriodBudgetId;
import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "L_PERIOD_BUDGET" )
public class PeriodBudget extends AbstractEntity<PeriodBudgetId> {
	

	private static final long serialVersionUID = 1L;
	
	
	public static final String PROP_ID = "id";


	private PeriodBudgetId id;
	private BigDecimal amount;
	private List<Operation> operations;
	
	
	public PeriodBudget() {
		
	}
	public PeriodBudget( Period period, Budget budget, BigDecimal amount ) {
		this.id = new PeriodBudgetId( period, budget );
		this.amount = amount;
	}
	
	@Transient
	public BigDecimal getConsumedAmount() {
		
		BigDecimal consumedAmount = BigDecimal.ZERO;
		
		for ( Operation operation : getOperations() ) {
			consumedAmount = consumedAmount.add( operation.getAmount() );
		}
		
		return consumedAmount;
		
	}
	
	@Transient
	public BigDecimal getRemainingAmount() {
		return getAmount() != null ? getAmount().subtract( getConsumedAmount() ) : BigDecimal.ZERO;
	}
	
	
	@EmbeddedId
	public PeriodBudgetId getId() {
		return id;
	}
	public PeriodBudget/*void*/ setId( PeriodBudgetId id ) {
		this.id = id;
		return this;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	
	@OneToMany( mappedBy = "periodBudget", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	public List<Operation> getOperations() {
		if ( operations == null ) {
			operations = new ArrayList<>();
		}
		return operations;
	}
	public void setOperations( List<Operation> operations ) {
		this.operations = operations;
	}
	public void addOperation( Operation operation ) {
		getOperations().add( operation );
	}
	public void removeOperation( Operation operation ) {
		getOperations().remove( operation );
	}
	
	
	@Embeddable
    public static class PeriodBudgetId implements Serializable {
		
		
		private static final long serialVersionUID = 1L;
		
		
		public static final String PROP_PERIOD = "period";
		public static final String PROP_BUDGET = "budget";
		
		
		private Period period;
		private Budget budget;
		
		
		public PeriodBudgetId( Period period, Budget budget ) {
			this.period = period;
			this.budget = budget;
		}
		public PeriodBudgetId() {
			
		}
		
		
		@NotNull
		@ManyToOne
		@JoinColumn(name = "T_PERIOD_ID")
		public Period getPeriod() {
			return period;
		}
		public void setPeriod( Period period ) {
			this.period = period;
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
		
		@Override
		public boolean equals( Object obj ) {
			
			// reference
			if ( this == obj ) return true;
			
			// nullité ou type
			if ( obj == null || ! getClass().isAssignableFrom( obj.getClass() ) ) return false;
			
			if ( period == null || period.getId() == null || budget == null || budget.getId() == null ) {
				return super.equals( obj );
			} else {
				// id persisté
				PeriodBudgetId id = (PeriodBudgetId) obj;
				return period.equals( id.getPeriod() ) && budget.equals( id.getBudget() );
			}
			
		}
		
		@Override
		public int hashCode() {
			
			if ( period == null || period.getId() == null || budget == null || budget.getId() == null ) {
				return super.hashCode();
			} else {
				return period.getId().hashCode() + budget.getId().hashCode();
			}

		}
		
	}


}
