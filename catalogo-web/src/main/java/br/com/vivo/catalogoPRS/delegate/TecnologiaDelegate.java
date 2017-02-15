package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.ejb.tecnologia.TecnologiaBeanLocal;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class TecnologiaDelegate {
	
	private static Logger logger = Logger.getLogger(TecnologiaDelegate.class);
	
	/**
	 * @return
	 */
	public List<TecnologiaTO> findAll() {
		List<TecnologiaTO> tecnologiaTOList = null;
		try {
			TecnologiaBeanLocal tecnologiaBeanLocal = (TecnologiaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TecnologiaBeanLocal.JNDI_NAME);
			tecnologiaTOList = tecnologiaBeanLocal.findAll();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return tecnologiaTOList;
	}
	
	public void searchTecnologia(PesquisaIdNomeTO pesquisaTO) throws BusinessException {
		try {
			TecnologiaBeanLocal tecnologiaBeanLocal = (TecnologiaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TecnologiaBeanLocal.JNDI_NAME);
			tecnologiaBeanLocal.searchTecnologia(pesquisaTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchTecnologia]", e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}
	}
	
	public Tecnologia findById(Integer idTecnologia) throws BusinessException {
		Tecnologia tecnologia = new Tecnologia();
		
		TecnologiaBeanLocal tecnologiaBeanLocal;
		try {
			tecnologiaBeanLocal = (TecnologiaBeanLocal) ServiceLocator.getInstance().getEJBLocal(TecnologiaBeanLocal.JNDI_NAME);
			tecnologia = tecnologiaBeanLocal.findById(idTecnologia);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
			throw e;
		}		
		
		return tecnologia;
	}
	
}