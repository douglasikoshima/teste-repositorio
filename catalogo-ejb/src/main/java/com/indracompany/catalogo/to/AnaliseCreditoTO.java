package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 */
public class AnaliseCreditoTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public AnaliseCreditoTO() {
    }

    public AnaliseCreditoTO(Integer idAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
    }

    public AnaliseCreditoTO(Integer idAnaliseCredito, String nmAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
        this.nmAnaliseCredito = nmAnaliseCredito;
    }

    public AnaliseCreditoTO(Integer idAnaliseCredito, String cdCor, Boolean flagScore) {
        this.idAnaliseCredito = idAnaliseCredito;
        this.cdCor = cdCor;
        this.flagScore = flagScore;
    }

    private Integer idAnaliseCredito;
    private String cdCor;
    private Date dtCriacao;
    private Date dtUltimaAlteracao;
    private String nmAnaliseCredito;
    private String nmUsuarioAlteracao;
    private String nmUsuarioCriacao;
    private CanalAtendimentoTO canalAtendimentoTO;
    private Boolean flagScore = false;
    private Boolean priorizado = Boolean.FALSE;

    public boolean contains(Object o) {
        return true;
    }

    public String getCdCor() {
        return cdCor;
    }

    public void setCdCor(String cdCor) {
        this.cdCor = cdCor;
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

    public Integer getIdAnaliseCredito() {
        return idAnaliseCredito;
    }

    public void setIdAnaliseCredito(Integer idAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
    }

    public String getNmAnaliseCredito() {
        return nmAnaliseCredito;
    }

    public void setNmAnaliseCredito(String nmAnaliseCredito) {
        this.nmAnaliseCredito = nmAnaliseCredito;
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

    public CanalAtendimentoTO getCanalAtendimentoTO() {
        return canalAtendimentoTO;
    }

    public void setCanalAtendimentoTO(CanalAtendimentoTO canalAtendimentoTO) {
        this.canalAtendimentoTO = canalAtendimentoTO;
    }

    @Override
    public String toString() {
        return StringUtils.join(new String[] { "idAnaliseCredito: " + this.idAnaliseCredito }, ", ");
    }

    public Boolean getFlagScore() {
        return flagScore;
    }

    public void setFlagScore(Boolean flagScore) {
        this.flagScore = flagScore;
    }

	public Boolean getPriorizado() {
		return priorizado;
	}

	public void setPriorizado(Boolean priorizado) {
		this.priorizado = priorizado;
	}

}