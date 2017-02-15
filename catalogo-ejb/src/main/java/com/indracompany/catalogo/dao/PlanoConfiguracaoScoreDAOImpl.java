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
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.PlanoConfiguracaoScoreDAO;
import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.PlanoConfiguracaoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Plano Configuracao Score.
 */
@Stateless
public class PlanoConfiguracaoScoreDAOImpl implements PlanoConfiguracaoScoreDAO {
	
	private static Logger logger = Logger.getLogger(PlanoConfiguracaoScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.PlanoConfiguracaoScoreDAO#existAssociationPlano(java.lang.Integer)
	 */
	public Boolean existAssociationPlano(Integer idPlano) throws DAOException {
		logger.debug("idPlano: " + idPlano);
		
		Boolean retorno = true;
		
		try {
			
			Query query = em.createQuery("select pcs from PlanoConfiguracaoScore pcs where pcs.planoCategoriaScore.plano.idPlano = :idPlano");
			query.setParameter("idPlano", idPlano);
			query.getSingleResult();
			
		} catch (NoResultException e) {
			retorno = false;
		} catch (NonUniqueResultException e) {
			retorno = true;
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [existAssociationPlano]", e);
		}
		
	    return retorno;
	}


	
	public void createUpdatePlanoConfiguracaoScoreList(List<PlanoConfiguracaoScoreTO> list) throws DAOException {
		for (PlanoConfiguracaoScoreTO scoreTO : list) {
			this.createUpdatePlanoConfiguracaoScore(scoreTO);
		} 

	}

	public void createUpdatePlanoConfiguracaoScore(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException {
		logger.debug("planoConfiguracaoScoreTO: " + planoConfiguracaoScoreTO);
		
		PlanoConfiguracaoScoreTOBuilder builder = new PlanoConfiguracaoScoreTOBuilder();
		
		try {
			em.merge(builder.createPlanoConfiguracaoScore(planoConfiguracaoScoreTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public PlanoConfiguracaoScoreTO findById(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException {
		logger.debug("planoConfiguracaoScoreTO: " + planoConfiguracaoScoreTO);
		
		PlanoConfiguracaoScoreTO result = null;
		PlanoConfiguracaoScoreTOBuilder builder = new PlanoConfiguracaoScoreTOBuilder();
		
		try {
			result = builder.createPlanoConfiguracaoScoreTO(em.find(PlanoConfiguracaoScore.class, planoConfiguracaoScoreTO.getIdPlanoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

	public void removePlanoConfiguracaoScore(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException {
		logger.debug("planoConfiguracaoScoreTO: " + planoConfiguracaoScoreTO);
		
		try {
			em.remove(em.find(PlanoConfiguracaoScore.class, planoConfiguracaoScoreTO.getIdPlanoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public void removePlanoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException {
		logger.debug("idCabecalho: " + idCabecalho);
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.PLANOCONFIGURACAOSCORE WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalho);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public void removePlanoConfiguracaoScoreByConstraint(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException {
		logger.debug("planoConfiguracaoScoreTO: " + planoConfiguracaoScoreTO);
		
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.PLANOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDPLANOCATEGORIASCORE = :idPlanoCategoriaScore ");
			queryStr.append(" AND IDANALISECREDITO = :idAnaliseCredito ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", planoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			query.setParameter("idPlanoCategoriaScore", planoConfiguracaoScoreTO.getPlanoCategoriaScoreTO().getIdPlanoCategoriaScore());
			query.setParameter("idAnaliseCredito", planoConfiguracaoScoreTO.getAnaliseCreditoTO().getIdAnaliseCredito());
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
		
	public void removePlanoConfiguracaoScoreByPlanoCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idPlanoCategoriaScoreList) throws DAOException {
		logger.debug(" idCabecalhoAnaliseCredito " +  idCabecalhoAnaliseCredito  + " idPlanoCategoriaScoreList: " + idPlanoCategoriaScoreList);

		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.PLANOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDPLANOCATEGORIASCORE IN (:idPlanoCategoriaScoreList) ");
			queryStr.append(" AND IDREGIONAL IS NULL ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idPlanoCategoriaScoreList", idPlanoCategoriaScoreList);

			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	public void removePlanoConfiguracaoScoreByPlanoCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idPlanoCategoriaScore, Integer idRegional) throws DAOException {
		logger.debug(" idCabecalhoAnaliseCredito " +  idCabecalhoAnaliseCredito  + " idPlanoCategoriaScore: " + idPlanoCategoriaScore + " idRegional: " + idRegional);

		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.PLANOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			queryStr.append(" AND IDPLANOCATEGORIASCORE = :idPlanoCategoriaScore ");
			queryStr.append(" AND IDREGIONAL = :idRegional ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idPlanoCategoriaScore", idPlanoCategoriaScore);
			query.setParameter("idRegional", idRegional);

			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<PlanoConfiguracaoScoreTO> pesquisarConfiguracaoPorCategorizacao(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO,
			CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO)
			throws DAOException {
		logger.debug("CategorizacaoAnaliseCreditoTO " +  categorizacaoAnaliseCreditoTO);
		List<PlanoConfiguracaoScoreTO> result = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" SELECT plcf ");
			queryStr.append(" FROM PlanoConfiguracaoScore plcf ");
			queryStr.append(" JOIN plcf.planoCategoriaScore plcs ");
			queryStr.append(" JOIN plcf.cabecalhoAnaliseCredito cac ");
			queryStr.append(" JOIN cac.canalAtendimento ca ");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			queryStr.append(" WHERE cac.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito ");
			params.put("idCabecalhoAnaliseCredito", cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			
			if(cabecalhoAnaliseCreditoTO.getCanalAtendimento() != null
					&& cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento() != null) {
				queryStr.append(" AND ca.idCanalAtendimento = :idCanalAtendimento ");
				params.put("idCanalAtendimento", cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento());
			}
			if(categorizacaoAnaliseCreditoTO.getIdCategoria() != null ){
				queryStr.append(" AND plcs.categoriaScore.idCategoriaScore = :idCategoriaScore ");
				params.put("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
			}
			if(categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")){
				queryStr.append(" AND upper(plcs.plano.nmComercial) LIKE upper(:nmComercial) ");
				params.put("nmComercial", "%"+categorizacaoAnaliseCreditoTO.getNome().replaceAll("%","")+"%");
			}
			
			result = new PlanoConfiguracaoScoreTOBuilder().createPlanoConfiguracaoScoreTOList(setQueryParameters(em.createQuery(queryStr.toString()), params).getResultList());
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idPlanoCategoriaScore, Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException {
		StringBuilder jpql = new StringBuilder();
		Map<String, Boolean> configuracaoScoreMap = new TreeMap<String, Boolean>();
		if(idsRegionais == null || idsRegionais.length == 0){
			idsRegionais = new Integer[1];
			idsRegionais[0] = 0;
		}
		jpql.append(
				" SELECT plcf " +
				" FROM PlanoConfiguracaoScore plcf " +
				" WHERE plcf.planoCategoriaScore.idPlanoCategoriaScore = :idPlanoCategoriaScore " +
				" AND plcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito "
		);
		
		try {
			List<PlanoConfiguracaoScore> configScoreList = 
				em.createQuery(jpql.toString())
					.setParameter("idPlanoCategoriaScore",idPlanoCategoriaScore)
					.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito)
					.getResultList();
			List<AnaliseCredito> scores = em.createQuery("SELECT ac FROM AnaliseCredito ac ").getResultList();
			
			for (Integer idRegional : idsRegionais) {
				for (AnaliseCredito score : scores) {
					Boolean scoreConfigurado = false;
					for (PlanoConfiguracaoScore configScore : configScoreList) {
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
					
					String key = new PlanoConfiguracaoScoreTO(idCabecalhoAnaliseCredito, idPlanoCategoriaScore, score.getIdAnaliseCredito(), idRegional).getChaveConfiguracaoScore();
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
	public void recriaConfiguracaoPorCabecalhoECategoria(List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTOList, List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTORemoveList) throws DAOException {
		Set<Map<String, Integer>> categorizacoesARecriar = new HashSet<Map<String,Integer>>();
		List<PlanoConfiguracaoScore> entitiesConfigARemover = new ArrayList<PlanoConfiguracaoScore>();
		for (PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO : planoConfiguracaoScoreTORemoveList) {
			Map<String, Integer> elem = new HashMap<String, Integer>();
			elem.put("idCabecalhoAnaliseCredito", planoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			elem.put("idCategorizacao", planoConfiguracaoScoreTO.getPlanoCategoriaScoreTO().getIdPlanoCategoriaScore());
			if(planoConfiguracaoScoreTO.getRegionalTO() != null){
				elem.put("idRegional", planoConfiguracaoScoreTO.getRegionalTO().getIdRegional());	
			}
			categorizacoesARecriar.add(elem);
		}
		for (Map<String, Integer> categPorRegional : categorizacoesARecriar) {
			StringBuilder sqlCategorizacaoAAtualizar = new StringBuilder(
					"SELECT plcf " +
					"FROM PlanoConfiguracaoScore plcf " +
					"WHERE plcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito " +
					"AND plcf.planoCategoriaScore.idPlanoCategoriaScore = :idCategorizacao "
			);
			if(categPorRegional.get("idRegional") == null || categPorRegional.get("idRegional").equals(0))
				sqlCategorizacaoAAtualizar.append("AND plcf.regional.idRegional IS NULL ");
			else
				sqlCategorizacaoAAtualizar.append("AND plcf.regional.idRegional = :idRegional ");
			Query queryEntitiesARemover = em.createQuery(sqlCategorizacaoAAtualizar.toString())
				.setParameter("idCabecalhoAnaliseCredito", categPorRegional.get("idCabecalhoAnaliseCredito"))
				.setParameter("idCategorizacao", categPorRegional.get("idCategorizacao"));
			if(categPorRegional.get("idRegional") != null && !categPorRegional.get("idRegional").equals(0)){
				queryEntitiesARemover.setParameter("idRegional", categPorRegional.get("idRegional"));
			};
			entitiesConfigARemover.addAll(queryEntitiesARemover.getResultList());
		}
		
		try {
			for (PlanoConfiguracaoScore ent : entitiesConfigARemover) {
				em.remove(ent);
			}
			em.flush();
			
			List<PlanoConfiguracaoScore> entitiesConfigAGravar = new PlanoConfiguracaoScoreTOBuilder().createPlanoConfiguracaoScoreList(planoConfiguracaoScoreTOList);
			for (PlanoConfiguracaoScore ent : entitiesConfigAGravar) {
				em.persist(ent);
			}
			em.flush();
			System.out.println(entitiesConfigARemover);			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	private Query setQueryParameters(Query query, Map<String, Object> params){
		for(String param : params.keySet()){
			query.setParameter(param, params.get(param));
		}
		return query;
	}
}
