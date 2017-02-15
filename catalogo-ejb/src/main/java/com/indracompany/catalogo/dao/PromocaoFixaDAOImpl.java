package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.indracompany.catalogo.dao.interfaces.PromocaoFixaDAO;
import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.ComposicaoPromocao;
import com.indracompany.catalogo.datalayer.PoliticaDispPromocao;
import com.indracompany.catalogo.datalayer.Promocao;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.datalayer.ValorPoliticaPromocao;
import com.indracompany.catalogo.datalayer.Promocao.InStatusConfiguracao;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

@Stateless
public class PromocaoFixaDAOImpl implements PromocaoFixaDAO {

    private static Logger log = Logger.getLogger(PromocaoFixaDAOImpl.class);
    
	//@Resource
	//private UserTransaction ut;
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    public List<PromocaoTO> search(PromocaoTO promocaoTO) throws DAOException {
        StringBuilder hql = new StringBuilder("select distinct ps from Promocao ps ");
        hql.append("left join ps.composicaoPromocaoList cp ");
        hql.append("where ps.idPromocao is not null ");
        
        Map<String,Object> params = new HashMap<String, Object>();
        if(!StringUtils.isBlank(promocaoTO.getCdPromocao())) {
            hql.append("and ps.cdPromocao like :cdPromocao ");
            params.put("cdPromocao", promocaoTO.getCdPromocao());
        }
        
        if (!StringUtils.isBlank(promocaoTO.getNmPromocao())) {
            hql.append("and lower(ps.nmPromocao) like lower(:nmPromocao) ");
            params.put("nmPromocao", String.format("%s%s%s", "%", promocaoTO.getNmPromocao(), "%"));            
        }
        
        if (!StringUtils.isBlank(promocaoTO.getNmServico())) {
            hql.append("and lower(cp.servico.nmComercial) like lower(:nmServico) ");
            params.put("nmServico", String.format("%s%s%s", "%", promocaoTO.getNmServico(), "%"));
        }
        
        if (!StringUtils.isBlank(promocaoTO.getStatus())) {
            if (promocaoTO.getStatus().equals("naoiniciado")) {
                hql.append("and ps.dtInicio > trunc(sysdate) ");
            } else if (promocaoTO.getStatus().equals("finalizado")) {
                hql.append("and ps.dtFim < trunc(sysdate) ");
            } else { /*vigente*/
                hql.append("and ps.dtInicio <= trunc(sysdate) and (ps.dtFim >= trunc(sysdate) or ps.dtFim is null) ");
            }
        }
        if (promocaoTO.getDtInicio() != null) {
            hql.append("and trunc(ps.dtInicio) >= trunc(:dtInicio)");
            params.put("dtInicio", promocaoTO.getDtInicio());
        }
         
        if (promocaoTO.getDtFim() != null) {
            hql.append("and (trunc(ps.dtFim) <= trunc(:dtFim) or ps.dtFim is null) ");
            params.put("dtFim", promocaoTO.getDtFim());
        }
        
        if (!StringUtils.isBlank(promocaoTO.getInStatusConfiguracao())) {
            hql.append(" and ps.inStatusConfiguracao = :inStatusConfiguracao");
            params.put("inStatusConfiguracao", promocaoTO.getInStatusConfiguracao());
        }

        try {
            return new PromocaoTOBuilder().buildTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar promocao", e);
        }
    }
    
