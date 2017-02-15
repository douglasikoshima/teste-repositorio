package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;

public interface PlanoUfRestricaoDAO {
	
	public void removeByIdsPlano(List<Integer> idList) throws DAOException;
	
	public void createUpdatePlanoUfRestricaoList(List<PlanoServicoUfRestricaoTO> toList) throws DAOException;
	
	public void createUpdatePlanoUfRestricao(PlanoServicoUfRestricaoTO to) throws DAOException;

}