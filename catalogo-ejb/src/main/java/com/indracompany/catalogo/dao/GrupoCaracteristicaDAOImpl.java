package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO;
import com.indracompany.catalogo.datalayer.GrupoCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

@Stateless
public class GrupoCaracteristicaDAOImpl implements GrupoCaracteristicaDAO {

	private static Logger logger = Logger.getLogger(GrupoCaracteristicaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoCaracteristicaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		List<GrupoCaracteristicaTO> list = null;
		GrupoCaracteristicaTOBuilder grupoCaracteristicaTOBuilder = new GrupoCaracteristicaTOBuilder();
		try {
			Query query = em.createNamedQuery("GrupoCaracteristica.findAll");
			list = grupoCaracteristicaTOBuilder.createGrupoCaracteristicaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
			
		return list;
	}	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO#createUpdateGrupoCaracteristica(com.indracompany.catalogo.to.GrupoCaracteristicaTO)
	 */
	public void createUpdateGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		GrupoCaracteristicaTOBuilder grupoCaracteristicaTOBuilder = new GrupoCaracteristicaTOBuilder();
		try {
			em.merge(grupoCaracteristicaTOBuilder.createGrupoCaracteristica(grupoCaracteristicaTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO#deleteGrupoCaracteristica(com.indracompany.catalogo.to.GrupoCaracteristicaTO)
	 */
	public void deleteGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		try {			
			em.remove(em.find(GrupoCaracteristica.class,grupoCaracteristicaTO.getIdGrupoCaracteristica()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoCaracteristicaDAO#getGrupoCaracteristica(com.indracompany.catalogo.to.GrupoCaracteristicaTO)
	 */
	public GrupoCaracteristicaTO getGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) throws DAOException {
		logger.debug("grupoCaracteristicaTO: " + grupoCaracteristicaTO);
		
		GrupoCaracteristicaTO grupo = null;
		GrupoCaracteristicaTOBuilder grupoCaracteristicaTOBuilder = new GrupoCaracteristicaTOBuilder();
		Query query = em.createQuery("select gc from GrupoCaracteristica gc where UPPER(gc.nmGrupoCaracteristica) = UPPER(:nmGrupoCaracteristica)");
		query.setParameter("nmGrupoCaracteristica",grupoCaracteristicaTO.getNmGrupoCaracteristica());
		try {
			grupo = grupoCaracteristicaTOBuilder.createGrupoCaracteristicaTO((GrupoCaracteristica)query.getSingleResult());
		} catch (NoResultException nre) {			
		}
		return grupo;
	}

}
