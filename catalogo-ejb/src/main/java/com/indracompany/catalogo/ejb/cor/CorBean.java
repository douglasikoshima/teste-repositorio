package com.indracompany.catalogo.ejb.cor;

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

import com.indracompany.catalogo.dao.interfaces.CorDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CorTO;

/**
 * @author 
 * 
 * EJB responsável por realizar as funções de Cor
 */
@Stateless(name = "CorBean", mappedName = "CorBean")
@Session(ejbName = "CorBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CorBean implements CorBeanLocal {
	
	private static Logger logger = Logger.getLogger(CorBean.class);
	
	@EJB
	private CorDAO corDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.CorBeanLocal#searchCor(com.indracompany.catalogo.to.CorTO)
	 */
	public List<CorTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<CorTO> list = null;
		
		try {
			list = corDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.CorBeanLocal#createUpdateCor(com.indracompany.catalogo.to.CorTO)
	 */
	public void createUpdateCor(CorTO corTO) throws BusinessException {
		logger.debug("corTO: " + corTO);
		
		try {
			corDAO.createUpdateCor(corTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.cor.CorBeanLocal#findById(com.indracompany.catalogo.to.CorTO)
	 */
	public CorTO findById(CorTO corTO) throws BusinessException {
		logger.debug("corTO: " + corTO);
		
		CorTO corResultTO = null;
		
		try {
			corResultTO = corDAO.findById(corTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return corResultTO;
	}
}
