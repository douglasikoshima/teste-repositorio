package br.com.indrasistemas.vivoservices.administracaosistema.endereco.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PesquisaEnderecoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	public static final int RESULTADO_MAXIMO = 50;

	private String logradouro;

	private String bairro;

	private String municipio;

	private String cep;

	private String uf;

	public PesquisaEnderecoTO() {
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
