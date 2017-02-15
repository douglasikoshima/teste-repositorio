package br.com.indrasistemas.vivoservices.administracaosistema.endereco.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class CEPInvalidoException extends BusinessException {

	private static final long serialVersionUID = -5135369286948172257L;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CEPInvalidoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public CEPInvalidoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CEPInvalidoException(Throwable arg0) {
		super(arg0);
	}

}
