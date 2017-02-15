package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.EpsDAO;
import com.indracompany.catalogo.datalayer.Eps;
import com.indracompany.catalogo.datalayer.GrupoEps;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EpsTO;

@Stateless
public class EpsDAOImpl implements EpsDAO{
	
	private static Logger logger = Logger.getLogger(AcaoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
    public List<EpsTO> findByName(EpsTO to) throws DAOException {
		logger.debug(to);
		List<EpsTO> result = new ArrayList<EpsTO>();
		try {
			StringBuilder queryStr = new StringBuilder(
				"select e from Eps e "
			);
			if(to != null){
				if(to.getNmEps() != null){
					queryStr.append("where upper(e.nmEps) = :nmEps ");
				}
			}
			Query query = em.createQuery(queryStr.toString());
			
			if(to != null){
				if(to.getNmEps() != null){
					query.setParameter("nmEps", to.getNmEps().toUpperCase());
				}
			}
			result = new EpsTOBuilder().createTOList((List<Eps>) query.getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
	
	public void mergeEpsGrupoEps(EpsTO to) throws DAOException {
		try {
			Eps eps = new EpsTOBuilder().createEntity(to);
			List<GrupoEps> grupoEpsList = findByName(new GrupoEps(to.getNmEps()));
			if(grupoEpsList.isEmpty()){
				eps.setGrupoEps(new GrupoEps(to.getNmEps()));
			} else {
				eps.getGrupoEps().setNmGrupoEps(to.getNmEps());
			}
			em.merge(eps);
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	public void remove(EpsTO to) throws DAOException {
		try {
			Eps eps = em.find(Eps.class, to.getIdEps());
			List<GrupoEps> grupoEpsList = findByName(new GrupoEps(eps.getNmEps()));
			em.remove(eps);
			for(GrupoEps grupoEps : grupoEpsList){
				List<Eps> epsList = grupoEps.getEpsList();
				if(epsList.size() <= 1){
					em.remove(grupoEps);
				}
			}
		}catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
    public List<EpsTO> search(EpsTO to) throws DAOException {
		logger.debug(to);
		List<EpsTO> result = new ArrayList<EpsTO>();
		try {
			StringBuilder queryStr = new StringBuilder(
				"select e from Eps e "
			);
			if(to != null){
				if(to.getNmEps() != null){
					queryStr.append("where e.nmEps like :nmEps ");
				}
			}
			queryStr.append("order by e.nmEps ");
			Query query = em.createQuery(queryStr.toString());
			
			if(to != null){
				if(to.getNmEps() != null){
					query.setParameter("nmEps", "%"+to.getNmEps().toUpperCase()+"%");
				}
			}
			result = new EpsTOBuilder().createTOList((List<Eps>) query.getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
    private List<GrupoEps> findByName(GrupoEps grupoEps){
		StringBuilder queryStr = new StringBuilder(
			" select ge from GrupoEps ge " +
			" where ge.nmGrupoEps = :grupoEps "
		);
		
		Query query = em.createQuery(queryStr.toString());
		query.setParameter("grupoEps", grupoEps.getNmGrupoEps());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
    public List<EpsTO> listEpsTO() throws DAOException {
        try {
            return new EpsTOBuilder().createTOList(em.createNamedQuery("Eps.findAll").getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao listar EpsTO", e);
        }        
    }
	
}