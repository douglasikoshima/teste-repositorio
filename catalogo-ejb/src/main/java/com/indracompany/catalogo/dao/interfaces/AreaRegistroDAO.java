package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.AreaRegistro;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaRegistroTO;

public interface AreaRegistroDAO {
	
	public AreaRegistro findById(Integer IdAreaRegistro) throws DAOException;
	
	public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws DAOException;
	
	public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws DAOException;
	
}