package com.indracompany.catalogo.ejb.produto;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.MultimidiaDAO;
import com.indracompany.catalogo.dao.interfaces.ProdutoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Produto
 */
@Stateless(name = "ProdutoBean", mappedName = "ProdutoBean")
@Session(ejbName = "ProdutoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProdutoBean implements ProdutoBeanLocal {

	private static Logger logger = Logger.getLogger(ProdutoBean.class);

	@EJB
	private ProdutoDAO produtoDAO;

	@EJB
	private MultimidiaDAO multimidiaDAO;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.produto.ProdutoBeanLocal#search(com.indracompany.catalogo.to.ProdutoTO)
	 */
	public List<ProdutoTO> search(ProdutoTO produtoTO) throws BusinessException {
		logger.debug("produtoTO: " + produtoTO);

		List<ProdutoTO> produtoTOList = null;

		try {
			produtoTOList = produtoDAO.search(produtoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return produtoTOList;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.produto.ProdutoBeanLocal#searchSemAssociacaoAcao(com.indracompany.catalogo.to.ProdutoTO, java.lang.Integer)
	 */
	public List<AssociacaoAcaoProdutoTO> searchSemAssociacaoAcao(ProdutoTO produtoTO, Integer idAcao) throws BusinessException {
		logger.debug("produtoTO: " + produtoTO);

		List<AssociacaoAcaoProdutoTO> produtoTOList = null;

		try {
			produtoTOList = produtoDAO.searchSemAssociacaoAcao(produtoTO, idAcao);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return produtoTOList;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.produto.ProdutoBeanLocal#searchComAssociacaoAcao(java.lang.Integer)
	 */
	public List<AssociacaoAcaoProdutoTO> searchComAssociacaoAcao(Integer idAcao) throws BusinessException {
		logger.debug("idAcao: " + idAcao);

		List<AssociacaoAcaoProdutoTO> produtoTOList = null;

		try {
			produtoTOList = produtoDAO.searchComAssociacaoAcao(idAcao);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return produtoTOList;
	}

	public List<ParametrizacaoProdutosTO> pesquisarParametrizacaoProdutos(ParametrizacaoProdutosTO pp)throws BusinessException {

		logger.debug("inicio - pesquisarParametrizacaoProdutos ");
		List<ParametrizacaoProdutosTO> produtos = null;
		try {

			produtos = produtoDAO.pesquisarParametrizacaoProdutos(pp);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return produtos;
	}
	public ParametrizacaoProdutosTO consultar(ProdutoTO produto) throws BusinessException{

		logger.debug("inicio - consultar ");
		ParametrizacaoProdutosTO pp = null;
		try {

			pp = produtoDAO.consultar(produto);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return pp;
	}

	public boolean atualizarProduto(ParametrizacaoProdutosTO pp) throws BusinessException{

		logger.debug("inicio - atualizarProduto ");
		try {
			ProdutoTO produtoTOParam = new ProdutoTO();
			produtoTOParam.setNmProduto(pp.getNomeProduto());
			List<ProdutoTO> produtoTOResultList = produtoDAO.search(produtoTOParam);
			for(ProdutoTO produtoTOElem: produtoTOResultList){
				if(!produtoTOElem.getIdProduto().equals(pp.getIdProduto())){
					throw new BusinessException("Nome Comercial j&aacute; existe");
				}
			}
			return produtoDAO.atualizarProduto(pp);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public List<MultimidiaTO> consultarMultimidida(ParametrizacaoProdutosTO pp) throws BusinessException {
		logger.debug("inicio - findById ");
		try {
			return multimidiaDAO.find(pp);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
}