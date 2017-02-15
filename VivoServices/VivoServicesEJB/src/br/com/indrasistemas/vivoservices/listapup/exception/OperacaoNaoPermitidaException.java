package br.com.indrasistemas.vivoservices.listapup.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class OperacaoNaoPermitidaException extends BusinessException {

	private static final long serialVersionUID = -2135362226948172257L;

	public OperacaoNaoPermitidaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OperacaoNaoPermitidaException(String arg0) {
		super(arg0);
	}

	public OperacaoNaoPermitidaException(Throwable arg0) {
		super(arg0);
	}

}
