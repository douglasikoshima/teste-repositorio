package br.com.indrasistemas.vivoservices.autenticacao.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class DadosAlteraSenhaTO extends BaseTransferObject {
	private static final long serialVersionUID = 1L;
	private String nrTelefone;
	private String senha;
	public String getNrTelefone() {
		return nrTelefone;
	}
	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
