package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.CidadeDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CidadeTO;

@Stateless
public class CidadeDAOImpl implements CidadeDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
    public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws DAOException {
        List<CidadeTO> cidadeTOList;
        try {
            cidadeTOList = new CidadeTOBuilder().buildBasicTOList(em.createNamedQuery("Cidade.findByIdAreaRegistro").setParameter("idAreaRegistro", idAreaRegistro).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter areas registro para idAreaRegistro %s", idAreaRegistro);
            throw new DAOException(msg, e);
        }
        return cidadeTOList;        
    }

}