package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaStatusImportacaoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

/**
 * @author equipe Catalogo
 * 
 * Classe responsável em fazer a persistencia no banco de dados de importacao de ServicoServicoArq.
 */
@Stateless
public class MatrizOfertaStatusImportacaoDAOImpl implements MatrizOfertaStatusImportacaoDAO {
	
	private static Logger logger = Logger.getLogger(MatrizOfertaStatusImportacaoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@SuppressWarnings("unchecked")
	public List<StatusArquivoImportacaoTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		MatrizOfertaStatusImportacaoTOBuilder builder = new MatrizOfertaStatusImportacaoTOBuilder(); 
		List<StatusArquivoImportacaoTO> list = null;
		
		try {
			Query query = em.createNamedQuery("MatrizOfertaStatusImportacao.findAll");
			list = builder.createStatusArquivoImportacaoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return list;
	}
	
}
