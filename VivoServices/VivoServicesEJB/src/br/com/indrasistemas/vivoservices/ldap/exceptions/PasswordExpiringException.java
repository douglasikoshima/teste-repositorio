package br.com.indrasistemas.vivoservices.ldap.exceptions; 

public class PasswordExpiringException extends Exception {

	private static final long serialVersionUID = 8727299737113812131L;
	
	private String ldapMessage = "";
    private long timeToExpire  = 0L;

    public PasswordExpiringException() {
    }

    public PasswordExpiringException(String message) {
        super(message);
    }

    public PasswordExpiringException(String message, String ldapMessage) {
        super(message);
        this.ldapMessage = ldapMessage;
    }

    public PasswordExpiringException(String message, long time) {
        super(message);
        this.timeToExpire = time;
    }

    public PasswordExpiringException(String message, long time, String ldapMessage) {
        super(message);
        this.ldapMessage = ldapMessage;
        this.timeToExpire = time;
    }

    public PasswordExpiringException(Throwable cause) {
        super(cause);
    }

    public PasswordExpiringException(String message, Throwable cause) {
        super(message,cause);
    }

    public PasswordExpiringException(String message, String ldapMessage, Throwable cause) {
        super(message,cause);
        this.ldapMessage = ldapMessage;
    }

    public PasswordExpiringException(String message, long time, Throwable cause) {
        super(message,cause);
        this.timeToExpire = time;
    }

    public PasswordExpiringException(String message, long time, String ldapMessage, Throwable cause) {
        super(message,cause);
        this.ldapMessage = ldapMessage;
        this.timeToExpire = time;
    }

    public long getTimeToExpireInDays(){
        return this.timeToExpire/60/60/24;
    }

    public long getTimeToExpireInHours(){
        return this.timeToExpire/60/60;
    }

    public long getTimeToExpireInMinutes(){
        return this.timeToExpire/60;
    }

    public long getTimeToExpireInSeconds(){
        return this.timeToExpire;
    }

}