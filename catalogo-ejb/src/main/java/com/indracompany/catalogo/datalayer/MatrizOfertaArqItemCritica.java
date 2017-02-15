package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MATRIZOFERTAARQITEMCRITICA", schema = "CATALOGOPRS_OW")
public class MatrizOfertaArqItemCritica implements Serializable {

    private static final long serialVersionUID = 1130674586753655438L;
    
    @Id
    @Column(name = "IDMATRIZOFERTAARQITEMCRITICA")
    private Integer idMatrizOfertaArqItemCritica;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMATRIZOFERTAARQUIVOITEM")
    private MatrizOfertaArquivoItem matrizOfertaArquivoItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMATRIZOFERTAARQUIVOCRITICA")
    private MatrizOfertaArquivoCritica matrizOfertaArquivoCritica;

    public Integer getIdMatrizOfertaArqItemCritica() {
        return idMatrizOfertaArqItemCritica;
    }
    public void setIdMatrizOfertaArqItemCritica(Integer idMatrizOfertaArqItemCritica) {
        this.idMatrizOfertaArqItemCritica = idMatrizOfertaArqItemCritica;
    }
    public MatrizOfertaArquivoCritica getMatrizOfertaArquivoCritica() {
        return matrizOfertaArquivoCritica;
    }
    public void setMatrizOfertaArquivoCritica(
            MatrizOfertaArquivoCritica matrizOfertaArquivoCritica) {
        this.matrizOfertaArquivoCritica = matrizOfertaArquivoCritica;
    }
    public MatrizOfertaArquivoItem getMatrizOfertaArquivoItem() {
        return matrizOfertaArquivoItem;
    }
    public void setMatrizOfertaArquivoItem(
            MatrizOfertaArquivoItem matrizOfertaArquivoItem) {
        this.matrizOfertaArquivoItem = matrizOfertaArquivoItem;
    }
}
