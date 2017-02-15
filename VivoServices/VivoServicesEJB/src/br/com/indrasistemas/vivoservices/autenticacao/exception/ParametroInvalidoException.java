package br.com.indrasistemas.vivoservices.autenticacao.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class ParametroInvalidoException extends BusinessException {

	private static final long serialVersionUID = -5135562286948172257L;

	public ParametroInvalidoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ParametroInvalidoException(String arg0) {
		super(arg0);
	}

	public ParametroInvalidoException(Throwable arg0) {
		super(arg0);
	}

}
