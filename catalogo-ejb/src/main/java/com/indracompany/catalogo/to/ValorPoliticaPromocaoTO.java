package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ValorPoliticaPromocaoTO implements Serializable {

    private static final long serialVersionUID = -3006441655292680790L;
    
    private Integer idValorPoliticaPromocao;
    private Integer idPromocao;
    private Long idCanalVenda;
    
    public ValorPoliticaPromocaoTO(){}
    
    public ValorPoliticaPromocaoTO(Integer idValorPoliticaPromocao){
        this.idValorPoliticaPromocao = idValorPoliticaPromocao;
    }
    
    public ValorPoliticaPromocaoTO(Integer idPromocao, Long idCanalVenda){
        this.idCanalVenda = idCanalVenda;
        this.idPromocao = idPromocao;
    }
    
    public ValorPoliticaPromocaoTO(Integer idPromocao, Long idCanalVenda, Integer idValorPoliticaPromocao){
        this.idCanalVenda = idCanalVenda;
        this.idPromocao = idPromocao;
        this.idValorPoliticaPromocao = idValorPoliticaPromocao;
    }    
    
    @Override
    public String toString() {
    	return new String((this.idCanalVenda != null ? this.idCanalVenda.toString()+"|" : " null: ") +  
    			(this.idPromocao != null ? this.idPromocao.toString()+"|" : "null: ") +
    			(this.idValorPoliticaPromocao != null ? this.idValorPoliticaPromocao.toString()+"|" : "null: ") 
    	);
    }
    
    public Long getIdCanalVenda() {
        return idCanalVenda;
    }
    public void setIdCanalVenda(Long idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }
    public Integer getIdPromocao() {
        return idPromocao;
    }
    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }
    public Integer getIdValorPoliticaPromocao() {
        return idValorPoliticaPromocao;
    }
    public void setIdValorPoliticaPromocao(Integer idValorPoliticaPromocao) {
        this.idValorPoliticaPromocao = idValorPoliticaPromocao;
    }

}
