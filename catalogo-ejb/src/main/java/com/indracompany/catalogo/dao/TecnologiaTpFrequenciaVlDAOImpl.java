package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.indracompany.catalogo.dao.interfaces.TecnologiaTpFrequenciaVlDAO;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequenciaVl;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaVlTO;

@Stateless
public class TecnologiaTpFrequenciaVlDAOImpl implements
		TecnologiaTpFrequenciaVlDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public TecnologiaTpFrequenciaVlTO obterTecnologiaTpFrequenciaVlTO(
			Integer idTecnologia, Integer idTipoFrequencia,
			Integer idVlFrequencia) throws DAOException {
		
		TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO = null;
		try {
			TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = obterTecnologiaTpFrequenciaVl(idTecnologia, idTipoFrequencia, idVlFrequencia);
			TecnologiaTpFrequenciaVlTOBuilder tecnologiaTpFrequenciaVlTOBuilder = new TecnologiaTpFrequenciaVlTOBuilder();
			tecnologiaTpFrequenciaVlTO = tecnologiaTpFrequenciaVlTOBuilder.createTecnologiaTpFrequenciaVlTO(tecnologiaTpFrequenciaVl);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterTecnologiaTpFrequenciaVlTO]", e);
		}
		return tecnologiaTpFrequenciaVlTO;
	}
	
	public TecnologiaTpFrequenciaVl obterTecnologiaTpFrequenciaVl(
			Integer idTecnologia, Integer idTipoFrequencia,
			Integer idVlFrequencia) throws DAOException {
		
		TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select ttfv from TecnologiaTpFrequenciaVl ttfv ");
			queryStr.append("join ttfv.tecnologiaTpFrequencia ttf ");
			queryStr.append("join ttf.tecnologia t ");
			queryStr.append("join ttf.tipoFrequencia tf ");
			queryStr.append("join ttfv.vlFrequencia v ");
			queryStr.append("where t.idTecnologia = ? ");
			queryStr.append("and tf.idTipoFrequencia = ? ");
			queryStr.append("and v.idVlFrequencia = ? ");
			
			Query select = em.createQuery(queryStr.toString());
			select.setParameter(1, idTecnologia);
			select.setParameter(2, idTipoFrequencia);
			select.setParameter(3, idVlFrequencia);
			
			tecnologiaTpFrequenciaVl = (TecnologiaTpFrequenciaVl) select.getSingleResult();
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterTecnologiaTpFrequenciaVl]", e);
		}
		return tecnologiaTpFrequenciaVl;
	}
	
}