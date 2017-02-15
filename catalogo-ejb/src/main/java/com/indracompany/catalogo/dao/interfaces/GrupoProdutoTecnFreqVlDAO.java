package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.datalayer.GrupoProdutoTecnFreqVl;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoTecnFreqVlTO;

/**
 * 
 * @author gmuniz
 *
 */
public interface GrupoProdutoTecnFreqVlDAO {
	
	public void save(GrupoProdutoTecnFreqVlTO grupoProdutoTecnFreqVlTO) throws DAOException;
	
	public void save(GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl) throws DAOException;
	
	public void remove(GrupoProdutoTecnFreqVl grupoProdutoTecnFreqVl) throws DAOException;
	
}