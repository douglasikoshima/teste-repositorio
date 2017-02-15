package WebServicesWSDL.exception;

import WebServicesWSDL.xml.XmlHeader;

public class TuxedoWarningException extends TuxedoException {

    private static final long serialVersionUID = -3509917058164851045L;

    public TuxedoWarningException() {
        super();
    }

    public TuxedoWarningException(String message) {
        super(message);
    }

    public TuxedoWarningException(Throwable cause) {
        super(cause);
    }

    public TuxedoWarningException(String message, Throwable cause) {
        super(message, cause);
    }

    public TuxedoWarningException(XmlHeader xmlHeader) {
        super(xmlHeader);
    }

    public TuxedoWarningException(XmlHeader xmlHeader, Throwable cause) {
        super(xmlHeader, cause);
    }
}