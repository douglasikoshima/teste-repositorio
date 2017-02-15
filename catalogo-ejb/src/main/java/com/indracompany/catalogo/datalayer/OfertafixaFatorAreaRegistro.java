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
@Table(name="OFERTAFIXAFATORAREAREGISTRO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXAFATORAREAREGISTRO_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXAFATORAREAREGISTRO_SQ", allocationSize = 1)
public class OfertafixaFatorAreaRegistro implements Serializable {

    private static final long serialVersionUID = -3121910094585934007L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXAFATORAREAREGISTRO_SQ")
    @Column(name = "IDOFERTAFIXAFATORAREAREGISTRO")
    private Integer idOfertaFixaFatorAreaRegistro;
    
    @ManyToOne
    @JoinColumn(name = "IDAREAREGISTRO", referencedColumnName = "IDAREAREGISTRO")
    private AreaRegistro areaRegistro;
    
    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXA", referencedColumnName = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;

    public OfertafixaFatorAreaRegistro(Ofertafixa ofertafixa, AreaRegistro areaRegistro) {
        this.ofertafixa = ofertafixa;
        this.areaRegistro = areaRegistro;
    }
    
    public OfertafixaFatorAreaRegistro() {
    }

    public AreaRegistro getAreaRegistro() {
        return areaRegistro;
    }

    public void setAreaRegistro(AreaRegistro areaRegistro) {
        this.areaRegistro = areaRegistro;
    }

    public Integer getIdOfertaFixaFatorAreaRegistro() {
        return idOfertaFixaFatorAreaRegistro;
    }

    public void setIdOfertaFixaFatorAreaRegistro(
            Integer idOfertaFixaFatorAreaRegistro) {
        this.idOfertaFixaFatorAreaRegistro = idOfertaFixaFatorAreaRegistro;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }
}