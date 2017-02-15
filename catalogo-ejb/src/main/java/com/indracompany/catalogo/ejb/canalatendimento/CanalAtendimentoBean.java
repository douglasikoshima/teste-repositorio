package com.indracompany.catalogo.ejb.canalatendimento;

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

import com.indracompany.catalogo.dao.interfaces.CanalAtendimentoDAO;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalAtendimentoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Servico Interatividade
 */
@Stateless(name = "CanalAtendimentoBean", mappedName = "CanalAtendimentoBean")
@Session(ejbName = "CanalAtendimentoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CanalAtendimentoBean implements CanalAtendimentoBeanLocal {
	
	private static Logger logger = Logger.getLogger(CanalAtendimentoBean.class);
	
	@EJB
	private CanalAtendimentoDAO canalAtendimentoDAO;

	public List<CanalAtendimentoTO> findAll() throws BusinessException {
		logger.debug("[findAll]");
		
		List<CanalAtendimentoTO> list = null;
		
		try {
			list = canalAtendimentoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	public CanalAtendimento findById (Integer idCanal) {
		logger.debug("[findById]");
		
		CanalAtendimento canalAtt = new CanalAtendimento();
		
		try {
			canalAtt = canalAtendimentoDAO.findById(idCanal);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new EJBException(e);			
		}
		
		return canalAtt;
	}
	
}
