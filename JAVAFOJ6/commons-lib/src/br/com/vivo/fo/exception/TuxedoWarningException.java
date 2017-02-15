package br.com.vivo.fo.exception;

import br.com.vivo.fo.xml.XmlHeader;

/**
 * Classe responsável por prover o lançamento de avisos produzidos pelos Façades "Custom Controls" 
 * na chamada do controle tuxedo.
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 
public class TuxedoWarningException extends TuxedoException 
{ 

	private static final long serialVersionUID = 4803515611241399585L;

	/**
	 * 
	 */
	public TuxedoWarningException() {
		super();
	}

	/**
	 * @param message
	 */
	public TuxedoWarningException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TuxedoWarningException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TuxedoWarningException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param xmlHeader
	 */
    public TuxedoWarningException(XmlHeader xmlHeader) {
        super(xmlHeader);
    }

	/**
	 * @param xmlHeader
	 * @param cause
	 */
    public TuxedoWarningException(XmlHeader xmlHeader, Throwable cause) {
        super(xmlHeader, cause);
    }

} 
