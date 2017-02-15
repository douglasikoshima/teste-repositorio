package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.PlataformaDAO;
import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Plataforma.
 */
@Stateless
public class PlataformaDAOImpl implements PlataformaDAO {
	
	private static Logger logger = Logger.getLogger(PlataformaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.PlataformaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PlataformaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		PlataformaTOBuilder plataformaTOBuilder = new PlataformaTOBuilder();
		List<PlataformaTO> plataformaList = null;
		
		try {
			Query query = em.createNamedQuery("Plataforma.findAll");
			plataformaList = plataformaTOBuilder.createPlataformaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return plataformaList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.PlataformaDAO#findAllWithExpections(java.lang.Integer[])
	 */
	@SuppressWarnings("unchecked")
	public List<PlataformaTO> findAllWithExpections(Integer[] idPlataformas) throws DAOException {
		logger.debug("[findAll]");
		
		PlataformaTOBuilder plataformaTOBuilder = new PlataformaTOBuilder();
		List<PlataformaTO> plataformaList = null;
		
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select p from Plataforma p  ");
			queryStr.append(" where p.idPlataforma not in( ");
			
			for (int i = 0; i < idPlataformas.length; i++) {
				if ( i == 0) {
					queryStr.append(" :idPlataforma" + i + " ");
				} else {
					queryStr.append(", :idPlataforma" + i + " ");
				}
			}
			
			queryStr.append(" ) ");
			
			Query query = em.createQuery(queryStr.toString());
			
			for (int i = 0; i < idPlataformas.length; i++) {
				query.setParameter("idPlataforma" + i, idPlataformas[i]);
			}
			
			plataformaList = plataformaTOBuilder.createPlataformaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAllWithExpections]", e);
		}
	    return plataformaList;
	}
    
	public PlataformaTO findByIdPlataforma(Integer idPlataforma) throws DAOException{
		PlataformaTO plataformaTO; 
		
		try {
			StringBuilder queryStr = new StringBuilder(
				"select p " +
				"from Plataforma p " +
				"where p.idPlataforma = :idPlataforma "	
			);
			Query query = em.createQuery(queryStr.toString());
			plataformaTO = new PlataformaTOBuilder().createPlataformaTO((Plataforma) query.setParameter("idPlataforma", idPlataforma).getSingleResult());
		} catch(Exception e){
			throw new DAOException("Erro ao executar o DAO [findByIdPlataforma]", e);
		}
		
		return plataformaTO;
	}
	
    @SuppressWarnings("unchecked")
    public List<PlataformaTO> findByIdCanalAtendimento(Integer idCanalAtendimento) throws DAOException {
        logger.debug(idCanalAtendimento);
        List<PlataformaTO> plataformaTOList;
        try {
            plataformaTOList = new PlataformaTOBuilder().createPlataformaTOList(em.createNamedQuery("Plataforma.findByIdCanalAtendimento").setParameter("idCanalAtendimento", idCanalAtendimento).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findByIdCanalAtendimento]", e);
        }
        return plataformaTOList;
    }
    
    public Plataforma findById(Integer idPlataforma) throws DAOException { 
    	logger.debug(idPlataforma);
    	Plataforma plataforma = new Plataforma();
    	    	
    	try {
			plataforma = em.find(Plataforma.class, idPlataforma);					
		} catch (Exception e) {			
			e.printStackTrace();
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
    	
    	return plataforma;
    }
}
