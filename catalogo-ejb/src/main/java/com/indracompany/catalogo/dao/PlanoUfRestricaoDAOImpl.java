package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.PlanoUfRestricaoDAO;
import com.indracompany.catalogo.datalayer.PlanoUfRestricao;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;

@Stateless
public class PlanoUfRestricaoDAOImpl implements PlanoUfRestricaoDAO {
	
	private static Logger logger = Logger.getLogger(PlanoUfRestricaoDAOImpl.class); 
	
	@PersistenceContext
	private EntityManager em;

	public void removeByIdsPlano(List<Integer> idPlanoList) throws DAOException {
		
		logger.debug(">> removeByIdsPlano()");
		
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" DELETE FROM PLANOUFRESTRICAO PUR ");
			queryStr.append(" WHERE PUR.IDPLANO IN ( :idPlanoList ) ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			
			query.setParameter("idPlanoList", idPlanoList);
			
			query.executeUpdate();
			
			logger.debug("<< removeByIdsPlano()");
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeByIdsPlano]", e);
		}

	}

	public void createUpdatePlanoUfRestricao(PlanoServicoUfRestricaoTO to) throws DAOException {
		
		logger.debug(">> createUpdatePlanoUfRestricao()");
		
		List<PlanoUfRestricao> entityList;
		PlanoUfRestricaoTOBuilder builder = new PlanoUfRestricaoTOBuilder();
		
		entityList = builder.createPlanoUfRestricaoList(to);
		
		for (PlanoUfRestricao pur : entityList) {
			
			try {
				em.merge(pur);
			} catch (Exception e) {
				throw new DAOException("Erro ao executar o DAO [createUpdatePlanoUfRestricao]", e);
			}
			
		}
		
		logger.debug("<< createUpdatePlanoUfRestricao()");
	}

	public void createUpdatePlanoUfRestricaoList(List<PlanoServicoUfRestricaoTO> toList) throws DAOException {
		
		logger.debug(">> createUpdatePlanoUfRestricaoList()");
		
		for (PlanoServicoUfRestricaoTO psTO : toList) {
			
			if (psTO.getUfTOList().size() > 0) {
				createUpdatePlanoUfRestricao(psTO);
			}

		}
		
		logger.debug("<< createUpdatePlanoUfRestricaoList()");
	}

}
