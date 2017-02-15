package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OFERTAFIXA", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Ofertafixa.findByCodigo", query = "select o from Ofertafixa o where o.cdOfertafixa like :cdOfertafixa"),
    @NamedQuery(name = "Ofertafixa.findById", query = "select o from Ofertafixa o where o.idOfertafixa = :idOfertafixa"),
    @NamedQuery(name = "Ofertafixa.findByName", query = "select o from Ofertafixa o where lower(o.dsOfertafixa) like lower(:dsOfertafixa)"),
    @NamedQuery(name = "Ofertafixa.findByCodigoExceptId", query = "select o from Ofertafixa o where o.cdOfertafixa like :cdOfertafixa and o.idOfertafixa <> :idOfertafixa"),
    @NamedQuery(name = "Ofertafixa.findByNameExceptId", query = "select o from Ofertafixa o where lower(o.dsOfertafixa) like lower(:dsOfertafixa) and o.idOfertafixa <> :idOfertafixa")
})
@SequenceGenerator(name = "OFERTAFIXA_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXA_SQ", allocationSize = 1)
public class Ofertafixa implements Serializable {
    
    public enum InStatusConfiguracao {
        Configurado("C"), Desconfigurado("D");
        private String value;
        private InStatusConfiguracao(String value) {
            this.value = value;
        }
    }

    private static final long serialVersionUID = 3764817269917068328L;
    
    public Ofertafixa(){}
    
    public Ofertafixa(Integer idOfertafixa) {
        this.idOfertafixa = idOfertafixa;
    }
    
    public Ofertafixa(Integer idOfertafixa, OfertafixaPolitica ofertafixaPolitica, Date dtCriacao, InStatusConfiguracao inStatusConfiguracao, String nmUsuarioCriacao) {
    	this.idOfertafixa = idOfertafixa;
    	this.cdOfertafixa = idOfertafixa.toString();
        this.ofertafixaPolitica = ofertafixaPolitica;
        this.dtCriacao = dtCriacao;
        this.nmUsuarioCriacao = nmUsuarioCriacao;
        this.inStatusConfiguracao = inStatusConfiguracao.value;
    }
    
    @Id
    @Column(name = "IDOFERTAFIXA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXA_SQ")
    private Integer idOfertafixa;

    @Column(name = "CDOFERTAFIXA")
    private String cdOfertafixa;
    
    @Column(name = "DSOFERTAFIXA")
    private String dsOfertafixa;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTFINAL")
    private Date dtFinal;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTINICIAL")
    private Date dtInicial;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;
    
    @Column(name = "INCONVERGENTECOBRE")
    private String inConvergenteCobre;

    @Column(name = "INCONVERGENTEFIBRA")
    private String inConvergenteFibra;
    
    @Column(name = "INFWT")
    private String inFWT;
    
    @Column(name = "INPORTAB")
    private String inPortab;
    
    @Column(name = "INSPEEDYSOLOCOBRE")
    private String inSpeedySoloCobre;
    
    @Column(name = "INSPEEDYSOLOFIBRA")
    private String inSpeedySoloFibra;
    
    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;
    
    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;
    
    @Column(name = "INSTATUSCONFIGURACAO")
    private String inStatusConfiguracao;
    
    @Column(name = "INFATB")
    private String inFATB;
    
    @Column(name = " INOFERTACLIENTEINADIMPLENTE")
    private String inOfertaClienteInadimplente;
    
    @Column(name = " INBASEPONTUAL")
    private String inBasePontual;
    
    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXAPOLITICA")
    private OfertafixaPolitica ofertafixaPolitica;

    @ManyToOne
    @JoinColumn(name = "IDSOLICITACAOCOMERCIALLINHA")
    private SolicitacaoComercial solicitacaoComercialLinha;

    @ManyToOne
    @JoinColumn(name = "IDSOLICITACAOCOMERCIALPLANO")
    private SolicitacaoComercial solicitacaoComercialPlano;    

    @ManyToOne
    @JoinColumn(name = "IDSOLICITACAOCOMERCIALPRECAD")
    private SolicitacaoComercial solicitacaoComercialPreCad;
    
    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaFatorAreaRegistro> ofertafixaFatorAreaRegistroList;
    
    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaFatorCanalVenda> ofertafixaFatorCanalVendaList;

    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaFatorEps> ofertafixaFatorEpsList;
    
    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaFatorLocalidade> ofertafixaFatorLocalidadeList;
    
    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaComplementar> ofertafixaComplementarList;
    
    @OneToMany(mappedBy="ofertafixa", cascade = CascadeType.REMOVE)
    private List<OfertafixaCreditoScore> ofertafixaCreditoScoreList;

    public String getCdOfertafixa() {
        return cdOfertafixa;
    }

    public void setCdOfertafixa(String cdOfertafixa) {
        this.cdOfertafixa = cdOfertafixa;
    }

    public String getDsOfertafixa() {
        return dsOfertafixa;
    }

