package fr.rbillard.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_USER" )
public class User extends AbstractEntity<Long> {
	
	
	private static final long serialVersionUID = 1L;


	public static final String PROP_ID = "id";
	public static final String PROP_LOGIN = "login";

	
	private Long id;
	private String login;
	private String password;
	private List<Period> periods;
	private List<Budget> budgets;
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	@NotNull
	@Column( unique = true )
	public String getLogin() {
		return login;
	}
	public void setLogin( String login ) {
		this.login = login;
	}
	
	
	@NotNull
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
	
	
	@OneToMany( mappedBy = "user", cascade = CascadeType.REMOVE )
	public List<Period> getPeriods() {
		if ( periods == null ) {
			periods = new ArrayList<Period>();
		}
		return periods;
	}
	public void setPeriods( List<Period> periods ) {
		this.periods = periods;
	}
	public void addPeriod( Period period ) {
		getPeriods().add( period );
	}
	public void removePeriod( Period period ) {
		getPeriods().remove( period );
	}
	
	
	@OneToMany( mappedBy = "user", cascade = CascadeType.REMOVE )
	public List<Budget> getBudgets() {
		if ( budgets == null ) {
			budgets = new ArrayList<Budget>();
		}
		return budgets;
	}
	public void setBudgets( List<Budget> budgets ) {
		this.budgets = budgets;
	}
	public void addBudget( Budget budget ) {
		getBudgets().add( budget );
	}
	public void removeBudget( Budget budget ) {
		getBudgets().remove( budget );
	}
	

}
