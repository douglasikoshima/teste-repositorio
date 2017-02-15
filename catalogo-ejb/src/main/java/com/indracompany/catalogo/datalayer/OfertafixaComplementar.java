package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OFERTAFIXACOMPLEMENTAR", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "OfertafixaComplementar.findByIdOfertaIdSolicitacaoComercial", query = "select oc from OfertafixaComplementar oc where oc.ofertafixa.idOfertafixa = :idOfertafixa and oc.solicitacaoComercial.idSolicitacaoComercial = :idSolicitacaoComercial")
})
@SequenceGenerator(name = "OFERTAFIXACOMPLEMENTAR_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAFIXACOMPLEMENTAR_SQ", allocationSize = 1)
public class OfertafixaComplementar implements Serializable {

    private static final long serialVersionUID = -6112662560086991846L;
    
    @Id
    @Column(name = "IDOFERTAFIXACOMPLEMENTAR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAFIXACOMPLEMENTAR_SQ")    
    private Integer idOfertafixaComplementar;
    
    @ManyToOne
    @JoinColumn(name = "IDSOLICITACAOCOMERCIAL", referencedColumnName = "IDSOLICITACAOCOMERCIAL")
    private SolicitacaoComercial solicitacaoComercial;

    @ManyToOne
    @JoinColumn(name = "IDOFERTAFIXA", referencedColumnName = "IDOFERTAFIXA")
    private Ofertafixa ofertafixa;
    
    private Integer pzMaximoVigencia;

    public Integer getIdOfertafixaComplementar() {
        return idOfertafixaComplementar;
    }

    public Ofertafixa getOfertafixa() {
        return ofertafixa;
    }

    public SolicitacaoComercial getSolicitacaoComercial() {
        return solicitacaoComercial;
    }

    public void setIdOfertafixaComplementar(Integer idOfertafixaComplementar) {
        this.idOfertafixaComplementar = idOfertafixaComplementar;
    }

    public void setOfertafixa(Ofertafixa ofertafixa) {
        this.ofertafixa = ofertafixa;
    }

    public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
        this.solicitacaoComercial = solicitacaoComercial;
    }

    public Integer getPzMaximoVigencia() {
        return pzMaximoVigencia;
    }

    public void setPzMaximoVigencia(Integer pzMaximoVigencia) {
        this.pzMaximoVigencia = pzMaximoVigencia;
    }

}
