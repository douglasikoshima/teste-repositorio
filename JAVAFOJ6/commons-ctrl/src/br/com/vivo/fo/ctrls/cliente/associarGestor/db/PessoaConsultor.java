package br.com.vivo.fo.ctrls.cliente.associarGestor.db;

public class PessoaConsultor implements java.io.Serializable {

	private static final long serialVersionUID = -3889362370795962203L;

	private String nrDocumento;
	private String idPessoa;
	private String nmLoginUsuario;
	private String idPessoaSistemaOrigem;
	private String nmNome;
	private String idPerfilConsultorAtd;
	private String idTipoRelacionamento;
	private String sgTipoRelacionamento;

	public String getNrDocumento() {
		return this.nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNmLoginUsuario() {
		return this.nmLoginUsuario;
	}

	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}

	public String getIdPessoaSistemaOrigem() {
		return this.idPessoaSistemaOrigem;
	}

	public void setIdPessoaSistemaOrigem(String idPessoaSistemaOrigem) {
		this.idPessoaSistemaOrigem = idPessoaSistemaOrigem;
	}

	public String getNmNome() {
		return this.nmNome;
	}

	public void setNmNome(String nmNome) {
		this.nmNome = nmNome;
	}

	public String getIPerfilConsultorAtd() {
		return this.idPerfilConsultorAtd;
	}

	public void setIPerfilConsultorAtd(String idPerfilConsultorAtd) {
		this.idPerfilConsultorAtd = idPerfilConsultorAtd;
	}

	public String getIdTipoRelacionamento() {
		return this.idTipoRelacionamento;
	}

	public void setIdTipoRelacionamento(String idTipoRelacionamento) {
		this.idTipoRelacionamento = idTipoRelacionamento;
	}

	public String getSgTipoRelacionamento() {
		return this.sgTipoRelacionamento;
	}

	public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
		this.sgTipoRelacionamento = sgTipoRelacionamento;
	}

}
