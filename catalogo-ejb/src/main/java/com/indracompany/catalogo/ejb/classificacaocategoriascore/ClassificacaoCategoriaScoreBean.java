package com.indracompany.catalogo.ejb.classificacaocategoriascore;

import java.util.List;

import javax.ejb.EJB;
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

import com.indracompany.catalogo.dao.interfaces.ClassificacaoCategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

@Stateless(name = "ClassificacaoCategoriaScoreBean", mappedName = "ClassificacaoCategoriaScoreBean")
@Session(ejbName = "ClassificacaoCategoriaScoreBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClassificacaoCategoriaScoreBean implements ClassificacaoCategoriaScoreBeanLocal{

	private static Logger logger = Logger.getLogger(ClassificacaoCategoriaScoreBean.class);
	
	@EJB
	private ClassificacaoCategoriaScoreDAO classificacaoCategoriaScoreDAO; 

	public List<ClassificacaoCategoriaScoreTO> findAll() throws BusinessException {

		List<ClassificacaoCategoriaScoreTO> classificacaoCategoriaScoreTOList = null;
		
		try {
			classificacaoCategoriaScoreTOList = classificacaoCategoriaScoreDAO.findAll();
		} catch (DAOException e){
            logger.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		return classificacaoCategoriaScoreTOList;
	}
	
}
