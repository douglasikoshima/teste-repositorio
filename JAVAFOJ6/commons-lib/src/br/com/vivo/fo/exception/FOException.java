/*
 * Created on 15/04/2004
 */
package br.com.vivo.fo.exception;

/**
 * Classe pai responsável por prover centralizar as ações de exceções possíveis no ambiente Front-Office.
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
