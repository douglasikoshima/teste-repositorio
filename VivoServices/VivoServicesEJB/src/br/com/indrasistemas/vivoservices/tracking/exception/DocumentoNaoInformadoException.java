package br.com.indrasistemas.vivoservices.tracking.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class DocumentoNaoInformadoException extends BusinessException {

	private static final long serialVersionUID = -5135369286948172257L;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DocumentoNaoInformadoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public DocumentoNaoInformadoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DocumentoNaoInformadoException(Throwable arg0) {
		super(arg0);
	}

}
