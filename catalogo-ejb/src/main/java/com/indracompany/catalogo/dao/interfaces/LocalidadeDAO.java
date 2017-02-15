package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Localidade;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.LocalidadeTO;

public interface LocalidadeDAO {
	
	public Localidade findById(Long IdLocalidade) throws DAOException;
	
	public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws DAOException;
	
}