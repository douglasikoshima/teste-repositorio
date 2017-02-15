package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.BandeiraTO;

public interface BandeiraDAO {
	
	/**
	 * @param bandeiraTO
	 * @return
	 * 
	 * Método responsável em pesquisar na base uma ou mais Bandeiras.
	 */
	public List<BandeiraTO> searchBandeira(BandeiraTO bandeiraTO) throws DAOException;
		
	/**
	 * @param bandeiraTO
	 * 
	 * Método responsável em criar/editar uma Bandeira na base.
	 */
	public void createUpdateBandeira(BandeiraTO bandeiraTO) throws DAOException;
 	
	/**
	 * @param bandeiraTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma Bandeira na base.
	 */
	public BandeiraTO findById(BandeiraTO bandeiraTO) throws DAOException;
	
	/**
	 * @param bandeiraTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma Bandeira na base
	 */
	public void removeBandeira(BandeiraTO bandeiraTO) throws DAOException;
	
	
	/**
	 * @param bandeiraTO
	 * @return
	 * @throws DAOException
	 */
	public Boolean existByCdBandeiraSAP(BandeiraTO bandeiraTO) throws DAOException;

	/**
	 * @return
	 * @throws DAOException
	 */
	public List<BandeiraTO> findAll() throws DAOException;
}
