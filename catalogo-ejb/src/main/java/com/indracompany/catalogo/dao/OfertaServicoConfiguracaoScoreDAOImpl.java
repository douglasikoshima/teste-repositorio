package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.OfertaServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.OfServicoConfiguracaoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Oferta Servico Configuracao Score.
 */
@Stateless
public class OfertaServicoConfiguracaoScoreDAOImpl implements OfertaServicoConfiguracaoScoreDAO {
	
	private static Logger logger = Logger.getLogger(OfertaServicoConfiguracaoScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.OfServicoConfiguracaoScoreDAO#existAssociationOfertaServico(java.lang.Integer)
	 */
	public Boolean existAssociationOfertaServico(Integer idOfertaServico) throws DAOException {
		logger.debug("idOfertaServico: " + idOfertaServico);
		
		Boolean retorno = true;
		
		try {
			
			Query query = em.createQuery("select oscs from OfServicoConfiguracaoScore oscs where oscs.ofertaServicoCategoriaScore.ofertaServico.idOfertaServico = :idOfertaServico");
			query.setParameter("idOfertaServico", idOfertaServico);
			query.getSingleResult();
			
		} catch (NoResultException e) {
			retorno = false;
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [existAssociationOfertaServico]", e);
		}
	    return retorno;
	}


	public void createUpdateOfertaServicoConfiguracaoScoreList(List<OfServicoConfiguracaoScoreTO> list) throws DAOException {
		for (OfServicoConfiguracaoScoreTO scoreTO : list) {
			this.createUpdateOfertaServicoConfiguracaoScore(scoreTO);
		}
	}

	public void createUpdateOfertaServicoConfiguracaoScore(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + ofServicoConfiguracaoScoreTO);
		
		OfServicoConfiguracaoScoreTOBuilder builder = new OfServicoConfiguracaoScoreTOBuilder();
		
		try {
			em.merge(builder.createOfServicoConfiguracaoScore(ofServicoConfiguracaoScoreTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.OfServicoConfiguracaoScoreDAO#findById(com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO)
	 */
	public OfServicoConfiguracaoScoreTO findById(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + ofServicoConfiguracaoScoreTO);
		
		OfServicoConfiguracaoScoreTO result = null;
		OfServicoConfiguracaoScoreTOBuilder ofServicoConfiguracaoScoreTOBuilder = new OfServicoConfiguracaoScoreTOBuilder();
		
		try {
			result = ofServicoConfiguracaoScoreTOBuilder.createOfServicoConfiguracaoScoreTO(em.find(OfServicoConfiguracaoScore.class, ofServicoConfiguracaoScoreTO.getIdOfServicoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.OfServicoConfiguracaoScoreDAO#removeOfServicoConfiguracaoScore(com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO)
	 */
	public void removeOfServicoConfiguracaoScore(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + ofServicoConfiguracaoScoreTO);
		
		try {
			em.remove(em.find(OfServicoConfiguracaoScore.class, ofServicoConfiguracaoScoreTO.getIdOfServicoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeOfServicoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException {
		logger.debug("idCabecalho: " + idCabecalho);
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalho);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeOfServicoConfiguracaoScoreByContraint(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + ofServicoConfiguracaoScoreTO);
		
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE  ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDOFERTASERVICOCATEGORIASCORE = :idOfertaServicoCategoriaScore ");
			queryStr.append(" AND IDANALISECREDITO = :idAnaliseCredito ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", ofServicoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			query.setParameter("idOfertaServicoCategoriaScore", ofServicoConfiguracaoScoreTO.getOfertaServicoCategoriaScoreTO().getIdOfertaServicoCategoriaScore());
			query.setParameter("idAnaliseCredito", ofServicoConfiguracaoScoreTO.getAnaliseCreditoTO().getIdAnaliseCredito());
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeOfServicoConfiguracaoScoreByOfertaCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idOfertaCategoriaScoreList) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + idCabecalhoAnaliseCredito + " idOfertaCategoriaScoreList: " + idOfertaCategoriaScoreList);
		
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE  ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDOFERTASERVICOCATEGORIASCORE IN (:idOfertaCategoriaScoreList)");
			queryStr.append(" AND IDREGIONAL IS NULL ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idOfertaCategoriaScoreList", idOfertaCategoriaScoreList);
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeOfServicoConfiguracaoScoreByOfertaCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idOfertaCategoriaScore, Integer idRegional) throws DAOException {
		logger.debug("ofServicoConfiguracaoScoreTO: " + idCabecalhoAnaliseCredito + " idOfertaCategoriaScore: " + idOfertaCategoriaScore + " idRegional: " + idRegional);
		
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE  ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDOFERTASERVICOCATEGORIASCORE IN (:idOfertaCategoriaScoreList)"); 
			queryStr.append(" AND IDREGIONAL = :idRegional ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idOfertaCategoriaScoreList", idOfertaCategoriaScore);
			query.setParameter("idRegional", idRegional);
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idOfertaServicoCategoriaScore, Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException {
		StringBuilder jpql = new StringBuilder();
		Map<String, Boolean> configuracaoScoreMap = new TreeMap<String, Boolean>();
		if(idsRegionais == null || idsRegionais.length == 0){
			idsRegionais = new Integer[1];
			idsRegionais[0] = 0;
		}
		jpql.append(
				" SELECT ofcf " +
				" FROM OfServicoConfiguracaoScore ofcf " +
				" WHERE ofcf.ofertaServicoCategoriaScore.idOfertaServicoCategoriaScore = :idOfertaServicoCategoriaScore " +
				" AND ofcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito "
		);
		
		try {
			List<OfServicoConfiguracaoScore> configScoreList = 
				em.createQuery(jpql.toString())
					.setParameter("idOfertaServicoCategoriaScore",idOfertaServicoCategoriaScore)
					.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito)
					.getResultList();
			List<AnaliseCredito> scores = em.createQuery("SELECT ac FROM AnaliseCredito ac ").getResultList();
			
			for (Integer idRegional : idsRegionais) {
				for (AnaliseCredito score : scores) {
					Boolean scoreConfigurado = false;
					for (OfServicoConfiguracaoScore configScore : configScoreList) {
						if((configScore.getRegional() == null 
							&& idRegional.equals(0) 
							&& score.getIdAnaliseCredito().equals(configScore.getAnaliseCredito().getIdAnaliseCredito())
							) || (
								configScore.getRegional() != null
								&& configScore.getRegional().getIdRegional().equals(idRegional)
								&& score.getIdAnaliseCredito().equals(configScore.getAnaliseCredito().getIdAnaliseCredito())
							)
						){
							scoreConfigurado = true;
						}
					}
					
					String key = new OfServicoConfiguracaoScoreTO(idCabecalhoAnaliseCredito, idOfertaServicoCategoriaScore, score.getIdAnaliseCredito(), idRegional).getChaveConfiguracaoScore();
					configuracaoScoreMap.put(key, scoreConfigurado);
				}
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
		System.out.println("configscore"+configuracaoScoreMap);
		return configuracaoScoreMap;
	}

	@SuppressWarnings("unchecked")
	public void recriaConfiguracaoPorCabecalhoECategoria(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTOList, List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTORemoveList) throws DAOException {
		Set<Map<String, Integer>> categorizacoesARecriar = new HashSet<Map<String,Integer>>();
		List<OfServicoConfiguracaoScore> entitiesConfigARemover = new ArrayList<OfServicoConfiguracaoScore>();
		for (OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO : ofServicoConfiguracaoScoreTORemoveList) {
			Map<String, Integer> elem = new HashMap<String, Integer>();
			elem.put("idCabecalhoAnaliseCredito", ofServicoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			elem.put("idCategorizacao", ofServicoConfiguracaoScoreTO.getOfertaServicoCategoriaScoreTO().getIdOfertaServicoCategoriaScore());
			if(ofServicoConfiguracaoScoreTO.getRegionalTO() != null){
				elem.put("idRegional", ofServicoConfiguracaoScoreTO.getRegionalTO().getIdRegional());				
			}
			categorizacoesARecriar.add(elem);
		}
		for (Map<String, Integer> categPorRegional : categorizacoesARecriar) {
			StringBuilder sqlCategorizacaoAAtualizar = new StringBuilder(
					"SELECT ofcf " +
					"FROM OfServicoConfiguracaoScore ofcf " +
					"WHERE ofcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito " +
					"AND ofcf.ofertaServicoCategoriaScore.idOfertaServicoCategoriaScore = :idCategorizacao "
			);
			if(categPorRegional.get("idRegional") == null || categPorRegional.get("idRegional").equals(0))
				sqlCategorizacaoAAtualizar.append("AND ofcf.regional.idRegional IS NULL ");
			else
				sqlCategorizacaoAAtualizar.append("AND ofcf.regional.idRegional = :idRegional ");
			Query queryEntitiesARemover = em.createQuery(sqlCategorizacaoAAtualizar.toString())
				.setParameter("idCabecalhoAnaliseCredito", categPorRegional.get("idCabecalhoAnaliseCredito"))
				.setParameter("idCategorizacao", categPorRegional.get("idCategorizacao"));
			if(categPorRegional.get("idRegional") != null && !categPorRegional.get("idRegional").equals(0)){
				queryEntitiesARemover.setParameter("idRegional", categPorRegional.get("idRegional"));
			};
			entitiesConfigARemover.addAll(queryEntitiesARemover.getResultList());
		}

		try {
			for (OfServicoConfiguracaoScore ent : entitiesConfigARemover) {
				em.remove(ent);
			}
			em.flush();
			
			List<OfServicoConfiguracaoScore> entitiesConfigAGravar = new OfServicoConfiguracaoScoreTOBuilder().createOfServicoConfiguracaoScoreList(ofServicoConfiguracaoScoreTOList);
			for (OfServicoConfiguracaoScore ent : entitiesConfigAGravar) {
				em.persist(ent);
			}
			em.flush();			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
