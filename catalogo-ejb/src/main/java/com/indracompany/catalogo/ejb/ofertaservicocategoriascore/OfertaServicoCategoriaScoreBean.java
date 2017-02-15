package com.indracompany.catalogo.ejb.ofertaservicocategoriascore;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.OfertaServicoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

@Stateless(name = "OfertaServicoCategoriaScoreBean", mappedName = "OfertaServicoCategoriaScoreBean")
@Session(ejbName = "OfertaServicoCategoriaScoreBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OfertaServicoCategoriaScoreBean implements OfertaServicoCategoriaScoreBeanLocal{

	private static Logger logger = Logger.getLogger(OfertaServicoCategoriaScoreBean.class);	
	
	@EJB
	private OfertaServicoCategoriaScoreDAO ofertaServicoCategoriaScoreDAO;
	
	public void removeByCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) throws BusinessException {
		logger.debug("ofertaServicoCategoriaScoreTO" + ofertaServicoCategoriaScoreTO);
		
		try {
			ofertaServicoCategoriaScoreDAO.removeByCategoriaScore(ofertaServicoCategoriaScoreTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}	
	
}
