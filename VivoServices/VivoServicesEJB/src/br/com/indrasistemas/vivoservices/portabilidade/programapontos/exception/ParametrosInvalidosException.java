/**
 * 
 */
package br.com.indrasistemas.vivoservices.portabilidade.programapontos.exception;

import br.com.indrasistemas.framework.service.BusinessException;

/**
 * @author C_RSRusso
 *
 */
public class ParametrosInvalidosException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6318306823293132124L;

	/**
	 * 
	 */
	public ParametrosInvalidosException() {
	}

	/**
	 * @param msg
	 */
	public ParametrosInvalidosException(String msg) {
		super(msg);
	}

	/**
	 * @param rootCause
	 */
	public ParametrosInvalidosException(Throwable rootCause) {
		super(rootCause);
	}

	/**
	 * @param msg
	 * @param rootCause
	 */
	public ParametrosInvalidosException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}

}
