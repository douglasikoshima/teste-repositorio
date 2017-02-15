/*
 * Created on 15/04/2004
 */
package br.com.vivo.fo.exception;

/**
 * Classe responsável por prover o lançamento de erros produzidos pelos Façades "Custom Controls" das classes do Front-Office.
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 
public class FacadeException extends FOException {

	private static final long serialVersionUID = 6372150719416603391L;

	/**
	 * 
	 */
	public FacadeException() {
		super();
		
	}

	/**
	 * @param message
	 */
	public FacadeException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public FacadeException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FacadeException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
