package com.indracompany.catalogo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.dao.interfaces.FamiliaDAO;
import com.indracompany.catalogo.datalayer.Categoria;
import com.indracompany.catalogo.datalayer.Familia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FamiliaTO;

@Stateless
public class FamiliaDAOImpl implements FamiliaDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<FamiliaTO> search(FamiliaTO familiaTO) throws DAOException {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("select f from Familia f where f.idFamilia is not null ");
        if (!StringUtils.isBlank(familiaTO.getCdFamilia())) {
            params.put("cdFamilia", familiaTO.getCdFamilia());
            hql.append("and f.cdFamilia like :cdFamilia ");
        }
        
        if (!StringUtils.isBlank(familiaTO.getNmFamilia())) {
            params.put("nmFamilia", String.format("%s%s%s", "%", familiaTO.getNmFamilia(), "%"));
            hql.append("and lower(f.nmFamilia) like lower(:nmFamilia) ");
        }

        if (familiaTO.getInCriacaoCatalogo() != null) {
            params.put("inCriacaoCatalogo", familiaTO.getInCriacaoCatalogo() ? "S" : "N");
            hql.append("and f.inCriacaoCatalogo = :inCriacaoCatalogo ");
        }

        if (familiaTO.getInDisponivel() != null) {
            params.put("inDisponivel", familiaTO.getInDisponivel() ? "S" : "N");
            hql.append("and f.inDisponivel = :inDisponivel ");
        }

        try {
            return new FamiliaTOBuilder().criarTOList((this.getQuery(hql.toString(), params).getResultList()));
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [search]", e);
        }
    }
    
    private Query getQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) {
            query.setParameter(param, params.get(param));
        }
        return query;
    }

    public FamiliaTO findById(Integer id) throws DAOException {
        try {
            return new FamiliaTOBuilder().criarTO(em.find(Familia.class, id));
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findById]", e);
        }
    }

    public FamiliaTO insertUpdate(FamiliaTO familiaTO) throws DAOException {
        try {
            Familia familia;
            if (familiaTO.getIdFamilia() != null) {
                familia = em.find(Familia.class, familiaTO.getIdFamilia());
            } else {
                familia = new Familia();
                familia.setInCriacaoCatalogo("S");
                familia.setInDisponivel("S");
                familia.setCdFamilia(em.createNativeQuery("select CATALOGOPRS_OW.CDFAMILIA_SQ.NEXTVAL FROM DUAL").getResultList().get(0).toString());
            }
            familia.setDsFamilia(familiaTO.getDsFamilia());
            familia.setNmFamilia(familiaTO.getNmFamilia());
            familia.setInAlteracaoCatalogo("S");
            return new FamiliaTOBuilder().criarTO(em.merge(familia));
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [insertUpdate]", e);
        }
    }
    
    public void remove(Integer id) throws DAOException {
        try {
            em.remove(em.find(Familia.class, id));
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [remove]", e);
        }
    }
    
    public void changeStatus(Integer id) throws DAOException {
        try {
            Familia familia = em.find(Familia.class, id);
            boolean newStatus = !familia.getInDisponivel().equalsIgnoreCase("S");
            familia.setInDisponivel(newStatus ? "S" : "N");
            if (!newStatus && familia.getCategoriaList() != null) {
                for(Categoria categoria : familia.getCategoriaList()) {
                    categoria.setInDisponivel(newStatus ? "S" : "N");
                }
            }
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [remove]", e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public FamiliaTO searchByName(String nmFamilia) throws DAOException {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("select f from Familia f where f.idFamilia is not null ");
        if (!StringUtils.isBlank(nmFamilia)) {
            params.put("nmFamilia", nmFamilia);
            hql.append("and lower(f.nmFamilia) like lower(:nmFamilia) ");
        }
        try {
        	List<FamiliaTO> toList = new FamiliaTOBuilder().criarTOList((this.getQuery(hql.toString(), params).getResultList()));
        	if (toList.size() > 0) {
        		return toList.iterator().next(); 
        	} else {
        		return null;
        	}
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [search]", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FamiliaTO> findAll() throws DAOException{
    	try {
    		return em.createNamedQuery("Familia.findAll").getResultList();
    	} catch (Exception e) {
    		throw new DAOException("Erro ao executar [findAll]", e);
    	}
    }
}
