package com.indracompany.catalogo.ejb.tipoproduto;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.TipoProdutoTOBuilder;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Tipo Produto com o EJB.  
 */
@Local
public interface TipoProdutoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/TipoProdutoBean";
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Tipos de Produtos
	 */
	public List<TipoProdutoTO> findAll() throws BusinessException;
	
	public List<TipoProdutoTO> findAll(TipoProdutoTOBuilder tipoProdutoTOBuilder) throws BusinessException;
	
	/**
	 * @return
	 * 
	 * Métodos responsável em obter todos os Tipos de Produtos
	 */
	public List<TipoProdutoTO> findAllButSimCards() throws BusinessException;
	
	/**
	 * @param tipoProdutoTO
	 * @return
	 * @throws BusinessException
	 */
	public TipoProdutoTO findById(TipoProdutoTO tipoProdutoTO) throws BusinessException;
}
