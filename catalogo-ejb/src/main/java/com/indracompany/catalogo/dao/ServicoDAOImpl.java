package com.indracompany.catalogo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistência no banco de dados de Serviço.
 */
@Stateless
public class ServicoDAOImpl implements ServicoDAO {
	
	private static Logger logger = Logger.getLogger(ServicoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchServicoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
		
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select ");
		queryStr.append(" new com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO( s.idServico, s.nmComercial, cs.nmCategoriaScore, cs.idCategoriaScore, trunc( avg(nvl(st.precoBase, 0)), 2 ), scs.idServicoCategoriaScore, ss.cdCodigo )");
		queryStr.append(" from ");
		queryStr.append(" Servico s ");
		queryStr.append(" inner join s.sistemaServico as ss ");
		queryStr.append(" inner join s.servicoCategoriaScore as scs ");
		queryStr.append(" inner join scs.categoriaScore as cs ");
		queryStr.append(" left outer join s.servicoTarifaList as st ");
		queryStr.append(" where cs.idCategoriaScore = :idCategoriaScore ");
		queryStr.append(" and ( st.cdTpTarifa = 'R' or st.cdTpTarifa is null ) ");
		queryStr.append(" and ( st.cdPerCobranca = 'MENSALMENTE' or st.cdPerCobranca is null ) ");
		
		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			queryStr.append(" and s.idServico = :idServico ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" and upper(s.nmComercial) like upper(:nmComercial) ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getIdAnaliseCredito() != null) {
			queryStr.append(" and scs.idServicoCategoriaScore not in (" +
					"select scat.idServicoCategoriaScore from ServicoConfiguracaoScore srvConf " +
					" inner join srvConf.analiseCredito anl" +
					" inner join srvConf.servicoCategoriaScore scat " +
					" inner join srvConf.cabecalhoAnaliseCredito cab " +
					" inner join scat.categoriaScore cats " +
					" where cab.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito " +
					" and  cats.idCategoriaScore = :idCategoriaScore " +
					" and anl.idAnaliseCredito = :idAnaliseCredito) ");
		}
		
		queryStr.append(" group by s.idServico, s.nmComercial, cs.nmCategoriaScore, cs.idCategoriaScore, scs.idServicoCategoriaScore, ss.cdCodigo");
		
		if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null || categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
			
			if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null && categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
				queryStr.append(" having trunc( avg(nvl(st.precoBase, 0)), 2 ) between :precoDe and :precoAte ");				
			} else {
				if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null ) {
					queryStr.append(" having trunc( avg(nvl(st.precoBase, 0)), 2 ) >= :precoDe ");	
				}
				if (categorizacaoAnaliseCreditoTO.getPrecoAte() != null ) {
					queryStr.append(" having trunc( avg(nvl(st.precoBase, 0)), 2 ) <= :precoAte ");
				}
			}
			
		}

		queryStr.append(" order by s.nmComercial ");
		
		Query query = em.createQuery(queryStr.toString());

		query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
		
		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			query.setParameter("idServico", categorizacaoAnaliseCreditoTO.getId());
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmComercial", "%" + categorizacaoAnaliseCreditoTO.getNome() + "%");
		}
		
		if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null || categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
			
			if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null && categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
				query.setParameter("precoDe", categorizacaoAnaliseCreditoTO.getPrecoDe());
				query.setParameter("precoAte", categorizacaoAnaliseCreditoTO.getPrecoAte());
			} else {
				if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null ) {
					query.setParameter("precoDe", categorizacaoAnaliseCreditoTO.getPrecoDe());	
				}
				if (categorizacaoAnaliseCreditoTO.getPrecoAte() != null ) {
					query.setParameter("precoAte", categorizacaoAnaliseCreditoTO.getPrecoAte());
				}
			}
			
		}
		
		if (categorizacaoAnaliseCreditoTO.getIdAnaliseCredito() != null) {
			query.setParameter("idCabecalhoAnaliseCredito", categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			query.setParameter("idAnaliseCredito", categorizacaoAnaliseCreditoTO.getIdAnaliseCredito());
		}

		List<CategorizacaoAnaliseCreditoTO> list = query.getResultList();
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
		logger.info("categorizacaoAnaliseCreditoTO: " + categorizacaoAnaliseCreditoTO);
		
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO(s, sp.plataforma.idPlataforma, sp.plataforma.nmPlataforma, ss) ");
		queryStr.append(" from ");
		queryStr.append(" ServicoPlataforma sp ");
		queryStr.append(" inner join sp.servico s ");
		queryStr.append(" inner join s.sistemaServico ss ");
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" inner join s.servicoCategoriaScore scs ");
		}

		queryStr.append(" where s.inDisponivel = 'S' ");
        queryStr.append(" and s.inFixa = 'N' ");
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" and scs.categoriaScore.idCategoriaScore = :idCategoriaScore ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getIdPlataformas() != null && categorizacaoAnaliseCreditoTO.getIdPlataformas().length > 0) {
			queryStr.append(" and sp.plataforma.idPlataforma in (:idPlataforma1, :idPlataforma2, :idPlataforma3)");
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" and upper(s.nmComercial) like upper(:nmComercial) ");
		}
		
		Query query = em.createQuery(queryStr.toString());
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
		}
		
		int contador = 0;
		
		if (categorizacaoAnaliseCreditoTO.getIdPlataformas() != null && categorizacaoAnaliseCreditoTO.getIdPlataformas().length > 0) {
			query.setParameter("idPlataforma1", 0);
			query.setParameter("idPlataforma2", 0);
			query.setParameter("idPlataforma3", 0);
			
			for (Integer idPlataforma : categorizacaoAnaliseCreditoTO.getIdPlataformas()) {
				if (contador == 0) {
					query.setParameter("idPlataforma1", idPlataforma);
				} else if (contador == 1) {
					query.setParameter("idPlataforma2", idPlataforma);
				} else {
					query.setParameter("idPlataforma3", idPlataforma);
				}
				contador++;
			}
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmComercial", "%" + categorizacaoAnaliseCreditoTO.getNome() + "%");
		}
		
		List<CategorizacaoAnaliseCreditoTO> list = query.getResultList();
		
		return list;
	}
	
	public void updateServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
		
		
	}

	public void updateValueServicoTO(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList) throws DAOException {
		CategorizacaoAnaliseCreditoTO parametro = new CategorizacaoAnaliseCreditoTO();
		Map<String, Float> valoresMap = new HashMap<String, Float>();
		for (ServicoConfiguracaoScoreTO scoreTO : servicoConfiguracaoScoreTOList) {
			Integer idCategoria = scoreTO.getServicoCategoriaScoreTO().getCategoriaScoreTO().getIdCategoriaScore();
			Integer id = scoreTO.getServicoCategoriaScoreTO().getIdServico();
			String key = idCategoria + "|" + id;
			Float valor = valoresMap.get(key);
			if (valor == null) {
				parametro.setIdCategoria(idCategoria);
				parametro.setId(id);
				List<CategorizacaoAnaliseCreditoTO> list = searchServicoConfigAnaliseCredito(parametro);
				valor = list.get(0).getValor();
				valoresMap.put(key, valor);
			}
			scoreTO.getServicoCategoriaScoreTO().setVlServico(valor);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PlanoServicoUfRestricaoTO> searchServicoUfRestricao(PlanoServicoUfRestricaoTO planoServicoUfRestricaoTO) throws DAOException {

		logger.debug(">> searchServicoUfRestricao()");
		
		List<PlanoServicoUfRestricaoTO> list;
		ServicoTOBuilder builder = new ServicoTOBuilder();
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" select ");
			queryStr.append(" s ");
			queryStr.append(" from ");
			queryStr.append(" Servico s ");
			queryStr.append(" inner join s.sistemaServico ss ");
			queryStr.append(" where s.inFixa = 'N' ");
			
			if (planoServicoUfRestricaoTO.getNome() != null && !planoServicoUfRestricaoTO.getNome().trim().equals("")) {
				queryStr.append(" and upper(s.nmComercial) like upper(:nmComercial) ");
			}
			
			if (planoServicoUfRestricaoTO.getCodigo() != null && !planoServicoUfRestricaoTO.getCodigo().trim().equals("")) {
				queryStr.append(" and upper(ss.cdCodigo) like upper(:cdCodigo) ");
			}
			
			if (planoServicoUfRestricaoTO.getIn4g() != null && !planoServicoUfRestricaoTO.getIn4g().trim().equals("")) {
				queryStr.append(" and upper(s.in4g) = upper(:in4g) ");
			}

			queryStr.append(" order by s.nmComercial ");
			
			Query query = em.createQuery(queryStr.toString());
			
			if (planoServicoUfRestricaoTO.getNome() != null && !planoServicoUfRestricaoTO.getNome().trim().equals("")) {
				query.setParameter("nmComercial", "%" + planoServicoUfRestricaoTO.getNome().trim() + "%");
			}
			
			if (planoServicoUfRestricaoTO.getCodigo() != null && !planoServicoUfRestricaoTO.getCodigo().trim().equals("")) {
				query.setParameter("cdCodigo", "%" + planoServicoUfRestricaoTO.getCodigo().trim() + "%");
			}
			
			if (planoServicoUfRestricaoTO.getIn4g() != null && !planoServicoUfRestricaoTO.getIn4g().trim().equals("")) {
				query.setParameter("in4g", planoServicoUfRestricaoTO.getIn4g());
			}
			
			list = builder.createPlanoServicoUfRestricaoTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [searchServicoUfRestricao]", e);
		}
		
		logger.debug("<< searchServicoUfRestricao()");

		return list;
	}

	
}
