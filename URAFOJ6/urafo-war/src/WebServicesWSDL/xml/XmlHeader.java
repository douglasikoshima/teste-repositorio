package WebServicesWSDL.xml;

/**
 * Classe responsável por manter os dados presentes no header do XML
 * retornado pelos serviços Tuxedo.
 * 
 * @modulo  Commons
 * @usecase Não Definido
 * @author  Fernando Gomes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5112274 $ - $Date: 2011/06/03 19:39:46 $
 **/ 
public class XmlHeader {
    private String  service;
    private String  user;
    private String  system;
    private char    severity;
    private String  error;
    private String  statusCode;
    private String  statusText;

    // CONSTRUCTORS
    
    public XmlHeader(String service, String user) {
        initServiceUser(service, user);
    }

    public XmlHeader(String system, char severity, String error, String statusText) throws IllegalArgumentException {
        initSystemSeverityError(system, severity, error, statusText);
    }

    public XmlHeader(String service, String user, String system, char severity, String error, String statusText) throws IllegalArgumentException {
        initServiceUser(service, user);
        initSystemSeverityError(system, severity, error, statusText);
    }

    // PUBLIC GETTERS & SETTERS
    
    public String  getService() { return service; }
    public String  getUser() { return user; }
    public String  getSystem() { return system; }
    public char    getSeverity() { return severity; }
    public String  getError() { return error; }
    public String  getStatusCode() { return statusCode; }
    public String  getStatusText() { return statusText; }

    
    // PUBLIC METHODS
    
    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        sb.append(this.service).append(':');
        sb.append(this.user).append(':');
        //sb.append(this.statusCode).append("] ");
        //sb.append(this.statusText);
        return sb.toString();
    }
     

    // PRIVATE METHODS
    
    private void initServiceUser(String service, String user) {
        this.service = service;
        this.user = user;
    }


    private void initSystemSeverityError(String system, char severity, String error, String statusText) throws IllegalArgumentException {
        if ((system == null) || (system.length() != 2)) throw new IllegalArgumentException("system");
        if ((error == null)  || (error.length() != 4))  throw new IllegalArgumentException("error");
        if ("IWE".indexOf(severity) == -1) throw new IllegalArgumentException("severity");
        
        // TODO: validate if both system and error are numeric
        
        this.system = system;
        this.severity = severity;
        this.error = error;
        
        this.statusCode = new StringBuffer().append(system).append(severity).append(error).toString();
        this.statusText = statusText;
    }    

} 
