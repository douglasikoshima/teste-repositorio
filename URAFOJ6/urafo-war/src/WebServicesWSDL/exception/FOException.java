package WebServicesWSDL.exception;

public class FOException extends Exception {

	private static final long serialVersionUID = 2027793546242168769L;

	public FOException() {
		super();
	}

	public FOException(String message) {
		super(message);
	}

	public FOException(Throwable cause) {
		super(cause);
	}

	public FOException(String message, Throwable cause) {
		super(message, cause);
	}
}