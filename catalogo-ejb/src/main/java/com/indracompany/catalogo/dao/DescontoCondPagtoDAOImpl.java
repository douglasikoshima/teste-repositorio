package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.DescontoCondPagtoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DescontoCondPagtoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Desconto Condição Pagamento.
 */
@Stateless
public class DescontoCondPagtoDAOImpl implements DescontoCondPagtoDAO {
	
	private static Logger logger = Logger.getLogger(DescontoCondPagtoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.DescontoCondPagtoDAO#createUpdateDescontoCondPagto(com.indracompany.catalogo.to.DescontoCondPagtoTO)
	 */
	public void createUpdateDescontoCondPagto(DescontoCondPagtoTO descontoCondPagtoTO) throws DAOException {
		logger.debug("descontoCondPagtoTO: " + descontoCondPagtoTO);
		
		DescontoCondPagtoTOBuilder descontoCondPagtoTOBuilder = new DescontoCondPagtoTOBuilder();
		
		try {
			em.merge(descontoCondPagtoTOBuilder.createDescontoCondPagto(descontoCondPagtoTO));

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [createUpdateDescontoCondPagto]", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.DescontoCondPagtoDAO#removeDescontoCondPagtoByFormaPagamento(java.lang.Integer)
	 */
	public void removeDescontoCondPagtoByFormaPagamento(Integer idFormaPagamento) throws DAOException {
		logger.debug("idFormaPagamento: " + idFormaPagamento);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.DESCONTOCONDPAGTO DC WHERE DC.IDCONDICAOPAGAMENTO IN (SELECT IDCONDICAOPAGAMENTO FROM CATALOGOPRS_OW.CONDICAOPAGAMENTO CP WHERE CP.IDFORMAPAGAMENTO = :idFormaPagamento) ");
			query.setParameter("idFormaPagamento", idFormaPagamento);
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeCondicaoPagamento]", e);
		}
	}
	
}
