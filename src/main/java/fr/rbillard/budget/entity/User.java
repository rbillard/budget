package fr.rbillard.budget.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import fr.rbillard.springhibernate.domain.entity.AbstractEntity;

@Entity
@Table( name = "T_USER" )
public class User extends AbstractEntity<Long> {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String login;
	private String password;
	private List<Period> periods;
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin( String login ) {
		this.login = login;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
	
	
	@OneToMany( mappedBy = "user" )
	@Cascade( CascadeType.ALL )
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
	

}
