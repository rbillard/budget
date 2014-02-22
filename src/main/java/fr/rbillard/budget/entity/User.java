package fr.rbillard.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.rbillard.springhibernate.authentification.SimpleUser;

@Entity
@Table( name = "T_USER" )
public class User extends SimpleUser {
	
	
	private static final long serialVersionUID = 1L;


	public static final String PROP_ID = "id";
	public static final String PROP_LOGIN = "login";

	
	private List<Period> periods;
	private List<Budget> budgets;
	

	@OneToMany( mappedBy = "user", cascade = CascadeType.REMOVE )
	public List<Period> getPeriods() {
		if ( periods == null ) {
			periods = new ArrayList<>();
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
			budgets = new ArrayList<>();
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
