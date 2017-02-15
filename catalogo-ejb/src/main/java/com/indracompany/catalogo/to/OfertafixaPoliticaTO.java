package com.indracompany.catalogo.to;

import java.io.Serializable;

public class OfertafixaPoliticaTO implements Serializable {

    private static final long serialVersionUID = 3630814377915817125L;
    
    private Integer idOfertafixaPolitica;
    private String inAreaRegistro;
    private String inCanalVenda;
    private String inEPS;
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

}
