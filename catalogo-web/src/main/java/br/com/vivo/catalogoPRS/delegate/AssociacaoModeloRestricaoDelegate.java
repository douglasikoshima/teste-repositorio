package br.com.vivo.catalogoPRS.delegate;

import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.modelorestricao.AssociacaoModeloRestricaoBeanLocal;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class AssociacaoModeloRestricaoDelegate {
	
	private static Logger logger = Logger.getLogger(AssociacaoModeloRestricaoDelegate.class);
	
	/**
	 * @param acaoTO
	 */
	public void createUpdateRestricoes(GrupoProdutoTO grupoProdutoTO, Map<Integer, List<ModeloTipoProdutoCompativelTO>> map, String user) throws BusinessException {
		logger.info("grupoProdutoTO: " + grupoProdutoTO);
		
		try {
			AssociacaoModeloRestricaoBeanLocal associacaoModeloRestricaoBeanLocal = (AssociacaoModeloRestricaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AssociacaoModeloRestricaoBeanLocal.JNDI_NAME);
			associacaoModeloRestricaoBeanLocal.createUpdateRestricoes(grupoProdutoTO, map, user);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateRestricoes]", e);
		}
	}

}