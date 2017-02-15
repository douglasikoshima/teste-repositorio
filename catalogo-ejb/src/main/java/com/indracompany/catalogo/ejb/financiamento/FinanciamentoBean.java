package com.indracompany.catalogo.ejb.financiamento;

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
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

@Stateless(name = "FinanciamentoBean", mappedName = "FinanciamentoBean")
@Session(ejbName = "FinanciamentoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FinanciamentoBean implements FinanciamentoBeanLocal {
	
	private static Logger logger = Logger.getLogger(FinanciamentoBean.class);
	
	@EJB
	private CondicaoPagamentoDAO condicaoPagamentoDAO;
	
	public List<CondicaoPagamentoTO> search(CondicaoPagamentoTO condicaoPagamentoTO) throws BusinessException {
		logger.debug(String.format("financiamentoTO: %s", condicaoPagamentoTO));
		List<CondicaoPagamentoTO> list; 
		try {
			list = condicaoPagamentoDAO.search(condicaoPagamentoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public CondicaoPagamentoTO insertUpdate(CondicaoPagamentoTO condicaoPagamentoTO) throws BusinessException {
		try {
			condicaoPagamentoTO = condicaoPagamentoDAO.createUpdateCondicaoPagamento(condicaoPagamentoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return condicaoPagamentoTO;
	}
	
	public void remove(Integer id) throws BusinessException {
		try {
			condicaoPagamentoDAO.remove(id);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public CondicaoPagamentoTO findById(Integer id) throws BusinessException {
		CondicaoPagamentoTO condicaoPagamentoTO;
		try {
			condicaoPagamentoTO = condicaoPagamentoDAO.findById(id);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return condicaoPagamentoTO;
	}
    
    public CondicaoPagamentoTO finByName(String nmCondicaoPagamento) throws BusinessException {
        CondicaoPagamentoTO condicaoPagamentoTO;
        try {
            condicaoPagamentoTO = condicaoPagamentoDAO.findByName(nmCondicaoPagamento);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return condicaoPagamentoTO;        
    }
}
