package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="MATRIZOFERTAARQUIVOCRITICA", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "MatrizOfertaArquivoCritica.obterPorIdMatrizOfertaArquivoItem", query = "select moaic.matrizOfertaArquivoCritica from MatrizOfertaArqItemCritica moaic where moaic.matrizOfertaArquivoItem.idMatrizOfertaArquivoItem = :idMatrizOfertaArquivoItem")
})
public class MatrizOfertaArquivoCritica implements Serializable {

    private static final long serialVersionUID = -5020511420170932658L;

    @Id
    @Column(name = "IDMATRIZOFERTAARQUIVOCRITICA")
    private Integer idMatrizOfertaArquivoCritica;
    
    @Column(name = "DSCCRITICAIMPORTACAO")
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
