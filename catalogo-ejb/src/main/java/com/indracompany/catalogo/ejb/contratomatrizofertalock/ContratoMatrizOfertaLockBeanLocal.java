package com.indracompany.catalogo.ejb.contratomatrizofertalock;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Ações com o EJB.  
 */
@Local
public interface ContratoMatrizOfertaLockBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ContratoMatrizOfertaLockBean";
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws BusinessException
	 * @return
	 * 
	 * Método responsável em verificar se o usuário corrente tem algum arquivo bloqueado.
	 */
	public Boolean existFileLockedByUser(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException;
	
	/**
	 * @throws BusinessException
	 * @return
	 * 
	 * Método responsável em obter o arquivo corrente que está bloqueado.
	 */
	public ContratoMatrizOfertaLockTO findFileLockedCurrent() throws BusinessException;
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws BusinessException
	 * 
	 * Método responsável em incluir/alerar um registro na tabela ContratoMatrizOfertaLock
	 */
	public void createUpdateContratoMatrizOfertaLock(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException;
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @throws BusinessException
	 * 
	 * Método responsável em atualizar o status de um registro.
	 */
	public void updateStatusLiberar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException;
	
}
