package application.exceptions;

public class UserAlreadyRegisteredExcpetion extends RuntimeException {

	private static final long serialVersionUID = -3369553252728783819L;

	public UserAlreadyRegisteredExcpetion() {
		super();
	}

	public UserAlreadyRegisteredExcpetion(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAlreadyRegisteredExcpetion(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyRegisteredExcpetion(String message) {
		super(message);
	}

	public UserAlreadyRegisteredExcpetion(Throwable cause) {
		super(cause);
	}

}
