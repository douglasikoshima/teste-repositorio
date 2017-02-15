package com.indracompany.catalogo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.GenericJDBCException;

import com.indracompany.catalogo.dao.interfaces.RelatorioFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

@Stateless
public class RelatorioFixaDAOImpl implements RelatorioFixaDAO {

    private static Logger log = Logger.getLogger(RelatorioFixaDAOImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    public List<TipoSolicitacaoComercialTO> findAllTpSolicitacaoComercial() throws DAOException {
        List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
        try {
            tipoSolicitacaoComercialTOList = new TipoSolicitacaoComercialTOBuilder().buildTOList(em.createQuery("select tsc from TipoSolicitacaoComercial tsc ").getResultList());
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findAllTpSolicitacaoComercial]",e);
        }
        return tipoSolicitacaoComercialTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicoRelatorioFixaTO> pesquisarServico(ServicoRelatorioFixaTO servicoRelatorioFixaTO) throws DAOException {
        StringBuilder sb = new StringBuilder("select vfes from VwFixaExportServ vfes where vfes.cdServicoCatalogo is not null ");
        Map<String, Object> params = new HashMap<String, Object>();
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getCdServicoCatalogo())) {
            sb.append("and vfes.cdServicoCatalogo like :cdServicoCatalogo ");
            params.put("cdServicoCatalogo", servicoRelatorioFixaTO.getCdServicoCatalogo());
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getCdServicoOrigem())) {
            sb.append("and vfes.cdServicoOrigem like :cdServicoOrigem ");
            params.put("cdServicoOrigem", servicoRelatorioFixaTO.getCdServicoOrigem());
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getCdTipoServico())) {
            sb.append("and vfes.cdTipoServico like :cdTipoServico ");
            params.put("cdTipoServico", servicoRelatorioFixaTO.getCdTipoServico());
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getNmServicoCatalogo())) {
            sb.append("and lower(vfes.nmServicoCatalogo) like lower(:nmServicoCatalogo) ");
            params.put("nmServicoCatalogo", String.format("%s%s%s", "%", servicoRelatorioFixaTO.getNmServicoCatalogo(), "%"));
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getNmServicoOrigem())) {
            sb.append("and lower(vfes.nmServicoOrigem) like lower(:nmServicoOrigem) ");
            params.put("nmServicoOrigem", String.format("%s%s%s", "%", servicoRelatorioFixaTO.getNmServicoOrigem(), "%"));
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getNmSistemaOrigem())) {
            sb.append("and lower(vfes.nmSistema) like lower(:nmSistema) ");
            params.put("nmSistema", String.format("%s%s%s", "%", servicoRelatorioFixaTO.getNmSistemaOrigem(), "%"));
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getNmTipoServico())) {
            sb.append("and lower(vfes.nmTipoServico) like lower(:nmTipoServico) ");
            params.put("nmTipoServico", String.format("%s%s%s", "%", servicoRelatorioFixaTO.getNmTipoServico(), "%"));
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getNmCategoria())) {
            sb.append("and lower(vfes.nmCategoria) like lower(:nmCategoria) ");
            params.put("nmCategoria", String.format("%s%s%s", "%", servicoRelatorioFixaTO.getNmCategoria(), "%"));
        }
        
        if (!StringUtils.isBlank(servicoRelatorioFixaTO.getDisponibilidade())) {
            sb.append("and lower(vfes.disponibilidade) like lower(:disponibilidade) ");
            params.put("disponibilidade", servicoRelatorioFixaTO.getDisponibilidade().equalsIgnoreCase("sim") ? "S" : "N");
        }        
        
        List<ServicoRelatorioFixaTO> servicoRelatorioFixaTOList;
        try {
            servicoRelatorioFixaTOList = new ServicoRelatorioFixaTOBuilder().buildTOList(obterQuery(sb.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarServico]",e);
        }
        return servicoRelatorioFixaTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<RelacionamentoRelatorioFixaTO> pesquisarRelacionamentoServico(RelacionamentoRelatorioFixaTO relacionamentoRelatorioFixaTO) throws DAOException {
        StringBuilder hql = new StringBuilder("select vfer from VwFixaExportRelserv vfer where vfer.cdTipoServicoMestre is not null ");
        Map<String, Object> params = new HashMap<String, Object>();
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdServicoCatalogoMestre())) {
            hql.append("and vfer.cdServicoCatalogoMestre like :cdServicoCatalogoMestre ");
            params.put("cdServicoCatalogoMestre", relacionamentoRelatorioFixaTO.getCdServicoCatalogoMestre());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdServicoCatalogoSubordinado())) {
            hql.append("and vfer.cdServicoCatalogoSubordinado like :cdServicoCatalogoSubordinado ");
            params.put("cdServicoCatalogoSubordinado", relacionamentoRelatorioFixaTO.getCdServicoCatalogoSubordinado());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdServicoOrigemMestre())) {
            hql.append("and vfer.cdServicoOrigemMestre like :cdServicoOrigemMestre ");
            params.put("cdServicoOrigemMestre", relacionamentoRelatorioFixaTO.getCdServicoOrigemMestre());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdServicoOrigemSubordinado())) {
            hql.append("and vfer.cdServicoOrigemSubordinado like :cdServicoOrigemSubordinado ");
            params.put("cdServicoOrigemSubordinado", relacionamentoRelatorioFixaTO.getCdServicoOrigemSubordinado());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdTipoServicoMestre())) {
            hql.append("and vfer.cdTipoServicoMestre like :cdTipoServicoMestre ");
            params.put("cdTipoServicoMestre", relacionamentoRelatorioFixaTO.getCdTipoServicoMestre());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getCdTipoServicoSubordinado())) {
            hql.append("and vfer.cdTipoServicoSubordinado like :cdTipoServicoSubordinado ");
            params.put("cdTipoServicoSubordinado", relacionamentoRelatorioFixaTO.getCdTipoServicoSubordinado());
        }
        
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getDscTipoRelacionamento())) {
            hql.append("and lower(vfer.dscTipoRelacionamento) like lower(:dscTipoRelacionamento) ");
            params.put("dscTipoRelacionamento", String.format("%s%s%s", "%", relacionamentoRelatorioFixaTO.getDscTipoRelacionamento(), "%"));
        }
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getNmServicoCatalogoMestre())) {
            hql.append("and lower(vfer.nmServicoCatalogoMestre) like lower(:nmServicoCatalogoMestre) ");
            params.put("nmServicoCatalogoMestre", String.format("%s%s%s", "%", relacionamentoRelatorioFixaTO.getNmServicoCatalogoMestre(), "%"));
        }
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getNmServicoCatalogoSubordinado())) {
            hql.append("and lower(vfer.nmServicoCatalogoSubordinado) like lower(:nmServicoCatalogoSubordinado) ");
            params.put("nmServicoCatalogoSubordinado", String.format("%s%s%s", "%", relacionamentoRelatorioFixaTO.getNmServicoCatalogoSubordinado(), "%"));
        }
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getNmServicoOrigemMestre())) {
            hql.append("and lower(vfer.nmServicoOrigemMestre) like lower(:nmServicoOrigemMestre) ");
            params.put("nmServicoOrigemMestre", String.format("%s%s%s", "%", relacionamentoRelatorioFixaTO.getNmServicoOrigemMestre(), "%"));
        }
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getNmServicoOrigemSubordinado())) {
            hql.append("and lower(vfer.nmServicoOrigemSubordinado) like lower(:nmServicoOrigemSubordinado) ");
            params.put("nmServicoOrigemSubordinado", String.format("%s%s%s", "%", relacionamentoRelatorioFixaTO.getNmServicoOrigemSubordinado(), "%"));
        }
        if (!StringUtils.isBlank(relacionamentoRelatorioFixaTO.getSgTipoRelacionamento())) {
            hql.append("and vfer.sgTipoRelacionamento like :sgTipoRelacionamento ");
            params.put("sgTipoRelacionamento", relacionamentoRelatorioFixaTO.getSgTipoRelacionamento());
        }
        
        List<RelacionamentoRelatorioFixaTO> relacionamentoRelatorioFixaTOList;
        try {
            relacionamentoRelatorioFixaTOList = new RelacionamentoRelatorioFixaTOBuilder().buildTOList(obterQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarRelacionamentoServico]",e);
        }
        return relacionamentoRelatorioFixaTOList;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<SolicitacaoComercialFixaTO> pesquisarSolicitacaoComercial(SolicitacaoComercialFixaTO solicitacaoComercialRelatorioFixaTO)throws DAOException {
        StringBuilder hql = new StringBuilder("select vfes from VwFixaExportSolcom vfes where vfes.cdServicoCatalogo is not null ");
        Map<String, Object> params = new HashMap<String, Object>();
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getCdServicoCatalogo())) {
            hql.append("and vfes.cdServicoCatalogo like :cdServicoCatalogo ");
            params.put("cdServicoCatalogo", solicitacaoComercialRelatorioFixaTO.getCdServicoCatalogo());
        }

        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getCdServicoOrigem())) {
            hql.append("and vfes.cdServicoOrigem like :cdServicoOrigem ");
            params.put("cdServicoOrigem", solicitacaoComercialRelatorioFixaTO.getCdServicoOrigem());
        }

        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getCdSolicitacaoComercial())) {
            hql.append("and vfes.cdSolicitacaoComercial like :cdSolicitacaoComercial ");
            params.put("cdSolicitacaoComercial", solicitacaoComercialRelatorioFixaTO.getCdSolicitacaoComercial());
        }
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getCdTipoServico())) {
            hql.append("and vfes.cdTipoServico like :cdTipoServico ");
            params.put("cdTipoServico", solicitacaoComercialRelatorioFixaTO.getCdTipoServico());
        }
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getCdTipoSolicitacaoComercial())) {
            hql.append("and vfes.cdTipoSolicitacaoComercial like :cdTipoSolicitacaoComercial ");
            params.put("cdTipoSolicitacaoComercial", solicitacaoComercialRelatorioFixaTO.getCdTipoSolicitacaoComercial());
        }
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getNmServicoCatalogo())) {
            hql.append("and lower(vfes.nmServicoCatalogo) like lower(:nmServicoCatalogo) ");
            params.put("nmServicoCatalogo", String.format("%s%s%s", "%", solicitacaoComercialRelatorioFixaTO.getNmServicoCatalogo(), "%"));
        }
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getNmServicoOrigem())) {
            hql.append("and lower(vfes.nmServicoOrigem) like lower(:nmServicoOrigem) ");
            params.put("nmServicoOrigem", String.format("%s%s%s", "%", solicitacaoComercialRelatorioFixaTO.getNmServicoOrigem(), "%"));
        }
        
        if (!StringUtils.isBlank(solicitacaoComercialRelatorioFixaTO.getNmSolicitacaoComercial())) {
            hql.append("and lower(vfes.nmSolicitacaoComercial) like lower(:nmSolicitacaoComercial) ");
            params.put("nmSolicitacaoComercial", String.format("%s%s%s", "%", solicitacaoComercialRelatorioFixaTO.getNmSolicitacaoComercial(), "%"));
        }
        
        if (solicitacaoComercialRelatorioFixaTO.getPrazoMaximoAtendimento() != null) {
            hql.append("and vfes.prazoMaximoAtendimento = :prazoMaximoAtendimento ");
            params.put("prazoMaximoAtendimento", solicitacaoComercialRelatorioFixaTO.getPrazoMaximoAtendimento());
        }
        
        if (solicitacaoComercialRelatorioFixaTO.getPrazoMaximoVigencia() != null) {
            hql.append("and vfes.prazoMaximoVigencia = :prazoMaximoVigencia ");
            params.put("prazoMaximoVigencia", solicitacaoComercialRelatorioFixaTO.getPrazoMaximoVigencia());
        }
        
        List<SolicitacaoComercialFixaTO> solicitacaoComercialRelatorioFixaTOList;
        try {
            solicitacaoComercialRelatorioFixaTOList = new SolicitacaoComercialFixaTOBuilder().buildTOList(obterQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarSolicitacaoComercial]",e);
        }
        return solicitacaoComercialRelatorioFixaTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<GrupoComercialRelatorioFixaTO> pesquisarGrupoComercial(GrupoComercialRelatorioFixaTO grupoComercialRelatorioFixaTO)throws DAOException {
        StringBuilder hql = new StringBuilder("select vfeg from VwFixaExportGc vfeg where vfeg.cdGrupoComercial is not null ");
        Map<String, Object> params = new HashMap<String, Object>();
        
        if (!StringUtils.isBlank(grupoComercialRelatorioFixaTO.getCdGrupoComercial())) {
            hql.append("and vfeg.cdGrupoComercial like :cdGrupoComercial ");
            params.put("cdGrupoComercial", grupoComercialRelatorioFixaTO.getCdGrupoComercial());
        }
        
        if (!StringUtils.isBlank(grupoComercialRelatorioFixaTO.getDsGrupoComercial())) {
            hql.append("and lower(vfeg.dsGrupoComercial) like lower(:dsGrupoComercial) ");
            params.put("dsGrupoComercial", String.format("%s%s%s", "%", grupoComercialRelatorioFixaTO.getDsGrupoComercial(), "%"));
        }
        
        List<GrupoComercialRelatorioFixaTO> grupoComercialRelatorioFixaTOList;
        try {
            grupoComercialRelatorioFixaTOList = new GrupoComercialRelatorioFixaTOBuilder().buildTOList(obterQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarSolicitacaoComercial]",e);
        }
        return grupoComercialRelatorioFixaTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<SCxGCxPMxACRelatorioFixaTO> pesquisarSCxGCxPMxAC(SCxGCxPMxACRelatorioFixaTO sCxGCxPMxACRelatorioFixaTO)throws DAOException {
        StringBuilder hql = new StringBuilder("select vfes from VwFixaExportScgc vfes where 1 = 1 ");
        Map<String, Object> params = new HashMap<String, Object>();
        
        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getCdAreaConcorrencia())) {
            hql.append("and vfes.cdAreaConcorrencia like :cdAreaConcorrencia ");
            params.put("cdAreaConcorrencia", sCxGCxPMxACRelatorioFixaTO.getCdAreaConcorrencia());
        }
        
        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getCdGrupoComercial())) {
            hql.append("and vfes.cdGrupoComercial like :cdGrupoComercial ");
            params.put("cdGrupoComercial", sCxGCxPMxACRelatorioFixaTO.getCdGrupoComercial());
        }
        
        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getCdPlanoMinutos())) {
            hql.append("and vfes.cdPlanoMinutos like :cdPlanoMinutos ");
            params.put("cdPlanoMinutos", sCxGCxPMxACRelatorioFixaTO.getCdPlanoMinutos());
        }

        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getCdSolicitacaoComercial())) {
            hql.append("and vfes.cdSolicitacaoComercial like :cdSolicitacaoComercial ");
            params.put("cdSolicitacaoComercial", sCxGCxPMxACRelatorioFixaTO.getCdSolicitacaoComercial());
        }
        
        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getCdTipoSolicitacaoComercial())) {
            hql.append("and vfes.cdTipoSolicitacaoComercial like :cdTipoSolicitacaoComercial ");
            params.put("cdTipoSolicitacaoComercial", sCxGCxPMxACRelatorioFixaTO.getCdTipoSolicitacaoComercial());
        }
        
        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getNmGrupoComercial())) {
            hql.append("and lower(vfes.nmGrupoComercial) like lower(:nmGrupoComercial) ");
            params.put("nmGrupoComercial", String.format("%s%s%s", "%", sCxGCxPMxACRelatorioFixaTO.getNmGrupoComercial(), "%"));
        }

        if (!StringUtils.isBlank(sCxGCxPMxACRelatorioFixaTO.getNmSolicitacaoComercial())) {
            hql.append("and lower(vfes.nmSolicitacaoComercial) like lower(:nmSolicitacaoComercial) ");
            params.put("nmSolicitacaoComercial", String.format("%s%s%s", "%", sCxGCxPMxACRelatorioFixaTO.getNmSolicitacaoComercial(), "%"));
        }
        
        List<SCxGCxPMxACRelatorioFixaTO> sCxGCxPMxACRelatorioFixaTOList;
        try {
            sCxGCxPMxACRelatorioFixaTOList = new SCxGCxPMxACRelatorioFixaTOBuilder().buildTOList(obterQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarSCxGCxPMxAC]",e);
        }
        return sCxGCxPMxACRelatorioFixaTOList;        
    }
    
	@SuppressWarnings("unchecked")
    public List<SCxENCxPFxGCxPMxACRelatorioFixaTO> pesquisarSCxENCxPFxGCxPMxAC(SCxENCxPFxGCxPMxACRelatorioFixaTO sCxENCxPFxGCxPMxACRelatorioFixaTO) throws DAOException {
		StringBuilder hql = new StringBuilder("select vfse from VwFixaExportScEnc vfse where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
        List<SCxENCxPFxGCxPMxACRelatorioFixaTO> result;
		
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdServico())) {
            hql.append("and vfse.cdServico = :cdServico ");
            params.put("cdServico", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdServico());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmServico())) {
            hql.append("and upper(vfse.nmServico) like :nmServico ");
            params.put("nmServico", "%" + sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmServico().toUpperCase() + "%");
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdTipoSolicitacaoComercial())) {
            hql.append("and vfse.cdTipoSolicitacaoComercial = :cdTipoSolicitacaoComercial ");
            params.put("cdTipoSolicitacaoComercial", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdTipoSolicitacaoComercial());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdSolicitacaoComercial())) {
            hql.append("and vfse.cdSolicitacaoComercial = :cdSolicitacaoComercial ");
            params.put("cdSolicitacaoComercial", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdSolicitacaoComercial());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmSolicitacaoComercial())) {
            hql.append("and upper(vfse.nmSolicitacaoComercial) like :nmSolicitacaoComercial ");
            params.put("nmSolicitacaoComercial", sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmSolicitacaoComercial().toUpperCase());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdEncargo())) {
            hql.append("and vfse.cdEncargo = :cdEncargo ");
            params.put("cdEncargo", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdEncargo());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getDsEncargo())) {
            hql.append("and upper(vfse.dsEncargo) like :dsEncargo ");
            params.put("dsEncargo", sCxENCxPFxGCxPMxACRelatorioFixaTO.getDsEncargo().toUpperCase());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdPlanoFinanciamento())) {
            hql.append("and vfse.cdPlanoFinanciamento = :cdPlanoFinanciamento ");
            params.put("cdPlanoFinanciamento", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdPlanoFinanciamento());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmPlanoFinanciamento())) {
            hql.append("and upper(vfse.nmPlanoFinanciamento) like :nmPlanoFinanciamento ");
            params.put("nmPlanoFinanciamento", sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmPlanoFinanciamento().toUpperCase());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdGrupoComercial())) {
            hql.append("and vfse.cdGrupoComercial = :cdGrupoComercial ");
            params.put("cdGrupoComercial", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdGrupoComercial());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmGrupoComercial())) {
            hql.append("and upper(vfse.nmGrupoComercial) like :nmGrupoComercial ");
            params.put("nmGrupoComercial", sCxENCxPFxGCxPMxACRelatorioFixaTO.getNmGrupoComercial().toUpperCase());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdPlanoMinutos())) {
            hql.append("and vfse.cdPlanoMinutos = :cdPlanoMinutos ");
            params.put("cdPlanoMinutos", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdPlanoMinutos());
        }
        if (!StringUtils.isBlank(sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdAreaConcorrencia())) {
            hql.append("and vfse.cdAreaConcorrencia = :cdAreaConcorrencia ");
            params.put("cdAreaConcorrencia", sCxENCxPFxGCxPMxACRelatorioFixaTO.getCdAreaConcorrencia());
        }
        
        try {
        	result = new SCxENCxPFxGCxPMxACRelatorioFixaTOBuilder().createTOList(obterQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            if (GenericJDBCException.class.isInstance(e.getCause()) && SQLException.class.isInstance(e.getCause().getCause())) {
                throw new DAOException(e.getCause().getCause().getMessage(), e);
            }
            throw new DAOException("Erro ao executar o DAO [pesquisarSCxENCxPFxGCxPMxAC]",e);
        }
        return result; 
	}
    
    private Query obterQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) {
            Object paramValue = params.get(param);
            log.debug(String.format("%s=%s", param, paramValue));
            query.setParameter(param, paramValue);
        }
        return query;
    }


}
