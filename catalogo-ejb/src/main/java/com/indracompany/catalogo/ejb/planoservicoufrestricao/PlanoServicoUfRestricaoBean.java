package com.indracompany.catalogo.ejb.planoservicoufrestricao;

import java.util.ArrayList;
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

import com.indracompany.catalogo.dao.interfaces.PlanoDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoUfRestricaoDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoUfRestricaoDAO;
import com.indracompany.catalogo.dao.interfaces.UfDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

@Stateless(name = "PlanoServicoUfRestricaoBean", mappedName = "PlanoServicoUfRestricaoBean")
@Session(ejbName = "PlanoServicoUfRestricaoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PlanoServicoUfRestricaoBean implements PlanoServicoUfRestricaoBeanLocal {
	
	private static Logger logger = Logger.getLogger(PlanoServicoUfRestricaoBean.class);
	
	@EJB
	private PlanoDAO planoDAO;
	
	@EJB
	private ServicoDAO servicoDAO;
	
	@EJB
	private UfDAO ufDAO;
	
	@EJB
	private PlanoUfRestricaoDAO planoUfRestricaoDAO;
	
	@EJB
	private ServicoUfRestricaoDAO servicoUfRestricaoDAO;

	public List<PlanoServicoUfRestricaoTO> searchPlano(PlanoServicoUfRestricaoTO to) throws BusinessException {
		
		logger.debug(">> searchPlano()");
		
		List<PlanoServicoUfRestricaoTO> list;
		
		try {
			list = planoDAO.searchPlanoUfRestricao(to);
		} catch (DAOException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços.", e);
		}
		
		logger.debug("<< searchPlano()");
		
		return list;
	}
	
	public List<PlanoServicoUfRestricaoTO> searchServico(PlanoServicoUfRestricaoTO to) throws BusinessException {
		
		logger.debug(">> searchServico()");
		
		List<PlanoServicoUfRestricaoTO> list;
		
		try {
			list = servicoDAO.searchServicoUfRestricao(to);
		} catch (DAOException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços.", e);
		}
		
		logger.debug("<< searchServico()");
		
		return list;
	}

	public List<UfTO> findAllUf() throws BusinessException {
		
		logger.debug(">> findAllUf()");
		
		List<UfTO> list;
		
		try {
			list = ufDAO.findAll();
		} catch (DAOException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços.", e);
		}
		
		logger.debug("<< findAllUf()");
		
		return list;
	}

	public void configurarRestricaoPlano(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException {
		
		logger.debug(">> configurarRestricaoPlano()");
		
		List<Integer> idPlanoList = new ArrayList<Integer>();
		
		try {
			
			for (PlanoServicoUfRestricaoTO psTO : toList) {
				idPlanoList.add(psTO.getId().intValue());
			}
			
			planoUfRestricaoDAO.removeByIdsPlano(idPlanoList);
			
			planoUfRestricaoDAO.createUpdatePlanoUfRestricaoList(toList);

		} catch (DAOException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços.", e);
		}
		
		logger.debug("<< configurarRestricaoPlano()");
		
	}

	public void configurarRestricaoServico(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException {
		
		logger.debug(">> configurarRestricaoServico()");
		
		List<Integer> idServicoList = new ArrayList<Integer>();
		
		try {
			
			for (PlanoServicoUfRestricaoTO psTO : toList) {
				idServicoList.add(psTO.getId().intValue());
			}
			
			servicoUfRestricaoDAO.removeByIdsServico(idServicoList);
			
			servicoUfRestricaoDAO.createUpdateServicoUfRestricaoList(toList);

		} catch (DAOException e) {
			throw new BusinessException("Ocorreu um erro inesperado. Entre em contato com a Central de Serviços.", e);
		}
		
		logger.debug("<< configurarRestricaoServico()");
		
	}

}