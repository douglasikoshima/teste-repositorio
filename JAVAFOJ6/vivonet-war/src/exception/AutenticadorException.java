package exception;

public class AutenticadorException extends Exception {

	private static final long serialVersionUID = -5135562286948172257L;

	public AutenticadorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AutenticadorException(String arg0) {
		super(arg0);
	}

	public AutenticadorException(Throwable arg0) {
		super(arg0);
	}

}
