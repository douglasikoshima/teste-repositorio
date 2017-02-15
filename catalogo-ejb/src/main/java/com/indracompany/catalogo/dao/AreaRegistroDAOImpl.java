package com.indracompany.catalogo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.dao.interfaces.AreaRegistroDAO;
import com.indracompany.catalogo.datalayer.AreaRegistro;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaRegistroTO;

@Stateless
public class AreaRegistroDAOImpl implements AreaRegistroDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public AreaRegistro findById(Integer IdAreaRegistro) throws DAOException {
		AreaRegistro areaRegistro = null;
		try {
			areaRegistro = em.find(AreaRegistro.class, IdAreaRegistro);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return areaRegistro;
	}
	
	@SuppressWarnings("unchecked")
    public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws DAOException {
        List<AreaRegistroTO> areaRegistroTOList;
        try {
            areaRegistroTOList = new AreaRegistroTOBuilder().createTOList(em.createNamedQuery("AreaRegistro.findByIdUf").setParameter("idUf", idUf).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter areas registro para uf %s", idUf);
            throw new DAOException(msg, e);
        }
        return areaRegistroTOList;
    }
	
	@SuppressWarnings("unchecked")
    public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws DAOException {
        try {
            StringBuilder hql = new StringBuilder("select a from AreaRegistro a where a.idarearegistro is not null ");
            Map<String, Object> params = new HashMap<String, Object>();
            if (areaRegistroTO.getUfTO() != null) {
                hql.append("and a.uf.idUf = :idUf ");
                params.put("idUf", areaRegistroTO.getUfTO().getIdUf());
            }
            if (!StringUtils.isBlank(areaRegistroTO.getCodigoArea())) {
                hql.append("and a.codigoArea = :codigoArea");
                params.put("codigoArea", areaRegistroTO.getCodigoArea());
            }
            Query query = em.createQuery(hql.toString());
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
            return new AreaRegistroTOBuilder().createTOList(query.getResultList());
        } catch (Exception e) {
            String msg = "Erro ao buscar canal venda";
            throw new DAOException(msg, e);
        }        
    }

}