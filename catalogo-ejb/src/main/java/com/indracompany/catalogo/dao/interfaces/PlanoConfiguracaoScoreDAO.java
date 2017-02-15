package com.indracompany.catalogo.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public interface PlanoConfiguracaoScoreDAO {
	
	public Boolean existAssociationPlano(Integer idPlano) throws DAOException;
	
	/**
	 * @param List<PlanoConfiguracaoScoreTO>
	 * 
	 * Método responsável em criar/editar uma lista de plano configuracao score na base.
	 */
	public void createUpdatePlanoConfiguracaoScoreList(List<PlanoConfiguracaoScoreTO> list) throws DAOException;
 	
	/**
	 * @param PlanoConfiguracaoScoreTO
	 * 
	 * Método responsável em criar/editar um plano configuracao score na base.
	 */
	public void createUpdatePlanoConfiguracaoScore(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param PlanoConfiguracaoScoreTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma PlanoConfiguracaoScore na base.
	 */
	public PlanoConfiguracaoScoreTO findById(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param PlanoConfiguracaoScoreTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma PlanoConfiguracaoScore na base
	 */
	public void removePlanoConfiguracaoScore(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException;

	/**
	 * @param idCabecalho
	 * @throws DAOException
	 */
	public void removePlanoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException;
	
	/**
	 * @param planoConfiguracaoScoreTO
	 * @throws DAOException
	 */
	public void removePlanoConfiguracaoScoreByConstraint(PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idPlanoCategoriaScoreList
	 * @throws DAOException
	 */
	public void removePlanoConfiguracaoScoreByPlanoCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idPlanoCategoriaScoreList) throws DAOException;
	
	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idPlanoCategoriaScore
	 * @param idRegional
	 * @throws DAOException
	 */
	public void removePlanoConfiguracaoScoreByPlanoCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idPlanoCategoriaScore, Integer idRegional) throws DAOException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param categorizacaoAnaliseCreditoTO
	 * @return List<PlanoConfiguracaoScoreTO>
	 * @throws DAOException
	 */
	public List<PlanoConfiguracaoScoreTO> pesquisarConfiguracaoPorCategorizacao(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO, CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * 
	 * @param idPlanoCategoriaScore
	 * @param idCabecalhoAnaliseCredito
	 * @param idsRegionais
	 * @return
	 * @throws DAOException
	 */
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idPlanoCategoriaScore,Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException;

	/**
	 * @param planoConfiguracaoScoreTORemoveList TODO
	 * @param planoConfiguracaoScoreTO
	 * @throws DAOException
	 * Substitui a configuração de analise de credito para cada combinação de cabecalhoAnaliseCreditoTO, planoCategoriaScoreTO, regionalTO informados. 
	 */
	public void recriaConfiguracaoPorCabecalhoECategoria(List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTOList, List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTORemoveList) throws DAOException;
}
