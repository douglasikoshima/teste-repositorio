package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TipoRelacionamentoTO implements Serializable {

    private static final long serialVersionUID = -5204673361209940880L;

    private Integer idTipoRelacionamento;
    private String dscTipoRelacionamento;
    private String sgTipoRelacionamento;
    
    public TipoRelacionamentoTO(){}
    
    public TipoRelacionamentoTO(String sgTipoRelacionamento){
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }
    
    public String getDscTipoRelacionamento() {
        return dscTipoRelacionamento;
    }
    
    public void setDscTipoRelacionamento(String dscTipoRelacionamento) {
        this.dscTipoRelacionamento = dscTipoRelacionamento;
    }
    
    public Integer getIdTipoRelacionamento() {
        return idTipoRelacionamento;
    }
    
    public void setIdTipoRelacionamento(Integer idTipoRelacionamento) {
        this.idTipoRelacionamento = idTipoRelacionamento;
    }
    
    public String getSgTipoRelacionamento() {
        return sgTipoRelacionamento;
    }
    
    public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !this.getClass().isInstance( o )) {
            return false;
        }
        TipoRelacionamentoTO tipoRelacionamento = ((TipoRelacionamentoTO) o);
        if (tipoRelacionamento.getSgTipoRelacionamento() == null) {
            return false;
        }
        return tipoRelacionamento.getSgTipoRelacionamento().equals(this.getSgTipoRelacionamento());
    }

    public enum Sigla {
        OA,CV,PQ,DE,DA,IN,AG,IX,CL,DC
    }
}
