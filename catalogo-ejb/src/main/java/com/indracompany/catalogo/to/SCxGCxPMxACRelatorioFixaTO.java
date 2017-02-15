package com.indracompany.catalogo.to;

import java.io.Serializable;

public class SCxGCxPMxACRelatorioFixaTO implements Serializable {

    private static final long serialVersionUID = -3836565377210197975L;
    
    private String cdAreaConcorrencia;
    private String cdGrupoComercial;
    private String cdPlanoMinutos;
    private String cdSolicitacaoComercial;
    private String cdTipoSolicitacaoComercial;
    private String nmGrupoComercial;
    private String nmSolicitacaoComercial;
    private String nmTipoSolicitacaoComercial;
    private String nmAreaConcorrencia;
    private String nmPlanoMinutos;
    private String cdServico;
    private String nmServico;
    
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

    public String getNmTipoSolicitacaoComercial() {
        return nmTipoSolicitacaoComercial;
    }

    public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
        this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
    }

    public String getNmAreaConcorrencia() {
        return nmAreaConcorrencia;
    }

    public void setNmAreaConcorrencia(String nmAreaConcorrencia) {
        this.nmAreaConcorrencia = nmAreaConcorrencia;
    }

    public String getNmPlanoMinutos() {
        return nmPlanoMinutos;
    }

    public void setNmPlanoMinutos(String nmPlanoMinutos) {
        this.nmPlanoMinutos = nmPlanoMinutos;
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
