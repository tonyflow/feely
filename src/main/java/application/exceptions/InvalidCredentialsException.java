package application.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1339605139646188610L;

	public InvalidCredentialsException() {
		super();

	}

	public InvalidCredentialsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
