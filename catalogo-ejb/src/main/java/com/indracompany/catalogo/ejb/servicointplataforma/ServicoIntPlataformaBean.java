package com.indracompany.catalogo.ejb.servicointplataforma;

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

import com.indracompany.catalogo.dao.interfaces.ServicoIntPlataformaDAO;
import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;



import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;




@Stateless(name = "ServicoIntPlataformaBean", mappedName = "ServicoIntPlataformaBean")
@Session(ejbName = "ServicoIntPlataformaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoIntPlataformaBean implements ServicoIntPlataformaBeanLocal {
	private static Logger logger = Logger.getLogger(ServicoIntPlataformaBean.class);
	
	@EJB
	private ServicoIntPlataformaDAO servicoIntPlataformaDAO;
	
	public List<ServicoIntPlataforma> findByIdServicoInteratividade (Integer idServicoInteratividade) {
		logger.debug("idServicoInteratividade");
		
		List<ServicoIntPlataforma> servIntPlatList = new ArrayList<ServicoIntPlataforma>();
		
		try {
			servIntPlatList = servicoIntPlataformaDAO.findByIdServicoInteratividade(idServicoInteratividade);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
				
		return servIntPlatList;
		
	}
	
	public void createUpdateServicoIntPlataforma (ServicoIntPlataformaTO servicoIntPlataformaTO) {
		
		try {
			
			servicoIntPlataformaDAO.createUpdateServicoIntPlataforma(servicoIntPlataformaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeSrvIntPlataformaById (Integer idServicoInteratividade) {
		
		try {
			servicoIntPlataformaDAO.removeSrvIntPlataformaById(idServicoInteratividade);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
}
