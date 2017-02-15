package com.indracompany.catalogo.ejb.canalvendaagrupador;

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

import com.indracompany.catalogo.dao.interfaces.CanalVendaDAO;
import com.indracompany.catalogo.dao.interfaces.EpsDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.EpsTO;

@Stateless(name = "CanalVendaAgrupadorBean", mappedName = "CanalVendaAgrupadorBean")
@Session(ejbName = "CanalVendaAgrupadorBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CanalVendaAgrupadorBean implements CanalVendaAgrupadorBeanLocal{

	private static Logger logger = Logger.getLogger(CanalVendaAgrupadorBean.class);
	private static final String MENSAGEM_ERRO_NMAGRUPADOR_JA_EXISTENTE = "Nome do Agrupador j&aacute; existe.";
	private static final String MENSAGEM_ERRO_ALTERACAO_ASSOCIACAO_CANALVENDAXEPS = "Esse agrupador &eacute; associado a um grupo comercial. Para alterar &eacute; preciso desassoci&aacute;-lo.";
	private static final String MENSAGEM_ERRO_EXCLUSAO_ASSOCIACAO_CANALVENDAXEPS = "Esse agrupador &eacute; associado a um grupo comercial. Para remover &eacute; preciso desassoci&aacute;-lo.";
	
	@EJB
	private CanalVendaDAO canalVendaDAO;
	@EJB
	private EpsDAO epsDAO;
	
	public List<EpsTO> getEpsList() throws BusinessException {
		
		List<EpsTO> result = null;
		
		try {
			result = epsDAO.search(new EpsTO());
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return result;
	}
	
	public List<CanalVendaTO> pesquisar(CanalVendaTO canalVendaTO) throws BusinessException{
		logger.debug(canalVendaTO.toString());
		
		List<CanalVendaTO> list = null;
		
		try {
			list = canalVendaDAO.pesquisar(canalVendaTO);
		} catch(DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	public CanalVendaTO merge(CanalVendaTO canalVendaTO) throws BusinessException{
		logger.debug(canalVendaTO.toString());
		
		CanalVendaTO canalVendaTOPNm,canalVendaTOPId = null;
		
		try {
			canalVendaTOPNm = canalVendaDAO.pesquisarPorNmCanalVenda(canalVendaTO);
			
			if(canalVendaTO.getIdCanalVenda() != null){
				canalVendaTOPId = canalVendaDAO.pesquisarPorIdCanalVenda(canalVendaTO);
				if(canalVendaTOPNm != null && !canalVendaTOPNm.getIdCanalVenda().equals(canalVendaTOPId.getIdCanalVenda())){
					throw new BusinessException("Nome do Grupo Comercial j&aacute existe.");
				}
			} else if(canalVendaTOPNm != null){
				throw new BusinessException("Esse Grupo Comercial j&aacute existe.");
			}
			
			canalVendaTOPNm = canalVendaDAO.createUpdate(canalVendaTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return canalVendaTOPNm;
	}
	
	public void remover(CanalVendaTO canalVendaTO) throws BusinessException{
		logger.debug(canalVendaTO.toString());
		
		try {
			if(!canalVendaDAO.possuiAssociacao(canalVendaTO))
				canalVendaDAO.remover(canalVendaTO);
			else 
				throw new BusinessException("N&atilde;o &eacute; poss&iacute;vel remover o Grupo pois existem configura&ccedil;&otilde;es associadas.");
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public void alternarInDisponivel(CanalVendaTO canalVendaTO) throws BusinessException {
		logger.debug(canalVendaTO.toString());

		try {
			canalVendaTO = canalVendaDAO.pesquisarPorIdCanalVenda(canalVendaTO);
			if(canalVendaTO.getInDisponivel().equals("S")) 
				canalVendaTO.setInDisponivel("N");
			else 
				canalVendaTO.setInDisponivel("S");
			canalVendaDAO.createUpdate(canalVendaTO);
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public CanalVendaTO pesquisarPorId(CanalVendaTO canalVendaTO) throws BusinessException {
		logger.debug(canalVendaTO.toString());
		
		CanalVendaTO retorno = null;
		
		try {
			retorno = canalVendaDAO.pesquisarPorIdCanalVenda(canalVendaTO);
		} catch (DAOException e){
			throw new EJBException(e);
		}
		
		return retorno;
	}

	public void associarAgrupador(List<Long> canalVendaIdList, Integer idEps) throws BusinessException {
		logger.debug("canalVendaIdList: "+canalVendaIdList.toString()+" idEps: "+idEps.toString());
		
		try {
			canalVendaDAO.associarEps(canalVendaIdList, idEps);
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}
	
	public void desassociarAgrupador(List<Long> canalVendaIdList) throws BusinessException {
		logger.debug("canalVendaIdList: "+canalVendaIdList.toString());
		
		try {
			canalVendaDAO.desAssociarEps(canalVendaIdList);
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<EpsTO> pesquisar(EpsTO epsTO) throws BusinessException {
		List<EpsTO> result = null;
		try {
			result = epsDAO.search(epsTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
		return result;
	}
	
	public void remover(EpsTO epsTO) throws BusinessException{
		logger.debug("epsTO: "+epsTO.toString());
		try {
			List<CanalVendaTO> canalVendaTOList = canalVendaDAO.findByIdEps(epsTO.getIdEps());
			if(canalVendaTOList.isEmpty())
				epsDAO.remove(epsTO);
			else
				throw new BusinessException(MENSAGEM_ERRO_EXCLUSAO_ASSOCIACAO_CANALVENDAXEPS); 
		}catch(DAOException e){
			throw new EJBException(e);
		}
	}
	
	public void merge(EpsTO epsTO) throws BusinessException{
		logger.debug("epsTO: "+epsTO.toString());
		try {
			List<EpsTO> epsTOSearchList = epsDAO.findByName(epsTO);
			if(!epsTOSearchList.isEmpty())
				throw new BusinessException(MENSAGEM_ERRO_NMAGRUPADOR_JA_EXISTENTE);
			else
				if(epsTO.getIdEps() == null || epsTO.getIdEps() == 0)
					epsDAO.mergeEpsGrupoEps(epsTO);
				else
					if(canalVendaDAO.findByIdEps(epsTO.getIdEps()).isEmpty())
						epsDAO.mergeEpsGrupoEps(epsTO);
					else
						throw new BusinessException(MENSAGEM_ERRO_ALTERACAO_ASSOCIACAO_CANALVENDAXEPS);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}
}
