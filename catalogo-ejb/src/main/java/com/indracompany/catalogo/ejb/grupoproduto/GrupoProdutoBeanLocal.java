package com.indracompany.catalogo.ejb.grupoproduto;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.GrupoProdutoTOBuilder;
import com.indracompany.catalogo.dao.MultimidiaTOBuilder;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Grupo Produto com o EJB.  
 */
@Local
public interface GrupoProdutoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/GrupoProdutoBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter Grupo Produto a partir de uma caracteristica
	 */
	public List<GrupoProdutoTO> findByCaracteristica(Integer idCaracteristica) throws BusinessException;
	
	/**
	 * @param idValorCaracteristica
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em obter Grupos Produto pelo valor da Caracteristica.
	 */
	public List<GrupoProdutoTO> findByValorCaracteristica(Integer idValorCaracteristica) throws BusinessException;
	
	
	/**
	 * @return
	 * @throws BusinessException
	 */
	public List<GrupoProdutoTO> findAll() throws BusinessException;
	
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 * @throws BusinessException
	 */
	public List<GrupoProdutoTO> search(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 * @throws BusinessException
	 */
	public GrupoProdutoTO findById(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
	public GrupoProdutoTO findById(Integer idGrupoProduto, GrupoProdutoTOBuilder grupoProdutoTOBuilder) throws BusinessException;
	
	public ParametrizacaoProdutosTO recuperarModelos(ParametrizacaoProdutosTO pp) throws BusinessException;
	
	public boolean vincularProdutosGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException;
	
	public boolean removerVinculoGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException;
	
	public List<TipoFrequenciaTO> obterTipoFrequenciaTOList(Integer idGrupoProduto, Integer idTecnologia) throws BusinessException;
	
	public List<CaracteristicaTO> obterCaracteristicaTOList(Integer idGrupoProduto) throws BusinessException;
	
	public List<ProdutoTO> obterProdutoTOList(Integer idGrupoProduto) throws BusinessException;
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto) throws BusinessException;
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto, MultimidiaTOBuilder multimidiaTOBuilder) throws BusinessException;
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagem(Integer idGrupoProduto) throws BusinessException;
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagemOuVideo(Integer idGrupoProduto) throws BusinessException;
	
	public MultimidiaTO obterMultimidiaTOTipoLink(Integer idGrupoProduto) throws BusinessException;
	
}