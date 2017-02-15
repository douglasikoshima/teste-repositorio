package com.indracompany.catalogo.ejb.classificacaomultimidia;

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

import com.indracompany.catalogo.dao.interfaces.ClassificacaoMultimidiaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;

/**
 * @author 
 * 
 * EJB responsável por realizar as funções de ClassificacaoMultimidia
 */
@Stateless(name = "ClassificacaoMultimidiaBean", mappedName = "ClassificacaoMultimidiaBean")
@Session(ejbName = "ClassificacaoMultimidiaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ClassificacaoMultimidiaBean implements ClassificacaoMultimidiaBeanLocal {
	
	private static Logger logger = Logger.getLogger(ClassificacaoMultimidiaBean.class);
	
	@EJB
	private ClassificacaoMultimidiaDAO corDAO;
	
	public List<ClassificacaoMultimidiaTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<ClassificacaoMultimidiaTO> list = null;
		
		try {
			list = corDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
}