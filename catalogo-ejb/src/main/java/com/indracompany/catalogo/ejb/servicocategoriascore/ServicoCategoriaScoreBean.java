package com.indracompany.catalogo.ejb.servicocategoriascore;

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

import com.indracompany.catalogo.dao.interfaces.ServicoCategoriaScoreDAO;
import com.indracompany.catalogo.ejb.categoriascore.CategoriaScoreBean;
import com.indracompany.catalogo.ejb.servicocategoriascore.ServicoCategoriaScoreBeanLocal;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

@Stateless(name = "ServicoCategoriaScoreBean", mappedName = "ServicoCategoriaScoreBean")
@Session(ejbName = "ServicoCategoriaScoreBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoCategoriaScoreBean implements ServicoCategoriaScoreBeanLocal{
	
	private static Logger logger = Logger.getLogger(CategoriaScoreBean.class);	
	
	@EJB
	private ServicoCategoriaScoreDAO servicoCategoriaScoreDAO;
	
	public void removeByCategoriaScore(ServicoCategoriaScoreTO servicoCategoriaScoreTO) throws BusinessException {
		logger.debug("servicoCategoriaScoreTO" + servicoCategoriaScoreTO);
		
		try {
			servicoCategoriaScoreDAO.removeByCategoriaScore(servicoCategoriaScoreTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}
}