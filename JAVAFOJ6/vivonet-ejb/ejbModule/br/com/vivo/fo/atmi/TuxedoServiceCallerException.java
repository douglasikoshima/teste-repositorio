package br.com.vivo.fo.atmi;

public class TuxedoServiceCallerException extends Exception {

    private static final long serialVersionUID = 1L;

    public TuxedoServiceCallerException() {
        super();
    }

    public TuxedoServiceCallerException(String message) {
        super(message);
    }

    public TuxedoServiceCallerException(Throwable cause) {
        super(cause);
    }

    public TuxedoServiceCallerException(String message, Throwable cause) {
        super(message, cause);
    }
}
