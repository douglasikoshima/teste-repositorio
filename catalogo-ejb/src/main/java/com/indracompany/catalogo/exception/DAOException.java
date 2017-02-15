package com.indracompany.catalogo.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private DAOException() {
	}

	/**
	 * Construtor
	 * 
	 * @param message
	 *            Mensagem de Erro
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Construtor
	 * 
	 * @param cause
	 *            Exception causadora
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            Mensagem de Erro
	 * 
	 * @param cause
	 *            Exception causadora
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
