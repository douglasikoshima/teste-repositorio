package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.datalayer.GrupoProdutoCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoCaracteristicaTO;

/**
 * 
 * @author gmuniz
 *
 */
public interface GrupoProdutoCaracteristicaDAO {
	
	public void save(GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO) throws DAOException;
	
	public void save(GrupoProdutoCaracteristica grupoProdutoCaracteristica) throws DAOException;
	
	public void remove(GrupoProdutoCaracteristica grupoProdutoCaracteristica) throws DAOException;
	
}