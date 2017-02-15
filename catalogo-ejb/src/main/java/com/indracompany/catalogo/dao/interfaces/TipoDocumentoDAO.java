package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public interface TipoDocumentoDAO {
	
	public List<TipoDocumentoTO> findAll() throws DAOException;
	
}