    public PromocaoTO merge(PromocaoTO promocaoTO) throws DAOException {
        try {
            Promocao entity;
            if (promocaoTO.getIdPromocao() != null) {
                entity = em.find(Promocao.class, promocaoTO.getIdPromocao());
                entity.setInStatusConfiguracao(InStatusConfiguracao.Configurado);
            } else {
                entity = new Promocao(InStatusConfiguracao.Configurado);
            }
            PromocaoTOBuilder promocaoTOBuilder = new PromocaoTOBuilder();
            entity = promocaoTOBuilder.updateAttachedEntity(promocaoTO, entity);
            return promocaoTOBuilder.buildTO(em.merge(entity));
        } catch (Exception e) {
            throw new DAOException("Erro ao gravar promocao", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ComposicaoPromocaoTO> findComposicaoByIdPromocao(Integer idPromocao) throws DAOException {
        String hql = "select cp.idComposicaoPromocao, cp.servico.nmComercial, cp.solicitacaoComercial.sistemaServico.servico.nmComercial, cp.solicitacaoComercial.nmSolicitacaoComercial, cp.pzValidadeDesconto, cp.servico.idServico from ComposicaoPromocao cp where cp.promocao.idPromocao = :idPromocao";
        try {
            return new ComposicaoPromocaoTOBuilder().buildTOList(em.createQuery(hql).setParameter("idPromocao", idPromocao).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar composicoes da promocao", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoServicoTO> findTipoServicoTO() throws DAOException {
        String hql = "select ts from TipoServico ts where ts.inFixa = 'S' order by ts.nmTipoServico";
        try {
            return new TipoServicoTOBuilder().createTOList(em.createQuery(hql).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar composicoes da promocao", e);
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico) throws DAOException {
        
        StringBuilder hql = new StringBuilder(" select distinct s.sistemaServico.sistema.idSistema, s.sistemaServico.sistema.nmSistema, s.cdServico, s.sistemaServico.complementoLegado.cdPS, s.nmComercial, s.inDisponivel, s.idServico, s.tipoServico.idTipoServico, s.tipoServico.nmTipoServico, s.descricao, s.categoria.cdCategoria, s.tipoServico.cdTipoServico ");
        hql.append(" from Servico s, ServicoServico sscl, ServicoServico ssdc ");
        hql.append(" where s.idServico = sscl.servico2.idServico ");
        hql.append(" and s.idServico = ssdc.servico2.idServico ");
        hql.append(" and sscl.tipoRelacionamento.idTipoRelacionamento = #relacionamentoCL# "); /*392*/
        hql.append(" and sscl.inFixa like 'S' ");
        hql.append(" and sscl.inDisponivel like 'S' ");                
        hql.append(" and ssdc.tipoRelacionamento.idTipoRelacionamento = #relacionamentoDC# "); /*395*/
        hql.append(" and ssdc.inFixa like 'S' ");
        hql.append(" and ssdc.inDisponivel like 'S' ");
        hql.append(" and s.inFixa like 'S' ");
        hql.append(" and s.inDisponivel like 'S' ");
        hql.append(" and s.tipoServico.idTipoServico = :idTipoServico ");
        hql.append(" order by s.nmComercial ");
        String idRelacionamentoCL = ((Integer) em.createQuery("select tr.idTipoRelacionamento from TipoRelacionamento tr where tr.sgTipoRelacionamento like 'CL'").getSingleResult()).toString();
        String idRelacionamentoDC = ((Integer) em.createQuery("select tr.idTipoRelacionamento from TipoRelacionamento tr where tr.sgTipoRelacionamento like 'DC'").getSingleResult()).toString();
        String hqlStr = hql.toString().replaceAll("#relacionamentoCL#", idRelacionamentoCL ).replaceAll("#relacionamentoDC#", idRelacionamentoDC);
        try {
            List<ServicoFixaTO> servicoFixaTOList = new ServicoFixaTOBuilder().getTOList(em.createQuery(hqlStr).setParameter("idTipoServico", idTipoServico).getResultList());
            return servicoFixaTOList;
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar servico para promocao", e);
        }  
    }
 
    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> findServicoDescontoByIdServico(Integer idServico) throws DAOException {
        StringBuilder hql = new StringBuilder(" select distinct s.sistemaServico.sistema.idSistema, s.sistemaServico.sistema.nmSistema, s.cdServico, s.sistemaServico.complementoLegado.cdPS, s.nmComercial, s.inDisponivel, s.idServico, s.tipoServico.idTipoServico, s.tipoServico.nmTipoServico, s.descricao ");
        hql.append(" from Servico s, ServicoServico ss ");
        hql.append(" where s.idServico = ss.servico1.idServico ");
        hql.append(" and ss.servico2.idServico = :idServico ");
        hql.append(" and ss.tipoRelacionamento.idTipoRelacionamento = :idTipoRelacionamento "); /*395*/
        hql.append(" and ss.inFixa like 'S' ");
        hql.append(" and ss.inDisponivel like 'S' ");
        hql.append(" and s.inFixa like 'S' ");
        hql.append(" and s.inDisponivel like 'S' ");
        hql.append(" order by s.nmComercial ");
        try {
        	Integer idTipoRelacionamento = (Integer) em.createQuery("select tr.idTipoRelacionamento from TipoRelacionamento tr where tr.sgTipoRelacionamento = 'DC' ").getSingleResult();
            List<ServicoFixaTO> servicoFixaTOList = new ServicoFixaTOBuilder().getTOList(em.createQuery(hql.toString())
            		.setParameter("idServico", idServico).setParameter("idTipoRelacionamento", idTipoRelacionamento)
            		.getResultList());
            return servicoFixaTOList;
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar servico para promocao", e);
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercialByIdServico(Integer idServico) throws DAOException {
        try {
            List<SolicitacaoComercial> resultList = em.createNamedQuery("SolicitacaoComercial.carregarSolicitacaoComercialPorServicoList").setParameter("idServicoFixa", idServico).getResultList();
            List<SolicitacaoComercialFixaTO> solicitacaoComercialTOList = new SolicitacaoComercialFixaTOBuilder().buildBasicTOList(resultList);
            return solicitacaoComercialTOList;
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar servico para promocao", e);
        }        
    }
    
    public PromocaoTO findByNome(String nome) throws DAOException {
        String hql = "select p from Promocao p where lower(p.nmPromocao) = lower(:nmPromocao)";
        try {
            return new PromocaoTOBuilder().buildTO((Promocao) em.createQuery(hql).setParameter("nmPromocao", nome).getResultList().iterator().next());
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar por codigo", e);
        }        
    }
    
    public void removeComposicaoById(Integer idComposicao) throws DAOException {
    	log.debug("removeComposicaoById("+idComposicao+")");
        try {
            em.remove(em.find(ComposicaoPromocao.class, idComposicao));
        } catch (Exception e) {
            throw new DAOException("Erro ao remover composicao: removeComposicaoById("+idComposicao+")", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public boolean persist(ComposicaoPromocaoTO composicaoPromocaoTO) throws DAOException {
        boolean dataPersisted = false;
        String hql = "select cp from ComposicaoPromocao cp where cp.servico.idServico = :idServico and cp.promocao.idPromocao = :idPromocao and cp.solicitacaoComercial.idSolicitacaoComercial = :idSolicitacaoComercial";
        try {
            List<ComposicaoPromocao> entityList = em.createQuery(hql)
                .setParameter("idServico", composicaoPromocaoTO.getIdServico())
                .setParameter("idPromocao", composicaoPromocaoTO.getIdPromocao())
                .setParameter("idSolicitacaoComercial", composicaoPromocaoTO.getIdSolicitacao())
                .getResultList();
            if (entityList.size() == 0) {
                em.merge(new ComposicaoPromocaoTOBuilder().buildEntity(composicaoPromocaoTO));
                dataPersisted = true;
            }
        } catch (Exception e) {
            String string = "Erro ao gravar composicao promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
        return dataPersisted;
    }
    
    @SuppressWarnings("unchecked")
    public List<DisponibilidadePromocaoFixaTO> obterCanalVendaPorPromocao(Integer idPromocao) throws DAOException {
        StringBuilder hql = new StringBuilder(" select vpp.idValorPoliticaPromocao, e.nmEps, cv.idCanalVenda, cv.nmCanalVenda, e.idEps, cv.cdCanalVenda ");
        hql.append(" from Eps e ");
        hql.append(" inner join e.canalVendaList cv ");
        hql.append(" left join cv.valorPoliticaPromocaoList vpp ");
        hql.append(" where vpp.promocao.idPromocao = :idPromocao ");
        //hql.append(" or vpp.promocao.idPromocao is null ");
        try {
            List<Object[]> resultList = em.createQuery(hql.toString()).setParameter("idPromocao", idPromocao).getResultList();
            List<DisponibilidadePromocaoFixaTO> toList = new DisponibilidadePromocaoFixaTOBuilder().buildTOList(resultList);
            return toList;
        } catch (Exception e) {
            String string = "Erro obter canais venda da promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }
    
    public void removeDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws DAOException {
        try {
        	//ut.begin();
            //Promocao promocao1 = em.find(Promocao.class, valorPoliticaPromocaoTOListPersist.iterator().next().getIdPromocao());
            if (valorPoliticaPromocaoTOListPersist.size() > 0) {
                Promocao promocao = em.find(Promocao.class, valorPoliticaPromocaoTOListPersist.iterator().next().getIdPromocao());
                PoliticaDispPromocao politicaDispPromocao = new PoliticaDispPromocao(promocao.getIdPromocao(), "S");
                em.merge(politicaDispPromocao);
                em.flush();
            }
            
            //List<ValorPoliticaPromocao> vp1 = em.createQuery("select vp from ValorPoliticaPromocao vp where vp.promocao.idPromocao = :idPromocao ")
            //.setParameter("idPromocao", promocao1.getIdPromocao()).getResultList();
            //System.out.println(valorPoliticaPromocaoTOListRemove.toString());
            System.out.println("remove:");
            System.out.println("DELETE VALORPOLITICAPROMOCAO WHERE IDVALORPOLITICAPROMOCAO IN (");

            int i = 0;
            for (ValorPoliticaPromocaoTO valorPoliticaPromocaoTO : valorPoliticaPromocaoTOListRemove) {
            	ValorPoliticaPromocao valorPoliticaPromocao = em.find(ValorPoliticaPromocao.class, valorPoliticaPromocaoTO.getIdValorPoliticaPromocao());
            	//System.out.println("to.IdValorPoliticaPromocao"+valorPoliticaPromocaoTO.getIdValorPoliticaPromocao()+" IdPromocao "+valorPoliticaPromocao.getPromocao().getIdPromocao()+ "idCanal "+valorPoliticaPromocao.getCanalVenda().getIdCanalVenda()+" IdValorPoliticaPromocao "+valorPoliticaPromocao.getIdValorPoliticaPromocao());
                em.remove(valorPoliticaPromocao);
                em.flush();
                //System.out.println("SELECT "+valorPoliticaPromocaoTO.getIdPromocao()+" IDPROMOCAO, "+valorPoliticaPromocaoTO.getIdCanalVenda()+" IDCANALVENDA FROM DUAL UNION ALL");
                //System.out.println("DELETE FROM VALORPOLITICAPROMOCAO VP WHERE VP.IDPROMOCAO = "+valorPoliticaPromocaoTO.getIdPromocao()+" AND VP.IDCANALVENDA = "+valorPoliticaPromocaoTO.getIdCanalVenda()+" ;");
                System.out.println(",'"+valorPoliticaPromocaoTO.getIdValorPoliticaPromocao()+"'");
                i++;
            }
            System.out.println(");");
            System.out.println("i total:"+i);
            em.flush();
            
            /*
            List<ValorPoliticaPromocao> vp2 = em.createQuery("select vp from ValorPoliticaPromocao vp where vp.promocao.idPromocao = :idPromocao ")
            .setParameter("idPromocao", promocao1.getIdPromocao()).getResultList();
            
            System.out.println(valorPoliticaPromocaoTOListPersist.toString());
            System.out.println("persist:");
            for (ValorPoliticaPromocaoTO valorPoliticaPromocaoTO : valorPoliticaPromocaoTOListPersist) {
                em.persist(new ValorPoliticaPromocao(valorPoliticaPromocaoTO.getIdPromocao(), valorPoliticaPromocaoTO.getIdCanalVenda()));
                System.out.println("SELECT "+valorPoliticaPromocaoTO.getIdPromocao()+" IDPROMOCAO, "+valorPoliticaPromocaoTO.getIdCanalVenda()+" IDCANALVENDA FROM DUAL UNION ALL");
            }          
            //ut.commit();
            System.out.println("persist end");
            */
        } catch (Exception e) {
            String string = "Erro obter canais venda da promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }
    
    public void persistDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws DAOException {
        try {
            //Promocao promocao1 = em.find(Promocao.class, valorPoliticaPromocaoTOListPersist.iterator().next().getIdPromocao());
            
            if (valorPoliticaPromocaoTOListPersist.size() > 0) {
                Promocao promocao = em.find(Promocao.class, valorPoliticaPromocaoTOListPersist.iterator().next().getIdPromocao());
                PoliticaDispPromocao politicaDispPromocao = new PoliticaDispPromocao(promocao.getIdPromocao(), "S");
                em.merge(politicaDispPromocao);
                em.flush();
            }
            
            for (ValorPoliticaPromocaoTO valorPoliticaPromocaoTO : valorPoliticaPromocaoTOListRemove) {
            	ValorPoliticaPromocao valorPoliticaPromocao = em.find(ValorPoliticaPromocao.class, valorPoliticaPromocaoTO.getIdValorPoliticaPromocao());
                em.remove(valorPoliticaPromocao);
            }
            em.flush();
            for (ValorPoliticaPromocaoTO valorPoliticaPromocaoTO : valorPoliticaPromocaoTOListPersist) {
                em.persist(new ValorPoliticaPromocao(valorPoliticaPromocaoTO.getIdPromocao(), valorPoliticaPromocaoTO.getIdCanalVenda()));
            }
        } catch (Exception e) {
            String string = "Erro obter canais venda da promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }
    
    public void remove(Integer idPromocao) throws DAOException {
        try {
            Promocao promocao = em.find(Promocao.class, idPromocao);
            Date hoje = DateTime.now().toDateMidnight().toDate();
            if (promocao == null) {
                log.debug(String.format("o id de promocao informado, nao corresponde com uma ocorrencia no banco de dados: %s", idPromocao));
            } else if (promocao.getDtInicio().compareTo(hoje) <= 0){
                log.debug(String.format("a promocao informada consta com uma data vigente: %s, %s", promocao.getIdPromocao(), DateTimeFormat.forPattern("dd/MM/yyyy").print(promocao.getDtInicio().getTime())));
            } else {
                em.remove(promocao);
            }
        } catch (Exception e) {
            String string = "Erro ao remover promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }
    
    public PromocaoTO findById(Integer idPromocao) throws DAOException {
        try {
            return new PromocaoTOBuilder().buildTO(em.find(Promocao.class, idPromocao));
        } catch (Exception e) {
            String string = "Erro ao remover promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }        
    }
    
    public PromocaoTO findByCodigoExceptId(String codigo, Integer idPromocao) throws DAOException {
        log.debug(String.format("codigo: %s, idPromocao: %s", codigo, idPromocao));
        PromocaoTO promocaoTO;
        try {
            if (idPromocao == null) {
                promocaoTO = new PromocaoTOBuilder().buildTO((Promocao) em.createNamedQuery("Promocao.findByCodigo").setParameter("cdPromocao", codigo).getSingleResult());
            } else {
                promocaoTO = new PromocaoTOBuilder().buildTO((Promocao) em.createNamedQuery("Promocao.findByCodigoExceptId").setParameter("cdPromocao", codigo).setParameter("idPromocao", idPromocao).getSingleResult());
            }
        } catch (NoResultException e) {
            promocaoTO = null;
        } catch (Exception e) {
            String msg = String.format("erro ao consultar promocao por codigo %s exceto pelo idPromocao %s", codigo, idPromocao);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(promocaoTO);
        return promocaoTO;
    }
    
    public PromocaoTO findByNameExceptId(String nome, Integer idPromocao) throws DAOException {
        log.debug(String.format("nome: %s, idPromocao: %s", nome, idPromocao));
        PromocaoTO promocaoTO;
        try {
            if (idPromocao == null) {
                promocaoTO = new PromocaoTOBuilder().buildTO((Promocao) em.createNamedQuery("Promocao.findByName").setParameter("nmPromocao", nome).getSingleResult());
            } else {
                promocaoTO = new PromocaoTOBuilder().buildTO((Promocao) em.createNamedQuery("Promocao.findByNameExceptId").setParameter("nmPromocao", nome).setParameter("idPromocao", idPromocao).getSingleResult());
            }
        } catch (NoResultException e) {
            promocaoTO = null;
        } catch (Exception e) {
            String msg = String.format("erro ao consultar promocao por nome %s exceto pelo idPromocao %s", nome, idPromocao);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(promocaoTO);
        return promocaoTO;
    }
    
    public List<PromocaoTO> reload(List<PromocaoTO> promocaoTOList) throws DAOException {
        log.debug(String.format("promocaoTOList: %s", promocaoTOList));
        List<PromocaoTO> promocaoTOListReload = new ArrayList<PromocaoTO>();
        try {
            PromocaoTOBuilder promocaoTOBuilder = new PromocaoTOBuilder();
            for (PromocaoTO promocaoTO : promocaoTOList) {
                promocaoTOListReload.add(promocaoTOBuilder.buildTO(em.find(Promocao.class, promocaoTO.getIdPromocao())));
            }
        } catch (Exception e) {
            String msg = String.format("erro ao recarregar promocoes", promocaoTOList);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return promocaoTOListReload;        
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
    
    @SuppressWarnings("unchecked")
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda) throws DAOException {
        StringBuilder hql = new StringBuilder(" select vpp.idValorPoliticaPromocao, e.nmEps, cv.idCanalVenda, cv.nmCanalVenda, e.idEps, cv.cdCanalVenda ");
        hql.append(" from Eps e ");
        hql.append(" inner join e.canalVendaList cv ");
        hql.append(" left join cv.valorPoliticaPromocaoList vpp ");
        hql.append(" where cv.idCanalVenda = :idCanalVenda ");
        try {
            List<Object[]> resultList = em.createQuery(hql.toString()).setParameter("idCanalVenda", idCanalVenda).getResultList();
            List<DisponibilidadePromocaoFixaTO> toList = new DisponibilidadePromocaoFixaTOBuilder().buildTOList(resultList);
            return toList;
        } catch (Exception e) {
            String string = "Erro obter canais venda da promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda, Integer idPromocao) throws DAOException {
        StringBuilder hql = new StringBuilder(" select vpp.idValorPoliticaPromocao, e.nmEps, cv.idCanalVenda, cv.nmCanalVenda, e.idEps, cv.cdCanalVenda ");
        hql.append(" from Eps e ");
        hql.append(" inner join e.canalVendaList cv ");
        hql.append(" left join cv.valorPoliticaPromocaoList vpp ");
        hql.append(" left join vpp.promocao p ");
        hql.append(" where cv.idCanalVenda = :idCanalVenda ");
        hql.append(" and (p.idPromocao = :idPromocao or p.idPromocao is null) ");
        try {
        	
            List<Object[]> resultList = em.createQuery(hql.toString()).setParameter("idCanalVenda", idCanalVenda).setParameter("idPromocao", idPromocao).getResultList();
            List<DisponibilidadePromocaoFixaTO> toList = new DisponibilidadePromocaoFixaTOBuilder().buildTOList(resultList);
            return toList;
        } catch (Exception e) {
            String string = "Erro obter canais venda da promocao";
            log.error(string, e);
            throw new DAOException(string, e);
        }
    }
}