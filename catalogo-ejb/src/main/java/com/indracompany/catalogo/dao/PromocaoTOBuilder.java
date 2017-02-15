package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.indracompany.catalogo.datalayer.Promocao;
import com.indracompany.catalogo.to.PromocaoTO;

public class PromocaoTOBuilder {
    public List<PromocaoTO> buildTOList(List<Promocao> entityList) {
        List<PromocaoTO> toList = new ArrayList<PromocaoTO>();
        for(Promocao entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public PromocaoTO buildTO(Promocao entity) {
        PromocaoTO to = null;
        if (entity != null) {
            to = new PromocaoTO();
            to.setCdPromocao(entity.getCdPromocao());
            to.setDtFim(entity.getDtFim());
            to.setDtInicio(entity.getDtInicio());
            to.setIdPromocao(entity.getIdPromocao());
            to.setNmPromocao(entity.getNmPromocao());
            Date hoje = DateTime.now().toDateMidnight().toDate();
            if (entity.getDtInicio().compareTo(hoje) <= 0 && (entity.getDtFim() == null || entity.getDtFim().compareTo(hoje) >= 0)) {
                to.setStatus("vigente");
            } else if (entity.getDtInicio().compareTo(hoje) > 0) {
                to.setStatus("naoiniciado");
            } else if (entity.getDtFim() != null && entity.getDtFim().compareTo(hoje) < 0) {
                to.setStatus("finalizado");
            } else {
                to.setStatus("");
            }
            to.setInStatusConfiguracao(entity.getInStatusConfiguracao());
            
        }
        return to;
    }

    /**
     * Transfer data from TO object to Entity object 
     * @param to
     * @param entity
     * @return the same entity
     * @throws NullPointerException if one of then be null
     */
    public Promocao updateAttachedEntity(PromocaoTO to, Promocao entity) {
        if (entity.getCdPromocao() == null) {
            entity.setCdPromocao(to.getCdPromocao());
        }
        entity.setNmPromocao(to.getNmPromocao());
        
        Date hoje = DateTime.now().toDateMidnight().toDate();
        
        if (entity.getDtInicio() == null || entity.getDtInicio().compareTo(hoje) > 0) {
            entity.setDtInicio(to.getDtInicio());
        }
        if (entity.getDtFim() == null || !(entity.getDtFim().compareTo(hoje) < 0)){
            entity.setDtFim(to.getDtFim());
        }
        return entity;
    }
}
