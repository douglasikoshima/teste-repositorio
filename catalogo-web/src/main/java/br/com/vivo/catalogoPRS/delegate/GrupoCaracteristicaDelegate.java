package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.grupocaracteristica.GrupoCaracteristicaBeanLocal;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

public class GrupoCaracteristicaDelegate {
	
	private static Logger logger = Logger.getLogger(GrupoCaracteristicaDelegate.class);
	
	/*public List<GrupoCaracteristicaTO> findAllLists() {
		logger.debug("inicio findAll");
		List<GrupoCaracteristicaTO> list = null;
		try {
			GrupoCaracteristicaBeanLocal grupoCaracteristicaBeanLocal = (GrupoCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoCaracteristicaBeanLocal.JNDI_NAME);
			list = grupoCaracteristicaBeanLocal.findAllLists();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return list;
	}*/
	
	public List<GrupoCaracteristicaTO> findAll() {
		logger.debug("inicio findAll");
		List<GrupoCaracteristicaTO> list = null;
		try {
			GrupoCaracteristicaBeanLocal grupoCaracteristicaBeanLocal = (GrupoCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoCaracteristicaBeanLocal.JNDI_NAME);
			list = grupoCaracteristicaBeanLocal.listarGrupoCaracteristica();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return list;
	}
	
	public void createUpdateGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws EJBException {
		logger.debug("inicio createUpdateGrupoCaracteristica");
		try {
			GrupoCaracteristicaBeanLocal grupoCaracteristicaBeanLocal = (GrupoCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoCaracteristicaBeanLocal.JNDI_NAME);
			grupoCaracteristicaBeanLocal.createUpdateGrupoCaracteristica(grupoCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar createUpdateGrupoCaracteristica", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
	}
	
	public void deleteGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws EJBException, BusinessException {
		logger.debug("inicio deleteGrupoCaracteristica");
		try {
			GrupoCaracteristicaBeanLocal grupoCaracteristicaBeanLocal = (GrupoCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoCaracteristicaBeanLocal.JNDI_NAME);
			grupoCaracteristicaBeanLocal.deleteGrupoCaracteristica(grupoCaracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar deleteGrupoCaracteristica", e);
		}		
	}	
	
	public GrupoCaracteristicaTO getGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws EJBException {
		logger.debug("inicio getGrupoCaracteristica");
		GrupoCaracteristicaTO grupo = null;
		try {
			GrupoCaracteristicaBeanLocal grupoCaracteristicaBeanLocal = (GrupoCaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoCaracteristicaBeanLocal.JNDI_NAME);
			grupo = grupoCaracteristicaBeanLocal.getGrupoCaracteristica(grupoCaracteristicaTO);			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar getGrupoCaracteristica", e);
		} catch (BusinessException e) {
			logger.error(e);
		}	
		return grupo;
	}		
}
