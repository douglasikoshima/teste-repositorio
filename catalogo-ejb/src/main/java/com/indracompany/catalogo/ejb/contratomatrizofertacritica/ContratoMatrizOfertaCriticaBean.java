package com.indracompany.catalogo.ejb.contratomatrizofertacritica;

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

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO;
import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaCriticaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Ação
 */
@Stateless(name = "ContratoMatrizOfertaCriticaBean", mappedName = "ContratoMatrizOfertaCriticaBean")
@Session(ejbName = "ContratoMatrizOfertaCriticaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.SUPPORTS, idleTimeoutSeconds = "300", remoteClientTimeout="0")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ContratoMatrizOfertaCriticaBean implements ContratoMatrizOfertaCriticaBeanLocal {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaCriticaBean.class);
	
	@EJB
	private ContratoMatrizOfertaCriticaDAO contratoMatrizOfertaCriticaDAO;
	
	@EJB
	private ContratoMatrizOfertaDAO contratoMatrizOfertaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertacritica.ContratoMatrizOfertaCriticaBeanLocal#findAllCriticas()
	 */
	public List<ContratoMatrizOfertaCriticaTO> findAllCriticas() throws BusinessException {
		logger.info("findAllCriticas");
		
		List<ContratoMatrizOfertaCriticaTO> list = null; 
		
		try {
			list = contratoMatrizOfertaCriticaDAO.findAllCriticas();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.contratomatrizofertacritica.ContratoMatrizOfertaCriticaBeanLocal#exportContratoMatrizOfertaCritica()
	 */
	public List<String> exportContratoMatrizOfertaCritica() throws BusinessException {
		logger.info("exportContratoMatrizOfertaCritica");
		
		List<String> list = null; 
		
		try {
			list = contratoMatrizOfertaDAO.exportContratoMatrizOfertaCriticas();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
		
	}
}
