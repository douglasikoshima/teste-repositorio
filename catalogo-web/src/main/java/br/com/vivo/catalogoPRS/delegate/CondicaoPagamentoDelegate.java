package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.condicapagamento.CondicaoPagamentoBeanLocal;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class CondicaoPagamentoDelegate {
	
	private static Logger logger = Logger.getLogger(CondicaoPagamentoDelegate.class);
	
	/**
	 * @return
	 */
	public void createUpdateCondicaoPagamentoList(List<CondicaoPagamentoTO> condicaoPagamentoTOList) {
		
		try {
			
			CondicaoPagamentoBeanLocal condicaoPagamentoBeanLocal = (CondicaoPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(CondicaoPagamentoBeanLocal.JNDI_NAME);
			condicaoPagamentoBeanLocal.createUpdateCondicaoPagamentoList(condicaoPagamentoTOList);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCondicaoPagamento]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
}
