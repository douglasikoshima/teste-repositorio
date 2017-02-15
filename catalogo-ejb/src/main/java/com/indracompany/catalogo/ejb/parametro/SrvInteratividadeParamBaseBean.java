package com.indracompany.catalogo.ejb.parametro;

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

import com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParamBaseDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SrvInteratividadeParamBaseTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Parametro.
 */
@Stateless(name = "SrvInteratividadeParamBaseBean", mappedName = "SrvInteratividadeParamBaseBean")
@Session(ejbName = "SrvInteratividadeParamBaseBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class SrvInteratividadeParamBaseBean implements SrvInteratividadeParamBaseBeanLocal {
	
	private static Logger logger = Logger.getLogger(SrvInteratividadeParamBaseBean.class);
	
	@EJB
	private SrvInteratividadeParamBaseDAO parametroDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.parametro.ParametroBeanLocal#findAll()
	 */
	public List<SrvInteratividadeParamBaseTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<SrvInteratividadeParamBaseTO> plataformaList = null;
		
		try {
			plataformaList = parametroDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return plataformaList;
	}
	
}
