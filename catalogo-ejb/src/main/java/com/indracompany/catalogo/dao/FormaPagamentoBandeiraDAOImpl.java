package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FormaPagamentoBandeiraDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Forma Pagamento Bandeira.
 */
@Stateless
public class FormaPagamentoBandeiraDAOImpl implements FormaPagamentoBandeiraDAO {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoBandeiraDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.PlataformaDAO#findAll()
	 */
	public void createUpdateFormaPagamentoBandeira(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO) throws DAOException {
		logger.debug("formaPagamentoBandeiraTO: " + formaPagamentoBandeiraTO);
		
		FormaPagamentoBandeiraTOBuilder formaPagamentoBandeiraTOBuilder = new FormaPagamentoBandeiraTOBuilder();
		
		try {
			em.merge(formaPagamentoBandeiraTOBuilder.createFormaPagamentoBandeira(formaPagamentoBandeiraTO));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoBandeiraDAO#removeFormaPagamentoBandeira(com.indracompany.catalogo.to.FormaPagamentoBandeiraTO)
	 */
	public void removeFormaPagamentoBandeira(FormaPagamentoBandeiraTO formaPagamentoBandeiraTO) throws DAOException {
		logger.debug("formaPagamentoBandeiraTO: " + formaPagamentoBandeiraTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.FORMAPAGAMENTOBANDEIRA WHERE IDFORMAPAGAMENTO = :idFormaPagamento ");
			query.setParameter("idFormaPagamento", formaPagamentoBandeiraTO.getIdFormaPagamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeFormaPagamentoBandeira]", e);
		}
	}
}
