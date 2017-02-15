package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoEncargoDAO;
import com.indracompany.catalogo.datalayer.CondicaoPagamentoEncargo;
import com.indracompany.catalogo.datalayer.DisponibilidadeCndcPagamento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoEncargoTO;

@Stateless
public class CondicaoPagamentoEncargoDAOImpl implements CondicaoPagamentoEncargoDAO{
	private static Logger logger = Logger.getLogger(CanalVendaSolicitacaoComercialDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CondicaoPagamentoEncargoTO> search(CondicaoPagamentoEncargoTO to) throws DAOException {
		logger.debug("condicaoPagamentoEncargoTO:" + to);
		CondicaoPagamentoEncargoTOBuilder builder = new CondicaoPagamentoEncargoTOBuilder();
		StringBuilder queryStr = new StringBuilder();
		List<CondicaoPagamentoEncargoTO> result = null;
		
		try {
			queryStr.append(
					" select cpe from CondicaoPagamentoEncargo cpe " +
					" join cpe.encargo e " +
					" join cpe.canalVenda c " +
					" where e.idEncargo = :idEncargo "
			);
			if(to.getCanalVendaTO() != null
					&& to.getCanalVendaTO().getIdCanalVenda() != null){
				queryStr.append(" and c.idCanalVenda = :idCanalVenda ");
			}

			Query query = em.createQuery(queryStr.toString());  
			
			query.setParameter("idEncargo", to.getIdEncargo());
			if(to.getCanalVendaTO() != null
					&& to.getCanalVendaTO().getIdCanalVenda() != null){
				query.setParameter("idCanalVenda", to.getCanalVendaTO().getIdCanalVenda());
			}
			
			result = builder.createTOList(query.getResultList());

		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}

	public void updateCascadeDisponibilidadeCndcPgtoEncargo(CondicaoPagamentoEncargoTO to) throws DAOException {
		CondicaoPagamentoEncargo condicaoPagamentoEncargo = null;
		PoliticaDispCndcPagamentoTOBuilder politicaDispCndcPagamentoTOBuilder = new PoliticaDispCndcPagamentoTOBuilder();
		
		try {
			condicaoPagamentoEncargo = em.find(CondicaoPagamentoEncargo.class, to.getIdCondicaoPagamentoEncargo());
			condicaoPagamentoEncargo.setPoliticaDispCndcPagamento(politicaDispCndcPagamentoTOBuilder.createEntity(to.getPoliticaDispCndcPagamentoTO()));
			for(DisponibilidadeCndcPagamento disponibilidadeCndcPagamento : condicaoPagamentoEncargo.getDisponibilidadeCndcPagamentoList()){
				disponibilidadeCndcPagamento.setPoliticaDispCndcPagamento(politicaDispCndcPagamentoTOBuilder.createEntity(to.getPoliticaDispCndcPagamentoTO()));
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public CondicaoPagamentoEncargoTO findById(CondicaoPagamentoEncargoTO to) throws DAOException {
		CondicaoPagamentoEncargoTO result = null; 
		CondicaoPagamentoEncargoTOBuilder builder = new CondicaoPagamentoEncargoTOBuilder();
		
		try {
			result = builder.createTO(em.find(CondicaoPagamentoEncargo.class, to.getIdCondicaoPagamentoEncargo()));
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}

	public void createUpdate(CondicaoPagamentoEncargoTO to) throws DAOException {
		CondicaoPagamentoEncargoTOBuilder builder = new CondicaoPagamentoEncargoTOBuilder();
		
		try {
			to.setInCriacaoCatalogo("S");
			em.merge(builder.createEntity(to));
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public Boolean existAssociation(CondicaoPagamentoEncargoTO to) throws DAOException {
		logger.debug("condicaoPagamentoEncargoTO:" + to);
		StringBuilder queryStr = new StringBuilder();
		Boolean result;
		
		try {
			queryStr.append(
					" select cpe from CondicaoPagamentoEncargo cpe " +
					" join cpe.encargo e " +
					" join cpe.canalVenda c " +
					" join cpe.condicaoPagamento cp " +
					" where cp.inFixa = 'S' "
			);
			if(to.getIdEncargo() != null){
				queryStr.append(" and e.idEncargo = :idEncargo ");
			}
			if(to.getCanalVendaTO() != null
					&& to.getCanalVendaTO().getIdCanalVenda() != null){
				queryStr.append(" and c.idCanalVenda = :idCanalVenda ");
			}
			if(to.getCondicaoPagamentoTO() != null
					&& to.getCondicaoPagamentoTO().getIdCondicaoPagamento() != null){
				queryStr.append(" and cp.idCondicaoPagamento = :idCondicaoPagamento ");
			}
			
			Query query = em.createQuery(queryStr.toString());  
			
			if(to.getIdEncargo() != null){
				query.setParameter("idEncargo", to.getIdEncargo());
			}
			if(to.getCanalVendaTO() != null
					&& to.getCanalVendaTO().getIdCanalVenda() != null){
				query.setParameter("idCanalVenda", to.getCanalVendaTO().getIdCanalVenda());
			}
			if(to.getCondicaoPagamentoTO() != null
					&& to.getCondicaoPagamentoTO().getIdCondicaoPagamento() != null){
				query.setParameter("idCondicaoPagamento", to.getCondicaoPagamentoTO().getIdCondicaoPagamento());
			}
			
			 result = !query.getResultList().isEmpty();

		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;	
	}
}
