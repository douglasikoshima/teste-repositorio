package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.datalayer.Familia;
import com.indracompany.catalogo.to.FamiliaTO;

public class FamiliaTOBuilder {
    
    public List<FamiliaTO> criarTOList(List<Familia> entityList) {
        List<FamiliaTO> toList = new ArrayList<FamiliaTO>();
        for (Familia entity : entityList) {
            toList.add(this.criarTO(entity));
        }
        return toList;
    }
    
    public FamiliaTO criarTO(Familia entity) {
        FamiliaTO to = this.criarTOSemCategoria(entity);
        if (to != null && entity.getCategoriaList() != null) {
            to.setCategoriaTOList(new CategoriaTOBuilder().criarTOList(entity.getCategoriaList()));
        }
        return to;
    }

    public FamiliaTO criarTOSemCategoria(Familia entity) {
        FamiliaTO to = null;
        if (entity != null) {
            to = new FamiliaTO();
            to.setCdFamilia(entity.getCdFamilia());
            to.setDsFamilia(entity.getDsFamilia());
            to.setIdFamilia(entity.getIdFamilia());
            if (!StringUtils.isBlank(entity.getInCriacaoCatalogo())) {
                to.setInCriacaoCatalogo(entity.getInCriacaoCatalogo().equalsIgnoreCase("S"));
            } else {
                to.setInCriacaoCatalogo(false);
            }
            if (!StringUtils.isBlank(entity.getInDisponivel())) {
                to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            } else {
                to.setInDisponivel(false);
            }
            to.setNmFamilia(entity.getNmFamilia());
        }
        return to;
    }
}
