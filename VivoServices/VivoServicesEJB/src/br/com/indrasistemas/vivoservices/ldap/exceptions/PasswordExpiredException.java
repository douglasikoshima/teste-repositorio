package br.com.indrasistemas.vivoservices.ldap.exceptions; 

public class PasswordExpiredException extends Exception {

	private static final long serialVersionUID = 1855889265267279660L;

	private String ldapMessage = "";

    public PasswordExpiredException() {
    }

    public PasswordExpiredException(String message) {
        super(message);
    }

    public PasswordExpiredException(String message, String ldapMessage) {
        super(message);
        this.ldapMessage = ldapMessage;
    }

    public PasswordExpiredException(Throwable cause) {
        super(cause);
    }

    public PasswordExpiredException(String message, Throwable cause) {
        super(message,cause);
    }

    public PasswordExpiredException(String message, String ldapMessage, Throwable cause) {
        super(message,cause);
        this.ldapMessage = ldapMessage;
    }

}
