package by.gsu.epamlab.exceptions;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 539708603659580627L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
	
}
