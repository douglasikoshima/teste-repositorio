package com.indracompany.catalogo.ejb.tipoproduto;

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

import com.indracompany.catalogo.dao.TipoProdutoTOBuilder;
import com.indracompany.catalogo.dao.interfaces.TipoProdutoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoProdutoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Tipo Produto
 */
@Stateless(name = "TipoProdutoBean", mappedName = "TipoProdutoBean")
@Session(ejbName = "TipoProdutoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TipoProdutoBean implements TipoProdutoBeanLocal {
	
	private static Logger logger = Logger.getLogger(TipoProdutoBean.class);
	
	@EJB
	private TipoProdutoDAO tipoProdutoDAO;
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipoproduto.TipoProdutoBeanLocal#findAll()
	 */
	public List<TipoProdutoTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			tipoProdutoTOList = tipoProdutoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoProdutoTOList;
	}
	
	public List<TipoProdutoTO> findAll(TipoProdutoTOBuilder tipoProdutoTOBuilder) throws BusinessException {
		logger.debug("[findAll]");
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			tipoProdutoTOList = tipoProdutoDAO.findAll(tipoProdutoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoProdutoTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipoproduto.TipoProdutoBeanLocal#findAll()
	 */
	public List<TipoProdutoTO> findAllButSimCards() throws BusinessException {
		logger.debug("[findAllButSimCards]");
		
		List<TipoProdutoTO> tipoProdutoTOList = null;
		
		try {
			tipoProdutoTOList = tipoProdutoDAO.findAllButSimCards();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoProdutoTOList;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipoproduto.TipoProdutoBeanLocal#findAll(com.indracompany.catalogo.to.TipoProdutoTO)
	 */
	public TipoProdutoTO findById(TipoProdutoTO tipoProdutoTO) throws BusinessException {
		logger.debug("tipoProdutoTO: " + tipoProdutoTO);
		
		TipoProdutoTO tipoProdutoResultTO = null;
		
		try {
			tipoProdutoResultTO = tipoProdutoDAO.findById(tipoProdutoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoProdutoResultTO;
	}
}
