package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;


/**
 * @author Luiz Pereira
 *
 */
public interface ContratoMatrizOfertaLockDAO {
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws DAOException
	 * @return
	 * 
	 * Método responsável em verificar se o usuário corrente tem algum arquivo bloqueado.
	 */
	public Boolean existFileLockedByUser(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException;
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws DAOException
	 * @return
	 * 
	 * Método responsável em obter o arquivo corrente que está bloqueado.
	 */
	public ContratoMatrizOfertaLockTO findFileLockedCurrent() throws DAOException;
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws DAOException
	 * 
	 * Método responsável em incluir/alerar um registro na tabela ContratoMatrizOfertaLock
	 */
	public void createUpdateContratoMatrizOfertaLock(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException;
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws DAOException
	 * 
	 * Método responsável em atualizar o status de um registro.
	 */
	public void updateStatusLiberar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException;
	
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws DAOException
	 * 
	 * Método responsável em alterar o arquivo de lock após importação.
	 */
	public void updateStatusImportar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException;
	
}
