package com.indracompany.catalogo.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public interface OfertaServicoConfiguracaoScoreDAO {
	
	/**
	 * @param idServico
	 * @return
	 * @throws DAOException
	 */
	public Boolean existAssociationOfertaServico(Integer idOfertaServico) throws DAOException;

	/**
	 * @param List<OfServicoConfiguracaoScoreTO>
	 * 
	 * Método responsável em criar/editar uma lista de Oferta servico configuracao score na base.
	 */
	public void createUpdateOfertaServicoConfiguracaoScoreList(List<OfServicoConfiguracaoScoreTO> list) throws DAOException;

	/**
	 * @param OfServicoConfiguracaoScoreTO
	 * 
	 * Método responsável em criar/editar uma Oferta servico configuracao score na base.
	 */
	public void createUpdateOfertaServicoConfiguracaoScore(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param ofServicoConfiguracaoScoreTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma OfServicoConfiguracaoScore na base.
	 */
	public OfServicoConfiguracaoScoreTO findById(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param ofServicoConfiguracaoScoreTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma OfServicoConfiguracaoScore na base
	 */
	public void removeOfServicoConfiguracaoScore(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException;

	/**
	 * @param idCabecalho
	 * @throws DAOException
	 */
	public void removeOfServicoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException;
	
	/**
	 * @param ofServicoConfiguracaoScoreTO
	 * @throws DAOException
	 */
	public void removeOfServicoConfiguracaoScoreByContraint(OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idOfertaCategoriaScoreList
	 * @throws DAOException
	 */
	public void removeOfServicoConfiguracaoScoreByOfertaCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idOfertaCategoriaScoreList) throws DAOException;

	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idOfertaCategoriaScore
	 * @param idRegional
	 * @throws DAOException
	 */
	public void removeOfServicoConfiguracaoScoreByOfertaCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idOfertaCategoriaScore, Integer idRegional) throws DAOException;
	
	/**
	 * @param idOfertaServicoCategoriaScore
	 * @param idCabecalhoAnaliseCredito
	 * @param idsRegionais
	 * @return
	 * @throws DAOException
	 */
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idOfertaServicoCategoriaScore, Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException;
	
	/**
	 * @param ofServicoConfiguracaoScoreTORemoveList TODO
	 * @param planoConfiguracaoScoreTOList
	 * @throws DAOException
	 */
	public void recriaConfiguracaoPorCabecalhoECategoria(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTOList, List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTORemoveList) throws DAOException;
}


