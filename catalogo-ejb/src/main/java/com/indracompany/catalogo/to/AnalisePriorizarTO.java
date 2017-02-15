package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalisePriorizarTO implements Serializable {

    private static final long serialVersionUID = 165115183754027213L;

    private String dsOfertafixa;
    private String inFWT;
    private String inConvergenteCobre;
    private String inConvergenteFibra;
    private String inSpeedySoloCobre;
    private String inSpeedySoloFibra;
    private String inPortab;
    private Date dtInicial;
    private Date dtFinal;
    private Integer idOfertafixaCreditoScore;
    private Integer cdPrioridade;
    private String nmServicoLinha;
    private String nmServicoPreCad;
    private String nmServicoPlano;
    private String cdOfertafixa;
    
    private static final String TEMPLATE_INFO = "%s (Servi&ccedil;o Linha) + %s (Servi&ccedil;o Plano) + %s (Tipo Oferta) + %s - %s (Per&iacute;odo Vig&ecirc;ncia)";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public String getInfo() {
        return String.format(TEMPLATE_INFO, this.getNmServicoLinha(), this.getNmServicoPlano(), this.getTipoOferta(), this.getDtInicialFormatado(), this.getDtFinalFormatado());
    }
    
    private String getDtFinalFormatado() {
        return this.getDtFinal() != null ? sdf.format(this.getDtFinal()) : "";
    }

    private String getDtInicialFormatado() {
        return this.getDtInicial() != null ? sdf.format(this.getDtInicial()) : "";
    }

    private String getTipoOferta() {
        StringBuilder sb = new StringBuilder();
        sb.append((this.getInConvergenteCobre().equals("S") ? "LSC | " : ""));
        sb.append((this.getInConvergenteFibra().equals("S") ? "LSF | " : ""));
        sb.append((this.getInSpeedySoloCobre().equals("S") ? "SSC | " : ""));
        sb.append((this.getInSpeedySoloFibra().equals("S") ? "SSF | " : ""));
        sb.append((this.getInPortab().equals("S") ? "PORT | " : ""));
        sb.append((this.getInFWT().equals("S") ? "FWT | " : ""));
        String retorno = sb.toString();
        if (retorno.lastIndexOf(" | ") > 0) {
            retorno = retorno.substring(0, sb.lastIndexOf(" | "));
        }
        return retorno;
    }

    public Integer getCdPrioridade() {
        return cdPrioridade;
    }
    public String getDsOfertafixa() {
        return dsOfertafixa;
    }
    public Date getDtFinal() {
        return dtFinal;
    }
    public Date getDtInicial() {
        return dtInicial;
    }
    public Integer getIdOfertafixaCreditoScore() {
        return idOfertafixaCreditoScore;
    }
    public String getInConvergenteCobre() {
        return inConvergenteCobre;
    }
    public String getInConvergenteFibra() {
        return inConvergenteFibra;
    }
    public String getInFWT() {
        return inFWT;
    }
    public String getInPortab() {
        return inPortab;
    }
    public String getInSpeedySoloCobre() {
        return inSpeedySoloCobre;
    }
    public String getInSpeedySoloFibra() {
        return inSpeedySoloFibra;
    }
    public String getNmServicoLinha() {
        return nmServicoLinha != null ? nmServicoLinha : nmServicoPreCad != null ? nmServicoPreCad : "";
    }
    public String getNmServicoPlano() {
        return nmServicoPlano != null ? nmServicoPlano : "";
    }
    public String getNmServicoPreCad() {
        return nmServicoPreCad;
    }
    public void setCdPrioridade(Integer cdPrioridade) {
        this.cdPrioridade = cdPrioridade;
    }
    public void setDsOfertafixa(String dsOfertafixa) {
        this.dsOfertafixa = dsOfertafixa;
    }
    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }
    public void setIdOfertafixaCreditoScore(Integer idOfertafixaCreditoScore) {
        this.idOfertafixaCreditoScore = idOfertafixaCreditoScore;
    }
    public void setInConvergenteCobre(String inConvergenteCobre) {
        this.inConvergenteCobre = inConvergenteCobre;
    }
    public void setInConvergenteFibra(String inConvergenteFibra) {
        this.inConvergenteFibra = inConvergenteFibra;
    }
    public void setInFWT(String inFWT) {
        this.inFWT = inFWT;
    }
    public void setInPortab(String inPortab) {
        this.inPortab = inPortab;
    }
    public void setInSpeedySoloCobre(String inSpeedySoloCobre) {
        this.inSpeedySoloCobre = inSpeedySoloCobre;
    }
    public void setInSpeedySoloFibra(String inSpeedySoloFibra) {
        this.inSpeedySoloFibra = inSpeedySoloFibra;
    }
    public void setNmServicoLinha(String nmServicoLinha) {
        this.nmServicoLinha = nmServicoLinha;
    }
    public void setNmServicoPlano(String nmServicoPlano) {
        this.nmServicoPlano = nmServicoPlano;
    }
    public void setNmServicoPreCad(String nmServicoPreCad) {
        this.nmServicoPreCad = nmServicoPreCad;
    }
	public String getCdOfertafixa() {
		return cdOfertafixa;
	}
	public void setCdOfertafixa(String cdOfertafixa) {
		this.cdOfertafixa = cdOfertafixa;
	}
    
    
}
