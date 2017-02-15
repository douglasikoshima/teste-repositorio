package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TipoSolicitacaoComercialDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;


@Stateless
public class TipoSolicitacaoComercialDAOImpl implements TipoSolicitacaoComercialDAO{
	private static Logger logger = Logger.getLogger(TipoSolicitacaoComercialDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<TipoSolicitacaoComercialTO> findAll() throws DAOException {
		logger.debug("Inicio: TipoSolicitacaoComercialDAO.findAll");
		List<TipoSolicitacaoComercialTO> result = new ArrayList<TipoSolicitacaoComercialTO>();
		result = new TipoSolicitacaoComercialTOBuilder().buildTOList(em.createNamedQuery("TipoSolicitacaoComercial.findAll").getResultList());
		logger.debug("Fim: TipoSolicitacaoComercialDAO.findAll");
		return result;
	}
}
