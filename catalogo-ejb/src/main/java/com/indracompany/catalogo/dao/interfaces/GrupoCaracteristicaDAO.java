package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

public interface GrupoCaracteristicaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os Grupos Caracteristicas da base de dados.
	 */
	public List<GrupoCaracteristicaTO> findAll() throws DAOException;
	
	/**
	 * @param grupoCaracteristicaTO
	 * @throws DAOException
	 * 
	 * Método responsável em criar/editar um Grupo Caracteristica.
	 */
	public void createUpdateGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException;
	
	/**
	 * @param grupoCaracteristicaTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover um Grupo Caracteristica da base de dados.
	 */
	public void deleteGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException;
	
	/**
	 * @param grupoCaracteristicaTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter um Grupo Caracteristica a partir de sua Primary Key.
	 */
	public GrupoCaracteristicaTO getGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException;
}
