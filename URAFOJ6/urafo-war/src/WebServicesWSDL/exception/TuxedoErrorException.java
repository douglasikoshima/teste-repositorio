package WebServicesWSDL.exception;

import WebServicesWSDL.xml.XmlHeader;

public class TuxedoErrorException extends TuxedoException {

    private static final long serialVersionUID = 3835139893282606079L;

    public TuxedoErrorException() {
        super();
    }

    public TuxedoErrorException(String message) {
        super(message);
    }

    public TuxedoErrorException(Throwable cause) {
        super(cause);
    }

    public TuxedoErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TuxedoErrorException(XmlHeader xmlHeader) {
        super(xmlHeader);
    }

    public TuxedoErrorException(XmlHeader xmlHeader, Throwable cause) {
        super(xmlHeader, cause);
    }
}
