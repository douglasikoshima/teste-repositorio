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
@Table(name="OFERTAFIXAFATORLOCALIDADE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXAFATORLOCALIDADE_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXAFATORLOCALIDADE_SQ", allocationSize = 1)
public class OfertafixaFatorLocalidade implements Serializable {

    private static final long serialVersionUID = 2664193800825303377L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXAFATORLOCALIDADE_SQ")
    @Column(name = "IDOFERTAFIXAFATORLOCALIDADE")
    private Integer idOfertafixaFatorLocalidade;
    
    @ManyToOne
    @JoinColumn(name = "IDLOCALIDADE", referencedColumnName = "IDLOCALIDADE")
    private Localidade localidade;
    
    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXA", referencedColumnName = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;

    public OfertafixaFatorLocalidade() {}
    
    public OfertafixaFatorLocalidade(Ofertafixa ofertafixa, Localidade localidade) {
        this.ofertafixa = ofertafixa;
        this.localidade = localidade;
    }

    public Integer getIdOfertafixaFatorLocalidade() {
        return idOfertafixaFatorLocalidade;
    }

    public void setIdOfertafixaFatorLocalidade(Integer idOfertafixaFatorLocalidade) {
        this.idOfertafixaFatorLocalidade = idOfertafixaFatorLocalidade;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }
    
}
