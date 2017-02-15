package br.com.indrasistemas.vivoservices.ldap; 

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPControl;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPModification;
import netscape.ldap.LDAPModificationSet;
import netscape.ldap.LDAPReferralException;
import netscape.ldap.LDAPSearchConstraints;
import netscape.ldap.LDAPSearchResults;
import netscape.ldap.controls.LDAPPasswordExpiredControl;
import netscape.ldap.controls.LDAPPasswordExpiringControl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.vivoservices.ldap.exceptions.PasswordExpiredException;
import br.com.indrasistemas.vivoservices.ldap.exceptions.PasswordExpiringException;

public class LDAPUtil{ 
    
	private transient Log logger = LogFactory.getLog(LDAPUtil.class);

    private LDAPConnection  ld                   = null;
    private String          _ldapServer          = "";
    private int             _ldapPort            = 0;
    private String          _organization        = "";
    private String          _orgUnit             = "";
    private String          _ouGroup             = "";
    private String          _pass                = "";
    private String          _dnGroup             = "";
    private String          _masterDn            = "";
    //private String          _ldapMessage         = "";
    
    public LDAPUtil() throws IOException {
    	Properties props = new Properties();
    	props.load(this.getClass().getClassLoader().getResourceAsStream("/br/ldap.properties"));

    	_ldapServer          = props.getProperty("LDAP_Server");
        _ldapPort            = Integer.parseInt(props.getProperty("LDAP_Port"));
        _organization        = props.getProperty("LDAP_Organization");
        _orgUnit             = props.getProperty("LDAP_OrgUnit");
        _ouGroup             = props.getProperty("LDAP_OuGroup");
        _pass                = props.getProperty("LDAP_pass");
        _dnGroup             = props.getProperty("LDAP_DnGroup");
        _masterDn            = "cn=Directory Manager";
    }
    
    public LDAPUtil(Properties props) {
        _ldapServer          = props.getProperty("LDAP_Server");
        _ldapPort            = Integer.parseInt(props.getProperty("LDAP_Port"));
        _organization        = props.getProperty("LDAP_Organization");
        _orgUnit             = props.getProperty("LDAP_OrgUnit");
        _ouGroup             = props.getProperty("LDAP_OuGroup");
        _pass                = props.getProperty("LDAP_pass");
        _dnGroup             = props.getProperty("LDAP_DnGroup");
        _masterDn            = "cn=Directory Manager";
    }

    private String getUserDN(String user){
        return (new StringBuffer("uid=").append(user).append(", ou=").append(_orgUnit).append(",o=").append(_organization)).toString();
    }
    
