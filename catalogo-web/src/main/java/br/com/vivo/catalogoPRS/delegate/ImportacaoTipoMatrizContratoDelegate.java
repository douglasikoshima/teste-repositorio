package br.com.vivo.catalogoPRS.delegate;

import java.io.InputStream;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.importacaotipomatriz.ImportacaoTipoMatrizContratoBeanLocal;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ImportacaoTipoMatrizContratoDelegate {
	
	private static Logger logger = Logger.getLogger(ImportacaoTipoMatrizContratoDelegate.class);
	
	public Boolean importContratoMatrizOferta(InputStream inputStream, String usuario) throws BusinessException {
		
		Boolean retorno = Boolean.TRUE;

		try {
			ImportacaoTipoMatrizContratoBeanLocal importacaoTipoMatrizContratoBeanLocal = (ImportacaoTipoMatrizContratoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoTipoMatrizContratoBeanLocal.JNDI_NAME);
			retorno = importacaoTipoMatrizContratoBeanLocal.importContratoMatrizOferta(inputStream, usuario);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [importContratoMatrizOferta]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		
		return retorno;
	}
	
	public List<String> exportContratoMatrizOferta(Boolean trabalho) throws BusinessException {
		
		List<String> list = null;
		
		try {
			ImportacaoTipoMatrizContratoBeanLocal importacaoTipoMatrizContratoBeanLocal = (ImportacaoTipoMatrizContratoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoTipoMatrizContratoBeanLocal.JNDI_NAME);
			list = importacaoTipoMatrizContratoBeanLocal.exportContratoMatrizOferta(trabalho);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [exportContratoMatrizOferta]", e);
		}
		
		return list;
	}
	
	public Boolean validarDados() throws BusinessException {
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			ImportacaoTipoMatrizContratoBeanLocal importacaoTipoMatrizContratoBeanLocal = (ImportacaoTipoMatrizContratoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoTipoMatrizContratoBeanLocal.JNDI_NAME);
			retorno = importacaoTipoMatrizContratoBeanLocal.validarDados();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [validarDados]", e);
		}
		
		return retorno;
	}
	
	public Boolean liberarProducao() throws BusinessException {
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			ImportacaoTipoMatrizContratoBeanLocal importacaoTipoMatrizContratoBeanLocal = (ImportacaoTipoMatrizContratoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoTipoMatrizContratoBeanLocal.JNDI_NAME);
			retorno = importacaoTipoMatrizContratoBeanLocal.liberarProducao();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [liberarProducao]", e);
		}
		
		return retorno;
	}
	
	public void removerDadosAntigos() throws BusinessException {
		
		try {
			ImportacaoTipoMatrizContratoBeanLocal importacaoTipoMatrizContratoBeanLocal = (ImportacaoTipoMatrizContratoBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoTipoMatrizContratoBeanLocal.JNDI_NAME);
			importacaoTipoMatrizContratoBeanLocal.removerDadosAntigos();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [removerDadosAntigos]", e);
		}
	}
}
