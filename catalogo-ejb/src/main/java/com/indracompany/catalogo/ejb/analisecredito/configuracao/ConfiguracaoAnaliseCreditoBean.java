package com.indracompany.catalogo.ejb.analisecredito.configuracao;

import java.util.ArrayList;
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

import com.indracompany.catalogo.dao.CabecalhoAnaliseCreditoTOBuilder;
import com.indracompany.catalogo.dao.interfaces.CabecalhoAnaliseCreditoDAO;
import com.indracompany.catalogo.dao.interfaces.OfertaServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoConfiguracaoScoreDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoConfiguracaoScoreDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Analise de Credito
 */
@Stateless(name = "ConfiguracaoAnaliseCreditoBean", mappedName = "ConfiguracaoAnaliseCreditoBean")
@Session(ejbName = "ConfiguracaoAnaliseCreditoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConfiguracaoAnaliseCreditoBean implements ConfiguracaoAnaliseCreditoBeanLocal {
	
	private static Logger logger = Logger.getLogger(ConfiguracaoAnaliseCreditoBean.class);
	
	@EJB
	private CabecalhoAnaliseCreditoDAO cabecalhoAnaliseCreditoDAO;
	
	@EJB
	private PlanoConfiguracaoScoreDAO planoConfiguracaoScoreDAO;

	@EJB
	private ServicoConfiguracaoScoreDAO servicoConfiguracaoScoreDAO;
	
	@EJB
	private OfertaServicoConfiguracaoScoreDAO ofertaServicoConfiguracaoScoreDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#searchAnaliseCredito(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public List<CabecalhoAnaliseCreditoTO> searchAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		List<CabecalhoAnaliseCreditoTO> list = null; 
		
		try {
			
			list = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#createUpdateAnaliseCredito(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public CabecalhoAnaliseCreditoTO createUpdateAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultadoCriacaoTO = null;
		
		try {
			
			if (cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null ) {
				cabecalhoAnaliseCreditoTO.setInDisponivel("N");
				if (cabecalhoAnaliseCreditoDAO.findByName(cabecalhoAnaliseCreditoTO) != null) {
					throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o com esse nome.");
				}
				
				CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoSearchTO = new CabecalhoAnaliseCreditoTO();
				cabecalhoAnaliseCreditoSearchTO.setInDisponivel(cabecalhoAnaliseCreditoTO.getInDisponivel());
				cabecalhoAnaliseCreditoSearchTO.setCanalAtendimento(cabecalhoAnaliseCreditoTO.getCanalAtendimento());
				
				List<CabecalhoAnaliseCreditoTO> list = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoSearchTO); 
				if (list != null && list.size() > 0) {
					throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o Inativa para esse Canal de Atendimento.");
				}
			}
			
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
			cabecalhoAnaliseCreditoResultTO = cabecalhoAnaliseCreditoDAO.createUpdateCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);
			
			planoConfiguracaoScoreDAO.removePlanoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			servicoConfiguracaoScoreDAO.removeServicoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			ofertaServicoConfiguracaoScoreDAO.removeOfServicoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			
			for (PlanoConfiguracaoScoreTO planoConfiguracaoScoreTO : cabecalhoAnaliseCreditoTO.getPlanoConfiguracaoScoreList()) {
				planoConfiguracaoScoreTO.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				planoConfiguracaoScoreDAO.createUpdatePlanoConfiguracaoScore(planoConfiguracaoScoreTO);
			}
			
			for (ServicoConfiguracaoScoreTO servicoConfiguracaoScoreTO : cabecalhoAnaliseCreditoTO.getServicoConfiguracaoScoreList()) {
				servicoConfiguracaoScoreTO.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				servicoConfiguracaoScoreDAO.createUpdateServicoConfiguracaoScore(servicoConfiguracaoScoreTO);
			}
			
			for (OfServicoConfiguracaoScoreTO ofServicoConfiguracaoScoreTO : cabecalhoAnaliseCreditoTO.getOfServicoConfiguracaoScoreList()){
				ofServicoConfiguracaoScoreTO.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				ofertaServicoConfiguracaoScoreDAO.createUpdateOfertaServicoConfiguracaoScore(ofServicoConfiguracaoScoreTO); 
			}
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return cabecalhoAnaliseCreditoResultadoCriacaoTO;
	}
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#findById(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public CabecalhoAnaliseCreditoTO findById(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			result = cabecalhoAnaliseCreditoDAO.findById(cabecalhoAnaliseCreditoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#removeAnaliseCredito(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public void removeAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		try {
			planoConfiguracaoScoreDAO.removePlanoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			servicoConfiguracaoScoreDAO.removeServicoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			ofertaServicoConfiguracaoScoreDAO.removeOfServicoConfiguracaoScoreByCabecalho(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			
			cabecalhoAnaliseCreditoDAO.removeCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}


	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#findAll()
	 */
	public List<CabecalhoAnaliseCreditoTO> findAll() throws BusinessException {
		
		List<CabecalhoAnaliseCreditoTO> list = null; 
		
		try {
			list = cabecalhoAnaliseCreditoDAO.findAll();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.analisecredito.configuracao.ConfiguracaoAnaliseCreditoBeanLocal#desativarAtivar(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public void desativarAtivar(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		
		
		try {
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = cabecalhoAnaliseCreditoDAO.findById(cabecalhoAnaliseCreditoTO);
			
			if (cabecalhoAnaliseCreditoResultTO.getInDisponivel().equals("S")) {
				cabecalhoAnaliseCreditoResultTO.setInDisponivel("N");
				
				CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoSearchTO = new CabecalhoAnaliseCreditoTO();
				
				cabecalhoAnaliseCreditoSearchTO.setCanalAtendimento(cabecalhoAnaliseCreditoResultTO.getCanalAtendimento());
				cabecalhoAnaliseCreditoSearchTO.setInDisponivel("N");
				
				List<CabecalhoAnaliseCreditoTO> list = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoSearchTO);
				
				if (list != null && list.size() > 0) {
					throw new BusinessException("N&atilde;o &eacute; poss&iacute;vel desativar esta configura&ccedil;&atilde;o, j&aacute; existe configura&ccedil;&atilde;o desativada para este canal.");
				}
			} else {
				CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoSearchTO = new CabecalhoAnaliseCreditoTO();
				
				cabecalhoAnaliseCreditoSearchTO.setCanalAtendimento(cabecalhoAnaliseCreditoResultTO.getCanalAtendimento());
				cabecalhoAnaliseCreditoSearchTO.setInDisponivel("S");
				
				List<CabecalhoAnaliseCreditoTO> list = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoSearchTO);
				
				if (list != null && list.size() > 0) {
					throw new BusinessException("N&atilde;o &eacute; poss&iacute;vel ativar esta configura&ccedil;&atilde;o, j&aacute; existe configura&ccedil;&atilde;o ativa para este canal.");
				}
				
				cabecalhoAnaliseCreditoResultTO.setInDisponivel("S");
			}
			
			cabecalhoAnaliseCreditoDAO.createUpdateCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoResultTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder(false);
			cabecalhoAnaliseCreditoTOBuilder.setCriarCanalAtendimento(true);
			cabecalhoAnaliseCreditoTOBuilder.setCriarPlanoConfiguracaoScore(false);
			result = this.buscarPorId(cabecalhoAnaliseCreditoTO, cabecalhoAnaliseCreditoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	
	public CabecalhoAnaliseCreditoTO findByIdWithPlanos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO, CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO+" categorizacaoAnaliseCreditoTO: "+categorizacaoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder(false);
			cabecalhoAnaliseCreditoTOBuilder.setCriarCanalAtendimento(true);
			cabecalhoAnaliseCreditoTOBuilder.setCriarPlanoConfiguracaoScore(false);
			result = this.buscarPorId(cabecalhoAnaliseCreditoTO, cabecalhoAnaliseCreditoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	
	public CabecalhoAnaliseCreditoTO findByIdWithServicos(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder(false);
			cabecalhoAnaliseCreditoTOBuilder.setCriarCanalAtendimento(true);
			cabecalhoAnaliseCreditoTOBuilder.setCriarServicoConfiguracaoScore(false);
			cabecalhoAnaliseCreditoTOBuilder.setCriarOfServicoConfiguracaoScore(false);
			result = this.buscarPorId(cabecalhoAnaliseCreditoTO, cabecalhoAnaliseCreditoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	
	public CabecalhoAnaliseCreditoTO findByIdWithOfertas(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder(false);
			cabecalhoAnaliseCreditoTOBuilder.setCriarCanalAtendimento(true);
			cabecalhoAnaliseCreditoTOBuilder.setCriarOfServicoConfiguracaoScore(false);
			result = this.buscarPorId(cabecalhoAnaliseCreditoTO, cabecalhoAnaliseCreditoTOBuilder);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}
	
	private CabecalhoAnaliseCreditoTO buscarPorId(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO,
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder)
			throws BusinessException, DAOException {
		
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		CabecalhoAnaliseCreditoTO result = null;
  	    result = cabecalhoAnaliseCreditoDAO.findById(cabecalhoAnaliseCreditoTO, cabecalhoAnaliseCreditoTOBuilder);
		return result;
	}
	
	
	public List<CabecalhoAnaliseCreditoTO> findAllNoChild() throws BusinessException {
		
		List<CabecalhoAnaliseCreditoTO> list = null; 
		
		try {
			list = cabecalhoAnaliseCreditoDAO.findAllNoChild();
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	public CabecalhoAnaliseCreditoTO createUpdatePlanoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultadoCriacaoTO = null;

		try {
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = mergeCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);
		
			if(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null){
				for (PlanoConfiguracaoScoreTO plcs : cabecalhoAnaliseCreditoTO.getPlanoConfiguracaoScoreList()) {
					plcs.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				}
				planoConfiguracaoScoreDAO.createUpdatePlanoConfiguracaoScoreList(cabecalhoAnaliseCreditoTO.getPlanoConfiguracaoScoreList());
			} else {
				planoConfiguracaoScoreDAO.recriaConfiguracaoPorCabecalhoECategoria(cabecalhoAnaliseCreditoTO.getPlanoConfiguracaoScoreList() ,cabecalhoAnaliseCreditoTO.getPlanoConfiguracaoScoreListRemove());
			}
			cabecalhoAnaliseCreditoResultadoCriacaoTO = new CabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoResultadoCriacaoTO.setInDisponivel(cabecalhoAnaliseCreditoResultTO.getInDisponivel());
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return cabecalhoAnaliseCreditoResultadoCriacaoTO;
	}
	
	public CabecalhoAnaliseCreditoTO createUpdateServicoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultadoCriacaoTO = null;
		
		try {

			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = mergeCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);

			if(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null){
				for (ServicoConfiguracaoScoreTO scs : cabecalhoAnaliseCreditoTO.getServicoConfiguracaoScoreList()) {
					scs.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				}
				servicoConfiguracaoScoreDAO.createUpdateServicoConfiguracaoScoreList(cabecalhoAnaliseCreditoTO.getServicoConfiguracaoScoreList());
			} else {
				servicoConfiguracaoScoreDAO.recriaConfiguracaoPorCabecalhoECategoria(cabecalhoAnaliseCreditoTO.getServicoConfiguracaoScoreList(),cabecalhoAnaliseCreditoTO.getServicoConfiguracaoScoreListRemove());
			}
			
			cabecalhoAnaliseCreditoResultadoCriacaoTO = new CabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoResultadoCriacaoTO.setInDisponivel(cabecalhoAnaliseCreditoResultTO.getInDisponivel());
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return cabecalhoAnaliseCreditoResultadoCriacaoTO;
	}
	
	
	public CabecalhoAnaliseCreditoTO createUpdateOfertaAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultadoCriacaoTO = null;
		
		try {

			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = mergeCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);//new CabecalhoAnaliseCreditoTO();

			if(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null){
				for (OfServicoConfiguracaoScoreTO oscs : cabecalhoAnaliseCreditoTO.getOfServicoConfiguracaoScoreList()) {
					oscs.setCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO);
				}
				ofertaServicoConfiguracaoScoreDAO.createUpdateOfertaServicoConfiguracaoScoreList(cabecalhoAnaliseCreditoTO.getOfServicoConfiguracaoScoreList());
			} else {
				ofertaServicoConfiguracaoScoreDAO.recriaConfiguracaoPorCabecalhoECategoria(cabecalhoAnaliseCreditoTO.getOfServicoConfiguracaoScoreList(), cabecalhoAnaliseCreditoTO.getOfServicoConfiguracaoScoreListRemove());
			}
			
			cabecalhoAnaliseCreditoResultadoCriacaoTO = new CabecalhoAnaliseCreditoTO(cabecalhoAnaliseCreditoResultTO.getIdCabecalhoAnaliseCredito());
			cabecalhoAnaliseCreditoResultadoCriacaoTO.setInDisponivel(cabecalhoAnaliseCreditoResultTO.getInDisponivel());
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return cabecalhoAnaliseCreditoResultadoCriacaoTO;
	}
	
	public CabecalhoAnaliseCreditoTO findByIdNoChild(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws BusinessException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		
		try {
			result = cabecalhoAnaliseCreditoDAO.findByIdNoChild(cabecalhoAnaliseCreditoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return result;
	}

	public void recriaServicoConfiguracaoAnaliseCredito(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList, List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTORemoveList) throws BusinessException {
		try {
			servicoConfiguracaoScoreDAO.recriaConfiguracaoPorCabecalhoECategoria(servicoConfiguracaoScoreTOList, servicoConfiguracaoScoreTORemoveList);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	public void recriaOfertaServicoConfiguracaoAnaliseCredito(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTOList, List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreTORemoveList) throws BusinessException {
		try {
			ofertaServicoConfiguracaoScoreDAO.recriaConfiguracaoPorCabecalhoECategoria(ofServicoConfiguracaoScoreTOList, ofServicoConfiguracaoScoreTORemoveList);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	private CabecalhoAnaliseCreditoTO mergeCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException, BusinessException{
		
	    CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTOValidacaoNome = cabecalhoAnaliseCreditoDAO.findByName(cabecalhoAnaliseCreditoTO);
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoSearchTO = new CabecalhoAnaliseCreditoTO();
		List<CabecalhoAnaliseCreditoTO> listValidacaoCanal = null;
		
		if (cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito() == null ) {
			cabecalhoAnaliseCreditoTO.setInDisponivel("N");
			cabecalhoAnaliseCreditoSearchTO.setInDisponivel(cabecalhoAnaliseCreditoTO.getInDisponivel());
			cabecalhoAnaliseCreditoSearchTO.setCanalAtendimento(cabecalhoAnaliseCreditoTO.getCanalAtendimento());
			listValidacaoCanal = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoSearchTO);
			if (cabecalhoAnaliseCreditoTOValidacaoNome != null) {
				throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o com esse nome.");
			}
			if (listValidacaoCanal != null && listValidacaoCanal.size() > 0) {
				throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o para esse Canal de Atendimento.");
			}
		} else {
			cabecalhoAnaliseCreditoSearchTO.setInDisponivel(cabecalhoAnaliseCreditoTO.getInDisponivel());
			cabecalhoAnaliseCreditoSearchTO.setCanalAtendimento(cabecalhoAnaliseCreditoTO.getCanalAtendimento());
			listValidacaoCanal = cabecalhoAnaliseCreditoDAO.searchCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoSearchTO);
			if (cabecalhoAnaliseCreditoTOValidacaoNome != null 
					&& !cabecalhoAnaliseCreditoTOValidacaoNome.getNmCabecalhoAnaliseCredito().equals(cabecalhoAnaliseCreditoDAO.findById(cabecalhoAnaliseCreditoTO).getNmCabecalhoAnaliseCredito())) {
				throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o com esse nome.");
			}
			if (listValidacaoCanal != null && listValidacaoCanal.size() > 0) {
				Boolean cabecalhoAnaliseCreditoInvalido = true;
				for (CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTOValidacaoCanal : listValidacaoCanal) {
					if (cabecalhoAnaliseCreditoTOValidacaoCanal.getIdCabecalhoAnaliseCredito().equals(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito())) {
						cabecalhoAnaliseCreditoInvalido = false;
					}
				}
				if(cabecalhoAnaliseCreditoInvalido){
					throw new BusinessException("J&aacute; existe uma configura&ccedil;&atilde;o para esse Canal de Atendimento.");
				}
			}
		}
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = new CabecalhoAnaliseCreditoTO();
		cabecalhoAnaliseCreditoResultTO = cabecalhoAnaliseCreditoDAO.createUpdateCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO);
		return cabecalhoAnaliseCreditoResultTO;
	}

	public List<CanalAtendimentoTO> getCanaisConfiguraveisPorRegional() throws BusinessException {
		List<CanalAtendimentoTO> result = new ArrayList<CanalAtendimentoTO>();
		for (CanalAtendimentoConfiguravelPorRegional canal : CanalAtendimentoConfiguravelPorRegional.values()) {
			CanalAtendimentoTO to = new CanalAtendimentoTO();
			to.setIdCanalAtendimento(canal.getIdCanalAtendimento());
			to.setSgVivoNet(canal.toString());
			result.add(to);
		}
		return result;
	}
}