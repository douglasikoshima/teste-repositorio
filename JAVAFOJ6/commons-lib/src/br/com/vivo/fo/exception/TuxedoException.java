package br.com.vivo.fo.exception;

import br.com.vivo.fo.xml.XmlHeader;

/**
 * Classe responsável por prover o lançamento de erros produzidos pelos Façades "Custom Controls" 
 * na chamada dos controles tuxedo.
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Luciano Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/04/25 13:53:21 $
 **/ 
public class TuxedoException extends FOException {

	private static final long serialVersionUID = -1496828830511098370L;
	private XmlHeader xmlHeader;

	/**
	 * 
	 */
	public TuxedoException() {
		super();
		this.xmlHeader = null;
	}

	/**
	 * @param message
	 */
	public TuxedoException(String message) {
		super(message);
		this.xmlHeader = null;
	}

	/**
	 * @param cause
	 */
	public TuxedoException(Throwable cause) {
		super(cause);
		this.xmlHeader = null;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TuxedoException(String message, Throwable cause) {
		super(message, cause);
		this.xmlHeader = null;
	}

	/**
	 * @param xmlHeader
	 */
    public TuxedoException(XmlHeader xmlHeader) {
        super(xmlHeader.getMessage());
        this.xmlHeader = xmlHeader;
    }

	/**
	 * @param xmlHeader
	 * @param cause
	 */
    public TuxedoException(XmlHeader xmlHeader, Throwable cause) {
        super(xmlHeader.getMessage(), cause);
        this.xmlHeader = xmlHeader;
    }

    /**
     * @return XmlHeader
     */
    public XmlHeader getXmlHeader() { return this.xmlHeader; }
    
}
