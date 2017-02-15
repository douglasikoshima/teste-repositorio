package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.CondicaoPagamentoEncargo;
import com.indracompany.catalogo.datalayer.DisponibilidadeCndcPagamento;
import com.indracompany.catalogo.datalayer.Encargo;
import com.indracompany.catalogo.datalayer.PoliticaDispCndcPagamento;
import com.indracompany.catalogo.datalayer.PoliticaDispSlctComercial;
import com.indracompany.catalogo.datalayer.Segmento;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.ServicoSegmento;
import com.indracompany.catalogo.datalayer.ServicoServico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;
import com.indracompany.catalogo.to.ServicoServicoTO;
import com.indracompany.catalogo.to.SistemaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Stateless
public class ServicoFixaDAOImpl implements ServicoFixaDAO {

    private static Logger log = Logger.getLogger(ServicoFixaDAOImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    @SuppressWarnings("unchecked")
    public List<ServicoFixaTO> search(ServicoFixaTO servicoFixaTO) throws DAOException {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder(
                "select s.sistemaServico.sistema.idSistema, s.sistemaServico.sistema.nmSistema, s.cdServico, s.sistemaServico.complementoLegado.cdPS, s.nmComercial, s.inDisponivel, s.idServico, s.tipoServico.idTipoServico, s.tipoServico.nmTipoServico, s.descricao " +
                "from Servico s " +
                "where s.inFixa = 'S' ");
        if (servicoFixaTO.getIdSistema() != null && servicoFixaTO.getIdSistema().compareTo(0) != 0) {
            params.put("idSistema", servicoFixaTO.getIdSistema());
            hql.append("and s.sistemaServico.sistema.idSistema like :idSistema ");
        }
        
        if (!StringUtils.isBlank(servicoFixaTO.getCdServico())) {
            params.put("cdServico", servicoFixaTO.getCdServico());
            hql.append("and s.cdServico like :cdServico ");
        }
        
        if (!StringUtils.isBlank(servicoFixaTO.getCdPS())) {
            params.put("cdPS", servicoFixaTO.getCdPS());
            hql.append("and s.sistemaServico.complementoLegado.cdPS like :cdPS ");
        }
        
        if (!StringUtils.isBlank(servicoFixaTO.getNomeDoServicoOrigem())) {
            params.put("descricao", String.format("%s%s%s", "%", servicoFixaTO.getNomeDoServicoOrigem(), "%"));
            hql.append("and lower(s.descricao) like lower(:descricao) ");
        }
        
        if(!StringUtils.isBlank(servicoFixaTO.getNmComercial())) {
            params.put("nmComercial", String.format("%s%s%s", "%", servicoFixaTO.getNmComercial(), "%"));
            hql.append("and lower(s.nmComercial) like lower(:nmComercial) ");            
        }
        
        if(!StringUtils.isBlank(servicoFixaTO.getNomeDoServicoOrigem())) {
            params.put("descricao", String.format("%s%s%s", "%", servicoFixaTO.getNomeDoServicoOrigem(), "%"));
            hql.append("and lower(s.descricao) like lower(:descricao) ");            
        }
        
        if(servicoFixaTO.getInDisponivel() != null) {
            params.put("inDisponivel", servicoFixaTO.getInDisponivel()? "S" : "N");
            hql.append("and s.inDisponivel like :inDisponivel ");            
        }
        
        if(servicoFixaTO.getIdTipoServico() != null) {
            params.put("idTipoServico", servicoFixaTO.getIdTipoServico());
            hql.append("and s.tipoServico.idTipoServico like " +
                    ":idTipoServico ");            
        }
        
        List<ServicoFixaTO> toList;
        try {
            toList = new ServicoFixaTOBuilder().getTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [search]",e);
        }
        
        return toList;
    }

	public DetalheServicoFixaTO findDetalheServicoFixaById(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException {
		DetalheServicoFixaTO result = null;
		ServicoFixaTOBuilder builder = new ServicoFixaTOBuilder();
		
		try {
			result = builder.createDetalheServicoFixaTO(findServicoFixaById(detalheServicoFixaTO.getIdServico()));
		} catch (Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}
	
	public void updateDetalheServicoFixa(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException {
		ServicoFixaTOBuilder builder = new ServicoFixaTOBuilder();
		Servico servico = null;
		
		try {
			servico = this.findServicoFixaById(detalheServicoFixaTO.getIdServico());
			builder.updateEntity(servico, detalheServicoFixaTO);
			em.merge(servico);
			
            if (detalheServicoFixaTO.getAlterarPoliticas()) {
    			if(servico.getTipoServico().getCdTipoServico().equals("4")){
    				updateDispSlctComercial(detalheServicoFixaTO);
    			} else {
    				updateDispCndcPagamento(detalheServicoFixaTO);
    			}
            }
			
		} catch(Exception e){
			throw new DAOException(e);
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

    public String changeStatus(Integer id) throws DAOException {
        String novoStatus;
        try {
            Servico s = em.find(Servico.class, id);
            s.setInDisponivel(s.getInDisponivel().equalsIgnoreCase("N") ? "S" : "N");
            novoStatus = s.getInDisponivel();
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [changeStatus]",e);
        }
        return novoStatus;     
    }

    @SuppressWarnings("unchecked")
    public List<RelacionamentoServicoFixaTO> searchRelationship(RelacionamentoServicoFixaTO relacionamentoServicoFixaTO) throws DAOException {
        List<RelacionamentoServicoFixaTO> toList;
        StringBuilder hql = new StringBuilder("select ss.inDisponivel, ss.servico2.cdServico, ss.servico2.nmComercial, ss.servico2.descricao, ss.idServicoServico, ss.inCriacaoCatalogo, ss.servico1.idServico, ss.tipoRelacionamento.idTipoRelacionamento, ss.tipoRelacionamento.dscTipoRelacionamento, ss.tipoRelacionamento.sgTipoRelacionamento from ServicoServico ss where ss.servico1.inFixa = 'S' ");
        Map<String, Object> params = new HashMap<String, Object>();
        if (relacionamentoServicoFixaTO.getIdServicoSearch() != null) {
            hql.append("and ss.servico1.idServico = :idServico1");
            params.put("idServico1", relacionamentoServicoFixaTO.getIdServicoSearch());
        }
        try {
            Query query = this.getQuery(hql.toString(), params);
            List<Object[]> entityList = query.getResultList();
            toList = new RelacionamentoServicoFixaTOBuilder().buildTOList(entityList);
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [searchRelationship]",e);
        }
        return toList;
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoRelacionamentoTO> findAllTpRelacionamento() throws DAOException {
        List<TipoRelacionamentoTO> tipoRelacionamentoTOList;
        try {
            tipoRelacionamentoTOList = new TipoRelacionamentoTOBuilder().buildTOList(em.createQuery("select tr from TipoRelacionamento tr where tr.inFixa = 'S' ").getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findAllTpRelacionamento]",e);
        }
        return tipoRelacionamentoTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoServicoTO> findAllTpServico() throws DAOException {
        List<TipoServicoTO> tipoServicoTOList;
        try {
            tipoServicoTOList = new TipoServicoTOBuilder().createTOList(em.createQuery("select ts from TipoServico ts where ts.inFixa = 'S' order by ts.dscTipoServico ").getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findAllTpServico]",e);
        }
        return tipoServicoTOList;
    }
    
    public RelacionamentoServicoFixaTO findRelationshipById(Integer idRelacionamento) throws DAOException {
        RelacionamentoServicoFixaTO to;
        try {
            to = new RelacionamentoServicoFixaTOBuilder().buildTO(em.find(ServicoServico.class, idRelacionamento));
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findRelationshipById]",e);
        }
        return to;
    }
    
    public String changeStatusRelationship(Integer idRelacionamento) throws DAOException {
        String novoStatus;
        try {
            ServicoServico s = em.find(ServicoServico.class, idRelacionamento);
            s.setInDisponivel(s.getInDisponivel().equalsIgnoreCase("N") ? "S" : "N");
            novoStatus = s.getInDisponivel();
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [changeStatusRelationship]",e);
        }
        return novoStatus;        
    }
    
    public Long countRelationshipByServiceAndType(Integer idServico1, Integer idTipoRelacionamento) throws DAOException {
        Long count;
        try {
            count = (Long) em.createQuery(
                    "select count(ss) from ServicoServico ss " +
                    "where ss.servico1.idServico = :idServico1 " +
                    "and ss.tipoRelacionamento.idTipoRelacionamento = :idTipoRelacionamento")
                    .setParameter("idServico1", idServico1)
                    .setParameter("idTipoRelacionamento", idTipoRelacionamento)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [countRelationshipByServiceAndType]",e);
        }
        return count;        
    }
    
    public void removeRelationship(Integer idRelacionamento) throws DAOException, BusinessException {
        try {
            ServicoServico ss = em.find(ServicoServico.class, idRelacionamento);
            if (ss.getInCriacaoCatalogo().equalsIgnoreCase("N")) {
                throw new BusinessException("Relacionamento criado no legado n&atilde;o pode ser removido.");
            }
            em.remove(ss);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [removeRelationship]",e);
        }
    }
    @SuppressWarnings("unchecked")
    private Servico findServicoFixaById(Integer idServico){
		StringBuilder queryStr = new StringBuilder();
		Servico result = null;
		List<Servico> servicoList = null; 
		
		queryStr.append(" select s from Servico s ");
		queryStr.append(" join s.sistemaServico ss ");
		queryStr.append(" join ss.sistema si ");
		queryStr.append(" where (si.idSistema = 8 or si.idSistema = 9) ");
		queryStr.append(" and s.idServico = :idServico");
		
		Query query = em.createQuery(queryStr.toString());
		query.setParameter("idServico", idServico);
		
		servicoList = (List<Servico>) query.getResultList();
		if(servicoList.isEmpty()){
			result = null;
		} else {
			result = servicoList.get(0);
		}
		
		return result;    
	}
    
    @SuppressWarnings("unchecked")
    private void updateDispSlctComercial(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException{
		List<PoliticaDispSlctComercial> politicaDispSlctComercialList = null;
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select p from PoliticaDispSlctComercial p ");
		queryStr.append(" where p.inAreaConcorrencia = :inAreaConcorrencia ");
		queryStr.append(" and p.inPlanoMinuto = :inPlanoMinuto ");

		Query query1 = em.createQuery(queryStr.toString());
		query1.setParameter("inAreaConcorrencia", detalheServicoFixaTO.getInAreaConcorrenciaDesconto());
		query1.setParameter("inPlanoMinuto", detalheServicoFixaTO.getInPlanoMinutoDesconto());
		
		politicaDispSlctComercialList = (List<PoliticaDispSlctComercial>) query1.getResultList();
		PoliticaDispSlctComercial politicaDispSlctComercial = null;
		if(!politicaDispSlctComercialList.isEmpty()){
			politicaDispSlctComercial = politicaDispSlctComercialList.get(0);
		}

		Servico servico = em.find(Servico.class, detalheServicoFixaTO.getIdServico());
		
		for(SolicitacaoComercial solicitacaoComercial : servico.getSistemaServico().getSolicitacaoComercialList()) {
			solicitacaoComercial.setPoliticaDispSlctComercial(politicaDispSlctComercial);
			for (CanalVendaSolicitacaoComercial cvsc : solicitacaoComercial.getCanalVendaSolicitacaoComercialList()) {
			    cvsc.setAreaConcorrenciaList(null);
                cvsc.setEspServicoPlanoMinutoList(null);
            }
		}
		
		em.merge(servico);
    }
    
    @SuppressWarnings("unchecked")
    private void updateDispCndcPagamento(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException{
		List<PoliticaDispCndcPagamento> politicaDispCndcPagamentoList = null;
		StringBuilder queryStr = new StringBuilder();
		
		queryStr = new StringBuilder();
		queryStr.append(" select p from PoliticaDispCndcPagamento p ");
		queryStr.append(" where p.inAreaConcorrencia = :inAreaConcorrencia ");
		queryStr.append(" and p.inPlanoMinuto = :inPlanoMinuto ");
		
		Query query1 = em.createQuery(queryStr.toString());
		query1.setParameter("inAreaConcorrencia", detalheServicoFixaTO.getInAreaConcorrenciaFinanciamento());
		query1.setParameter("inPlanoMinuto", detalheServicoFixaTO.getInPlanoMinutoFinanciamento());
		
		politicaDispCndcPagamentoList = (List<PoliticaDispCndcPagamento>) query1.getResultList();
		PoliticaDispCndcPagamento politicaDispCndcPagamento = null;
		if(!politicaDispCndcPagamentoList.isEmpty()){
			politicaDispCndcPagamento = politicaDispCndcPagamentoList.get(0);
		}

		Servico servico = em.find(Servico.class, detalheServicoFixaTO.getIdServico());
		
		for(SolicitacaoComercial solicitacaoComercial : servico.getSistemaServico().getSolicitacaoComercialList()){
			for(Encargo encargo : solicitacaoComercial.getEncargoList()){
				for(CondicaoPagamentoEncargo condicaoPagamentoEncargo : encargo.getCondicaoPagamentoEncargoList()){
					condicaoPagamentoEncargo.setPoliticaDispCndcPagamento(politicaDispCndcPagamento);
                    for (DisponibilidadeCndcPagamento dcp : condicaoPagamentoEncargo.getDisponibilidadeCndcPagamentoList()) {
                        em.remove(dcp);
                    }
                    condicaoPagamentoEncargo.setDisponibilidadeCndcPagamentoList(null);
				}
			}
		}
		
		em.merge(servico);

    }
    
    public void gravarRelacionamento(ServicoServicoTO servicoServicoTO) throws DAOException {
        try {
            String hql = "select count(ss) from ServicoServico ss where ss.tipoRelacionamento.idTipoRelacionamento = :idTipoRelacionamento and ss.servico1.idServico = :idServico1 and ss.servico2.idServico = :idServico2";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idTipoRelacionamento", servicoServicoTO.getIdTipoRelacionamento());
            params.put("idServico1", servicoServicoTO.getIdServico1());
            params.put("idServico2", servicoServicoTO.getIdServico2());
            if (((Long)this.getQuery(hql, params).getSingleResult()) == 0) {
                em.merge(new ServicoServicoTOBuilder().buildEntity(servicoServicoTO));
            }
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [gravarRelacionamento]",e);
        }
    }

	public SistemaTO findSistemaByIdServico(ServicoFixaTO servicoFixaTO) throws DAOException {
		SistemaTO result = null;
		
        try {
        	result =  new SistemaTOBuilder().createTO(em.find(Servico.class, servicoFixaTO.getIdServico()).getSistemaServico().getSistema());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findSistemaByIdServico]",e);
        }
        
        return result;
	}
    
    public SistemaTO findSistemaByIdServico(int idServico) throws DAOException {
        return findSistemaByIdServico(new ServicoFixaTO(idServico));
    }
    
    @SuppressWarnings("unchecked")
    public List<CategorizacaoAnaliseCreditoTO> searchServicoFixaAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer();
        hql.append("select new com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO(s, ss) ");
        hql.append(" from Servico s ");
        hql.append(" inner join s.sistemaServico ss ");
        
        if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
            hql.append(" inner join s.servicoCategoriaScore scs ");
            hql.append(" where s.inFixa = 'S' ");
            hql.append(" and scs.categoriaScore.idCategoriaScore = :idCategoriaScore " );
            params.put("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
        } else {
            hql.append(" where s.inFixa = 'S' ");
        }
        
        
        if (!StringUtils.isBlank(categorizacaoAnaliseCreditoTO.getNome())) {
            hql.append(" and upper(s.nmComercial) like upper(:nmComercial) ");
            params.put("nmComercial", String.format("%s%s%s", "%", categorizacaoAnaliseCreditoTO.getNome() ,"%"));
        }
        
        try {
            return this.getQuery(hql.toString(), params).getResultList();
        } catch (Exception e) {
            String msg = "Erro ao obter servicos";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
    }

	@SuppressWarnings("unchecked")
	public List<ServicoFixaTO> searchServicoFixaSegmento(ServicoFixaTO servicoFixaTO) throws DAOException {
        Map<String, Object> params = new HashMap<String, Object>();
        
        StringBuilder queryStrP1 = new StringBuilder(
        	"select distinct s " +
        	"from Servico s " +
        	"join s.sistemaServico ss " +
        	"join s.tipoServico ts " +
        	"join s.categoria c " +
        	"join c.familia f "
        );
        
        StringBuilder queryStrP2 = new StringBuilder(
           	"where ts.idTipoServico = :idTipoServico "
        );
        params.put("idTipoServico", servicoFixaTO.getIdTipoServico());
        
        if(servicoFixaTO.getSegmentoTO() != null){
        	switch(servicoFixaTO.getSegmentoTO().getTipoPesquisaSegmento()){
        	case SEGMENTO:
        		queryStrP1.append(
        			"join s.servicoSegmento sseg " +
        			"join sseg.segmento seg "
        		);
        		queryStrP2.append(
        			"and seg.idSegmento = :idSegmento"
        		);
        		params.put("idSegmento", servicoFixaTO.getSegmentoTO().getIdSegmento());
        		break;
        	case SEGMENTADOS:
        		queryStrP1.append(
        			"join s.servicoSegmento sseg " +
        			"join sseg.segmento seg "	
        		);
        		break;
        	case NAO_SEGMENTADOS:
        		queryStrP1.append(
        			"left join s.servicoSegmento sseg " +
        			"left join sseg.segmento seg "
        		);
        		queryStrP2.append(
        			"and seg.idSegmento is null "	
        		);
        		break;
        	}
         }
        if(servicoFixaTO.getSegmentoTO() != null && servicoFixaTO.getSegmentoTO().getInInfancia() != null){
        	queryStrP2.append("and sseg.inInfancia = :inInfancia ");
        	params.put("inInfancia", servicoFixaTO.getSegmentoTO().getInInfancia());
        }
        if(servicoFixaTO.getCdCategoria() != null && !servicoFixaTO.getCdCategoria().equals("")){
        	queryStrP2.append("and c.cdCategoria = :cdCategoria ");
        	params.put("cdCategoria", servicoFixaTO.getCdCategoria());
        }
        if(servicoFixaTO.getFamiliaTO() != null){
        	queryStrP2.append("and f.idFamilia = :idFamilia ");
        	params.put("idFamilia", servicoFixaTO.getFamiliaTO().getIdFamilia());
        }
        if(servicoFixaTO.getCdServico() != null && !servicoFixaTO.getCdServico().equals("")){
        	queryStrP2.append("and ss.cdCodigo = :cdServico ");
        	params.put("cdServico", servicoFixaTO.getCdServico());
        }
        if(servicoFixaTO.getTecnologiaTO() != null){
        	queryStrP1.append("join s.tecnologiaList t ");
        	queryStrP2.append("and t.idTecnologia = :idTecnologia ");
        	params.put("idTecnologia", servicoFixaTO.getTecnologiaTO().getIdTecnologia());
        }
        try {
        	Query query = getQuery(queryStrP1.toString() + queryStrP2.toString(), params);
        	List<ServicoFixaTO> servicoFixaList = new ServicoFixaTOBuilder().buildServicoSegmentoTOList(query.getResultList());
        	return servicoFixaList;
            
        } catch (Exception e) {
            String msg = "Erro ao executar [searchServicoFixaSegmento]";
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
	}

	@SuppressWarnings("unchecked")
	public void saveServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws DAOException {
		try {
			StringBuilder queryStr = new StringBuilder(
				"select ssg " +
				"from ServicoSegmento ssg " +
				"join ssg.segmento sg " +
				"join ssg.servico s " +
				"where s.idServico in (:idServicoList) "	
			);
			List<Integer> idServicoList = new ArrayList<Integer>();
			for(ServicoFixaTO servicoFixaTO: servicoSegmentoTO.getServicoTOList()){
				idServicoList.add(servicoFixaTO.getIdServico());
			}
			List<ServicoSegmento> segmentoServicoList = em.createQuery(queryStr.toString()).setParameter("idServicoList",idServicoList).getResultList();
			for(ServicoSegmento servicoSegmento : segmentoServicoList){
				em.remove(servicoSegmento);
			}
			em.flush();
			
			for(ServicoFixaTO servicoFixaTO : servicoSegmentoTO.getServicoTOList()){
				ServicoSegmento servicoSegmento = new ServicoSegmento();
				servicoSegmento.setInInfancia(servicoSegmentoTO.getInInfancia());
				servicoSegmento.setDtCriacao(Calendar.getInstance());
				servicoSegmento.setNmUsuarioCriacao(servicoSegmentoTO.getNmUsuarioCriacao());
				servicoSegmento.setSegmento(new Segmento(servicoSegmentoTO.getSegmentoTO().getIdSegmento()));
				servicoSegmento.setServico(new Servico(servicoFixaTO.getIdServico()));
				servicoSegmento.setInExportacao("N");
				em.merge(servicoSegmento);
			}
		} catch(Exception e) {
			throw new DAOException("Erro a executar [saveServicoSegmento]", e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void disassociateServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws DAOException {
		try {
			StringBuilder queryStr = new StringBuilder(
				"select ssg " +
				"from ServicoSegmento ssg " +
				"join ssg.servico s " +
				"where s.idServico in (:idServicoList) "	
			);
			List<Integer> idServicoList = new ArrayList<Integer>();
			for(ServicoFixaTO servicoFixaTO : servicoSegmentoTO.getServicoTOList()){
				idServicoList.add(servicoFixaTO.getIdServico());
			}
			List resultList = em.createQuery(queryStr.toString()).setParameter("idServicoList",idServicoList).getResultList();
			List<ServicoSegmento> segmentoServicoList = resultList;
			for(ServicoSegmento servicoSegmento : segmentoServicoList){
				em.remove(servicoSegmento);
			}
			
		} catch(Exception e) {
			throw new DAOException("Erro a executar [disassociateServicoSegmento]", e);
		}
	}
}
