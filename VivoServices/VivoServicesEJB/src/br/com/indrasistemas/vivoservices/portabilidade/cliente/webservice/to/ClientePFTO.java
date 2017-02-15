package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

public class ClientePFTO {

	public ClientePFTO() {
	}

	private static final long serialVersionUID = -1337032197893232238L;

	private String nmPessoa;
	private String sgSexo;
	private String dtNascimento;
	private String sgEstadoCivil;
	private String nrCPR;

	private EnderecoTO endereco;
	private TelefoneTO telefone;
	private DocumentoTO documento;

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

	public EnderecoTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoTO endereco) {
		this.endereco = endereco;
	}

	public TelefoneTO getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneTO telefone) {
		this.telefone = telefone;
	}

	public DocumentoTO getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoTO documento) {
		this.documento = documento;
	}

}