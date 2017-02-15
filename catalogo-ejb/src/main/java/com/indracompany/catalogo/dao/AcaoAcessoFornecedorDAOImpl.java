package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AcaoAcessoFornecedorDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoAcessoFornecedorTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Acao Acesso Fornecedor
 */
@Stateless
public class AcaoAcessoFornecedorDAOImpl implements AcaoAcessoFornecedorDAO {
	
	private static Logger logger = Logger.getLogger(AcaoAcessoFornecedorDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoAcessoFornecedorDAO#createUpdateAcaoAcessoFornecedor(com.indracompany.catalogo.to.AcaoAcessoFornecedorTO)
	 */
	public void createUpdateAcaoAcessoFornecedor(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException {
		logger.debug("acaoAcessoFornecedorTO: " + acaoAcessoFornecedorTO);
		
		AcaoAcessoFornecedorTOBuilder acaoAcessoFornecedorTOBuilder = new AcaoAcessoFornecedorTOBuilder();
		
		try {
			em.merge(acaoAcessoFornecedorTOBuilder.createAcaoAcessoFornecedor(acaoAcessoFornecedorTO));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [createUpdateAcaoAcessoFornecedor]", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoAcessoFornecedorDAO#removeAcaoAcessoFornecedor(com.indracompany.catalogo.to.AcaoAcessoFornecedorTO)
	 */
	public void removeAcaoAcessoFornecedor(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException {
		logger.debug("acaoAcessoFornecedorTO: " + acaoAcessoFornecedorTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.ACAOACESSOFORNECEDOR WHERE IDACAO = :idAcao ");
			query.setParameter("idAcao", acaoAcessoFornecedorTO.getIdAcao());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeAcaoAcessoFornecedor]", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findFornecedorByAcao(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException {
		logger.debug("acaoAcessoFornecedorTO: " + acaoAcessoFornecedorTO);
		
		List<Integer> list = null;
		
		try {
			Query query = em.createQuery("select distinct aaf.idFornecedor from AcaoAcessoFornecedor aaf where aaf.idAcao = :idAcao ");
			query.setParameter("idAcao", acaoAcessoFornecedorTO.getIdAcao());
			
			list = query.getResultList();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findFornecedorByAcao]", e);
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findPerfilByAcao(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) throws DAOException {
		logger.debug("acaoAcessoFornecedorTO: " + acaoAcessoFornecedorTO);
		
		List<Integer> list = null;
		
		try {
			Query query = em.createQuery("select distinct aaf.idPerfilSCA from AcaoAcessoFornecedor aaf where aaf.idAcao = :idAcao ");
			query.setParameter("idAcao", acaoAcessoFornecedorTO.getIdAcao());
			
			list = query.getResultList();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findPerfilByAcao]", e);
		}
		
		return list;
	}
}
