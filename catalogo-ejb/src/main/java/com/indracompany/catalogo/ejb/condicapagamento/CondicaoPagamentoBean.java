package com.indracompany.catalogo.ejb.condicapagamento;

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
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;
import com.indracompany.catalogo.to.DescontoCondPagtoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Condicao Pagamento
 */
@Stateless(name = "CondicaoPagamentoBean", mappedName = "CondicaoPagamentoBean")
@Session(ejbName = "CondicaoPagamentoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CondicaoPagamentoBean implements CondicaoPagamentoBeanLocal {
	
	private static Logger logger = Logger.getLogger(CondicaoPagamentoBean.class);
	
	@EJB
	private CondicaoPagamentoDAO condicaoPagamentoDAO;
	
	@EJB
	private DescontoCondPagtoDAO descontoCondPagtoDAO;
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.condicapagamento.CondicaoPagamentoBeanLocal#createUpdateCondicaoPagamentoList(java.util.List)
	 */
	public void createUpdateCondicaoPagamentoList(List<CondicaoPagamentoTO> condicaoPagamentoTOList) throws BusinessException {
		logger.debug("condicaoPagamentoTO: " + condicaoPagamentoTOList);
		
		try {
			descontoCondPagtoDAO.removeDescontoCondPagtoByFormaPagamento(condicaoPagamentoTOList.get(0).getFormaPagamentoTO().getIdFormaPagamento());
			condicaoPagamentoDAO.removeTipoProdutoCondPagto(condicaoPagamentoTOList.get(0));
			condicaoPagamentoDAO.removeCondicaoPagamento(condicaoPagamentoTOList.get(0));
			
			DescontoCondPagtoTO descontoCondPagtoTO = null;
			
			for (CondicaoPagamentoTO condicaoPagamentoTO : condicaoPagamentoTOList) {
				descontoCondPagtoTO = condicaoPagamentoTO.getDescontoCondPagtoTO();
				condicaoPagamentoTO.setDescontoCondPagtoTO(null);
				condicaoPagamentoTO = condicaoPagamentoDAO.createUpdateCondicaoPagamento(condicaoPagamentoTO);
				descontoCondPagtoTO.setCondicaoPagamentoTO(condicaoPagamentoTO);
				descontoCondPagtoDAO.createUpdateDescontoCondPagto(descontoCondPagtoTO);
			}
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
}
