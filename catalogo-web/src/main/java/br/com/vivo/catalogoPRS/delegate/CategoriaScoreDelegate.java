package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.CategoriaScoreTOBuilder;
import com.indracompany.catalogo.ejb.categoriascore.CategoriaScoreBeanLocal;
import com.indracompany.catalogo.to.CategoriaScoreTO;

public class CategoriaScoreDelegate {
	
	private static Logger logger = Logger.getLogger(ServicoInteratividadeDelegate.class);	
	
	public void createUpdateCategoriaScore(CategoriaScoreTO categoriaScoreTO) {
		
		try{
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreBeanLocal.createUpdateCategoriaScore(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}
 	
	public List<CategoriaScoreTO> searchCategoriaScore(CategoriaScoreTO categoriaScoreTO) {
		
		List<CategoriaScoreTO> categoriaScoreTOList = null;
		
		try {
			
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreTOList = categoriaScoreBeanLocal.searchCategoriaScore(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [searchCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return categoriaScoreTOList;
	}
	
	
	public CategoriaScoreTO findById(CategoriaScoreTO categoriaScoreTO) {

		CategoriaScoreTO categoriaScoreTO2 = new CategoriaScoreTO(); 
		
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreTO2 = categoriaScoreBeanLocal.findById(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAll]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return categoriaScoreTO2;
	}
 
	public List<CategoriaScoreTO> findAll() {
		List<CategoriaScoreTO> categoriaScoreTOList = null;
		
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreTOList = categoriaScoreBeanLocal.findAll();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAll]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return categoriaScoreTOList;
	}
	
	public List<CategoriaScoreTO> findAll(CategoriaScoreTOBuilder categoriaScoreTOBuilder) {
		List<CategoriaScoreTO> categoriaScoreTOList = null;
		
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreTOList = categoriaScoreBeanLocal.findAll(categoriaScoreTOBuilder);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAll]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return categoriaScoreTOList;
	}
	
	public void removeCategoriaScore(CategoriaScoreTO categoriaScoreTO){
		
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreBeanLocal.removeCategoriaScore(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removeCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}
	
	public void removeAssociation(CategoriaScoreTO categoriaScoreTO){

		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreBeanLocal.removeAssociation(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removeAssociation]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
	}
	
	public List<String> findAssociation(CategoriaScoreTO categoriaScoreTO){
		
		List<String> associationList = new ArrayList<String>();
		
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			associationList = categoriaScoreBeanLocal.findAssociation(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAssociation]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return associationList;
	}
	
	public boolean existCdCategoriaScore(CategoriaScoreTO categoriaScoreTO){
		boolean b = false;
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			b = categoriaScoreBeanLocal.existCdCategoriaScore(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [existCdCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		return b;
	}
	
	public CategoriaScoreTO findByCdCategoria(CategoriaScoreTO categoriaScoreTO){
		try {
			CategoriaScoreBeanLocal categoriaScoreBeanLocal = (CategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaScoreBeanLocal.JNDI_NAME);
			categoriaScoreTO = categoriaScoreBeanLocal.findByCdCategoria(categoriaScoreTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findByCdCategoriaScore]",e);
		} catch(BusinessException e){
			logger.error(e);
		}
		return categoriaScoreTO;
	}
	
}
