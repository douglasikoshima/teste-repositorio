package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.caracteristica.CaracteristicaBeanLocal;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CaracteristicaDelegate {
	
	private static Logger logger = Logger.getLogger(CaracteristicaDelegate.class);
	
	/**
	 * @param caracteristicaTO
	 * @return
	 */
	public List<CaracteristicaTO> searchCaracteristica(CaracteristicaTO caracteristicaTO) {
		
		List<CaracteristicaTO> list = null;
		
		try {
			CaracteristicaBeanLocal caracteristicaBeanLocal = (CaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CaracteristicaBeanLocal.JNDI_NAME);
			list = caracteristicaBeanLocal.searchCaracteristica(caracteristicaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/**
	 * @param caracteristicaTO
	 */
	public CaracteristicaTO createUpdateCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException {
		
		
		CaracteristicaTO caracteristicaResultTO = null;
		
		try {
			CaracteristicaBeanLocal caracteristicaBeanLocal = (CaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CaracteristicaBeanLocal.JNDI_NAME);
			caracteristicaResultTO = caracteristicaBeanLocal.createUpdateCaracteristica(caracteristicaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCaracteristica]", e);
		}
		
		return caracteristicaResultTO;
	}

	/**
	 * @param caracteristicaTO
	 * @return
	 */
	public CaracteristicaTO findById(CaracteristicaTO caracteristicaTO) {
	
		CaracteristicaTO caracteristicaTOResult = null;
		
		try {
			CaracteristicaBeanLocal caracteristicaBeanLocal = (CaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CaracteristicaBeanLocal.JNDI_NAME);
			caracteristicaTOResult = caracteristicaBeanLocal.findById(caracteristicaTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return caracteristicaTOResult;
	}
	
	/**
	 * @param caracteristicaTO
	 */
	public void removeCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException {
		
		try {
			CaracteristicaBeanLocal caracteristicaBeanLocal = (CaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CaracteristicaBeanLocal.JNDI_NAME);
			caracteristicaBeanLocal.removeCaracteristica(caracteristicaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeCaracteristica]", e);
		}
	}
	
	public void searchCaracteristica(PesquisaIdNomeTO pesquisaTO) throws BusinessException {
		try {
			CaracteristicaBeanLocal caracteristicaBeanLocal = (CaracteristicaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CaracteristicaBeanLocal.JNDI_NAME);
			caracteristicaBeanLocal.searchCaracteristica(pesquisaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchCaracteristica]",e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
}
