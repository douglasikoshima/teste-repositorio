package com.indracompany.catalogo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.indracompany.catalogo.dao.interfaces.OfertaServicoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 * 
 * Classe respons�vel em fazer a persistencia no banco de dados de Oferta Servi�o.
 */
@Stateless
public class OfertaServicoDAOImpl implements OfertaServicoDAO {
	
	
	
	@PersistenceContext
	private EntityManager em;
	
	private OfertaServicoTOBuilder ofertaServicoTOBuilder = new OfertaServicoTOBuilder();
	
	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
		StringBuffer queryStr = new StringBuffer();
		
		queryStr.append("select o from OfertaServico o ");
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" inner join o.ofertaServicoCategoriaScore oscs ");
		}
		
		queryStr.append(" where o.idOfertaServico is not null ");
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" and oscs.categoriaScore.idCategoriaScore = :idCategoriaScore ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" and upper(o.nmOfertaServico) like upper(:nmOfertaServico) ");
		}
		
		Query query = em.createQuery(queryStr.toString());
		
		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmOfertaServico", "%" + categorizacaoAnaliseCreditoTO.getNome() + "%");
		}
		
		List<CategorizacaoAnaliseCreditoTO> list = ofertaServicoTOBuilder.createCategorizacaoAnaliseCreditoTOList(query.getResultList());
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {

		StringBuffer queryStr = new StringBuffer();		
		queryStr.append("SELECT ");
		queryStr.append(" OFERTA.IDOFERTASERVICO, OFERTA.NMOFERTASERVICO, OFERTA.NMCATEGORIASCORE, OFERTA.IDCATEGORIASCORE, SUM(OFERTA.PRECOMEDIO) AS VLOFERTA, OFERTA.IDOFERTASERVICOCATEGORIASCORE, OFERTA.CDOFERTASERVICO ");
		queryStr.append("FROM ( ");
		queryStr.append("SELECT ");
		queryStr.append(" OS.IDOFERTASERVICO, OS.NMOFERTASERVICO, CS.NMCATEGORIASCORE, CS.IDCATEGORIASCORE, OSCS.IDOFERTASERVICOCATEGORIASCORE, OS.CDOFERTASERVICO, ");
		queryStr.append(" ( SELECT NVL(TRUNC( AVG(ST.PRECOBASE), 2 ), 0) ");
		queryStr.append(" FROM CATALOGOPRS_OW.SERVICOTARIFA ST ");
		queryStr.append(" INNER JOIN CATALOGOPRS_OW.SERVICO S1 ON S1.IDSERVICO = ST.IDSERVICO ");
		queryStr.append(" WHERE S1.IDSERVICO = S.IDSERVICO AND ST.CDTPTARIFA = 'R' AND ST.CDPERCOBRANCA = 'MENSALMENTE' ");
		queryStr.append(" ) AS PRECOMEDIO, S.IDSERVICO ");
		queryStr.append(" FROM CATALOGOPRS_OW.OFERTASERVICO OS ");
		queryStr.append(" INNER JOIN CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE OSCS ON OS.IDOFERTASERVICO = OSCS.IDOFERTASERVICO ");
		queryStr.append(" INNER JOIN CATALOGOPRS_OW.CATEGORIASCORE CS ON OSCS.IDCATEGORIASCORE = CS.IDCATEGORIASCORE ");
		queryStr.append(" LEFT OUTER JOIN CATALOGOPRS_OW.SERVICOOFERTASERVICO SOS ON OS.IDOFERTASERVICO = SOS.IDOFERTASERVICO ");
		queryStr.append(" LEFT OUTER JOIN CATALOGOPRS_OW.SERVICO S ON SOS.IDSERVICO = S.IDSERVICO ");
		queryStr.append(" WHERE CS.IDCATEGORIASCORE = :idCategoriaScore ");
		
		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			queryStr.append(" AND OS.IDOFERTASERVICO = :idOfertaServico ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" AND UPPER(OS.NMOFERTASERVICO) LIKE UPPER(:nmOfertaServico) ");
		}
		
		if (categorizacaoAnaliseCreditoTO.getIdAnaliseCredito() != null) {
			queryStr.append("  AND OSCS.IDOFERTASERVICOCATEGORIASCORE IN ( " +
					" SELECT SERVICOCAT7_.IDOFERTASERVICOCATEGORIASCORE " +
					" FROM  CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE SERVICOCON5_, " +
                    "	    CATALOGOPRS_OW.ANALISECREDITO ANALISECRE6_, " +
                    "	    CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE SERVICOCAT7_, " +
                    "       CATALOGOPRS_OW.CABECALHOANALISECREDITO CABECALHOA8_, " +
                    "       CATALOGOPRS_OW.CATEGORIASCORE CATEGORIAS9_ " +
                    " WHERE SERVICOCON5_.IDANALISECREDITO = ANALISECRE6_.IDANALISECREDITO " +
			        "       AND SERVICOCON5_.IDOFERTASERVICOCATEGORIASCORE = SERVICOCAT7_.IDOFERTASERVICOCATEGORIASCORE " +
			        "       AND SERVICOCON5_.IDCABECALHOANALISECREDITO = CABECALHOA8_.IDCABECALHOANALISECREDITO " +
			        "       AND SERVICOCAT7_.IDCATEGORIASCORE = CATEGORIAS9_.IDCATEGORIASCORE " +
			        "       AND (    (CABECALHOA8_.IDCABECALHOANALISECREDITO = :idCabecalhoAnaliseCredito) " +
			        "            AND (CATEGORIAS9_.IDCATEGORIASCORE = :idCategoriaScore) " +
			        "            AND (ANALISECRE6_.IDANALISECREDITO = :idAnaliseCredito)" +
			        "           )                    " +
			        " ) ");
			
		}
		
		queryStr.append(" ) OFERTA ");
		queryStr.append(" GROUP BY OFERTA.IDOFERTASERVICO, OFERTA.NMOFERTASERVICO, OFERTA.NMCATEGORIASCORE, OFERTA.IDCATEGORIASCORE, OFERTA.IDOFERTASERVICOCATEGORIASCORE, OFERTA.CDOFERTASERVICO ");
		
		if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null || categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
			
			if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null && categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
				queryStr.append(" HAVING SUM(OFERTA.PRECOMEDIO) BETWEEN :precoDe AND :precoAte ");				
			} else {
				if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null ) {
					queryStr.append(" HAVING SUM(OFERTA.PRECOMEDIO) >= :precoDe ");	
				}
				if (categorizacaoAnaliseCreditoTO.getPrecoAte() != null ) {
					queryStr.append(" HAVING SUM(OFERTA.PRECOMEDIO) <= :precoAte ");
				}
			}
			
		}

		queryStr.append(" ORDER BY OFERTA.NMOFERTASERVICO ");
		
		Query query = em.createNativeQuery(queryStr.toString());

		query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
		
		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			query.setParameter("idOfertaServico", categorizacaoAnaliseCreditoTO.getId());
		}
		
		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmOfertaServico", "%" + categorizacaoAnaliseCreditoTO.getNome() + "%");
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

		List<CategorizacaoAnaliseCreditoTO> list = ofertaServicoTOBuilder.createCategorizacaoAnaliseCreditoTOListNative(query.getResultList());
		
		return list;
	}
	
	public void updateOfertaServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
	
	}

	public void updateValueOfertaServicoTO(List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoTOList) throws DAOException {
		CategorizacaoAnaliseCreditoTO parametro = new CategorizacaoAnaliseCreditoTO();
		Map<String, Float> valoresMap = new HashMap<String, Float>();
		for (OfServicoConfiguracaoScoreTO scoreTO : ofServicoConfiguracaoTOList) {
			Integer idCategoria = scoreTO.getOfertaServicoCategoriaScoreTO().getCategoriaScoreTO().getIdCategoriaScore();
			Integer id = scoreTO.getOfertaServicoCategoriaScoreTO().getIdOfertaServico();
			String key = idCategoria + "|" + id;
			Float valor = valoresMap.get(key);
			if (valor == null) {
				parametro.setIdCategoria(idCategoria);
				parametro.setId(id);
				List<CategorizacaoAnaliseCreditoTO> list = searchOfertaServicoConfigAnaliseCredito(parametro);
				valor = list.get(0).getValor();
				valoresMap.put(key, valor);
			}
			scoreTO.getOfertaServicoCategoriaScoreTO().setVlOfertaServico(valor);
		}
	}
	
}
	