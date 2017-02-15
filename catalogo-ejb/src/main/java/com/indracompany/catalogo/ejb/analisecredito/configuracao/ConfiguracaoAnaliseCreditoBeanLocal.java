package com.indracompany.catalogo.ejb.analisecredito.configuracao;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsavel em manter contrato localmente 
 * das funcionalidades de Analise de Credito com o EJB.  
 */
@Local
public interface ConfiguracaoAnaliseCreditoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ConfiguracaoAnaliseCreditoBean";
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 * Metodo responsavel em pesquisar uma Analise de Credito.
	 * @throws BusinessException 
	 */
	public List<CabecalhoAnaliseCreditoTO> searchAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * 
	 * Metodo responsavel em criar ou editar uma Analise de Credito.
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO createUpdateAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 * Metodo responsavel em retornar somente uma Analise de Credito.
	 */
	public CabecalhoAnaliseCreditoTO findById(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException
	 * 
	 * Metodo responsavel em remover uma analise a partir de um ID
	 */
	public void removeAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @return
	 * 
	 * Metodo responsavel em obter todos os Cabecalhos de Analise de Credito
	 */
	public List<CabecalhoAnaliseCreditoTO> findAll() throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException
	 */
	public void desativarAtivar(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithServicos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param categorizacaoAnaliseCreditoTO
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO, CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO findByIdWithOfertas(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @throws BusinessException 
	 */
	public List<CabecalhoAnaliseCreditoTO> findAllNoChild() throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 * 
	 * Metodo responsavel em criar ou editar um Plano Analise de Credito.
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO createUpdatePlanoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 * 
	 * Metodo responsavel em criar ou editar um Servico Analise de Credito.
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO createUpdateServicoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @param idCategoriaScore
	 * 
	 * Metodo responsavel em criar ou editar uma Oferta Analise de Credito.
	 * @throws BusinessException 
	 */
	public CabecalhoAnaliseCreditoTO createUpdateOfertaAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param cabecalhoAnaliseCreditoTO
	 * @return
	 * 
	 * Metodo responsavel em retornar somente uma Analise de Credito.
	 */
	public CabecalhoAnaliseCreditoTO findByIdNoChild(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException;
	
	///**
	// * 
	// * @param planoConfiguracaoScoreTOList
	// * @param planoConfiguracaoScoreTORemoveList TODO
	// * @throws BusinessException
	// */
	//public void recriaPlanoConfiguracaoAnaliseCredito(List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTOList, List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTORemoveList) throws BusinessException;

	/**
	 * 
	 * @param servicoConfiguracaoScoreTOList
	 * @param servicoConfiguracaoScoreTORemoveList
	 * @throws BusinessException
	 */
	public void recriaServicoConfiguracaoAnaliseCredito(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList, List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTORemoveList) throws BusinessException;
	/**
	 * 
	 * @param ofServicoConfiguracaoScoreTOList
	 * @param ofServicoConfiguracaoScoreTORemoveList TODO
	 * @throws BusinessException
	 */
	public void recriaOfertaServicoConfiguracaoAnaliseCredito(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTOList, List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTORemoveList) throws BusinessException;
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CanalAtendimentoTO> getCanaisConfiguraveisPorRegional() throws BusinessException;
}
