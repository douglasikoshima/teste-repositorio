package br.com.indrasistemas.framework.foundation.servicegateway.tuxedo.ejb;

/**
 * Exceção lançada na ocorrência de qualquer problema nas chamadas aos serviços
 * Tuxedo.
 * 
 * @author C_LBraga
 * 
 */
class TuxedoServiceCallerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6576408171949325147L;

	public TuxedoServiceCallerException() {
		super();
	}

	public TuxedoServiceCallerException(String message) {
		super(message);
	}

	public TuxedoServiceCallerException(Throwable cause) {
		super(cause);
	}

	public TuxedoServiceCallerException(String message, Throwable cause) {
		super(message, cause);
	}
}
