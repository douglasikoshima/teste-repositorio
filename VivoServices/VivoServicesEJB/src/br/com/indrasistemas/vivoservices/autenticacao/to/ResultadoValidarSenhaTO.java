package br.com.indrasistemas.vivoservices.autenticacao.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoValidarSenhaTO extends RespostaWSTO {

	private static final long serialVersionUID = 1L;
	private Integer statCom;
	private String cdCodigoRetorno;
	private String primeiroNome;
	private Integer tipoLinha;
	private String nome;
	private String e_mail;
	private String sexo;
	private String dtNasc;
	

	public Integer getStatCom() {
		return statCom;
	}
	public void setStatCom(Integer statCom) {
		this.statCom = statCom;
	}
	public String getCdCodigoRetorno() {
		return cdCodigoRetorno;
	}
	public void setCdCodigoRetorno(String cdCodigoRetorno) {
		this.cdCodigoRetorno = cdCodigoRetorno;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public Integer getTipoLinha() {
		return tipoLinha;
	}
	public void setTipoLinha(Integer tipoLinha) {
		this.tipoLinha = tipoLinha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	
}
