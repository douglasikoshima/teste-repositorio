package com.indracompany.catalogo.ejb.servicosolicitacaocomercial;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.AreaConcorrenciaDAO;
import com.indracompany.catalogo.dao.interfaces.CanalVendaDAO;
import com.indracompany.catalogo.dao.interfaces.CanalVendaSolicitacaoComercialDAO;
import com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoDAO;
import com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoEncargoDAO;
import com.indracompany.catalogo.dao.interfaces.DisponibilidadeCndcPagamentoDAO;
import com.indracompany.catalogo.dao.interfaces.DisponibilidadeSlctComercialDAO;
import com.indracompany.catalogo.dao.interfaces.EncargoDAO;
import com.indracompany.catalogo.dao.interfaces.EspServicoPlanoMinutoDAO;
import com.indracompany.catalogo.dao.interfaces.PoliticaDispCndcPagamentoDAO;
import com.indracompany.catalogo.dao.interfaces.PoliticaDispSlctComercialDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.dao.interfaces.SolicitacaoComercialDAO;
import com.indracompany.catalogo.dao.interfaces.TipoEncargoDAO;
import com.indracompany.catalogo.dao.interfaces.TipoSolicitacaoComercialDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CondicaoPagamentoEncargoTO;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;
import com.indracompany.catalogo.to.DisponibilidadeCndcPagamentoTO;
import com.indracompany.catalogo.to.DisponibilidadeSlctComercialTO;
import com.indracompany.catalogo.to.PoliticaDispCndcPagamentoTO;
import com.indracompany.catalogo.to.PoliticaDispSlctComercialTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;
import com.indracompany.catalogo.to.SistemaTO;

