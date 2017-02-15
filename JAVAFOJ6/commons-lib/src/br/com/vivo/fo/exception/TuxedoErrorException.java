package br.com.vivo.fo.exception;

import br.com.vivo.fo.xml.XmlHeader;

public class TuxedoErrorException extends TuxedoException
{ 

	private static final long serialVersionUID = 2580633976311213560L;

	/**
	 * 
	 */
	public TuxedoErrorException() {
		super();
	}

	/**
	 * @param message
	 */
	public TuxedoErrorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TuxedoErrorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TuxedoErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param xmlHeader
	 */
    public TuxedoErrorException(XmlHeader xmlHeader) {
        super(xmlHeader);
    }

	/**
	 * @param xmlHeader
	 * @param cause
	 */
    public TuxedoErrorException(XmlHeader xmlHeader, Throwable cause) {
        super(xmlHeader, cause);
    }

} 
