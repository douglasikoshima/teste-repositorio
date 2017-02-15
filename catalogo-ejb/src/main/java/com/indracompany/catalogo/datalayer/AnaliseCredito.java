package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 */
@Entity
@NamedQueries( { @NamedQuery(name = "AnaliseCredito.findAll", query = "SELECT ac FROM AnaliseCredito ac order by ac.idAnaliseCredito") })
@Table(name = "ANALISECREDITO", schema = "CATALOGOPRS_OW")
public class AnaliseCredito implements Serializable {
    private static final long serialVersionUID = 1L;

    public AnaliseCredito() {
    }

    public AnaliseCredito(Integer idAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
    }

    @Id
    @Column(name = "IDANALISECREDITO")
    private Integer idAnaliseCredito;

    @Column(name = "CDCOR")
    private String cdCor;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "NMANALISECREDITO")
    private String nmAnaliseCredito;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

    // bi-directional many-to-one association to Ofservicoconfiguracaoscore
    @OneToMany(mappedBy = "analiseCredito")
    private List<OfServicoConfiguracaoScore> ofservicoconfiguracaoscoreList;

    // bi-directional many-to-one association to Planoanalisecredito
    @OneToMany(mappedBy = "analiseCredito")
    private List<PlanoAnaliseCredito> planoAnaliseCreditoList;

    // bi-directional many-to-one association to Planoconfiguracaoscore
    @OneToMany(mappedBy = "analiseCredito")
    private List<PlanoConfiguracaoScore> planoconfiguracaoscoreList;

    // bi-directional many-to-one association to Servicoconfiguracaoscore
    @OneToMany(mappedBy = "analiseCredito")
    private List<ServicoConfiguracaoScore> servicoconfiguracaoscoreList;

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

    public List<OfServicoConfiguracaoScore> getOfservicoconfiguracaoscoreList() {
        return ofservicoconfiguracaoscoreList;
    }

    public void setOfservicoconfiguracaoscoreList(List<OfServicoConfiguracaoScore> ofservicoconfiguracaoscoreList) {
        this.ofservicoconfiguracaoscoreList = ofservicoconfiguracaoscoreList;
    }

    public List<PlanoAnaliseCredito> getPlanoAnaliseCreditoList() {
        return planoAnaliseCreditoList;
    }

    public void setPlanoAnaliseCreditoList(List<PlanoAnaliseCredito> planoAnaliseCreditoList) {
        this.planoAnaliseCreditoList = planoAnaliseCreditoList;
    }

    public List<PlanoConfiguracaoScore> getPlanoconfiguracaoscoreList() {
        return planoconfiguracaoscoreList;
    }

    public void setPlanoconfiguracaoscoreList(List<PlanoConfiguracaoScore> planoconfiguracaoscoreList) {
        this.planoconfiguracaoscoreList = planoconfiguracaoscoreList;
    }

    public List<ServicoConfiguracaoScore> getServicoconfiguracaoscoreList() {
        return servicoconfiguracaoscoreList;
    }

    public void setServicoconfiguracaoscoreList(List<ServicoConfiguracaoScore> servicoconfiguracaoscoreList) {
        this.servicoconfiguracaoscoreList = servicoconfiguracaoscoreList;
    }
}