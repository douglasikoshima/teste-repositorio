package com.indracompany.catalogo.ejb.plataforma;

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

import com.indracompany.catalogo.dao.interfaces.PlataformaDAO;
import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlataformaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Plataforma
 */
@Stateless(name = "PlataformaBean", mappedName = "PlataformaBean")
@Session(ejbName = "PlataformaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PlataformaBean implements PlataformaBeanLocal {
	
	private static Logger logger = Logger.getLogger(PlataformaBean.class);
	
	@EJB
	private PlataformaDAO plataformaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.servicointeratividade.interfaces.PlataformaBeanLocal#findAll()
	 */
	public List<PlataformaTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<PlataformaTO> plataformaList = null;
		
		try {
			plataformaList = plataformaDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return plataformaList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.plataforma.PlataformaBeanLocal#findAllWithExpections(java.lang.Integer[])
	 */
	public List<PlataformaTO> findAllWithExpections(Integer[] idPlataformas) throws BusinessException {
		logger.debug("idPlataformas: " + idPlataformas);
		
		List<PlataformaTO> plataformaList = null;
		
		try {
			plataformaList = plataformaDAO.findAllWithExpections(idPlataformas);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return plataformaList;
	}
	
    public List<PlataformaTO> findByIdCanalAtendimento(Integer idCanalAtendimento) throws BusinessException {
        logger.debug("idCanalAtendimento: " + idCanalAtendimento);
        List<PlataformaTO> plataformaList = null;
        try {
            plataformaList = plataformaDAO.findByIdCanalAtendimento(idCanalAtendimento);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return plataformaList;        
    }
    
    public Plataforma findById(Integer idPlataforma) throws BusinessException {
    	logger.debug("idPlataforma: " + idPlataforma);
    	Plataforma plataforma = new Plataforma();
    	
    	try {
			plataforma = plataformaDAO.findById(idPlataforma);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
    	    	
    	return plataforma;
    }
    
}
