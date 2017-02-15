package br.com.vivo.ldap; 

import br.com.vivo.fo.admsistemas.vo.LDAPVODocument.LDAPVO;
import br.com.vivo.fo.admsistemas.vo.ListaUsuarioLDAPVODocument.ListaUsuarioLDAPVO;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.ldap.exceptions.PasswordExpiredException;
import br.com.vivo.ldap.exceptions.PasswordExpiringException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
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

public class LDAPUtil{ 
    
    private Logger log = new Logger("ldap");

    private LDAPConnection  ld                   = null;
    private String          _ldapServer          = ConstantesCRM.SVAZIO;
    private int             _ldapPort            = 0;
    private String          _organization        = ConstantesCRM.SVAZIO;
    private String          _orgUnit             = ConstantesCRM.SVAZIO;
    private String          _ouGroup             = ConstantesCRM.SVAZIO;
    private String          _pass                = ConstantesCRM.SVAZIO;
    private String          _dnGroup             = ConstantesCRM.SVAZIO;
    private String          _masterDn            = ConstantesCRM.SVAZIO;
    private String          _ldapMessage         = ConstantesCRM.SVAZIO;

    public LDAPUtil(HttpServletRequest request) throws IOException {
        this(new LoadProperties(request).getProperties());
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
    
    public boolean autenticaUsuario(String user, String passwd) throws PasswordExpiredException, PasswordExpiringException, LDAPException{
        log.debug("Iniciando Metodo autenticaUsuario()");
        boolean autenticado = false;
        long checked = 0;
        String title = ConstantesCRM.SVAZIO;
        try{
            connect();
            try{
                log.debug(">>Iniciando a autenticacao do Usuario: "+user);
                ld.authenticate(3, getUserDN(user),passwd); // Pode retornar Exception caso a senha estiver expirada
                autenticado = ld.isAuthenticated();
                log.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
                log.debug(">>Checando Controles do LDAP");
                checked = checkControls();
                log.debug(">>Controles do LDAP checados");
                log.debug(">>Desconectando para buscar atributos (usando cn=DM)");
                disconnect();
                log.debug(">>Buscando atributos do Usuario: "+user);
                title = getAtributoValor(user, "title");
                log.debug(">>Procedimento de Busca de atributos sem problemas [title = "+title+"]");
            }catch(LDAPException e){
                log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
                switch(e.getLDAPResultCode()){
                    case LDAPException.INVALID_CREDENTIALS: //Se expirar a senha, captura para continuar o processo de autenticação
                        autenticado = ld.isAuthenticated(); 
                        log.error(">>ERRO na autenticacao no LDAP [autenticado = "+autenticado+"]");
                        log.error(">>Checando Controles do LDAP");
                        checked = checkControls();
                        log.error(">>Controles do LDAP checados");
                        if(checked==0){ //Caso não retorne os controle de Senha Expirada, ele errou a senha.
                            log.error(">>Não retornaram controle de Senha Expirada assumindo Erro na Senha");
                            disconnect();
                            throw e;
                        }
                        log.error(">>Desconectando para buscar atributos (usando cn=DM)");
                        disconnect();
                        log.error(">>Buscando atributos do Usuario: "+user);
                        title = getAtributoValor(user, "title");
                        log.error(">>Procedimento de Busca de atributos sem problemas [title = "+title+"]");
                        break;
                    default:
                        disconnect();
                        throw e;
                }
            }
            log.debug(">>Verificando os Controles Retornados em conjunto com o atributo do usuario");
            //Verificação dos dados para autenticação
            if(checked>0){
                log.debug(">>Controle Senha Expirando retornado");
                disconnect();
                throw new PasswordExpiringException("Senha Expirando!\nPor favor, altere a sua senha, pois caso expire será bloqueada.",checked);

            //Verifica se estiver Expirada a senha e o atributo title for FALSE para saber que nao foi feito o reset da senha.
            }else if(checked<0 && title.equalsIgnoreCase("false")){
                log.debug(">>Verificado senha Expirada e o atributo title=FALSE indicando que nao foi feito o reset da senha.");
                log.debug(">>Retornando Senha Expirada!");
                disconnect();
                throw new PasswordExpiredException("Senha Expirada! Contate o Administrador para realizar o reset sua senha.");

            //Para Senha expirada, feito o reset, nao autentica o usuario, forçando aqui a validar o usuario.
            }else if(checked<0 && "true".equalsIgnoreCase(title) && ConstantesCRM.PASSWORD_RESET.equals(passwd)){
                log.debug(">>Senha expirada, feito o reset (title=TRUE), forcando validacao do usuario.");
                log.debug(">>Retornando autenticado = true");
                autenticado = true;
            }
        }catch(LDAPException e){
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
        log.debug(">>Finalizando a autenticacao do Usuario: "+user);
        disconnect();
        return autenticado;
    }
    
    private void connect() throws LDAPException{
        log.debug(">>Instanciando conexao LDAP ");
        ld = new LDAPConnection();
        log.debug(">>Conectando ao Servidor LDAP "+_ldapServer+":"+_ldapPort);
        ld.connect(_ldapServer, _ldapPort);
        log.debug(">>Conexao realizada com sucesso ao LDAP");
    }
    
    private void disconnect(){
        log.debug(">>Iniciando procedimento de Desconexao");
        if(ld != null){
            try{
                log.debug(">>Desconectando do Servidor LDAP");
                ld.disconnect();
                log.debug(">>Desconectando com sucesso do Servidor LDAP");
            }catch(LDAPException ex){
                log.error(">>ERRO ao Desconectar do Servidor LDAP ["+ex.getLDAPErrorMessage()+"]",ex);
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
            log.debug(">>Verificando Tipos de LDAPControls");
            for(int i = 0;i < respCtls.length;i++){
                if(respCtls[i] instanceof LDAPPasswordExpiredControl){
                    LDAPPasswordExpiredControl expired = (LDAPPasswordExpiredControl) respCtls[i];
                    log.debug(">>Verificado Tipo LDAPPasswordExpiredControl ["+expired.getMessage()+"]");
                    retorno = -1;
                }else if(respCtls[i] instanceof LDAPPasswordExpiringControl){
                    LDAPPasswordExpiringControl expiring = (LDAPPasswordExpiringControl) respCtls[i];
                    log.debug(">>Verificado Tipo LDAPPasswordExpiringControl ["+expiring.getMessage()+", "+expiring.getSecondsToExpiration()+"]");
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
                log.debug(">>Iniciando a autenticacao do Usuario: "+user);
                ld.authenticate(3,getUserDN(user),senhaAntiga);
                log.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
            }catch(LDAPException e){
                log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
                switch(e.getLDAPResultCode()){
                    case LDAPException.INVALID_CREDENTIALS:
                        log.debug(">>Checando Controles do LDAP (Verificando se Expirado)");
                        checked = checkControls();
                        log.debug(">>Controles do LDAP checados");
                        if(checked<0){
                            break;
                        }else{
                            log.error(">>Não retornaram controle de Senha Expirada assumindo Erro na Senha");
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
            disconnect();
            throw e;
        }
    }

    public LDAPVO getUsuarioByLogin(String user, int scope){
        return getPesquisaUsuarios("uid="+user, LDAPConnection.SCOPE_SUB).getLDAPVOArray(0);
    }

    public ListaUsuarioLDAPVO getListaUsuariosByLogin(String user){
        return getPesquisaUsuarios("uid=*"+user+"*", LDAPConnection.SCOPE_SUB);
    }
    
    public ListaUsuarioLDAPVO getListaUsuariosByName(String name){
        return getPesquisaUsuarios("cn=*"+name+"*", LDAPConnection.SCOPE_SUB);
    }
    
    public ListaUsuarioLDAPVO getPesquisaUsuarios(String filter, int scope){
        log.debug("Iniciando Metodo getPesquisaUsuarios()");
        String[] myAttrs = {
                            "entryid",
                            "employeenumber",
                            "uid",
                            "cn",
                            "sn",
                            "mail",
                            "givenname",
                            "description",
                            "title",
                            "nsaccountlock",
                            "accountunlocktime",
                            "createtimestamp",
                            "modifytimestamp",
                            "passwordexpwarned",
                            "passwordretrycount",
                            "passwordexpirationtime",
                            "pwdchangedtime",
                            "userpassword",
                            //"passwordhistory",
                            "uniquemember"
                            };
        ListaUsuarioLDAPVO listaLdapVO = ListaUsuarioLDAPVO.Factory.newInstance();
        LDAPEntry findEntry = null;
        try{
            connect();
            log.debug(">>Iniciando a autenticacao do Usuario: "+_masterDn);
            ld.authenticate(_masterDn, _pass);
            log.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
            StringBuffer searchBase = new StringBuffer("ou=").append(_orgUnit).append(",o=").append(_organization);
            LDAPSearchConstraints cons = ld.getSearchConstraints();
            cons.setBatchSize(1);
            log.debug(">>Iniciando procedimento de Busca de atributos");
            log.debug(">>Busca: ld.search("+searchBase.toString()+", "+scope+", "+filter+", "+myAttrs+", false, "+cons+")");
            LDAPSearchResults res = ld.search(searchBase.toString(), scope, filter, myAttrs, false, cons);
            log.debug(">>Busca realizada com sucesso [count="+res.getCount()+"]");
            log.debug(">>Alimentando VO");
            while(res.hasMoreElements()) {
                LDAPVO ldapVO = LDAPVO.Factory.newInstance();
                try{
                    ldapVO.addNewUser().addNewPassword();
                    ldapVO.addNewAccount();
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
                    String attrName = anAttr.getName();
                    Enumeration enumVals = anAttr.getStringValues();
                    if(enumVals != null) {
                        while(enumVals.hasMoreElements()){
                            String valor = (String) enumVals.nextElement();
                            if("entryid".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setEntryId(valor);
                            }else if("employeenumber".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setEmployeeNumber(valor);
                            }else if("uid".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setUid(valor);
                            }else if("cn".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setCn(valor);
                            }else if("sn".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setSn(valor);
                            }else if("givenName".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setGivenName(valor);
                            }else if("mail".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setMail(valor);
                            }else if("description".equalsIgnoreCase(attrName)){
                                ldapVO.getAccount().setDescription(valor);
                            }else if("title".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setTitle(valor);
                            }else if("nsaccountlock".equalsIgnoreCase(attrName)){
                                ldapVO.getAccount().setNsAccountLock(valor);
                            }else if("passwordexpwarned".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setPasswordExpWarned(valor);
                            }else if("passwordretrycount".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setPasswordRetryCount(valor);
                            }else if("createtimestamp".equalsIgnoreCase(attrName)){
                                ldapVO.getAccount().setCreateTimestamp(valor);
                            }else if("accountunlocktime".equalsIgnoreCase(attrName)){
                                ldapVO.getAccount().setAccountUnlockTime(valor);
                            }else if("modifytimestamp".equalsIgnoreCase(attrName)){
                                ldapVO.getAccount().setModifyTimestamp(valor);
                            }else if("passwordexpirationtime".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setPasswordExpirationTime(valor);
                            }else if("pwdchangedtime".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setPasswordChangedTime(valor);
                            }else if("userpassword".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().getPassword().setUserPassword(valor);
                            //}else if("passwordhistory".equalsIgnoreCase(attrName)){
                            }else if("uniquemember".equalsIgnoreCase(attrName)){
                                ldapVO.getUser().setUniqueMember(valor);
                            }
                        }//End while(enumVals.hasMoreElements())
                    }// End if(enumVals != null)
                }// End while(enumAttrs.hasMoreElements())
                listaLdapVO.addNewLDAPVO().set(ldapVO.copy());
            }// End while(res.hasMoreElements())
        }catch(LDAPException e){
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
        }
        disconnect();
        return listaLdapVO;
    }

    public String getAtributoValor(String user, String atributo){
        log.debug("Iniciando Metodo getAtributoValor()");
        String valor = "";
        String[] myAttrs = {atributo};
        LDAPEntry findEntry = null;
        try{
            connect();
            log.debug(">>Iniciando a autenticacao do Usuario: "+_masterDn);
            ld.authenticate(_masterDn, _pass);
            log.debug(">>Procedimento de Autenticacao sem problemas [autenticado = "+ld.isAuthenticated()+"]");
            String searchBase = new StringBuffer("ou=").append(_orgUnit).append(",o=").append(_organization).toString();
            LDAPSearchConstraints cons = ld.getSearchConstraints();
            cons.setBatchSize(1);
            log.debug(">>Iniciando procedimento de Busca de atributos");
            log.debug(">>Busca: ld.search("+searchBase.toString()+", "+LDAPConnection.SCOPE_SUB+", uid="+user+", "+myAttrs+", false, "+cons+")");
            LDAPSearchResults res = ld.search(searchBase, LDAPConnection.SCOPE_SUB, "uid="+user, myAttrs, false, cons);
            log.debug(">>Busca realizada com sucesso [count="+res.getCount()+"]");
            log.debug(">>Processando valores");
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
            log.error(">>ERRO LDAP ["+e.getLDAPResultCode()+"|"+e.getLDAPErrorMessage()+"]", e);
        }
        disconnect();
        return valor;
    }
} 
