package com.indracompany.catalogo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoLinhaDAO;
import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.Ofertafixa;
import com.indracompany.catalogo.datalayer.OfertafixaCreditoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

@Stateless
public class AnaliseCreditoLinhaDAOImpl implements AnaliseCreditoLinhaDAO {

    private static Logger log = Logger.getLogger(AnaliseCreditoLinhaDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<AnaliseCreditoLinhaTO> search(AnaliseCreditoLinhaTO analiseCreditoLinhaTO, List<AnaliseCreditoTO> analiseCreditoTOListScore)
            throws DAOException {
        log.debug("Preparando para consulta no banco de dados.");
        List<AnaliseCreditoLinhaTO> analiseCreditoLinhaList;
        try {
            log.debug("Preparando HQL para consulta 'AnaliseCreditoLinha.search'.");
            Map<String, Object> params = new HashMap<String, Object>();
            StringBuilder hql = new StringBuilder("select o.idOfertafixa, o.dsOfertafixa, o.inFWT, o.inConvergenteFibra, ");
            hql.append("o.inConvergenteCobre, o.inSpeedySoloFibra, o.inSpeedySoloCobre, o.inPortab, o.dtInicial, o.dtFinal, ");
            hql.append("ofcs.idOfertafixaCreditoScore, ac.idAnaliseCredito ,");
            hql.append("sln.nmComercial, ");
            hql.append("spc.nmComercial, ");
            hql.append("spl.nmComercial, ");
            hql.append("o.cdOfertafixa ");
            hql.append("from Ofertafixa o ");
            hql.append("left join o.ofertafixaCreditoScoreList ofcs ");
            hql.append("left join ofcs.analiseCredito ac ");
            hql.append("left join o.solicitacaoComercialLinha scln ");
            hql.append("left join scln.sistemaServico ssln ");
            hql.append("left join ssln.servico sln ");
            hql.append("left join o.solicitacaoComercialPreCad scpc ");
            hql.append("left join scpc.sistemaServico sspc ");
            hql.append("left join sspc.servico spc ");
            hql.append("left join o.solicitacaoComercialPlano scpl ");
            hql.append("left join scpl.sistemaServico sspl ");
            hql.append("left join sspl.servico spl ");
            hql.append("where (o.dtFinal >= trunc(sysdate) or o.dtFinal is null) ");
            
            if (analiseCreditoLinhaTO.getScore() != null && !analiseCreditoLinhaTO.getScore().equals(Integer.valueOf(0))) {
                if (analiseCreditoLinhaTO.getScore() > 0) {
                    hql.append(" and o.idOfertafixa in (select ofcs_2.ofertafixa.idOfertafixa from OfertafixaCreditoScore ofcs_2 ");
                    hql.append("where ofcs_2.analiseCredito.idAnaliseCredito = :idAnaliseCredito) ");
                    params.put("idAnaliseCredito", analiseCreditoLinhaTO.getScore());
                } else { 
                    hql.append(" and o.idOfertafixa not in (select ofcs_2.ofertafixa.idOfertafixa from OfertafixaCreditoScore ofcs_2) ");
                }
            }

            if (analiseCreditoLinhaTO.getIdServico() != null && !analiseCreditoLinhaTO.getIdServico().equals(Integer.valueOf(0))) {
                hql.append("and sln.idServico = :idServico ");
                params.put("idServico", analiseCreditoLinhaTO.getIdServico());
            }

            if (!StringUtils.isBlank(analiseCreditoLinhaTO.getDsOfertafixa())) {
                hql.append("and UPPER(o.dsOfertafixa) like :dsOfertafixa ");
                params.put("dsOfertafixa", "%" + analiseCreditoLinhaTO.getDsOfertafixa().toUpperCase() + "%");
            }
            hql.append("order by o.dsOfertafixa ");

			analiseCreditoLinhaList = new AnaliseCreditoLinhaTOBuilder().createTOList(this.getQuery(hql.toString(), params).getResultList(), analiseCreditoTOListScore);
            this.adicionarPriorizado(analiseCreditoLinhaList);
            
        } catch (Exception e) {
            String msg = String.format("erro pesquisar oferta para idAnaliseCredito %s", analiseCreditoLinhaTO.getScore());
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug("Return analiseCreditoLinhaList da busca no banco:" + analiseCreditoLinhaList);
        return analiseCreditoLinhaList;
    }

	private void adicionarPriorizado(List<AnaliseCreditoLinhaTO> analiseCreditoLinhaList) {
		for(AnaliseCreditoLinhaTO analiseCreditoLinhaTODB : analiseCreditoLinhaList) {
			for (AnaliseCreditoTO analiseCreditoTODB : analiseCreditoLinhaTODB.getAnaliseCreditoTOList()) {
				StringBuilder string = new StringBuilder("select count(*) from Ofertafixa o ");
				string.append("inner join o.ofertafixaCreditoScoreList ofcs ");
				string.append("inner join ofcs.ofertaFixaCreditoPriorAg ofcpa ");
				string.append("where o.idOfertafixa = :idOfertafixa ");
				string.append("and ofcs.analiseCredito.idAnaliseCredito = :idAnaliseCredito ");
				Query countQuery = em.createQuery(string.toString());
				countQuery.setParameter("idOfertafixa", analiseCreditoLinhaTODB.getIdOferta());
				countQuery.setParameter("idAnaliseCredito", analiseCreditoTODB.getIdAnaliseCredito());
				long longValue = ((Long)countQuery.getSingleResult()).longValue();
				if (longValue > 0) {
					analiseCreditoTODB.setPriorizado(true);
					analiseCreditoLinhaTODB.setPriorizado(true);
				} else {
					analiseCreditoTODB.setPriorizado(false);
				}
			}
		}
	}

    @SuppressWarnings("unchecked")
    public List<AnaliseCreditoLinhaTO> loadServicoLinha() throws DAOException {
        log.debug("Preparando para a consulta no banco de dados.");
        try {
            log.debug("NamedQuery para consulta 'Servico.searchServicoLinha' da Entity 'OfertaFixaAdicional.");
            return new AnaliseCreditoLinhaTOBuilder().buildTOServicoList(em.createNamedQuery("Servico.searchServicoLinha").getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AnaliseCreditoTO> loadScore() throws DAOException {
        log.debug("Preparando para a consulta no banco de dados.");
        try {
            log.debug("NamedQuery para consulta 'AnaliseCredito.findAll' da Entity 'AnaliseCredito.");
            return new AnaliseCreditoTOBuilder().createAnaliseCreditoTOList(em.createNamedQuery("AnaliseCredito.findAll").getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public OfertafixaCreditoScore find(Integer idOfertaFixa, Integer idAnaliseCredito) {

        try {
            StringBuilder hql = new StringBuilder("select ofcs from OfertafixaCreditoScore ofcs where ");
            Map<String, Object> params = new HashMap<String, Object>();
            hql.append("ofcs.ofertafixa.idOfertafixa = :ifOfertafixa ");
            params.put("ifOfertafixa", idOfertaFixa);
            hql.append("and ofcs.analiseCredito.idAnaliseCredito = :idAnaliseCredito ");
            params.put("idAnaliseCredito", idAnaliseCredito);
            return (OfertafixaCreditoScore) this.getQuery(hql.toString(), params).getResultList().iterator().next();
        } catch (NoSuchElementException e) {

            return null;
        }
    }

    public void excluir(OfertafixaCreditoScore ocs) throws DAOException {
        log.debug("Preparando para o delete no banco de dados.");
        try {
            em.remove(ocs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public void gravar(AnaliseCreditoLinhaTO acl) throws DAOException {
        log.debug("Preparando para o persist no banco de dados.");
        AnaliseCredito ac = null;
        Ofertafixa of = null;
        if (acl.getAnaliseCreditoTO() != null && acl.getAnaliseCreditoTO().getIdAnaliseCredito() > 0) {
            ac = em.find(AnaliseCredito.class, acl.getAnaliseCreditoTO().getIdAnaliseCredito());
        }
        if (acl.getIdOferta() != null && acl.getIdOferta() > 0) {
            of = em.find(Ofertafixa.class, acl.getIdOferta());
        }
        OfertafixaCreditoScore ocs = new OfertafixaCreditoScore(null, new Date(), "teste", ac, of, null, null);

        em.persist(ocs);
    }

    private Query getQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) {
            Object paramValue = params.get(param);
            query.setParameter(param, params.get(param));
            log.debug(String.format("%s=%s", param, paramValue));
        }
        return query;
    }

}
