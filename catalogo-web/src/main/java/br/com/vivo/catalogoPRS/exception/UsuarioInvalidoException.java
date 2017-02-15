package br.com.vivo.catalogoPRS.exception;

public class UsuarioInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException(String message, Throwable e) {
		super(message, e);
	}

	public UsuarioInvalidoException(String message) {
		super(message);
	}
	
}
