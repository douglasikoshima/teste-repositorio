package com.indracompany.catalogo.ejb.tipolinha;

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

import com.indracompany.catalogo.dao.interfaces.TipoLinhaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoLinhaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Tipo Linha
 */
@Stateless(name = "TipoLinhaBean", mappedName = "TipoLinhaBean")
@Session(ejbName = "TipoLinhaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TipoLinhaBean implements TipoLinhaBeanLocal {
	
	private static Logger logger = Logger.getLogger(TipoLinhaBean.class);
	
	@EJB
	private TipoLinhaDAO tipoLinhaDAO;
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipolinha.TipoLinhaBeanLocal#findAll()
	 */
	public List<TipoLinhaTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<TipoLinhaTO> tipoLinhaTOList = null;
		
		try {
			tipoLinhaTOList = tipoLinhaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoLinhaTOList;
	}
	
}
