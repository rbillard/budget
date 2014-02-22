package fr.rbillard.budget.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.rbillard.budget.entity.Operation;

public class OperationDTO {
	
	private final Long id;
	private final Date date;
	private final String label;
	private final BigDecimal amount;
	private final Long budgetId;

	public OperationDTO( Operation operation ) {
		
		// TODO use mapper
		
		this.id = operation.getId();
		this.date = operation.getDate();
		this.label = operation.getLabel();
		this.amount = operation.getAmount();
		this.budgetId = operation.getPeriodBudget().getId().getBudget().getId();
		
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
	
	public Long getBudgetId() {
		return budgetId;
	}
	
	public static List<OperationDTO> listOperation2ListOperationDTO( List<Operation> operations ) {
		
		List<OperationDTO> operationsDTO = new ArrayList<>( operations.size() );
		
		for ( Operation operation : operations ) {
			operationsDTO.add( new OperationDTO( operation ) );
		}
		
		return operationsDTO;
		
	}

}
