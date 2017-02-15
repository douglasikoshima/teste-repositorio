package br.com.indrasistemas.vivoservices.portabilidade.cliente.to;

import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.DocumentoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.EnderecoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.TelefoneTO;

public class DadosPFTO {

	public DadosPFTO() {
	}

	public DadosPFTO(String nmPessoa, String sgSexo, String dtNascimento,
			String sgEstadoCivil, String nrCPR, TelefoneTO tel,
			DocumentoTO doc, EnderecoTO end)

	{
		this.setNmPessoa(nmPessoa);
		this.setSgSexo(sgSexo);
		this.setDtNascimento(dtNascimento);
		this.setSgEstadoCivil(sgEstadoCivil);
		this.setNrCPR(nrCPR);

		this.telefone = new DadosTelefoneTO();
		this.telefone.setDsTipoTelefone(tel.getDsTipoTelefone());
		this.telefone.setNrTelefone(tel.getNrTelefone());
		this.telefone.setNmContato(tel.getNmContato());

		this.documento = new DadosDocumentoTO();
		this.documento.setDsTipoDocumento(doc.getDsTipoDocumento());
		this.documento.setNrDocumento(doc.getNrDocumento());
		this.documento.setDtExpedicao(doc.getDtExpedicao());
		this.documento.setDsOrgaoEmissor(doc.getDsOrgaoEmissor());
		this.documento.setSgUFDocumento(doc.getSgUFDocumento());

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

	private static final long serialVersionUID = -1337032197893232238L;

	private String nmPessoa;
	private String sgSexo;
	private String dtNascimento;
	private String sgEstadoCivil;
	private String nrCPR;

	private DadosTelefoneTO telefone;
	private DadosDocumentoTO documento;
	private DadosEnderecoTO endereco;

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public String getSgSexo() {
		return sgSexo;
	}

	public void setSgSexo(String sgSexo) {
		this.sgSexo = sgSexo;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSgEstadoCivil() {
		return sgEstadoCivil;
	}

	public void setSgEstadoCivil(String sgEstadoCivil) {
		this.sgEstadoCivil = sgEstadoCivil;
	}

	public String getNrCPR() {
		return nrCPR;
	}

	public void setNrCPR(String nrCPR) {
		this.nrCPR = nrCPR;
	}

	public DadosTelefoneTO getTelefone() {
		return telefone;
	}

	public void setTelefone(DadosTelefoneTO telefone) {
		this.telefone = telefone;
	}

	public DadosDocumentoTO getDocumento() {
		return documento;
	}

	public void setDocumento(DadosDocumentoTO documento) {
		this.documento = documento;
	}

	public DadosEnderecoTO getEndereco() {
		return endereco;
	}

	public void setEndereco(DadosEnderecoTO endereco) {
		this.endereco = endereco;
	}

}