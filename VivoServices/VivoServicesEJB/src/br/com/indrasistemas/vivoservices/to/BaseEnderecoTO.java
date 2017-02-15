package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class BaseEnderecoTO extends BaseTransferObject {

	private Long id;

	private String logradouro;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String localidade;

	private String municipio;

	private String cep;

	private String tipoLogradouro;

	private String tituloLogradouro;

	private BasePaisTO pais;

	private BaseUfTO uf;

	private BaseTipoEnderecoTO tipo;

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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getTituloLogradouro() {
		return tituloLogradouro;
	}

	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public BasePaisTO getPais() {
		return pais;
	}

	public void setPais(BasePaisTO pais) {
		this.pais = pais;
	}

	public BaseTipoEnderecoTO getTipo() {
		return tipo;
	}

	public void setTipo(BaseTipoEnderecoTO tipo) {
		this.tipo = tipo;
	}

	public BaseUfTO getUf() {
		return uf;
	}

	public void setUf(BaseUfTO uf) {
		this.uf = uf;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    StringBuffer retValue = new StringBuffer();
	    
	    retValue.append("BaseEnderecoTO ( ")
	        .append(super.toString()).append(TAB)
	        .append("id = ").append(this.id).append(TAB)
	        .append("logradouro = ").append(this.logradouro).append(TAB)
	        .append("numero = ").append(this.numero).append(TAB)
	        .append("complemento = ").append(this.complemento).append(TAB)
	        .append("bairro = ").append(this.bairro).append(TAB)
	        .append("localidade = ").append(this.localidade).append(TAB)
	        .append("municipio = ").append(this.municipio).append(TAB)
	        .append("cep = ").append(this.cep).append(TAB)
	        .append("tipoLogradouro = ").append(this.tipoLogradouro).append(TAB)
	        .append("tituloLogradouro = ").append(this.tituloLogradouro).append(TAB)
	        .append("pais = ").append(this.pais).append(TAB)
	        .append("uf = ").append(this.uf).append(TAB)
	        .append("tipo = ").append(this.tipo).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}
	
	

}
