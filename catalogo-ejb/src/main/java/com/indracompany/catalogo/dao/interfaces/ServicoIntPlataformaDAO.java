package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;

public interface ServicoIntPlataformaDAO {
	
	public List<ServicoIntPlataforma> findByIdServicoInteratividade (Integer idServicoInteratividade ) throws DAOException;

	public void createUpdateServicoIntPlataforma (ServicoIntPlataformaTO servicoIntPlataformaTO) throws DAOException;
	
	public void removeSrvIntPlataformaById (Integer idServicoInteratividade) throws DAOException;
	
}
