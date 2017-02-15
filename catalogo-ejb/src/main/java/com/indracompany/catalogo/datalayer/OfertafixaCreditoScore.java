package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="OFERTAFIXACREDITOSCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXACREDITOSCORE_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXACREDITOSCORE_SQ", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "OfertafixaCreditoScore.findByIdAnaliseCredito", query = "select ocs from OfertafixaCreditoScore ocs " +
    		"left join ocs.ofertaFixaCreditoPriorAg ofcpa left join ocs.ofertafixaCreditoPrior ofcp left join ofcpa.eps ep where ocs.analiseCredito.idAnaliseCredito = :idAnaliseCredito"),
    @NamedQuery(name = "OfertafixaCreditoScore.findByIdAnaliseCreditoRemove", query = "select ocs from OfertafixaCreditoScore ocs " +
    		"inner join ocs.ofertaFixaCreditoPriorAg ofcpa inner join ocs.ofertafixaCreditoPrior ofcp inner join ofcpa.eps ep where ocs.analiseCredito.idAnaliseCredito = :idAnaliseCredito and ep.idEps = :idEps"),
    @NamedQuery(name = "OfertafixaCreditoScore.findOfertafixaByOfertafixaCreditoScore", query = "select sc from OfertafixaCreditoScore sc where sc.idOfertafixaCreditoScore = :idofertafixacreditoscore")
})
public class OfertafixaCreditoScore implements Serializable {

    private static final long serialVersionUID = -5985812041021466481L;
    
    public OfertafixaCreditoScore(){}
    
    
    
    
    @Id
    @Column(name = "IDOFERTAFIXACREDITOSCORE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXACREDITOSCORE_SQ")
    private Integer idOfertafixaCreditoScore;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;
    
    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDANALISECREDITO")
    private AnaliseCredito analiseCredito;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;
    
	@OneToMany(mappedBy="ofertafixaCreditoScore", cascade = CascadeType.REMOVE)
	@JoinColumn(name = "IDOFERTAFIXACREDITOSCORE")
	private List<OfertafixaCreditoPrior> ofertafixaCreditoPrior;    
    
    @OneToMany(mappedBy="ofertafixaCreditoScore", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "IDOFERTAFIXACREDITOSCORE")
    private List<OfertaFixaCreditoPriorAg> ofertaFixaCreditoPriorAg;
    
    public OfertafixaCreditoScore(Integer idOferta, Integer idAnaliseCredito) {
        this.ofertafixa = new Ofertafixa(idOferta);
        this.analiseCredito = new AnaliseCredito(idAnaliseCredito);
    }

    public AnaliseCredito getAnaliseCredito() {
        return analiseCredito;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public Integer getIdOfertafixaCreditoScore() {
        return idOfertafixaCreditoScore;
    }

    public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public void setAnaliseCredito(AnaliseCredito analiseCredito) {
        this.analiseCredito = analiseCredito;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public void setIdOfertafixaCreditoScore(Integer idOfertafixaCreditoScore) {
        this.idOfertafixaCreditoScore = idOfertafixaCreditoScore;
    }

    public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }

    public List<OfertafixaCreditoPrior> getOfertafixaCreditoPrior() {
        return ofertafixaCreditoPrior;
    }

    public void setOfertafixaCreditoPrior(List<OfertafixaCreditoPrior> ofertafixaCreditoPrior) {
        this.ofertafixaCreditoPrior = ofertafixaCreditoPrior;
    }

	public List<OfertaFixaCreditoPriorAg> getOfertaFixaCreditoPriorAg() {
		return ofertaFixaCreditoPriorAg;
	}

	public void setOfertaFixaCreditoPriorAg(List<OfertaFixaCreditoPriorAg> ofertaFixaCreditoPriorAg) {
		this.ofertaFixaCreditoPriorAg = ofertaFixaCreditoPriorAg;
	}

	public OfertafixaCreditoScore(Integer idOfertafixaCreditoScore, Date dtCriacao, String nmUsuarioCriacao, AnaliseCredito analiseCredito, Ofertafixa ofertafixa, OfertafixaCreditoPrior ofertafixaCreditoPrior, List<OfertaFixaCreditoPriorAg> ofertaFixaCreditoPriorAg) {
        super();
        this.idOfertafixaCreditoScore = idOfertafixaCreditoScore;
        this.dtCriacao = dtCriacao;
        this.nmUsuarioCriacao = nmUsuarioCriacao;
        this.analiseCredito = analiseCredito;
        this.ofertafixa = ofertafixa;
        this.ofertaFixaCreditoPriorAg = ofertaFixaCreditoPriorAg;
    }
    
    public OfertafixaCreditoScore(Integer idOfertafixaCreditoScore, Date dtCriacao, String nmUsuarioCriacao, AnaliseCredito analiseCredito, Ofertafixa ofertafixa, List<OfertafixaCreditoPrior> ofertafixaCreditoPrior) {
        super();
        this.idOfertafixaCreditoScore = idOfertafixaCreditoScore;
        this.dtCriacao = dtCriacao;
        this.nmUsuarioCriacao = nmUsuarioCriacao;
        this.analiseCredito = analiseCredito;
        this.ofertafixa = ofertafixa;
        this.ofertafixaCreditoPrior = ofertafixaCreditoPrior;
    }
    public String toString(){
        return idOfertafixaCreditoScore.toString();
    }
}
