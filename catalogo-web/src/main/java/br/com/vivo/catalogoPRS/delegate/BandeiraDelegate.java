package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.bandeira.BandeiraBeanLocal;
import com.indracompany.catalogo.to.BandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class BandeiraDelegate {
	
	private static Logger logger = Logger.getLogger(BandeiraDelegate.class);
	
	/**
	 * @param bandeiraTO
	 * @return
	 */
	public List<BandeiraTO> searchBandeira(BandeiraTO bandeiraTO) {
		
		List<BandeiraTO> bandeiraTOList = null;
		
		try {
			
			BandeiraBeanLocal bandeiraBeanLocal = (BandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(BandeiraBeanLocal.JNDI_NAME);
			bandeiraTOList = bandeiraBeanLocal.searchBandeira(bandeiraTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchBandeira]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return bandeiraTOList;
	}
	
	/**
	 * @param bandeiraTO
	 */
	public void createUpdateBandeira(BandeiraTO bandeiraTO) throws BusinessException {
		
		try {
			BandeiraBeanLocal bandeiraBeanLocal = (BandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(BandeiraBeanLocal.JNDI_NAME);
			bandeiraBeanLocal.createUpdateBandeira(bandeiraTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateBandeira]", e);
		}
	}

	/**
	 * @param bandeiraTO
	 * @return
	 */
	public BandeiraTO findById(BandeiraTO bandeiraTO) {
	
		BandeiraTO bandeiraTOResult = null;
		
		try {
			BandeiraBeanLocal bandeiraBeanLocal = (BandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(BandeiraBeanLocal.JNDI_NAME);
			bandeiraTOResult = bandeiraBeanLocal.findById(bandeiraTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return bandeiraTOResult;
	}
	
	/**
	 * @param bandeiraTO
	 */
	public void removeBandeira(BandeiraTO bandeiraTO) throws BusinessException {
		
		try {
			BandeiraBeanLocal bandeiraBeanLocal = (BandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(BandeiraBeanLocal.JNDI_NAME);
			bandeiraBeanLocal.removeBandeira(bandeiraTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeBandeira]", e);
		}
	}
	
	/**
	 * @return
	 */
	public List<BandeiraTO> findAll() {
		
		List<BandeiraTO> bandeiraTOList = null;
		
		try {
			
			BandeiraBeanLocal bandeiraBeanLocal = (BandeiraBeanLocal) ServiceLocator.getInstance().getEJBLocal(BandeiraBeanLocal.JNDI_NAME);
			bandeiraTOList = bandeiraBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return bandeiraTOList;
	}

}
