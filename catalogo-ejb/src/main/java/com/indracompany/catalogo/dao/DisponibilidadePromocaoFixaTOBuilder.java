package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;

public class DisponibilidadePromocaoFixaTOBuilder {

    public List<DisponibilidadePromocaoFixaTO> buildTOList(List<Object[]> entityList) {
        List<DisponibilidadePromocaoFixaTO> toList = new ArrayList<DisponibilidadePromocaoFixaTO>();
        for(Object[] entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public DisponibilidadePromocaoFixaTO buildTO(Object[] entity) {
        DisponibilidadePromocaoFixaTO to = null;
        if (entity != null) {
            to = new DisponibilidadePromocaoFixaTO();
            to.setIdValorPoliticaPromocaoTO((Integer) entity[0]);
            to.setNmEps((String)entity[1]);
            to.setIdCanalVenda(((Long)entity[2]).intValue() );
            to.setNmCanalVenda((String)entity[3]);
            to.setIdEps((Integer)entity[4]);
            to.setCdCanalVenda(Integer.parseInt((String)entity[5]));
        }
        return to;
    }

}
