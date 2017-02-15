package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

public class EnderecoTO {

	public EnderecoTO() {
	}

	private static final long serialVersionUID = -25487197893232238L;

	private String sgTipoEndereco;
	private String nmTipoLogradouro;
	private String nmTitLogradouro;
	private String nrCEP;
	private String nmLogradouro;
	private String nrLogradouro;
	private String nmComplemento;
	private String nmBairro;
	private String nmMunicipio;
	private String sgUF;

	public String getSgTipoEndereco() {
		return sgTipoEndereco;
	}

	public void setSgTipoEndereco(String sgTipoEndereco) {
		this.sgTipoEndereco = sgTipoEndereco;
	}

	public String getNmTipoLogradouro() {
		return nmTipoLogradouro;
	}

	public void setNmTipoLogradouro(String nmTipoLogradouro) {
		this.nmTipoLogradouro = nmTipoLogradouro;
	}

	public String getNmTitLogradouro() {
		return nmTitLogradouro;
	}

	public void setNmTitLogradouro(String nmTitLogradouro) {
		this.nmTitLogradouro = nmTitLogradouro;
	}

	public String getNrCEP() {
		return nrCEP;
	}

	public void setNrCEP(String nrCEP) {
		this.nrCEP = nrCEP;
	}

	public String getNmLogradouro() {
		return nmLogradouro;
	}

	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}

	public String getNrLogradouro() {
		return nrLogradouro;
	}

	public void setNrLogradouro(String nrLogradouro) {
		this.nrLogradouro = nrLogradouro;
	}

	public String getNmComplemento() {
		return nmComplemento;
	}

	public void setNmComplemento(String nmComplemento) {
		this.nmComplemento = nmComplemento;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getNmMunicipio() {
		return nmMunicipio;
	}

	public void setNmMunicipio(String nmMunicipio) {
		this.nmMunicipio = nmMunicipio;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

}