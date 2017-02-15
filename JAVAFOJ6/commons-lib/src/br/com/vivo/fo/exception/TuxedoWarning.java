package br.com.vivo.fo.exception; 

/**
 * Classe respons�vel por prover o lan�amento de avisos produzidos pelos Fa�ades "Custom Controls" 
 * na chamada do controle tuxedo.
 * 
 * @modulo  Global
 * @usecase N�o Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 
public class TuxedoWarning extends Throwable 
{ 

	private static final long serialVersionUID = 5865391715230863952L;

	/**
	 * 
	 */
	public TuxedoWarning() {
		super();
		
	}

	/**
	 * @param message
	 */
	public TuxedoWarning(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public TuxedoWarning(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TuxedoWarning(String message, Throwable cause) {
		super(message, cause);
		
	}
} 
