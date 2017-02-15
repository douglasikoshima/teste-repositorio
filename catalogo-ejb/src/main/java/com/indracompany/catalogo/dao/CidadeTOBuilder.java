package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Cidade;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.LocalidadeTO;

public class CidadeTOBuilder {

    public CidadeTO buildBasicTO(Cidade entity) {
        CidadeTO to = null;
        if (entity != null) {
            to = new CidadeTO();
            to.setDtUltimaAlteracao(entity.getDtUltimaAlteracao());
            to.setIdCidade(entity.getIdCidade());
            to.setNmCidade(entity.getNmCidade());
            to.setNmUsuarioAlteracao(entity.getNmUsuarioAlteracao());
        }
        return to;
    }

    public List<CidadeTO> buildBasicTOList(List<Cidade> entityList) {
        List<CidadeTO> toList = new ArrayList<CidadeTO>();
        for (Cidade entity : entityList) {
            toList.add(this.buildBasicTO(entity));
        }
        return toList;
    }
    
    public CidadeTO buildBasicTOWithAreaRegistro(Cidade entity) {
        CidadeTO to = null;
        if (entity != null) {
            to = this.buildBasicTO(entity);
            to.setAreaRegistroTO(new AreaRegistroTOBuilder().createTO(entity.getAreaRegistro()));
        }
        return to;
    }
    public CidadeTO buildTO(Cidade entity) {
        CidadeTO to = null;
        if (entity != null) { 
            to = this.buildBasicTO(entity);
            to.setAreaRegistroTO(new AreaRegistroTOBuilder().createTO(entity.getAreaRegistro()));
            to.setLocalidadeTOList(new LocalidadeTOBuilder().buildBasicTOList(entity.getLocalidadeList()));
            for(LocalidadeTO localidadeTO : to.getLocalidadeTOList()) {
            	localidadeTO.setCidadeTO(to);
            }
        }
        return to;
    }

}
