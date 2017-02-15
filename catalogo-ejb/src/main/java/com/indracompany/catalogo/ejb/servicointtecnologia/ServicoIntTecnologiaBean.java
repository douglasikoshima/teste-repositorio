package com.indracompany.catalogo.ejb.servicointtecnologia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoIntTecnologiaDAO;
import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;



@Stateless(name = "ServicoIntTecnologiaBean", mappedName = "ServicoIntCanalBean")
@Session(ejbName = "ServicoIntTecnologiaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoIntTecnologiaBean implements ServicoIntTecnologiaBeanLocal {
	
	private static Logger logger = Logger.getLogger(ServicoIntTecnologiaBean.class);
	
	@EJB
	private ServicoIntTecnologiaDAO servicoIntTecnologiaDAO;
	
	public List<ServicoIntTecnologia> findByIdServicoInteratividade (Integer idServicoInteratividade) { 
		logger.debug("idServicoInteratividade");
		
		List<ServicoIntTecnologia> servIntTecnoList = new ArrayList<ServicoIntTecnologia>();
		
		try {
			servIntTecnoList = servicoIntTecnologiaDAO.findByIdServicoInteratividade(idServicoInteratividade);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return servIntTecnoList;
	}
	
	public void createUpdateServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) {
		
		try {
			
			servicoIntTecnologiaDAO.createUpdateServicoIntTecnologia(servicoIntTecnologiaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
	}
	
	public void removeServIntTecnologiaById (Integer idServicoInteratividade) {
		
		try {
			
			servicoIntTecnologiaDAO.removeServIntTecnologiaById(idServicoInteratividade);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
}
