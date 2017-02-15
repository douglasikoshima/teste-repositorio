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
@Table(name="OFERTAFIXAFATORCANALVENDA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTAFIXAFATORCANALVENDA_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXAFATORCANALVENDA_SQ", allocationSize = 1)
public class OfertafixaFatorCanalVenda implements Serializable {

    private static final long serialVersionUID = 5659736235306260515L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXAFATORCANALVENDA_SQ")
    @Column(name = "IDOFERTAFIXAFATORCANALVENDA")
    private Integer idOfertafixaFatorCanalVenda;
    
    @ManyToOne
    @JoinColumn(name = "IDCANALVENDA", referencedColumnName = "IDCANALVENDA")
    private CanalVenda canalVenda;
    
    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXA", referencedColumnName = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;

    public OfertafixaFatorCanalVenda() {
    }    
    
    public OfertafixaFatorCanalVenda(Ofertafixa ofertafixa, CanalVenda canalVenda) {
        this.canalVenda = canalVenda;
        this.ofertafixa = ofertafixa;
    }

    public CanalVenda getCanalVenda() {
        return canalVenda;
    }

    public void setCanalVenda(CanalVenda canalVenda) {
        this.canalVenda = canalVenda;
    }

    public Integer getIdOfertafixaFatorCanalVenda() {
        return idOfertafixaFatorCanalVenda;
    }

    public void setIdOfertafixaFatorCanalVenda(Integer idOfertafixaFatorCanalVenda) {
        this.idOfertafixaFatorCanalVenda = idOfertafixaFatorCanalVenda;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }
    
}
