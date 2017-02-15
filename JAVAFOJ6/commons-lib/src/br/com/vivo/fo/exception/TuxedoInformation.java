package br.com.vivo.fo.exception; 

/**
 * Classe responsável por prover o lançamento de informações produzidos por todas as classes do Front-Office.
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 

public class TuxedoInformation extends Throwable 
{ 

	private static final long serialVersionUID = 6900452911247621710L;

	/**
	 * 
	 */
	public TuxedoInformation() {
		super();
		
	}

	/**
	 * @param message
	 */
	public TuxedoInformation(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public TuxedoInformation(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TuxedoInformation(String message, Throwable cause) {
		super(message, cause);
		
	}
} 