    public void setDsOfertafixa(String dsOfertafixa) {
        this.dsOfertafixa = dsOfertafixa;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    public Date getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public Integer getIdOfertafixa() {
        return idOfertafixa;
    }

    public void setIdOfertafixa(Integer idOfertafixa) {
        this.idOfertafixa = idOfertafixa;
    }

    public String getInConvergenteCobre() {
        return inConvergenteCobre;
    }

    public void setInConvergenteCobre(String inConvergenteCobre) {
        this.inConvergenteCobre = inConvergenteCobre;
    }

    public String getInConvergenteFibra() {
        return inConvergenteFibra;
    }

    public void setInConvergenteFibra(String inConvergenteFibra) {
        this.inConvergenteFibra = inConvergenteFibra;
    }

    public String getInFWT() {
        return inFWT;
    }

    public void setInFWT(String inFWT) {
        this.inFWT = inFWT;
    }

    public String getInPortab() {
        return inPortab;
    }

    public void setInPortab(String inPortab) {
        this.inPortab = inPortab;
    }

    public String getInSpeedySoloCobre() {
        return inSpeedySoloCobre;
    }

    public void setInSpeedySoloCobre(String inSpeedySoloCobre) {
        this.inSpeedySoloCobre = inSpeedySoloCobre;
    }

    public String getInSpeedySoloFibra() {
        return inSpeedySoloFibra;
    }

    public void setInSpeedySoloFibra(String inSpeedySoloFibra) {
        this.inSpeedySoloFibra = inSpeedySoloFibra;
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

    public OfertafixaPolitica getOfertafixaPolitica() {
        return ofertafixaPolitica;
    }

    public void setOfertafixaPolitica(OfertafixaPolitica ofertafixaPolitica) {
        this.ofertafixaPolitica = ofertafixaPolitica;
    }

    public SolicitacaoComercial getSolicitacaoComercialLinha() {
        return solicitacaoComercialLinha;
    }

    public void setSolicitacaoComercialLinha(
            SolicitacaoComercial solicitacaoComercialLinha) {
        this.solicitacaoComercialLinha = solicitacaoComercialLinha;
    }

    public SolicitacaoComercial getSolicitacaoComercialPlano() {
        return solicitacaoComercialPlano;
    }

    public void setSolicitacaoComercialPlano(
            SolicitacaoComercial solicitacaoComercialPlano) {
        this.solicitacaoComercialPlano = solicitacaoComercialPlano;
    }

    public SolicitacaoComercial getSolicitacaoComercialPreCad() {
        return solicitacaoComercialPreCad;
    }

    public void setSolicitacaoComercialPreCad(
            SolicitacaoComercial solicitacaoComercialPreCad) {
        this.solicitacaoComercialPreCad = solicitacaoComercialPreCad;
    }

    public List<OfertafixaFatorAreaRegistro> getOfertafixaFatorAreaRegistroList() {
        return ofertafixaFatorAreaRegistroList;
    }

    public void setOfertafixaFatorAreaRegistroList(
            List<OfertafixaFatorAreaRegistro> ofertafixaFatorAreaRegistroList) {
        this.ofertafixaFatorAreaRegistroList = ofertafixaFatorAreaRegistroList;
    }

    public List<OfertafixaFatorCanalVenda> getOfertafixaFatorCanalVendaList() {
        return ofertafixaFatorCanalVendaList;
    }

    public void setOfertafixaFatorCanalVendaList(
            List<OfertafixaFatorCanalVenda> ofertafixaFatorCanalVendaList) {
        this.ofertafixaFatorCanalVendaList = ofertafixaFatorCanalVendaList;
    }

    public List<OfertafixaFatorLocalidade> getOfertafixaFatorLocalidadeList() {
        return ofertafixaFatorLocalidadeList;
    }

    public void setOfertafixaFatorLocalidadeList(
            List<OfertafixaFatorLocalidade> ofertafixaFatorLocalidadeList) {
        this.ofertafixaFatorLocalidadeList = ofertafixaFatorLocalidadeList;
    }

    public List<OfertafixaFatorEps> getOfertafixaFatorEpsList() {
        return ofertafixaFatorEpsList;
    }

    public void setOfertafixaFatorEpsList(
            List<OfertafixaFatorEps> ofertafixaFatorEpsList) {
        this.ofertafixaFatorEpsList = ofertafixaFatorEpsList;
    }

    public List<OfertafixaComplementar> getOfertafixaComplementarList() {
        return ofertafixaComplementarList;
    }

    public void setOfertafixaComplementarList(List<OfertafixaComplementar> ofertafixaComplementarList) {
        this.ofertafixaComplementarList = ofertafixaComplementarList;
    }

    public String getInStatusConfiguracao() {
        return inStatusConfiguracao;
    }

    public void setInStatusConfiguracao(String inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao;
    }
    
    public void setInStatusConfiguracao(InStatusConfiguracao inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao.value;
    }

    public List<OfertafixaCreditoScore> getOfertafixaCreditoScoreList() {
        return ofertafixaCreditoScoreList;
    }

    public void setOfertafixaCreditoScoreList(List<OfertafixaCreditoScore> ofertafixaCreditoScoreList) {
        this.ofertafixaCreditoScoreList = ofertafixaCreditoScoreList;
    }

	public String getInFATB() {
		return inFATB;
	}

	public void setInFATB(String inFATB) {
		this.inFATB = inFATB;
	}

	public String getInOfertaClienteInadimplente() {
		return inOfertaClienteInadimplente;
	}

	public void setInOfertaClienteInadimplente(String inOfertaClienteInadimplente) {
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}

	public String getInBasePontual() {
		return inBasePontual;
	}

	public void setInBasePontual(String inBasePontual) {
		this.inBasePontual = inBasePontual;
	}
}
