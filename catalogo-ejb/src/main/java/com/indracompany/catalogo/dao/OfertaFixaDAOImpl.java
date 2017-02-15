package com.indracompany.catalogo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.OfertaFixaDAO;
import com.indracompany.catalogo.datalayer.AreaRegistro;
import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.Cidade;
import com.indracompany.catalogo.datalayer.Localidade;
import com.indracompany.catalogo.datalayer.Ofertafixa;
import com.indracompany.catalogo.datalayer.OfertafixaComplementar;
import com.indracompany.catalogo.datalayer.OfertafixaFatorAreaRegistro;
import com.indracompany.catalogo.datalayer.OfertafixaFatorLocalidade;
import com.indracompany.catalogo.datalayer.OfertafixaPolitica;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.datalayer.TipoServico;
import com.indracompany.catalogo.datalayer.Ofertafixa.InStatusConfiguracao;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AgrupadorCNLTO;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaPoliticaTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.UfTO;

@Stateless
public class OfertaFixaDAOImpl implements OfertaFixaDAO {

    private static Logger log = Logger.getLogger(OfertaFixaDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> searchServicoLinha() throws DAOException {
        log.debug("inicio");
        List<ServicoFixaTO> servicoLinhaList;
        try {
            servicoLinhaList = new ServicoFixaTOBuilder().buildBasicTOList(this.em.createNamedQuery("Servico.searchServicoLinha").getResultList());
        } catch (Exception e) {
            String msg = "erro ao consultar servico linha";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(servicoLinhaList);
        return servicoLinhaList;
    }

    @SuppressWarnings("unchecked")
    public List<SolicitacaoComercialFixaTO> carregarSolicitacaoComercialPorServicoList(Integer idServicoFixa) throws DAOException {
        log.debug("inicio");
        List<SolicitacaoComercialFixaTO> solicitacaoComercialNormalList;
        try {
            solicitacaoComercialNormalList = new SolicitacaoComercialFixaTOBuilder().buildBasicTOList(em.createNamedQuery("SolicitacaoComercial.carregarSolicitacaoComercialPorServicoList").setParameter("idServicoFixa", idServicoFixa).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao consultar solicitacao comercial para servico %s", idServicoFixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(solicitacaoComercialNormalList);
        return solicitacaoComercialNormalList;
    }

    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> carregarServicoFixaTOPlanoList(Integer idServicoFixa) throws DAOException {
        log.debug("inicio");
        List<ServicoFixaTO> servicoFixaTOPlanoList;
        try {
            servicoFixaTOPlanoList = new ServicoFixaTOBuilder().buildBasicTOList(this.em.createNamedQuery("Servico.carregarServicoFixaPlanoList").setParameter("idServicoFixa", idServicoFixa).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao consultar plano servico para servico %s", idServicoFixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(servicoFixaTOPlanoList);
        return servicoFixaTOPlanoList;
    }

    @SuppressWarnings("unchecked")
    public List<OfertafixaTO> search(OfertafixaTO ofertafixaTO) throws DAOException {
        log.debug(ofertafixaTO);
        List<OfertafixaTO> ofertafixaTOList;
        try {
            StringBuilder hql = new StringBuilder("select o from Ofertafixa o where o.idOfertafixa is not null ");
            Map<String, Object> params = new HashMap<String, Object>();

            if (!StringUtils.isBlank(ofertafixaTO.getCdOfertafixa())) {
                hql.append(" and o.cdOfertafixa like :cdOfertafixa ");
                params.put("cdOfertafixa", ofertafixaTO.getCdOfertafixa());
            }
            if (!StringUtils.isBlank(ofertafixaTO.getDsOfertafixa())) {
                hql.append(" and lower(o.dsOfertafixa) like lower(:dsOfertafixa) ");
                params.put("dsOfertafixa", String.format("%s%s%s", "%", ofertafixaTO.getDsOfertafixa(), "%"));
            }
            if (!StringUtils.isBlank(ofertafixaTO.getInConvergenteCobre()) || !StringUtils.isBlank(ofertafixaTO.getInConvergenteFibra())
                || !StringUtils.isBlank(ofertafixaTO.getInFWT()) || !StringUtils.isBlank(ofertafixaTO.getInPortab())
                || !StringUtils.isBlank(ofertafixaTO.getInSpeedySoloCobre()) || !StringUtils.isBlank(ofertafixaTO.getInSpeedySoloFibra())
                || !StringUtils.isBlank(ofertafixaTO.getInBasePontual()) || !StringUtils.isBlank(ofertafixaTO.getInOfertaClienteInadimplente())
                || !StringUtils.isBlank(ofertafixaTO.getInFATB())) {
                hql.append(" and ( ");
                StringBuilder hqlToOr = new StringBuilder();
                if (!StringUtils.isBlank(ofertafixaTO.getInConvergenteCobre())) {
                    hqlToOr.append(" or o.inConvergenteCobre like :inConvergenteCobre ");
                    params.put("inConvergenteCobre", ofertafixaTO.getInConvergenteCobre());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInConvergenteFibra())) {
                    hqlToOr.append(" or o.inConvergenteFibra like :inConvergenteFibra ");
                    params.put("inConvergenteFibra", ofertafixaTO.getInConvergenteFibra());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInFWT())) {
                    hqlToOr.append(" or o.inFWT like :inFWT ");
                    params.put("inFWT", ofertafixaTO.getInFWT());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInPortab())) {
                    hqlToOr.append(" or o.inPortab like :inPortab ");
                    params.put("inPortab", ofertafixaTO.getInPortab());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInSpeedySoloCobre())) {
                    hqlToOr.append(" or o.inSpeedySoloCobre like :inSpeedySoloCobre ");
                    params.put("inSpeedySoloCobre", ofertafixaTO.getInSpeedySoloCobre());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInSpeedySoloFibra())) {
                    hqlToOr.append(" or o.inSpeedySoloFibra like :inSpeedySoloFibra ");
                    params.put("inSpeedySoloFibra", ofertafixaTO.getInSpeedySoloFibra());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInBasePontual())) {
                	hqlToOr.append(" or o.inBasePontual like :inBasePontual ");
                	params.put("inBasePontual", ofertafixaTO.getInBasePontual());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInOfertaClienteInadimplente())) {
                	hqlToOr.append(" or o.inOfertaClienteInadimplente like :inOfertaClienteInadimplente ");
                	params.put("inOfertaClienteInadimplente", ofertafixaTO.getInOfertaClienteInadimplente());
                }
                if (!StringUtils.isBlank(ofertafixaTO.getInFATB())) {
                	hqlToOr.append(" or o.inFATB like :inFATB ");
                	params.put("inFATB", ofertafixaTO.getInFATB());
                }
                hql.append(hqlToOr.toString().replaceFirst("or ", ""));
                hql.append(") ");
            }
            if (!StringUtils.isBlank(ofertafixaTO.getStatus())) {
                if (ofertafixaTO.getStatus().equals("naoiniciado")) {
                    hql.append("and o.dtInicial > trunc(sysdate) ");
                } else if (ofertafixaTO.getStatus().equals("finalizado")) {
                    hql.append("and o.dtFinal < trunc(sysdate) ");
                } else { /* vigente */
                    hql.append("and o.dtInicial <= trunc(sysdate) and (o.dtFinal >= trunc(sysdate) or o.dtFinal is null) ");
                }
            }
            if (ofertafixaTO.getDtInicial() != null) {
                hql.append("and o.dtInicial <= trunc(:dtInicial) and (o.dtFinal >= trunc(:dtFinalInicio) or o.dtFinal is null) ");
                params.put("dtInicial", ofertafixaTO.getDtInicial());
                params.put("dtFinalInicio", ofertafixaTO.getDtInicial());
            }
            if (ofertafixaTO.getDtFinal() != null) {
                hql.append("and o.dtInicial <= trunc(:dtInicialFim) and (o.dtFinal >= trunc(:dtFinal) or o.dtFinal is null) ");
                params.put("dtInicialFim", ofertafixaTO.getDtFinal());
                params.put("dtFinal", ofertafixaTO.getDtFinal());
            }
            if (ofertafixaTO.getServicoOfertaFixaTOPlano() != null && ofertafixaTO.getServicoOfertaFixaTOPlano().getId() != null) {
                hql.append(" and o.solicitacaoComercialPlano.sistemaServico.servico.idServico = :idServicoPlano ");
                params.put("idServicoPlano", ofertafixaTO.getServicoOfertaFixaTOPlano().getId());
            }
            if (ofertafixaTO.getServicoOfertaFixaTOLinha() != null && ofertafixaTO.getServicoOfertaFixaTOLinha().getId() != null) {
                hql.append(" and o.solicitacaoComercialLinha.sistemaServico.servico.idServico = :idServicoLinha ");
                params.put("idServicoLinha", ofertafixaTO.getServicoOfertaFixaTOLinha().getId());
            }
            if (ofertafixaTO.getSolicitacaoComercialFixaTOPlano() != null
                && ofertafixaTO.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial() != null) {
                hql.append(" and o.solicitacaoComercialPlano.idSolicitacaoComercial = :idSolicitacaoComercialPlano");
                params.put("idSolicitacaoComercialPlano", ofertafixaTO.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial());
            }
            if (ofertafixaTO.getSolicitacaoComercialFixaTOLinha() != null
                && ofertafixaTO.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial() != null) {
                hql.append(" and o.solicitacaoComercialLinha.idSolicitacaoComercial = :idSolicitacaoComercialLinha");
                params.put("idSolicitacaoComercialLinha", ofertafixaTO.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial());
            }
            if (ofertafixaTO.getSolicitacaoComercialFixaTOPreCad() != null
                && ofertafixaTO.getSolicitacaoComercialFixaTOPreCad().getIdSolicitacaoComercial() != null) {
                hql.append(" and o.solicitacaoComercialPreCad.idSolicitacaoComercial = :idSolicitacaoComercialPreCad");
                params.put("idSolicitacaoComercialPreCad", ofertafixaTO.getSolicitacaoComercialFixaTOPreCad().getIdSolicitacaoComercial());
            }
            if (!StringUtils.isBlank(ofertafixaTO.getInStatusConfiguracao())) {
                hql.append(" and o.inStatusConfiguracao = :inStatusConfiguracao");
                params.put("inStatusConfiguracao", ofertafixaTO.getInStatusConfiguracao());
            }
            if (StringUtils.isNotEmpty(ofertafixaTO.getPsServicoPlano())){
                hql.append(" and o.solicitacaoComercialPlano.sistemaServico.complementoLegado.cdPS = :psServicoPlano");
                params.put("psServicoPlano", ofertafixaTO.getPsServicoPlano().toString());           	
            }
            if (StringUtils.isNotEmpty(ofertafixaTO.getPsServivoLinha())){
            	hql.append(" and o.solicitacaoComercialLinha.sistemaServico.complementoLegado.cdPS = :psServivoLinha");
                params.put("psServivoLinha", ofertafixaTO.getPsServivoLinha().toString());            	
            }   
            if (StringUtils.isNotEmpty(ofertafixaTO.getOpSolicitacaoComercialNormal())){
            	hql.append(" and o.solicitacaoComercialLinha.operacaoComercial.cdOperacaoComercial = :opSolicitacaoComercialNormal");
                params.put("opSolicitacaoComercialNormal", ofertafixaTO.getOpSolicitacaoComercialNormal().toString());
            }
            if (StringUtils.isNotEmpty(ofertafixaTO.getOpSolicitacaoComercialPlano())){
                hql.append(" and o.solicitacaoComercialPlano.operacaoComercial.cdOperacaoComercial = :opSolicitacaoComercialPlano");
                params.put("opSolicitacaoComercialPlano", ofertafixaTO.getOpSolicitacaoComercialPlano().toString());            	
            } 
            if (StringUtils.isNotEmpty(ofertafixaTO.getOpSolicitacaoComercialPreCadastro())){
                hql.append(" and o.solicitacaoComercialPreCad.operacaoComercial.cdOperacaoComercial = :opSolicitacaoComercialPreCad");
                params.put("opSolicitacaoComercialPreCad", ofertafixaTO.getOpSolicitacaoComercialPreCadastro().toString());            	
            }           
            hql.append(" order by o.dsOfertafixa");           
            log.debug("search hql: " + hql);
            ofertafixaTOList = new OfertafixaTOBuilder().buildTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao consultar oferta para os filtros informados %s", ofertafixaTO);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(ofertafixaTOList);
        return ofertafixaTOList;
    }

