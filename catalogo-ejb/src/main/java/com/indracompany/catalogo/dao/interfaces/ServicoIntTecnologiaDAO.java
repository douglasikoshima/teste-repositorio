package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;

public interface ServicoIntTecnologiaDAO {

	public List<ServicoIntTecnologia> findByIdServicoInteratividade (Integer idServicoInteratividade) throws DAOException;
	
	public void createUpdateServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) throws DAOException;
	
	public void removeServIntTecnologiaById (Integer idServicoInteratividade) throws DAOException;
	
}
