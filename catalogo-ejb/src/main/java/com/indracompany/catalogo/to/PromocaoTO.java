package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PromocaoTO implements Serializable {

    private static final long serialVersionUID = -1811765168014063246L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static final String VIGENTE = "vigente";
    public static final String NAO_INICIADO = "naoiniciado";
    public static final String FINALIZADO = "finalizado";    
    
    private Integer idPromocao;
    private String nmPromocao;
    private Date dtInicio;
    private Date dtFim;
    private String cdPromocao;
    private String inStatusConfiguracao;
    private PoliticaDispPromocaoTO politicaDispPromocaoTO;
    private List<ComposicaoPromocaoTO> composicaoPromocaoTOList;
    
    private String nmServico;
    private String status = "";
    
    public String getPeriodoVigencia(){
        return String.format("%s - %s", this.getDtInicioFormatado(), this.getDtFimFormatado());
    }
    
    public String formatDate(Date date) {
        if (date != null) {
            return sdf.format(date);
        } else {
            return "";
        }        
    }
    
    public boolean isExcluivel() {
        return this.getStatus().equals(NAO_INICIADO) || this.getStatus().equals("");
    }
    
    public boolean isAlteravel() {
        return !this.getStatus().equals(FINALIZADO);
    }
    
    public String getNmPromocaoDisabled() {
        return getStatus().equals(NAO_INICIADO) || getStatus().equals("") ? "" : "disabled";
    }
    
    public String getDtInicioDisabled() {
        return getStatus().equals(NAO_INICIADO) || getStatus().equals("") ? "" : "disabled";
    }
    
    public String getDtFimDisabled() {
        return getStatus().equals(FINALIZADO) ? "disabled" : "";
    }
    
    public String getCdPromocaoDisabled(){
        return this.getCdPromocao() != null && !this.getCdPromocao().equals("") ? "disabled" : "";
    }
    
    public String getComposicaoPromocaoTODisable() {
        return getStatus().equals(NAO_INICIADO) || getStatus().equals("") ? "" : "disabled";
    }
    
    public String getDisponibilidadeDisabled() {
        return getStatus().equals(NAO_INICIADO) || getStatus().equals("") ? "" : "disabled";
    }
    
    public String getDisponibilidadeAlteracao() {
        return getStatus().equals(VIGENTE) || getStatus().equals(NAO_INICIADO) || getStatus().equals("") ? "" : "disabled";
    }    
    
    public boolean isPendenteConfiguracao() {
        return "D".equals(this.getInStatusConfiguracao());
    }
    
    /* default getters and setters*/
    public String getDtInicioFormatado(){
        return this.formatDate(this.dtInicio);
    }
    
    public String getDtFimFormatado(){
        return this.formatDate(this.dtFim);
    }    
    
    public String getCdPromocao() {
        return cdPromocao;
    }
    
    public void setCdPromocao(String cdPromocao) {
        this.cdPromocao = cdPromocao;
    }
    
    public Date getDtFim() {
        return dtFim;
    }
    
    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }
    
    public Date getDtInicio() {
        return dtInicio;
    }
    
    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }
    
    public Integer getIdPromocao() {
        return idPromocao;
    }
    
    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }
    
    public String getNmPromocao() {
        return nmPromocao;
    }
    
    public void setNmPromocao(String nmPromocao) {
        this.nmPromocao = nmPromocao;
    }
    
    public PoliticaDispPromocaoTO getPoliticaDispPromocaoTO() {
        return politicaDispPromocaoTO;
    }
    
    public void setPoliticaDispPromocaoTO(PoliticaDispPromocaoTO politicaDispPromocaoTO) {
        this.politicaDispPromocaoTO = politicaDispPromocaoTO;
    }

    public String getNmServico() {
        return nmServico;
    }

    public void setNmServico(String nmServico) {
        this.nmServico = nmServico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInStatusConfiguracao() {
        return inStatusConfiguracao;
    }

    public void setInStatusConfiguracao(String inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao;
    }

    public List<ComposicaoPromocaoTO> getComposicaoPromocaoTOList() {
        return composicaoPromocaoTOList;
    }

    public void setComposicaoPromocaoTOList(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) {
        this.composicaoPromocaoTOList = composicaoPromocaoTOList;
    }
}