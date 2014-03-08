package fr.rbillard.budget.message;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MessageCreateOperation implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private Long userId;
	private Long periodId;
	private Long budgetId;
	private BigDecimal amount;
	private Date date;
	private String label;
	private MultipartFile file;
	
	
	@NotNull
	public Long getUserId() {
		return userId;
	}
	public MessageCreateOperation setUserId( Long userId ) {
		this.userId = userId;
		return this;
	}
	
	
	@NotNull
	public Long getPeriodId() {
		return periodId;
	}
	public MessageCreateOperation setPeriodId( Long periodId ) {
		this.periodId = periodId;
		return this;
	}
	
	
	@NotNull
	public Long getBudgetId() {
		return budgetId;
	}
	public MessageCreateOperation setBudgetId( Long budgetId ) {
		this.budgetId = budgetId;
		return this;
	}
	
	
	@NotNull
	public BigDecimal getAmount() {
		return amount;
	}
	public MessageCreateOperation setAmount( BigDecimal amount ) {
		this.amount = amount;
		return this;
	}
	
	
	@NotNull
	public Date getDate() {
		return date;
	}
	public MessageCreateOperation setDate( Date date ) {
		this.date = date;
		return this;
	}
	
	
	@NotNull
	public String getLabel() {
		return label;
	}
	public MessageCreateOperation setLabel( String label ) {
		this.label = label;
		return this;
	}
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile( MultipartFile file ) {
		this.file = file;
	}
	

}
