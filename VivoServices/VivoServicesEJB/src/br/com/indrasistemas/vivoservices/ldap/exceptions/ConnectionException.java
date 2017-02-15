package br.com.indrasistemas.vivoservices.ldap.exceptions; 

public class ConnectionException extends Exception { 

	private static final long serialVersionUID = 6592599829692635260L;

	public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message,cause);
    }
} 
