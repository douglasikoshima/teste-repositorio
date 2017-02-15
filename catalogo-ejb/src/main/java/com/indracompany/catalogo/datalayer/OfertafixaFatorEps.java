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
@Table(name="OFERTAFIXAFATOREPS", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXAFATOREPS_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXAFATOREPS_SQ", allocationSize = 1)
public class OfertafixaFatorEps implements Serializable {

    private static final long serialVersionUID = 7525929613753305246L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXAFATOREPS_SQ")
    @Column(name = "IDOFERTAFIXAFATOREPS")
    private Integer idOfertaFixaFatorEps;
    
    @ManyToOne
    @JoinColumn(name = "IDEPS", referencedColumnName = "IDEPS")
    private Eps eps;

    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXA", referencedColumnName = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;

    public OfertafixaFatorEps() {}
    
    public OfertafixaFatorEps(Ofertafixa ofertafixa, Eps eps) {
    	this.eps = eps;
    	this.ofertafixa = ofertafixa;    	
    }
    
    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public Integer getIdOfertaFixaFatorEps() {
        return idOfertaFixaFatorEps;
    }

    public void setIdOfertaFixaFatorEps(Integer idOfertaFixaFatorEps) {
        this.idOfertaFixaFatorEps = idOfertaFixaFatorEps;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }
    
}
