package com.indracompany.catalogo.ejb.bandeira;

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

import com.indracompany.catalogo.dao.interfaces.BandeiraDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.BandeiraTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em implementar e realizar as funções de Bandeira
 */
@Stateless(name = "BandeiraBean", mappedName = "BandeiraBean")
@Session(ejbName = "BandeiraBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BandeiraBean implements BandeiraBeanLocal {
	
	private static Logger logger = Logger.getLogger(BandeiraBean.class);
	
	@EJB
	private BandeiraDAO bandeiraDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.BandeiraBeanLocal#searchBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public List<BandeiraTO> searchBandeira(BandeiraTO bandeiraTO) throws BusinessException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		List<BandeiraTO> list = null; 
		
		try {
			list = bandeiraDAO.searchBandeira(bandeiraTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.BandeiraBeanLocal#createUpdateBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public void createUpdateBandeira(BandeiraTO bandeiraTO) throws BusinessException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		try {
			
			bandeiraDAO.createUpdateBandeira(bandeiraTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.BandeiraBeanLocal#findById(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public BandeiraTO findById(BandeiraTO bandeiraTO) throws BusinessException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		BandeiraTO bandeiraResultTO = null;
		
		try {
			bandeiraResultTO = bandeiraDAO.findById(bandeiraTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return bandeiraResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.BandeiraBeanLocal#removeBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public void removeBandeira(BandeiraTO bandeiraTO) throws BusinessException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		try {
			
			BandeiraTO bandeiraResultTO = bandeiraDAO.findById(bandeiraTO);
			
			if (bandeiraResultTO.getFormaPagamentoBandeiraTOList() != null && bandeiraResultTO.getFormaPagamentoBandeiraTOList().size() > 0) {
				throw new BusinessException("Existem formas de pagamento associadas a esta bandeira, ela n&atilde;o pode ser exclu&iacute;da.");
			} else {
				bandeiraDAO.removeBandeira(bandeiraTO);
			}
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.bandeira.BandeiraBeanLocal#findAll()
	 */
	public List<BandeiraTO> findAll() throws BusinessException {
		
		List<BandeiraTO> list = null; 
		
		try {
			list = bandeiraDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
}
