package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CidadeTO;

public interface CidadeDAO {
	
	public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws DAOException;
	
}