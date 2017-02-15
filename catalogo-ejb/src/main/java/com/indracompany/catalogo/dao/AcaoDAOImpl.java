package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AcaoDAO;
import com.indracompany.catalogo.datalayer.Acao;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Ações.
 */
@Stateless
public class AcaoDAOImpl implements AcaoDAO {
	
	private static Logger logger = Logger.getLogger(AcaoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#searchAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	@SuppressWarnings("unchecked")
	public List<AcaoTO> searchAcao(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		List<AcaoTO> list = null;
		
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select a from Acao a");
			queryStr.append(" where a.dtCriacao is not null ");
			
			if (acaoTO.getNmAcao() != null && !acaoTO.getNmAcao().equals("")) {
				queryStr.append("and upper(a.nmAcao) like upper(:nmAcao) ");
			}
			
			if (acaoTO.getSgAcao() != null && !acaoTO.getSgAcao().equals("")) {
				queryStr.append("and upper(a.sgAcao) like upper(:sgAcao) ");
			}

			if (acaoTO.getInDisponivel() != null && !acaoTO.getInDisponivel().equals("")) {
				queryStr.append("and a.inDisponivel = :inDisponivel ");
			}
			
			if (acaoTO.getDtInicial() != null && acaoTO.getDtFinal() != null) {
				queryStr.append("and a.dtInicial >= :dtInicial and a.dtFinal <= :dtFinal ");
			}
			
			if (acaoTO.getIsVigente() != null) {
				if (!acaoTO.getIsVigente().equals("S")) {
					queryStr.append("and a.dtFinal <= sysdate ");
				} else {
					queryStr.append("and a.dtFinal > sysdate ");
				}
			}
			
			if ( acaoTO.getCanalAtendimentoTO() != null ){
				queryStr.append("and a.canalAtendimento.idCanalAtendimento = :idCanalAtendimento");
			} else {
				queryStr.append("and a.canalAtendimento IS NULL");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (acaoTO.getInDisponivel() != null && !acaoTO.getInDisponivel().equals("")) {
				query.setParameter("inDisponivel", acaoTO.getInDisponivel());
			}
			
			if (acaoTO.getInDisponivel() != null && !acaoTO.getInDisponivel().equals("")) {
				query.setParameter("inDisponivel", acaoTO.getInDisponivel());
			}
			
			if (acaoTO.getNmAcao() != null && !acaoTO.getNmAcao().equals("")) {
				query.setParameter("nmAcao", "%" + acaoTO.getNmAcao() + "%");
			}
			
			if (acaoTO.getSgAcao() != null && !acaoTO.getSgAcao().equals("")) {
				query.setParameter("sgAcao", "%" + acaoTO.getSgAcao() + "%");
			}
			
			if (acaoTO.getDtInicial() != null && acaoTO.getDtFinal() != null) {
				query.setParameter("dtInicial", acaoTO.getDtInicial());
				query.setParameter("dtFinal", acaoTO.getDtFinal());
			}
			
			if ( acaoTO.getCanalAtendimentoTO() != null ){
				query.setParameter("idCanalAtendimento", acaoTO.getCanalAtendimentoTO().getIdCanalAtendimento());
			}
			
			AcaoTOBuilder acaoTOBuilder = new AcaoTOBuilder();
			list = acaoTOBuilder.createAcaoTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#createUpdateAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	public AcaoTO createUpdateAcao(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		AcaoTOBuilder acaoTOBuilder = new AcaoTOBuilder();
		AcaoTO acaoResultTO = null;
		
		try {
			acaoResultTO = acaoTOBuilder.createAcaoTO(em.merge(acaoTOBuilder.createAcao(acaoTO)));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return acaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#findById(com.indracompany.catalogo.to.AcaoTO)
	 */
	public AcaoTO findById(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		AcaoTO acaoResultTO = null;
		AcaoTOBuilder acaoTOBuilder = new AcaoTOBuilder();
		
		try {
			acaoResultTO = acaoTOBuilder.createAcaoTO(em.find(Acao.class, acaoTO.getIdAcao()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return acaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#removeAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	public void removeAcao(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		try {
			em.remove(em.find(Acao.class, acaoTO.getIdAcao()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#findBySigla(com.indracompany.catalogo.to.AcaoTO)
	 */
	public AcaoTO findBySigla(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		AcaoTO acaoResultTO = null;
		AcaoTOBuilder acaoTOBuilder = new AcaoTOBuilder();
		
		try {
			
			Query query = em.createQuery("select a from Acao a where upper(a.sgAcao) = upper(:sgAcao)");
			query.setParameter("sgAcao", acaoTO.getSgAcao());
			acaoResultTO = acaoTOBuilder.createAcaoTO((Acao) query.getSingleResult());
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return acaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.AcaoDAO#existAssociation(com.indracompany.catalogo.to.AcaoTO)
	 */
	public Boolean existAssociation(AcaoTO acaoTO) throws DAOException {
		logger.debug("acaoTO: " + acaoTO);
		
		try {
			Query query = em.createQuery("select a from Acao a, MatrizOfertaItemPreco mo where a.idAcao = mo.acao.idAcao and a.idAcao = :idAcao");
			query.setParameter("idAcao", acaoTO.getIdAcao());
			query.getSingleResult();
		} catch (NoResultException e) { 
			return false;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return true;
	}


	@SuppressWarnings("unchecked")
	public List<AcaoTO> findAll() throws DAOException {
		try {
			return new AcaoTOBuilder().createAcaoTOList(em.createNamedQuery("Acao.findAll").getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
}
