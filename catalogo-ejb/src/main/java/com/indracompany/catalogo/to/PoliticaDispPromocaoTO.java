package com.indracompany.catalogo.to;

import java.io.Serializable;

public class PoliticaDispPromocaoTO implements Serializable {

    private static final long serialVersionUID = -4133394446339338802L;
    
    private Integer idPoliticaPromocao;
    private String inCanalVenda;
    
    public Integer getIdPoliticaPromocao() {
        return idPoliticaPromocao;
    }
    
    public void setIdPoliticaPromocao(Integer idPoliticaPromocao) {
        this.idPoliticaPromocao = idPoliticaPromocao;
    }
    
    public String getInCanalVenda() {
        return inCanalVenda;
    }
    
    public void setInCanalVenda(String inCanalVenda) {
        this.inCanalVenda = inCanalVenda;
    }
    
    @Override
    public String toString(){
        return String.format("[idPoliticaPromocao=%s, inCanalVenda=%s]", idPoliticaPromocao, inCanalVenda);
    }
}
