package com.indracompany.catalogo.ejb.fornecedorsca;

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

import com.indracompany.catalogo.dao.interfaces.FornecedorSCADAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FornecedorSCATO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Fornecedor da SCA
 */
@Stateless(name = "FornecedorSCABean", mappedName = "FornecedorSCABean")
@Session(ejbName = "FornecedorSCABean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FornecedorSCABean implements FornecedorSCABeanLocal {
	
	private static Logger logger = Logger.getLogger(FornecedorSCABean.class);
	
	@EJB
	private FornecedorSCADAO fornecedorSCADAO;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.fornecedorsca.FornecedorSCABeanLocal#findAll()
	 */
	public List<FornecedorSCATO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<FornecedorSCATO> list = null;
		
		try {
			list = fornecedorSCADAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return list;
	}
	
}