    public boolean autenticaUsuario(String user, String passwd) throws PasswordExpiredException, PasswordExpiringException, LDAPException {
        logger.debug("Iniciando Metodo autenticaUsuario()");
        boolean autenticado = false;
        long checked = 0;
        String title = "";
        try{
            connect();
            try{
                logger.debug(">>Iniciando a autenticacao do Usuario: "+user);
                ld.authenticate(3, getUserDN(user),passwd); // Pode retornar Exception caso a senha estiver expirada
                autenticado = ld.isAuthenticated();
                logger.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
                logger.debug(">>Checando Controles do LDAP");
                checked = checkControls();
                logger.debug(">>Controles do LDAP checados");
                logger.debug(">>Desconectando para buscar atributos (usando cn=DM)");
                disconnect();
                logger.debug(">>Buscando atributos do Usuario: "+user);
                title = getAtributoValor(user, "title");
                logger.debug(">>Procedimento de Busca de atributos sem problemas [title = "+title+"]");
            }catch(LDAPException e){
                logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
                switch(e.getLDAPResultCode()){
                    case LDAPException.INVALID_CREDENTIALS: //Se expirar a senha, captura para continuar o processo de autenticação
                        autenticado = ld.isAuthenticated(); 
                        logger.error(">>ERRO na autenticacao no LDAP [autenticado = "+autenticado+"]");
                        logger.error(">>Checando Controles do LDAP");
                        checked = checkControls();
                        logger.error(">>Controles do LDAP checados");
                        if(checked==0){ //Caso não retorne os controle de Senha Expirada, ele errou a senha.
                            logger.error(">>Não retornaram controle de Senha Expirada assumindo Erro na Senha");
                            disconnect();
                            throw e;
                        }
                        logger.error(">>Desconectando para buscar atributos (usando cn=DM)");
                        disconnect();
                        logger.error(">>Buscando atributos do Usuario: "+user);
                        title = getAtributoValor(user, "title");
                        logger.error(">>Procedimento de Busca de atributos sem problemas [title = "+title+"]");
                        break;
                    default:
                        disconnect();
                        throw e;
                }
            }
            logger.debug(">>Verificando os Controles Retornados em conjunto com o atributo do usuario");
            //Verificação dos dados para autenticação
            if(checked>0){
                logger.debug(">>Controle Senha Expirando retornado");
                disconnect();
                throw new PasswordExpiringException("Senha Expirando!\nPor favor, altere a sua senha, pois caso expire será bloqueada.",checked);

            //Verifica se estiver Expirada a senha e o atributo title for FALSE para saber que nao foi feito o reset da senha.
            }else if(checked<0 && title.equalsIgnoreCase("false")){
                logger.debug(">>Verificado senha Expirada e o atributo title=FALSE indicando que nao foi feito o reset da senha.");
                logger.debug(">>Retornando Senha Expirada!");
                disconnect();
                throw new PasswordExpiredException("Senha Expirada! Contate o Administrador para realizar o reset sua senha.");

            //Para Senha expirada, feito o reset, nao autentica o usuario, forçando aqui a validar o usuario.
            }else if(checked<0 && "true".equalsIgnoreCase(title) && "654321".equals(passwd)){
                logger.debug(">>Senha expirada, feito o reset (title=TRUE), forcando validacao do usuario.");
                logger.debug(">>Retornando autenticado = true");
                autenticado = true;
            }
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
        logger.debug(">>Finalizando a autenticacao do Usuario: "+user);
        disconnect();
        return autenticado;
    }
    
    private void connect() throws LDAPException{
        logger.debug(">>Instanciando conexao LDAP ");
        ld = new LDAPConnection();
        logger.debug(">>Conectando ao Servidor LDAP "+_ldapServer+":"+_ldapPort);
        ld.connect(_ldapServer, _ldapPort);
        logger.debug(">>Conexao realizada com sucesso ao LDAP");
    }
    
    private void disconnect(){
        logger.debug(">>Iniciando procedimento de Desconexao");
        if(ld != null){
            try{
                logger.debug(">>Desconectando do Servidor LDAP");
                ld.disconnect();
                logger.debug(">>Desconectando com sucesso do Servidor LDAP");
            }catch(LDAPException ex){
                logger.error(">>ERRO ao Desconectar do Servidor LDAP ["+ex.getLDAPErrorMessage()+"]",ex);
            }
        }
    }

    /**
     * Verifica os controles retornados pelo LDAP como expiração de Senha
     * Se a senha estiver expirada, retorna um valor negativo;
     * Se a senha estiver expirando, retorna um valor positivo, o tempo em ms para a expíração da senha;
     * Se nao possuir controles, retorna o valor zero.
    */
    private long checkControls(){
        long retorno = 0;
        LDAPControl[] respCtls = null;
        if((respCtls = ld.getResponseControls()) != null){
            logger.debug(">>Verificando Tipos de LDAPControls");
            for(int i = 0;i < respCtls.length;i++){
                if(respCtls[i] instanceof LDAPPasswordExpiredControl){
                    LDAPPasswordExpiredControl expired = (LDAPPasswordExpiredControl) respCtls[i];
                    logger.debug(">>Verificado Tipo LDAPPasswordExpiredControl ["+expired.getMessage()+"]");
                    retorno = -1;
                }else if(respCtls[i] instanceof LDAPPasswordExpiringControl){
                    LDAPPasswordExpiringControl expiring = (LDAPPasswordExpiringControl) respCtls[i];
                    logger.debug(">>Verificado Tipo LDAPPasswordExpiringControl ["+expiring.getMessage()+", "+expiring.getSecondsToExpiration()+"]");
                    retorno = expiring.getSecondsToExpiration();
                }
            }
        }
        return retorno;
    }

    /*
    private void handleExceptions(LDAPException e) throws LDAPException {
        switch(e.getLDAPResultCode()){
            case LDAPException.SERVER_DOWN:
                
                break;

            case LDAPException.CONNECT_ERROR:
                _ldapMessage = "\nFalha na autenticação de usuário e senha.Por Favor, tente novamente, e persistindo o erro entre em contato com o Help Desk\n";
                break;

            case LDAPException.NO_SUCH_OBJECT:
                _ldapMessage = "\nNão Existe tal objeto\n";
                break;

            case LDAPException.NO_SUCH_ATTRIBUTE:
                _ldapMessage = "\nNão Existe tal atributo\n";
                break;

            case LDAPException.CONSTRAINT_VIOLATION:
                if(e.getLDAPErrorMessage().toUpperCase().indexOf("PASSWORD RETRY")>-1){
                    _ldapMessage = "Limite de tentativas para a senha excedido. Entre em contato com o Help Desk.";
                }else{
                    _ldapMessage = e.getLDAPErrorMessage();
                }
                break;

            case LDAPException.INVALID_CREDENTIALS:
                _ldapMessage = "\"Senha incorreta tente novamente.\n\nO seu usuário será bloqueado após 03 tentativas incorretas.\"";
                break;

            case LDAPException.INSUFFICIENT_ACCESS_RIGHTS:
                _ldapMessage = "Não possui direitos para realizar a operação solicitada!";
                break;
                
            case LDAPException.UNWILLING_TO_PERFORM:
                if(e.getLDAPErrorMessage().toUpperCase().indexOf("ACCOUNT INACTIVATED")>-1){
                    _ldapMessage = "Login Bloqueado. Entre em contato com o Help Desk.";
                }else{
                    _ldapMessage = e.getLDAPErrorMessage();
                }
                break;

            case LDAPException.ATTRIBUTE_OR_VALUE_EXISTS:
            
                break;

            case LDAPException.ENTRY_ALREADY_EXISTS:
            
                break;

            case LDAPException.LDAP_TIMEOUT:
            
                break;

            case LDAPException.TIME_LIMIT_EXCEEDED:
            
                break;

            default:
                throw e;
        }
    }*/

    public void modificaPropriaSenha(String user, String senhaAntiga, String senhaNova) throws LDAPException {
        long checked = 0;
        try{
            connect();
            try{
                logger.debug(">>Iniciando a autenticacao do Usuario: "+user);
                ld.authenticate(3,getUserDN(user),senhaAntiga);
                logger.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
            }catch(LDAPException e){
                logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
                switch(e.getLDAPResultCode()){
                    case LDAPException.INVALID_CREDENTIALS:
                        logger.debug(">>Checando Controles do LDAP (Verificando se Expirado)");
                        checked = checkControls();
                        logger.debug(">>Controles do LDAP checados");
                        if(checked<0){
                            break;
                        }else{
                            logger.error(">>Não retornaram controle de Senha Expirada assumindo Erro na Senha");
                            disconnect();
                            throw e;
                        }
                }
            }
            LDAPAttribute   atributo = new LDAPAttribute("userPassword", senhaNova);
            LDAPModificationSet mods = new LDAPModificationSet();
            mods.add(LDAPModification.REPLACE, atributo);
            ld.modify(getUserDN(user), mods);
            disconnect();
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw (e);
        }
    }

    public void modificaAtributo(String user, String attributeName, String attributeValue) throws LDAPException {
        try{
            connect();
            ld.authenticate(_masterDn, _pass);
            LDAPAttribute   atributo = new LDAPAttribute(attributeName,attributeValue);
            LDAPModificationSet mods = new LDAPModificationSet();
            mods.add(LDAPModification.REPLACE, atributo);
            ld.modify(getUserDN(user),mods);
            disconnect();
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw (e);
        }
    }

    public void addAtributo(String user, String attributeName, String attributeValue) throws LDAPException {
        try{
            connect();
            ld.authenticate(_masterDn, _pass);
            LDAPAttribute   atributo = new LDAPAttribute(attributeName, attributeValue);
            LDAPModificationSet mods = new LDAPModificationSet();
            mods.add( LDAPModification.ADD, atributo);
            ld.modify(getUserDN(user), mods);
            disconnect();
        }catch(LDAPException e) {
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
    }

    public void adicionaUsuarioGrupo(String atribute, String user) throws LDAPException {
        try {
            connect();
            ld.authenticate(_masterDn, _pass );
            LDAPAttribute   atributo = new LDAPAttribute(atribute, getUserDN(user));
            LDAPModificationSet mods = new LDAPModificationSet();
            mods.add( LDAPModification.ADD, atributo);
            StringBuffer entryDN = new StringBuffer(_dnGroup).append(", ou=").append(_ouGroup).append(",o=").append(_organization);
            ld.modify( entryDN.toString(), mods );
            disconnect();
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
    }

    public void apagarUsuario(String user) throws LDAPException {
        try{
            connect();
            ld.authenticate(_masterDn,_pass);
            ld.delete(getUserDN(user));
            disconnect();
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
   }

    public void setAdicionaUsuario(String user, LDAPAttributeSet ldapAttr) throws LDAPException {
        try {
            connect();
            ld.authenticate(_masterDn, _pass);
            LDAPEntry myEntry = new LDAPEntry( getUserDN(user), ldapAttr);
            ld.add(myEntry);
            disconnect();
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
    }

    public String getAtributoValor(String user, String atributo){
        logger.debug("Iniciando Metodo getAtributoValor()");
        String valor = "";
        String[] myAttrs = {atributo};
        LDAPEntry findEntry = null;
        try{
            connect();
            logger.debug(">>Iniciando a autenticacao do Usuario: "+_masterDn);
            ld.authenticate(_masterDn, _pass);
            logger.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
            String searchBase = new StringBuffer("ou=").append(_orgUnit).append(",o=").append(_organization).toString();
            LDAPSearchConstraints cons = ld.getSearchConstraints();
            cons.setBatchSize(1);
            logger.debug(">>Iniciando procedimento de Busca de atributos");
            logger.debug(">>Busca: ld.search("+searchBase.toString()+", "+LDAPConnection.SCOPE_SUB+", uid="+user+", "+myAttrs+", false, "+cons+")");
            LDAPSearchResults res = ld.search(searchBase, LDAPConnection.SCOPE_SUB, "uid="+user, myAttrs, false, cons);
            logger.debug(">>Busca realizada com sucesso [count="+res.getCount()+"]");
            logger.debug(">>Processando valores");
            while(res.hasMoreElements()) {
                try{
                    findEntry = res.next();
                }catch(LDAPReferralException e){
                    disconnect();
                    throw (e);
                }catch(LDAPException e){
                    continue;
                }
                LDAPAttributeSet findAttrs = findEntry.getAttributeSet();
                Enumeration enumAttrs = findAttrs.getAttributes();
                while(enumAttrs.hasMoreElements()){
                    LDAPAttribute anAttr = (LDAPAttribute) enumAttrs.nextElement();
                    //String attrName = anAttr.getName();
                    Enumeration enumVals = anAttr.getStringValues();
                    if(enumVals != null) {
                        valor = (String) enumVals.nextElement();
                    }
                }
            }
        }catch(LDAPException e){
            logger.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
        }
        disconnect();
        return valor;
    }
} 
