package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Localidade;
import com.indracompany.catalogo.datalayer.OfertafixaFatorLocalidade;
import com.indracompany.catalogo.to.LocalidadeTO;

public class LocalidadeTOBuilder {
    
    public List<LocalidadeTO> buildTOList(List<Localidade> entityList) {
        List<LocalidadeTO> toList = new ArrayList<LocalidadeTO>();
        for (Localidade entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }
    
    public List<LocalidadeTO> extractTOList(List<OfertafixaFatorLocalidade> entityList) {
        List<LocalidadeTO> toList = new ArrayList<LocalidadeTO>();
        for (OfertafixaFatorLocalidade entity : entityList) {
            toList.add(this.buildTO(entity.getLocalidade()));
        }
        return toList;
    }

    public LocalidadeTO buildTO(Localidade entity) {
        LocalidadeTO to = null;
        if(entity != null) {
            to = this.buildBasicTO(entity);
            to.setCidadeTO(new CidadeTOBuilder().buildBasicTOWithAreaRegistro(entity.getCidade()));
        }
        return to;
    }

    public List<LocalidadeTO> buildBasicTOList(List<Localidade> entityList) {
        List<LocalidadeTO> toList = new ArrayList<LocalidadeTO>();
        for (Localidade entity : entityList) {
            toList.add(this.buildBasicTO(entity));
        }
        return toList;
    }

    public LocalidadeTO buildBasicTO(Localidade entity) {
        LocalidadeTO to = null;
        if (entity != null) {
            to = new LocalidadeTO();
            to.setCdLocalidade(entity.getCdLocalidade());
            to.setIdLocalidade(entity.getIdLocalidade());
            to.setNmLocalidade(entity.getNmLocalidade());            
        }
        return to;
    }
}
