package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.acao.AcaoBeanLocal;
import com.indracompany.catalogo.to.AcaoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class AcaoDelegate {
	
	private static Logger logger = Logger.getLogger(AcaoDelegate.class);
	
	/**
	 * @param acaoTO
	 * @return
	 */
	public List<AcaoTO> searchAcao(AcaoTO acaoTO) {
		
		List<AcaoTO> acaoList = null;
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoList = acaoBeanLocal.searchAcao(acaoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchAcao]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return acaoList;
	}
	
	/**
	 * @param acaoTO
	 */
	public void createUpdateAcao(AcaoTO acaoTO) throws BusinessException {
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoBeanLocal.createUpdateAcao(acaoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateAcao]", e);
		}
	}

	/**
	 * @param acaoTO
	 * @return
	 */
	public AcaoTO findById(AcaoTO acaoTO) {
	
		AcaoTO acaoTOResult = null;
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoTOResult = acaoBeanLocal.findById(acaoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return acaoTOResult;
	}
	
	/**
	 * @param acaoTO
	 */
	public void removeAcao(AcaoTO acaoTO) throws BusinessException {
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoBeanLocal.removeAcao(acaoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeAcao]", e);
		}
	}
	
	/**
	 * @param idAcao
	 * @param idsProduto
	 * @throws BusinessException
	 */
	public void removeAssociacaoAcaoProduto(AcaoTO acaoTO, String[] idsProduto) throws BusinessException {
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoBeanLocal.removeAssociacaoAcaoProduto(acaoTO, idsProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removeAssociacaoAcaoProduto]", e);
		}
	}
	
	/**
	 * @param idAcao
	 * @param idsProduto
	 * @throws BusinessException
	 */
	public void createAssociacaoAcaoProduto(AcaoTO acaoTO, String[] idsProduto) throws BusinessException {
		
		try {
			AcaoBeanLocal acaoBeanLocal = (AcaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(AcaoBeanLocal.JNDI_NAME);
			acaoBeanLocal.createAssociacaoAcaoProduto(acaoTO, idsProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createAssociacaoAcaoProduto]", e);
		}
	}
}
