package com.indracompany.catalogo.ejb.formapagamento;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoDAO;
import com.indracompany.catalogo.dao.interfaces.DescontoCondPagtoDAO;
import com.indracompany.catalogo.dao.interfaces.FormaPagamentoBandeiraDAO;
import com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO;
import com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalAtndParamDAO;
import com.indracompany.catalogo.dao.interfaces.FormaPagtoCanalParamDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;
import com.indracompany.catalogo.to.FormaPagamentoTO;
import com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO;
import com.indracompany.catalogo.to.FormaPagtoCanalParamTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Forma Pagamento
 */
@Stateless(name = "FormaPagamentoBean", mappedName = "FormaPagamentoBean")
@Session(ejbName = "FormaPagamentoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FormaPagamentoBean implements FormaPagamentoBeanLocal {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoBean.class);
	
	@EJB
	private FormaPagamentoDAO formaPagamentoDAO;
	
	@EJB
	private FormaPagamentoBandeiraDAO formaPagamentoBandeiraDAO;
	
	@EJB
	private CondicaoPagamentoDAO condicaoPagamentoDAO;
	
	@EJB
	private FormaPagtoCanalParamDAO formaPagtoCanalParamDAO;
	
	@EJB
	private FormaPagtoCanalAtndParamDAO formaPagtoCanalAtndParamDAO;
	
	@EJB
	private DescontoCondPagtoDAO descontoCondPagtoDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.PlataformaBeanLocal#findAll()
	 */
	public List<FormaPagamentoTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		
		try {
			formaPagamentoTOList = formaPagamentoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return formaPagamentoTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal#searchFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public List<FormaPagamentoTO> searchFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException {
		logger.debug("searchFormaPagamento" + formaPagamentoTO);
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		
		try {
			formaPagamentoTOList = formaPagamentoDAO.searchFormaPagamento(formaPagamentoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return formaPagamentoTOList;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal#findById(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public FormaPagamentoTO findById(FormaPagamentoTO formaPagamentoTO) throws BusinessException {
		logger.debug("findById" + formaPagamentoTO);
		
		FormaPagamentoTO formaPagamentoResultTO = null;
		
		try {
			formaPagamentoResultTO = formaPagamentoDAO.findById(formaPagamentoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return formaPagamentoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal#createUpdateFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public FormaPagamentoTO createUpdateFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		FormaPagamentoTO formaPagamentoResultTO = null;
		
		try {
			formaPagamentoResultTO = formaPagamentoDAO.createUpdateFormaPagamento(formaPagamentoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return formaPagamentoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal#removeFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public void removeFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws BusinessException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		try {
			
			formaPagamentoDAO.removeFormaPagtoCanalAtndParam(formaPagamentoTO);
			formaPagamentoDAO.removeFormaPagtoCanalParam(formaPagamentoTO);
			
			CondicaoPagamentoTO condicaoPagamentoTO = new CondicaoPagamentoTO();
			condicaoPagamentoTO.setFormaPagamentoTO(formaPagamentoTO);
			
			descontoCondPagtoDAO.removeDescontoCondPagtoByFormaPagamento(formaPagamentoTO.getIdFormaPagamento());
			condicaoPagamentoDAO.removeTipoProdutoCondPagto(condicaoPagamentoTO);
			condicaoPagamentoDAO.removeCondicaoPagamento(condicaoPagamentoTO);
			
			FormaPagamentoBandeiraTO formaPagamentoBandeiraTO = new FormaPagamentoBandeiraTO();
			formaPagamentoBandeiraTO.setIdFormaPagamento(formaPagamentoTO.getIdFormaPagamento());
			
			formaPagamentoBandeiraDAO.removeFormaPagamentoBandeira(formaPagamentoBandeiraTO);
			formaPagamentoDAO.removeFormaPagamento(formaPagamentoTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.formapagamento.FormaPagamentoBeanLocal#createUpdateFormaPagtoCanalParam(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public void createUpdateFormaPagtoCanalParam(FormaPagamentoTO formaPagamentoTO) throws BusinessException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		try {
			
			formaPagamentoDAO.removeFormaPagtoCanalAtndParam(formaPagamentoTO);
			formaPagamentoDAO.removeFormaPagtoCanalParam(formaPagamentoTO);
			
			FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO = formaPagamentoTO.getFormaPagtoCanalParamTO().getFormaPagtoCanalAtndParamTO();
			formaPagamentoTO.getFormaPagtoCanalParamTO().setFormaPagtoCanalAtndParamTO(null);
			FormaPagtoCanalParamTO formaPagtoCanalParamTO = formaPagtoCanalParamDAO.createUpdateFormaPagtoCanalParam(formaPagamentoTO.getFormaPagtoCanalParamTO());
			
			formaPagtoCanalAtndParamTO.setFormaPagtoCanalParamTO(formaPagtoCanalParamTO);
			formaPagtoCanalAtndParamDAO.createUpdateFormaPagtoCanalParam(formaPagtoCanalAtndParamTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
