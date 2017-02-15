package com.indracompany.catalogo.ejb.tecnologia;

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

import com.indracompany.catalogo.dao.interfaces.TecnologiaDAO;
import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Tipo Cliente
 */
@Stateless(name = "TecnologiaBean", mappedName = "TecnologiaBean")
@Session(ejbName = "TecnologiaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TecnologiaBean implements TecnologiaBeanLocal {
	
	private static Logger logger = Logger.getLogger(TecnologiaBean.class);
	
	@EJB
	private TecnologiaDAO tecnologiaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.tecnologia.TecnologiaBeanLocal#findAll()
	 */
	public List<TecnologiaTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		List<TecnologiaTO> tipoClienteTOList = null;
		try {
			tipoClienteTOList = tecnologiaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return tipoClienteTOList;
	}
	
	public void searchTecnologia(PesquisaIdNomeTO pesquisaTO) throws BusinessException {
		logger.debug("[searchTecnologia]");
		try {
			tecnologiaDAO.searchTecnologia(pesquisaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public Tecnologia findById(Integer idTecnologia) throws BusinessException {
		logger.debug("[findById]");
		
		Tecnologia tecnologia = new Tecnologia();
		try {
			tecnologia = tecnologiaDAO.findById(idTecnologia);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return tecnologia;		
	}	
	
}