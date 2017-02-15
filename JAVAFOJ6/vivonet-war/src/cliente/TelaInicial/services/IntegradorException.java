package cliente.TelaInicial.services;

/**
 * Exce��o lan�ada pela classe IntegradorPortais quando alguma exce��o ocorre.
 */
public class IntegradorException extends Exception {

	private static final long serialVersionUID = 8784369367945252845L;

	/**
	 * @see Exception#Exception(String)
	 */
	public IntegradorException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(String, Exception)
	 */
	public IntegradorException(String message, Exception cause) {
		super(message, cause);
	}
}
