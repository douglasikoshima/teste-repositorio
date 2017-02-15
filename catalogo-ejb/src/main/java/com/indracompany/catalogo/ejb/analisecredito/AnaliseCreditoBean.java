package com.indracompany.catalogo.ejb.analisecredito;

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

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoDAO;
import com.indracompany.catalogo.dao.interfaces.EpsDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.EpsTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Analise de Credito
 */
@Stateless(name = "AnaliseCreditoBean", mappedName = "AnaliseCreditoBean")
@Session(ejbName = "AnaliseCreditoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AnaliseCreditoBean implements AnaliseCreditoBeanLocal {
	
	private static Logger logger = Logger.getLogger(AnaliseCreditoBean.class);
	
	@EJB
	private AnaliseCreditoDAO analiseCreditoDAO;
    @EJB 
    private EpsDAO epsDAO;

	

	public AnaliseCreditoTO findById(AnaliseCreditoTO analiseCreditoTO) throws BusinessException {
		logger.debug("analiseCreditoTO: " + analiseCreditoTO);
		
		AnaliseCreditoTO result = null;
		
		try {
			result = analiseCreditoDAO.findById(analiseCreditoTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	

	public List<AnaliseCreditoTO> findAll() throws BusinessException {
		
		List<AnaliseCreditoTO> list = null; 
		
		try {
			list = analiseCreditoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
    public List<EpsTO> listEpsTO() throws BusinessException {
    	
    	List<EpsTO> list = null; 
    	
        try {
        	list = epsDAO.listEpsTO();
        } catch (DAOException e) {
            throw new BusinessException(e);
        }
		return list;        
    }	
}