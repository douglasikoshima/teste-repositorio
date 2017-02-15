package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.GrupoProdutoTOBuilder;
import com.indracompany.catalogo.dao.MultimidiaTOBuilder;
import com.indracompany.catalogo.ejb.grupoproduto.GrupoProdutoBeanLocal;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class GrupoProdutoDelegate {
	
	private static Logger logger = Logger.getLogger(GrupoProdutoDelegate.class);
	
	
	/**
	 * @param idCaracteristica
	 * @return
	 */
	public List<GrupoProdutoTO> findByCaracteristica(Integer idCaracteristica) {
		logger.info("idCaracteristica: " + idCaracteristica);
		
		List<GrupoProdutoTO> grupoProdutoTOList = null;
		
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoTOList = grupoProdutoBeanLocal.findByCaracteristica(idCaracteristica);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return grupoProdutoTOList;
	}
	
	/**
	 * @param idValorCaracteristica
	 * @return
	 */
	public List<GrupoProdutoTO> findByValorCaracteristica(Integer idValorCaracteristica) {
		logger.info("idValorCaracteristica: " + idValorCaracteristica);
		
		List<GrupoProdutoTO> grupoProdutoTOList = null;
		
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoTOList = grupoProdutoBeanLocal.findByValorCaracteristica(idValorCaracteristica);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findByValorCaracteristica]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return grupoProdutoTOList;
	}
	
	/**
	 * @return
	 */
	public List<GrupoProdutoTO> findAll() {
		logger.info("findAll: ");
		
		List<GrupoProdutoTO> grupoProdutoTOList = null;
		
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoTOList = grupoProdutoBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return grupoProdutoTOList;
	}
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 */
	public List<GrupoProdutoTO> search(GrupoProdutoTO grupoProdutoTO) {
		logger.info("grupoProdutoTO: " + grupoProdutoTO);
		
		List<GrupoProdutoTO> grupoProdutoTOList = null;
		
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoTOList = grupoProdutoBeanLocal.search(grupoProdutoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [search]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return grupoProdutoTOList;
	}
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 */
	public GrupoProdutoTO findById(GrupoProdutoTO grupoProdutoTO) {
		logger.info("grupoProdutoTO: " + grupoProdutoTO);
		
		GrupoProdutoTO grupoProdutoResultTO = null;
		
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoResultTO = grupoProdutoBeanLocal.findById(grupoProdutoTO);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return grupoProdutoResultTO;
	}
	
	public GrupoProdutoTO findById(Integer idGrupoProduto, GrupoProdutoTOBuilder grupoProdutoTOBuilder) {
		GrupoProdutoTO grupoProdutoTO = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			grupoProdutoTO = grupoProdutoBeanLocal.findById(idGrupoProduto, grupoProdutoTOBuilder);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return grupoProdutoTO;
	}
	
	public ParametrizacaoProdutosTO recuperarModelos(ParametrizacaoProdutosTO pp) {
		
		try {
			return ((GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME)).recuperarModelos(pp);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		}
		return null;
	}
	
	public boolean vincularProdutosGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		try {
			return ((GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME)).vincularProdutosGrupoProduto(pp);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		}
		return false;
	}
	
	public boolean removerVinculoGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		try {
			return ((GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME)).removerVinculoGrupoProduto(pp);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		}
		return false;
	}
	
	public List<TipoFrequenciaTO> obterTipoFrequenciaTOList(Integer idGrupoProduto, Integer idTecnologia) throws BusinessException {
		List<TipoFrequenciaTO> tipoFrequenciaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			tipoFrequenciaTOList = grupoProdutoBeanLocal.obterTipoFrequenciaTOList(idGrupoProduto, idTecnologia);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterTipoFrequenciaTOList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return tipoFrequenciaTOList;
	}
	
	public List<CaracteristicaTO> obterCaracteristicaTOList(Integer idGrupoProduto) throws BusinessException {
		List<CaracteristicaTO> tipoFrequenciaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			tipoFrequenciaTOList = grupoProdutoBeanLocal.obterCaracteristicaTOList(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterCaracteristicaTOList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return tipoFrequenciaTOList;
	}
	
	public List<ProdutoTO> obterProdutoTOList(Integer idGrupoProduto) {
		List<ProdutoTO> produtoTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			produtoTOList = grupoProdutoBeanLocal.obterProdutoTOList(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterProdutoTOList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return produtoTOList;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto) {
		List<MultimidiaTO> multimidiaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			multimidiaTOList = grupoProdutoBeanLocal.obterMultimidiaTOList(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterMultimidiaTOList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return multimidiaTOList;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto, MultimidiaTOBuilder multimidiaTOBuilder) {
		List<MultimidiaTO> multimidiaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			multimidiaTOList = grupoProdutoBeanLocal.obterMultimidiaTOList(idGrupoProduto, multimidiaTOBuilder);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterMultimidiaTOList]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return multimidiaTOList;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagem(Integer idGrupoProduto) {
		List<MultimidiaTO> multimidiaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			multimidiaTOList = grupoProdutoBeanLocal.obterMultimidiaTOListTipoImagem(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterMultimidiaTOListTipoImagem]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return multimidiaTOList;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagemOuVideo(Integer idGrupoProduto) {
		List<MultimidiaTO> multimidiaTOList = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			multimidiaTOList = grupoProdutoBeanLocal.obterMultimidiaTOListTipoImagemOuVideo(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterMultimidiaTOListTipoImagemOuVideo]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return multimidiaTOList;
	}
	
	public MultimidiaTO obterMultimidiaTOTipoLink(Integer idGrupoProduto) {
		MultimidiaTO multimidiaTO = null;
		try {
			GrupoProdutoBeanLocal grupoProdutoBeanLocal = (GrupoProdutoBeanLocal) ServiceLocator.getInstance().getEJBLocal(GrupoProdutoBeanLocal.JNDI_NAME);
			multimidiaTO = grupoProdutoBeanLocal.obterMultimidiaTOTipoLink(idGrupoProduto);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [obterMultimidiaTOTipoLink]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return multimidiaTO;
	}
	
}