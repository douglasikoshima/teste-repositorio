package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.ejb.servicointeratividade.ServicoInteratividadeBeanLocal;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class ServicoInteratividadeDelegate {
	
	private static Logger logger = Logger.getLogger(ServicoInteratividadeDelegate.class);
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 */
	public List<ServicoInteratividadeTO> searchServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		
		List<ServicoInteratividadeTO> pacoteBonusList = null;
		
		try {
			ServicoInteratividadeBeanLocal servicoInteratividadeBeanLocal = (ServicoInteratividadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoInteratividadeBeanLocal.JNDI_NAME);
			pacoteBonusList = servicoInteratividadeBeanLocal.searchServicoInteratividade(servicoInteratividadeTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchPacoteBonus]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return pacoteBonusList;
	}
	
	/**
	 * @param servicoInteratividadeTO
	 */
	public ServicoInteratividadeTO createUpdateServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		
		ServicoInteratividadeTO to = new ServicoInteratividadeTO();
		try {
			ServicoInteratividadeBeanLocal servicoInteratividadeBeanLocal = (ServicoInteratividadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoInteratividadeBeanLocal.JNDI_NAME);
			to = servicoInteratividadeBeanLocal.createUpdateServicoInteratividade(servicoInteratividadeTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdatePacoteBonus]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return to;
	}
	
	public ServicoInteratividade createServicointeratividade (ServicoInteratividade si) {
		
		ServicoInteratividade createdServicointeratividade = new ServicoInteratividade();
		
		try {
			ServicoInteratividadeBeanLocal servicoInteratividadeBeanLocal = (ServicoInteratividadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoInteratividadeBeanLocal.JNDI_NAME);
			createdServicointeratividade = servicoInteratividadeBeanLocal.createServicointeratividade(si);
			
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createServicointeratividade]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return createdServicointeratividade;
	}
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 */
	public ServicoInteratividadeTO findById(ServicoInteratividadeTO servicoInteratividadeTO) {
	
		ServicoInteratividadeTO pacoteBonusTOResult = null;
		
		try {
			ServicoInteratividadeBeanLocal pacoteBonusBeanLocal = (ServicoInteratividadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoInteratividadeBeanLocal.JNDI_NAME);
			pacoteBonusTOResult = pacoteBonusBeanLocal.findById(servicoInteratividadeTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return pacoteBonusTOResult;
	}
	
	public Integer validarServicointeratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		
		Integer idServicointeratividade = null;
		try {
			ServicoInteratividadeBeanLocal pacoteBonusBeanLocal = (ServicoInteratividadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoInteratividadeBeanLocal.JNDI_NAME);
			idServicointeratividade = pacoteBonusBeanLocal.validarServicointeratividade(servicoInteratividadeTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return idServicointeratividade; 
		
	}
	
}
