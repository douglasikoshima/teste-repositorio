package br.com.vivo.fo.ctrls.cliente.associarGestor.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Gestor implements java.io.Serializable {
	
	private static final long serialVersionUID = -3662138559345870704L;

	private String idConta;
	private String idTipoRelacionamento;
	private String sgTipoRelacionamento;
	private String idPessoaSistemaOrigem;
	private String nmPessoaGestor;
	private String nmGestor;
	private String nmMeioGestor;
	private String nmSobreNomeGestor;
	private String nrTelefoneCelVivo;
	private String nrTelefoneCelOutro;
	private String nrTelefoneFixo;
	private String email;
	private String nrDocumento;
	private String idTipoDocumento;
	private String sgTipoDocumento;
	private Date dtNascimento;
	private String idUuarioAlteracao;
	private String nmLoginUsuario;
	private Date dtUltimaAlteracao;
	private String cdConta;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String getIdConta() {
		return this.idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
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

	public String getIdPessoaSistemaOrigem() {
		return this.idPessoaSistemaOrigem;
	}

	public void setIdPessoaSistemaOrigem(String idPessoaSistemaOrigem) {
		this.idPessoaSistemaOrigem = idPessoaSistemaOrigem;
	}

	public String getNmPessoaGestor() {
		return this.nmPessoaGestor;
	}

	public void setNmPessoaGestor(String nmPessoaGestor) {
		this.nmPessoaGestor = nmPessoaGestor;
	}

	public String getNmGestor() {
		return this.nmGestor;
	}

	public void setNmGestor(String nmGestor) {
		this.nmGestor = nmGestor;
	}

	public String getNmMeioGestor() {
		return this.nmMeioGestor;
	}

	public void setNmMeioGestor(String nmMeioGestor) {
		this.nmMeioGestor = nmMeioGestor;
	}

	public String getNmSobreNomeGestor() {
		return this.nmSobreNomeGestor;
	}

	public void setNmSobreNomeGestor(String nmSobreNomeGestor) {
		this.nmSobreNomeGestor = nmSobreNomeGestor;
	}

	public String getNrTelefoneCelVivo() {
		return this.nrTelefoneCelVivo;
	}

	public String getNrTelefoneCelVivoFormatado() {
		return ConstantesCRM.formatPhoneNumber(this.nrTelefoneCelVivo);
	}

	public void setNrTelefoneCelVivo(String nrTelefoneCelVivo) {
		this.nrTelefoneCelVivo = nrTelefoneCelVivo;
	}

	public String getNrTelefoneCelOutro() {
		return this.nrTelefoneCelOutro;
	}

	public String getNrTelefoneCelOutroFormatado() {
		return ConstantesCRM.formatPhoneNumber(this.nrTelefoneCelOutro);
	}

	public void setNrTelefoneCelOutro(String nrTelefoneCelOutro) {
		this.nrTelefoneCelOutro = nrTelefoneCelOutro;
	}

	public String getNrTelefoneFixo() {
		return this.nrTelefoneFixo;
	}

	public String getNrTelefoneFixoFormatado() {
		return ConstantesCRM.formatPhoneNumber(this.nrTelefoneFixo);
	}

	public void setNrTelefoneFixo(String nrTelefoneFixo) {
		this.nrTelefoneFixo = nrTelefoneFixo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNrDocumento() {
		return this.nrDocumento;
	}

	public String getNrDocumentoFormatado() {
		return this.nmMeioGestor != null && this.nmMeioGestor.length() > 0 ? this.mask(this.nmMeioGestor, "###.###.###-##") : this.mask(this.nrDocumento, "###.###.###-##");
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getSgTipoDocumento() {
		return this.sgTipoDocumento;
	}

	public void setSgTipoDocumento(String sgTipoDocumento) {
		this.sgTipoDocumento = sgTipoDocumento;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public String getDtNascimentoFormatado() {
		return this.dtNascimento != null ? this.sdf.format(this.dtNascimento) : "";
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getIdUuarioAlteracao() {
		return this.idUuarioAlteracao;
	}

	public void setIdUuarioAlteracao(String idUuarioAlteracao) {
		this.idUuarioAlteracao = idUuarioAlteracao;
	}

	public String getNmLoginUsuario() {
		return this.nmLoginUsuario;
	}

	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}

	public Date getDtUltimaAlteracao() {
		return this.dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public String getDtUltimaAlteracaoFormatado() {
		return this.dtUltimaAlteracao != null ? this.sdf.format(this.dtUltimaAlteracao) : "";
	}

	public String getCdConta() {
		return this.cdConta;
	}

	public void setCdConta(String cdConta) {
		this.cdConta = cdConta;
	}

	public String mask(String value, String mask) {

		// Valida entradas
		if (value == null) {
			return "";
		} else if ((mask == null) || (mask.trim().length() == 0)) {
			return value;
		}

		// Monta elementos
		int valueIndex = 0, maskIndex = 0;
		char maskChar = ' ', resultChar = ' ';
		StringBuffer buffer = new StringBuffer();

		// Processa a mascara
		while ((valueIndex < value.length()) && (maskIndex < mask.length())) {
			maskChar = mask.charAt(maskIndex++);

			if (maskChar == '#') {
				resultChar = value.charAt(valueIndex++);
			} else {
				resultChar = maskChar;
			}

			buffer.append(resultChar);
		}

		return buffer.toString();
	}
}