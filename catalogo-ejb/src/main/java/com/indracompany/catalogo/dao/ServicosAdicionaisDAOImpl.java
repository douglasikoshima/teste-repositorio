package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicosAdicionaisDAO;
import com.indracompany.catalogo.datalayer.OfertaFixaAdicional;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Stateless
public class ServicosAdicionaisDAOImpl implements ServicosAdicionaisDAO {

    private static Logger log = Logger.getLogger(ServicosAdicionaisDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<ServicosAdicionaisTO> search() throws DAOException {
        log.debug("Preparando para consulta no banco de dados.");
        try {
            log.debug("NamedQuery para consulta 'OfertaFixaAdicional.search' da Entity 'OfertaFixaAdicional.");
            List<ServicosAdicionaisTO> servicoAdicionalTOList = new ServicosAdicionaisTOBuilder().buildTOList(em.createNamedQuery("OfertaFixaAdicional.search").getResultList());
            Set<ServicosAdicionaisTO> servicosAdicionaisTOSet = new LinkedHashSet<ServicosAdicionaisTO>();
            for (ServicosAdicionaisTO servicosAdicionaisTODB : servicoAdicionalTOList) {
                servicosAdicionaisTOSet.add(servicosAdicionaisTODB);
                servicosAdicionaisTOSet.addAll(this.carregarServicosAdicionaisTODependentes(servicosAdicionaisTODB));
                servicosAdicionaisTOSet.addAll(this.carregarServicosAdicionaisTOObrigatoriedadeAlta(servicosAdicionaisTODB));
            }
            return new ArrayList<ServicosAdicionaisTO>(servicosAdicionaisTOSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private List<ServicosAdicionaisTO> carregarServicosAdicionaisTODependentes(ServicosAdicionaisTO servicosAdicionaisTO) {
        StringBuilder hql = new StringBuilder("select distinct sc2 ");
        hql.append("from ServicoServico ss ");
        hql.append("join ss.servico1.sistemaServico.solicitacaoComercialList sc1 ");
        hql.append("join ss.servico2.sistemaServico.solicitacaoComercialList sc2 ");
        hql.append("where ss.tipoRelacionamento.sgTipoRelacionamento = 'DE' ");
        hql.append("and sc1.idSolicitacaoComercial = :idSolicitacaoComercial ");
        hql.append("and sc1.operacaoComercial.idOperacaoComercial = sc2.operacaoComercial.idOperacaoComercial ");
        hql.append("and sc1.tipoSolicitacaoComercial.idTipoSolicitacaoComercial = sc2.tipoSolicitacaoComercial.idTipoSolicitacaoComercial ");
        return executeQueryAdicionais(servicosAdicionaisTO, hql);
    }
    
    private List<ServicosAdicionaisTO> carregarServicosAdicionaisTOObrigatoriedadeAlta(ServicosAdicionaisTO servicosAdicionaisTO) {
        StringBuilder hql = new StringBuilder("select distinct sc1 ");
        hql.append("from ServicoServico ss ");
        hql.append("join ss.servico1.sistemaServico.solicitacaoComercialList sc1 ");
        hql.append("join ss.servico2.sistemaServico.solicitacaoComercialList sc2 ");
        hql.append("where ss.tipoRelacionamento.sgTipoRelacionamento = 'OA' ");
        hql.append("and sc2.idSolicitacaoComercial = :idSolicitacaoComercial ");
        hql.append("and sc2.operacaoComercial.idOperacaoComercial = sc1.operacaoComercial.idOperacaoComercial ");
        hql.append("and sc2.tipoSolicitacaoComercial.idTipoSolicitacaoComercial = sc1.tipoSolicitacaoComercial.idTipoSolicitacaoComercial ");
        return executeQueryAdicionais(servicosAdicionaisTO, hql);
    }    

    @SuppressWarnings("unchecked")
    private List<ServicosAdicionaisTO> executeQueryAdicionais(ServicosAdicionaisTO servicosAdicionaisTO, StringBuilder hql) {
        Query createQuery = em.createQuery(hql.toString());
        createQuery.setParameter("idSolicitacaoComercial", servicosAdicionaisTO.getIdSolicitacaoComercial());
        List<SolicitacaoComercial> solicitacaoComercialList = createQuery.getResultList();
        List<ServicosAdicionaisTO> servicosAdicionaisTOList = new ArrayList<ServicosAdicionaisTO>();
        ServicosAdicionaisTOBuilder servicosAdicionaisTOBuilder = new ServicosAdicionaisTOBuilder();
        for(SolicitacaoComercial solicitacaoComercial : solicitacaoComercialList) {
            servicosAdicionaisTOList.add(servicosAdicionaisTOBuilder.buildDependenteTO(servicosAdicionaisTO, solicitacaoComercial));
        }
        return servicosAdicionaisTOList;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<TipoServicoTO> loadTipoServico() throws DAOException {
        try {
            return new TipoServicoTOBuilder().createTOList(em.createNamedQuery("TipoServico.loadTipoServicos").getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ServicosAdicionaisTO> findServicosByIdTipoServico(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException {
        try {
            return new ServicosAdicionaisTOBuilder().buildTOServicoList(em.createNamedQuery("Servico.findServicoByIdTipoServico").setParameter(
                    "idTipoServico", servicosAdicionaisTO.getIdTipoServico()).getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException {
        try {
            return new SolicitacaoComercialFixaTOBuilder().buildBasicTOList(em.createNamedQuery("SolicitacaoComercial.carregarSolicitacaoComercialPorServicoList")
                    .setParameter("idServicoFixa", servicosAdicionaisTO.getIdServico()).getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ServicosAdicionaisTO> recordSolicitacaoComercial(OfertaFixaAdicional ofertaFixaAdicional) throws DAOException {
        try {
            log.debug("Gravando o servico adicional: " + ofertaFixaAdicional.toString());
            em.persist(ofertaFixaAdicional);
            em.flush();
            em.refresh(ofertaFixaAdicional);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        log.debug("Retornando lista atualizada de servicos adicionais.");
        return new ServicosAdicionaisTOBuilder().buildTOList(em.createNamedQuery("OfertaFixaAdicional.search").getResultList());
    }

    public List<ServicosAdicionaisTO> deleteSolicitacaoComercial(Integer idOfertaFixaAdicional) throws DAOException {
        try {
            log.debug("Deletando o servico adicional: " + idOfertaFixaAdicional);
            em.remove(em.find(OfertaFixaAdicional.class, idOfertaFixaAdicional));
        } catch (Exception e) {
            throw new DAOException(e);
        }
        log.debug("Retornando lista atualizada de servicos adicionais.");
        return this.search();
    }

    public Boolean existsSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException {
        Long count = 1L;
        Boolean exists;
        try {
            log.debug("Verificando o servico adicional: " + servicosAdicionaisTO.toString());
            StringBuilder hql = new StringBuilder("select count(ofa) from OfertaFixaAdicional ofa where ");
            Map<String, Object> params = new HashMap<String, Object>();

            // Adiciona os parâmetros
            if (servicosAdicionaisTO.getIdSolicitacaoComercial() != null) {
                hql.append(" ofa.solicitacaoComercial.idSolicitacaoComercial =:idSolicitacaoComercial ");
                params.put("idSolicitacaoComercial", servicosAdicionaisTO.getIdSolicitacaoComercial());
            }

            count = (Long) (this.getQuery(hql.toString(), params).getSingleResult());

        } catch (Exception e) {
            throw new DAOException(e);
        }
        log.debug("Retornando resultado se existe servico adicional.");

        exists = (count == 0 ? false : true);
        log.debug("Existe servico: " + exists);
        return exists;
    }
    
    public ServicosAdicionaisTO load(Integer idServicosAdicionais) throws DAOException {
        try {
            return new ServicosAdicionaisTOBuilder().buildTO(em.find(OfertaFixaAdicional.class, idServicosAdicionais));
        } catch (Exception e) {
            throw new DAOException(String.format("Erro ao carregar servico adicional com id %s", idServicosAdicionais));
        }
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
