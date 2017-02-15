package br.vivo.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPModification;
import netscape.ldap.LDAPModificationSet;

public class LdapUtil
{ 
	private static Logger logger = null;
	
    private LDAPConnection  ld            = null;
    private String          _server       = "";
    private int             _port         = 0;
    private String          _organization = "";
    private String          _orgUnit      = "";
    private String			_pwd          = "";
    private String			_masterDn     = "";
    
    public LdapUtil(String server, int port, String organization, String unit, String masterDn, String pwd, Logger log) 
    {
    	LdapUtil.logger = log;
        _server       = server;
        _port         = port;
        _organization = organization;
        _orgUnit      = unit;
        _masterDn     = masterDn;
        _pwd		  = pwd;
    }
    
    public int testarConexao()
    {
    	int result = 0;
    	
    	result += startConnection();
    	result += endConnection();
    	
    	return result;
    }

    private String getUserDN(String user)
    {
        return (new StringBuffer("uid=").append(user).append(", ou=").append(_orgUnit).append(",o=").append(_organization)).toString();
    }
    
    public int validarUsuario(String user)
    {
    	try
    	{
	    	startConnection();
	    	
	    	LDAPEntry entry = null;
	    	String userDN = getUserDN(user);
	    	
	    	entry = ld.read(userDN);
	    	entry.getDN();
	    	
	    	endConnection();
	    	
	    	return 0;
    	}
    	catch (LDAPException e) {
    		logger.log(Level.SEVERE, "Erro ao validar Usuário!");
    		logger.log(Level.SEVERE, "VALIDARUSUARIO > ERRO ORACLE -> ldapcode={0},ldaperrmc={1}", new Object[] {e.getLDAPResultCode(), e.getLDAPErrorMessage()});
			return -1;
		}
    }
    
    public int desbloqueiaUsuario(String login)
    {
    	int result = 0;
    	
		result += modificaAtributo(login, "nsaccountlock", "false");
		result += modificaAtributo(login, "passwordRetryCount", "0");
		
		return result;
    }
    
    public int reinicializaSenha(String login)
    {
    	int result = 0;
    	
    	result += modificaAtributo(login, "userPassword", "654321");
		result += modificaAtributo(login, "title", "true");
		
		return result;
    }
    
    public int modificaAtributo(String user, String attributeName, String attributeValue)
    {
    	startConnection();
    	
    	try
    	{
	        ld.authenticate(_masterDn, _pwd);
	        
	        LDAPAttribute atributo = new LDAPAttribute(attributeName,attributeValue);
	        LDAPModificationSet mods = new LDAPModificationSet();
	        mods.add(LDAPModification.REPLACE, atributo);
	        
	        ld.modify(getUserDN(user), mods);
    	} 
        catch (LDAPException e) 
        {
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão LDAP!");
    		logger.log(Level.SEVERE, "STARTCONNECTION > ERRO ORACLE -> ldapcode={0},ldaperrmc={1}", new Object[] {e.getLDAPResultCode(), e.getLDAPErrorMessage()});
    		return -1;
        }
        
        endConnection();
        
        return 0;
    }
    
    private int startConnection()
    {
        try 
        {
        	ld = new LDAPConnection();
			ld.connect(_server, _port);
		} 
        catch (LDAPException e) 
        {
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão LDAP!");
    		logger.log(Level.SEVERE, "STARTCONNECTION > ERRO ORACLE -> ldapcode={0},ldaperrmc={1}", new Object[] {e.getLDAPResultCode(), e.getLDAPErrorMessage()});
    		return -1;
        }
        return 0;
    }
    
    private int endConnection()
    {
        try
        {
        	ld.disconnect();
        }
	    catch (LDAPException e) 
	    {
	    	logger.log(Level.SEVERE, "Erro ao finalizar conexão LDAP!");
			logger.log(Level.SEVERE, "ENDCONNECTION > ERRO ORACLE -> ldapcode={0},ldaperrmc={1}", new Object[] {e.getLDAPResultCode(), e.getLDAPErrorMessage()});
			return -1;
		}
	    return 0;
    }
} 