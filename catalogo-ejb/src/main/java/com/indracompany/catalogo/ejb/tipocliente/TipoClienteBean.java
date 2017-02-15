package com.indracompany.catalogo.ejb.tipocliente;

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

import com.indracompany.catalogo.dao.interfaces.TipoClienteDAO;
import com.indracompany.catalogo.datalayer.TipoCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoClienteTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Tipo Cliente
 */
@Stateless(name = "TipoClienteBean", mappedName = "TipoClienteBean")
@Session(ejbName = "TipoClienteBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TipoClienteBean implements TipoClienteBeanLocal {
	
	private static Logger logger = Logger.getLogger(TipoClienteBean.class);
	
	@EJB
	private TipoClienteDAO tipoClienteDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tipocliente.TipoClienteBeanLocal#findAll()
	 */
	public List<TipoClienteTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<TipoClienteTO> tipoClienteTOList = null;
		
		try {
			tipoClienteTOList = tipoClienteDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoClienteTOList;
	}
	
	public TipoCliente findById(Integer idTipoCliente) throws BusinessException {
		logger.debug("[findById]");
		
		TipoCliente tpCliente = new TipoCliente();
		
		try {
			tpCliente = tipoClienteDAO.findById(idTipoCliente);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return tpCliente;
	}
}
