package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EpsTO;

public interface EpsDAO {

	public List<EpsTO> findByName(EpsTO to) throws DAOException;
	
	public void mergeEpsGrupoEps(EpsTO to) throws DAOException;	
	
	public void remove(EpsTO to) throws DAOException;
	
	public List<EpsTO> search(EpsTO to) throws DAOException;
	
	public List<EpsTO> listEpsTO() throws DAOException;
	
}