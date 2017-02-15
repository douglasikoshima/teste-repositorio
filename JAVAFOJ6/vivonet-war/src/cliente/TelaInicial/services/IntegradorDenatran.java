package cliente.TelaInicial.services;

import br.com.vivo.fo.constantes.ConstantesCRM;

//import br.com.vivo.fo.log.Logger;

public class IntegradorDenatran {

    //private transient Logger logger = new Logger("telainicial");

    // Parâmetros comuns usados na criação das url's de autenticação
    // Caracteres para a composição de url's
    //private final char ADD_FIRST_PARAMETER = '?';
    //private final char ADD_PARAMETER = '&';

    private String apiLogin  = ConstantesCRM.SVAZIO;
    private String apiSenha  = ConstantesCRM.SVAZIO;
    private String subcriber = ConstantesCRM.SVAZIO;
    private String plataform = ConstantesCRM.SVAZIO;
    private String authURL   = ConstantesCRM.SVAZIO;
    
    private String loginUsuario = ConstantesCRM.SVAZIO;
    private String canalAtendimento = ConstantesCRM.SVAZIO;
    private String enderecoIP = ConstantesCRM.SVAZIO;
    private String codigoSessao = ConstantesCRM.SVAZIO;


    public String getApiLogin(){
        return this.apiLogin;
    }

    public String getApiSenha(){
        return this.apiSenha;
    }

    public String getSubscriber(){
        return this.subcriber;
    }

    public String getPlataform(){
        return this.plataform;
    }

    public String getURL(){
        return this.authURL;
    }

    public void setApiLogin(String value){
        this.apiLogin = value;
    }

    public void setApiSenha(String value){
        this.apiSenha = value;
    }

    public void setSubscriber(String value){
        this.subcriber = value;
    }

    public void setPlataform(String value){
        this.plataform = value;
    }

    public void setURL(String value){
        this.authURL = value;
    }
    
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public String getCanalAtendimento() {
		return canalAtendimento;
	}
	public void setCanalAtendimento(String canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}
	public String getEnderecoIP() {
		return enderecoIP;
	}
	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}
	public String getCodigoSessao() {
		return codigoSessao;
	}
	public void setCodigoSessao(String codigoSessao) {
		this.codigoSessao = codigoSessao;
	}    

    public String getToken() throws IntegradorException {
        if(isEmpty(getApiLogin())) throw new IntegradorException("O valor do campo API Login não pode ser vazio");
        if(isEmpty(getApiSenha())) throw new IntegradorException("O valor do campo API Senha não pode ser vazio");
        if(isEmpty(getSubscriber())) throw new IntegradorException("O valor do campo Subscriber não pode ser vazio (Nr. Linha)");
        if(isEmpty(getPlataform())) throw new IntegradorException("O valor do campo plataforma não pode ser vazio (Tp. Linha)");
        if(isEmpty(getURL())) throw new IntegradorException("A URL do autenticador não pode ser vazia.");
        
        if(isEmpty(getLoginUsuario())) throw new IntegradorException("O LOGIN USUARIO do autenticador não pode ser vazia.");
        if(isEmpty(getCanalAtendimento())) throw new IntegradorException("O CANAL DE ATENDIMENTO do autenticador não pode ser vazia.");
        if(isEmpty(getEnderecoIP())) throw new IntegradorException("O ENDERECO IP do autenticador não pode ser vazia.");
        if(isEmpty(getCodigoSessao())) throw new IntegradorException("O CODIGO SESSAO do autenticador não pode ser vazia.");

        
        return GetToken.get(getURL(), getURL4CreateSession(getSubscriber(),getPlataform(),getApiLogin(),getApiSenha(),getLoginUsuario(),getCanalAtendimento(),getEnderecoIP(),getCodigoSessao()));
    }


    private String getURL4CreateSession(String subscriber, String plataforma, String apiLogin, String apiSenha, String login, String canal, String ip, String sessao){
        return "command=createSession&subscriber="+ subscriber + "&plataforma=" + plataforma 
                                                               + "&apilogin=" + apiLogin 
                                                               + "&apipassword=" + apiSenha 
                                                               + "&x-loginUsuario=" + login 
                                                               + "&x-canalAtendimento=" + canal
                                                               + "&x-enderecoIP=" + ip
                                                               + "&x-codigoSessao=" + sessao;
    }

    private String getURL4VerifySession(String apiLogin, String apiSenha, String iToken){
        return "command=verifySession&apilogin=" + apiLogin + "&apipassword=" + apiSenha + "&token=" + iToken;
    }

    private boolean isEmpty(String value){
        return (value==null || ConstantesCRM.SVAZIO.equals(value));
    }
}
