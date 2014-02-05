package fr.rbillard.budget.exception;

public class NoSuchEntityException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchEntityException( String message) {
		super( message );
	}

}
