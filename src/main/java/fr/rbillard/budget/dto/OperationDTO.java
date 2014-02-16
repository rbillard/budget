package fr.rbillard.budget.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.rbillard.budget.app.AppConstants;
import fr.rbillard.budget.entity.Operation;
import fr.rbillard.utils.DateUtils;

public class OperationDTO {
	
	private final Long id;
	private final String date;
	private final String label;
	private final BigDecimal amount;
	private final Long budgetId;

	public OperationDTO( Operation operation ) {
		
		// TODO use mapper
		
		this.id = operation.getId();
		this.date = DateUtils.dateToString( operation.getDate(), AppConstants.FORMAT_DATE );
		this.label = operation.getLabel();
		this.amount = operation.getAmount();
		this.budgetId = operation.getPeriodBudget().getId().getBudget().getId();
		
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
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
