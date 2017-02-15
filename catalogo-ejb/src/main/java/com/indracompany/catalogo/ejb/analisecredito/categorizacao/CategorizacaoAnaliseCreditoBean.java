package com.indracompany.catalogo.ejb.analisecredito.categorizacao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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

import com.indracompany.catalogo.dao.interfaces.OfertaServicoCategoriaScoreDAO;
import com.indracompany.catalogo.dao.interfaces.OfertaServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.dao.interfaces.OfertaServicoDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoCategoriaScoreDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoConfiguracaoScoreDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoCategoriaScoreDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

/**
 * @author Luiz Pereira
 * 
 * EJB responsável por realizar as funções de Categorização de Analise de Crédito.
 */
@Stateless(name = "CategorizacaoAnaliseCreditoBean", mappedName = "CategorizacaoAnaliseCreditoBean")
@Session(ejbName = "CategorizacaoAnaliseCreditoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategorizacaoAnaliseCreditoBean implements CategorizacaoAnaliseCreditoBeanLocal {
	
	private static Logger logger = Logger.getLogger(CategorizacaoAnaliseCreditoBean.class);
	
	@EJB
	private OfertaServicoDAO ofertaComplementarDAO;
	
	@EJB
	private PlanoDAO planoDAO;
	
	@EJB
	private ServicoDAO servicoDAO;
	
	@EJB
	private OfertaServicoCategoriaScoreDAO ofertaServicoCategoriaScoreDAO;
	
	@EJB
	private ServicoCategoriaScoreDAO servicoCategoriaScoreDAO;
	
	@EJB
	private PlanoCategoriaScoreDAO planoCategoriaScoreDAO;
	
	@EJB
	private PlanoConfiguracaoScoreDAO planoConfiguracaoScoreDAO;
	
	@EJB
	private ServicoConfiguracaoScoreDAO servicoConfiguracaoScoreDAO;
	
	@EJB
	private OfertaServicoConfiguracaoScoreDAO ofertaServicoConfiguracaoScoreDAO;
    
    @EJB
    private ServicoFixaDAO servicoFixaDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchServico(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = servicoDAO.searchServicoAnaliseCredito(categorizacaoAnaliseCreditoTO);
		} catch (DAOException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchServico(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = servicoDAO.searchServicoConfigAnaliseCredito(categorizacaoAnaliseCreditoTO);
			for (CategorizacaoAnaliseCreditoTO to : list) {
				to.setIdCabecalhoAnaliseCredito(categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
				to.setScoresConfigurados(servicoConfiguracaoScoreDAO.getConfiguracaoScoreMap(
						to.getIdCategoriaScore()
						, categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()
						, categorizacaoAnaliseCreditoTO.getIdRegionais()
				));
			}			
		} catch (DAOException e) {
			logger.error(e);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchOfertaComplementar(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlano(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = planoDAO.searchPlanoAnaliseCredito(categorizacaoAnaliseCreditoTO);
		} catch (DAOException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchOfertaComplementar(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = planoDAO.searchPlanoConfigAnaliseCredito(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoTO != null
					&& categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() != null
					&& categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() > 0) {
				for (CategorizacaoAnaliseCreditoTO to : list) {
					to.setIdCabecalhoAnaliseCredito(categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
					to.setScoresConfigurados(planoConfiguracaoScoreDAO.getConfiguracaoScoreMap(
							to.getIdCategoriaScore()
							, categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()
							, categorizacaoAnaliseCreditoTO.getIdRegionais()
					));
				}
			}
		} catch (DAOException e) {
			logger.error(e);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchPlano(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServico(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = ofertaComplementarDAO.searchOfertaServicoAnaliseCredito(categorizacaoAnaliseCreditoTO);
		} catch (DAOException e) {
			logger.error(e);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchOfertaServicoConfig(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoConfig(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		
		List<CategorizacaoAnaliseCreditoTO> list = null;
		
		try {
			list = ofertaComplementarDAO.searchOfertaServicoConfigAnaliseCredito(categorizacaoAnaliseCreditoTO);
			for (CategorizacaoAnaliseCreditoTO to : list) {
				to.setIdCabecalhoAnaliseCredito(categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
				to.setScoresConfigurados(ofertaServicoConfiguracaoScoreDAO.getConfiguracaoScoreMap(
						to.getIdCategoriaScore()
						, categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()
						, categorizacaoAnaliseCreditoTO.getIdRegionais()
				));
			}
		} catch (DAOException e) {
			logger.error(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#createServicoCategoriaScore(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO, java.lang.String, java.util.List)
	 */
	public void createServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException {
		
		 try {
			 	ServicoCategoriaScoreTO servicoCategoriaScoreTO = null;
				
				for (Integer id : ids) {
					
					servicoCategoriaScoreTO = new ServicoCategoriaScoreTO();
					servicoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(categorizacaoAnaliseCreditoTO.getIdCategoria()));
					servicoCategoriaScoreTO.setDtUsuarioCriacao(new Date());
					servicoCategoriaScoreTO.setIdServico(id);
					servicoCategoriaScoreTO.setNmUsuarioCriacao(user);
					
					servicoCategoriaScoreDAO.removeByServico(servicoCategoriaScoreTO);
					servicoCategoriaScoreDAO.createServicoCategoriaScore(servicoCategoriaScoreTO);
					
				}
			} catch (DAOException e) {
				logger.error(e);
			}
		
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#createOfertaServicoCategoriaScore(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO, java.lang.String, java.util.List)
	 */
	public void createOfertaServicoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException {
		
		try {
			OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO = null;
			
			for (Integer id : ids) {
				
				ofertaServicoCategoriaScoreTO = new OfertaServicoCategoriaScoreTO();
				ofertaServicoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(categorizacaoAnaliseCreditoTO.getIdCategoria()));
				ofertaServicoCategoriaScoreTO.setDtUsuarioCriacao(new Date());
				ofertaServicoCategoriaScoreTO.setIdOfertaServico(id);
				ofertaServicoCategoriaScoreTO.setNmUsuarioCriacao(user);
				
				ofertaServicoCategoriaScoreDAO.removeByOfertaServico(ofertaServicoCategoriaScoreTO);
				ofertaServicoCategoriaScoreDAO.createOfertaServicoCategoriaScore(ofertaServicoCategoriaScoreTO);
				
			}
		} catch (DAOException e) {
			logger.error(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#createPlanoCategoriaScore(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO, java.lang.String, java.util.List)
	 */
	public void createPlanoCategoriaScore(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO, String user, List<Integer> ids) throws BusinessException {
		
		try {
			PlanoCategoriaScoreTO planoCategoriaScoreTO = null;
			
			for (Integer id : ids) {
				
				planoCategoriaScoreTO = new PlanoCategoriaScoreTO();
				planoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(categorizacaoAnaliseCreditoTO.getIdCategoria()));
				planoCategoriaScoreTO.setDtUsuarioCriacao(new Date());
				planoCategoriaScoreTO.setIdPlano(id);
				planoCategoriaScoreTO.setNmUsuarioCriacao(user);
				
				planoCategoriaScoreDAO.removeByPlano(planoCategoriaScoreTO);
				planoCategoriaScoreDAO.createPlanoCategoriaScore(planoCategoriaScoreTO);
				
			}
		} catch (DAOException e) {
			logger.error(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#desassociarServicoCategoriaScore(java.util.List)
	 */
	public void desassociarServicoCategoriaScore(List<Integer> ids) throws BusinessException {
		try {
			for (Integer id : ids) {
				if (!servicoConfiguracaoScoreDAO.existAssociationServico(id)) {
					servicoCategoriaScoreDAO.removeByServico(new ServicoCategoriaScoreTO(id));
				} else {
					throw new BusinessException("J&aacute; existe An&aacute;lise de Cr&eacutedito configurada para essa associa&ccedil;&atilde;o.");
				}
			}
		} catch (DAOException e) {
			logger.error(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#desassociarOfertaServicoCategoriaScore(java.util.List)
	 */
	public void desassociarOfertaServicoCategoriaScore(List<Integer> ids) throws BusinessException {
		try {
			for (Integer id : ids) {
				if (!ofertaServicoConfiguracaoScoreDAO.existAssociationOfertaServico(id)) {
					ofertaServicoCategoriaScoreDAO.removeByOfertaServico(new OfertaServicoCategoriaScoreTO(id));
				} else {
					throw new BusinessException("J&aacute; existe An&aacute;lise de Cr&eacutedito configurada para essa associa&ccedil;&atilde;o.");
				}
			}
		} catch (DAOException e) {
			logger.error(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#desassociarPlanoCategoriaScore(java.util.List)
	 */
	public void desassociarPlanoCategoriaScore(List<Integer> ids) throws BusinessException {
		try {
			for (Integer id : ids) {
				if (!planoConfiguracaoScoreDAO.existAssociationPlano(id)) {
					planoCategoriaScoreDAO.removeByPlano(new PlanoCategoriaScoreTO(id));
				} else {
					throw new BusinessException("J&aacute; existe An&aacute;lise de Cr&eacutedito configurada para essa associa&ccedil;&atilde;o.");
				}
			}
		} catch (DAOException e) {
			logger.error(e);
		}
	}
    
    /* (non-Javadoc)
     * @see com.indracompany.catalogo.ejb.analisecredito.categorizacao.CategorizacaoAnaliseCreditoBeanLocal#searchServicoFixa(com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO)
     */
    public List<CategorizacaoAnaliseCreditoTO> searchServicoFixa(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
        
        List<CategorizacaoAnaliseCreditoTO> list = null;
        
        try {
            list = servicoFixaDAO.searchServicoFixaAnaliseCredito(categorizacaoAnaliseCreditoTO);
        } catch (DAOException e) {
            logger.error(e);
        }
        
        return list;
    }    
}
