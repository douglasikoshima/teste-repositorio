package com.indracompany.catalogo.ejb.grupocaracteristica;

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
import com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

@Stateless(name = "GrupoCaracteristicaBean", mappedName = "GrupoCaracteristicaBean")
@Session(ejbName = "GrupoCaracteristicaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GrupoCaracteristicaBean implements GrupoCaracteristicaBeanLocal {
	
	private static Logger logger = Logger.getLogger(GrupoCaracteristicaBean.class);
	
	@EJB
	private GrupoCaracteristicaDAO grupoCaracteristicaDAO;
	
	@EJB
	private CaracteristicaDAO caracteristicaDAO;
	
	public List<GrupoCaracteristicaTO> listarGrupoCaracteristica()
			throws BusinessException {
		logger.debug("[listarGrupoCaracteristica]");
		
		try {
			return grupoCaracteristicaDAO.findAll();
		} catch (DAOException e) {			
			throw new EJBException(e);
		}
	}

	public void createUpdateGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		try {
			
			grupoCaracteristicaDAO.createUpdateGrupoCaracteristica(grupoCaracteristicaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public void deleteGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		try {
			
			CaracteristicaTO caracteristicaTO = new CaracteristicaTO();
			caracteristicaTO.setGrupoCaracteristicaTO(grupoCaracteristicaTO);
			List<CaracteristicaTO> list = caracteristicaDAO.findByGrupoCaracteristica(caracteristicaTO);
			
			if (list != null && list.size() > 0) {
				throw new BusinessException("Opera&ccedil;&atilde;o n&atilde;o permitida. Esse Grupo possui caracter&iacute;sticas associadas.");
			}
			
			grupoCaracteristicaDAO.deleteGrupoCaracteristica(grupoCaracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public GrupoCaracteristicaTO getGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws BusinessException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		try {
			return grupoCaracteristicaDAO.getGrupoCaracteristica(grupoCaracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
