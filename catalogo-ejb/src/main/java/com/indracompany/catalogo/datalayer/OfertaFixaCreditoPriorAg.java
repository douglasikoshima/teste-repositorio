package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OFERTAFIXACREDITOPRIORAG", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXACREDITOPRIORAG_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXACREDITOPRIORAG_SQ", allocationSize = 1)
public class OfertaFixaCreditoPriorAg implements Serializable {

	private static final long serialVersionUID = 2605922681585049358L;

	@Id
    @Column(name = "IDOFERTAFIXACREDITOPRIORAG")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXACREDITOPRIORAG_SQ")
    private Integer idOfertafixaCreditoPriorAg;
	
    @Column(name = "CDPRIORIDADE")
    private Integer cdPrioridade;
    
    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXACREDITOSCORE")
    private OfertafixaCreditoScore ofertafixaCreditoScore;    
    
    @ManyToOne
    @JoinColumn(name = "IDEPS")
    private Eps eps;

    
    public OfertaFixaCreditoPriorAg() {
    }

    public OfertaFixaCreditoPriorAg(OfertafixaCreditoScore ofertafixaCreditoScore, Integer cdPrioridade, Eps eps) {
        this.ofertafixaCreditoScore = ofertafixaCreditoScore;
        this.cdPrioridade = cdPrioridade;
        this.eps = eps;
    }
    
	public Integer getCdPrioridade() {
		return cdPrioridade;
	}

	public void setCdPrioridade(Integer cdPrioridade) {
		this.cdPrioridade = cdPrioridade;
	}


	public Integer getIdOfertafixaCreditoPriorAg() {
		return idOfertafixaCreditoPriorAg;
	}

	public void setIdOfertafixaCreditoPriorAg(Integer idOfertafixaCreditoPriorAg) {
		this.idOfertafixaCreditoPriorAg = idOfertafixaCreditoPriorAg;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public OfertafixaCreditoScore getOfertafixaCreditoScore() {
		return ofertafixaCreditoScore;
	}

	public void setOfertafixaCreditoScore(
			OfertafixaCreditoScore ofertafixaCreditoScore) {
		this.ofertafixaCreditoScore = ofertafixaCreditoScore;
	}

	
    
}

