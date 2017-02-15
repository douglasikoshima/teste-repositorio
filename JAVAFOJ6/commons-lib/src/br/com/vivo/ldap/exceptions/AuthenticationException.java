package br.com.vivo.ldap.exceptions; 

public class AuthenticationException extends Exception { 

	private static final long serialVersionUID = -1578550960826229389L;

	public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message,cause);
    }
} 
