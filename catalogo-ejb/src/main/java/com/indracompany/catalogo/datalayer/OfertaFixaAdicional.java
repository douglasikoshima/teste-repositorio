package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries( { @NamedQuery(name = "OfertaFixaAdicional.search", query = "from OfertaFixaAdicional ofa where ofa.dtFim >= trunc(sysdate) or ofa.dtFim is null") })
@Entity
@SequenceGenerator(name = "OFERTAFIXAADICIONAL_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXAADICIONAL_SQ", allocationSize = 1)
@Table(name = "OFERTAFIXAADICIONAL", schema = "CATALOGOPRS_OW")
public class OfertaFixaAdicional implements Serializable {
    private static final long serialVersionUID = 1L;

    /* Construtor default */
    public OfertaFixaAdicional() {
    }

    /* Construtor para gravar */
    public OfertaFixaAdicional(Long idSolicitacaoComercial, Date dtInicio, Date dtFim, Long tempoVigencia) {
        this.solicitacaoComercial = new SolicitacaoComercial(idSolicitacaoComercial);
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.tempoVigencia = tempoVigencia;
    }

    /* Construtor para excluir */
    public OfertaFixaAdicional(Integer idServicosAdicionais, Long idSolicitacaoComercial, Date dtInicio, Date dtFim, Long tempoVigencia) {
        this.idOfertaFixaAdicional = idServicosAdicionais;
        this.solicitacaoComercial = new SolicitacaoComercial(idSolicitacaoComercial);
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.tempoVigencia = tempoVigencia;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXAADICIONAL_SQ")
    @Column(name = "IDOFERTAFIXAADICIONAL")
    private Integer idOfertaFixaAdicional;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTINICIAL", nullable = false)
    private Date dtInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTFINAL")
    private Date dtFim;

    @ManyToOne
    @JoinColumn(name = "IDSOLICITACAOCOMERCIAL")
    private SolicitacaoComercial solicitacaoComercial;

    @Column(name = "PZMAXIMOVIGENCIA")
    private Long tempoVigencia;

    // Getters and Setters
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

    public Integer getIdOfertaFixaAdicional() {
        return idOfertaFixaAdicional;
    }

    public void setIdOfertaFixaAdicional(Integer idOfertaFixaAdicional) {
        this.idOfertaFixaAdicional = idOfertaFixaAdicional;
    }

    public SolicitacaoComercial getSolicitacaoComercial() {
        return solicitacaoComercial;
    }

    public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
        this.solicitacaoComercial = solicitacaoComercial;
    }

    public Long getTempoVigencia() {
        return tempoVigencia;
    }

    public void setTempoVigencia(Long tempoVigencia) {
        this.tempoVigencia = tempoVigencia;
    }

    @Override
    public String toString() {
        return String.format("[idOfertaFixaAdicional=%s, dtInicio=%s, dtFim=%s, solicitacaoComercial=%s, tempoVigencia=%s]", idOfertaFixaAdicional,
                dtInicio, dtFim, solicitacaoComercial, tempoVigencia);
    }
}
