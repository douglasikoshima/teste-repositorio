package br.com.vivo.catalogoPRS.delegate;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.contratomatrizofertalock.ContratoMatrizOfertaLockBeanLocal;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ContratoMatrizOfertaLockDelegate {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaLockDelegate.class);
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * @return
	 * 
	 * Método responsável em verificar se o usuário corrente tem algum arquivo bloqueado.
	 */
	public Boolean existFileLockedByUser(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) {
		Boolean retorno = null;
		
		try {
			ContratoMatrizOfertaLockBeanLocal contratoMatrizOfertaLockBeanLocal = (ContratoMatrizOfertaLockBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaLockBeanLocal.JNDI_NAME);
			retorno = contratoMatrizOfertaLockBeanLocal.existFileLockedByUser(contratoMatrizOfertaLockTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [existFileLockedByUser]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return retorno;
	}
	
	/**
	 * @return
	 * 
	 * Método responsável em obter o arquivo corrente que está bloqueado.
	 */
	public ContratoMatrizOfertaLockTO findFileLockedCurrent() {
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTOResult = null;
		
		try {
			ContratoMatrizOfertaLockBeanLocal contratoMatrizOfertaLockBeanLocal = (ContratoMatrizOfertaLockBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaLockBeanLocal.JNDI_NAME);
			contratoMatrizOfertaLockTOResult = contratoMatrizOfertaLockBeanLocal.findFileLockedCurrent();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findFileLockedCurrent]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return contratoMatrizOfertaLockTOResult;
	}
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * 
	 * Método responsável em incluir/alerar um registro na tabela ContratoMatrizOfertaLock
	 */
	public void createUpdateContratoMatrizOfertaLock(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) {
		
		try {
			ContratoMatrizOfertaLockBeanLocal contratoMatrizOfertaLockBeanLocal = (ContratoMatrizOfertaLockBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaLockBeanLocal.JNDI_NAME);
			contratoMatrizOfertaLockBeanLocal.createUpdateContratoMatrizOfertaLock(contratoMatrizOfertaLockTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateContratoMatrizOfertaLock]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	/**
	 * @param contratoMatrizOfertaLockTO
	 * 
	 * Método responsável em atualizar o status de um registro.
	 */
	public void updateStatusLiberar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) {
		
		try {
			ContratoMatrizOfertaLockBeanLocal contratoMatrizOfertaLockBeanLocal = (ContratoMatrizOfertaLockBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaLockBeanLocal.JNDI_NAME);
			contratoMatrizOfertaLockBeanLocal.updateStatusLiberar(contratoMatrizOfertaLockTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [updateStatusLiberar]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
}
