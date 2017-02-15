package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.TecnologiaDAO;
import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.IdNomeTO;
import com.indracompany.catalogo.to.PaginacaoDataTableTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Tecnologia.
 */
@Stateless
public class TecnologiaDAOImpl implements TecnologiaDAO {
	
	private static Logger logger = Logger.getLogger(TecnologiaDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.TecnologiaDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TecnologiaTO> findAll() throws DAOException {
		logger.debug("[findAll]");
		
		TecnologiaTOBuilder tecnologiaTOBuilder = new TecnologiaTOBuilder();
		List<TecnologiaTO> tecnologiaTOList = null;
		
		try {
			Query query = em.createNamedQuery("Tecnologia.findAll");
			tecnologiaTOList = tecnologiaTOBuilder.createTecnologiaTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
	    return tecnologiaTOList;
	}
	
	@SuppressWarnings("unchecked")
	public List<TecnologiaTO> findAllTecnologiaServico(String inFixa) throws DAOException {
		logger.debug("[findAllTecnologiaServico]");
		
		try {
			StringBuilder queryStr = new StringBuilder(
				"select distinct t " +
				"from Tecnologia t " +
				"join t.servicoList s "
			);
			if(inFixa != null){
				queryStr.append("where s.inFixa = :inFixa ");
			}
			Query query = em.createQuery(queryStr.toString());
			if(inFixa != null){
				query.setParameter("inFixa", inFixa);
			}
			return new TecnologiaTOBuilder().createTecnologiaTOList(query.getResultList());
		} catch (Exception e){
			throw new DAOException("Erro ao executar o DAO [findAllTecnologiaServico]",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void searchTecnologia(PesquisaIdNomeTO pesquisaTO) throws DAOException {
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select tt.idtecnologia, tt.nmtecnologia, ");
			queryStr.append("ttf.idtipofrequencia, ttf.nmtipofrequencia, ");
			queryStr.append("tvf.idvlfrequencia, tvf.vlfrequencia ");
			queryStr.append("from (select tto.*, rownum seq ");
			queryStr.append("from (select t.idtecnologia, t.nmtecnologia ");
			queryStr.append("from catalogoprs_ow.tecnologia t ");
			queryStr.append("order by t.nmtecnologia asc) tto) tt, ");
			queryStr.append("catalogoprs_ow.tecnologiatpfrequencia tttf, ");
			queryStr.append("catalogoprs_ow.tipofrequencia ttf, ");
			queryStr.append("catalogoprs_ow.tecnologiatpfrequenciavl tttfv, ");
			queryStr.append("catalogoprs_ow.vlfrequencia tvf ");
			queryStr.append("where tt.idtecnologia = tttf.idtecnologia(+) ");
			queryStr.append("and tttf.idtipofrequencia = ttf.idtipofrequencia(+) ");
			queryStr.append("and tttf.idtecnologiatpfrequencia = tttfv.idtecnologiatpfrequencia(+) ");
			queryStr.append("and tttfv.idvlfrequencia = tvf.idvlfrequencia(+) ");
			queryStr.append("and tt.seq > ? and tt.seq <= ? ");
			queryStr.append("order by tt.nmtecnologia, ttf.nmtipofrequencia, tvf.vlfrequencia");
			
			Query select = em.createNativeQuery(queryStr.toString());
			PaginacaoDataTableTO paginacaoDataTableTO = pesquisaTO.getPaginacaoDataTableTO();
			select.setParameter(1, paginacaoDataTableTO.getRegistroAtual());
			select.setParameter(2, paginacaoDataTableTO.getRegistroAtual() + paginacaoDataTableTO.getRegistrosPorPagina());
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				IdNomeTOBuilder idNomeTOBuilder = new IdNomeTOBuilder();
				List<IdNomeTO> resultado = idNomeTOBuilder.createIdNomeTOList(resultList);
				pesquisaTO.setResultado(resultado);
				
				StringBuilder countQueryStr = new StringBuilder();
				countQueryStr.append("select count(t.idTecnologia) ");
				countQueryStr.append("from Tecnologia t ");
				
				Query countSelect = em.createQuery(countQueryStr.toString());
				Long count = (Long) countSelect.getResultList().get(0);
				paginacaoDataTableTO.setTotalRegistros(count.intValue());
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar [searchTecnologia] "+e);
		}
	}
	
	public Tecnologia findById(Integer idTecnologia) throws DAOException {
		Tecnologia tecnologia = new Tecnologia();
		try {
			tecnologia = em.find(Tecnologia.class, idTecnologia);			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return tecnologia;
	}
	
}