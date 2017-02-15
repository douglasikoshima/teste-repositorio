package com.indracompany.catalogo.ejb.categoriascore;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.CategoriaScoreTOBuilder;
import com.indracompany.catalogo.dao.interfaces.CategoriaScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaScoreTO;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;


@Stateless(name = "CategoriaScoreBean", mappedName = "CategoriaScoreBean")
@Session(ejbName = "CategoriaScoreBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoriaScoreBean implements CategoriaScoreBeanLocal{
	
	private static Logger logger = Logger.getLogger(CategoriaScoreBean.class);
	
	@EJB
	private CategoriaScoreDAO categoriaScoreDAO;

	public void createUpdateCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException {
		
		try {
			categoriaScoreTO.setCdCategoriaScore(categoriaScoreTO.getCdCategoriaScore().trim().toUpperCase());
			categoriaScoreDAO.createUpdateCategoriaScore(categoriaScoreTO);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public List<CategoriaScoreTO> findAll(){
		List<CategoriaScoreTO> categoriaScoreTOList = new ArrayList<CategoriaScoreTO>();
		
		try {
			categoriaScoreTOList = categoriaScoreDAO.findAll();
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return categoriaScoreTOList;
	}
	
	public List<CategoriaScoreTO> findAll(CategoriaScoreTOBuilder categoriaScoreTOBuilder) throws BusinessException {
		List<CategoriaScoreTO> categoriaScoreTOList = new ArrayList<CategoriaScoreTO>();
		
		try {
			categoriaScoreTOList = categoriaScoreDAO.findAll(categoriaScoreTOBuilder);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return categoriaScoreTOList;
	}
	
	public CategoriaScoreTO findById(CategoriaScoreTO categoriaScoreTO) throws BusinessException {
		
		try {
			categoriaScoreTO = categoriaScoreDAO.findById(categoriaScoreTO);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return categoriaScoreTO;
	}

	public List<CategoriaScoreTO> searchCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException {
		logger.debug("categoriaScoreTO" + categoriaScoreTO);
		
		List<CategoriaScoreTO> categoriaScoreTOList = null;
		
		try {
			categoriaScoreTOList = categoriaScoreDAO.searchCategoriaScore(categoriaScoreTO);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return categoriaScoreTOList;
	}

	public void removeCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException {
		
		try {
			categoriaScoreDAO.removeAssociation(categoriaScoreTO);
			categoriaScoreDAO.removeCategoriaScore(categoriaScoreTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	public List<String> findAssociation(CategoriaScoreTO categoriaScoreTO){
		
		List<String> associacaoList = new ArrayList<String>();
		
		try {
			if(categoriaScoreDAO.existAssociationPlanoCategoriaScore(categoriaScoreTO)){
				associacaoList.add("Plano");
			}
			if(categoriaScoreDAO.existAssociationServicoCategoriaScore(categoriaScoreTO)){
				associacaoList.add("Servico");
			}
			if(categoriaScoreDAO.existAssociationOfertaServicoCategoriaScore(categoriaScoreTO)){
				associacaoList.add("OfertaServico");
			}
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return associacaoList;
	}

	public void removeAssociation(CategoriaScoreTO categoriaScoreTO) throws BusinessException {

		try {
			categoriaScoreDAO.removeAssociation(categoriaScoreTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public boolean existCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws BusinessException {

		try {
			return categoriaScoreDAO.existCdCategoriaScore(categoriaScoreTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public CategoriaScoreTO findByCdCategoria(CategoriaScoreTO categoriaScoreTO) throws BusinessException {
		try {
			return categoriaScoreDAO.findByCdCategoriaScore(categoriaScoreTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	
}
