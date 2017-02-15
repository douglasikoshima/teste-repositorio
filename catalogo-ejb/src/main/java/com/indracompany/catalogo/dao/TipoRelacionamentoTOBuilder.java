package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoRelacionamento;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;

public class TipoRelacionamentoTOBuilder {

    public TipoRelacionamentoTO buildTO(TipoRelacionamento entity) {
        TipoRelacionamentoTO to = null;
            if(entity != null) {
                to = new TipoRelacionamentoTO();
                to.setDscTipoRelacionamento(entity.getDscTipoRelacionamento());
                to.setIdTipoRelacionamento(entity.getIdTipoRelacionamento());
                to.setSgTipoRelacionamento(entity.getSgTipoRelacionamento());
            }
        return to;
    }

    public List<TipoRelacionamentoTO> buildTOList(List<TipoRelacionamento> entityList) {
        List<TipoRelacionamentoTO> toList = new ArrayList<TipoRelacionamentoTO>();
        for (TipoRelacionamento entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }
}
