package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO;
import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.IdNomeTO;
import com.indracompany.catalogo.to.PaginacaoDataTableTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Caracteristica.
 */
@Stateless
public class CaracteristicaDAOImpl implements CaracteristicaDAO {
	
	private static Logger logger = Logger.getLogger(CaracteristicaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#searchCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	@SuppressWarnings("unchecked")
	public List<CaracteristicaTO> searchCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		List<CaracteristicaTO> list = null;
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select c from Caracteristica c");
			queryStr.append(" where c.dtCriacao is not null ");
			
			if (caracteristicaTO.getNmCaracteristica() != null && !caracteristicaTO.getNmCaracteristica().equals("")) {				
				queryStr.append(" and upper(c.nmCaracteristica) like upper(:nmCaracteristica)");
			}
			
			if (caracteristicaTO.getGrupoCaracteristicaTO() != null) {				
				queryStr.append(" and c.grupoCaracteristica.idGrupoCaracteristica = :idGrupoCaracteristica ");
			}
			
			if (caracteristicaTO.getIndComparavel() != null) {				
				queryStr.append(" and c.indComparavel = :indComparavel ");
			}
			
			if (caracteristicaTO.getIndExibivel() != null) {				
				queryStr.append(" and c.indExibivel = :indExibivel ");
			}
			
			if (caracteristicaTO.getInDisponivel() != null) {				
				queryStr.append(" and c.inDisponivel = :inDisponivel ");
			}
			
			if (caracteristicaTO.getInSimulador() != null) {				
				queryStr.append(" and c.inSimulador = :inSimulador ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (caracteristicaTO.getNmCaracteristica() != null && !caracteristicaTO.getNmCaracteristica().equals("")) {	
				query.setParameter("nmCaracteristica", "%" + caracteristicaTO.getNmCaracteristica() + "%");
			}
			
			if (caracteristicaTO.getGrupoCaracteristicaTO() != null) {				
				query.setParameter("idGrupoCaracteristica", caracteristicaTO.getGrupoCaracteristicaTO().getIdGrupoCaracteristica());
			}
			
			if (caracteristicaTO.getIndComparavel() != null) {
				query.setParameter("indComparavel", caracteristicaTO.getIndComparavel());
			}
			
			if (caracteristicaTO.getIndExibivel() != null) {	
				query.setParameter("indExibivel", caracteristicaTO.getIndExibivel());
			}
			
			if (caracteristicaTO.getInDisponivel() != null) {	
				query.setParameter("inDisponivel", caracteristicaTO.getInDisponivel());
			}
			
			if (caracteristicaTO.getInSimulador() != null) {
				query.setParameter("inSimulador", caracteristicaTO.getInSimulador());
			}
			
			CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
			list = caracteristicaTOBuilder.createCaracteristicaTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#createUpdateCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public CaracteristicaTO createUpdateCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
		CaracteristicaTO caracteristicaResultadoTO = null;
		
		try {
			caracteristicaResultadoTO = caracteristicaTOBuilder.createCaracteristicaTO(em.merge(caracteristicaTOBuilder.createCaracteristica(caracteristicaTO)));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return caracteristicaResultadoTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#findById(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public CaracteristicaTO findById(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		CaracteristicaTO caracteristicaResultTO = null;
		CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
		
		try {
			caracteristicaResultTO = caracteristicaTOBuilder.createCaracteristicaTO(em.find(Caracteristica.class, caracteristicaTO.getIdCaracteristica()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return caracteristicaResultTO;
	}
	
	public Caracteristica findById(Integer idCaracteristica) throws DAOException {
		Caracteristica caracteristica = null;
		try {
			caracteristica = em.find(Caracteristica.class, idCaracteristica);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return caracteristica;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#removeCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public void removeCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		try {
			em.remove(em.find(Caracteristica.class, caracteristicaTO.getIdCaracteristica()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#findByName(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	public CaracteristicaTO findByName(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		CaracteristicaTO caracteristicaResultTO = null;
		
		try {
			
			String queryStr = "select c from Caracteristica c where upper(c.nmCaracteristica) = upper(:nmCaracteristica) ";
			Query query = em.createQuery(queryStr);
			query.setParameter("nmCaracteristica", caracteristicaTO.getNmCaracteristica());
			
			CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
			caracteristicaResultTO = caracteristicaTOBuilder.createCaracteristicaTO((Caracteristica)query.getSingleResult());
			
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return caracteristicaResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CaracteristicaDAO#findByGrupoCaracteristica(com.indracompany.catalogo.to.CaracteristicaTO)
	 */
	@SuppressWarnings("unchecked")
	public List<CaracteristicaTO> findByGrupoCaracteristica(CaracteristicaTO caracteristicaTO) throws DAOException {
		logger.debug("caracteristicaTO: " + caracteristicaTO);
		
		List<CaracteristicaTO> caracteristicaResultTO = null;
		
		try {
			
			String queryStr = "select c from Caracteristica c where c.grupoCaracteristica.idGrupoCaracteristica = :idGrupoCaracteristica";
			Query query = em.createQuery(queryStr);
			query.setParameter("idGrupoCaracteristica", caracteristicaTO.getGrupoCaracteristicaTO().getIdGrupoCaracteristica());
			
			CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
			caracteristicaResultTO = caracteristicaTOBuilder.createCaracteristicaTOList(query.getResultList());
			
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return caracteristicaResultTO;
	}

	@SuppressWarnings("unchecked")
	public List<CaracteristicaTO> findAll() throws DAOException {
		List<CaracteristicaTO> result = null;
		try {
			 result = new CaracteristicaTOBuilder().createCaracteristicaTOList(em.createNamedQuery("Caracteristica.findAll").getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar [findAll] "+e);
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void searchCaracteristica(PesquisaIdNomeTO pesquisaTO) throws DAOException {
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select tc.IDCARACTERISTICA, tc.NMCARACTERISTICA, ");
			queryStr.append("tv.IDVALORCARACTERISTICA, tv.VALOR ");
			queryStr.append("from (select co.*, rownum seq ");
			queryStr.append("from (select c.IDCARACTERISTICA, c.NMCARACTERISTICA ");
			queryStr.append("from CATALOGOPRS_OW.CARACTERISTICA c ");
			queryStr.append("order by c.NMCARACTERISTICA asc) co) tc, ");
			queryStr.append("(select v.IDCARACTERISTICA, v.IDVALORCARACTERISTICA, v.VALOR ");
			queryStr.append("from CATALOGOPRS_OW.VALORCARACTERISTICA v) tv ");
			queryStr.append("where tc.IDCARACTERISTICA = tv.IDCARACTERISTICA(+) ");
			queryStr.append("and tc.seq > ? and tc.seq <= ? ");
			queryStr.append("order by tc.NMCARACTERISTICA, tv.VALOR asc ");
			
			Query select = em.createNativeQuery(queryStr.toString());
			PaginacaoDataTableTO paginacaoDataTableTO = pesquisaTO.getPaginacaoDataTableTO();
			select.setParameter(1, paginacaoDataTableTO.getRegistroAtual());
			select.setParameter(2, paginacaoDataTableTO.getRegistroAtual() + paginacaoDataTableTO.getRegistrosPorPagina());
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				IdNomeTOBuilder idNomeTOBuilder = new IdNomeTOBuilder();
				List<IdNomeTO> resultado = idNomeTOBuilder.createIdNomeTOList(resultList);
				pesquisaTO.setResultado(resultado);
				
				StringBuilder countQueryStr = new StringBuilder();
				countQueryStr.append("select count(c.idCaracteristica) ");
				countQueryStr.append("from Caracteristica c ");
				
				Query countSelect = em.createQuery(countQueryStr.toString());
				Long count = (Long) countSelect.getResultList().get(0);
				paginacaoDataTableTO.setTotalRegistros(count.intValue());
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar [searchCaracteristica] "+e);
		}
	}
	
}