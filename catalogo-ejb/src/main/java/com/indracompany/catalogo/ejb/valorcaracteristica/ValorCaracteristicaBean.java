package com.indracompany.catalogo.ejb.valorcaracteristica;

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

import com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;


@Stateless(name = "ValorCaracteristicaBean", mappedName = "ValorCaracteristicaBean")
@Session(ejbName = "ValorCaracteristicaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ValorCaracteristicaBean implements ValorCaracteristicaBeanLocal {
	
	private static Logger logger = Logger.getLogger(ValorCaracteristicaBean.class);
	
	@EJB
	ValorCaracteristicaDAO valorCaracteristicaDAO;
		
	public List<ValorCaracteristicaTO> listarValorCaracteristica()
	throws BusinessException {
		logger.debug("[listarValorCaracteristica]");
		
		try {
			return valorCaracteristicaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public void createUpdateValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		try {
			
			
			ValorCaracteristicaTO valorCaracteristicaResultTO = valorCaracteristicaDAO.findByValor(valorCaracteristicaTO);
			if (valorCaracteristicaResultTO != null && !valorCaracteristicaResultTO.getIdValorCaracteristica().equals(valorCaracteristicaTO.getIdValorCaracteristica())) {
				throw new BusinessException("Opera&ccedil;&atilde;o n&atilde;o permitida. Valor j&aacute; cadastrado para essa Caracter&iacute;stica.");
			}
			
			valorCaracteristicaDAO.createUpdateValorCaracteristica(valorCaracteristicaTO);
		} catch (DAOException e) {
		throw new EJBException(e);
		}
	}
	
	public void saveValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		try {
			if (valorCaracteristicaDAO.findByNome(
					valorCaracteristicaTO.getCaracteristicaTO().getIdCaracteristica(),
					valorCaracteristicaTO.getValor()) != null) {
				throw new BusinessException("Valor já existe!");
			}
			
			valorCaracteristicaDAO.save(valorCaracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public List<ValorCaracteristicaTO> findByCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO ){  
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		try {
			return valorCaracteristicaDAO.findByCaracteristica(valorCaracteristicaTO); 
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public void deleteValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		try {
			valorCaracteristicaDAO.deleteValorCaracteristica(valorCaracteristicaTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public ValorCaracteristicaTO find(ValorCaracteristicaTO valorCaracteristicaTO) throws BusinessException {
		
		ValorCaracteristicaTO valorCaracteristicaResultTO = null;
		
		try {
			
			valorCaracteristicaResultTO = valorCaracteristicaDAO.find(valorCaracteristicaTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return valorCaracteristicaResultTO;
	}
}
