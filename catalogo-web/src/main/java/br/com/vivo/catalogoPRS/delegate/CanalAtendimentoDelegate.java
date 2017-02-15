package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.ejb.canalatendimento.CanalAtendimentoBeanLocal;
import com.indracompany.catalogo.to.CanalAtendimentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CanalAtendimentoDelegate {
	
	private static Logger logger = Logger.getLogger(CanalAtendimentoDelegate.class);
	
	/**
	 * @return
	 */
	public List<CanalAtendimentoTO> findAll() {
		
		List<CanalAtendimentoTO> canalAtendimentoTOList = null;
		
		try {
			CanalAtendimentoBeanLocal canalAtendimentoBeanLocal = (CanalAtendimentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalAtendimentoBeanLocal.JNDI_NAME);
			canalAtendimentoTOList = canalAtendimentoBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		logger.debug("findAll");
		
		return canalAtendimentoTOList;
	}
	
	public CanalAtendimento findById(Integer idCanal) throws BusinessException {
			CanalAtendimento canal = new CanalAtendimento();
		
			CanalAtendimentoBeanLocal canalAtendimentoBeanLocal;
			try {
				canalAtendimentoBeanLocal = (CanalAtendimentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CanalAtendimentoBeanLocal.JNDI_NAME);
				canal = canalAtendimentoBeanLocal.findById(idCanal);
				
			} catch (ServiceLocatorException e) {
				
				e.printStackTrace();
			}
	
		return canal;
	}
}
