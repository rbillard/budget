package fr.rbillard.budget.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_PERIOD" )
public class Period extends AbstractEntity<Long> {
	
	
	private static final long serialVersionUID = 1L;


	public static final String PROP_ID = "id";
	public static final String PROP_USER = "user";
	
	
	private Long id;
	private String label;
	private Date startDate;
	private Date endDate;
	private User user;
	private List<PeriodBudget> lBudgets;
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	@NotEmpty
	public String getLabel() {
		return label;
	}
	public void setLabel( String label ) {
		this.label = label;
	}
	
	
	@NotNull
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate( Date startDate ) {
		this.startDate = startDate;
	}
	
	
	@NotNull
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate( Date endDate ) {
		this.endDate = endDate;
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
	
	
	@NotNull
	@OneToMany( mappedBy = "id.period", cascade = CascadeType.REMOVE )
	public List<PeriodBudget> getlBudgets() {
		if ( lBudgets == null ) {
			lBudgets = new ArrayList<>();
		}
		return lBudgets;
	}
	public void setlBudgets( List<PeriodBudget> lBudgets ) {
		this.lBudgets = lBudgets;
	}
	public void addlBudget( PeriodBudget lBudget ) {
		getlBudgets().add( lBudget );
	}
	
	@Transient
	public BigDecimal getTotalAmount() {
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for ( PeriodBudget periodBudget : getlBudgets() ) {
			totalAmount = totalAmount.add( periodBudget.getAmount() );
		}
		
		return totalAmount;
		
	}
	
	@Transient
	public BigDecimal getTotalConsumedAmount() {
		
		BigDecimal totalConsumedAmount = BigDecimal.ZERO;
		
		for ( PeriodBudget periodBudget : getlBudgets() ) {
			totalConsumedAmount = totalConsumedAmount.add( periodBudget.getConsumedAmount() );
		}
		
		return totalConsumedAmount;
		
	}
	
	@Transient
	public BigDecimal getRemainingAmount() {
		return getTotalAmount().subtract( getTotalConsumedAmount() );
	}

	// Validation
	@Transient
	// TODO message en conf
	@AssertTrue( message = "La date de début doit être antérieur à la date de fin" )
	private boolean isStartDateBeforeEndDate() {
		// TODO
		return true;
	}
	
}
