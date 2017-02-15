/*
 * Created on 15/04/2004
 */
package br.com.vivo.fo.exception;

/**
 * Classe pai respons�vel por prover centralizar as a��es de exce��es poss�veis no ambiente Front-Office.
 **/ 
public class FOException extends Exception {

	private static final long serialVersionUID = 4764433947564069659L;

	/**
	 * 
	 */
	public FOException() {
		super();
		
	}

	/**
	 * @param message
	 */
	public FOException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public FOException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FOException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
