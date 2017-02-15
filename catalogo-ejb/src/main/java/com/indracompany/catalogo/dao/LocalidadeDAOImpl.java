package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.LocalidadeDAO;
import com.indracompany.catalogo.datalayer.Localidade;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.LocalidadeTO;

@Stateless
public class LocalidadeDAOImpl implements LocalidadeDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Localidade findById(Long IdLocalidade) throws DAOException {
		Localidade localidade = null;
		try {
			localidade = em.find(Localidade.class, IdLocalidade);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return localidade;
	}
	
	@SuppressWarnings("unchecked")
    public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws DAOException {
        List<LocalidadeTO> localidadeTOList;
        try {
            localidadeTOList = new LocalidadeTOBuilder().buildTOList(em.createNamedQuery("Localidade.findByIdCidade").setParameter("idCidade", idCidade).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter localidades para cidade %s ", idCidade);
            throw new DAOException(msg, e);
        }        
        return localidadeTOList;
    }

}