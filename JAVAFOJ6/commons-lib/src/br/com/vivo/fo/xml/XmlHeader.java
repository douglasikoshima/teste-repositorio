package br.com.vivo.fo.xml;

import br.com.vivo.fo.commons.utils.exceptions.ExceptionContainer;
import br.com.vivo.fo.constantes.ConstantesCRM;

import java.io.Serializable;

/**
 * Manter os dados presentes no header do XML retornado pelos servi�os Tuxedo.
 * 
 * @modulo  Commons
 * @usecase N�o Definido
 * @author  Fernando Gomes
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5112274 $ - $Date: 2012/10/11 14:15:32 $
 **/ 
public class XmlHeader implements Serializable{

	private static final long serialVersionUID = 6030562337163202063L;
	private String  service;
    private String  user;
    private String  system;
    private char    severity;
    private String  error;
    private String  statusCode;
    private String  statusText;

    // CONSTRUCTORS
    
    /**
     * Constroi um <code>XmlHeader</code> com o servi�o e usu�rio especificados.
     * 
     * @param service <code>String</code> que cont�m o nome do servi�o Tuxedo.
     * @param user <code>String</code> que cont�m o nome do usu�rio chamador do servi�o Tuxedo.
     */
    public XmlHeader(String service, String user) {
        initServiceUser(service, user);
        initSystemSeverityError(ExceptionContainer.DEFAULT_STATUS_CODE, 'E', ExceptionContainer.DEFAULT_ERROR_CODE, null);
    }

    /**
     * Constroi um <code>XmlHeader</code> com os parametros especificados.
     * 
     * @param service    <code>String</code> que cont�m o nome do servi�o Tuxedo.
     * @param user       <code>String</code> que cont�m o nome do usu�rio chamador do servi�o Tuxedo.
     * @param severity   <code>char</code> que cont�m o c�digo de severidade retornado pelo servi�o Tuxedo. Deve ser 'I', 'W' ou 'E'
     * @param errorCode  <code>String</code> que cont�m o c�digo de erro retornado pelo servi�o Tuxedo.
     * @param statusText <code>String</code> que cont�m a mensagem de erro retornada do servi�o Tuxedo.
     */
    public XmlHeader(String service, String user, String system, char severity, String errorCode, String statusText) throws IllegalArgumentException {
        initServiceUser(service, user);
        initSystemSeverityError(system, severity, errorCode, statusText);
    }

    // PUBLIC GETTERS & SETTERS
    
    /**
     * Retorna o nome do servi�o Tuxedo.
     * 
     * @return <code>String</code> contendo o nome do servi�o.
     */ 
    public String  getService() { return service; }
    
    /**
     * Retorna o nome do usu�rio chamador do servi�o Tuxedo.
     * 
     * @return <code>String</code> contendo o nome do usu�rio.
     */ 
    public String  getUser() { return user; }
    
    /**
     * Retorna o nome do sistema acionado pelo servi�o Tuxedo, o qual foi emissor da mensagem de erro.
     * 
     * @return <code>String</code> contendo o nome do sistema.
     */ 
    public String  getSystem() { return system; }
    
    /**
     * Retorna o c�digo de severidade retornado pelo servi�o Tuxedo.
     * 
     * @return <code>char</code> contendo a severidade.
     */ 
    public char    getSeverity() { return severity; }
    
    /**
     * Retorna o c�digo de erro retornado pelo servi�o Tuxedo.
     * 
     * @return <code>String</code> contendo o c�digo de erro.
     */ 
    public String  getError() { return error; }
    
    /**
     * Retorna o c�digo de erro <i>extendido</i> retornado pelo servi�o Tuxedo.
     * 
     * @return <code>String</code> contendo o c�digo extendido de erro.
     */ 
    public String  getStatusCode() { return statusCode; }
    
    /**
     * Retorna a mensagem de erro retornada pelo servi�o Tuxedo.
     * 
     * @return <code>String</code> contendo a mensagem de erro.
     */ 
    public String  getStatusText() { return statusText; }

    
    // PUBLIC METHODS
    
    /**
     * Retorna os componentes deste <code>XmlHeader</code> formatados.
     */
    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        sb.append(this.service).append(':');
        sb.append(this.user).append(':');
        sb.append(this.statusCode).append("] ");
        sb.append(this.statusText);
        return sb.toString();
    }
     

    /**
     * Retorna os componentes deste <code>XmlHeader</code> formatados.
     */
    public String toString() {
        return getMessage();
    }


    // PRIVATE METHODS
    
    /**
     * Inicia somente os campos service e user
     */
    private void initServiceUser(String service, String user) {
        this.service = service;
        this.user = user;
    }


    /**
     * Inicia somente os campos system, severity, error e statusText
     */
    private void initSystemSeverityError(String system, char severity, String error, String statusText) throws IllegalArgumentException {
        if ((system == null) || (system.length() != 2)) throw new IllegalArgumentException("system");
        if ((error == null)  || (error.length() != 4))  throw new IllegalArgumentException(ConstantesCRM.SERROR);
        if ("IWE".indexOf(severity) == -1) throw new IllegalArgumentException("severity");
        
        // TODO: validate if system and error are both numeric
        
        this.system = system;
        this.severity = severity;
        this.error = error;
        
        this.statusCode = new StringBuffer().append(system).append(severity).append(error).toString();
        this.statusText = statusText;
    }    

} 
