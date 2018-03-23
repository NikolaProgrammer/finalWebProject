package by.gsu.epamlab.exceptions;

public class CannotDeleteFileException extends Exception {

	private static final long serialVersionUID = 1449037668537386099L;

	public CannotDeleteFileException() {
		super();
	}

	public CannotDeleteFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotDeleteFileException(String message) {
		super(message);
	}

	public CannotDeleteFileException(Throwable cause) {
		super(cause);
	}
	
	
}
