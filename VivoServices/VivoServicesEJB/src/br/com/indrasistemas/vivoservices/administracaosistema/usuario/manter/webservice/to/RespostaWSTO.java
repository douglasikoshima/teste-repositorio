package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to;

public class RespostaWSTO extends br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO {

	private static final long serialVersionUID = 8022588268332239722L;

	private String idUsuario;
	private String login;
	private String idUF;
	private String nome;
	private String primeiroAcesso;
	private String sobrenome;
	
	private String codError;
	private String msgError;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getIdUF() {
		return idUF;
	}
	public void setIdUF(String idUF) {
		this.idUF = idUF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPrimeiroAcesso() {
		return primeiroAcesso;
	}
	public void setPrimeiroAcesso(String primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
}
