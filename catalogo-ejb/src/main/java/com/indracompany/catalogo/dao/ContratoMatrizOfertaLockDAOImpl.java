package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO;
import com.indracompany.catalogo.datalayer.ContratoMatrizOfertaLock;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de ContratoMatrizOfertaLock.
 */
@Stateless
public class ContratoMatrizOfertaLockDAOImpl implements ContratoMatrizOfertaLockDAO {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaLockDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO#createUpdateContratoMatrizOfertaLock(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public void createUpdateContratoMatrizOfertaLock(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		ContratoMatrizOfertaLockTOBuilder contratoMatrizOfertaLockTOBuilder = new ContratoMatrizOfertaLockTOBuilder();
		
		try {
			em.merge(contratoMatrizOfertaLockTOBuilder.createContratoMatrizOferta(contratoMatrizOfertaLockTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO#existFileLockedByUser(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public Boolean existFileLockedByUser(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			
			Query query = em.createQuery("select cmol from ContratoMatrizOfertaLock cmol where cmol.nmUsuarioCriacao = :nmUsuarioCriacao and cmol.inStatus = 'S'");
			query.setParameter("nmUsuarioCriacao", contratoMatrizOfertaLockTO.getNmUsuarioCriacao());
			query.getSingleResult();
			
		} catch (NoResultException e) {
			retorno = Boolean.FALSE;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return retorno;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO#findFileLockedCurrent(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public ContratoMatrizOfertaLockTO findFileLockedCurrent() throws DAOException {
		logger.info("findFileLockedCurrent ");
		
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTOResult = null;
		ContratoMatrizOfertaLockTOBuilder contratoMatrizOfertaLockTOBuilder = new ContratoMatrizOfertaLockTOBuilder();
		
		try {
			
			Query query = em.createQuery("select cmol from ContratoMatrizOfertaLock cmol where cmol.inStatus = 'S'");
			contratoMatrizOfertaLockTOResult = contratoMatrizOfertaLockTOBuilder.createContratoMatrizOfertaLockTO((ContratoMatrizOfertaLock) query.getSingleResult());
			
		} catch (NoResultException e) {
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return contratoMatrizOfertaLockTOResult;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO#updateStatus(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public void updateStatusLiberar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		try {
			
			Query query = em.createNativeQuery("update CATALOGOPRS_OW.CONTRATOMATRIZOFERTALOCK set INSTATUS = 'N', INLIBERAR = 'S', DTALTERACAO = ?, NMUSUARIOALTERACAO = ? where INSTATUS = 'S'");
			query.setParameter(1, contratoMatrizOfertaLockTO.getDtAlteracao());
			query.setParameter(2, contratoMatrizOfertaLockTO.getNmUsuarioAlteracao());
			query.executeUpdate();
			
		} catch (NoResultException e) {
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO#updateStatusImportar(com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO)
	 */
	public void updateStatusImportar(ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO) throws DAOException {
		logger.info("contratoMatrizOfertaLockTO " + contratoMatrizOfertaLockTO);
		
		try {
			
			Query query = em.createNativeQuery("update CATALOGOPRS_OW.CONTRATOMATRIZOFERTALOCK set INIMPORTACAO = 'S', INVALIDADO = 'N', DTALTERACAO = ?, NMUSUARIOALTERACAO = ? where INSTATUS = 'S'");
			query.setParameter(1, contratoMatrizOfertaLockTO.getDtAlteracao());
			query.setParameter(2, contratoMatrizOfertaLockTO.getNmUsuarioAlteracao());
			query.executeUpdate();
			
		} catch (NoResultException e) {
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}
