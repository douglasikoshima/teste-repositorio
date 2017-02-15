package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 * 
 */
@Entity
@Table(name = "OFERTASERVICO", schema = "CATALOGOPRS_OW")
public class OfertaServico implements Serializable {
    private static final long serialVersionUID = 1L;

    public OfertaServico() {
    }

    public OfertaServico(Integer idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }

    @Id
    @Column(name = "IDOFERTASERVICO")
    private Integer idOfertaServico;

    @Column(name = "CDOFERTASERVICO")
    private String cdOfertaServico;

    @Column(name = "DSNOTA")
    private String dsNota;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "NMOFERTASERVICO")
    private String nmOfertaServico;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

    // bi-directional many-to-one association to Ofertasapplanooferta
    @OneToMany(mappedBy = "ofertaServico")
    private List<OfertaSAPPlanoOferta> ofertaSAPPlanoOfertaList;

    // bi-directional many-to-one association to Servicoofertaservico
    @OneToMany(mappedBy = "ofertaServico")
    private List<ServicoOfertaServico> servicoOfertaServicoList;

    // bi-directional many-to-one association to Servicoofertaservico
    @OneToOne(mappedBy = "ofertaServico")
    private OfertaServicoCategoriaScore ofertaServicoCategoriaScore;

    public String getCdOfertaServico() {
        return cdOfertaServico;
    }

    public void setCdOfertaServico(String cdOfertaServico) {
        this.cdOfertaServico = cdOfertaServico;
    }

    public String getDsNota() {
        return dsNota;
    }

    public void setDsNota(String dsNota) {
        this.dsNota = dsNota;
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

    public Integer getIdOfertaServico() {
        return idOfertaServico;
    }

    public void setIdOfertaServico(Integer idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }

    public String getNmOfertaServico() {
        return nmOfertaServico;
    }

    public void setNmOfertaServico(String nmOfertaServico) {
        this.nmOfertaServico = nmOfertaServico;
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

    public List<OfertaSAPPlanoOferta> getOfertaSAPPlanoOfertaList() {
        return ofertaSAPPlanoOfertaList;
    }

    public void setOfertaSAPPlanoOfertaList(List<OfertaSAPPlanoOferta> ofertaSAPPlanoOfertaList) {
        this.ofertaSAPPlanoOfertaList = ofertaSAPPlanoOfertaList;
    }

    public List<ServicoOfertaServico> getServicoOfertaServicoList() {
        return servicoOfertaServicoList;
    }

    public void setServicoOfertaServicoList(List<ServicoOfertaServico> servicoOfertaServicoList) {
        this.servicoOfertaServicoList = servicoOfertaServicoList;
    }

    public OfertaServicoCategoriaScore getOfertaServicoCategoriaScore() {
        return ofertaServicoCategoriaScore;
    }

    public void setOfertaServicoCategoriaScore(OfertaServicoCategoriaScore ofertaServicoCategoriaScore) {
        this.ofertaServicoCategoriaScore = ofertaServicoCategoriaScore;
    }
}