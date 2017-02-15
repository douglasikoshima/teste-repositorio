package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
@NamedQuery(name = "OfertafixaPolitica.findId", query = " SELECT ofp FROM OfertafixaPolitica ofp WHERE ofp.inAreaRegistro = :inAreaRegistro AND ofp.inCanalVenda = :inCanalVenda AND ofp.inEPS = :inEPS AND ofp.inLocalidade = :inLocalidade "),
@NamedQuery(name = "OfertafixaPolitica.findById", query = " SELECT ofp FROM OfertafixaPolitica ofp WHERE ofp.idOfertafixaPolitica = :idOfertafixaPolitica ")
})
@Table(name="OFERTAFIXAPOLITICA", schema = "CATALOGOPRS_OW")
public class OfertafixaPolitica implements Serializable {

    private static final long serialVersionUID = -8841904894835390274L;
    
    public OfertafixaPolitica(){
    }
    
    public OfertafixaPolitica(Integer idOfertafixaPolitica) {
        this.idOfertafixaPolitica = idOfertafixaPolitica;
    }
    
    @Id
    @Column(name = "IDOFERTAFIXAPOLITICA")
    private Integer idOfertafixaPolitica;
    
    @Column(name = "INAREAREGISTRO")
    private String inAreaRegistro;
    
    @Column(name = "INCANALVENDA")
    private String inCanalVenda;

    @Column(name = "INEPS")
    private String inEPS;
    
    @Column(name = "INLOCALIDADE")
    private String inLocalidade;
    
    public Integer getIdOfertafixaPolitica() {
        return idOfertafixaPolitica;
    }
    
    public void setIdOfertafixaPolitica(Integer idOfertafixaPolitica) {
        this.idOfertafixaPolitica = idOfertafixaPolitica;
    }
    
    public String getInAreaRegistro() {
        return inAreaRegistro;
    }
    
    public void setInAreaRegistro(String inAreaRegistro) {
        this.inAreaRegistro = inAreaRegistro;
    }
    
    public String getInCanalVenda() {
        return inCanalVenda;
    }
    
    public void setInCanalVenda(String inCanalVenda) {
        this.inCanalVenda = inCanalVenda;
    }
    
    public String getInEPS() {
        return inEPS;
    }
    
    public void setInEPS(String inEPS) {
        this.inEPS = inEPS;
    }
    
    public String getInLocalidade() {
        return inLocalidade;
    }
    
    public void setInLocalidade(String inLocalidade) {
        this.inLocalidade = inLocalidade;
    }
    
    @Override
    public String toString() {
        return String.format("idOfertafixaPolitica=%s inAreaRegistro=%s inCanalVenda=%s inEPS=%s inLocalidade=%s", idOfertafixaPolitica, inAreaRegistro, inCanalVenda, inEPS, inLocalidade);
    }
}
