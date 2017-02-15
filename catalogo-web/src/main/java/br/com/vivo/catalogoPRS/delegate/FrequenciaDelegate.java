package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.frequencia.FrequenciaBeanLocal;
import com.indracompany.catalogo.to.FrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FrequenciaDelegate {
	
	private static Logger logger = Logger.getLogger(FrequenciaDelegate.class);
	
	/**
	 * @return
	 */
	public List<FrequenciaTO> findAll() {
		
		List<FrequenciaTO> frequenciaTOList = null;
		
		try {
			FrequenciaBeanLocal frequenciaBeanLocal = (FrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FrequenciaBeanLocal.JNDI_NAME);
			frequenciaTOList = frequenciaBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return frequenciaTOList;
	}
	
	/**
	 * @param frequenciaTO
	 */
	public void createUpdateFrequencia(FrequenciaTO frequenciaTO) throws BusinessException {
		
		try {
			FrequenciaBeanLocal frequenciaBeanLocal = (FrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FrequenciaBeanLocal.JNDI_NAME);
			frequenciaBeanLocal.createUpdateFrequencia(frequenciaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateFrequencia]", e);
		}
	}

	/**
	 * @param frequenciaTO
	 * @return
	 */
	public FrequenciaTO findById(FrequenciaTO frequenciaTO) {
	
		FrequenciaTO frequenciaTOResult = null;
		
		try {
			FrequenciaBeanLocal frequenciaBeanLocal = (FrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FrequenciaBeanLocal.JNDI_NAME);
			frequenciaTOResult = frequenciaBeanLocal.findById(frequenciaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return frequenciaTOResult;
	}
	
	/**
	 * @param frequenciaTO
	 */
	public void removeFrequencia(FrequenciaTO frequenciaTO) throws BusinessException {
		
		try {
			FrequenciaBeanLocal frequenciaBeanLocal = (FrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FrequenciaBeanLocal.JNDI_NAME);
			frequenciaBeanLocal.removeFrequencia(frequenciaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeFrequencia]", e);
		}
	}
}
