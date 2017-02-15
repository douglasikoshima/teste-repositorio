package com.indracompany.catalogo.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public interface ServicoConfiguracaoScoreDAO {
	
	/**
	 * @param idServico
	 * @return
	 * @throws DAOException
	 */
	public Boolean existAssociationServico(Integer idServico) throws DAOException;
	
	/**
	 * @param ServicoConfiguracaoScoreTO
	 * 
	 * Método responsável em criar/editar uma lista de Servicos configuracao score na base.
	 */
	public void createUpdateServicoConfiguracaoScoreList(List<ServicoConfiguracaoScoreTO> list) throws DAOException;
 	
	/**
	 * @param ServicoConfiguracaoScoreTO
	 * 
	 * Método responsável em criar/editar um Servico configuracao score na base.
	 */
	public void createUpdateServicoConfiguracaoScore (ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException;

	/**
	 * @param servicoConfiguracaoScoreTO
	 * @return
	 * @throws DAOException 
	 *
	 * Método responsável em buscar uma ServicoConfiguracaoScore na base.
	 */
	public ServicoConfiguracaoScoreTO findById(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param servicoConfiguracaoScoreTO
	 * @throws DAOException
	 * 
	 * Método responsável em remover uma ServicoConfiguracaoScore na base
	 */
	public void removeServicoConfiguracaoScore(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException;
	
	
	/**
	 * @param idCabecalho
	 * @throws DAOException
	 */
	public void removeServicoConfiguracaoScoreByCabecalho(Integer idCabecalho) throws DAOException;
	
	/**
	 * @param servicoConfiguracaoScoreTO
	 * @throws DAOException
	 */
	public void removeServicoConfiguracaoScoreByConstraint(ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO) throws DAOException;
	
	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idServicoCategoriaScoreList
	 * @throws DAOException
	 */
	public void removeServicoConfiguracaoScoreByServicoCategoriaScoreId(Integer idCabecalhoAnaliseCredito, List<Integer> idServicoCategoriaScoreList) throws DAOException;
	
	/**
	 * @param idCabecalhoAnaliseCredito
	 * @param idServicoCategoriaScore
	 * @param idRegional
	 * @throws DAOException
	 */
	public void removeServicoConfiguracaoScoreByServicoCategoriaScoreIdRegional(Integer idCabecalhoAnaliseCredito, Integer idServicoCategoriaScore, Integer idRegional) throws DAOException;
	/**
	 * @param idPlanoCategoriaScore
	 * @param idCabecalhoAnaliseCredito
	 * @param idsRegionais
	 * @return
	 * @throws DAOException
	 */
	public Map<String, Boolean> getConfiguracaoScoreMap(Integer idServicoCategoriaScore,Integer idCabecalhoAnaliseCredito, Integer[] idsRegionais) throws DAOException;
	
	/**
	 * 
	 * @param planoConfiguracaoScoreTOList
	 * @param planoConfiguracaoScoreTORemoveList
	 * @throws DAOException
	 */
	public void recriaConfiguracaoPorCabecalhoECategoria(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList, List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTORemoveList) throws DAOException;	
}
