package by.gsu.epamlab.exceptions;

public class SkipOperationExeption extends Exception {

	private static final long serialVersionUID = -6161370968202907851L;

	public SkipOperationExeption() {
		super();
	}

	public SkipOperationExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public SkipOperationExeption(String message) {
		super(message);
	}

	public SkipOperationExeption(Throwable cause) {
		super(cause);
	}
	
}
