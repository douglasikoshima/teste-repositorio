package com.indracompany.catalogo.ejb.grupoproduto;

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

import com.indracompany.catalogo.dao.GrupoProdutoTOBuilder;
import com.indracompany.catalogo.dao.MultimidiaTOBuilder;
import com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO;
import com.indracompany.catalogo.dao.interfaces.MultimidiaDAO;
import com.indracompany.catalogo.datalayer.TipoMultimidia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Servico Interatividade
 */
@Stateless(name = "GrupoProdutoBean", mappedName = "GrupoProdutoBean")
@Session(ejbName = "GrupoProdutoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GrupoProdutoBean implements GrupoProdutoBeanLocal {
	
	private static Logger logger = Logger.getLogger(GrupoProdutoBean.class);
	
	@EJB
	private GrupoProdutoDAO grupoProdutoDAO;
	@EJB
	private MultimidiaDAO multimidiaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.grupoproduto.GrupoProdutoBeanLocal#findByCaracteristica(java.lang.Integer)
	 */
	public List<GrupoProdutoTO> findByCaracteristica(Integer idCaracteristica) throws BusinessException {
		logger.debug("idCaracteristica: " + idCaracteristica);
		
		List<GrupoProdutoTO> list = null;
		
		try {
			list = grupoProdutoDAO.findByCaracteristica(idCaracteristica);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.grupoproduto.GrupoProdutoBeanLocal#findByValorCaracteristica(java.lang.Integer)
	 */
	public List<GrupoProdutoTO> findByValorCaracteristica(Integer idValorCaracteristica) throws BusinessException {
		logger.debug("idValorCaracteristica: " + idValorCaracteristica);
		
		List<GrupoProdutoTO> list = null;
		
		try {
			list = grupoProdutoDAO.findByValorCaracteristica(idValorCaracteristica);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.grupoproduto.GrupoProdutoBeanLocal#findAll()
	 */
	public List<GrupoProdutoTO> findAll() throws BusinessException {
		List<GrupoProdutoTO> list = null;
		
		try {
			list = grupoProdutoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.grupoproduto.GrupoProdutoBeanLocal#search(com.indracompany.catalogo.to.GrupoProdutoTO)
	 */
	public List<GrupoProdutoTO> search(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		List<GrupoProdutoTO> list = null;
		
		try {
			list = grupoProdutoDAO.search(grupoProdutoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	public GrupoProdutoTO findById(GrupoProdutoTO grupoProdutoTO) throws BusinessException {
		GrupoProdutoTO grupoProdutoResultTO = null;
		
		try {
			grupoProdutoResultTO = grupoProdutoDAO.findById(grupoProdutoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return grupoProdutoResultTO;
	}
	
	public GrupoProdutoTO findById(Integer idGrupoProduto, GrupoProdutoTOBuilder grupoProdutoTOBuilder) throws BusinessException {
		GrupoProdutoTO grupoProdutoTO = null;
		try {
			grupoProdutoTO = grupoProdutoDAO.findById(idGrupoProduto, grupoProdutoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return grupoProdutoTO;
	}
	
	public ParametrizacaoProdutosTO recuperarModelos(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		
		try {
			return grupoProdutoDAO.recuperarModelos(pp);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	public boolean vincularProdutosGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		try {
			return grupoProdutoDAO.vincularProdutosGrupoProduto(pp);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	public boolean removerVinculoGrupoProduto(ParametrizacaoProdutosTO pp) throws BusinessException {
		
		try {
			return grupoProdutoDAO.removerVinculoGrupoProduto(pp);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	public List<TipoFrequenciaTO> obterTipoFrequenciaTOList(Integer idGrupoProduto, Integer idTecnologia) throws BusinessException {
		List<TipoFrequenciaTO> list = null;
		try {
			list = grupoProdutoDAO.obterTipoFrequenciaTOList(idGrupoProduto, idTecnologia);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<CaracteristicaTO> obterCaracteristicaTOList(Integer idGrupoProduto) throws BusinessException {
		List<CaracteristicaTO> list = null;
		try {
			list = grupoProdutoDAO.obterCaracteristicaTOList(idGrupoProduto);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<ProdutoTO> obterProdutoTOList(Integer idGrupoProduto) throws BusinessException {
		List<ProdutoTO> list = null;
		try {
			list = grupoProdutoDAO.obterProdutoTOList(idGrupoProduto);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto) throws BusinessException {
		List<MultimidiaTO> list = null;
		try {
			list = multimidiaDAO.obterMultimidiaTOList(idGrupoProduto, null, new MultimidiaTOBuilder(false));
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto, MultimidiaTOBuilder multimidiaTOBuilder) throws BusinessException {
		List<MultimidiaTO> list = null;
		try {
			list = multimidiaDAO.obterMultimidiaTOList(idGrupoProduto, null, multimidiaTOBuilder);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagem(Integer idGrupoProduto) throws BusinessException {
		List<MultimidiaTO> list = null;
		try {
			list = multimidiaDAO.obterMultimidiaTOList(idGrupoProduto, new Integer[] {TipoMultimidia.ID_IMAGEM}, new MultimidiaTOBuilder(false));
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public List<MultimidiaTO> obterMultimidiaTOListTipoImagemOuVideo(Integer idGrupoProduto) throws BusinessException {
		List<MultimidiaTO> list = null;
		try {
			list = multimidiaDAO.obterMultimidiaTOList(idGrupoProduto, new Integer[] {TipoMultimidia.ID_IMAGEM, TipoMultimidia.ID_VIDEO}, new MultimidiaTOBuilder(false));
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return list;
	}
	
	public MultimidiaTO obterMultimidiaTOTipoLink(Integer idGrupoProduto) throws BusinessException {
		MultimidiaTO to = null;
		try {
			List<MultimidiaTO> list = multimidiaDAO.obterMultimidiaTOList(idGrupoProduto, new Integer[] {TipoMultimidia.ID_LINK}, new MultimidiaTOBuilder(false));
			to = (list != null && !list.isEmpty()) ? list.get(0) : null;
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return to;
	}
	
}