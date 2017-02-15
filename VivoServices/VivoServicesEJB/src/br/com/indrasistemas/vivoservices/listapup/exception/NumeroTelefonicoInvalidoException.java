package br.com.indrasistemas.vivoservices.listapup.exception;

import br.com.indrasistemas.framework.service.BusinessException;

public class NumeroTelefonicoInvalidoException extends BusinessException {

	private static final long serialVersionUID = -5135369286948172257L;

	public NumeroTelefonicoInvalidoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NumeroTelefonicoInvalidoException(String arg0) {
		super(arg0);
	}

	public NumeroTelefonicoInvalidoException(Throwable arg0) {
		super(arg0);
	}

}
