package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class SCxENCxPFxGCxPMxACRelatorioFixaForm extends ValidatorActionForm  implements java.io.Serializable {
	
	private static final long serialVersionUID = -967777986765024052L;
	
    private String cdServico;
	private String cdAreaConcorrencia;
	private String cdEncargo;
    private String cdGrupoComercial;
    private String cdPlanoFinanciamento;
    private String cdPlanoMinutos;
    private String cdSolicitacaoComercial;
    private String cdTipoSolicitacaoComercial;
    private String dsEncargo;
    private String nmGrupoComercial;
    private String nmPlanoFinanciamento;
    private String nmServico;
    private String nmSolicitacaoComercial;
    
	public String getCdAreaConcorrencia() {
		return cdAreaConcorrencia;
	}

	public void setCdAreaConcorrencia(String cdAreaConcorrencia) {
		this.cdAreaConcorrencia = cdAreaConcorrencia;
	}

	public String getCdEncargo() {
		return cdEncargo;
	}

	public void setCdEncargo(String cdEncargo) {
		this.cdEncargo = cdEncargo;
	}

	public String getCdGrupoComercial() {
		return cdGrupoComercial;
	}

	public void setCdGrupoComercial(String cdGrupoComercial) {
		this.cdGrupoComercial = cdGrupoComercial;
	}

	public String getCdPlanoFinanciamento() {
		return cdPlanoFinanciamento;
	}

	public void setCdPlanoFinanciamento(String cdPlanoFinanciamento) {
		this.cdPlanoFinanciamento = cdPlanoFinanciamento;
	}

	public String getCdPlanoMinutos() {
		return cdPlanoMinutos;
	}

	public void setCdPlanoMinutos(String cdPlanoMinutos) {
		this.cdPlanoMinutos = cdPlanoMinutos;
	}

	public String getCdServico() {
		return cdServico;
	}

	public void setCdServico(String cdServico) {
		this.cdServico = cdServico;
	}

	public String getCdSolicitacaoComercial() {
		return cdSolicitacaoComercial;
	}

	public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
		this.cdSolicitacaoComercial = cdSolicitacaoComercial;
	}

	public String getCdTipoSolicitacaoComercial() {
		return cdTipoSolicitacaoComercial;
	}

	public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
		this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
	}

	public String getDsEncargo() {
		return dsEncargo;
	}

	public void setDsEncargo(String dsEncargo) {
		this.dsEncargo = dsEncargo;
	}

	public String getNmGrupoComercial() {
		return nmGrupoComercial;
	}

	public void setNmGrupoComercial(String nmGrupoComercial) {
		this.nmGrupoComercial = nmGrupoComercial;
	}

	public String getNmPlanoFinanciamento() {
		return nmPlanoFinanciamento;
	}

	public void setNmPlanoFinanciamento(String nmPlanoFinanciamento) {
		this.nmPlanoFinanciamento = nmPlanoFinanciamento;
	}

	public String getNmServico() {
		return nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}

	public String getNmSolicitacaoComercial() {
		return nmSolicitacaoComercial;
	}

	public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
		this.nmSolicitacaoComercial = nmSolicitacaoComercial;
	}

}
