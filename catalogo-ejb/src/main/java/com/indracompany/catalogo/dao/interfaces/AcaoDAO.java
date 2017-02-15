package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoTO;

/**
 * @author BRUNO
 *
 */
public interface AcaoDAO {
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 * Método responsável em pesquisar na base uma ou mais Ações.
	 */
	public List<AcaoTO> searchAcao(AcaoTO acaoTO) throws DAOException;
		
	/**
	 * @param acaoTO
	 * 
	 * Método responsável em criar/editar uma Ação na base.
	 */
	public AcaoTO createUpdateAcao(AcaoTO acaoTO) throws DAOException;
 	
	/**
	 * @param acaoTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma Ação na base.
	 */
	public AcaoTO findById(AcaoTO acaoTO) throws DAOException;
	
	/**
	 * @param acaoTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma Ação na base
	 */
	public void removeAcao(AcaoTO acaoTO) throws DAOException;
	
	/**
	 * @param acaoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter uma ação pela Sigla
	 */
	public AcaoTO findBySigla(AcaoTO acaoTO) throws DAOException;
	
	
	/**
	 * @param acaoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em verificar se existe associação da 
	 * Ação com a Tabela MatrizOfertaItemPreco.
	 */
	public Boolean existAssociation(AcaoTO acaoTO) throws DAOException;
	
	public List<AcaoTO> findAll() throws DAOException;
}
