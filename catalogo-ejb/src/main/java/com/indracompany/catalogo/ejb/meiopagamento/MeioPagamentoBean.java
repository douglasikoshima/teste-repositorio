package com.indracompany.catalogo.ejb.meiopagamento;

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

import com.indracompany.catalogo.dao.interfaces.MeioPagamentoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MeioPagamentoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Plataforma
 */
@Stateless(name = "MeioPagamentoBean", mappedName = "MeioPagamentoBean")
@Session(ejbName = "MeioPagamentoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MeioPagamentoBean implements MeioPagamentoBeanLocal {
	
	private static Logger logger = Logger.getLogger(MeioPagamentoBean.class);
	
	@EJB
	private MeioPagamentoDAO meioPagamentoDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.meiopagamento.MeioPagamentoBeanLocal#findAll()
	 */
	public List<MeioPagamentoTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<MeioPagamentoTO> meioPagamentoTOList = null;
		
		try {
			meioPagamentoTOList = meioPagamentoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return meioPagamentoTOList;
	}
	
}
