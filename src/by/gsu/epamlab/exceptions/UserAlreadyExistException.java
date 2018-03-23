package by.gsu.epamlab.exceptions;

public class UserAlreadyExistException extends DAOException {

	private static final long serialVersionUID = 9179849766680338845L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistException(String message) {
		super(message);
	}

	public UserAlreadyExistException(Throwable cause) {
		super(cause);
	}
	

}
