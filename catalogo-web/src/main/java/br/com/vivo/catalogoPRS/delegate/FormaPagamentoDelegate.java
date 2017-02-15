package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal;
import com.indracompany.catalogo.to.FormaPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class FormaPagamentoDelegate {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoDelegate.class);
	
	/**
	 * @return
	 */
	public List<FormaPagamentoTO> findAll() {
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			formaPagamentoTOList = formaPagamentoBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return formaPagamentoTOList;
	}
	
	public List<FormaPagamentoTO> searchFormaPagamento(FormaPagamentoTO formaPagamentoTO) {
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			formaPagamentoTOList = formaPagamentoBeanLocal.searchFormaPagamento(formaPagamentoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [searchFormaPagamento]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return formaPagamentoTOList;
	}
	
	public FormaPagamentoTO findById(FormaPagamentoTO formaPagamentoTO) {
		
		FormaPagamentoTO formaPagamentoResultTO = null;
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			formaPagamentoResultTO = formaPagamentoBeanLocal.findById(formaPagamentoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return formaPagamentoResultTO;
	}
	
	public FormaPagamentoTO createUpdateFormaPagamento(FormaPagamentoTO formaPagamentoTO) {
		
		FormaPagamentoTO FormaPagamentoResultTO = null;
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			FormaPagamentoResultTO = formaPagamentoBeanLocal.createUpdateFormaPagamento(formaPagamentoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateFormaPagamento]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return FormaPagamentoResultTO;
	}
	
	public void removeFormaPagamento(FormaPagamentoTO formaPagamentoTO) {
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			formaPagamentoBeanLocal.removeFormaPagamento(formaPagamentoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateFormaPagamento]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
	
	public void createUpdateFormaPagtoCanalParam(FormaPagamentoTO formaPagamentoTO) {
		
		try {
			FormaPagamentoBeanLocal formaPagamentoBeanLocal = (FormaPagamentoBeanLocal) ServiceLocator.getInstance().getEJBLocal(FormaPagamentoBeanLocal.JNDI_NAME);
			formaPagamentoBeanLocal.createUpdateFormaPagtoCanalParam(formaPagamentoTO);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [createUpdateFormaPagtoCanalParam]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
	}
}
