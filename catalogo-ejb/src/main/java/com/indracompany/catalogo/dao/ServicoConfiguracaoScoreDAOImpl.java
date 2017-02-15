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

import com.indracompany.catalogo.dao.interfaces.ServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.ServicoConfiguracaoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Servico Configuracao Score.
 */
@Stateless
public class ServicoConfiguracaoScoreDAOImpl implements ServicoConfiguracaoScoreDAO {
	
	private static Logger logger = Logger.getLogger(ServicoConfiguracaoScoreDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ServicoConfiguracaoScoreDAO#existAssociationServico(java.lang.Integer)
	 */
	public Boolean existAssociationServico(Integer idServico) throws DAOException {
		logger.debug("idServico: " + idServico);
		
		Boolean retorno = true;
		
		try {
			
			Query query = em.createQuery("select scs from ServicoConfiguracaoScore scs where scs.servicoCategoriaScore.servico.idServico = :idServico");
			query.setParameter("idServico", idServico);
			query.getSingleResult();
			
		} catch (NoResultException e) {
			retorno = false;
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [existAssociationServico]", e);
		}
	    return retorno;
	}

	
	public void createUpdateServicoConfiguracaoScoreList(List<ServicoConfiguracaoScoreTO> list) throws DAOException {
		for (ServicoConfiguracaoScoreTO scoreTO : list) {
			this.createUpdateServicoConfiguracaoScore(scoreTO);
		}
	}

	public void createUpdateServicoConfiguracaoScore(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("servicoConfiguracaoScoreTO: " + servicoConfiguracaoScoreTO);
		
		ServicoConfiguracaoScoreTOBuilder builder = new ServicoConfiguracaoScoreTOBuilder();
		
		try {
			em.merge(builder.createServicoConfiguracaoScore(servicoConfiguracaoScoreTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public ServicoConfiguracaoScoreTO findById(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("servicoConfiguracaoScoreTO: " + servicoConfiguracaoScoreTO);
		
		ServicoConfiguracaoScoreTO result = null;
		ServicoConfiguracaoScoreTOBuilder builder = new ServicoConfiguracaoScoreTOBuilder();
		
		try {
			result = builder.createServicoConfiguracaoScoreTO(em.find(ServicoConfiguracaoScore.class, servicoConfiguracaoScoreTO.getIdServicoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

	public void removeServicoConfiguracaoScore(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("servicoConfiguracaoScoreTO: " + servicoConfiguracaoScoreTO);
		
		try {
			em.remove(em.find(ServicoConfiguracaoScore.class, servicoConfiguracaoScoreTO.getIdServicoConfiguracaoScore()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	public void removeServicoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException {
		logger.debug("idCabecalho: " + idCabecalho);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito ");
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalho);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeServicoConfiguracaoScoreByConstraint(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException {
		logger.debug("servicoConfiguracaoScoreTO: " + servicoConfiguracaoScoreTO);
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito  ");
			queryStr.append(" AND IDSERVICOCATEGORIASCORE = :idServicoCategoriaScore  ");
			queryStr.append(" AND IDANALISECREDITO = :idAnaliseCredito  ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", servicoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			query.setParameter("idServicoCategoriaScore", servicoConfiguracaoScoreTO.getServicoCategoriaScoreTO().getIdServicoCategoriaScore());
			query.setParameter("idAnaliseCredito", servicoConfiguracaoScoreTO.getAnaliseCreditoTO().getIdAnaliseCredito());
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeServicoConfiguracaoScoreByServicoCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idServicoCategoriaScoreList) throws DAOException {
		logger.debug(" idCabecalhoAnaliseCredito " +  idCabecalhoAnaliseCredito  + " idServicoCategoriaScoreList: " + idServicoCategoriaScoreList);
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito  ");
			queryStr.append(" AND IDSERVICOCATEGORIASCORE IN (:idServicoCategoriaScoreList) ");
			queryStr.append(" AND IDREGIONAL IS NULL ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idServicoCategoriaScoreList", idServicoCategoriaScoreList);
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeServicoConfiguracaoScoreByServicoCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idServicoCategoriaScore, Integer idRegional) throws DAOException {
		logger.debug(" idCabecalhoAnaliseCredito " +  idCabecalhoAnaliseCredito  + " idServicoCategoriaScore: " + idServicoCategoriaScore + " idRegional: " + idRegional);
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" DELETE FROM CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE ");
			queryStr.append(" WHERE IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito  ");
			queryStr.append(" AND IDSERVICOCATEGORIASCORE IN (:idServicoCategoriaScoreList) ");
			queryStr.append(" AND IDREGIONAL = :idRegional ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			query.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito);
			query.setParameter("idServicoCategoriaScoreList", idServicoCategoriaScore);
			query.setParameter("idRegional", idRegional);
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


	@SuppressWarnings("unchecked")
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idServicoCategoriaScore, Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException {
		StringBuilder jpql = new StringBuilder();
		Map<String, Boolean> configuracaoScoreMap = new TreeMap<String, Boolean>();
		if(idsRegionais == null || idsRegionais.length == 0){
			idsRegionais = new Integer[1];
			idsRegionais[0] = 0;
		}
		jpql.append(
				" SELECT svcf " +
				" FROM ServicoConfiguracaoScore svcf " +
				" WHERE svcf.servicoCategoriaScore.idServicoCategoriaScore = :idServicoCategoriaScore " +
				" AND svcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito "
		);
		
		try {
			List<ServicoConfiguracaoScore> configScoreList = 
				em.createQuery(jpql.toString())
					.setParameter("idServicoCategoriaScore",idServicoCategoriaScore)
					.setParameter("idCabecalhoAnaliseCredito", idCabecalhoAnaliseCredito)
					.getResultList();
			List<AnaliseCredito> scores = em.createQuery("SELECT ac FROM AnaliseCredito ac ").getResultList();
			
			for (Integer idRegional : idsRegionais) {
				for (AnaliseCredito score : scores) {
					Boolean scoreConfigurado = false;
					for (ServicoConfiguracaoScore configScore : configScoreList) {
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
					
					String key = new ServicoConfiguracaoScoreTO(idCabecalhoAnaliseCredito, idServicoCategoriaScore, score.getIdAnaliseCredito(), idRegional).getChaveConfiguracaoScore();
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
	public void recriaConfiguracaoPorCabecalhoECategoria(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList, List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTORemoveList) throws DAOException {
		Set<Map<String, Integer>> categorizacoesARecriar = new HashSet<Map<String,Integer>>();
		List<ServicoConfiguracaoScore> entitiesConfigARemover = new ArrayList<ServicoConfiguracaoScore>();
		for (ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO : servicoConfiguracaoScoreTORemoveList) {
			Map<String, Integer> elem = new HashMap<String, Integer>();
			elem.put("idCabecalhoAnaliseCredito", servicoConfiguracaoScoreTO.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			elem.put("idCategorizacao", servicoConfiguracaoScoreTO.getServicoCategoriaScoreTO().getIdServicoCategoriaScore());
			if(servicoConfiguracaoScoreTO.getRegionalTO() != null){
				elem.put("idRegional", servicoConfiguracaoScoreTO.getRegionalTO().getIdRegional());
			}
			categorizacoesARecriar.add(elem);
		}
		for (Map<String, Integer> categPorRegional : categorizacoesARecriar) {
			StringBuilder sqlCategorizacaoAAtualizar = new StringBuilder(
					"SELECT svcf " +
					"FROM ServicoConfiguracaoScore svcf " +
					"WHERE svcf.cabecalhoAnaliseCredito.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito " +
					"AND svcf.servicoCategoriaScore.idServicoCategoriaScore = :idCategorizacao "
			);
			if(categPorRegional.get("idRegional") == null || categPorRegional.get("idRegional").equals(0))
				sqlCategorizacaoAAtualizar.append("AND svcf.regional.idRegional IS NULL ");
			else
				sqlCategorizacaoAAtualizar.append("AND svcf.regional.idRegional = :idRegional ");
			Query queryEntitiesARemover = em.createQuery(sqlCategorizacaoAAtualizar.toString())
				.setParameter("idCabecalhoAnaliseCredito", categPorRegional.get("idCabecalhoAnaliseCredito"))
				.setParameter("idCategorizacao", categPorRegional.get("idCategorizacao"));
			if(categPorRegional.get("idRegional") != null && !categPorRegional.get("idRegional").equals(0)){
				queryEntitiesARemover.setParameter("idRegional", categPorRegional.get("idRegional"));
			};
			entitiesConfigARemover.addAll(queryEntitiesARemover.getResultList());
		}
		
		try {
			for (ServicoConfiguracaoScore ent : entitiesConfigARemover) {
				em.remove(ent);
			}
			em.flush();
			
			List<ServicoConfiguracaoScore> entitiesConfigAGravar = new ServicoConfiguracaoScoreTOBuilder().createServicoConfiguracaoScoreList(servicoConfiguracaoScoreTOList);
			for (ServicoConfiguracaoScore ent : entitiesConfigAGravar) {
				em.persist(ent);
			}
			em.flush();
			System.out.println(entitiesConfigARemover);			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}	
}