    public OfertafixaTO gravar(OfertafixaTO ofertafixaTO, OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO) throws DAOException {
        log.debug(ofertafixaTO);
        OfertafixaTO ofertafixaTOResult;
        try {
            Ofertafixa entity;
            if (ofertafixaTO.getIdOfertafixa() != null) {
                entity = em.find(Ofertafixa.class, ofertafixaTO.getIdOfertafixa());
                entity.setDtUltimaAlteracao(new Date());
                entity.setInStatusConfiguracao(InStatusConfiguracao.Configurado);
            } else {
            	BigDecimal idOfertafixa = (BigDecimal) em.createNativeQuery("select catalogoprs_ow.ofertafixa_sq.nextval from dual").getResultList().iterator().next();
            	OfertafixaPolitica ofp = (OfertafixaPolitica) em.createNamedQuery("OfertafixaPolitica.findId")
						.setParameter("inAreaRegistro", "N")
						.setParameter("inCanalVenda", "N")
						.setParameter("inEPS", "N")
						.setParameter("inLocalidade", "N")
						.getResultList().get(0);
            	entity = new Ofertafixa(idOfertafixa.intValue(), ofp, new Date(), InStatusConfiguracao.Configurado, ofertafixaTO.getNmUsuarioCriacao());
            }
            OfertafixaTOBuilder ofertafixaTOBuilder = new OfertafixaTOBuilder();
            entity = em.merge(ofertafixaTOBuilder.buildEntityRecordable(entity, ofertafixaTO));
            em.flush();
            em.refresh(entity);
            this.atualizacaoFWT(entity);
            em.flush();
            em.refresh(entity);
            
            if(ofertafixaTO.getOfertafixaComplementarTOList().size() > 0){
            	this.gravarComplementar(ofertafixaTO.getOfertafixaComplementarTOList(), entity);
            	em.flush();
            	em.refresh(entity);
            }

            if(ofertafixaDisponibilidadeTO.getIdAreaRegistroList().size() > 0 ||ofertafixaDisponibilidadeTO.getAgrupadorCNLTOList().size() > 0){
                this.gravarDisponibilidade(ofertafixaDisponibilidadeTO, entity);
                em.flush();
                em.refresh(entity);
            }
            ofertafixaTOResult = ofertafixaTOBuilder.buildTO(entity);
        } catch (Exception e) {
            String msg = String.format("erro ao gravar oferta %s", ofertafixaTO);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(ofertafixaTOResult);
        return ofertafixaTOResult;
    }

    private void atualizacaoFWT(Ofertafixa entity) {
        if ("N".equals(entity.getInFWT())) {
            List<OfertafixaComplementar> ofertafixaComplementarListRemove = new ArrayList<OfertafixaComplementar>();
            for (OfertafixaComplementar ofertafiaComplementar : entity.getOfertafixaComplementarList()) {
                if (ofertafiaComplementar.getSolicitacaoComercial().getSistemaServico().getServico().getTipoServico().getCdTipoServico().equalsIgnoreCase("22")) {
                    ofertafixaComplementarListRemove.add(ofertafiaComplementar);
                }
            }
            for (OfertafixaComplementar ofertafiaComplementar : ofertafixaComplementarListRemove) {
                em.remove(ofertafiaComplementar);
            }
        }
    }

    private void gravarDisponibilidade(OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO, Ofertafixa entity) throws DAOException {    	
        if (ofertafixaDisponibilidadeTO != null) {
            //this.gravarOfertafixaFatorCanalVenda(entity, ofertafixaDisponibilidadeTO.getIdCanalVendaList());
            this.gravarOfertafixaFatorAreaRegistro(entity, ofertafixaDisponibilidadeTO.getIdAreaRegistroList());
            this.gravarOfertafixaFatorLocalidade(entity, ofertafixaDisponibilidadeTO.getAgrupadorCNLTOList());
            this.atualizarOfertafixaPolitica(entity);
        }
    }

    @SuppressWarnings("unchecked")
    private void gravarOfertafixaFatorLocalidade(Ofertafixa ofertafixa, List<AgrupadorCNLTO> agrupadorCNLTOList) throws DAOException {
        log.debug(String.format("gravando ofertafixa fator localidade para ofertafixa = %s com %s agrupadores de localidade", ofertafixa.getIdOfertafixa(), agrupadorCNLTOList.size()));
        try {
            final List<Long> idLocalidadeList = this.extrairIdLocalidadeList(agrupadorCNLTOList);
            
            final List<Long> idLocalidadeListRemove = new ArrayList<Long>(idLocalidadeList.size());
            CollectionUtils.forAllDo(ofertafixa.getOfertafixaFatorLocalidadeList(), new Closure() {
                public void execute(Object obj) {
                    OfertafixaFatorLocalidade offl = (OfertafixaFatorLocalidade) obj;
                    if (idLocalidadeList.contains(offl.getLocalidade().getIdLocalidade())){
                        idLocalidadeListRemove.add(offl.getLocalidade().getIdLocalidade());
                    } else {
                        em.remove(offl);
                    }
                }
            });
            List<Long> idLocalidadeListPersist = new ArrayList<Long>(CollectionUtils.subtract(idLocalidadeList, idLocalidadeListRemove));
            em.flush();
            Long qtdeLocalidade = (Long) em.createNamedQuery("Localidade.countAll").getSingleResult();
            if (qtdeLocalidade.intValue() > idLocalidadeListPersist.size()) {
                for (Long idLocalidade : idLocalidadeListPersist) {
                    Localidade localidade = em.find(Localidade.class, idLocalidade);
                    em.persist(new OfertafixaFatorLocalidade(ofertafixa, localidade));
                }
            }
            em.flush();
        } catch (Exception e) {
            String msg = String.format("erro ao gravar ofertafixa fator localidade para ofertafixa = %s com %s agrupadores de localidades", ofertafixa.getIdOfertafixa(), agrupadorCNLTOList.size());
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        
    }

    private List<Long> extrairIdLocalidadeList(List<AgrupadorCNLTO> agrupadorCNLTOList) {
        List<Long> idLocalidadeList = new ArrayList<Long>();
        for(AgrupadorCNLTO agrupadorCNLTO : agrupadorCNLTOList) {
            for (LocalidadeTO localidadeTO : agrupadorCNLTO.getLocalidadeTOList()) {
                idLocalidadeList.add(localidadeTO.getIdLocalidade());
            }
        }
        return idLocalidadeList;
    }

    public OfertafixaTO findByCodigoExceptId(String cdOfertafixa, Integer idOfertafixa) throws DAOException {
        log.debug(String.format("cdOfertafixa: %s, idOfertafixa: %s", cdOfertafixa, idOfertafixa));
        OfertafixaTO ofertafixaTO;
        try {
            if (idOfertafixa == null) {
                ofertafixaTO = new OfertafixaTOBuilder().buildBasicTO((Ofertafixa) em.createNamedQuery("Ofertafixa.findByCodigo").setParameter("cdOfertafixa", cdOfertafixa).getSingleResult());
            } else {
                ofertafixaTO = new OfertafixaTOBuilder().buildBasicTO((Ofertafixa) em.createNamedQuery("Ofertafixa.findByCodigoExceptId").setParameter("cdOfertafixa", cdOfertafixa).setParameter("idOfertafixa", idOfertafixa).getSingleResult());
            }
        } catch (NoResultException e) {
            ofertafixaTO = null;
        } catch (Exception e) {
            String msg = String.format("erro ao consultar oferta por cdOfertafixa %s exceto pelo idOfertafixa %s", cdOfertafixa, idOfertafixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(ofertafixaTO);
        return ofertafixaTO;
    }

    @SuppressWarnings("unchecked")
    public OfertafixaTO findByNameExceptId(String nmOfertafixa, Integer idOfertafixa) throws DAOException {
        log.debug(String.format("nmOfertafixa: %s, idOfertafixa: %s", nmOfertafixa, idOfertafixa));
        OfertafixaTO ofertafixaTO = null;
        try {
            List<Ofertafixa> ofertafixaList;
            if (idOfertafixa == null) {
                ofertafixaList = em.createNamedQuery("Ofertafixa.findByName").setParameter("dsOfertafixa", nmOfertafixa).getResultList();
                if (ofertafixaList.size() > 0) {
                    ofertafixaTO = new OfertafixaTOBuilder().buildBasicTO(ofertafixaList.iterator().next());
                }
            } else {
                ofertafixaList = em.createNamedQuery("Ofertafixa.findByNameExceptId").setParameter("dsOfertafixa", nmOfertafixa).setParameter("idOfertafixa", idOfertafixa).getResultList();
                if (ofertafixaList.size() > 0) {
                    ofertafixaTO = new OfertafixaTOBuilder().buildBasicTO(ofertafixaList.iterator().next());
                }
            }
        } catch (Exception e) {
            String msg = String.format("erro ao consultar oferta por nmOfertafixa %s exceto pelo idOfertafixa %s", nmOfertafixa, idOfertafixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(ofertafixaTO);
        return ofertafixaTO;
    }

    public void excluir(Integer idOfertafixa) throws DAOException {
        log.debug(String.format("idOfertafixa: %s", idOfertafixa));
        try {
            em.remove(em.find(Ofertafixa.class, idOfertafixa));
        } catch (Exception e) {
            String msg = String.format("erro ao excluir oferta com idOfertafixa %s ", idOfertafixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
    }

    public OfertafixaTO findByIdWithComplementar(Integer idOfertafixa) throws DAOException {
        log.debug(idOfertafixa);
        OfertafixaTO ofertafixaTO;
        try {
            ofertafixaTO = new OfertafixaTOBuilder().buildTOWithComplementar(em.find(Ofertafixa.class, idOfertafixa));
            List<OfertafixaComplementarTO> ofertafixaComplementarTOList = ofertafixaTO.getOfertafixaComplementarTOList();
            Set<OfertafixaComplementarTO> ofertafixaComplementarTOSet = new LinkedHashSet<OfertafixaComplementarTO>();
            for (OfertafixaComplementarTO ofertafixaComplementarTO : ofertafixaComplementarTOList) {
                ofertafixaComplementarTOSet.add(ofertafixaComplementarTO);
                ofertafixaComplementarTOSet.addAll(this.carregarOfertafixaComplementarTODependentes(ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial(), ofertafixaComplementarTO.getPzMaximoVigencia()));
                ofertafixaComplementarTOSet.addAll(this.carregarOfertafixaComplementarTOObrigatoriedadeAlta(ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial(), ofertafixaComplementarTO.getPzMaximoVigencia()));
            }
            ofertafixaTO.setOfertafixaComplementarTOList(ofertafixaComplementarTOSet);
        } catch (Exception e) {
            String msg = String.format("erro ao buscar oferta pelo id: %s", idOfertafixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ofertafixaTO;
    }

    private List<OfertafixaComplementarTO> carregarOfertafixaComplementarTODependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) {
        StringBuilder hql = new StringBuilder("select distinct sc2 ");
        hql.append("from ServicoServico ss ");
        hql.append("join ss.servico1.sistemaServico.solicitacaoComercialList sc1 ");
        hql.append("join ss.servico2.sistemaServico.solicitacaoComercialList sc2 ");
        hql.append("where ss.tipoRelacionamento.sgTipoRelacionamento = 'DE' ");
        hql.append("and sc1.idSolicitacaoComercial = :idSolicitacaoComercial ");
        hql.append("and sc1.operacaoComercial.idOperacaoComercial = sc2.operacaoComercial.idOperacaoComercial ");
        hql.append("and sc1.tipoSolicitacaoComercial.idTipoSolicitacaoComercial = sc2.tipoSolicitacaoComercial.idTipoSolicitacaoComercial ");
        /*validacao para desconto*/
        hql.append("and ((ss.servico2.tipoServico.cdTipoServico = 4 ");
        hql.append("and exists (select ss2.idServicoServico from ServicoServico ss2 where ((ss2.tipoRelacionamento.sgTipoRelacionamento = 'DC' and ss.servico2.espServicoDesconto.sgTipoAplicacaoDesconto in ('P','F')) or (ss2.tipoRelacionamento.sgTipoRelacionamento = 'DT' and ss.servico2.espServicoDesconto.sgTipoAplicacaoDescontoAperiod in ('P','F'))))) ");
        hql.append("or (ss.servico2.tipoServico.cdTipoServico <> 4)) ");
        return executeQuerySolCom(idSolicitacaoComercial, pzMaximoVigencia, hql);
    }
    
    private List<OfertafixaComplementarTO> carregarOfertafixaComplementarTOObrigatoriedadeAlta(Long idSolicitacaoComercial, Integer pzMaximoVigencia) {
        StringBuilder hql = new StringBuilder("select distinct sc1 from ServicoServico ss ");
        hql.append("join ss.servico1.sistemaServico.solicitacaoComercialList sc1 ");
        hql.append("join ss.servico2.sistemaServico.solicitacaoComercialList sc2 ");
        hql.append("where ss.tipoRelacionamento.sgTipoRelacionamento = 'OA' ");
        hql.append("and sc2.idSolicitacaoComercial = :idSolicitacaoComercial ");
        hql.append("and sc2.operacaoComercial.idOperacaoComercial = sc1.operacaoComercial.idOperacaoComercial ");
        hql.append("and sc2.tipoSolicitacaoComercial.idTipoSolicitacaoComercial = sc1.tipoSolicitacaoComercial.idTipoSolicitacaoComercial ");        
        return executeQuerySolCom(idSolicitacaoComercial, pzMaximoVigencia, hql);        
    }

    @SuppressWarnings("unchecked")
    private List<OfertafixaComplementarTO> executeQuerySolCom(Long idSolicitacaoComercial, Integer pzMaximoVigencia, StringBuilder hql) {
        List<SolicitacaoComercialFixaTO> solicitacaoComercialFixaTOList = new SolicitacaoComercialFixaTOBuilder().buildBasicTOList(em.createQuery(hql.toString()).setParameter("idSolicitacaoComercial", idSolicitacaoComercial).getResultList());
        List<OfertafixaComplementarTO> ofertafixaComplementarTOList = new ArrayList<OfertafixaComplementarTO>();
        for(SolicitacaoComercialFixaTO solicitacaoComercialFixaTO : solicitacaoComercialFixaTOList) {
            ofertafixaComplementarTOList.add(new OfertafixaComplementarTO(solicitacaoComercialFixaTO, pzMaximoVigencia, true, idSolicitacaoComercial));
        }
        return ofertafixaComplementarTOList;
    }

    @SuppressWarnings("unchecked")
    public List<TipoServicoTO> carregarTipoServicoTOList() throws DAOException {
        List<TipoServicoTO> tipoServicoTOList;
        try {
            tipoServicoTOList = new TipoServicoTOBuilder().createTOList(em.createNamedQuery("TipoServico.carregarTipoServicoParaOfertafixa").getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao carregar tipo servico");
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return tipoServicoTOList;
    }

    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico, OfertafixaTO ofertafixaTO) throws DAOException {
        log.debug(String.format("idTipoServico=%s", idTipoServico));
        List<ServicoFixaTO> servicoFixaTOList;
        try {
            TipoServico ts = em.find(TipoServico.class, idTipoServico);
            StringBuilder hql = new StringBuilder("select distinct s.idServico, s.nmComercial, cl.cdPS ");
            hql.append("from ServicoServico ss ");
            hql.append("join ss.servico1 s ");
            hql.append("join s.sistemaServico sis ");
            hql.append("join sis.complementoLegado cl ");
            hql.append("where s.inFixa = 'S' ");
            hql.append("and s.inDisponivel = 'S' ");
            hql.append("and s.tipoServico.inFixa = 'S' ");
            hql.append("and s.tipoServico.idTipoServico = :idTipoServico ");
            hql.append("and s.idServico in (select vc.idServicoFilho from VwFixaClasseLinha vc where vc.idServicoPai = :idServicoLinha) ");
            hql.append("and s.idServico not in (select vg.idServicoFilho from VwFixaGenerica vg where vg.sgTipoRelacionamento in ('IX', 'IN') and vg.idServicoPai in (:idServicoLinha, :idServicoPlano)) ");
            if (ts.getCdTipoServico().equals("4")) {
                hql.append("and ((ss.tipoRelacionamento.sgTipoRelacionamento = 'DC' and s.espServicoDesconto.sgTipoAplicacaoDesconto in ('P','F')) or (ss.tipoRelacionamento.sgTipoRelacionamento = 'DT' and s.espServicoDesconto.sgTipoAplicacaoDescontoAperiod in ('P','F')))");
            }
            if ("S".equals(ofertafixaTO.getInFWT())) {
                hql.append("and s.idServico not in (select distinct ss2.servico1.idServico ");
                hql.append("from ServicoServico ss2 ");
                hql.append("where ss2.tipoRelacionamento.sgTipoRelacionamento in ('IX','IN') ");
                hql.append("and ss2.servico2.tipoServico.cdTipoServico = 22 ");
                hql.append("and s.idServico in (ss2.servico1.idServico, ss2.servico2.idServico)) ");
            }
            hql.append("order by s.nmComercial");
            System.out.println("hql:" + hql);
            	
            Map<String, Object> params = new HashMap<String, Object>();
            SolicitacaoComercial scLinha = em.find(SolicitacaoComercial.class, ofertafixaTO.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial());
            params.put("idServicoLinha", scLinha.getSistemaServico().getServico().getIdServico());
            SolicitacaoComercial scPlano = em.find(SolicitacaoComercial.class, ofertafixaTO.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial());
            Integer idServicoPlano = scPlano.getSistemaServico().getServico().getIdServico();
            params.put("idServicoPlano", idServicoPlano);
            params.put("idTipoServico", idTipoServico);            

            servicoFixaTOList = new ServicoFixaTOBuilder().getBasicTOList(this.getQuery(hql.toString(), params).getResultList());
            Set<Servico> servicoIncompativel = new LinkedHashSet<Servico>();
            for (OfertafixaComplementarTO ofcTO : ofertafixaTO.getOfertafixaComplementarTOList()) {
                Query qry = em.createQuery("select s " +
                        "from VwFixaClasseLinha vfg, Servico s " +
                        "inner join s.sistemaServico.solicitacaoComercialList sc " +
                        "where vfg.sgTipoRelacionamento in ('IN', 'IX') " +
                        "and vfg.idServicoPai = s.idServico " +
                        "and sc.idSolicitacaoComercial = :idSolicitacaoComercial");
                qry.setParameter("idSolicitacaoComercial", ofcTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial());
                System.out.println("idSolicitacaoComercial " + ofcTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial().toString());
                servicoIncompativel.addAll(qry.getResultList());
            }
            for (Servico s : servicoIncompativel) {
                log.debug(s.getIdServico());
                servicoFixaTOList.remove(new ServicoFixaTO(s.getIdServico()));
            }
        } catch (Exception e) {
            String msg = String.format("erro ao buscar sercico tipo %s", idTipoServico);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(servicoFixaTOList);
        return servicoFixaTOList;
    }

    @SuppressWarnings("unchecked")
    private void gravarComplementar(List<OfertafixaComplementarTO> ofertafixaComplementarTOList, Ofertafixa ofertafixa) throws DAOException {
        log.debug(String.format("ofertafixaComplementarTOList: ", ofertafixaComplementarTOList));
        try {
            for(OfertafixaComplementarTO ofertafixaComplementarTO : ofertafixaComplementarTOList) {
                if (ofertafixaComplementarTO.isDependente()) {
                    continue;
                }
                List<OfertafixaComplementar> ofertafixaComplementarList = em.createNamedQuery("OfertafixaComplementar.findByIdOfertaIdSolicitacaoComercial").setParameter("idOfertafixa", ofertafixa.getIdOfertafixa()).setParameter("idSolicitacaoComercial", ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial()).getResultList();
                OfertafixaComplementar ofertafixaComplementar;
                if (ofertafixaComplementarList.size() > 0) {
                    ofertafixaComplementar = ofertafixaComplementarList.iterator().next();
                } else {
                    ofertafixaComplementar = new OfertafixaComplementar();
                    ofertafixaComplementar.setOfertafixa(ofertafixa);
                    ofertafixaComplementar.setSolicitacaoComercial(new SolicitacaoComercial(ofertafixaComplementarTO.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial()));
                }
                ofertafixaComplementar.setPzMaximoVigencia(ofertafixaComplementarTO.getPzMaximoVigencia());
                if (!ofertafixaComplementarTO.isParaExcluir()) {
                    em.merge(ofertafixaComplementar);
                } else if (ofertafixaComplementar.getIdOfertafixaComplementar() != null) {
                    em.remove(ofertafixaComplementar);
                }
                em.flush();
            }
        } catch (Exception e) {
            String msg = String.format("erro ao gravar ofertas complementares: %s ", ofertafixaComplementarTOList);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
    }

    public void excluirOfertaComplementar(Integer idOfertafixaComplementar) throws DAOException {
        log.debug(String.format("idOfertafixaComplementar: %s", idOfertafixaComplementar));
        try {
            em.remove(em.find(OfertafixaComplementar.class, idOfertafixaComplementar));
        } catch (Exception e) {
            String msg = String.format("erro ao excluir oferta com idOfertafixaComplementar %s ", idOfertafixaComplementar);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
    }

    @SuppressWarnings("unchecked")
    public OfertafixaDisponibilidadeTO carregarDisponibilidadeTO(Integer idOfertafixa) throws DAOException {
        log.debug(String.format("idOfertafixa:%s", idOfertafixa));
        OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO = new OfertafixaDisponibilidadeTO();
        try {
        	Ofertafixa ofertafixa = new Ofertafixa();
        	if (idOfertafixa != null) {
        		ofertafixa = em.find(Ofertafixa.class, idOfertafixa);
                ofertafixaDisponibilidadeTO.setCanalVendaTOList(new CanalVendaTOBuilder().createTOList(em.createNamedQuery("CanalVenda.findByIdOfertafixa").setParameter("idOfertafixa", idOfertafixa).getResultList()));
                ofertafixaDisponibilidadeTO.setAreaRegistroTOList(new AreaRegistroTOBuilder().createTOList(em.createNamedQuery("AreaRegistro.findByIdOfertafixa").setParameter("idOfertafixa", idOfertafixa).getResultList()));
        	} 
            ofertafixaDisponibilidadeTO.setAgrupadorCNLTOList(this.obterAgrupadorCNLTO(ofertafixa));
        } catch (Exception e) {
            String msg = String.format("erro carregar disponibilidade para ofertafixa %s ", idOfertafixa);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ofertafixaDisponibilidadeTO;
    }
    
    /*@SuppressWarnings("unchecked")
    private void gravarOfertafixaFatorCanalVenda(Ofertafixa ofertafixa, final List<Long> idCanalVendaList) throws DAOException{
        log.debug(String.format("idOfertafixa:%s idCanalVendaList:%s", ofertafixa.getIdOfertafixa(), idCanalVendaList));
        try {
            final List<Long> idCanalVendaListRemove = new ArrayList<Long>(idCanalVendaList.size());
            CollectionUtils.forAllDo(ofertafixa.getOfertafixaFatorCanalVendaList(), new Closure() {
                public void execute(Object obj) {
                    OfertafixaFatorCanalVenda offcv = (OfertafixaFatorCanalVenda) obj;
                    if (idCanalVendaList.contains(offcv.getCanalVenda().getIdCanalVenda())) {
                        idCanalVendaListRemove.add(offcv.getCanalVenda().getIdCanalVenda());
                    } else {
                        em.remove(offcv);
                    }
                }
            });
            List<Long> idCanalVendaListPersist = new ArrayList<Long>(CollectionUtils.subtract(idCanalVendaList, idCanalVendaListRemove));
            em.flush();
            Long qtdeCanalVenda = (Long) em.createNamedQuery("CanalVenda.countAll").getSingleResult();
            if (qtdeCanalVenda.intValue() > idCanalVendaListPersist.size()) {
                for(Long idCanalVenda : idCanalVendaListPersist) {
                    CanalVenda canalVenda = em.find(CanalVenda.class, idCanalVenda);
                    em.persist(new OfertafixaFatorCanalVenda(ofertafixa, canalVenda)); 
                }
            }
            em.flush();
        } catch (Exception e) {
            String msg = String.format("erro ao gravar fator canal venda para oferta %s com canais venda %s ", ofertafixa.getIdOfertafixa(), idCanalVendaList);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
    }*/
    
    @SuppressWarnings("unchecked")
    private void gravarOfertafixaFatorAreaRegistro(Ofertafixa ofertafixa, final List<Integer> idAreaRegistroList) throws DAOException {
        log.debug(String.format("idOfertafixa:%s idAreaRegistroList:%s", ofertafixa.getIdOfertafixa(), idAreaRegistroList));
        try {
            final List<Integer> idAreaRegistroListRemove = new ArrayList<Integer>(idAreaRegistroList.size());
            CollectionUtils.forAllDo(ofertafixa.getOfertafixaFatorAreaRegistroList(), new Closure() {
                public void execute(Object obj) {
                    OfertafixaFatorAreaRegistro offar = (OfertafixaFatorAreaRegistro) obj;
                    if (idAreaRegistroList.contains(offar.getAreaRegistro().getIdarearegistro())) {
                        idAreaRegistroListRemove.add(offar.getAreaRegistro().getIdarearegistro());
                    } else {
                        em.remove(offar);
                    }
                }
            });
            List<Integer> idAreaRegistroListPersist = new ArrayList<Integer>(CollectionUtils.subtract(idAreaRegistroList, idAreaRegistroListRemove));
            em.flush();
            Long qtdeAreaRegistro = (Long) em.createNamedQuery("AreaRegistro.countAll").getSingleResult();
            if (qtdeAreaRegistro.intValue() > idAreaRegistroListPersist.size()) {
                for(Integer idAreaRegistro : idAreaRegistroListPersist) {
                    AreaRegistro areaRegistro = em.find(AreaRegistro.class, idAreaRegistro);
                    em.persist(new OfertafixaFatorAreaRegistro(ofertafixa, areaRegistro)); 
                }
            }
            em.flush();
        } catch (Exception e) {
            String msg = String.format("erro ao gravar fator area registro para oferta %s com areas registro %s ", ofertafixa.getIdOfertafixa(), idAreaRegistroList);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }      
    }
    
    @SuppressWarnings("unchecked")
    public List<UfTO> obterUfTOList() throws DAOException {
        log.debug(String.format("obterUfTOList"));
        List<UfTO> ufTOList;
        try {
            ufTOList = new UfTOBuilder().createTOList(em.createNamedQuery("Uf.findAll").getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter UFs");
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ufTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws DAOException{
        log.debug(String.format("idUf:%s", idUf));
        List<AreaRegistroTO> areaRegistroTOList;
        try {
            areaRegistroTOList = new AreaRegistroTOBuilder().createTOList(em.createNamedQuery("AreaRegistro.findByIdUf").setParameter("idUf", idUf).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter areas registro para uf %s", idUf);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return areaRegistroTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws DAOException {
        log.debug(String.format("idAreaRegistro:%s", idAreaRegistro));
        List<CidadeTO> cidadeTOList;
        try {
            cidadeTOList = new CidadeTOBuilder().buildBasicTOList(em.createNamedQuery("Cidade.findByIdAreaRegistro").setParameter("idAreaRegistro", idAreaRegistro).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter areas registro para idAreaRegistro %s", idAreaRegistro);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return cidadeTOList;        
    }
    
    @SuppressWarnings("unchecked")
    public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws DAOException {
        log.debug(String.format("idCidade:%s", idCidade));
        List<LocalidadeTO> localidadeTOList;
        try {
            localidadeTOList = new LocalidadeTOBuilder().buildTOList(em.createNamedQuery("Localidade.findByIdCidade").setParameter("idCidade", idCidade).getResultList());
        } catch (Exception e) {
            String msg = String.format("erro ao obter localidades para cidade %s ", idCidade);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }        
        return localidadeTOList;
    }
    
    public List<OfertafixaTO> reload(List<OfertafixaTO> ofertafixaTOList) throws DAOException {
        log.debug(String.format("ofertafixaTOList:%s", ofertafixaTOList));
        List<OfertafixaTO> ofertafixaTOListReloaded = new ArrayList<OfertafixaTO>();
        try {
            OfertafixaTOBuilder ofertafixaTOBuilder = new OfertafixaTOBuilder();
            for(OfertafixaTO ofertafixaTO : ofertafixaTOList) {
                ofertafixaTOListReloaded.add(ofertafixaTOBuilder.buildTO(em.find(Ofertafixa.class, ofertafixaTO.getIdOfertafixa())));
            }
        } catch (Exception e) {
            String msg = String.format("erro ao recarregar ofertas %s ", ofertafixaTOList);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ofertafixaTOListReloaded;
    }
    
    public SolicitacaoComercialFixaTO findSolicitacaoComercialById(Long idSolicitacaoComercial) throws DAOException {
        log.debug(String.format("idSolicitacaoComercial:%s", idSolicitacaoComercial));
        try {
        	return new SolicitacaoComercialFixaTOBuilder().buildBasicTO(em.find(SolicitacaoComercial.class, idSolicitacaoComercial));
        } catch (Exception e) {
            String msg = String.format("erro ao carregar solicitacao comercial com id=%s ", idSolicitacaoComercial);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }    	
    }
    
    public CidadeTO findCidadeById(Integer idCidade) throws DAOException {
        log.debug(String.format("idCidade:%s", idCidade));
        try {
        	return new CidadeTOBuilder().buildTO(em.find(Cidade.class, idCidade));
        } catch (Exception e) {
            String msg = String.format("erro ao carregar cidade com id=%s ", idCidade);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }    	
    }
    
    @SuppressWarnings("unchecked")
    public List<EpsTO> listEpsTO() throws DAOException {
        log.debug("init");
        try {
            return new EpsTOBuilder().createTOList(em.createNamedQuery("Eps.findAll").getResultList());
        } catch (Exception e) {
            String msg = "Erro ao listar EpsTO";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws DAOException {
        log.debug(String.format("canalVendaTO:%s", canalVendaTO));
        try {
            StringBuilder hql = new StringBuilder("select c from CanalVenda c where c.idCanalVenda is not null ");
            Map<String, Object> params = new HashMap<String, Object>();
            if (canalVendaTO.getEpsTO() != null) {
                if (canalVendaTO.getEpsTO().getIdEps().equals(0)) {
                    hql.append("and c.eps.idEps is null ");
                } else {
                    hql.append("and c.eps.idEps = :idEps ");
                    params.put("idEps", canalVendaTO.getEpsTO().getIdEps());
                }
            }
            if (StringUtils.isNotBlank(canalVendaTO.getCdCanalVenda())) {
                hql.append("and c.cdCanalVenda = :cdCanalVenda");
                params.put("cdCanalVenda", canalVendaTO.getCdCanalVenda());
            }
            return new CanalVendaTOBuilder().createTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            String msg = "Erro ao buscar canal venda";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws DAOException {
        log.debug(String.format("areaRegistroTO:%s", areaRegistroTO));
        try {
            StringBuilder hql = new StringBuilder("select a from AreaRegistro a where a.idarearegistro is not null ");
            Map<String, Object> params = new HashMap<String, Object>();
            if (areaRegistroTO.getUfTO() != null) {
                hql.append("and a.uf.idUf = :idUf ");
                params.put("idUf", areaRegistroTO.getUfTO().getIdUf());
            }
            if (!StringUtils.isBlank(areaRegistroTO.getCodigoArea())) {
                hql.append("and a.codigoArea = :codigoArea");
                params.put("codigoArea", areaRegistroTO.getCodigoArea());
            }
            return new AreaRegistroTOBuilder().createTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            String msg = "Erro ao buscar canal venda";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }        
    }

    public List<OfertafixaComplementarTO> findOfertafixaComplementarTOWithDependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) throws DAOException {
        log.debug("");
        Set<OfertafixaComplementarTO> ofertafixaComplementarTOSet = new LinkedHashSet<OfertafixaComplementarTO>();
        try {
            ofertafixaComplementarTOSet.add(new OfertafixaComplementarTO(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(em.find(SolicitacaoComercial.class, idSolicitacaoComercial)), pzMaximoVigencia));
            ofertafixaComplementarTOSet.addAll(this.carregarOfertafixaComplementarTODependentes(idSolicitacaoComercial, pzMaximoVigencia));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        return new ArrayList<OfertafixaComplementarTO>(ofertafixaComplementarTOSet);
    }
    
    @SuppressWarnings("unchecked")
	public List<Long> carregarIdCanalVendaListCompativel(Long idSolicitacaoComercial) throws DAOException {
    	log.debug(String.format("idSolicitacaoComercial:%s", idSolicitacaoComercial));
    	List<Long> idCanalVendaListCompativel = new ArrayList<Long>(); 
    	try {
			String hql = "select new java.lang.Long(cvsc.canalVenda.idCanalVenda) from CanalVendaSolicitacaoComercial cvsc where cvsc.inDisponivel = 'S' and cvsc.solicitacaoComercial.idSolicitacaoComercial = :idSolicitacaoComercial";
			idCanalVendaListCompativel.addAll(em.createQuery(hql).setParameter("idSolicitacaoComercial", idSolicitacaoComercial).getResultList());
		} catch (Exception e) {
            log.error(e.getMessage());
            throw new DAOException(e.getMessage());
		}
    	
    	return idCanalVendaListCompativel;
    }

    public CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws DAOException {
        log.debug(idCanalVenda);
        CanalVendaTO canalVendaTO;
        try {
            canalVendaTO = new CanalVendaTOBuilder().createTO(em.find(CanalVenda.class, idCanalVenda));
        } catch (Exception e) {
            String msg = String.format("erro ao buscar canal venda pelo id: %s", idCanalVenda);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return canalVendaTO;
    }
    
    public AreaRegistroTO findAreaRegistroTOById(Integer idAreaRegistro) throws DAOException {
        log.debug(idAreaRegistro);
        AreaRegistroTO areaRegistroTO;
        try {
            areaRegistroTO = new AreaRegistroTOBuilder().createTO(em.find(AreaRegistro.class, idAreaRegistro));
        } catch (Exception e) {
            String msg = String.format("erro ao buscar area registro pelo id: %s", idAreaRegistro);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return areaRegistroTO;
    }

    private void atualizarOfertafixaPolitica(Ofertafixa ofertafixa) {
        em.refresh(ofertafixa);
        Map<String, Object> params = new HashMap<String, Object>();
        
        OfertafixaPoliticaTO ofertafixaPoliticaTOId = new OfertafixaPoliticaTOBuilder().buildTO((OfertafixaPolitica) em.createNamedQuery("OfertafixaPolitica.findById").setParameter("idOfertafixaPolitica", ofertafixa.getOfertafixaPolitica().getIdOfertafixaPolitica()).getSingleResult());
        
        params.put("inAreaRegistro", ofertafixa.getOfertafixaFatorAreaRegistroList() != null && ofertafixa.getOfertafixaFatorAreaRegistroList().size() > 0 ? "S" : "N");


        params.put("inLocalidade", ofertafixa.getOfertafixaFatorLocalidadeList() != null && ofertafixa.getOfertafixaFatorLocalidadeList().size() > 0 ? "S" : "N");
        params.put("inCanalVenda", ofertafixaPoliticaTOId.getInCanalVenda());
        params.put("inEPS", ofertafixaPoliticaTOId.getInEPS());
        String hql = "select op from OfertafixaPolitica op where op.inAreaRegistro = :inAreaRegistro and op.inCanalVenda = :inCanalVenda and op.inEPS = :inEPS and op.inLocalidade = :inLocalidade";
        ofertafixa.setOfertafixaPolitica((OfertafixaPolitica) this.getQuery(hql, params).getSingleResult());
        em.merge(ofertafixa);
    }

    @SuppressWarnings("unchecked")
    private List<AgrupadorCNLTO> obterAgrupadorCNLTO(Ofertafixa ofertafixa) {
        List<AgrupadorCNLTO> agrupadorCNLTOList = null;
        if (ofertafixa != null && ofertafixa.getOfertafixaFatorLocalidadeList() != null && ofertafixa.getOfertafixaFatorLocalidadeList().size() > 0) {
            List<LocalidadeTO> localidadeTOList = new LocalidadeTOBuilder().extractTOList(ofertafixa.getOfertafixaFatorLocalidadeList());

            Set<AgrupadorCNLTO> agrupadorCNLTOSet = new TreeSet<AgrupadorCNLTO>();
            for (LocalidadeTO localidadeTO : localidadeTOList) {
                if (localidadeTO.getCidadeTO() != null) {
                    agrupadorCNLTOSet.add(
                            new AgrupadorCNLTO(
                                    localidadeTO.getCidadeTO().getNmCidade(), 
                                    localidadeTO.getCidadeTO().getAreaRegistroTO().getCodigoArea(), 
                                    localidadeTO.getCidadeTO().getAreaRegistroTO().getUfTO().getNmUf()
                            ));
                }
            }
            agrupadorCNLTOList = new ArrayList<AgrupadorCNLTO>();
            for (final AgrupadorCNLTO agrupadorCNLTO : agrupadorCNLTOSet) {
                agrupadorCNLTO.setLocalidadeTOList(CollectionUtils.select(localidadeTOList, new Predicate() {
                    public boolean evaluate(Object obj) {
                        LocalidadeTO localidadeTO = (LocalidadeTO)obj;
                        if (localidadeTO.getCidadeTO() != null) {
                            return new AgrupadorCNLTO(
                                    localidadeTO.getCidadeTO().getNmCidade(),
                                    localidadeTO.getCidadeTO().getAreaRegistroTO().getCodigoArea(), 
                                    localidadeTO.getCidadeTO().getAreaRegistroTO().getUfTO().getNmUf()
                                ).equals(agrupadorCNLTO);
                        }                        
                        return false;
                    }
                }));
                agrupadorCNLTOList.add(agrupadorCNLTO);
            }
        }

        return agrupadorCNLTOList;
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