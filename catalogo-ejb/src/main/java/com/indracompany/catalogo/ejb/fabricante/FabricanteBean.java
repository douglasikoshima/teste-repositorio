package com.indracompany.catalogo.ejb.fabricante;

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

import com.indracompany.catalogo.dao.interfaces.FabricanteDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FabricanteTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Plataforma
 */
@Stateless(name = "FabricanteBean", mappedName = "FabricanteBean")
@Session(ejbName = "FabricanteBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FabricanteBean implements FabricanteBeanLocal {
	
	private static Logger logger = Logger.getLogger(FabricanteBean.class);
	
	@EJB
	private FabricanteDAO fabricanteDAO;
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.fabricante.FabricanteBeanLocal#findAll()
	 */
	public List<FabricanteTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			fabricanteTOList = fabricanteDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return fabricanteTOList;
	}
	
	/**
	 * @param fabricanteTO
	 * @return
	 * @throws BusinessException
	 */
	public List<FabricanteTO> search(FabricanteTO fabricanteTO) throws BusinessException {
		logger.debug("fabricanteTO: " + fabricanteTO);
		
		List<FabricanteTO> fabricanteTOList = null;
		
		try {
			fabricanteTOList = fabricanteDAO.search(fabricanteTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return fabricanteTOList;
	}
}
