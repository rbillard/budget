package fr.rbillard.budget.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	
	
	public PeriodBudget( Period period, Budget budget, BigDecimal amount ) {
		this.id = new PeriodBudgetId( period, budget );
		this.amount = amount;
	}
	
	
	@EmbeddedId
	public PeriodBudgetId getId() {
		return id;
	}
	public void setId( PeriodBudgetId id ) {
		this.id = id;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
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
		
	}

}
