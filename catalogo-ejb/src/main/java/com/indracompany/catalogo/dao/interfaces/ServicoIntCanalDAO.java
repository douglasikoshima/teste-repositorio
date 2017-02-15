package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntCanalTO;

public interface ServicoIntCanalDAO {

	public List<ServicoIntCanal> findById (Integer idServicoIntCanal) throws DAOException;
	
	public void createUpdateServicoIntCanal(ServicoIntCanalTO servicoIntCanalTO) throws DAOException;
	
	public void removeSrvIntCanalById(Integer idServicoInteratividade) throws DAOException;
	
}
