package com.indracompany.catalogo.ejb.canal;

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

import com.indracompany.catalogo.dao.interfaces.CanalDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Canal
 */
@Stateless(name = "CanalBean", mappedName = "CanalBean")
@Session(ejbName = "CanalBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CanalBean implements CanalBeanLocal {
	
	private static Logger logger = Logger.getLogger(CanalBean.class);
	
	@EJB
	private CanalDAO canalDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.canal.CanalBeanLocal#findAll()
	 */
	public List<CanalTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<CanalTO> canalTOList = null;
		
		try {
			canalTOList = canalDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return canalTOList;
	}
	
}
