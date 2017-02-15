package br.com.indrasistemas.vivoservices.listapup.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class LinhaInexistenteException extends BusinessException {

	private static final long serialVersionUID = -5135369286948172257L;

	public LinhaInexistenteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LinhaInexistenteException(String arg0) {
		super(arg0);
	}

	public LinhaInexistenteException(Throwable arg0) {
		super(arg0);
	}

}
