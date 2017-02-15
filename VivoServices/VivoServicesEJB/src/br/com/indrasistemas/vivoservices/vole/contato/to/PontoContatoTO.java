package br.com.indrasistemas.vivoservices.vole.contato.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PontoContatoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	public static final int RESULTADO_MAXIMO = 50;

	String nrCnpj;

	String sgUF;

	String nrDDD;

	String cdConta;

	long idTipoEmpresa;

	String dsConsultorRelacionamento;

	String nmGerenteContas;

	long nrTelefoneGerContas;

	String dsEmailGercontas;

	long nrTelefoneConsultor;

	String dsEmailConsultor;

	String nmTecnicoResidente;

	long nrTelefoneTecnico;

	String dsEmailTecnico;

	String dsGAM;

	long nrTelefoneGAM;

	String dsEmailGAM;

	String dsGerenteSecao;

	long nrTelefoneGerSecao;

	String dsEmailGerSecao;

	String dsGerenteDivisao;

	long nrTelefoneGerDivisao;

	String dsEmailGerDivisao;

	String dsRazaoSocialDealer;

	long nrTelef1;

	long nrTelef2;

	String dsEmailDealer;

	String dsCidadeDealer;

	public String getsgUF() {
		return sgUF;
	}

	public void setsgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getDsCidadeDealer() {
		return dsCidadeDealer;
	}

	public void setDsCidadeDealer(String dsCidadeDealer) {
		this.dsCidadeDealer = dsCidadeDealer;
	}

	public String getDsConsultorRelacionamento() {
		return dsConsultorRelacionamento;
	}

	public void setDsConsultorRelacionamento(String dsConsultorRelacionamento) {
		this.dsConsultorRelacionamento = dsConsultorRelacionamento;
	}

	public String getDsEmailConsultor() {
		return dsEmailConsultor;
	}

	public void setDsEmailConsultor(String dsEmailConsultor) {
		this.dsEmailConsultor = dsEmailConsultor;
	}

	public String getDsEmailDealer() {
		return dsEmailDealer;
	}

	public void setDsEmailDealer(String dsEmailDealer) {
		this.dsEmailDealer = dsEmailDealer;
	}

	public String getDsEmailGAM() {
		return dsEmailGAM;
	}

	public void setDsEmailGAM(String dsEmailGAM) {
		this.dsEmailGAM = dsEmailGAM;
	}

	public String getDsEmailGercontas() {
		return dsEmailGercontas;
	}

	public void setDsEmailGercontas(String dsEmailGercontas) {
		this.dsEmailGercontas = dsEmailGercontas;
	}

	public String getDsEmailGerDivisao() {
		return dsEmailGerDivisao;
	}

	public void setDsEmailGerDivisao(String dsEmailGerDivisao) {
		this.dsEmailGerDivisao = dsEmailGerDivisao;
	}

	public String getDsEmailGerSecao() {
		return dsEmailGerSecao;
	}

	public void setDsEmailGerSecao(String dsEmailGerSecao) {
		this.dsEmailGerSecao = dsEmailGerSecao;
	}

	public String getDsEmailTecnico() {
		return dsEmailTecnico;
	}

	public void setDsEmailTecnico(String dsEmailTecnico) {
		this.dsEmailTecnico = dsEmailTecnico;
	}

	public String getDsGAM() {
		return dsGAM;
	}

	public void setDsGAM(String dsGAM) {
		this.dsGAM = dsGAM;
	}

	public String getDsGerenteDivisao() {
		return dsGerenteDivisao;
	}

	public void setDsGerenteDivisao(String dsGerenteDivisao) {
		this.dsGerenteDivisao = dsGerenteDivisao;
	}

	public String getDsGerenteSecao() {
		return dsGerenteSecao;
	}

	public void setDsGerenteSecao(String dsGerenteSecao) {
		this.dsGerenteSecao = dsGerenteSecao;
	}

	public String getDsRazaoSocialDealer() {
		return dsRazaoSocialDealer;
	}

	public void setDsRazaoSocialDealer(String dsRazaoSocialDealer) {
		this.dsRazaoSocialDealer = dsRazaoSocialDealer;
	}

	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	public String getNmGerenteContas() {
		return nmGerenteContas;
	}

	public void setNmGerenteContas(String nmGerenteContas) {
		this.nmGerenteContas = nmGerenteContas;
	}

	public String getNmTecnicoResidente() {
		return nmTecnicoResidente;
	}

	public void setNmTecnicoResidente(String nmTecnicoResidente) {
		this.nmTecnicoResidente = nmTecnicoResidente;
	}

	public String getNrCnpj() {
		return nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	public long getNrTelef1() {
		return nrTelef1;
	}

	public void setNrTelef1(long nrTelef1) {
		this.nrTelef1 = nrTelef1;
	}

	public long getNrTelef2() {
		return nrTelef2;
	}

	public void setNrTelef2(long nrTelef2) {
		this.nrTelef2 = nrTelef2;
	}

	public long getNrTelefoneConsultor() {
		return nrTelefoneConsultor;
	}

	public void setNrTelefoneConsultor(long nrTelefoneConsultor) {
		this.nrTelefoneConsultor = nrTelefoneConsultor;
	}

	public long getNrTelefoneGAM() {
		return nrTelefoneGAM;
	}

	public void setNrTelefoneGAM(long nrTelefoneGAM) {
		this.nrTelefoneGAM = nrTelefoneGAM;
	}

	public long getNrTelefoneGerContas() {
		return nrTelefoneGerContas;
	}

	public void setNrTelefoneGerContas(long nrTelefoneGerContas) {
		this.nrTelefoneGerContas = nrTelefoneGerContas;
	}

	public long getNrTelefoneGerDivisao() {
		return nrTelefoneGerDivisao;
	}

	public void setNrTelefoneGerDivisao(long nrTelefoneGerDivisao) {
		this.nrTelefoneGerDivisao = nrTelefoneGerDivisao;
	}

	public long getNrTelefoneGerSecao() {
		return nrTelefoneGerSecao;
	}

	public void setNrTelefoneGerSecao(long nrTelefoneGerSecao) {
		this.nrTelefoneGerSecao = nrTelefoneGerSecao;
	}

	public long getNrTelefoneTecnico() {
		return nrTelefoneTecnico;
	}

	public void setNrTelefoneTecnico(long nrTelefoneTecnico) {
		this.nrTelefoneTecnico = nrTelefoneTecnico;
	}

	public String getNrDDD() {
		return nrDDD;
	}

	public void setNrDDD(String nrDDD) {
		this.nrDDD = nrDDD;
	}

	public String getCdConta() {
		return cdConta;
	}

	public void setCdConta(String cdConta) {
		this.cdConta = cdConta;
	}

}
