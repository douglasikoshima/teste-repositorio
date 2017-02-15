package com.indracompany.catalogo.ejb.frequencia;

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

import com.indracompany.catalogo.dao.interfaces.FrequenciaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FrequenciaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Frequencia
 */
/**
 * @author Luiz
 *
 */
@Stateless(name = "FrequenciaBean", mappedName = "FrequenciaBean")
@Session(ejbName = "FrequenciaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FrequenciaBean implements FrequenciaBeanLocal {
	
	private static Logger logger = Logger.getLogger(FrequenciaBean.class);
	
	@EJB
	private FrequenciaDAO frequenciaDAO;
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.frequencia.FrequenciaBeanLocal#findAll()
	 */
	public List<FrequenciaTO> findAll() throws BusinessException {
		
		List<FrequenciaTO> list = null; 
		
		try {
			
			list = frequenciaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.frequencia.FrequenciaBeanLocal#createUpdateFrequencia(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public void createUpdateFrequencia(FrequenciaTO frequenciaTO) throws BusinessException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		try {
			
			frequenciaDAO.createUpdateFrequencia(frequenciaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.frequencia.FrequenciaBeanLocal#findById(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public FrequenciaTO findById(FrequenciaTO frequenciaTO) throws BusinessException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		FrequenciaTO frequenciaResultTO = null;
		
		try {
			frequenciaResultTO = frequenciaDAO.findById(frequenciaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return frequenciaResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.frequencia.FrequenciaBeanLocal#removeFrequencia(com.indracompany.catalogo.to.FrequenciaTO)
	 */
	public void removeFrequencia(FrequenciaTO frequenciaTO) throws BusinessException {
		logger.debug("frequenciaTO: " + frequenciaTO);
		
		try {
	
			frequenciaDAO.removeFrequencia(frequenciaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
