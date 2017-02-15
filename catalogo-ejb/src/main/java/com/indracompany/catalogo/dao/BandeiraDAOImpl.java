package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.BandeiraDAO;
import com.indracompany.catalogo.datalayer.Bandeira;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.BandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco dos dados de Bandeira.
 */
@Stateless
public class BandeiraDAOImpl implements BandeiraDAO {
	
	private static Logger logger = Logger.getLogger(BandeiraDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.BandeiraDAO#searchBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	@SuppressWarnings("unchecked")
	public List<BandeiraTO> searchBandeira(BandeiraTO bandeiraTO) throws DAOException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		List<BandeiraTO> list = null;
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select b from Bandeira b ");
			queryStr.append(" where b.dtCriacao is not null ");
			
			if (bandeiraTO.getNmBandeira() != null && !bandeiraTO.getNmBandeira().equals("")) {
				queryStr.append(" and upper(b.nmBandeira) like upper(:nmBandeira) ");
			}
			
			if (bandeiraTO.getCdBandeiraSAP() != null && !bandeiraTO.getCdBandeiraSAP().equals("")) {
				queryStr.append(" and upper(b.cdBandeiraSAP) like upper(:cdBandeiraSAP) ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (bandeiraTO.getNmBandeira() != null && !bandeiraTO.getNmBandeira().equals("")) {
				query.setParameter("nmBandeira", "%" + bandeiraTO.getNmBandeira() + "%");
			}
			
			if (bandeiraTO.getCdBandeiraSAP() != null && !bandeiraTO.getCdBandeiraSAP().equals("")) {
				query.setParameter("cdBandeiraSAP", "%" + bandeiraTO.getCdBandeiraSAP() + "%");
			}
			
			BandeiraTOBuilder bandeiraTOBuilder = new BandeiraTOBuilder();
			list = bandeiraTOBuilder.createBandeiraTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.BandeiraDAO#createUpdateBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public void createUpdateBandeira(BandeiraTO bandeiraTO) throws DAOException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		BandeiraTOBuilder bandeiraTOBuilder = new BandeiraTOBuilder();
		
		try {
			em.merge(bandeiraTOBuilder.createBandeira(bandeiraTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.BandeiraDAO#findById(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public BandeiraTO findById(BandeiraTO bandeiraTO) throws DAOException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		BandeiraTO acaoResultTO = null;
		BandeiraTOBuilder bandeiraTOBuilder = new BandeiraTOBuilder();
		
		try {
			acaoResultTO = bandeiraTOBuilder.createBandeiraTO(em.find(Bandeira.class, bandeiraTO.getIdBandeira()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return acaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.BandeiraDAO#removeBandeira(com.indracompany.catalogo.to.BandeiraTO)
	 */
	public void removeBandeira(BandeiraTO bandeiraTO) throws DAOException {
		logger.debug("bandeiraTO: " + bandeiraTO);
		
		try {
			em.remove(em.find(Bandeira.class, bandeiraTO.getIdBandeira()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	public Boolean existByCdBandeiraSAP(BandeiraTO bandeiraTO) throws DAOException {
		
		try {
			
			Query query = em.createQuery("select b from Bandeira b where b.cdBandeiraSAP = :cdBandeiraSAP ");
			query.setParameter("cdBandeiraSAP", bandeiraTO.getCdBandeiraSAP());
			query.getSingleResult();
			
		} catch (NoResultException e) { 
			return false;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<BandeiraTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		BandeiraTOBuilder bandeiraTOBuilder = new BandeiraTOBuilder();
		List<BandeiraTO> bandeiraTOList = null;
		
		try {
			Query query = em.createNamedQuery("Bandeira.findAll");
			bandeiraTOList = bandeiraTOBuilder.createBandeiraTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return bandeiraTOList;
	}
}
