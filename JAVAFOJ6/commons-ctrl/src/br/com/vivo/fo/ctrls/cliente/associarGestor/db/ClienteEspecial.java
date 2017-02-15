package br.com.vivo.fo.ctrls.cliente.associarGestor.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class ClienteEspecial implements java.io.Serializable {

	private static final long serialVersionUID = -8706102448692995038L;

	private String cdCodigoClienteEspecial;
	private String nmNomeCompleto;
	private String nrLinhaContato1;
	private String nrLinhaContato2;
	private String nrCpf;
	private String email;
	private Date dtAniversario;
	private String tpCargo;
	private String cnpj;
	private String nmTipoRelacionamento;
	private String idUuarioAlteracao;
	private String nmLoginUsuario;
	private Date dtUltimaAlteracao;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public String getCdCodigoClienteEspecial() {
		return this.cdCodigoClienteEspecial;
	}

	public void setCdCodigoClienteEspecial(String cdCodigoClienteEspecial) {
		this.cdCodigoClienteEspecial = cdCodigoClienteEspecial;
	}

	public String getNmNomeCompleto() {
		return this.nmNomeCompleto;
	}

	public void setNmNomeCompleto(String nmNomeCompleto) {
		this.nmNomeCompleto = nmNomeCompleto;
	}

	public String getNrLinhaContato1() {
		return this.nrLinhaContato1;
	}

	public String getNrLinhaContato1Formatado() {
		return ConstantesCRM.formatPhoneNumber(this.nrLinhaContato1);
	}

	public void setNrLinhaContato1(String nrLinhaContato1) {
		this.nrLinhaContato1 = nrLinhaContato1;
	}

	public String getNrLinhaContato2() {
		return this.nrLinhaContato2;
	}

	public String getNrLinhaContato2Formatado() {
		return ConstantesCRM.formatPhoneNumber(this.nrLinhaContato2);
	}

	public void setNrLinhaContato2(String nrLinhaContato2) {
		this.nrLinhaContato2 = nrLinhaContato2;
	}

	public String getNrCpf() {
		return this.nrCpf;
	}

	public String getNrCpfFormatado() {
		return this.mask(this.nrCpf, "###.###.###-##");
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtAniversario() {
		return this.dtAniversario;
	}

	public String getDtAniversarioFormatado() {
		return this.dtAniversario != null ? this.sdf.format(this.dtAniversario) : "";
	}

	public void setDtAniversario(Date dtAniversario) {
		this.dtAniversario = dtAniversario;
	}

	public String getTpCargo() {
		return this.tpCargo;
	}

	public void setTpCargo(String tpCargo) {
		this.tpCargo = tpCargo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNmTipoRelacionamento() {
		return this.nmTipoRelacionamento;
	}

	public void setNmTipoRelacionamento(String nmTipoRelacionamento) {
		this.nmTipoRelacionamento = nmTipoRelacionamento;
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
