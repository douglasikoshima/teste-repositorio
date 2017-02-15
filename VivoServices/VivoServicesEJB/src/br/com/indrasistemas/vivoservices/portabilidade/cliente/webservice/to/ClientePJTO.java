package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

public class ClientePJTO {

	public ClientePJTO() {
	}

	private static final long serialVersionUID = -13344497893232238L;

	private String nmRazaoSocial;
	private String nmFantasia;
	private String dtFundacao;
	private String sgClassTributaria;
	private String sgClassEmpresa;

	private String nrCNPJ;
	private String nrCNAE;
	private String nrCCM;

	private String nrInscricao;
	private String sgTipoInscricao;

	private TelefoneTO telefone;
	private EnderecoTO endereco;

	public String getNmRazaoSocial() {
		return nmRazaoSocial;
	}

	public void setNmRazaoSocial(String nmRazaoSocial) {
		this.nmRazaoSocial = nmRazaoSocial;
	}

	public String getNmFantasia() {
		return nmFantasia;
	}

	public void setNmFantasia(String nmFantasia) {
		this.nmFantasia = nmFantasia;
	}

	public String getDtFundacao() {
		return dtFundacao;
	}

	public void setDtFundacao(String dtFundacao) {
		this.dtFundacao = dtFundacao;
	}

	public String getSgClassTributaria() {
		return sgClassTributaria;
	}

	public void setSgClassTributaria(String sgClassTributaria) {
		this.sgClassTributaria = sgClassTributaria;
	}

	public String getSgClassEmpresa() {
		return sgClassEmpresa;
	}

	public void setSgClassEmpresa(String sgClassEmpresa) {
		this.sgClassEmpresa = sgClassEmpresa;
	}

	public String getNrCNPJ() {
		return nrCNPJ;
	}

	public void setNrCNPJ(String nrCNPJ) {
		this.nrCNPJ = nrCNPJ;
	}

	public String getNrCNAE() {
		return nrCNAE;
	}

	public void setNrCNAE(String nrCNAE) {
		this.nrCNAE = nrCNAE;
	}

	public String getNrCCM() {
		return nrCCM;
	}

	public void setNrCCM(String nrCCM) {
		this.nrCCM = nrCCM;
	}

	public String getNrInscricao() {
		return nrInscricao;
	}

	public void setNrInscricao(String nrInscricao) {
		this.nrInscricao = nrInscricao;
	}

	public String getSgTipoInscricao() {
		return sgTipoInscricao;
	}

	public void setSgTipoInscricao(String sgTipoInscricao) {
		this.sgTipoInscricao = sgTipoInscricao;
	}

	public TelefoneTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneTO telefone) {
		this.telefone = telefone;
	}

	public EnderecoTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoTO endereco) {
		this.endereco = endereco;
	}

}