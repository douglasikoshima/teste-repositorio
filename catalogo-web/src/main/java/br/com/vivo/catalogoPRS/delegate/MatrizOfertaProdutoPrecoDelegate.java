package br.com.vivo.catalogoPRS.delegate;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.MatrizOfertaItemPreco;
import com.indracompany.catalogo.ejb.matrizofertaprodutopreco.MatrizOfertaProdutoPrecoBeanLocal;
import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;
import com.indracompany.catalogo.to.OfertaSAPTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

public class MatrizOfertaProdutoPrecoDelegate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1180964602480705668L;
	private static Logger logger = Logger.getLogger(MatrizOfertaItemPreco.class);
	
	public List<MatrizOfertaProdutoPrecoTO> search(MatrizOfertaProdutoPrecoTO matrizOfertaProdutoPrecoTO){
		
		List<MatrizOfertaProdutoPrecoTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.search(matrizOfertaProdutoPrecoTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [search]");
		} catch(BusinessException e){
			logger.error(e);
		} 
		
		return result; 
	}
	
	public List<AcaoTO> findAllAcaoTO(){
		List<AcaoTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.findAllAcaoTO();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAllAcaoTO]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}
	
	public List<CanalTO> findAllCanalTO(){
		List<CanalTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.findAllCanalTO();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAllCanalTO]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}
	
	public List<CanalAtendimentoTO> findAllCanalAtendimentoTO(){
		List<CanalAtendimentoTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.findAllCanalAtendimentoTO();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAllCanalAtendimentoTO]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}
	
	public List<OfertaSAPTO> findAllOfertaSAPTO(){
		List<OfertaSAPTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.findAllOfertaSAPTO();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAllOfertaSAPTO]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}

	public List<OrganizacaoVendaTO> findAllOrganizacaoVendaTO(){
		List<OrganizacaoVendaTO> result = null; 
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			result = matrizOfertaProdutoPrecoBeanLocal.findAllOrganizacaoVendaTO();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findAllOrganizacaoVendaTO]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}

	
	public void removePrecoList(List<Long> idList, String userName){
		
		try{
			MatrizOfertaProdutoPrecoBeanLocal matrizOfertaProdutoPrecoBeanLocal = (MatrizOfertaProdutoPrecoBeanLocal) ServiceLocator.getInstance().getEJBLocal(MatrizOfertaProdutoPrecoBeanLocal.JNDI_NAME);
			matrizOfertaProdutoPrecoBeanLocal.removePrecoList(idList, userName);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [removePrecoList]");
		} catch(BusinessException e){
			logger.error(e);
		}
	}
}
