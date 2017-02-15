package com.indracompany.catalogo.dao.interfaces;

import javax.ejb.Local;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

@Local
public interface OfertaServicoCategoriaScoreDAO {
	
	public void removeByCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException;
	
	public void removeByOfertaServico(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException;
	
	public void createOfertaServicoCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws DAOException;
}
