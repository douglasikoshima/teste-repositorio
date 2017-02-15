package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.DisponibilidadeCndcPagamentoDAO;
import com.indracompany.catalogo.datalayer.AreaConcorrencia;
import com.indracompany.catalogo.datalayer.CondicaoPagamentoEncargo;
import com.indracompany.catalogo.datalayer.DisponibilidadeCndcPagamento;
import com.indracompany.catalogo.datalayer.EspServicoPlanoMinuto;
import com.indracompany.catalogo.datalayer.PoliticaDispCndcPagamento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DisponibilidadeCndcPagamentoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

@Stateless
public class DisponibilidadeCndcPagamentoDAOImpl implements DisponibilidadeCndcPagamentoDAO{
	
	private static Logger log = Logger.getLogger(DisponibilidadeCndcPagamentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	public List<DisponibilidadeCndcPagamentoTO> findByIdCondicaoPagamentoEncargo(DisponibilidadeCndcPagamentoTO to) throws DAOException {
		log.debug("DisponibilidadeCndcPagamentoTO: " + to);
		DisponibilidadeCndcPagamentoTOBuilder disponibilidadeCndcPagamentoTOBuilder = new DisponibilidadeCndcPagamentoTOBuilder();
		List<DisponibilidadeCndcPagamentoTO> result = new ArrayList<DisponibilidadeCndcPagamentoTO>();
		CondicaoPagamentoEncargo condicaoPagamentoEncargo = null;
		
		try {
			condicaoPagamentoEncargo = em.find(CondicaoPagamentoEncargo.class , to.getIdCondicaoPagamentoEncargo());
			result = disponibilidadeCndcPagamentoTOBuilder.createTOList(condicaoPagamentoEncargo.getDisponibilidadeCndcPagamentoList());
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;	
	}

	@SuppressWarnings("unchecked")
	public void createUpdateDispArConcPlMinByIdCndcPgtoEnc(List<DisponibilidadeCndcPagamentoTO> toList) throws DAOException {
		log.debug("disponibilidadeCndcPagamentoTO:" + toList);
		DisponibilidadeCndcPagamentoTOBuilder builder = new DisponibilidadeCndcPagamentoTOBuilder();
		StringBuilder queryStr = new StringBuilder();
		List<Long> idAreaConcorrenciaList = new ArrayList<Long>();
		List<Integer> idServicoList = new ArrayList<Integer>();
		List<DisponibilidadeCndcPagamento> disponibilidadeCndcComercialList = null;
		
		try {
			
			idAreaConcorrenciaList.add(new Long(0));
			idServicoList.add(new Integer(0));
			for(DisponibilidadeCndcPagamentoTO disponibilidadeCndcPagamentoTO : toList){
				idAreaConcorrenciaList.add(disponibilidadeCndcPagamentoTO.getAreaConcorrenciaTO().getIdAreaConcorrencia());
				idServicoList.add(disponibilidadeCndcPagamentoTO.getEspServicoPlanoMinutoTO().getIdServico());
			}
			
			queryStr.append(
					" select dcp from DisponibilidadeCndcPagamento dcp " +
					" join dcp.condicaoPagamentoEncargo cpe " +
					" join dcp.areaConcorrencia a " +
					" where cpe.idCondicaoPagamentoEncargo = :idCondicaoPagamentoEncargo" +
					" and a.idAreaConcorrencia not in (:idAreaConcorrenciaList) "
			);
			
			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idCondicaoPagamentoEncargo", toList.get(0).getIdCondicaoPagamentoEncargo());
			query1.setParameter("idAreaConcorrenciaList", idAreaConcorrenciaList);
			
			disponibilidadeCndcComercialList = (List<DisponibilidadeCndcPagamento>) query1.getResultList();
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcComercialList){
				em.remove(disponibilidadeCndcPagamento);
			}
			
			queryStr = new StringBuilder();
			queryStr.append(
					" select dcp from DisponibilidadeCndcPagamento dcp " +
					" join dcp.condicaoPagamentoEncargo cpe " +
					" join dcp.espServicoPlanoMinuto epm " +
					" where cpe.idCondicaoPagamentoEncargo = :idCondicaoPagamentoEncargo" +
					" and epm.idServico not in (:idServicoList) "
			);
			
			Query query2 = em.createQuery(queryStr.toString());
			query2.setParameter("idCondicaoPagamentoEncargo", toList.get(0).getIdCondicaoPagamentoEncargo());
			query2.setParameter("idServicoList", idServicoList);
			
			disponibilidadeCndcComercialList = (List<DisponibilidadeCndcPagamento>) query2.getResultList();
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcComercialList){
				em.remove(disponibilidadeCndcPagamento);
			}

			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : builder.createEntityList(toList)){
				em.merge(disponibilidadeCndcPagamento);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public void removeByIdCndcPgtoEncargo(Long idCndcPgtoEncargo) throws DAOException {
		log.debug("idCndcPgtoEncargo:" + idCndcPgtoEncargo);
		List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList = null;
		
		try {
			disponibilidadeCndcPagamentoList = em.find(CondicaoPagamentoEncargo.class, idCndcPgtoEncargo).getDisponibilidadeCndcPagamentoList();
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcPagamentoList){
				em.remove(disponibilidadeCndcPagamento);
			}
			
			em.flush();
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createConfigForPlMinArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		log.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<AreaConcorrencia> areaConcorrenciaList = new ArrayList<AreaConcorrencia>();
		List<EspServicoPlanoMinuto> espServicoPlanoMinutoList = new ArrayList<EspServicoPlanoMinuto>();
		List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList = new ArrayList<DisponibilidadeCndcPagamento>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr.append(
					" select a from AreaConcorrencia a " +
					" where a.idAreaConcorrencia in (:idAreaConcorrenciaList) "
			);

			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idAreaConcorrenciaList", new AreaConcorrenciaTOBuilder().getIdList(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList()));

			areaConcorrenciaList = query1.getResultList();
			
			queryStr = new StringBuilder();
			queryStr.append(
					" select epm from EspServicoPlanoMinuto epm " +
					" where epm.idServico in (:idServicoList) "
			);
			
			Query query2 = em.createQuery(queryStr.toString());
			query2.setParameter("idServicoList", new EspServicoPlanoMinutoTOBuilder().getIdList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList()));
			
			espServicoPlanoMinutoList = query2.getResultList();

			for(AreaConcorrencia areaConcorrencia : areaConcorrenciaList){
				for(EspServicoPlanoMinuto espServicoPlanoMinuto : espServicoPlanoMinutoList){
					disponibilidadeCndcPagamentoList.add(new DisponibilidadeCndcPagamento(
						em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo())
						,em.find(PoliticaDispCndcPagamento.class, em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo()).getPoliticaDispCndcPagamento().getIdPoliticaDispCndcPagamento())
						,espServicoPlanoMinuto
						,areaConcorrencia
					));
				}
			}
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcPagamentoList){
				em.persist(disponibilidadeCndcPagamento);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createConfigForArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		log.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<AreaConcorrencia> areaConcorrenciaList = new ArrayList<AreaConcorrencia>();
		List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList = new ArrayList<DisponibilidadeCndcPagamento>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr.append(
					" select a from AreaConcorrencia a " +
					" where a.idAreaConcorrencia in (:idAreaConcorrenciaList) "
			);

			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idAreaConcorrenciaList", new AreaConcorrenciaTOBuilder().getIdList(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList()));

			areaConcorrenciaList = query1.getResultList();
			
			for(AreaConcorrencia areaConcorrencia : areaConcorrenciaList){
				disponibilidadeCndcPagamentoList.add(new DisponibilidadeCndcPagamento(
						em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo())
						,em.find(PoliticaDispCndcPagamento.class, em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo()).getPoliticaDispCndcPagamento().getIdPoliticaDispCndcPagamento())
						,null
						,areaConcorrencia
				));
			}
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcPagamentoList){
				em.persist(disponibilidadeCndcPagamento);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void createConfigForPlMin(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		log.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<EspServicoPlanoMinuto> espServicoPlanoMinutoList = new ArrayList<EspServicoPlanoMinuto>();
		List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList = new ArrayList<DisponibilidadeCndcPagamento>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr = new StringBuilder();
			queryStr.append(
					" select epm from EspServicoPlanoMinuto epm " +
					" where epm.idServico in (:idServicoList) "
			);
			
			Query query2 = em.createQuery(queryStr.toString());
			query2.setParameter("idServicoList", new EspServicoPlanoMinutoTOBuilder().getIdList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList()));
			
			espServicoPlanoMinutoList = query2.getResultList();

			for(EspServicoPlanoMinuto espServicoPlanoMinuto : espServicoPlanoMinutoList){
				disponibilidadeCndcPagamentoList.add(new DisponibilidadeCndcPagamento(
					em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo())
					,em.find(PoliticaDispCndcPagamento.class, em.find(CondicaoPagamentoEncargo.class, servicoSolicitacaoComercialTO.getIdCondicaoPagamentoEncargo()).getPoliticaDispCndcPagamento().getIdPoliticaDispCndcPagamento())
					,espServicoPlanoMinuto
					,null
				));
			}
			
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : disponibilidadeCndcPagamentoList){
				em.persist(disponibilidadeCndcPagamento);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}



}
