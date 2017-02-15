package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CabecalhoAnaliseCreditoDAO;
import com.indracompany.catalogo.datalayer.CabecalhoAnaliseCredito;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco dos dados do Cabecalho de Analise de Credito
 */
@Stateless
public class CabecalhoAnaliseCreditoDAOImpl implements CabecalhoAnaliseCreditoDAO {
	
	private static Logger logger = Logger.getLogger(CabecalhoAnaliseCreditoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	

	@SuppressWarnings("unchecked")
	public List<CabecalhoAnaliseCreditoTO> searchCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		List<CabecalhoAnaliseCreditoTO> list = null;
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select cac from CabecalhoAnaliseCredito cac ");
			queryStr.append(" where cac.dtCriacao is not null ");
			
			
			if (cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito() != null && !cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito().equals("")) {
				queryStr.append(" and upper(cac.nmCabecalhoAnaliseCredito) like upper(:nmCabecalhoAnaliseCredito) ");
			}
			
			if (cabecalhoAnaliseCreditoTO.getCanalAtendimento() != null && cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento() != null) {
				queryStr.append(" and cac.canalAtendimento.idCanalAtendimento = :idCanalAtendimento  ");
			}
			
			if (cabecalhoAnaliseCreditoTO.getInDisponivel() != null) {
				queryStr.append(" and cac.inDisponivel = :inDisponivel  ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito() != null && !cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito().equals("")) {
				query.setParameter("nmCabecalhoAnaliseCredito", "%" + cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito() + "%");
			}
			
			if (cabecalhoAnaliseCreditoTO.getCanalAtendimento() != null && cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento() != null) {
				query.setParameter("idCanalAtendimento", cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento());
			}
			
			if (cabecalhoAnaliseCreditoTO.getInDisponivel() != null) {
				query.setParameter("inDisponivel", cabecalhoAnaliseCreditoTO.getInDisponivel());
			}
			
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
			list = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	public CabecalhoAnaliseCreditoTO findByName(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultTO = null;
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select cac from CabecalhoAnaliseCredito cac ");
			queryStr.append(" where upper(cac.nmCabecalhoAnaliseCredito) like upper(:nmCabecalhoAnaliseCredito) ");
			
			Query query = em.createQuery(queryStr.toString());
			
			query.setParameter("nmCabecalhoAnaliseCredito", cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito());
			
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
			cabecalhoAnaliseCreditoResultTO = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTO((CabecalhoAnaliseCredito) query.getSingleResult());

		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return cabecalhoAnaliseCreditoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CabecalhoAnaliseCreditoDAO#createUpdateCabecalhoAnaliseCredito(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public CabecalhoAnaliseCreditoTO createUpdateCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoResultadoTO = null;
		
		
		try {
			cabecalhoAnaliseCreditoResultadoTO = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTO(em.merge(cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCredito(cabecalhoAnaliseCreditoTO)));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return cabecalhoAnaliseCreditoResultadoTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CabecalhoAnaliseCreditoDAO#findById(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public CabecalhoAnaliseCreditoTO findById(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		return findById(cabecalhoAnaliseCreditoTO, new CabecalhoAnaliseCreditoTOBuilder());
	}
	
	public CabecalhoAnaliseCreditoTO findById(
			CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO,
			CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder)
			throws DAOException {
		CabecalhoAnaliseCreditoTO result = null;
		try {
			Integer idCabecalhoAnaliseCredito = cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito();
			CabecalhoAnaliseCredito cabecalhoAnaliseCredito = em.find(CabecalhoAnaliseCredito.class, idCabecalhoAnaliseCredito);
			result = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTO(cabecalhoAnaliseCredito);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CabecalhoAnaliseCreditoDAO#removeCabecalhoAnaliseCredito(com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO)
	 */
	public void removeCabecalhoAnaliseCredito(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		try {
			em.remove(em.find(CabecalhoAnaliseCredito.class, cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	

	@SuppressWarnings("unchecked")
	public List<CabecalhoAnaliseCreditoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
		List<CabecalhoAnaliseCreditoTO> cabecalhoAnaliseCreditoTOList = null;
		
		try {
			Query query = em.createNamedQuery("CabecalhoAnaliseCredito.findAll");
			cabecalhoAnaliseCreditoTOList = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return cabecalhoAnaliseCreditoTOList;
	}



	public CabecalhoAnaliseCreditoTO copiarCabecalhoAnaliseCredito(Integer idCabecalhoAnaliseCredito) throws DAOException {
		logger.debug("idCabecalhoAnaliseCreditoTO: " + idCabecalhoAnaliseCredito);
		
		CabecalhoAnaliseCreditoTO to = this.findById(new CabecalhoAnaliseCreditoTO(idCabecalhoAnaliseCredito));
		
		to.setIdCabecalhoAnaliseCredito(null);
		this.createUpdateCabecalhoAnaliseCredito(to);
		
		return to;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CabecalhoAnaliseCreditoTO> findAllNoChild() throws DAOException {
		logger.debug("[findAll]");
		
		CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
		List<CabecalhoAnaliseCreditoTO> cabecalhoAnaliseCreditoTOList = null;
		
		try {
			Query query = em.createNamedQuery("CabecalhoAnaliseCredito.findAll");
			cabecalhoAnaliseCreditoTOList = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTOListNoChild(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return cabecalhoAnaliseCreditoTOList;
	}
	
	public CabecalhoAnaliseCreditoTO findByIdNoChild(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO) throws DAOException {
		logger.debug("cabecalhoAnaliseCreditoTO: " + cabecalhoAnaliseCreditoTO);
		
		CabecalhoAnaliseCreditoTO result = null;
		CabecalhoAnaliseCreditoTOBuilder cabecalhoAnaliseCreditoTOBuilder = new CabecalhoAnaliseCreditoTOBuilder();
		
		try {
			result = cabecalhoAnaliseCreditoTOBuilder.createCabecalhoAnaliseCreditoTOListNoChild(em.find(CabecalhoAnaliseCredito.class, cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

}
