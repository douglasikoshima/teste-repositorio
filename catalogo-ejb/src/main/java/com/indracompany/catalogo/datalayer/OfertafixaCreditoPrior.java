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
@Table(name = "OFERTAFIXACREDITOPRIOR", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXACREDITOPRIOR_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXACREDITOPRIOR_SQ", allocationSize = 1)
public class OfertafixaCreditoPrior implements Serializable {

    private static final long serialVersionUID = 797192217639060548L;

    @Id
    @Column(name = "IDOFERTAFIXACREDITOPRIOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXACREDITOPRIOR_SQ")
    private Integer idOfertafixaCreditoPrior;

    @Column(name = "CDPRIORIDADE")
    private Integer cdPrioridade;

    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXACREDITOSCORE")
    private OfertafixaCreditoScore ofertafixaCreditoScore;
    
	@ManyToOne
	@JoinColumn(name = "IDCANALVENDA")
	private CanalVenda canalVenda;    

    public OfertafixaCreditoPrior() {
    }

    public OfertafixaCreditoPrior(OfertafixaCreditoScore ofertafixaCreditoScore, Integer cdPrioridade, CanalVenda canalVenda) {
        this.ofertafixaCreditoScore = ofertafixaCreditoScore;
        this.cdPrioridade = cdPrioridade;
        this.canalVenda = canalVenda;
    }

    public Integer getCdPrioridade() {
        return cdPrioridade;
    }

    public Integer getIdOfertafixaCreditoPrior() {
        return idOfertafixaCreditoPrior;
    }

    public OfertafixaCreditoScore getOfertafixaCreditoScore() {
        return ofertafixaCreditoScore;
    }

    public void setCdPrioridade(Integer cdPrioridade) {
        this.cdPrioridade = cdPrioridade;
    }

    public void setIdOfertafixaCreditoPrior(Integer idOfertafixaCreditoPrior) {
        this.idOfertafixaCreditoPrior = idOfertafixaCreditoPrior;
    }

    public void setOfertafixaCreditoScore(OfertafixaCreditoScore ofertafixaCreditoScore) {
        this.ofertafixaCreditoScore = ofertafixaCreditoScore;
    }

	public CanalVenda getCanalVenda() {
		return canalVenda;
	}

	public void setCanalVenda(CanalVenda canalVenda) {
		this.canalVenda = canalVenda;
	}
}
