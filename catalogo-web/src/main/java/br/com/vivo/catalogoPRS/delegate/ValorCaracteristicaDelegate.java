package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.valorcaracteristica.ValorCaracteristicaBeanLocal;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public class ValorCaracteristicaDelegate {
	
	private static Logger logger = Logger.getLogger(ValorCaracteristicaDelegate.class);
	
	public List<ValorCaracteristicaTO> findAll() {
		logger.debug("inicio findAll");
		List<ValorCaracteristicaTO> list = null;
		try {
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			list = valorCaracteristicaBeanLocal.listarValorCaracteristica();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return list;
	}
	
	public void createUpdateValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		logger.debug("inicio createUpdateValorCaracteristica");
		try {
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			valorCaracteristicaBeanLocal.createUpdateValorCaracteristica(valorCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateValorCaracteristica]", e);
		}		
	}	
	
	public void saveValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		logger.debug("inicio saveValorCaracteristica");
		try {
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			valorCaracteristicaBeanLocal.saveValorCaracteristica(valorCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [saveValorCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public void deleteValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws EJBException {
		logger.debug("inicio deleteValorCaracteristica");
		try {
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			valorCaracteristicaBeanLocal.deleteValorCaracteristica(valorCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar [deleteValorCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
	}	
	
	public List<ValorCaracteristicaTO> findByCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) {
		logger.debug("inicio findByCaracteristica");
		List<ValorCaracteristicaTO> list = null;
		try {
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			list = valorCaracteristicaBeanLocal.findByCaracteristica(valorCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return list;
	}
	
	public ValorCaracteristicaTO find(ValorCaracteristicaTO valorCaracteristicaTO) {
		logger.debug("inicio find");
		
		ValorCaracteristicaTO valorCaracteristicaResultTO = null;
		
		try {
			
			ValorCaracteristicaBeanLocal valorCaracteristicaBeanLocal = (ValorCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ValorCaracteristicaBeanLocal.JNDI_NAME);
			valorCaracteristicaResultTO = valorCaracteristicaBeanLocal.find(valorCaracteristicaTO);
		
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [find]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return valorCaracteristicaResultTO;
	}
}
