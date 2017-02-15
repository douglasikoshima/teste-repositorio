package br.com.vivo.ldap.exceptions; 

import br.com.vivo.fo.constantes.ConstantesCRM;

public class PasswordExpiredException extends Exception {

	private static final long serialVersionUID = 155114610845081829L;
	private String ldapMessage = ConstantesCRM.SVAZIO;

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
