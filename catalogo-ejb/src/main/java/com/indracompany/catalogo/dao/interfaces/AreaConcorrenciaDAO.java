package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;

public interface AreaConcorrenciaDAO {
	
	public List<AreaConcorrenciaTO> findAll() throws DAOException;
	
	public List<AreaConcorrenciaTO> findAllInIdList(List<Long> idList)  throws DAOException;
	
	public List<AreaConcorrenciaTO> findAllNotInIdList(List<Long> idList) throws DAOException;
}
