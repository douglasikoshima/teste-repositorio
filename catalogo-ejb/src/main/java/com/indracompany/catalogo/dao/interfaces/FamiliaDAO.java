package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FamiliaTO;

public interface FamiliaDAO {
    
    public List<FamiliaTO> search(FamiliaTO familiaTO) throws DAOException;

    public FamiliaTO findById(Integer id) throws DAOException;

    public FamiliaTO insertUpdate(FamiliaTO familiaTO) throws DAOException;
    
    public void remove(Integer id) throws DAOException;
    
    public void changeStatus(Integer id) throws DAOException;
    
    public FamiliaTO searchByName(String nmFamilia) throws DAOException;
    
    public List<FamiliaTO> findAll() throws DAOException;

}
