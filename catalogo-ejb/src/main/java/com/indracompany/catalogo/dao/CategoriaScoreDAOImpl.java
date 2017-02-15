package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CategoriaScoreDAO;
import com.indracompany.catalogo.datalayer.CategoriaScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaScoreTO;


@Stateless
public class CategoriaScoreDAOImpl implements CategoriaScoreDAO{

	private static Logger logger = Logger.getLogger(CategoriaScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;	
	
	public void createUpdateCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		
		CategoriaScoreTOBuilder categoriaScoreTOBuilder = new CategoriaScoreTOBuilder();
		
		try {
			em.merge(categoriaScoreTOBuilder.createCategoriaScore(categoriaScoreTO));
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<CategoriaScoreTO> findAll() throws DAOException {
		return findAll(new CategoriaScoreTOBuilder());
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaScoreTO> findAll(CategoriaScoreTOBuilder categoriaScoreTOBuilder) throws DAOException {
		List<CategoriaScoreTO> CategoriaScoreTOList = new ArrayList<CategoriaScoreTO>();
		try {
			Query query = em.createNamedQuery("CategoriaScore.findAll");
			CategoriaScoreTOList = categoriaScoreTOBuilder.createCategoriaScoreTOList(query.getResultList());
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		return CategoriaScoreTOList;
	}

	public CategoriaScoreTO findById(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		
		CategoriaScoreTOBuilder categoriaScoreTOBuilder = new CategoriaScoreTOBuilder();
		
		try {
			categoriaScoreTO = categoriaScoreTOBuilder.createCategoriaScoreTO(em.find(CategoriaScore.class, categoriaScoreTOBuilder.createCategoriaScore(categoriaScoreTO).getIdCategoriaScore()));
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		return categoriaScoreTO;
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaScoreTO> searchCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		logger.debug("categoriaScoreTO: " + categoriaScoreTO);
		
		List<CategoriaScoreTO> categoriaScoreTOList = null;
		StringBuffer queryStringBuffer = new StringBuffer();
		CategoriaScoreTOBuilder categoriaScoreTOBuilder = new CategoriaScoreTOBuilder();
		
		try {
			
			if(categoriaScoreTO != null){
				queryStringBuffer.append("select ca from CategoriaScore ca where 1 = 1");
				if(categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore() != null){
					queryStringBuffer.append(" and ca.classificacaoCategoriaScore.idClassificacaoCategoriaScore = :idClassificacaoCategoriaScore");
				}
				if(categoriaScoreTO.getNmCategoriaScore() != null){
					queryStringBuffer.append(" and upper(ca.nmCategoriaScore) like :nmCategoriaScore");
				}
			}
			
			Query query = em.createQuery(queryStringBuffer.toString());

			if(categoriaScoreTO != null){
				if(categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore() != null){
					query.setParameter("idClassificacaoCategoriaScore",categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore());
				}
				if(categoriaScoreTO.getNmCategoriaScore() != null){
					query.setParameter("nmCategoriaScore", new String("%"+categoriaScoreTO.getNmCategoriaScore()).toUpperCase()+"%");
				}
			}
			categoriaScoreTOList = categoriaScoreTOBuilder.createCategoriaScoreTOList(query.getResultList());
		}
		catch (Exception e) {
			throw new DAOException(e);
		}		
		
		return categoriaScoreTOList;
	}

	public void removeCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		logger.debug("categoriaScoreTO: " + categoriaScoreTO);
		
		try {
			CategoriaScore categoriaScore = em.find(CategoriaScore.class, categoriaScoreTO.getIdCategoriaScore());
			em.remove(categoriaScore);
			
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}

	public boolean existAssociationOfertaServicoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {

		try {
			
			Query query = em.createQuery("select p from PlanoCategoriaScore p where p.categoriaScore.idCategoriaScore = :idCategoriaScore");
			query.setParameter("idCategoriaScore", categoriaScoreTO.getIdCategoriaScore());
			query.getResultList();
			
		} catch (NoResultException e) { 
			return false;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return true;
	}

	public boolean existAssociationPlanoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		
		try {
			Query query = em.createQuery("select s from ServicoCategoriaScore s where s.categoriaScore.idCategoriaScore = :idCategoriaScore");
			query.setParameter("idCategoriaScore", categoriaScoreTO.getIdCategoriaScore());
			query.getResultList();
			
		} catch (NoResultException e) { 
			return false;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return true;
	}

	public boolean existAssociationServicoCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {

		try {
			Query query = em.createQuery("select o from OfertaServicoCategoriaScore o where o.categoriaScore.idCategoriaScore = :idCategoriaScore");
			query.setParameter("idCategoriaScore", categoriaScoreTO.getIdCategoriaScore());
			query.getResultList();
			
		} catch (NoResultException e) { 
			return false;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return true;
	}

	public void removeAssociation(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		logger.debug("categoriaScoreTO: " + categoriaScoreTO);
		
		try {
			// TODO Alterar delete de native query para padrao jpa
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE WHERE IDSERVICOCATEGORIASCORE IN (SELECT IDSERVICOCATEGORIASCORE FROM CATALOGOPRS_OW.SERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?)").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.PLANOCONFIGURACAOSCORE WHERE IDPLANOCATEGORIASCORE IN (SELECT IDPLANOCATEGORIASCORE FROM CATALOGOPRS_OW.PLANOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?)").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE WHERE IDOFERTASERVICOCATEGORIASCORE IN (SELECT IDOFERTASERVICOCATEGORIASCORE FROM CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?)").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
			em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.PLANOCATEGORIASCORE WHERE IDCATEGORIASCORE = ?").setParameter(1, categoriaScoreTO.getIdCategoriaScore()).executeUpdate();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}

	public boolean existCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		
		try {
			Query query = em.createQuery("select c from CategoriaScore c where c.cdCategoriaScore = :cdCategoriaScore ");
			query.setParameter("cdCategoriaScore", categoriaScoreTO.getCdCategoriaScore().toUpperCase());
			if(query.getResultList().isEmpty())	
				return false;
			else 
				return true;
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public CategoriaScoreTO findByCdCategoriaScore(CategoriaScoreTO categoriaScoreTO) throws DAOException {
		
		CategoriaScoreTOBuilder categoriaScoreTOBuilder = new CategoriaScoreTOBuilder();
		List<CategoriaScoreTO> categoriaScoreTOList = new ArrayList<CategoriaScoreTO>();
		
		try {
			Query query = em.createQuery("select c from CategoriaScore c where c.cdCategoriaScore = :cdCategoriaScore ");
			query.setParameter("cdCategoriaScore", categoriaScoreTO.getCdCategoriaScore().toUpperCase());
			categoriaScoreTOList  = categoriaScoreTOBuilder.createCategoriaScoreTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		if(categoriaScoreTOList.size() > 0){
			return categoriaScoreTOList.get(0);
		}else {
			return null;
		}
	}
}
