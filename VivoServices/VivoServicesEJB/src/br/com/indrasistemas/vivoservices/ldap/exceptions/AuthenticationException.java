package br.com.indrasistemas.vivoservices.ldap.exceptions; 

public class AuthenticationException extends Exception { 

	private static final long serialVersionUID = -5868230356229695162L;

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
