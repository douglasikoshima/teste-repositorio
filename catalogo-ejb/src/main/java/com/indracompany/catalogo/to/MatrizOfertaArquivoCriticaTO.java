package com.indracompany.catalogo.to;

import java.io.Serializable;

public class MatrizOfertaArquivoCriticaTO implements Serializable {

    private static final long serialVersionUID = 3006562018049330963L;
    
    private Integer idMatrizOfertaArquivoCritica;
    private String dscCriticaImportacao;
    
    public String getDscCriticaImportacao() {
        return dscCriticaImportacao;
    }
    public void setDscCriticaImportacao(String dscCriticaImportacao) {
        this.dscCriticaImportacao = dscCriticaImportacao;
    }
    public Integer getIdMatrizOfertaArquivoCritica() {
        return idMatrizOfertaArquivoCritica;
    }
    public void setIdMatrizOfertaArquivoCritica(Integer idMatrizOfertaArquivoCritica) {
        this.idMatrizOfertaArquivoCritica = idMatrizOfertaArquivoCritica;
    }

}
