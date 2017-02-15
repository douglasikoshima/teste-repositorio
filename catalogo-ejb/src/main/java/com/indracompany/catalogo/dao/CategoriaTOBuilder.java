package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.datalayer.Categoria;
import com.indracompany.catalogo.to.CategoriaTO;

public class CategoriaTOBuilder {
    
    public CategoriaTO criarTO(Categoria entity) {
        CategoriaTO to = null;
        if (entity != null) {
            to = new CategoriaTO();
            to.setDsCategoria(entity.getDsCategoria());
            to.setDtCriacao(entity.getDtCriacao());
            to.setDtUltimaAlteracao(entity.getDtUltimaAlteracao());
            to.setIdCategoria(entity.getIdCategoria());
            if (!StringUtils.isBlank(entity.getIndCatalogo())) {
                to.setIndCatalogo(entity.getIndCatalogo().equalsIgnoreCase("S"));
            } else {
                to.setIndCatalogo(false);
            }
            if(!StringUtils.isBlank(entity.getInDisponivel())) {
                to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            } else {
                to.setInDisponivel(false);
            }
            if (!StringUtils.isBlank(entity.getInFixa())) {
                to.setInFixa(entity.getInFixa().equalsIgnoreCase("S"));
            } else {
                to.setInFixa(false);
            }
            to.setNmCategoria(entity.getNmCategoria());
            to.setNmUsuarioAlteracao(entity.getNmUsuarioAlteracao());
            to.setNmUsuarioCriacao(entity.getNmuUsuarioCriacao());
            to.setCdCategoria(entity.getCdCategoria());
            to.setInAlteracaoCatalogo(entity.getInAlteracaoCatalogo());
        }
        
        return to;
    }
    
    public CategoriaTO criarTOComFamilia(Categoria entity) {
        CategoriaTO to = this.criarTO(entity);
        if (to != null) {
            to.setFamiliaTO(new FamiliaTOBuilder().criarTOSemCategoria(entity.getFamilia()));
        }
        return to;
    }

    public List<CategoriaTO> criarTOList(List<Categoria> entityList) {
        List<CategoriaTO> toList = new ArrayList<CategoriaTO>();
        for (Categoria entity : entityList) {
            toList.add(this.criarTO(entity));
        }
        return toList;
    }

    public Set<CategoriaTO> criarTOSet(List<Categoria> entityList) {
        Set<CategoriaTO> toList = new HashSet<CategoriaTO>();
        for (Categoria entity : entityList) {
            toList.add(this.criarTO(entity));
        }
        return toList;
    }
    
    public List<CategoriaTO> criarTOListComFamilia(List<Categoria> entityList) {
        List<CategoriaTO> toList = new ArrayList<CategoriaTO>();
        for (Categoria entity : entityList) {
            toList.add(this.criarTOComFamilia(entity));
        }
        return toList;
    }

}
