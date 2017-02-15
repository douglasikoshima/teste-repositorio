package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ComposicaoPromocao;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;

public class ComposicaoPromocaoTOBuilder {

    public List<ComposicaoPromocaoTO> buildTOList(List<Object[]> entityList) {
        List<ComposicaoPromocaoTO> toList = new ArrayList<ComposicaoPromocaoTO>();
        for(Object[] entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public ComposicaoPromocaoTO buildTO(Object[] entity) {
        ComposicaoPromocaoTO to = null;
        if (entity != null) {
            to = new ComposicaoPromocaoTO();
            to.setIdComposicao((Integer)entity[0]);
            to.setNomeServico((String)entity[1]);
            to.setNomeDesconto((String)entity[2]);
            to.setNomeSolicitacao((String)entity[3]);
            to.setValidadeDesconto((Integer)entity[4]);
            to.setIdServico((Integer)entity[5]);
        }
        return to;
    }

    public ComposicaoPromocao buildEntity(ComposicaoPromocaoTO to) {
        ComposicaoPromocao entity = null;
        if (to != null) {
            entity = new ComposicaoPromocao(to.getIdServico(), to.getIdPromocao(), new Long(to.getIdSolicitacao()), to.getValidadeDesconto());
        }
        return entity;
    }

}
