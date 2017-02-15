package br.com.vivo.fo.exception;

/**
 * Classe responsável por prover o lançamento global de avisos produzidos por todas as classes do Front-Office.
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 
public class WarningException extends FOException {

	private static final long serialVersionUID = -7518754653773924587L;

	public WarningException() {
		super();
		
	}

	/**
	 * @param message
	 */
	public WarningException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public WarningException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WarningException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
