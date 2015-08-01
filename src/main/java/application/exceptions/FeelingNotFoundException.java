package application.exceptions;

public class FeelingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5777387484777486689L;

	public FeelingNotFoundException() {
		super();
	}

	public FeelingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FeelingNotFoundException(String message) {
		super(message);
	}

}
