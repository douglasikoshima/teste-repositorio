package WebServicesWSDL.exception;

import WebServicesWSDL.xml.XmlHeader;

/**
 * Classe responsável por prover o lançamento de erros produzidos pelos Façades "Custom Controls"
 * na chamada dos controles tuxedo.
 **/
public class TuxedoException extends FOException {

    private static final long serialVersionUID = 1951084832233704965L;

    private XmlHeader         xmlHeader;

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
    public XmlHeader getXmlHeader() {
        return this.xmlHeader;
    }

}
