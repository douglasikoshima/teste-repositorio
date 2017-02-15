package com.indracompany.catalogo.ejb.familia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FamiliaTO;

@Local
public interface FamiliaBeanLocal {
    
    public static final String JNDI_NAME = "java:comp/env/FamiliaBean";
    
    public List<FamiliaTO> search(FamiliaTO familiaTO) throws BusinessException;

    public FamiliaTO findById(Integer id) throws BusinessException;

    public FamiliaTO insertUpdate(FamiliaTO familiaTO) throws BusinessException;
    
    public void remove(Integer id) throws BusinessException;
    
    public void changeStatus(Integer id) throws BusinessException;
    
    public FamiliaTO searchByName(String nmFamilia) throws BusinessException;

}
