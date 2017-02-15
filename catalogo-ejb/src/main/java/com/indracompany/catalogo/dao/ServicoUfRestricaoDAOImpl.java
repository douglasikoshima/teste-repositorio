package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoUfRestricaoDAO;
import com.indracompany.catalogo.datalayer.ServicoUfRestricao;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;

@Stateless
public class ServicoUfRestricaoDAOImpl implements ServicoUfRestricaoDAO {
	
	private static Logger logger = Logger.getLogger(ServicoUfRestricaoDAOImpl.class); 
	
	@PersistenceContext
	private EntityManager em;

	public void createUpdateServicoUfRestricao(PlanoServicoUfRestricaoTO to) throws DAOException {
		logger.debug(">> createUpdateServicoUfRestricao()");
		
		List<ServicoUfRestricao> entityList;
		ServicoUfRestricaoTOBuilder builder = new ServicoUfRestricaoTOBuilder();
		
		entityList = builder.createServicoUfRestricaoList(to);
		
		for (ServicoUfRestricao sur : entityList) {
			
			try {
				em.merge(sur);
			} catch (Exception e) {
				throw new DAOException("Erro ao executar o DAO [createUpdateServicoUfRestricao]", e);
			}
			
		}
		
		logger.debug("<< createUpdateServicoUfRestricao()");		
	}

	public void createUpdateServicoUfRestricaoList(List<PlanoServicoUfRestricaoTO> toList) throws DAOException {
		logger.debug(">> createUpdateServicoUfRestricaoList()");
		
		for (PlanoServicoUfRestricaoTO psTO : toList) {
			
			if (psTO.getUfTOList().size() > 0) {
				createUpdateServicoUfRestricao(psTO);
			}

		}
		
		logger.debug("<< createUpdateServicoUfRestricaoList()");		
	}

	public void removeByIdsServico(List<Integer> idServicoList) throws DAOException {
		logger.debug(">> removeByIdsServico()");
		
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" DELETE FROM SERVICOUFRESTRICAO SUR ");
			queryStr.append(" WHERE SUR.IDSERVICO IN ( :idServicoList ) ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			
			query.setParameter("idServicoList", idServicoList);
			
			query.executeUpdate();
			
			logger.debug("<< removeByIdsServico()");
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeByIdsServico]", e);
		}
	}

}
