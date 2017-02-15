package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;

public class ServicosAdicionaisTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4743382312257127L;
    // Atributos
    private Integer id; // Var genérico para pesquisar por ID.
    private Integer idServicosAdicionais; // Chave-primária da tabela OfertaFixaAdicional (idOfertaFixaAdicional).
    private Integer idServico; // Campo oculto para usuário.
    private String cdServico; // Utilizado para a view.
    private String nomeServico;
    private String nmTipoServico;
    private Integer idTipoServico; // Campo oculto para usuário.
    private Integer cdTipoServico;
    private SolicitacaoComercial solicitacaoComercial;
    private Long idSolicitacaoComercial;
    private String cdSolicitacaoComercial;
    private String nomeSolicitacaoComercial;
    private Date dtInicio;
    private Date dtFim;
    private Long tempoVigencia; // Prazo de vigência
    private boolean dependente;
    private boolean paraExcluir = false;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public ServicosAdicionaisTO(){}
    
    public ServicosAdicionaisTO(SolicitacaoComercialFixaTO solicitacaoComercialFixaTO, Date dtInicio, Date dtFim, Long tempoVigencia2, boolean dependente) {
        this();
        this.dependente = dependente;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    public String getDtInicioFormatado() {
        return this.formatDate(this.dtInicio);
    }
    
    public String getDtFimFormatado() {
        return this.formatDate(this.dtFim);
    }    
    
    private String formatDate(Date date) {
        if (date != null) {
            return sdf.format(date);
        } else {
            return "";
        }
    }
    
    /* Getters and Setters */
    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeSolicitacaoComercial() {
        return nomeSolicitacaoComercial;
    }

    public void setNomeSolicitacaoComercial(String nomeSolicitacao) {
        this.nomeSolicitacaoComercial = nomeSolicitacao;
    }

    public Long getTempoVigencia() {
        return tempoVigencia;
    }

    public void setTempoVigencia(Long tempoVigencia) {
        this.tempoVigencia = tempoVigencia;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer tipoServico) {
        this.idTipoServico = tipoServico;
    }

    public Integer getCdTipoServico() {
        return cdTipoServico;
    }

    public void setCdTipoServico(Integer cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFinal) {
        this.dtFim = dtFinal;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicial) {
        this.dtInicio = dtInicial;
    }

    public Integer getIdServicosAdicionais() {
        return idServicosAdicionais;
    }

    public void setIdServicosAdicionais(Integer idServicosAdicionais) {
        this.idServicosAdicionais = idServicosAdicionais;
    }

    public Long getIdSolicitacaoComercial() {
        return idSolicitacaoComercial;
    }

    public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
        this.idSolicitacaoComercial = idSolicitacaoComercial;
    }

    public String getCdSolicitacaoComercial() {
        return cdSolicitacaoComercial;
    }

    public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
        this.cdSolicitacaoComercial = cdSolicitacaoComercial;
    }

    public SolicitacaoComercial getSolicitacaoComercial() {
        return solicitacaoComercial;
    }

    public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
        this.solicitacaoComercial = solicitacaoComercial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public String getPeriodoVigencia() {
        return String.format("%s - %s", this.getDtInicioFormatado(), this.getDtFimFormatado());
    }

    @Override
    public String toString() {
        return String.format(
                "[idServicosAdicionais=%s, idSolicitacao=%s, nomeSolicitacao=%s, cdSolicitacao=%s, idServico=%s, cdServico=%s, nomeServico=%s, idTipoServico=%s"
                        + ", cdTipoServico=%s, dtInicio=%s, dtFim=%s, tempoVigencia=%s]", idServicosAdicionais, idSolicitacaoComercial,
                nomeSolicitacaoComercial, cdSolicitacaoComercial, idServico, cdServico, nomeServico, idTipoServico, cdTipoServico, dtInicio, dtFim,
                tempoVigencia);
    }

    public String getNmTipoServico() {
        return nmTipoServico;
    }

    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }

    public boolean isDependente() {
        return dependente;
    }

    public void setDependente(boolean dependente) {
        this.dependente = dependente;
    }

    public boolean isParaExcluir() {
        return paraExcluir;
    }

    public void setParaExcluir(boolean paraExcluir) {
        this.paraExcluir = paraExcluir;
    }
}
