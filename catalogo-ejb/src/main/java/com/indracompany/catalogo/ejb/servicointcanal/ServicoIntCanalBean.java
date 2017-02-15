package com.indracompany.catalogo.ejb.servicointcanal;

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


import com.indracompany.catalogo.dao.interfaces.ServicoIntCanalDAO;
import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntCanalTO;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;



@Stateless(name = "ServicoIntCanalBean", mappedName = "ServicoIntCanalBean")
@Session(ejbName = "ServicoIntCanalBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoIntCanalBean implements ServicoIntCanalBeanLocal {
	
	private static Logger logger = Logger.getLogger(ServicoIntCanalBean.class);
	
	@EJB
	private ServicoIntCanalDAO servicoIntCanalDAO;
	
	
	public List<ServicoIntCanal> findById (Integer idServicoInteratividade) { 
		logger.debug("idServicoIntCanal: " + idServicoInteratividade);
				
		List<ServicoIntCanal> servIntCanalList = new ArrayList<ServicoIntCanal>();
		
		try {
			servIntCanalList = servicoIntCanalDAO.findById(idServicoInteratividade);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return servIntCanalList;
	}
	
	public void createUpdateServicoIntCanal (ServicoIntCanalTO servicoIntCanalTO) {
		
		try {
			
			servicoIntCanalDAO.createUpdateServicoIntCanal(servicoIntCanalTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
	}
	
	public void removeSrvIntCanalById(Integer idServicoInteratividade) {
		
		try {
			servicoIntCanalDAO.removeSrvIntCanalById(idServicoInteratividade);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	


}
