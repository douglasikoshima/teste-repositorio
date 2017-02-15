package com.indracompany.catalogo.ejb.funcionalidade;

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

import com.indracompany.catalogo.dao.interfaces.FuncionalidadeDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FuncionalidadeTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Servico Interatividade
 */
@Stateless(name = "FuncionalidadeBean", mappedName = "FuncionalidadeBean")
@Session(ejbName = "FuncionalidadeBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FuncionalidadeBean implements FuncionalidadeBeanLocal {
	
	private static Logger logger = Logger.getLogger(FuncionalidadeBean.class);
	
	@EJB
	private FuncionalidadeDAO funcionalidadeDAO;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.funcionalidade.FuncionalidadeBeanLocal#findAll()
	 */
	public List<FuncionalidadeTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<FuncionalidadeTO> list = null;
		
		try {
			list = funcionalidadeDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
}
