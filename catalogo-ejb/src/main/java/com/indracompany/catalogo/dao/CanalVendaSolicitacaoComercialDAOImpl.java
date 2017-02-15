package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CanalVendaSolicitacaoComercialDAO;
import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.DisponibilidadeSlctComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;


@Stateless
public class CanalVendaSolicitacaoComercialDAOImpl implements CanalVendaSolicitacaoComercialDAO{
	private static Logger logger = Logger.getLogger(CanalVendaSolicitacaoComercialDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public CanalVendaSolicitacaoComercialTO findById(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO) throws DAOException {
		logger.debug("CanalVendaSolicitacaoComercialTO:" + canalVendaSolicitacaoComercialTO);
		CanalVendaSolicitacaoComercialTO result = null;
		CanalVendaSolicitacaoComercialTOBuilder builder = new CanalVendaSolicitacaoComercialTOBuilder();
		
		try {
			result = builder.createTO(em.find(CanalVendaSolicitacaoComercial.class, canalVendaSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial()));
		} catch(Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}
	
	public void switchDisponibilidadeCanalVendaSlctCmrl(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO) throws DAOException {
		logger.debug("CanalVendaSolicitacaoComercialTO:" + canalVendaSolicitacaoComercialTO);
//		CanalVendaSolicitacaoComercialTOBuilder builder = new CanalVendaSolicitacaoComercialTOBuilder();
		
		try {
//			canalVendaSolicitacaoComercialTO.setInCriacaoCatalogo("S");
//			em.merge(builder.createEntity(canalVendaSolicitacaoComercialTO));
            CanalVendaSolicitacaoComercial csc = em.find(CanalVendaSolicitacaoComercial.class, canalVendaSolicitacaoComercialTO.getIdCanalVendaSolicitacaoComercial());
            csc.setInDisponivel(csc.getInDisponivel().equals("S") ? "N" : "S");
            em.merge(csc);
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}

	public void updateCascadeDisponibilidadeSlctCmrlCanalVenda(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("CanalVendaSolicitacaoComercialTO: " + to);

		try {
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, to.getIdCanalVendaSolicitacaoComercial());
			
			canalVendaSolicitacaoComercial.setInDisponivel(to.getInDisponivel());
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
				disponibilidadeSlctComercial.setIdDisponibilidadeSlctComercial(to.getPoliticaDispSlctComercialTO().getIdPoliticaDispSlctComercial());
			}
			
			em.merge(canalVendaSolicitacaoComercial);

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<AreaConcorrenciaTO> findAreaConcorrenciaListById(Long idCanalVendaSlctCmrl) throws DAOException {
		logger.debug("idCanalVendaSlctCmrl: " + idCanalVendaSlctCmrl);
		List<AreaConcorrenciaTO> result = new ArrayList<AreaConcorrenciaTO>();
		AreaConcorrenciaTOBuilder areaConcorrenciaTOBuilder = new AreaConcorrenciaTOBuilder();
		
		
		try {
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, idCanalVendaSlctCmrl);
			
			//canalVendaSolicitacaoComercial.get
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
				result.add(areaConcorrenciaTOBuilder.createTO(disponibilidadeSlctComercial.getAreaConcorrencia()));
			}

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

	public List<EspServicoPlanoMinutoTO> findPlanoDeMinutosById(Long idCanalVendaSlctCmrl) throws DAOException {
		logger.debug("idCanalVendaSlctCmrl: " + idCanalVendaSlctCmrl);
		List<EspServicoPlanoMinutoTO> result = new ArrayList<EspServicoPlanoMinutoTO>();
		EspServicoPlanoMinutoTOBuilder espServicoPlanoMinutoTOBuilder = new EspServicoPlanoMinutoTOBuilder();
		
		try {
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, idCanalVendaSlctCmrl);
			for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
				result.add(espServicoPlanoMinutoTOBuilder.createTO(disponibilidadeSlctComercial.getEspServicoPlanoMinuto()));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;	
	}

}
