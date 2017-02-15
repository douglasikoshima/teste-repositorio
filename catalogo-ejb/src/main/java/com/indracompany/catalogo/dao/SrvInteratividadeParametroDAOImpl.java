package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParametroDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SrvInteratividadeParamTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Servico Interatividade Parametro.
 */
@Stateless
public class SrvInteratividadeParametroDAOImpl implements SrvInteratividadeParametroDAO {
	
	private static Logger logger = Logger.getLogger(SrvInteratividadeParametroDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParametroDAO#createUpdateSrvInteratividadeParam(com.indracompany.catalogo.to.SrvInteratividadeParamTO)
	 */
	public void createUpdateSrvInteratividadeParam(SrvInteratividadeParamTO srvInteratividadeParametroTO) throws DAOException {
		logger.debug("srvInteratividadeParametroTO: " + srvInteratividadeParametroTO);
		
		SrvInteratividadeParamTOBuilder srvInteratividadeParametroTOBuilder = new SrvInteratividadeParamTOBuilder();
		
		try {
			em.merge(srvInteratividadeParametroTOBuilder.createSrvInteratividadeParametro(srvInteratividadeParametroTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.SrvInteratividadeParametroDAO#removeSrvInteratividadeParamById(java.lang.Integer)
	 */
	public void removeSrvInteratividadeParamById(Integer idServicoInteratividade) throws DAOException {
		logger.debug("idServicoInteratividade: " + idServicoInteratividade);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.SRVINTERATIVIDADEPARAM where IDSERVICOINTERATIVIDADE = :idServicoInteratividade ");
			query.setParameter("idServicoInteratividade", idServicoInteratividade);
			query.executeUpdate();
		
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
