package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.DisponibilidadeSlctComercialDAO;
import com.indracompany.catalogo.datalayer.AreaConcorrencia;
import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.DisponibilidadeSlctComercial;
import com.indracompany.catalogo.datalayer.EspServicoPlanoMinuto;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DisponibilidadeSlctComercialTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;


@Stateless
public class DisponibilidadeSlctComercialDAOImpl implements DisponibilidadeSlctComercialDAO{
    private static Logger log = Logger.getLogger(DisponibilidadeSlctComercialDAOImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
	public void createUpdate(DisponibilidadeSlctComercialTO to) throws DAOException {
		log.debug("disponibilidadeSlctComercialTO:" + to);
		DisponibilidadeSlctComercialTOBuilder builder = new DisponibilidadeSlctComercialTOBuilder();
		
		try {
			em.merge(builder.createEntity(to));
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void createUpdateByIdCanalVendaSlctCmrl(List<DisponibilidadeSlctComercialTO> toList) throws DAOException {
		log.debug("disponibilidadeSlctComercialTO:" + toList);
		DisponibilidadeSlctComercialTOBuilder builder = new DisponibilidadeSlctComercialTOBuilder();
		StringBuilder queryStr = new StringBuilder();
		List<Long> idAreaConcorrenciaList = new ArrayList<Long>();
		List<Integer> idServicoList = new ArrayList<Integer>();
		List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList = null;
		
		try {
			
			idAreaConcorrenciaList.add(new Long(0));
			idServicoList.add(new Integer(0));
			for(DisponibilidadeSlctComercialTO disponibilidadeSlctComercialTO : toList){
				idAreaConcorrenciaList.add(disponibilidadeSlctComercialTO.getAreaConcorrenciaTO().getIdAreaConcorrencia());
				idServicoList.add(disponibilidadeSlctComercialTO.getEspServicoPlanoMinutoTO().getIdServico());
			}
			
			queryStr.append(
					" select dsc from DisponibilidadeSlctComercial dsc " +
					" join dsc.canalVendaSolicitacaoComercial csc " +
					" join dsc.areaConcorrencia a " +
					" where csc.idCanalVendaSolicitacaoCmrl = :idCanalVendaSlctCmrl" +
					" and a.idAreaConcorrencia not in (:idAreaConcorrenciaList) "
			);
			
			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idCanalVendaSlctCmrl", toList.get(0).getIdCanalVendaSolicitacaoComercial());
			query1.setParameter("idAreaConcorrenciaList", idAreaConcorrenciaList);
			
			disponibilidadeSlctComercialList = (List<DisponibilidadeSlctComercial>) query1.getResultList();
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.remove(disponibilidadeSlctComercial);
			}
			
			queryStr = new StringBuilder();
			queryStr.append(
					" select dsc from DisponibilidadeSlctComercial dsc " +
					" join dsc.canalVendaSolicitacaoComercial csc " +
					" join dsc.espServicoPlanoMinuto epm " +
					" where csc.idCanalVendaSolicitacaoCmrl = :idCanalVendaSlctCmrl" +
					" and epm.idServico not in (:idServicoList) "
			);
			
			Query query2 = em.createQuery(queryStr.toString());
			query2.setParameter("idCanalVendaSlctCmrl", toList.get(0).getIdCanalVendaSolicitacaoComercial());
			query2.setParameter("idServicoList", idServicoList);
			
			disponibilidadeSlctComercialList = (List<DisponibilidadeSlctComercial>) query2.getResultList();
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.remove(disponibilidadeSlctComercial);
			}

			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : builder.createEntityList(toList)){
				em.merge(disponibilidadeSlctComercial);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public void removeByIdCanalVendaSlctCmrl(Long idCanalVendaSolicitacaoCmrl) throws DAOException {
		log.debug("idCanalVendaSlctCmrl:" + idCanalVendaSolicitacaoCmrl);
		List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList = null;
		
		try {

			disponibilidadeSlctComercialList = em.find(CanalVendaSolicitacaoComercial.class, idCanalVendaSolicitacaoCmrl).getDisponibilidadeSlctComercialList();
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.remove(disponibilidadeSlctComercial);
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
		List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList = new ArrayList<DisponibilidadeSlctComercial>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr.append(
					" select a from AreaConcorrencia a " +
					" where a.idAreaConcorrencia in (:idAreaConcorrenciaList) "
			);

			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idAreaConcorrenciaList", new AreaConcorrenciaTOBuilder().getIdList(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList()));

			areaConcorrenciaList = query1.getResultList();
			
			queryStr = new StringBuilder();
			queryStr.append(
					" select epm from EspServicoPlanoMinuto epm " +
					" where epm.idServico in (:idServicoList) "
			);
			
			Query query2 = em.createQuery(queryStr.toString());
			query2.setParameter("idServicoList", new EspServicoPlanoMinutoTOBuilder().getIdList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList()));
			
			espServicoPlanoMinutoList = query2.getResultList();

			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdSolicitacaoComercial());

			for(AreaConcorrencia areaConcorrencia : areaConcorrenciaList){
				for(EspServicoPlanoMinuto espServicoPlanoMinuto : espServicoPlanoMinutoList){
					disponibilidadeSlctComercialList.add(new DisponibilidadeSlctComercial(
							canalVendaSolicitacaoComercial
							,solicitacaoComercial.getPoliticaDispSlctComercial()
							,espServicoPlanoMinuto
							,areaConcorrencia
					));
				}
			}
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.persist(disponibilidadeSlctComercial);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void createConfigForPlMin(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		log.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<EspServicoPlanoMinuto> espServicoPlanoMinutoList = new ArrayList<EspServicoPlanoMinuto>();
		List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList = new ArrayList<DisponibilidadeSlctComercial>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr = new StringBuilder();
			queryStr.append(
					" select epm from EspServicoPlanoMinuto epm " +
					" where epm.idServico in (:idServicoList) "
			);
			
			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idServicoList", new EspServicoPlanoMinutoTOBuilder().getIdList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList()));
			
			espServicoPlanoMinutoList = query1.getResultList();

			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdSolicitacaoComercial());
			
			for(EspServicoPlanoMinuto espServicoPlanoMinuto : espServicoPlanoMinutoList){
				disponibilidadeSlctComercialList.add(new DisponibilidadeSlctComercial(
						canalVendaSolicitacaoComercial
						,solicitacaoComercial.getPoliticaDispSlctComercial()
						,espServicoPlanoMinuto
						,null
				));
			}
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.persist(disponibilidadeSlctComercial);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void createConfigForArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		log.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<AreaConcorrencia> areaConcorrenciaList = new ArrayList<AreaConcorrencia>();
		List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList = new ArrayList<DisponibilidadeSlctComercial>();

		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr.append(
					" select a from AreaConcorrencia a " +
					" where a.idAreaConcorrencia in (:idAreaConcorrenciaList) "
			);

			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idAreaConcorrenciaList", new AreaConcorrenciaTOBuilder().getIdList(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList()));

			areaConcorrenciaList = query1.getResultList();
			
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdSolicitacaoComercial());
			
			for(AreaConcorrencia areaConcorrencia : areaConcorrenciaList){
				disponibilidadeSlctComercialList.add(new DisponibilidadeSlctComercial(
					canalVendaSolicitacaoComercial
					,solicitacaoComercial.getPoliticaDispSlctComercial()
					,null
					,areaConcorrencia
				));
			}
			
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : disponibilidadeSlctComercialList){
				em.persist(disponibilidadeSlctComercial);
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
}
