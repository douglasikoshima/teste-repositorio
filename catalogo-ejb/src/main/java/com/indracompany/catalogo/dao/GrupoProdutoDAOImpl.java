package com.indracompany.catalogo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO;
import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.Produto;
import com.indracompany.catalogo.datalayer.ProdutoGrupoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.to.GamaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.PaginacaoDataTableTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.SistemaProdutoTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;
import com.indracompany.catalogo.to.VlFrequenciaTO;

@Stateless
public class GrupoProdutoDAOImpl implements GrupoProdutoDAO {

	private static Logger logger = Logger.getLogger(GrupoProdutoDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#findByCaracteristica(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoProdutoTO> findByCaracteristica(Integer idCaracteristica) throws DAOException {
		logger.info("idCaracteristica: " + idCaracteristica);

		List<GrupoProdutoTO> list = null;

		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select distinct ");
		queryStr.append(" gp ");
		queryStr.append("from ");
		queryStr.append(" GrupoProduto gp, GrupoProdutoCaracteristica gpc ");
		queryStr.append("where ");
		queryStr.append("gpc.grupoProduto.idGrupoProduto = gp.idGrupoProduto ");
		queryStr.append("and gpc.caracteristica.idCaracteristica = :idCaracteristica ");

		Query query = em.createQuery(queryStr.toString());
		query.setParameter("idCaracteristica", idCaracteristica);

		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
		list = grupoProdutoTOBuilder.createGrupoProdutoTOList(query.getResultList());

		return list;
	} 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#findByCaracteristica(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoProdutoTO> findByValorCaracteristica(Integer idValorCaracteristica) throws DAOException {
		logger.info("idCaracteristica: " + idValorCaracteristica);

		List<GrupoProdutoTO> list = null;

		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select distinct ");
		queryStr.append(" gp ");
		queryStr.append("from ");
		queryStr.append(" GrupoProduto gp, GrupoProdutoCaracteristica gpc ");
		queryStr.append("where ");
		queryStr.append("gpc.grupoProduto.idGrupoProduto = gp.idGrupoProduto ");
		queryStr.append("and gpc.valorCaracteristica.idValorCaracteristica = :idValorCaracteristica ");

		Query query = em.createQuery(queryStr.toString());
		query.setParameter("idValorCaracteristica", idValorCaracteristica);

		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
		list = grupoProdutoTOBuilder.createGrupoProdutoTOList(query.getResultList());

