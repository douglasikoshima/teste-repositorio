package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_SCGC", schema = "CATALOGOPRS_OW")
public class VwFixaExportScgc implements Serializable {

    private static final long serialVersionUID = 7878490751974657018L;

    @Id
    @Column(name = "NUMEROREGISTRO")
    private Long numeroRegistro;
    
    @Column(name = "CDAREACONCORRENCIA")
    private String cdAreaConcorrencia;
    
    @Column(name = "CDGRUPOCOMERCIAL")
    private String cdGrupoComercial;
    
    @Column(name = "CDPLANOMINUTOS")
    private String cdPlanoMinutos;
    
    @Column(name = "cdServico")
    private String cdServico;
    
    @Column(name = "CDSOLICITACAOCOMERCIAL")
    private String cdSolicitacaoComercial;
    
    @Column(name = "CDTIPOSOLICITACAOCOMERCIAL")
    private String cdTipoSolicitacaoComercial;
    
    @Column(name = "NMGRUPOCOMERCIAL")
    private String nmGrupoComercial;
    
    @Column(name = "NMSOLICITACAOCOMERCIAL")
    private String nmSolicitacaoComercial;
    
    @Column(name = "NMTIPOSOLICITACAOCOMERCIAL")
    private String nmTipoSolicitacaoComercial;
    
    @Column(name = "NMAREACONCORRENCIA")
    private String nmAreaConcorrencia;
    
    @Column(name = "NMPLANOMINUTOS")
    private String nmPlanoMinutos;
    
    @Column(name = "NMSERVICO")
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

	public String getNmAreaConcorrencia() {
		return nmAreaConcorrencia;
	}

	public void setNmAreaConcorrencia(String nmAreaConcorrencia) {
		this.nmAreaConcorrencia = nmAreaConcorrencia;
	}

	public String getNmGrupoComercial() {
		return nmGrupoComercial;
	}

	public void setNmGrupoComercial(String nmGrupoComercial) {
		this.nmGrupoComercial = nmGrupoComercial;
	}

	public String getNmPlanoMinutos() {
		return nmPlanoMinutos;
	}

	public void setNmPlanoMinutos(String nmPlanoMinutos) {
		this.nmPlanoMinutos = nmPlanoMinutos;
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

	public String getNmTipoSolicitacaoComercial() {
		return nmTipoSolicitacaoComercial;
	}

	public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
		this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
	}

	public Long getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Long numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

}
