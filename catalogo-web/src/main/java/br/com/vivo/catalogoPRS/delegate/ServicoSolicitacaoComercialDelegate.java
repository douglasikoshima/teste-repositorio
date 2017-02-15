package br.com.vivo.catalogoPRS.delegate;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.ejb.servicosolicitacaocomercial.ServicoSolicitacaoComercialBeanLocal;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public class ServicoSolicitacaoComercialDelegate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6582099756613380366L;
	private static Logger logger = Logger.getLogger(SolicitacaoComercial.class);
	
	public List<ServicoSolicitacaoComercialTO> search(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO){
		
		List<ServicoSolicitacaoComercialTO> result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.searchSolicitacaoComercial(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [search]");
		} catch(BusinessException e){
			logger.error(e);
		} 
		
		return result; 
	}
	
	public ServicoSolicitacaoComercialTO findSistemaByIdServico(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO){
		
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.findSistemaByIdServico(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findSistemaByIdServico]");
		} catch(BusinessException e){
			logger.error(e);
		} 
		
		return result; 
	}
	
	
	public void switchDisponibilidadeSlctCmrl(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO){
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			 servicoSolicitacaoComercialBeanLocal.switchDisponibilidadeSlctCmrl(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [switchDisponibilidadeSlctCmrl]");
		} catch(BusinessException e){
			logger.error(e);
		}
	}

	public ServicoSolicitacaoComercialTO switchDisponibilidadeCanalVendaSlctCmrl(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO){
		ServicoSolicitacaoComercialTO result = null;
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.switchDisponibilidadeCanalVendaSlctCmrl(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [switchDisponibilidadeCanalVendaSlctCmrl]");
		} catch(BusinessException e){
			logger.error(e);
		}
		return result;
	}

	public void switchDisponibilidadePlMinutosAreaConcorrencia(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO){
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			 servicoSolicitacaoComercialBeanLocal.switchDisponibilidadeSlctCmrl(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [switchDisponibilidadePlMinutosAreaConcorrencia]");
		} catch(BusinessException e){
			logger.error(e);
		}
	}

	public ServicoSolicitacaoComercialTO findCanalVendaBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialTO = servicoSolicitacaoComercialBeanLocal.findCanalVendaBySolicitacaoComercial(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findCanalVendaBySolicitacaoComercial]");
		} catch(BusinessException e){
			logger.error(e);
		} 
		
		return servicoSolicitacaoComercialTO; 

	}
	
	public ServicoSolicitacaoComercialTO getCombos() {
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = null;
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialTO = servicoSolicitacaoComercialBeanLocal.getCombos();
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [getCombos]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return servicoSolicitacaoComercialTO; 

	}
	
	public ServicoSolicitacaoComercialTO searchEncargoBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null;
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.searchEncargoBySolicitacaoComercial(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [searchEncargoBySolicitacaoComercial]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result; 
	}
	
	public void createUpdateCanalVendaSlctCmrlList(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialBeanLocal.createUpdateCanalVendaSlctCmrlList(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCanalVendaSlctCmrlList]");
		} catch(BusinessException e){
			logger.error(e);
		}
	}	
	
	public ServicoSolicitacaoComercialTO findDispArConcPlMinByIdCnVendaSlct(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.findDispArConcPlMinByIdCnVendaSlct(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findDispArConcPlMinByIdCnVendaSlct]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result;
	}
	
	public ServicoSolicitacaoComercialTO createUpdateDispArConcPlMinByIdCnVendaSlct(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.createUpdateDispArConcPlMinByIdCnVendaSlct(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [createUpdateDispArConcPlMinByIdCnVendaSlct]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result;
	}
	
	public ServicoSolicitacaoComercialTO searchCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.searchCndcPgtoEncargo(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [searchCndcPgtoEncargo]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result;
	}
	
	public ServicoSolicitacaoComercialTO findDispArConcPlMinByIdCndcPgtoEnc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.findDispArConcPlMinByIdCndcPgtoEnc(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findDispArConcPlMinByIdCndcPgtoEnc]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result;
	}

	public ServicoSolicitacaoComercialTO findCanalVendaCndcPgtoByIdEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = null; 
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.findCanalVendaCndcPgtoByIdEncargo(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [findCanalVendaCndcPgtoByIdEncargo]");
		} catch(BusinessException e){
			logger.error(e);
		}
		
		return result;
	}
	
	public void createUpdateDispArConcPlMinByIdCndcPgtoEnc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialBeanLocal.createUpdateDispArConcPlMinByIdCndcPgtoEnc(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [createUpdateDispArConcPlMinByIdCndcPgtoEnc]");
		} catch(BusinessException e){
			logger.error(e);
		}
	}
	
	public ServicoSolicitacaoComercialTO switchDisponibilidadeCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			result = servicoSolicitacaoComercialBeanLocal.switchDisponibilidadeCndcPgtoEncargo(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [switchDisponibilidadeCndcPgtoEncargo]");
		} catch(BusinessException e) {
			logger.error(e);
		}
		
		return result; 
	}
	
	public void createUpdateCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException{
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialBeanLocal.createUpdateCndcPgtoEncargo(servicoSolicitacaoComercialTO);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [createUpdateCndcPgtoEncargo]");
		}
	}

	public void saveSolicitacaoOfertaClienteInadimplente(List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOList){
		
		try{
			ServicoSolicitacaoComercialBeanLocal servicoSolicitacaoComercialBeanLocal = (ServicoSolicitacaoComercialBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoSolicitacaoComercialBeanLocal.JNDI_NAME);
			servicoSolicitacaoComercialBeanLocal.saveSolicitacaoOfertaClienteInadimplente(servicoSolicitacaoComercialTOList);
		} catch(ServiceLocatorException e){
			throw new EJBException("Erro ao realizar o lookup de [saveSolicitacaoOfertaClienteInadimplente]");
		} catch(BusinessException e) {
			logger.error(e);
		}
	}
}
