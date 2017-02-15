package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO;
import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

@Stateless
public class ValorCaracteristicaDAOImpl implements ValorCaracteristicaDAO {
	
	private static Logger logger = Logger.getLogger(ValorCaracteristicaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ValorCaracteristicaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		List<ValorCaracteristicaTO> list = null;
		ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
		try {
			Query query = em.createNamedQuery("ValorCaracteristica.findAll");
			list = valorCaracteristicaTOBuilder.createValorCaracteristicaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
			
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO#findByCaracteristica(com.indracompany.catalogo.to.ValorCaracteristicaTO)
	 */
	@SuppressWarnings("unchecked")
	public List<ValorCaracteristicaTO> findByCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		List<ValorCaracteristicaTO> list = null;
		ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
			queryStr.append("select v from ValorCaracteristica v where v.caracteristica.idCaracteristica = :idCaracteristica ");
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idCaracteristica", valorCaracteristicaTO.getIdValorCaracteristica());
			list = valorCaracteristicaTOBuilder.createValorCaracteristicaTOList(query.getResultList());

		} catch (NoResultException e) {
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByCaracteristica]", e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO#createUpdateValorCaracteristica(com.indracompany.catalogo.to.ValorCaracteristicaTO)
	 */
	public void createUpdateValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
		
		try {
			em.merge(valorCaracteristicaTOBuilder.createValorCaracteristica(valorCaracteristicaTO));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO#deleteValorCaracteristica(com.indracompany.catalogo.to.ValorCaracteristicaTO)
	 */
	public void deleteValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		try {
			em.remove(em.find(ValorCaracteristica.class, valorCaracteristicaTO.getIdValorCaracteristica())); 
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ValorCaracteristicaDAO#findByValor(com.indracompany.catalogo.to.ValorCaracteristicaTO)
	 */
	public ValorCaracteristicaTO findByValor(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		
		ValorCaracteristicaTO valorCaracteristicaResultTO = null;
		ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
			queryStr.append("select v from ValorCaracteristica v ");
			queryStr.append(" where v.caracteristica.idCaracteristica = :idCaracteristica ");
			queryStr.append(" and upper(v.valor) = upper(:valor)");
			
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idCaracteristica", valorCaracteristicaTO.getCaracteristicaTO().getIdCaracteristica());
			query.setParameter("valor", valorCaracteristicaTO.getValor());
			valorCaracteristicaResultTO = valorCaracteristicaTOBuilder.createValorCaracteristicaTO((ValorCaracteristica) query.getSingleResult());

		} catch (NoResultException e) {
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByValor]", e);
		}
		
		return valorCaracteristicaResultTO;
	}
	
	public ValorCaracteristicaTO find(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		
		ValorCaracteristicaTO valorCaracteristicaResultTO = null;
		ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
		
		try {
			
			valorCaracteristicaResultTO = valorCaracteristicaTOBuilder.createValorCaracteristicaTO(em.find(ValorCaracteristica.class, valorCaracteristicaTO.getIdValorCaracteristica()));
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [find]", e);
		}
		
		return valorCaracteristicaResultTO;
	}
	
	public ValorCaracteristica findById(Integer idValorCaracteristica) throws DAOException {
		ValorCaracteristica valorCaracteristica = null;
		try {
			valorCaracteristica = em.find(ValorCaracteristica.class, idValorCaracteristica);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return valorCaracteristica;
	}
	
	public ValorCaracteristica findByNome(Integer idCaracteristica, String valor) throws DAOException {
		ValorCaracteristica valorCaracteristica = null;
		try {
			Query select = em.createQuery("select v from ValorCaracteristica v join v.caracteristica c where c.idCaracteristica = ? and v.valor = ?");
			select.setParameter(1, idCaracteristica);
			select.setParameter(2, valor);
			valorCaracteristica = (ValorCaracteristica) select.getSingleResult();
		} catch (NoResultException e) {
			valorCaracteristica = null;
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByNome]", e);
		}
		return valorCaracteristica;
	}
	
	public void save(ValorCaracteristicaTO valorCaracteristicaTO) throws DAOException {
		logger.debug("valorCaracteristicaTO: " + valorCaracteristicaTO);
		try {
			ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
			ValorCaracteristica valorCaracteristica = valorCaracteristicaTOBuilder.createValorCaracteristica(valorCaracteristicaTO);
			save(valorCaracteristica);
			valorCaracteristicaTO.setIdValorCaracteristica(valorCaracteristica.getIdValorCaracteristica());
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(ValorCaracteristica valorCaracteristica) throws DAOException {
		try {
			em.persist(valorCaracteristica);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
}