		return list;
	} 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoProdutoTO> findAll() throws DAOException {
		logger.debug("[findAll]");

		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
		List<GrupoProdutoTO> grupoProdutoTOList = null;

		try {
			Query query = em.createNamedQuery("GrupoProduto.findAll");
			grupoProdutoTOList = grupoProdutoTOBuilder.createGrupoProdutoTOList(query.getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
		return grupoProdutoTOList;
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#search(com.indracompany.catalogo.to.GrupoProdutoTO)
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoProdutoTO> search(GrupoProdutoTO grupoProdutoTO) throws DAOException {
		logger.debug("grupoProdutoTO: " + grupoProdutoTO);

		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
		List<GrupoProdutoTO> grupoProdutoTOList = null;

		try {

			StringBuffer queryStr = new StringBuffer();
			queryStr.append(
					"select gp " +
					"from " +
					"GrupoProduto gp "
			);
			if(grupoProdutoTO.getTecnologiaTOList() != null && !grupoProdutoTO.getTecnologiaTOList().isEmpty()){
				queryStr.append("join gp.tecnologiaList t ");
			}
			if(grupoProdutoTO.getCaracteristicaTOList() != null && !grupoProdutoTO.getCaracteristicaTOList().isEmpty()){
				queryStr.append("join gp.caracteristicaList c ");
			}
			queryStr.append(
					"where " +
					"gp.dtCriacao is not null "
			);
			if(grupoProdutoTO.getInDisponivel() != null) {
				queryStr.append("and gp.inDisponivel = :inDisponivel ");
			}
			if(grupoProdutoTO.getFabricanteTO() != null && grupoProdutoTO.getFabricanteTO().getIdFabricante() != null) {
				queryStr.append("and gp.fabricante.idFabricante = :idFabricante ");
			}

			if(grupoProdutoTO.getTipoProdutoTO() != null && grupoProdutoTO.getTipoProdutoTO().getIdTipoProduto() != null) {
				queryStr.append("and gp.tipoProduto.idTipoProduto = :idTipoProduto ");
			}

			if(grupoProdutoTO.getIdGrupoProduto() != null) {
				queryStr.append("and gp.idGrupoProduto = :idGrupoProduto ");
			}

			if(grupoProdutoTO.getNmGrupoProduto() != null){
				queryStr.append("and gp.nmGrupoProduto like :nmGrupoProduto ");
			}
			if(grupoProdutoTO.getTecnologiaTOList() != null && !grupoProdutoTO.getTecnologiaTOList().isEmpty()){
				queryStr.append("and t.idTecnologia in (:idsTecnologiaList) ");
			}

			if(grupoProdutoTO.getCaracteristicaTOList() != null && !grupoProdutoTO.getCaracteristicaTOList().isEmpty()){
				queryStr.append("and c.idCaracteristica in (:idsCaracteristicaList) ");
			}

			queryStr.append("order by gp.nmGrupoProduto ");

			Query query = em.createQuery(queryStr.toString());

			if (grupoProdutoTO.getInDisponivel() != null) {
				query.setParameter("inDisponivel", grupoProdutoTO.getInDisponivel());
			}

			if (grupoProdutoTO.getFabricanteTO() != null && grupoProdutoTO.getFabricanteTO().getIdFabricante() != null) {
				query.setParameter("idFabricante", grupoProdutoTO.getFabricanteTO().getIdFabricante());
			}

			if (grupoProdutoTO.getTipoProdutoTO() != null && grupoProdutoTO.getTipoProdutoTO().getIdTipoProduto() != null) {
				query.setParameter("idTipoProduto", grupoProdutoTO.getTipoProdutoTO().getIdTipoProduto());
			}

			if (grupoProdutoTO.getIdGrupoProduto() != null) {
				query.setParameter("idGrupoProduto", grupoProdutoTO.getIdGrupoProduto());
			}

			if(grupoProdutoTO.getNmGrupoProduto() != null){
				query.setParameter("nmGrupoProduto", "%" + grupoProdutoTO.getNmGrupoProduto() + "%");
			}

			if(grupoProdutoTO.getTecnologiaTOList() != null && !grupoProdutoTO.getTecnologiaTOList().isEmpty()){
				query.setParameter("idsTecnologiaList", new TecnologiaTOBuilder().getIdList(grupoProdutoTO.getTecnologiaTOList()));
			}

			if(grupoProdutoTO.getCaracteristicaTOList() != null && !grupoProdutoTO.getCaracteristicaTOList().isEmpty()){
				query.setParameter("idsCaracteristicaList", new CaracteristicaTOBuilder().getIdList(grupoProdutoTO.getCaracteristicaTOList()));
			}

			grupoProdutoTOList = grupoProdutoTOBuilder.createGrupoProdutoTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [search]", e);
		}
		return grupoProdutoTOList;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#search(com.indracompany.catalogo.to.PesquisaGrupoProdutoTO)
	 */
	@SuppressWarnings("unchecked")
	public void search(PesquisaGrupoProdutoTO pesquisaTO) throws DAOException {
		try {
			PesquisaGrupoProdutoTO.Parametros queryParams = pesquisaTO.getParametros();
			PaginacaoDataTableTO paginacaoDataTableTO = pesquisaTO.getPaginacaoDataTableTO();
			List<PesquisaGrupoProdutoTO.LinhaResultado> resultado = pesquisaTO.getResultado();
			
			Map<String, Object> queryParamsMap = new HashMap<String, Object>();
			queryParamsMap.put("idTipoProduto", queryParams.getIdTipoProduto());
			queryParamsMap.put("idFabricante", queryParams.getIdFabricante());
			queryParamsMap.put("idModelo", queryParams.getIdModelo());
			queryParamsMap.put("idTecnologia", queryParams.getIdTecnologia());
			queryParamsMap.put("idsCaracteristicas", queryParams.getIdsCaracteristicas());
			queryParamsMap.put("idsValoresCaracteristicas", queryParams.getIdsValoresCaracteristicas());
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select gp.idGrupoProduto, gp.nmGrupoProduto, f.nmFabricante, ");
			queryStr.append("gp.inFimVida, gp.inDisponivel, ");
			queryStr.append("count(gpc.id), count(gpm.id) ");
			queryStr.append("from GrupoProduto gp join gp.fabricante f join gp.tipoProduto tp ");
			
			StringBuilder countQueryStr = new StringBuilder();
			countQueryStr.append("select count(distinct gp.id) ");
			countQueryStr.append("from GrupoProduto gp join gp.fabricante f join gp.tipoProduto tp ");
			
			if (queryParamsMap.get("idTecnologia") != null) {
				queryStr.append("join gp.tecnologiaList t ");
				countQueryStr.append("join gp.tecnologiaList t ");
			}
			
			if (queryParamsMap.get("idsCaracteristicas") != null) {
				queryStr.append("join gp.caracteristicaList gpc ");
				countQueryStr.append("join gp.caracteristicaList gpc ");
			}
			
			if (queryParamsMap.get("idsValoresCaracteristicas") != null) {
				if (queryParamsMap.get("idsCaracteristicas") == null) {
					queryStr.append("join gp.caracteristicaList gpc ");
					countQueryStr.append("join gp.caracteristicaList gpc ");
				}
				queryStr.append("join gpc.valorCaracteristicaList gpvc ");
				countQueryStr.append("join gpc.valorCaracteristicaList gpvc ");
			}
			
			if (queryParamsMap.get("idsCaracteristicas") == null && queryParamsMap.get("idsValoresCaracteristicas") == null) {
				queryStr.append("left join gp.caracteristicaList gpc ");
			}
			
			queryStr.append("left join gp.multimidiaList gpm ");
			queryStr.append("where tp.idTipoProduto = :idTipoProduto ");
			
			countQueryStr.append("where tp.idTipoProduto = :idTipoProduto ");
			
			if (queryParamsMap.get("idFabricante") != null) {
				queryStr.append("and f.idFabricante = :idFabricante ");
				countQueryStr.append("and f.idFabricante = :idFabricante ");
			}
			
			if (queryParamsMap.get("idModelo") != null) {
				queryStr.append("and gp.idGrupoProduto = :idModelo ");
				countQueryStr.append("and gp.idGrupoProduto = :idModelo ");
			}
			
			if (queryParamsMap.get("idTecnologia") != null) {
				queryStr.append("and t.idTecnologia = :idTecnologia ");
				countQueryStr.append("and t.idTecnologia = :idTecnologia ");
			}
			
			if (queryParamsMap.get("idsCaracteristicas") != null && queryParamsMap.get("idsValoresCaracteristicas") != null) {
				queryStr.append("and (gpc.id in (:idsCaracteristicas) or gpvc.id in (:idsValoresCaracteristicas)) ");
				countQueryStr.append("and (gpc.id in (:idsCaracteristicas) or gpvc.id in (:idsValoresCaracteristicas)) ");
			} else {
				if (queryParamsMap.get("idsCaracteristicas") != null) {
					queryStr.append("and gpc.id in (:idsCaracteristicas) ");
					countQueryStr.append("and gpc.id in (:idsCaracteristicas) ");
				}
				if (queryParamsMap.get("idsValoresCaracteristicas") != null) {
					queryStr.append("and gpvc.id in (:idsValoresCaracteristicas) ");
					countQueryStr.append("and gpvc.id in (:idsValoresCaracteristicas) ");
				}
			}
			
			queryStr.append("group by gp.idGrupoProduto, gp.nmGrupoProduto, f.nmFabricante, ");
			queryStr.append("gp.inFimVida, gp.inDisponivel ");
			
			if (paginacaoDataTableTO.getNomeCampoOrdenacao() != null) {
				queryStr.append("order by ");
				queryStr.append(paginacaoDataTableTO.getNomeCampoOrdenacao());
				queryStr.append(paginacaoDataTableTO.getOrdemCrescente() ? " asc " : " desc ");
			}
			
			Query select = em.createQuery(queryStr.toString())
				.setFirstResult(paginacaoDataTableTO.getRegistroAtual())
				.setMaxResults(paginacaoDataTableTO.getRegistrosPorPagina());
			Query countSelect = em.createQuery(countQueryStr.toString());
			
			for (Entry<String,Object> entry : queryParamsMap.entrySet()) {
				if (entry.getValue() != null) {
					select.setParameter(entry.getKey(), entry.getValue());
					countSelect.setParameter(entry.getKey(), entry.getValue());
				}
			}
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				for (Object[] objects : resultList) {
					PesquisaGrupoProdutoTO.LinhaResultado linhaResultado = pesquisaTO.new LinhaResultado();
					linhaResultado.setIdGrupoProduto((Integer) objects[0]);
					linhaResultado.setNmGrupoProduto((String) objects[1]);
					linhaResultado.setNmFabricante((String) objects[2]);
					linhaResultado.setInFimVida((String) objects[3]);
					linhaResultado.setInDisponivel((String) objects[4]);
					linhaResultado.setInCaracteristica(((Long) objects[5]).longValue() > 0 ? "S" : "N");
					linhaResultado.setInMultimidia(((Long) objects[6]).longValue() > 0 ? "S" : "N");
					resultado.add(linhaResultado);
				}
				Long count = (Long) countSelect.getResultList().get(0);
				paginacaoDataTableTO.setTotalRegistros(count.intValue());
			}

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [search]", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.GrupoProdutoDAO#findById(com.indracompany.catalogo.to.GrupoProdutoTO)
	 */
	public GrupoProdutoTO findById(GrupoProdutoTO grupoProdutoTO) throws DAOException {
		logger.debug("grupoProdutoTO: " + grupoProdutoTO);
		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
		return findById(grupoProdutoTO.getIdGrupoProduto(), grupoProdutoTOBuilder);
	}
	
	public GrupoProdutoTO findById(Integer idGrupoProduto, GrupoProdutoTOBuilder grupoProdutoTOBuilder) throws DAOException {
		GrupoProdutoTO grupoProdutoTO = null;
		try {
			grupoProdutoTO = grupoProdutoTOBuilder.createGrupoProdutoTO(findById(idGrupoProduto));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return grupoProdutoTO;
	}
	
	public GrupoProduto findById(Integer idGrupoProduto) throws DAOException {
		GrupoProduto grupoProduto = null;
		try {
			grupoProduto = em.find(GrupoProduto.class, idGrupoProduto);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		return grupoProduto;
	}
	
	public GrupoProduto findByNome(String nmGrupoProduto) throws DAOException {
		GrupoProduto grupoProduto = null;
		try {
			Query select = em.createQuery("from GrupoProduto where nmGrupoProduto = ?");
			select.setParameter(1, nmGrupoProduto);
			grupoProduto = (GrupoProduto) select.getSingleResult();
		} catch (NoResultException e) {
			grupoProduto = null;
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findByNome]", e);
		}
		return grupoProduto;
	}

	public void save(GrupoProdutoTO grupoProdutoTO) throws DAOException {
		logger.debug("grupoProdutoTO: " + grupoProdutoTO);
		try {
			GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
			GrupoProduto grupoProduto = grupoProdutoTOBuilder.createGrupoProduto(grupoProdutoTO);
			save(grupoProduto);
			grupoProdutoTO.setIdGrupoProduto(grupoProduto.getIdGrupoProduto());
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void save(GrupoProduto grupoProduto) throws DAOException {
		try {
			em.persist(grupoProduto);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [save]", e);
		}
	}
	
	public void update(GrupoProduto grupoProduto) throws DAOException {
		try {
			em.merge(grupoProduto);
			em.flush();
		} catch(Exception e) {
			throw new DAOException("Erro ao executar o DAO [update]", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ParametrizacaoProdutosTO recuperarModelos(ParametrizacaoProdutosTO pp){

		logger.debug("inicio - recuperarModelos ");
		StringBuilder query = new StringBuilder();
		query.append("select gp.idGrupoProduto, gp.nmGrupoProduto ");
		query.append(" from GrupoProduto gp ");
		query.append(" join gp.fabricante f ");
		query.append(" left join gp.tecnologiaList t ");
		query.append(" join gp.tipoProduto tp ");
		query.append(" where f.idFabricante = :idFabricante ");
		if(pp.getIdTecnologia()!= null){

			query.append(" and t.idTecnologia = :idTecnologia ");
		}
		query.append(" and tp.idTipoProduto = :idTipoProduto ");
		query.append(" and gp.inDisponivel = 'S' ");
		Query select = em.createQuery(query.toString())
		.setParameter("idFabricante", pp.getIdFabricante())
		.setParameter("idTipoProduto", pp.getIdTipoProduto());

		if(pp.getIdTecnologia()!= null){
			select.setParameter("idTecnologia", pp.getIdTecnologia());
		}
		List<Object[]> results = select.getResultList();
		pp.setGrupoProdutoTOs(new LinkedList<GrupoProdutoTO>());
		for(Object[] result: results){

			query = new StringBuilder();
			GrupoProdutoTO gp = new GrupoProdutoTO((Integer)result[0]);
			gp.setNmGrupoProduto((String)result[1]);
			query.append(" select c.idCor, c.nmCor, c.rgb ");
			query.append(" from Multimidia m ");
			query.append(" join m.grupoProduto gp ");
			query.append(" join m.cor c ");
			query.append(" where gp.idGrupoProduto = :idGrupoProduto ");
			List<Object[]> rs = (List<Object[]>) em.createQuery(query.toString())
				.setParameter("idGrupoProduto", (Integer)result[0])
				.getResultList();
			gp.setCores(new LinkedList<CorTO>());
			for(Object[] r: rs){

				CorTO cor = new CorTO();
				cor.setIdCor((Integer)r[0]);
				cor.setNmCor((String)r[1]);
				cor.setRgb((String)r[2]);
				gp.getCores().add(cor);
			}
			pp.getGrupoProdutoTOs().add(gp);
		}
		return pp;
	}
	public boolean vincularProdutosGrupoProduto(ParametrizacaoProdutosTO pp){

		try{
			for(GrupoProdutoTO gpTO : pp.getGrupoProdutoTOs()){

				GrupoProduto gp = em.find(GrupoProduto.class, gpTO.getIdGrupoProduto());
				for(Integer id:pp.getIdProdutos()){

					Produto p = em.find(Produto.class, id);
					if(pp.getGrupoProdutoTOs().get(0).getCorTO() != null){

						p.setCor(em.find(Cor.class, gpTO.getCorTO().getIdCor()));
						em.merge(p);
					}
					ProdutoGrupoProduto pgp = new ProdutoGrupoProduto();
					pgp.setDtCriacao(new Date());
					pgp.setGrupoProduto(gp);
					pgp.setInDisponivel("S");
					pgp.setNmUsuarioCriacao(pp.getNomeUsuarioAltercao());
					pgp.setProduto(p);
					em.merge(pgp);
				}
			}
			return true;
		}catch(Exception e){

			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean removerVinculoGrupoProduto(ParametrizacaoProdutosTO pp){

		try{
			for(GrupoProdutoTO gpTO : pp.getGrupoProdutoTOs()){

				GrupoProduto gp = em.find(GrupoProduto.class, gpTO.getIdGrupoProduto());
				for(Integer id:pp.getIdProdutos()){

					StringBuilder select = new StringBuilder(" select pgp from ProdutoGrupoProduto pgp join pgp.produto p ");
					if(pp.getRemoverVinculos()){

						select.append(" join pgp.grupoProduto gp ");
						select.append(" where p.idProduto = :idProduto ");
						select.append(" and gp.idGrupoProduto = :idGrupoProduto ");
					}else{

						select.append(" where p.idProduto = :idProduto ");
					}
					Query query = em.createQuery(select.toString()).setParameter("idProduto",id);
					if(pp.getRemoverVinculos()){

						query.setParameter("idGrupoProduto", gp.getIdGrupoProduto());
					}
					List<ProdutoGrupoProduto> results = query.getResultList();
					for(ProdutoGrupoProduto pgp: results){

						em.remove(pgp);
					}
				}
			}
			return true;
		}catch(Exception e){

			e.printStackTrace();
			return false;
		}
	}

	public void remove(GrupoProdutoTO to) throws DAOException{
		
		try {
			em.remove(em.find(GrupoProduto.class, to.getIdGrupoProduto()));
			em.flush();
		} catch(Exception e){
			throw new DAOException("Erro ao executar o DAO [remove]",e);
		}
	}

	public void copy(GrupoProdutoTO to) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public List<TipoFrequenciaTO> obterTipoFrequenciaTOList(Integer idGrupoProduto, Integer idTecnologia) throws DAOException {
		List<TipoFrequenciaTO> tipoFrequenciaTOList = new ArrayList<TipoFrequenciaTO>();
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select tf.idtipofrequencia, tf.nmtipofrequencia, ");
			queryStr.append("v.idvlfrequencia, v.vlfrequencia ");
			queryStr.append("from catalogoprs_ow.grupoprodutotecnologia gpt, ");
			queryStr.append("catalogoprs_ow.grupoprodutotecnfreqvl gpttfv, ");
			queryStr.append("catalogoprs_ow.tecnologiatpfrequenciavl ttfv, ");
			queryStr.append("catalogoprs_ow.vlfrequencia v, ");
			queryStr.append("catalogoprs_ow.tecnologiatpfrequencia ttf, ");
			queryStr.append("catalogoprs_ow.tipofrequencia tf ");
			queryStr.append("where gpt.idgrupoprodutotecn = gpttfv.idgrupoprodutotecn ");
			queryStr.append("and gpttfv.idtecnologiafrequenciavl = ttfv.idtecnologiatpfrequenciavl ");
			queryStr.append("and ttfv.idvlfrequencia = v.idvlfrequencia ");
			queryStr.append("and ttfv.idtecnologiatpfrequencia = ttf.idtecnologiatpfrequencia ");
			queryStr.append("and ttf.idtipofrequencia = tf.idtipofrequencia ");
			queryStr.append("and gpt.idgrupoproduto = ? and gpt.idtecnologia = ? ");
			queryStr.append("order by tf.nmtipofrequencia, v.vlfrequencia");
			
			Query select = em.createNativeQuery(queryStr.toString());
			select.setParameter(1, idGrupoProduto);
			select.setParameter(2, idTecnologia);
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				Map<Integer, TipoFrequenciaTO> tipoFrequenciaTOMap = new HashMap<Integer, TipoFrequenciaTO>();
				Map<Integer, VlFrequenciaTO> vlFrequenciaTOMap = new HashMap<Integer, VlFrequenciaTO>();
				for (Object[] objects : resultList) {
					Integer idTipoFrequencia = ((BigDecimal) objects[0]).intValue();
					TipoFrequenciaTO tipoFrequenciaTO = tipoFrequenciaTOMap.get(idTipoFrequencia);
					if (tipoFrequenciaTO == null) {
						tipoFrequenciaTO = new TipoFrequenciaTO();
						tipoFrequenciaTO.setIdTipoFrequencia(idTipoFrequencia);
						tipoFrequenciaTO.setNmTipoFrequencia((String) objects[1]);
						tipoFrequenciaTO.setVlFrequenciaTOList(new ArrayList<VlFrequenciaTO>());
						tipoFrequenciaTOMap.put(idTipoFrequencia, tipoFrequenciaTO);
					}
					Integer idVlFrequencia = ((BigDecimal) objects[2]).intValue();
					VlFrequenciaTO vlFrequenciaTO = vlFrequenciaTOMap.get(idVlFrequencia);
					if (vlFrequenciaTO == null) {
						vlFrequenciaTO = new VlFrequenciaTO();
						vlFrequenciaTO.setIdVlFrequencia(idVlFrequencia);
						vlFrequenciaTO.setVlFrequencia((String) objects[3]);
						vlFrequenciaTOMap.put(idVlFrequencia, vlFrequenciaTO);
					}
					tipoFrequenciaTO.getVlFrequenciaTOList().add(vlFrequenciaTO);
				}
				tipoFrequenciaTOList.addAll(tipoFrequenciaTOMap.values());
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterTipoFrequenciaTOList]", e);
		}
		return tipoFrequenciaTOList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CaracteristicaTO> obterCaracteristicaTOList(Integer idGrupoProduto) throws DAOException {
		List<CaracteristicaTO> caracteristicaTOList = new ArrayList<CaracteristicaTO>();
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select c.idcaracteristica, c.nmcaracteristica, ");
			queryStr.append("vc.idvalorcaracteristica, vc.valor ");
			queryStr.append("from catalogoprs_ow.grupoprodutocaracteristica gpc, ");
			queryStr.append("catalogoprs_ow.caracteristica c, ");
			queryStr.append("catalogoprs_ow.valorcaracteristica vc ");
			queryStr.append("where gpc.idcaracteristica = c.idcaracteristica ");
			queryStr.append("and gpc.idcaracteristica = vc.idcaracteristica(+) ");
			queryStr.append("and gpc.idvalorcaracteristica = vc.idvalorcaracteristica(+) ");
			queryStr.append("and gpc.idgrupoproduto = ? ");
			queryStr.append("order by c.nmcaracteristica, vc.valor");
			
			Query select = em.createNativeQuery(queryStr.toString());
			select.setParameter(1, idGrupoProduto);
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				Map<Integer, CaracteristicaTO> caracteristicaTOMap = new HashMap<Integer, CaracteristicaTO>();
				for (Object[] objects : resultList) {
					Integer idCaracteristica = ((BigDecimal) objects[0]).intValue();
					CaracteristicaTO caracteristicaTO = caracteristicaTOMap.get(idCaracteristica);
					if (caracteristicaTO == null) {
						caracteristicaTO = new CaracteristicaTO();
						caracteristicaTO.setIdCaracteristica(idCaracteristica);
						caracteristicaTO.setNmCaracteristica((String) objects[1]);
						caracteristicaTO.setDominioValoresCaracteristica(new ArrayList<ValorCaracteristicaTO>());
						caracteristicaTOMap.put(idCaracteristica, caracteristicaTO);
					}
					if (objects[2] != null) {
						ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
						valorCaracteristicaTO.setIdValorCaracteristica(((BigDecimal) objects[2]).intValue());
						valorCaracteristicaTO.setValor((String) objects[3]);
						caracteristicaTO.getDominioValoresCaracteristica().add(valorCaracteristicaTO);
					}
				}
				caracteristicaTOList.addAll(caracteristicaTOMap.values());
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterCaracteristicaTOList]", e);
		}
		return caracteristicaTOList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProdutoTO> obterProdutoTOList(Integer idGrupoProduto) throws DAOException {
		List<ProdutoTO> produtoTOList = new ArrayList<ProdutoTO>();
		try {
//			StringBuilder queryStr = new StringBuilder();
//			queryStr.append("select p from Produto p ");
//			queryStr.append("join p.produtoGrupoProdutos pgp ");
//			queryStr.append("join pgp.grupoProduto gp ");
//			queryStr.append("where gp.idGrupoProduto = ? ");
//			
//			Query select = em.createQuery(queryStr.toString());
//			select.setParameter(1, idGrupoProduto);
//			
//			List<Produto> resultList = (List<Produto>) select.getResultList();
//			if (resultList != null && !resultList.isEmpty()) {
//				ProdutoTOBuilder produtoTOBuilder = new ProdutoTOBuilder(false);
//				produtoTOBuilder.setCriarSistemaProduto(true);
//				produtoTOBuilder.setCriarGama(true);
//				produtoTOList = produtoTOBuilder.createProdutoTOList(resultList);
//			}
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select p.idproduto, p.nmproduto, ");
			queryStr.append("sp.idsistemaproduto, sp.cdcodigo, ");
			queryStr.append("g.idgama, g.nmgama ");
			queryStr.append("from catalogoprs_ow.produto p, ");
			queryStr.append("catalogoprs_ow.sistemaproduto sp, ");
			queryStr.append("catalogoprs_ow.gama g, ");
			queryStr.append("catalogoprs_ow.produtogrupoproduto pgp ");
			queryStr.append("where p.idproduto = sp.idproduto(+) ");
			queryStr.append("and p.idgama = g.idgama(+) ");
			queryStr.append("and p.idproduto = pgp.idproduto ");
			queryStr.append("and pgp.idgrupoproduto = ? ");
			queryStr.append("order by sp.cdcodigo");
			
			Query select = em.createNativeQuery(queryStr.toString());
			select.setParameter(1, idGrupoProduto);
			
			List<Object[]> resultList = (List<Object[]>) select.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				Map<Integer, GamaTO> gamaTOMap = new HashMap<Integer, GamaTO>();
				for (Object[] objects : resultList) {
					ProdutoTO produtoTO = new ProdutoTO();
					produtoTO.setIdProduto(((BigDecimal) objects[0]).intValue());
					produtoTO.setNmProduto((String) objects[1]);
					if (objects[2] != null) {
						SistemaProdutoTO sistemaProdutoTO = new SistemaProdutoTO();
						sistemaProdutoTO.setIdSistemaProduto(((BigDecimal) objects[2]).intValue());
						sistemaProdutoTO.setCdCodigo((String) objects[3]);
						produtoTO.setSistemaProdutoTO(sistemaProdutoTO);
					}
					if (objects[4] != null) {
						Integer idGama = ((BigDecimal) objects[4]).intValue();
						GamaTO gamaTO = gamaTOMap.get(idGama);
						if (gamaTO == null) {
							gamaTO = new GamaTO();
							gamaTO.setIdGama(idGama);
							gamaTO.setNmGama((String) objects[5]);
							gamaTOMap.put(idGama, gamaTO);
						}
						produtoTO.setGamaTO(gamaTO);
					}
					produtoTOList.add(produtoTO);
				}
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [obterProdutoTOList]", e);
		}
		return produtoTOList;
	}
	
}