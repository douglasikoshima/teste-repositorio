package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfertaSAPTO;

public interface OfertaSAPDAO {
	public List<OfertaSAPTO> findAll() throws DAOException;
}