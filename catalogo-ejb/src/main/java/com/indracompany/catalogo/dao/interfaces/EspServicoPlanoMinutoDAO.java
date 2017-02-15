package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;

public interface EspServicoPlanoMinutoDAO {

	public List<EspServicoPlanoMinutoTO> findAllNotInIdList(List<Integer> idList, Integer idSistema) throws DAOException;
	
	public List<EspServicoPlanoMinutoTO> findAllInIdList(List<Integer> idList) throws DAOException;
	
}
