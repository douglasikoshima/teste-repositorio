package br.com.vivo.catalogoPRS.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.ejb.gerenciadorregrasconfiguracao.GerenciadorRegrasConfiguracaoBeanLocal;
import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;
import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public class GerenciadorRegrasConfiguracaoDelegate {
	private static Logger logger = Logger.getLogger(SolicitacaoComercial.class);
	
	public List<String> buscarNmIndicadorComercialPorTipoFiltro(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO){
		List<String> result = new ArrayList<String>();
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.buscarNmIndicadorComercialPorTipoFiltro(gerenciadorRegrasConfiguracaoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [buscarNmIndicadorComercialPorTipoFiltro]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	public List<RegraPrioridadeAltaTO> buscarRegraPrioridadeAltaTO(){
		List<RegraPrioridadeAltaTO> result = new ArrayList<RegraPrioridadeAltaTO>();
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.buscarRegraPrioridadeAltaTO();
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [buscarRegraPrioridadeAltaTO]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	public List<IndicadorComercialDocumentoTO> buscarTodosIndicadoresDocumento(){
		List<IndicadorComercialDocumentoTO> result = new ArrayList<IndicadorComercialDocumentoTO>();
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.buscarTodosIndicadoresDocumento();
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [buscarTodosIndicadoresDocumento]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	public List<TipoDocumentoTO> buscarTodosTiposDocumento(){
		List<TipoDocumentoTO> result = new ArrayList<TipoDocumentoTO>();
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.buscarTodosTiposDocumento();
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [buscarTodosTiposDocumento]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}

	public IndicadorComercialDocumentoTO buscarTipoDocumentoPorIndicador(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO){
		IndicadorComercialDocumentoTO result = null;
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.buscarTipoDocumentoPorIndicador(indicadorComercialDocumentoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [buscarTipoDocumentoPorIndicador]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisar(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO){
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();
		
		return result;
	}
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisarPorIdCanalAtendimento(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO){
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();
		
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			result = gerenciadorRegrasConfiguracaoBeanLocal.pesquisarPorIdCanalAtendimento(gerenciadorRegrasConfiguracaoTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarPorIdCanalAtendimento]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
		
		return result;
	}
	
	public void salvar(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO){
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			gerenciadorRegrasConfiguracaoBeanLocal.salvar(gerenciadorRegrasConfiguracaoTO); 
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [salvar(GerenciadorRegrasConfiguracaoTO)]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
	}
	
	
	public void salvar(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO){
		try {
			GerenciadorRegrasConfiguracaoBeanLocal gerenciadorRegrasConfiguracaoBeanLocal = (GerenciadorRegrasConfiguracaoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GerenciadorRegrasConfiguracaoBeanLocal.JNDI_NAME);
			gerenciadorRegrasConfiguracaoBeanLocal.salvar(indicadorComercialDocumentoTO); 
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [salvar(IndicadorComercialDocumentoTO)]");
		} catch(BusinessException e){
			logger.error(e.getMessage());
		}
	}
}
