package br.com.vivo.ldap.exceptions; 

public class ConnectionException extends Exception { 

	private static final long serialVersionUID = 3918928567825481760L;

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
