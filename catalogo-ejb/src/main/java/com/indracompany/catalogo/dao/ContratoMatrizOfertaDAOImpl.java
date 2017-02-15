package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO;
import com.indracompany.catalogo.datalayer.ContratoMatrizOferta;
import com.indracompany.catalogo.exception.DAOException;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de ContratoMatrizOferta.
 */
@Stateless
public class ContratoMatrizOfertaDAOImpl implements ContratoMatrizOfertaDAO {
	
	private static Logger logger = Logger.getLogger(ContratoMatrizOfertaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO#exportContratoMatrizOferta()
	 */
	@SuppressWarnings("unchecked")
	public List<String> exportContratoMatrizOferta() throws DAOException {
		logger.debug("exportContratoMatrizOferta");
		
		List<String> listRetorno = null;
		
		try {
			
			// REALIZADO COM NATIVE QUERY POIS NÃO HÁ CONCATENAÇÃO DE RESULTADO NO JPA
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("SELECT ");
			queryStr.append("  (SELECT CDCODIGO FROM CATALOGOPRS_OW.SISTEMAPLANO SP WHERE SP.IDSISTEMAPLANO = MOTC.IDSISTEMAPLANO) || ';' ||  ");
			queryStr.append("(SELECT CDCODIGO FROM CATALOGOPRS_OW.SISTEMASERVICO SS WHERE SS.IDSISTEMASERVICO = MOTC.IDSISTEMASERVICO) || ';' || ");
			queryStr.append("(SELECT NMCSA FROM CATALOGOPRS_OW.CSA CSA WHERE CSA.IDCSA = MOTC.IDCSA) || ';' || ");
			queryStr.append("MOTC.VALOR AS CONCATENADO ");
			queryStr.append(" FROM ");
			queryStr.append(" CATALOGOPRS_OW.MATRIZOFERTATIPOCONTRATO MOTC ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			listRetorno = query.getResultList();
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listRetorno;
	} 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO#deleteAll()
	 */
	public void deleteAll() throws DAOException {
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.CONTRATOMATRIZOFERTA");
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO#createUpdateContratoMatrizOferta(com.indracompany.catalogo.to.ContratoMatrizOfertaTO)
	 */
	public void createUpdateContratoMatrizOferta(ContratoMatrizOferta contratoMatrizOferta) throws DAOException {
		
		try {
			em.persist(contratoMatrizOferta);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO#createUpdateContratoMatrizOferta(java.util.List)
	 */
	public void createUpdateContratoMatrizOferta(List<ContratoMatrizOferta> contratoMatrizOfertaTOList) throws DAOException {
		
		if (contratoMatrizOfertaTOList != null) {
			
			Integer contador = 0;
			
			for (ContratoMatrizOferta contratoMatrizOferta : contratoMatrizOfertaTOList) {
				if (contratoMatrizOferta != null) {
					em.persist(contratoMatrizOferta);
					if (contador % 400 == 0) {
						em.flush();
						em.clear();
					}
					contador++;
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO#validarDados()
	 */
	public Boolean validarDados() throws DAOException {
		
		try {
			
			Query query = em.createNativeQuery("CALL CATALOGOPRS_OW.PKG_IMPORTACAO_CONTRATO_MATRIZ.SP_VALIDARTABELAESPELHO()");
			query.executeUpdate();
		
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return Boolean.TRUE;
	}
	
	public Boolean liberarProducao() throws DAOException {
		
		try {
			
			Query query = em.createNativeQuery("CALL CATALOGOPRS_OW.PKG_IMPORTACAO_CONTRATO_MATRIZ.SP_LIBERARPRODUCAO()");
			query.executeUpdate();
		
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return Boolean.TRUE;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO#exportContratoMatrizOfertaCritica()
	 */
	@SuppressWarnings("unchecked")
	public List<String> exportContratoMatrizOfertaCriticas() throws DAOException {
		logger.debug("exportContratoMatrizOfertaCritica");
		
		List<String> listRetorno = null;
		
		try {
			
			StringBuffer queryStr = new StringBuffer(); 
			queryStr.append("SELECT CMO.CODIGOPLANO || ';' || CMO.CODIGOSERVICO || ';' || CMO.SIGLACSA || ';' || CMO.VALORCONTRATO || ';' || MOCC.DSCCRITICAIMPORTACAO AS CONCATENADO ");
			queryStr.append("FROM ");
			queryStr.append("	CATALOGOPRS_OW.CONTRATOMATRIZOFERTA CMO ");
			queryStr.append("LEFT JOIN CATALOGOPRS_OW.CONTRATOMATRIZOFERTACRITICA CMOC ");
			queryStr.append("ON (CMO.IDCONTRATOMATRIZ = CMOC.IDCONTRATOMATRIZ) ");
			queryStr.append("LEFT JOIN CATALOGOPRS_OW.MATRIZOFERTACONTRATOCRITICA MOCC ");
			queryStr.append("ON (CMOC.IDMATRIZOFERTAARQUIVOCRITICA = MOCC.IDMATRIZOFERTAARQUIVOCRITICA) ");
			queryStr.append("ORDER BY CMO.NUMEROLINHA ");
			
			// REALIZADO COM NATIVE QUERY POIS NÃO HÁ CONCATENAÇÃO DE RESULTADO NO JPA
			Query query = em.createNativeQuery(queryStr.toString());
			listRetorno = query.getResultList();
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listRetorno;
	} 
}
