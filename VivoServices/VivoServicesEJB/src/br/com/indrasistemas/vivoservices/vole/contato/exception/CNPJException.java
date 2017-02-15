package br.com.indrasistemas.vivoservices.vole.contato.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class CNPJException extends BusinessException {

	private static final long serialVersionUID = -5135369286948172257L;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CNPJException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public CNPJException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CNPJException(Throwable arg0) {
		super(arg0);
	}

}
