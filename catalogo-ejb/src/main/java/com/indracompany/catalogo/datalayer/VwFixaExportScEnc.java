package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_SCENC", schema = "CATALOGOPRS_OW")
public class VwFixaExportScEnc{
	@Id
	@Column(name = "NUMEROREGISTRO") 
	private Long numeroRegistro;
	
	@Column(name = "CDSERVICO") 
	private String cdServico;
	
	@Column(name = "NMSERVICO") 
	private String nmServico;
	
	@Column(name = "CDTIPOSOLICITACAOCOMERCIAL") 
	private String cdTipoSolicitacaoComercial;
	
	@Column(name = "NMTIPOSOLICITACAOCOMERCIAL") 
	private String nmTipoSolicitacaoComercial;
	
	@Column(name = "CDSOLICITACAOCOMERCIAL") 
	private String cdSolicitacaoComercial;
	
	@Column(name = "NMSOLICITACAOCOMERCIAL") 
	private String nmSolicitacaoComercial;
	
	@Column(name = "CDENCARGO") 
	private String cdEncargo;
	
	@Column(name = "DSENCARGO") 
	private String dsEncargo;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "CDPLANOFINANCIAMENTO") 
	private String cdPlanoFinanciamento;
	
	@Column(name = "NMPLANOFINANCIAMENTO") 
	private String nmPlanoFinanciamento;
	
	@Column(name = "CDGRUPOCOMERCIAL") 
	private String cdGrupoComercial;
	
	@Column(name = "NMGRUPOCOMERCIAL") 
	private String nmGrupoComercial;
	
	@Column(name = "CDPLANOMINUTOS") 
	private String cdPlanoMinutos;
	
	@Column(name = "NMPLANOMINUTOS") 
	private String nmPlanoMinutos;
	
	@Column(name = "CDAREACONCORRENCIA") 
	private String cdAreaConcorrencia;
	
	@Column(name = "NMAREACONCORRENCIA") 
	private String nmAreaConcorrencia;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDsEncargo() {
		return dsEncargo;
	}

	public void setDsEncargo(String dsEncargo) {
		this.dsEncargo = dsEncargo;
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

	public String getNmPlanoFinanciamento() {
		return nmPlanoFinanciamento;
	}

	public void setNmPlanoFinanciamento(String nmPlanoFinanciamento) {
		this.nmPlanoFinanciamento = nmPlanoFinanciamento;
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
