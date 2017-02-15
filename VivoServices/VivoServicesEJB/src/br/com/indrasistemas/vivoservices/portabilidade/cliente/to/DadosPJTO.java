package br.com.indrasistemas.vivoservices.portabilidade.cliente.to;

import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.EnderecoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.TelefoneTO;

public class DadosPJTO {

	public DadosPJTO() {
	}

	public DadosPJTO(String nmRazaoSocial, String nmFantasia,
			String dtFundacao, String sgClassTributaria, String sgClassEmpresa,
			String nrCNPJ, String nrCNAE, String nrCCM, String nrInscricao,
			String sgTipoInscricao, TelefoneTO tel, EnderecoTO end) {

		this.setNmRazaoSocial(nmRazaoSocial);
		this.setNmFantasia(nmFantasia);
		this.setDtFundacao(dtFundacao);

		this.setNrCNPJ(nrCNPJ);
		this.setNrCNAE(nrCNAE);
		this.setNrCCM(nrCCM);
		this.setNrInscricao(nrInscricao);
		this.setSgTipoInscricao(sgTipoInscricao);

		this.telefone = new DadosTelefoneTO();
		this.telefone.setDsTipoTelefone(tel.getDsTipoTelefone());
		this.telefone.setNmContato(tel.getNmContato());
		this.telefone.setNrTelefone(tel.getNrTelefone());

		if (end != null) {
			this.endereco = new DadosEnderecoTO();
			this.endereco.setSgTipoEndereco(end.getSgTipoEndereco());
			this.endereco.setNmTipoLogradouro(end.getNmTipoLogradouro());
			this.endereco.setNmTitLogradouro(end.getNmTitLogradouro());
			this.endereco.setNrCEP(end.getNrCEP());
			this.endereco.setNmLogradouro(end.getNmLogradouro());
			this.endereco.setNrLogradouro(end.getNrLogradouro());
			this.endereco.setNmComplemento(end.getNmComplemento());
			this.endereco.setNmBairro(end.getNmBairro());
			this.endereco.setNmMunicipio(end.getNmMunicipio());
			this.endereco.setSgUF(end.getSgUF());
		}

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

	private DadosTelefoneTO telefone;
	private DadosEnderecoTO endereco;

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

	public DadosTelefoneTO getTelefone() {
		return telefone;
	}

	public void setTelefone(DadosTelefoneTO telefone) {
		this.telefone = telefone;
	}

	public DadosEnderecoTO getEndereco() {
		return endereco;
	}

	public void setEndereco(DadosEnderecoTO endereco) {
		this.endereco = endereco;
	}

}