@Stateless(name = "ServicoSolicitacaoComercialBean", mappedName = "ServicoSolicitacaoComercialBean")
@Session(ejbName = "ServicoSolicitacaoComercialBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoSolicitacaoComercialBean implements ServicoSolicitacaoComercialBeanLocal {
	
	@EJB
	private ServicoFixaDAO servicoFixaDAO;
	@EJB
	private SolicitacaoComercialDAO solicitacaoComercialDAO;
	@EJB
	private TipoSolicitacaoComercialDAO tipoSolicitacaoComercialDAO;
	@EJB
	private EncargoDAO encargoDAO;
	@EJB
	private TipoEncargoDAO tipoEncargoDAO;
	@EJB
	private CanalVendaDAO canalVendaDAO;
	@EJB
	private AreaConcorrenciaDAO areaConcorrenciaDAO;
	@EJB
	private PoliticaDispSlctComercialDAO politicaDispSlctComercialDAO;
	@EJB
	private CanalVendaSolicitacaoComercialDAO canalVendaSolicitacaoComercialDAO;
	@EJB
	private EspServicoPlanoMinutoDAO espServicoPlanoMinutoDAO;
	@EJB 
	private DisponibilidadeSlctComercialDAO disponibilidadeSlctComercialDAO;
	@EJB
	private CondicaoPagamentoEncargoDAO condicaoPagamentoEncargoDAO;
	@EJB
	private DisponibilidadeCndcPagamentoDAO disponibilidadeCndcPagamentoDAO;
	@EJB
	private PoliticaDispCndcPagamentoDAO politicaDispCndcPagamentoDAO;
	@EJB
	private CondicaoPagamentoDAO condicaoPagamentoDAO;
	
	public List<ServicoSolicitacaoComercialTO> searchSolicitacaoComercial(ServicoSolicitacaoComercialTO to) throws BusinessException {
		
		List<ServicoSolicitacaoComercialTO> result = null;
		
		try {
			result = solicitacaoComercialDAO.search(to);
		} catch (DAOException e) {
			throw new BusinessException(e);
		} 
		
		return result;
	}

	public ServicoSolicitacaoComercialTO findSistemaByIdServico(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		SistemaTO sistemaTO = null;
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			ServicoFixaTO servicoFixaTO = new ServicoFixaTO();
			servicoFixaTO.setIdServico(servicoSolicitacaoComercialTO.getIdServico());
			sistemaTO =  servicoFixaDAO.findSistemaByIdServico(servicoFixaTO);
			result.setIdSistema(sistemaTO.getIdSistema());
		} catch (DAOException e) {
			throw new BusinessException(e);
		} 
		
		return result;
	}
	
	public void switchDisponibilidadeSlctCmrl(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		//List<ServicoSolicitacaoComercialTO> result = new ArrayList<ServicoSolicitacaoComercialTO>();
		
		try {
			if(servicoSolicitacaoComercialTO.getInDisponivel().equals("S")){
				servicoSolicitacaoComercialTO.setInDisponivel("N");
			} else {
				servicoSolicitacaoComercialTO.setInDisponivel("S");
			}
			
			solicitacaoComercialDAO.createUpdate(servicoSolicitacaoComercialTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
	
	public ServicoSolicitacaoComercialTO switchDisponibilidadeCanalVendaSlctCmrl(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO = new CanalVendaSolicitacaoComercialTO(servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());

		try {
			canalVendaSolicitacaoComercialTO = canalVendaSolicitacaoComercialDAO.findById(canalVendaSolicitacaoComercialTO);
			if(canalVendaSolicitacaoComercialTO.getInDisponivel().equalsIgnoreCase("S")){
				canalVendaSolicitacaoComercialTO.setInDisponivel("N");
				result.setMensagemRetorno("Configura&ccedil;&atildeo Desativada com sucesso!");
			} else {
				canalVendaSolicitacaoComercialTO.setInDisponivel("S");
				result.setMensagemRetorno("Configura&ccedil;&atildeo Ativada com sucesso!");
			}
			
			canalVendaSolicitacaoComercialDAO.switchDisponibilidadeCanalVendaSlctCmrl(canalVendaSolicitacaoComercialTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		return result;
	}	
	
	public ServicoSolicitacaoComercialTO getCombos() throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			result.setTipoSolicitacaoComercialTOList(tipoSolicitacaoComercialDAO.findAll());
			result.setTipoEncargoTOList(tipoEncargoDAO.findAll());
			result.setCanalVendaFullTOList(canalVendaDAO.findAll());
			result.setCondicaoPagamentoFullTOList(condicaoPagamentoDAO.search(new CondicaoPagamentoTO()));
		} catch(DAOException e){
			throw new BusinessException(e);
		}
		
		return result;
	}

	public ServicoSolicitacaoComercialTO searchEncargoBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			result.setEncargoPoliticaPrecificacaoTOList(encargoDAO.searchBySolicitacaoComercial(servicoSolicitacaoComercialTO));
		} catch(DAOException e){
			throw new BusinessException(e);
		}
		
		return result;
	}

	public void createUpdateCanalVendaSlctCmrlList(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		try {
			solicitacaoComercialDAO.createUpdateCanalVendaSlctCmrlList(servicoSolicitacaoComercialTO);
		} catch(DAOException e){
			throw new BusinessException(e);
		}
	}

	public ServicoSolicitacaoComercialTO findCanalVendaBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result;
		List<Long> idCanalVendaList = new ArrayList<Long>();
		
		try {
			result = solicitacaoComercialDAO.findCanalVendaBySolicitacaoComercial(servicoSolicitacaoComercialTO);
			for(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO : result.getCanalVendaSolicitacaoComercialTOList()){
				idCanalVendaList.add(canalVendaSolicitacaoComercialTO.getCanalVendaTO().getIdCanalVenda());
			}
			result.setCanalVendaSelecionavelTOList(canalVendaDAO.findAllNotInIdList(idCanalVendaList, "S"));
			if(servicoSolicitacaoComercialTO.getPoliticaDispSlctComercialTO() != null){
				result.setPoliticaDispSlctComercialTO(politicaDispSlctComercialDAO.findById(servicoSolicitacaoComercialTO.getPoliticaDispSlctComercialTO()));
			}
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		return result;
	}

	public ServicoSolicitacaoComercialTO findDispArConcPlMinByIdCnVendaSlct(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result;
		List<Long> idAreaConcorrenciaList = new ArrayList<Long>();
		List<Integer> idEspServicoPlMinList = new ArrayList<Integer>();
		
		try {
			
			result = solicitacaoComercialDAO.findDispSlctCmrlByIdCnVendaSlct(servicoSolicitacaoComercialTO);
			for(DisponibilidadeSlctComercialTO disponibilidadeSlctComercialTO : result.getDisponibilidadeSlctComercialTOList()){
				if(disponibilidadeSlctComercialTO.getAreaConcorrenciaTO() != null && disponibilidadeSlctComercialTO.getAreaConcorrenciaTO().getIdAreaConcorrencia() != null){
					idAreaConcorrenciaList.add(disponibilidadeSlctComercialTO.getAreaConcorrenciaTO().getIdAreaConcorrencia());
				}
				if(disponibilidadeSlctComercialTO.getEspServicoPlanoMinutoTO() != null && disponibilidadeSlctComercialTO.getEspServicoPlanoMinutoTO().getIdServico() != null){
					idEspServicoPlMinList.add(disponibilidadeSlctComercialTO.getEspServicoPlanoMinutoTO().getIdServico());
				}
			}
			result.setAreaConcorrenciaSlctSelecionadaTOList(areaConcorrenciaDAO.findAllInIdList(idAreaConcorrenciaList));
			result.setAreaConcorrenciaSlctSelecionavelTOList(areaConcorrenciaDAO.findAllNotInIdList(idAreaConcorrenciaList));
			result.setEspServicoPlanoMinutoSlctSelecionavelTOList(espServicoPlanoMinutoDAO.findAllNotInIdList(idEspServicoPlMinList, servicoSolicitacaoComercialTO.getIdSistema()));
			result.setEspServicoPlanoMinutoSlctSelecionadoTOList(espServicoPlanoMinutoDAO.findAllInIdList(idEspServicoPlMinList));
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		return result;
	}

	public ServicoSolicitacaoComercialTO createUpdateDispArConcPlMinByIdCnVendaSlct(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			disponibilidadeSlctComercialDAO.removeByIdCanalVendaSlctCmrl(servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
			
			ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO2 = solicitacaoComercialDAO.findById(servicoSolicitacaoComercialTO);
			if(servicoSolicitacaoComercialTO2.getPoliticaDispSlctComercialTO() != null){
				servicoSolicitacaoComercialTO.setPoliticaDispSlctComercialTO(servicoSolicitacaoComercialTO2.getPoliticaDispSlctComercialTO());
			} else {
				servicoSolicitacaoComercialTO.setPoliticaDispSlctComercialTO(new PoliticaDispSlctComercialTO("N","N"));
			}

			if(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList() != null && servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList() != null){
				if(!servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList().isEmpty() && !servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList().isEmpty()){
					disponibilidadeSlctComercialDAO.createConfigForPlMinArConc(servicoSolicitacaoComercialTO);
				}
				if(!servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList().isEmpty() && servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList().isEmpty()){
					disponibilidadeSlctComercialDAO.createConfigForArConc(servicoSolicitacaoComercialTO);
				}
				if(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList().isEmpty() && !servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList().isEmpty()){
					disponibilidadeSlctComercialDAO.createConfigForPlMin(servicoSolicitacaoComercialTO);
				}
			}
		} catch(DAOException e){
			throw new BusinessException(e);
		}
		
		return result;
	}

	public void createUpdateDispArConcPlMinByIdCndcPgtoEnc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		
		try {
			disponibilidadeCndcPagamentoDAO.removeByIdCndcPgtoEncargo(servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo());
			
			if(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList() != null && servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList() != null){
				if(!servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList().isEmpty() && !servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList().isEmpty()){
					disponibilidadeCndcPagamentoDAO.createConfigForPlMinArConc(servicoSolicitacaoComercialTO);
				}
				if(!servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList().isEmpty() && servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList().isEmpty()){
					disponibilidadeCndcPagamentoDAO.createConfigForArConc(servicoSolicitacaoComercialTO);
				}
				if(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList().isEmpty() && !servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList().isEmpty()){
					disponibilidadeCndcPagamentoDAO.createConfigForPlMin(servicoSolicitacaoComercialTO);
				}
			}
		} catch(DAOException e){
			throw new BusinessException(e);
		}
	}

	public void removeDspSlctByIdCanalVendaSlctCmrl(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException{
		try {
			disponibilidadeSlctComercialDAO.removeByIdCanalVendaSlctCmrl(servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
		} catch(DAOException e){
			throw new BusinessException(e);
		}
	}
	
	public ServicoSolicitacaoComercialTO searchCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			CondicaoPagamentoEncargoTO condicaoPagamentoEncargoTO = new CondicaoPagamentoEncargoTO(
					new CanalVendaTO(servicoSolicitacaoComercialTO.getIdCanalVendaEncargo())
					,servicoSolicitacaoComercialTO.getIdEncargo()
			);
			result.setCondicaoPagamentoEncargoTOList(condicaoPagamentoEncargoDAO.search(condicaoPagamentoEncargoTO));
		} catch(DAOException e){
			throw new BusinessException(e);
		}
		
		return result;
	}

	public ServicoSolicitacaoComercialTO findDispArConcPlMinByIdCndcPgtoEnc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		List<Long> idAreaConcorrenciaList = new ArrayList<Long>();
		List<Integer> idEspServicoPlMinList = new ArrayList<Integer>();
		
		try {
			
			result.setDisponibilidadeCndcPagamentoTOList(disponibilidadeCndcPagamentoDAO.findByIdCondicaoPagamentoEncargo(new DisponibilidadeCndcPagamentoTO(new Long(0),servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo())));
			for(DisponibilidadeCndcPagamentoTO disponibilidadeCndcPagamentoTO : result.getDisponibilidadeCndcPagamentoTOList()){
				if(disponibilidadeCndcPagamentoTO.getAreaConcorrenciaTO() != null && disponibilidadeCndcPagamentoTO.getAreaConcorrenciaTO().getIdAreaConcorrencia() != null){
					idAreaConcorrenciaList.add(disponibilidadeCndcPagamentoTO.getAreaConcorrenciaTO().getIdAreaConcorrencia());
				}
				if(disponibilidadeCndcPagamentoTO.getEspServicoPlanoMinutoTO() != null && disponibilidadeCndcPagamentoTO.getEspServicoPlanoMinutoTO().getIdServico() != null){
					idEspServicoPlMinList.add(disponibilidadeCndcPagamentoTO.getEspServicoPlanoMinutoTO().getIdServico());

				}
			}
            
			result.setAreaConcorrenciaCndcSelecionadaTOList(areaConcorrenciaDAO.findAllInIdList(idAreaConcorrenciaList));
			result.setAreaConcorrenciaCndcSelecionavelTOList(areaConcorrenciaDAO.findAllNotInIdList(idAreaConcorrenciaList));
			result.setEspServicoPlanoMinutoCndcSelecionavelTOList(espServicoPlanoMinutoDAO.findAllNotInIdList(idEspServicoPlMinList, servicoSolicitacaoComercialTO.getIdSistema()));
			result.setEspServicoPlanoMinutoCndcSelecionadoTOList(espServicoPlanoMinutoDAO.findAllInIdList(idEspServicoPlMinList));
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		return result;
	}

	public ServicoSolicitacaoComercialTO findCanalVendaCndcPgtoByIdEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			result.setCanalVendaCndcPgtoEncargoTOList(canalVendaDAO.findByIdEncargo(servicoSolicitacaoComercialTO.getIdEncargo()));
		} catch(DAOException e){
			throw new BusinessException(e);
		}
		
		return result;
	}

	public ServicoSolicitacaoComercialTO switchDisponibilidadeCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		CondicaoPagamentoEncargoTO condicaoPagamentoEncargoTO = new CondicaoPagamentoEncargoTO(servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo());

		try {
			condicaoPagamentoEncargoTO = condicaoPagamentoEncargoDAO.findById(condicaoPagamentoEncargoTO);
			if(condicaoPagamentoEncargoTO.getInDisponivel().equalsIgnoreCase("S")){
				condicaoPagamentoEncargoTO.setInDisponivel("N");
				result.setMensagemRetorno("Configura&ccedil;&atildeo Desativada com sucesso!");
			} else {
				condicaoPagamentoEncargoTO.setInDisponivel("S");
				result.setMensagemRetorno("Configura&ccedil;&atildeo Ativada com sucesso!");
			}
			
			condicaoPagamentoEncargoDAO.createUpdate(condicaoPagamentoEncargoTO);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		return result;
	}

	public void createUpdateCndcPgtoEncargo(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws BusinessException {
		try {
//		    servicoSolicitacaoComercialTO.getCondicaoPagamentoEncargoTO().setPoliticaDispCndcPagamentoTO(politicaDispCndcPagamentoDAO.findId(new PoliticaDispCndcPagamentoTO("S","S")));
			if(condicaoPagamentoEncargoDAO.existAssociation(servicoSolicitacaoComercialTO.getCondicaoPagamentoEncargoTO())){
				throw new BusinessException("J&aacute; existe registro para essa associa&ccedil;&atilde;o ");
			}
			PoliticaDispCndcPagamentoTO findByIdServico = politicaDispCndcPagamentoDAO.findByIdServico(servicoSolicitacaoComercialTO.getIdServico());
            servicoSolicitacaoComercialTO.getCondicaoPagamentoEncargoTO().setPoliticaDispCndcPagamentoTO(findByIdServico);
			condicaoPagamentoEncargoDAO.createUpdate(servicoSolicitacaoComercialTO.getCondicaoPagamentoEncargoTO());
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

	public void saveSolicitacaoOfertaClienteInadimplente(List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOList) throws BusinessException {
		
		try {
			solicitacaoComercialDAO.saveSolicitacaoOfertaClienteInadimplente(servicoSolicitacaoComercialTOList);
		} catch(DAOException e){
			throw new BusinessException(e);
		}
	}
	
}
