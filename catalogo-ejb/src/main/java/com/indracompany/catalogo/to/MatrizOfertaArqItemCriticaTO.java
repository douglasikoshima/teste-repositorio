package com.indracompany.catalogo.to;

import java.io.Serializable;

public class MatrizOfertaArqItemCriticaTO implements Serializable {

    private static final long serialVersionUID = 1881925119177727111L;
    
    private Integer idMatrizOfertaArqItemCritica;
    private MatrizOfertaArquivoItemTO matrizOfertaArquivoItemTO;
    private MatrizOfertaArquivoCriticaTO matrizOfertaArquivoCriticaTO;
    
    public Integer getIdMatrizOfertaArqItemCritica() {
        return idMatrizOfertaArqItemCritica;
    }
    public void setIdMatrizOfertaArqItemCritica(Integer idMatrizOfertaArqItemCritica) {
        this.idMatrizOfertaArqItemCritica = idMatrizOfertaArqItemCritica;
    }
    public MatrizOfertaArquivoCriticaTO getMatrizOfertaArquivoCriticaTO() {
        return matrizOfertaArquivoCriticaTO;
    }
    public void setMatrizOfertaArquivoCriticaTO(
            MatrizOfertaArquivoCriticaTO matrizOfertaArquivoCriticaTO) {
        this.matrizOfertaArquivoCriticaTO = matrizOfertaArquivoCriticaTO;
    }
    public MatrizOfertaArquivoItemTO getMatrizOfertaArquivoItemTO() {
        return matrizOfertaArquivoItemTO;
    }
    public void setMatrizOfertaArquivoItemTO(
            MatrizOfertaArquivoItemTO matrizOfertaArquivoItemTO) {
        this.matrizOfertaArquivoItemTO = matrizOfertaArquivoItemTO;
    }
    
    
    

}
