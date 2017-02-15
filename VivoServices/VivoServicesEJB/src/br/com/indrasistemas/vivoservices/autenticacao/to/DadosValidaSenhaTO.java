package br.com.indrasistemas.vivoservices.autenticacao.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class DadosValidaSenhaTO extends BaseTransferObject {
	
	private static final long serialVersionUID = 1L;
	private Integer cdDDD;
	private Integer cdNumTelefone;
	private String senha;

	public Integer getCdDDD() {
		return cdDDD;
	}
	public void setCdDDD(Integer cdDDD) {
		this.cdDDD = cdDDD;
	}
	public Integer getCdNumTelefone() {
		return cdNumTelefone;
	}
	public void setCdNumTelefone(Integer cdNumTelefone) {
		this.cdNumTelefone = cdNumTelefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
