package com.indracompany.catalogo.ejb.tipofrequencia;

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

import com.indracompany.catalogo.dao.interfaces.TipoFrequenciaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoFrequenciaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Frequencia
 */
/**
 * @author Luiz
 *
 */
@Stateless(name = "TipoFrequenciaBean", mappedName = "TipoFrequenciaBean")
@Session(ejbName = "TipoFrequenciaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TipoFrequenciaBean implements TipoFrequenciaBeanLocal {
	
	private static Logger logger = Logger.getLogger(TipoFrequenciaBean.class);
	
	@EJB
	private TipoFrequenciaDAO tipoFrequenciaDAO;
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipofrequencia.TipoFrequenciaBeanLocal#findAll()
	 */
	public List<TipoFrequenciaTO> findAll() throws BusinessException {
		
		List<TipoFrequenciaTO> list = null; 
		
		try {
			
			list = tipoFrequenciaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipofrequencia.TipoFrequenciaBeanLocal#createUpdateTipoFrequencia(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public void createUpdateTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		try {
			
			if (tipoFrequenciaTO.getIdTipoFrequencia() == null ) {
				if (tipoFrequenciaDAO.existTipoFrequencia(tipoFrequenciaTO)) {
					throw new BusinessException("Opera&ccedil;&atilde;o n&atilde;o permitida. Esse Tipo de Frequ&ecirc;ncia j&aacute; existe.");
				}
			}
			
			if (tipoFrequenciaDAO.existTipoFrequencia(tipoFrequenciaTO)) {
				throw new BusinessException("");
			}
			
			tipoFrequenciaDAO.createUpdateTipoFrequencia(tipoFrequenciaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipofrequencia.TipoFrequenciaBeanLocal#findById(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public TipoFrequenciaTO findById(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		TipoFrequenciaTO tipoFrequenciaTOResultTO = null;
		
		try {
			tipoFrequenciaTOResultTO = tipoFrequenciaDAO.findById(tipoFrequenciaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoFrequenciaTOResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipofrequencia.TipoFrequenciaBeanLocal#removeTipoFrequencia(com.indracompany.catalogo.to.TipoFrequenciaTO)
	 */
	public void removeTipoFrequencia(TipoFrequenciaTO tipoFrequenciaTO) throws BusinessException {
		logger.debug("tipoFrequenciaTO: " + tipoFrequenciaTO);
		
		try {
	
			tipoFrequenciaDAO.removeTipoFrequencia(tipoFrequenciaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
