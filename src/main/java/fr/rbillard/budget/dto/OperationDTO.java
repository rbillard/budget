package fr.rbillard.budget.dto;

import java.math.BigDecimal;
import java.util.Date;

import fr.rbillard.budget.entity.Operation;

public class OperationDTO {
	
	private final Long id;
	private final Date date;
	private final String label;
	private final BigDecimal amount;

	public OperationDTO( Operation operation ) {
		
		// TODO use mapper
		
		this.id = operation.getId();
		this.date = operation.getDate();
		this.label = operation.getLabel();
		this.amount = operation.getAmount();
		
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getLabel() {
		return label;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	

}
