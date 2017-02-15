package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SegmentoTO;

public interface SegmentoDAO {
	
	public List<SegmentoTO> findAll() throws DAOException;
}
