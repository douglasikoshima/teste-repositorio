package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CanalVendaDAO;
import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.Eps;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;

@Stateless
public class CanalVendaDAOImpl implements CanalVendaDAO{
	
	private static Logger logger = Logger.getLogger(CanalVendaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> findAll() throws DAOException {
		List<CanalVendaTO> result = null;
		
		try {
			Query query = em.createNamedQuery("CanalVenda.findAll");
			result = new CanalVendaTOBuilder().createTOList(query.getResultList());
		} catch(Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> pesquisar(CanalVendaTO canalVendaTO) throws DAOException{
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		StringBuffer queryStr = new StringBuffer();
		List<CanalVendaTO> list = null;
		
		try {
			queryStr.append("select c from CanalVenda c where 1=1 ");
            Map<String, Object> params = new HashMap<String, Object>();
			if(canalVendaTO.getInDisponivel() != null ){
				queryStr.append(" and c.inDisponivel = :inDisponivel ");
				params.put("inDisponivel", canalVendaTO.getInDisponivel());
			}
			if(canalVendaTO.getInCriacaoCatalogo() != null){
				queryStr.append(" and (c.inCriacaoCatalogo = :inCriacaoCatalogo or (c.inCriacaoCatalogo is null and :inCriacaoCatalogo = 'N'))");
				params.put("inCriacaoCatalogo", canalVendaTO.getInCriacaoCatalogo());
			}
			if(canalVendaTO.getCdCanalVenda() != null){
				queryStr.append(" and upper(c.cdCanalVenda) = upper(:cdCanalVenda) ");
				params.put("cdCanalVenda", canalVendaTO.getCdCanalVenda());
			}
			if(canalVendaTO.getNmCanalVenda() != null){
				queryStr.append(" and upper(c.nmCanalVenda) like upper(:nmCanalVenda)");
				params.put("nmCanalVenda", "%" + canalVendaTO.getNmCanalVenda() + "%");
			}
            if (canalVendaTO.getEpsTO() != null && canalVendaTO.getEpsTO().getIdEps() != null) {
                queryStr.append(" and c.eps.idEps = :idEps");
                params.put("idEps", canalVendaTO.getEpsTO().getIdEps());
            }
			queryStr.append(" order by c.nmCanalVenda");

			Query query = this.getQuery(queryStr.toString(), params);  

			CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
			list = builder.createTOList(query.getResultList());

		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return list;
	}
	
	public CanalVendaTO createUpdate(CanalVendaTO canalVendaTO) throws DAOException{
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
		CanalVenda ent = null;
		
		try {
			if(canalVendaTO.getIdCanalVenda() == null){
				canalVendaTO.setCdCanalVenda(em.createNativeQuery("SELECT CATALOGOPRS_OW.CANALVENDACODIGO_SQ.NEXTVAL FROM DUAL").getResultList().get(0).toString());
			}
			CanalVenda canalVenda = builder.createEntity(canalVendaTO);
            canalVenda.setInAlteracaoCatalogo("S");
            ent = em.merge(canalVenda);
		} catch(Exception e) {
			throw new DAOException(e);
		}
		return builder.createTO(ent);
	}
	
	public void remover(CanalVendaTO canalVendaTO) throws DAOException{
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		try {
			em.remove(em.find(CanalVenda.class,canalVendaTO.getIdCanalVenda()));
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
    public CanalVendaTO pesquisarPorNmCanalVenda(CanalVendaTO canalVendaTO) throws DAOException{
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		CanalVendaTO result = null;
		StringBuffer queryStr = new StringBuffer("select c from CanalVenda c where upper(c.nmCanalVenda) = upper(:nmCanalVenda)");
		CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
		
		try {
			if(canalVendaTO != null){
				Query query = em.createQuery(queryStr.toString());
				query.setParameter("nmCanalVenda",canalVendaTO.getNmCanalVenda());
				List<CanalVendaTO> list = (builder.createTOList(query.getResultList()));
				if(!list.isEmpty())
					result = (CanalVendaTO) list.get(0);
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}

	public CanalVendaTO pesquisarPorIdCanalVenda(CanalVendaTO canalVendaTO) throws DAOException {
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		CanalVendaTO result = null;
		CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
		
		try {
			if(canalVendaTO != null){
				result = builder.createTO(em.find(CanalVenda.class,canalVendaTO.getIdCanalVenda()));
			}
			
		} catch(Exception e){
			throw new DAOException(e);
		}
	
		return result;
	}

	public Boolean possuiAssociacao(CanalVendaTO canalVendaTO) throws DAOException {
		logger.debug("canalVendaTO:" + canalVendaTO);
		
		CanalVenda canalVenda = null;
		StringBuffer queryStr = new StringBuffer();
		
		try {
			queryStr.append("select c from CanalVenda c where c.idCanalVenda = :idCanalVenda");
			
			Query query = em.createQuery(queryStr.toString());
			
			query.setParameter("idCanalVenda", canalVendaTO.getIdCanalVenda());
			
			canalVenda = (CanalVenda) query.getSingleResult();
			
			if(canalVenda.getCanalVendaSolicitacaoComercial().isEmpty() 
			   && canalVenda.getCondicaoPagamentoEncargoList().isEmpty()
			) 
				return Boolean.FALSE;
			else
				return Boolean.TRUE;
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> findAllInIdList(List<CanalVendaTO> canalVendaTOList, String inDisponibilidade) throws DAOException {
		logger.debug("canalVendaTOList:" + canalVendaTOList);
		
		List<CanalVendaTO> result = null;
		CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
		List<Long> idList = new ArrayList<Long>();
		StringBuffer queryStr = new StringBuffer(" select c from CanalVenda c where 1=1 ");
		
		try {
			if(inDisponibilidade != null && (inDisponibilidade.equalsIgnoreCase("S") || inDisponibilidade.equalsIgnoreCase("N"))){
				queryStr.append(" and c.inDisponivel = :inDisponibilidade ");
			}
			queryStr.append(" and c.idCanalVenda in (:idList) ");
			queryStr.append(" order by c.nmCanalVenda ");
			
			Query query = em.createQuery(queryStr.toString());

			if(inDisponibilidade != null && (inDisponibilidade.equalsIgnoreCase("S") || inDisponibilidade.equalsIgnoreCase("N"))){
				query.setParameter("inDisponibilidade", inDisponibilidade);
			}
			if(!canalVendaTOList.isEmpty()){
				idList = builder.getIdList(canalVendaTOList);
				query.setParameter("idList", idList);
			} else {
				idList.add(new Long(0));
				query.setParameter("idList", idList);
			}
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> findAllNotInIdList(List<Long> idList, String inDisponibilidade) throws DAOException {
		logger.debug("canalVendaTOList:" + idList + "inDisponibilidade:" + inDisponibilidade);
		
		List<CanalVendaTO> result = null;
		CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
		StringBuffer queryStr = new StringBuffer(" select c from CanalVenda c where 1=1 ");
		
		try {
			if(inDisponibilidade != null && (inDisponibilidade.equalsIgnoreCase("S") || inDisponibilidade.equalsIgnoreCase("N"))){
				queryStr.append(" and c.inDisponivel = :inDisponibilidade ");
			}
			if(!idList.isEmpty()){
				queryStr.append(" and c.idCanalVenda not in (:idList) ");
			}
			queryStr.append(" order by c.nmCanalVenda ");
			
			Query query = em.createQuery(queryStr.toString());

			if(inDisponibilidade != null && (inDisponibilidade.equalsIgnoreCase("S") || inDisponibilidade.equalsIgnoreCase("N"))){
				query.setParameter("inDisponibilidade", inDisponibilidade);
			}
			if(!idList.isEmpty()){
				query.setParameter("idList", idList);
			}
			
			result = builder.createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> findByIdEncargo(Long idEncargo) throws DAOException {
		logger.debug("findByIdEncargo:" + idEncargo);
		
		StringBuffer queryStr = new StringBuffer();
		List<CanalVendaTO> list = null;
		
		try {
			queryStr.append(
					"select distinct c from CanalVenda c " +
					"join c.condicaoPagamentoEncargoList cpe " +
					"join cpe.encargo e " +
					"where 1=1 "
			);
			
			if(idEncargo != null ){
				queryStr.append("and e.idEncargo = :idEncargo ");
			}

			queryStr.append("order by c.nmCanalVenda");

			Query query = em.createQuery(queryStr.toString());  
			
			if(idEncargo != null ){
				query.setParameter("idEncargo", idEncargo);
			}

			CanalVendaTOBuilder builder = new CanalVendaTOBuilder();
			list = builder.createTOList(query.getResultList());

		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return list;	
	}

	@SuppressWarnings("unchecked")
    public void associarEps(List<Long> canalVendaIdList, Integer idEps) throws DAOException{
		canalVendaIdList.add(new Long(0));
		
		StringBuilder queryStr = new StringBuilder(
			"select cv from CanalVenda cv " +
			"where cv.idCanalVenda in (:canalVendaIdList) "
		);

		try {
			List<CanalVenda> canalVendaList = em.createQuery(queryStr.toString()).setParameter("canalVendaIdList", canalVendaIdList).getResultList(); 
			for(CanalVenda canalVenda : canalVendaList){
				canalVenda.setEps(em.find(Eps.class, idEps));
				em.merge(canalVenda);
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
    public void desAssociarEps(List<Long> canalVendaIdList) throws DAOException{
		canalVendaIdList.add(new Long(0));
		
		StringBuilder queryStr = new StringBuilder(
			"select cv from CanalVenda cv " +
			"where cv.idCanalVenda in (:canalVendaIdList) "
		);

		try {
			List<CanalVenda> canalVendaList = em.createQuery(queryStr.toString()).setParameter("canalVendaIdList", canalVendaIdList).getResultList(); 
			for(CanalVenda canalVenda : canalVendaList){
				canalVenda.setEps(null);
				em.merge(canalVenda);
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public List<CanalVendaTO> findByIdEps(Integer idEps) throws DAOException {
		List<CanalVendaTO> result;

		try {
			result = new CanalVendaTOBuilder().createTOList(em.find(Eps.class, idEps).getCanalVendaList());
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
    
    private Query getQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) {
            query.setParameter(param, params.get(param));
        }
        return query;
    }

	public CanalVenda findById(Long idCanalVenda) throws DAOException {
		CanalVenda canalVenda = null;
		try {
			canalVenda = em.find(CanalVenda.class, idCanalVenda);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return canalVenda;
	}
	
	@SuppressWarnings("unchecked")
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws DAOException {
        try {
            StringBuilder hql = new StringBuilder("select c from CanalVenda c where c.idCanalVenda is not null ");
            Map<String, Object> params = new HashMap<String, Object>();
            if (canalVendaTO.getEpsTO() != null) {
                if (canalVendaTO.getEpsTO().getIdEps().equals(0)) {
                    hql.append("and c.eps.idEps is null ");
                } else {
                    hql.append("and c.eps.idEps = :idEps ");
                    params.put("idEps", canalVendaTO.getEpsTO().getIdEps());
                }
            }
            if (StringUtils.isNotBlank(canalVendaTO.getCdCanalVenda())) {
                hql.append("and c.cdCanalVenda = :cdCanalVenda");
                params.put("cdCanalVenda", canalVendaTO.getCdCanalVenda());
            }
            return new CanalVendaTOBuilder().createTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar canal venda", e);
        }
    }
	
}