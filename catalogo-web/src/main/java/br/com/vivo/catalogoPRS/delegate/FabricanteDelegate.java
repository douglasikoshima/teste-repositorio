package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.fabricante.FabricanteBeanLocal;
import com.indracompany.catalogo.to.FabricanteTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FabricanteDelegate {
	
	private static Logger logger = Logger.getLogger(FabricanteDelegate.class);
	
	/**
	 * @return
	 */
	public List<FabricanteTO> findAll() {
		
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			FabricanteBeanLocal fabricanteBeanLocal = (FabricanteBeanLocal) ServiceLocator.getInstance().getEJBLocal(FabricanteBeanLocal.JNDI_NAME);
			fabricanteTOList = fabricanteBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return fabricanteTOList;
	}
	
	/**
	 * @param fabricanteTO
	 * @return
	 */
	public List<FabricanteTO> search(FabricanteTO fabricanteTO) {
		
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			
			FabricanteBeanLocal fabricanteBeanLocal = (FabricanteBeanLocal) ServiceLocator.getInstance().getEJBLocal(FabricanteBeanLocal.JNDI_NAME);
			fabricanteTOList = fabricanteBeanLocal.search(fabricanteTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [search]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return fabricanteTOList;
	}
}
