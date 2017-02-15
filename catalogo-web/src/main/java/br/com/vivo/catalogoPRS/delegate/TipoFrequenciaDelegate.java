package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.tipofrequencia.TipoFrequenciaBeanLocal;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class TipoFrequenciaDelegate {
	
	private static Logger logger = Logger.getLogger(TipoFrequenciaDelegate.class);
	
	/**
	 * @return
	 */
	public List<TipoFrequenciaTO> findAll() {
		
		List<TipoFrequenciaTO> tipoFrequenciaTOList = null;
		
		try {
			TipoFrequenciaBeanLocal tipoFrequenciaBeanLocal = (TipoFrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoFrequenciaBeanLocal.JNDI_NAME);
			tipoFrequenciaTOList = tipoFrequenciaBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoFrequenciaTOList;
	}
	
	/**
	 * @param tipoFrequenciaTO
	 */
	public void createUpdateTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException {
		
		try {
			TipoFrequenciaBeanLocal tipoFrequenciaBeanLocal = (TipoFrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoFrequenciaBeanLocal.JNDI_NAME);
			tipoFrequenciaBeanLocal.createUpdateTipoFrequencia(tipoFrequenciaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateTipoFrequencia]", e);
		}
	}

	/**
	 * @param tipoFrequenciaTO
	 * @return
	 */
	public TipoFrequenciaTO findById(TipoFrequenciaTO tipoFrequenciaTO) {
	
		TipoFrequenciaTO tipoFrequenciaTOResult = null;
		
		try {
			TipoFrequenciaBeanLocal tipoFrequenciaBeanLocal = (TipoFrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoFrequenciaBeanLocal.JNDI_NAME);
			tipoFrequenciaTOResult = tipoFrequenciaBeanLocal.findById(tipoFrequenciaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return tipoFrequenciaTOResult;
	}
	
	/**
	 * @param tipoFrequenciaTO
	 */
	public void removeTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException {
		
		try {
			TipoFrequenciaBeanLocal tipoFrequenciaBeanLocal = (TipoFrequenciaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TipoFrequenciaBeanLocal.JNDI_NAME);
			tipoFrequenciaBeanLocal.removeTipoFrequencia(tipoFrequenciaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeTipoFrequencia]", e);
		}
	}
}
