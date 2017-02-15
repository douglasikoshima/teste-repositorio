package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.UfTO;

public interface UfDAO {
	public List<UfTO> findAll() throws DAOException;
}
