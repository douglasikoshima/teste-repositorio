package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.MatrizOfertaArquivoCritica;
import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

public class MatrizOfertaArquivoCriticaTOBuilder {

    /**
     * BasicTO &eacute; um objeto preenchido sem nenhum relacionamento
     * @param matrizOfertaArquivoCriticaList
     * @return
     */
    public List<MatrizOfertaArquivoCriticaTO> buildBasicTOList(List<MatrizOfertaArquivoCritica> entityList) {
        List<MatrizOfertaArquivoCriticaTO> toList = new ArrayList<MatrizOfertaArquivoCriticaTO>();
        for (MatrizOfertaArquivoCritica entity : entityList) {
            toList.add(this.buildBasicTO(entity));
        }
        return toList;
    }

    /**
     * BasicTO &eacute; um objeto preenchido sem nenhum relacionamento
     * @param entity
     * @return
     */
    public MatrizOfertaArquivoCriticaTO buildBasicTO(MatrizOfertaArquivoCritica entity) {
        MatrizOfertaArquivoCriticaTO to = null;
        if (entity != null) {
            to = new MatrizOfertaArquivoCriticaTO();
            to.setDscCriticaImportacao(entity.getDscCriticaImportacao());
            to.setIdMatrizOfertaArquivoCritica(entity.getIdMatrizOfertaArquivoCritica());
        }
        return to;
    }
}
