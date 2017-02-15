package com.indracompany.catalogo.ejb.servicointcliente;

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

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import com.indracompany.catalogo.dao.interfaces.ServicoIntClienteDAO;
import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntClienteTO;


@Stateless(name = "ServicoIntClienteBean", mappedName = "ServicoIntClienteBean")
@Session(ejbName = "ServicoIntClienteBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoIntClienteBean implements ServicoIntClienteBeanLocal {
	private static Logger logger = Logger.getLogger(ServicoIntClienteBean.class);
	
	@EJB
	private ServicoIntClienteDAO servicoIntClienteDAO;
	
	public List<ServicoIntCliente> findByIdServicoInteratividade (Integer idServicoInteratividade) {
		logger.debug("idServicoInteratividade");
		
		List<ServicoIntCliente> servIntClienteList = new ArrayList<ServicoIntCliente>();
		
		try {
			servIntClienteList = servicoIntClienteDAO.findByIdServicoInteratividade(idServicoInteratividade);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return servIntClienteList;
	}
	
	public void createUpdateServicoIntCliente (ServicoIntClienteTO servicoIntClienteTO) {
		
		try {
			
			servicoIntClienteDAO.createUpdateServicoIntCliente(servicoIntClienteTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeServIntClienteById (Integer idServicoInteratividade) { 
		logger.debug("idServicoInteratividade");
		
		try {
			
			servicoIntClienteDAO.removeServIntClienteById(idServicoInteratividade);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

}
