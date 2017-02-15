package com.indracompany.catalogo.ejb.contratomatrizofertalock;

import javax.ejb.EJB;
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

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Lock de Arquivo para Importação.
 */
@Stateless(name = "ContratoMatrizOfertaLockBean", mappedName = "ContratoMatrizOfertaLockBean")
@Session(ejbName = "ContratoMatrizOfertaLockBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ContratoMatrizOfertaLockBean implements ContratoMatrizOfertaLockBeanLocal {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaLockBean.class);
	
	@EJB
	private ContratoMatrizOfertaLockDAO contratoMatrizOfertaLockDAO;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertalock.ContratoMatrizOfertaLockBeanLocal#createUpdateContratoMatrizOfertaLock(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public void createUpdateContratoMatrizOfertaLock(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		try {
			contratoMatrizOfertaLockDAO.createUpdateContratoMatrizOfertaLock(contratoMatrizOfertaLockTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertalock.ContratoMatrizOfertaLockBeanLocal#existFileLockedByUser(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public Boolean existFileLockedByUser(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			retorno = contratoMatrizOfertaLockDAO.existFileLockedByUser(contratoMatrizOfertaLockTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		
		return retorno;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertalock.ContratoMatrizOfertaLockBeanLocal#findFileLockedCurrent(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public ContratoMatrizOfertaLockTO findFileLockedCurrent() throws BusinessException {
		logger.info("findFileLockedCurrent ");
		
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTOResult = null;
		
		try {
			contratoMatrizOfertaLockTOResult = contratoMatrizOfertaLockDAO.findFileLockedCurrent();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		
		return contratoMatrizOfertaLockTOResult;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertalock.ContratoMatrizOfertaLockBeanLocal#updateStatus(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public void updateStatusLiberar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws BusinessException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		try {
			contratoMatrizOfertaLockDAO.updateStatusLiberar(contratoMatrizOfertaLockTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		
	}
}
