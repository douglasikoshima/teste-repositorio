package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;

public class SCxGCxPMxACRelatorioFixaForm extends ValidatorActionForm  implements java.io.Serializable {
	
    private static final long serialVersionUID = -879996800426381035L;
    
    private String cdAreaConcorrencia;
    private String cdGrupoComercial;
    private String cdPlanoMinutos;
    private String cdSolicitacaoComercial;
    private String cdTipoSolicitacaoComercial;
    private String nmGrupoComercial;
    private String nmSolicitacaoComercial;
    private String cdServico;
    private String nmServico;
    
    public SCxGCxPMxACRelatorioFixaTO buildTO() {
        SCxGCxPMxACRelatorioFixaTO to = new SCxGCxPMxACRelatorioFixaTO();
        to.setCdServico(this.cdServico);
        to.setNmServico(this.nmServico);
        to.setCdAreaConcorrencia(this.cdAreaConcorrencia);
        to.setCdGrupoComercial(this.cdGrupoComercial);
        to.setCdPlanoMinutos(this.cdPlanoMinutos);
        to.setCdSolicitacaoComercial(this.cdSolicitacaoComercial);
        to.setCdTipoSolicitacaoComercial(this.cdTipoSolicitacaoComercial);
        to.setNmGrupoComercial(this.nmGrupoComercial);
        to.setNmSolicitacaoComercial(this.nmSolicitacaoComercial);
        return to;
    }

    public String getCdAreaConcorrencia() {
        return cdAreaConcorrencia;
    }

    public void setCdAreaConcorrencia(String cdAreaConcorrencia) {
        this.cdAreaConcorrencia = cdAreaConcorrencia;
    }

    public String getCdGrupoComercial() {
        return cdGrupoComercial;
    }

    public void setCdGrupoComercial(String cdGrupoComercial) {
        this.cdGrupoComercial = cdGrupoComercial;
    }

    public String getCdPlanoMinutos() {
        return cdPlanoMinutos;
    }

    public void setCdPlanoMinutos(String cdPlanoMinutos) {
        this.cdPlanoMinutos = cdPlanoMinutos;
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

    public String getNmGrupoComercial() {
        return nmGrupoComercial;
    }

    public void setNmGrupoComercial(String nmGrupoComercial) {
        this.nmGrupoComercial = nmGrupoComercial;
    }

    public String getNmSolicitacaoComercial() {
        return nmSolicitacaoComercial;
    }

    public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
        this.nmSolicitacaoComercial = nmSolicitacaoComercial;
    }

	public String getCdServico() {
		return cdServico;
	}

	public void setCdServico(String cdServico) {
		this.cdServico = cdServico;
	}

	public String getNmServico() {
		return nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}	

}
