package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

public class CategoriaScoreTO implements Serializable{
	
	public CategoriaScoreTO() {	}
	
	public CategoriaScoreTO(Integer idCategoriaScore) {	
		this.idCategoriaScore = idCategoriaScore;
	}
	
	private static final long serialVersionUID = 1L;
	private Integer idCategoriaScore;
	private String cdCategoriaScore;
	private String nmCategoriaScore;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO;
	private boolean possuiAssociacaoPlano;
	private boolean possuiAssociacaoServico;
    private boolean possuiAssociacaoServicoFixa;
	private boolean possuiAssociacaoOfertaServico;
	
	public String getCdCategoriaScore() {
		return cdCategoriaScore;
	}

	public void setCdCategoriaScore(String cdCategoriaScore) {
		this.cdCategoriaScore = cdCategoriaScore;
	}

	public ClassificacaoCategoriaScoreTO getClassificacaoCategoriaScoreTO() {
		return classificacaoCategoriaScoreTO;
	}

	public void setClassificacaoCategoriaScoreTO(
			ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO) {
		this.classificacaoCategoriaScoreTO = classificacaoCategoriaScoreTO;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdCategoriaScore() {
		return idCategoriaScore;
	}

	public void setIdCategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}

	public String getNmCategoriaScore() {
		return nmCategoriaScore;
	}

	public void setNmCategoriaScore(String nmCategoriaScore) {
		this.nmCategoriaScore = nmCategoriaScore;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public boolean isPossuiAssociacaoOfertaServico() {
		return possuiAssociacaoOfertaServico;
	}

	public void setPossuiAssociacaoOfertaServico(
			boolean possuiAssociacaoOfertaServico) {
		this.possuiAssociacaoOfertaServico = possuiAssociacaoOfertaServico;
	}

	public boolean isPossuiAssociacaoPlano() {
		return possuiAssociacaoPlano;
	}

	public void setPossuiAssociacaoPlano(boolean possuiAssociacaoPlano) {
		this.possuiAssociacaoPlano = possuiAssociacaoPlano;
	}

	public boolean isPossuiAssociacaoServico() {
		return possuiAssociacaoServico;
	}

	public void setPossuiAssociacaoServico(boolean possuiAssociacaoServico) {
		this.possuiAssociacaoServico = possuiAssociacaoServico;
	}

    public boolean isPossuiAssociacaoServicoFixa() {
        return possuiAssociacaoServicoFixa;
    }

    public void setPossuiAssociacaoServicoFixa(boolean possuiAssociacaoServicoFixa) {
        this.possuiAssociacaoServicoFixa = possuiAssociacaoServicoFixa;
    }

}
