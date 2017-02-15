package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.MultimidiaDAO;
import com.indracompany.catalogo.datalayer.Multimidia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;

@Stateless
public class MultimidiaDAOImpl implements MultimidiaDAO {
	
	private static Logger logger = Logger.getLogger(MultimidiaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public List<MultimidiaTO> find(ParametrizacaoProdutosTO pp) throws DAOException {
		return obterMultimidiaTOList(pp.getIdModelo(), null, new MultimidiaTOBuilder());
	}
	
	@SuppressWarnings("unchecked")
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto, Integer[] idsTiposMultimidia, MultimidiaTOBuilder multimidiaTOBuilder) throws DAOException {
		List<MultimidiaTO> multimidiaTOList = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			
			if (idsTiposMultimidia == null || idsTiposMultimidia.length == 0) {
				queryStr.append("select m from Multimidia m ");
				queryStr.append("join m.grupoProduto gp ");
				queryStr.append("where gp.idGrupoProduto = ?");
			} else {
				queryStr.append("select m from Multimidia m ");
				queryStr.append("join m.grupoProduto gp ");
				queryStr.append("join m.tipoMultimidia tm ");
				queryStr.append("where gp.idGrupoProduto = ? ");
				queryStr.append("where tm.idTipoMultimidia in (?)");
			}
			
			Query select = em.createQuery(queryStr.toString());
			select.setParameter(1, idGrupoProduto);
			
			if (idsTiposMultimidia != null && idsTiposMultimidia.length > 0) {
				select.setParameter(2, idsTiposMultimidia);
			}
			
			List<Multimidia> resultList = (List<Multimidia>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				multimidiaTOList = multimidiaTOBuilder.createMultimidiaTOList(resultList);
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterMultimidiaTOList]", e);
		}
		return multimidiaTOList;
	}
	
	public void save(MultimidiaTO multimidiaTO) throws DAOException {
		logger.debug("multimidiaTO: " + multimidiaTO);
		try {
			MultimidiaTOBuilder multimidiaTOBuilder = new MultimidiaTOBuilder();
			Multimidia multimidia = multimidiaTOBuilder.createMultimidia(multimidiaTO);
			save(multimidia);
			multimidiaTO.setIdMultimidia(multimidia.getIdMultimidia());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(Multimidia multimidia) throws DAOException {
		try {
			em.persist(multimidia);
			em.flush();
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void remove(Multimidia multimidia) throws DAOException {
		try {
			em.remove(multimidia);
			em.flush();
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [remove]", e);
		}
	}
	
}