package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoEncargoTO;

public interface TipoEncargoDAO {
	public List<TipoEncargoTO> findAll() throws DAOException; 
}
