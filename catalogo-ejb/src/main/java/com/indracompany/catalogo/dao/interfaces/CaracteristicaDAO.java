package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;

public interface CaracteristicaDAO {
	
	/**
	 * @param caracteristicaTO
	 * @return
	 * 
	 * Método responsável em pesquisar na base uma ou mais Caracteristicas.
	 */
	public List<CaracteristicaTO> searchCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException;
		
	/**
	 * @param caracteristicaTO
	 * 
	 * Método responsável em criar/editar uma Caracteristica na base.
	 */
	public CaracteristicaTO createUpdateCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException;
 	
	/**
	 * @param caracteristicaTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma Caracteristica na base.
	 */
	public CaracteristicaTO findById(CaracteristicaTO caracteristicaTO) throws DAOException;
	
	public Caracteristica findById(Integer idCaracteristica) throws DAOException;
	
	/**
	 * @param caracteristicaTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma Caracteristica na base
	 */
	public void removeCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException;
	
	/**
	 * @param caracteristicaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter uma caracteristica pelo nome
	 */
	public CaracteristicaTO findByName(CaracteristicaTO caracteristicaTO) throws DAOException;
	
	/**
	 * @param caracteristicaTO
	 * @return
	 * @throws DAOException
	 */
	public List<CaracteristicaTO> findByGrupoCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException;
	
	/**
	 * 
	 * @param pesquisaTO
	 * @throws DAOException
	 * 
	 * Método responsável em pesquisar características para uma DataTable
	 */
	public void searchCaracteristica(PesquisaIdNomeTO pesquisaTO) throws DAOException;
	
	public List<CaracteristicaTO> findAll() throws DAOException;
	
}
