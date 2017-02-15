package com.indracompany.catalogo.ejb.caracteristica;

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

import com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Caracteristica
 */
@Stateless(name = "CaracteristicaBean", mappedName = "CaracteristicaBean")
@Session(ejbName = "CaracteristicaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CaracteristicaBean implements CaracteristicaBeanLocal {
	
	private static Logger logger = Logger.getLogger(CaracteristicaBean.class);
	
	@EJB
	private CaracteristicaDAO caracteristicaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.caracteristica.CaracteristicaBeanLocal#searchCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public List<CaracteristicaTO> searchCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		List<CaracteristicaTO> list = null; 
		
		try {
			list = caracteristicaDAO.searchCaracteristica(caracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.caracteristica.CaracteristicaBeanLocal#createUpdateCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public CaracteristicaTO createUpdateCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		CaracteristicaTO caracteristicaRetornoTO = null;
		
		try {
			
			CaracteristicaTO caracteristicaResultTO = caracteristicaDAO.findByName(caracteristicaTO);
			if (caracteristicaResultTO != null && !caracteristicaResultTO.getIdCaracteristica().equals(caracteristicaTO.getIdCaracteristica())) {
				throw new BusinessException("Opera&ccedil;&atilde;o n&atilde;o permitida. A Caracter&iacute;stica j&aacute; existe.");
			}

			caracteristicaRetornoTO = caracteristicaDAO.createUpdateCaracteristica(caracteristicaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		 
		return caracteristicaRetornoTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.caracteristica.CaracteristicaBeanLocal#findById(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public CaracteristicaTO findById(CaracteristicaTO caracteristicaTO) throws BusinessException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		CaracteristicaTO caracteristicaResultTO = null;
		
		try {
			caracteristicaResultTO = caracteristicaDAO.findById(caracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return caracteristicaResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.caracteristica.CaracteristicaBeanLocal#removeCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public void removeCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		try {
			caracteristicaDAO.removeCaracteristica(caracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public void searchCaracteristica(PesquisaIdNomeTO pesquisaTO) throws BusinessException{
		logger.debug("searchCaracteristica");
		try {
			caracteristicaDAO.searchCaracteristica(pesquisaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
