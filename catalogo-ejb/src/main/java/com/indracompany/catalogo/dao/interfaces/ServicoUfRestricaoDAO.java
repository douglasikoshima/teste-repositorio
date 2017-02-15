package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;

public interface ServicoUfRestricaoDAO {
	
	public void removeByIdsServico(List<Integer> idList) throws DAOException;
	
	public void createUpdateServicoUfRestricaoList(List<PlanoServicoUfRestricaoTO> toList) throws DAOException;
	
	public void createUpdateServicoUfRestricao(PlanoServicoUfRestricaoTO to) throws DAOException;

}