package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AcaoProdutoDAO;
import com.indracompany.catalogo.datalayer.AcaoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Ações.
 */
@Stateless
public class AcaoProdutoDAOImpl implements AcaoProdutoDAO {
	
	private static Logger logger = Logger.getLogger(AcaoProdutoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#createUpdateAcaoProduto(com.indracompany.catalogo.to.AcaoProdutoTO)
	 */
	public void createUpdateAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException {
		logger.debug("acaoProdutoTO: " + acaoProdutoTO);
		
		AcaoProdutoTOBuilder acaoProdutoTOBuilder = new AcaoProdutoTOBuilder();
		
		AcaoProduto acaoProduto = acaoProdutoTOBuilder.createAcaoProduto(acaoProdutoTO);
		
		try {
			em.merge(acaoProduto);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#removeAcaoProduto(com.indracompany.catalogo.to.AcaoProdutoTO)
	 */
	public void removeAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException {
		logger.debug("acaoProdutoTO: " + acaoProdutoTO);
		
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.ACAOPRODUTO WHERE IDACAO = :idAcao AND IDPRODUTO = :idProduto AND IDORGANIZACAOVENDAS = :idOrganizacaoVendas AND IDCANAL = :idCanal AND IDPLATAFORMA = :idPlataforma ");
			query.setParameter("idAcao", acaoProdutoTO.getAcaoTO().getIdAcao());
			query.setParameter("idProduto", acaoProdutoTO.getProdutoTO().getIdProduto());
			query.setParameter("idOrganizacaoVendas", acaoProdutoTO.getOrganizacaoVendaTO().getIdOrganizacaoVendas());
			query.setParameter("idCanal", acaoProdutoTO.getCanalTO().getIdCanal());
			query.setParameter("idPlataforma", acaoProdutoTO.getPlataformaTO().getIdPlataforma());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void removeAssociationAcaoProduto(AcaoProdutoTO acaoProdutoTO) throws DAOException {
		logger.debug("acaoProdutoTO: " + acaoProdutoTO);
		
		try {
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.ACAOPRODUTO WHERE IDACAO = :idAcao");
			query.setParameter("idAcao", acaoProdutoTO.getAcaoTO().getIdAcao());
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
