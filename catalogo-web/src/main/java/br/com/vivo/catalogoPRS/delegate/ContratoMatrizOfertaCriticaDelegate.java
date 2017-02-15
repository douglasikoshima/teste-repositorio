package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.contratomatrizofertacritica.ContratoMatrizOfertaCriticaBeanLocal;
import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ContratoMatrizOfertaCriticaDelegate {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaCriticaDelegate.class);
	
	/**
	 * @return
	 * 
	 * Método responsável em obter todas as Criticas.
	 */
	public List<ContratoMatrizOfertaCriticaTO> findAllCriticas() {
		List<ContratoMatrizOfertaCriticaTO> list = null;
		
		try {
			ContratoMatrizOfertaCriticaBeanLocal contratoMatrizOfertaCriticaBeanLocal = (ContratoMatrizOfertaCriticaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaCriticaBeanLocal.JNDI_NAME);
			list = contratoMatrizOfertaCriticaBeanLocal.findAllCriticas();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAllCriticas]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	public List<String> exportContratoMatrizOfertaCritica() {
		
		List<String> list = null;
		
		try {
			ContratoMatrizOfertaCriticaBeanLocal contratoMatrizOfertaCriticaBeanLocal = (ContratoMatrizOfertaCriticaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ContratoMatrizOfertaCriticaBeanLocal.JNDI_NAME);
			list = contratoMatrizOfertaCriticaBeanLocal.exportContratoMatrizOfertaCritica();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [exportContratoMatrizOfertaCritica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return list;
		
	}
	
}
