package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.datalayer.GrupoProdutoTecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoTecnologiaTO;

/**
 * 
 * @author gmuniz
 *
 */
public interface GrupoProdutoTecnologiaDAO {
	
	public void save(GrupoProdutoTecnologiaTO grupoProdutoTecnologiaTO) throws DAOException;
	
	public void save(GrupoProdutoTecnologia grupoProdutoTecnologia) throws DAOException;
	
	public void remove(GrupoProdutoTecnologia grupoProdutoTecnologia) throws DAOException;
	
}