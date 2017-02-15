package com.indracompany.catalogo.ejb.servicointeratividade;

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

import com.indracompany.catalogo.dao.interfaces.ServicoIntCanalDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoIntClienteDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoIntPlataformaDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoIntTecnologiaDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoInteratividadeDAO;
import com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParametroDAO;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntCanalTO;
import com.indracompany.catalogo.to.ServicoIntClienteTO;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;
import com.indracompany.catalogo.to.SrvInteratividadeParamTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Servico Interatividade
 */
@Stateless(name = "ServicoInteratividadeBean", mappedName = "ServicoInteratividadeBean")
@Session(ejbName = "ServicoInteratividadeBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoInteratividadeBean implements ServicoInteratividadeBeanLocal {
	
	private static Logger logger = Logger.getLogger(ServicoInteratividadeBean.class);
	
	@EJB
	private ServicoInteratividadeDAO servicoInteratividadeDAO;
	
	@EJB
	private SrvInteratividadeParametroDAO srvInteratividadeParametroDAO;
	
	@EJB
	private ServicoIntCanalDAO servicoIntCanalDAO;
	
	@EJB
	private ServicoIntPlataformaDAO servicoIntPlataformaDAO;
	
	@EJB
	private ServicoIntClienteDAO servicoIntClienteDAO;
	
	@EJB
	private ServicoIntTecnologiaDAO servicoIntTecnologiaDAO;
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.ServicoInteratividadeBeanLocal#searchServicoInteratividade(com.indracompany.catalogo.to.ServicoInteratividadeTO)
	 */
	public List<ServicoInteratividadeTO> searchServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		List<ServicoInteratividadeTO> list = null; 
		
		try {
			list = servicoInteratividadeDAO.searchServicoInteratividade(servicoInteratividadeTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.ServicoInteratividadeBeanLocal#createUpdateServicoInteratividade(com.indracompany.catalogo.to.ServicoInteratividadeTO)
	 */
	public ServicoInteratividadeTO createUpdateServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		try {
			
			if (servicoInteratividadeTO.getIdServicoInteratividade() != null) {
				srvInteratividadeParametroDAO.removeSrvInteratividadeParamById(servicoInteratividadeTO.getIdServicoInteratividade());
				servicoIntCanalDAO.removeSrvIntCanalById(servicoInteratividadeTO.getIdServicoInteratividade());
				servicoIntPlataformaDAO.removeSrvIntPlataformaById(servicoInteratividadeTO.getIdServicoInteratividade());
				servicoIntClienteDAO.removeServIntClienteById(servicoInteratividadeTO.getIdServicoInteratividade());
				servicoIntTecnologiaDAO.removeServIntTecnologiaById(servicoInteratividadeTO.getIdServicoInteratividade());
			}
			ServicoInteratividadeTO servicoInteratividadeResultTO = servicoInteratividadeDAO.createUpdateServicoInteratividade(servicoInteratividadeTO);
			
			for (SrvInteratividadeParamTO srvInteratividadeParametroTO : servicoInteratividadeTO.getSrvInteratividadeParametroTOList()) {
				srvInteratividadeParametroTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoInteratividadeResultTO.getIdServicoInteratividade()));
				srvInteratividadeParametroDAO.createUpdateSrvInteratividadeParam(srvInteratividadeParametroTO);
			}
			// ServicoIntCanal
			for (ServicoIntCanalTO servicoIntCanalTO : servicoInteratividadeTO.getServicoIntCanalTOList()) {
				servicoIntCanalTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoInteratividadeResultTO.getIdServicoInteratividade()));
				servicoIntCanalDAO.createUpdateServicoIntCanal(servicoIntCanalTO);
			}
			// ServicoIntPlataforma
			for (ServicoIntPlataformaTO servicoIntPlataformaTO : servicoInteratividadeTO.getServicoIntPlataformaTOList()) {
				servicoIntPlataformaTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoInteratividadeResultTO.getIdServicoInteratividade()));
				servicoIntPlataformaDAO.createUpdateServicoIntPlataforma(servicoIntPlataformaTO);
			}
			// ServicoIntCliente
			for (ServicoIntClienteTO servicoIntClienteTO : servicoInteratividadeTO.getServicoIntClienteTOList()) {
				servicoIntClienteTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoInteratividadeResultTO.getIdServicoInteratividade()));
				servicoIntClienteDAO.createUpdateServicoIntCliente(servicoIntClienteTO);
			}			
			//ServicoIntTecnologia
			for (ServicoIntTecnologiaTO servicoIntTecnologiaTO : servicoInteratividadeTO.getServicoIntTecnologiaTOList()) {
				servicoIntTecnologiaTO.setServicoInteratividadeTO(new ServicoInteratividadeTO(servicoInteratividadeResultTO.getIdServicoInteratividade()));
				servicoIntTecnologiaDAO.createUpdateServicoIntTecnologia(servicoIntTecnologiaTO);
			}
			
			
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return servicoInteratividadeTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.ServicoInteratividadeBeanLocal#findById(com.indracompany.catalogo.to.ServicoInteratividadeTO)
	 */
	public ServicoInteratividadeTO findById(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		ServicoInteratividadeTO servicoInteratividadeResultTO = null;
		
		try {
			servicoInteratividadeResultTO = servicoInteratividadeDAO.findById(servicoInteratividadeTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return servicoInteratividadeResultTO;
	}
	
	public ServicoInteratividade createServicointeratividade(ServicoInteratividade si) {
		logger.debug("createServicointeratividade");
		
		ServicoInteratividade createdServicointeratividade = new ServicoInteratividade();
		
		try {
			
			createdServicointeratividade = servicoInteratividadeDAO.createServicointeratividade(si);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return createdServicointeratividade;
	}
	
	public Integer validarServicointeratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		
		Integer idServicointeratividade = null;
		
		try {
			
			idServicointeratividade = servicoInteratividadeDAO.validarServicointeratividade(servicoInteratividadeTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return idServicointeratividade;
	}
	
	
}
