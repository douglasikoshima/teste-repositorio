package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.dao.GrupoProdutoTOBuilder;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 *
 */
public interface GrupoProdutoDAO {
	
	/**
	 * @param idCaracteristica
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter produtos por Caracteristica.
	 */
	public List<GrupoProdutoTO> findByCaracteristica(Integer idCaracteristica) throws DAOException;
	
	
	/**
	 * @param idValorCaracteristica
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter todos os Grupos Produto pelo valor da Caracteristica
	 */
	public List<GrupoProdutoTO> findByValorCaracteristica(Integer idValorCaracteristica) throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public List<GrupoProdutoTO> findAll() throws DAOException;
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 * @throws DAOException
	 */
	public List<GrupoProdutoTO> search(GrupoProdutoTO grupoProdutoTO) throws DAOException;
	
	/**
	 * 
	 * @param pesquisaTO
	 * @throws DAOException
	 */
	public void search(PesquisaGrupoProdutoTO pesquisaTO) throws DAOException;
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em obter um único Grupo Produto por sua Primary Key.
	 */
	public GrupoProdutoTO findById(GrupoProdutoTO grupoProdutoTO) throws DAOException;
	
	public GrupoProdutoTO findById(Integer idGrupoProduto, GrupoProdutoTOBuilder grupoProdutoTOBuilder) throws DAOException;
	
	public GrupoProduto findById(Integer idGrupoProduto) throws DAOException;
	
	public GrupoProduto findByNome(String nmGrupoProduto) throws DAOException;
	
	public void save(GrupoProduto grupoProduto) throws DAOException;
	
	public void save(GrupoProdutoTO grupoProdutoTO) throws DAOException;
	
	public void update(GrupoProduto grupoProduto) throws DAOException;
	
	public ParametrizacaoProdutosTO recuperarModelos(ParametrizacaoProdutosTO pp);
	
	public boolean vincularProdutosGrupoProduto(ParametrizacaoProdutosTO pp);
	
	public boolean removerVinculoGrupoProduto(ParametrizacaoProdutosTO pp);
	
	public void remove(GrupoProdutoTO to) throws DAOException;
	
	public void copy(GrupoProdutoTO to) throws DAOException;
	
	public List<TipoFrequenciaTO> obterTipoFrequenciaTOList(Integer idGrupoProduto, Integer idTecnologia) throws DAOException;
	
	public List<CaracteristicaTO> obterCaracteristicaTOList(Integer idGrupoProduto) throws DAOException;
	
	public List<ProdutoTO> obterProdutoTOList(Integer idGrupoProduto) throws DAOException;
	
}