package com.indracompany.catalogo.ejb.planocategoriascore;

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

import com.indracompany.catalogo.dao.interfaces.PlanoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;

@Stateless(name = "PlanoCategoriaScoreBean", mappedName = "PlanoCategoriaScoreBean")
@Session(ejbName = "PlanoCategoriaScoreBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PlanoCategoriaScoreBean implements PlanoCategoriaScoreBeanLocal{
	
	private static Logger logger = Logger.getLogger(PlanoCategoriaScoreBean.class);	
	
	@EJB
	private PlanoCategoriaScoreDAO planoCategoriaScoreDAO;
	
	public void removeByCategoriaScore(PlanoCategoriaScoreTO planoCategoriaScoreTO) throws BusinessException {
		logger.debug("planoCategoriaScoreTO" + planoCategoriaScoreTO);
		
		try {
			planoCategoriaScoreDAO.removeByCategoriaScore(planoCategoriaScoreTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}
}
