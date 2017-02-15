package com.indracompany.catalogo.ejb.analisecredito.categorizacao;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável por manter contrato localmente 
 * das funcionalidades de Categorização de Analise de Crédito com o EJB.  
 */
@Local
public interface CategorizacaoAnaliseCreditoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CategorizacaoAnaliseCreditoBean";

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws BusinessException
	 * 
	 * Método Responsável em pesquisar Serviços de acordo com os parâmetros informados.
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws BusinessException
	 * 
	 * Método Responsável em pesquisar Serviços de acordo com os parâmetros informados.
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws BusinessException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlano(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param idCabecalhoAnaliseCredito
	 * @param idRegional
	 * @return
	 * @throws BusinessException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws BusinessException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws BusinessException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @throws BusinessException
	 */
	public void createServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException;

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @throws BusinessException
	 */
	public void createOfertaServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException;

	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @throws BusinessException
	 */
	public void createPlanoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @param ids
	 * @throws BusinessException
	 */
	public void desassociarServicoCategoriaScore(List<Integer> ids) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @param ids
	 * @throws BusinessException
	 */
	public void desassociarOfertaServicoCategoriaScore(List<Integer> ids) throws BusinessException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @param user
	 * @param ids
	 * @throws BusinessException
	 */
	public void desassociarPlanoCategoriaScore(List<Integer> ids) throws BusinessException;

    /**
     * @param categorizacaoAnaliseCreditoTO
     * @return
     * @throws BusinessException
     * 
     * Método Responsável em pesquisar Serviços da Fixa de acordo com os parâmetros informados.
     */
    public List<CategorizacaoAnaliseCreditoTO> searchServicoFixa(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException;
}