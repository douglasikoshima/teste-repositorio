package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.indracompany.catalogo.datalayer.ComplementoLegado;
import com.indracompany.catalogo.datalayer.Ofertafixa;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoOfertafixaTO;


public class OfertafixaTOBuilder {
    public List<OfertafixaTO> buildTOList(List<Ofertafixa> entityList) {
        List<OfertafixaTO> toList = new ArrayList<OfertafixaTO>();
        for(Ofertafixa entity: entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public OfertafixaTO buildTO(Ofertafixa entity) {
        OfertafixaTO to = null;
        if (entity != null) {
            to = this.buildBasicTO(entity);
            to.setOfertafixaPoliticaTO(new OfertafixaPoliticaTOBuilder().buildTO(entity.getOfertafixaPolitica()));
            if(entity.getSolicitacaoComercialLinha() !=null){
                to.setSolicitacaoComercialFixaTOLinha(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(entity.getSolicitacaoComercialLinha()));
            }
            
            if(entity.getSolicitacaoComercialPlano() != null){
            	to.setSolicitacaoComercialFixaTOPlano(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(entity.getSolicitacaoComercialPlano()));
            }
            
            if (entity.getSolicitacaoComercialPlano() != null) {
            	to.setSolicitacaoComercialFixaTOPlano(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(entity.getSolicitacaoComercialPlano()));            	
            }
            
            if (entity.getSolicitacaoComercialPreCad() != null) {
            	to.setSolicitacaoComercialFixaTOPreCad(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(entity.getSolicitacaoComercialPreCad()));            	
            }
            
            if (entity.getSolicitacaoComercialPlano() != null && entity.getSolicitacaoComercialPlano().getOperacaoComercial() != null) {
            	to.getSolicitacaoComercialFixaTOPlano().setOperacaoComercial(new OperacaoComercialTOBuilder().buildBasicTO(entity.getSolicitacaoComercialPlano().getOperacaoComercial()));
            	to.setOpSolicitacaoComercialPlano(to.getSolicitacaoComercialFixaTOPlano().getOperacaoComercial().getCdOperacaoComercial());
            }
            
            if (entity.getSolicitacaoComercialLinha() != null && entity.getSolicitacaoComercialLinha().getOperacaoComercial() != null) {
            	to.getSolicitacaoComercialFixaTOLinha().setOperacaoComercial(new OperacaoComercialTOBuilder().buildBasicTO(entity.getSolicitacaoComercialLinha().getOperacaoComercial()));
            	to.setOpSolicitacaoComercialNormal(to.getSolicitacaoComercialFixaTOLinha().getOperacaoComercial().getCdOperacaoComercial());
            }
            
            if (entity.getSolicitacaoComercialPreCad() != null && entity.getSolicitacaoComercialPreCad().getOperacaoComercial() != null ) {
            	to.getSolicitacaoComercialFixaTOPreCad().setOperacaoComercial(new OperacaoComercialTOBuilder().buildBasicTO(entity.getSolicitacaoComercialPreCad().getOperacaoComercial()));
            	to.setOpSolicitacaoComercialPreCadastro(to.getSolicitacaoComercialFixaTOPreCad().getOperacaoComercial().getCdOperacaoComercial());
            }
            
            if (to.getSolicitacaoComercialFixaTOLinha() != null) {
                Servico servico = entity.getSolicitacaoComercialLinha().getSistemaServico().getServico();
                ComplementoLegado complementoLegado = entity.getSolicitacaoComercialLinha().getSistemaServico().getComplementoLegado();
                to.setServicoOfertaFixaTOLinha(new ServicoOfertafixaTO(servico.getIdServico(), complementoLegado.getCdPS()+" - "+servico.getNmComercial()));
                to.getServicoOfertaFixaTOLinha().setComplementoLegado(new ComplementoLegadoTOBuilder().createTO(entity.getSolicitacaoComercialLinha().getSistemaServico().getComplementoLegado()));   
                to.setPsServivoLinha(to.getServicoOfertaFixaTOLinha().getComplementoLegado().getCdPS());
            } else if (to.getSolicitacaoComercialFixaTOPreCad() != null) {
                Servico servico = entity.getSolicitacaoComercialPreCad().getSistemaServico().getServico();
                ComplementoLegado complementoLegado = entity.getSolicitacaoComercialPreCad().getSistemaServico().getComplementoLegado();
                to.setServicoOfertaFixaTOLinha(new ServicoOfertafixaTO(servico.getIdServico(), complementoLegado.getCdPS()+" - "+servico.getNmComercial()));
                to.getServicoOfertaFixaTOLinha().setComplementoLegado(new ComplementoLegadoTOBuilder().createTO(entity.getSolicitacaoComercialPreCad().getSistemaServico().getComplementoLegado()));
                to.setPsServivoLinha(to.getServicoOfertaFixaTOLinha().getComplementoLegado().getCdPS());
            }
            if (to.getServicoOfertaFixaTOLinha() != null && to.getSolicitacaoComercialFixaTOPlano() != null) {
                Servico servico = entity.getSolicitacaoComercialPlano().getSistemaServico().getServico();
                ComplementoLegado complementoLegado = entity.getSolicitacaoComercialPlano().getSistemaServico().getComplementoLegado();
                to.setServicoOfertaFixaTOPlano(new ServicoOfertafixaTO(servico.getIdServico(), complementoLegado.getCdPS()+" - "+servico.getNmComercial()));
                to.getServicoOfertaFixaTOPlano().setComplementoLegado(new ComplementoLegadoTOBuilder().createTO(entity.getSolicitacaoComercialPlano().getSistemaServico().getComplementoLegado()));
                to.setPsServicoPlano(to.getServicoOfertaFixaTOPlano().getComplementoLegado().getCdPS());
            }        
        
        }
        return to;
    }

    public OfertafixaTO buildBasicTO(Ofertafixa entity) {
        OfertafixaTO to = null;
        if (entity != null) {
            to = new OfertafixaTO();
            to.setCdOfertafixa(entity.getCdOfertafixa());
            to.setDsOfertafixa(entity.getDsOfertafixa());
            to.setDtCriacao(entity.getDtCriacao());
            to.setDtFinal(entity.getDtFinal());
            to.setDtInicial(entity.getDtInicial());
            to.setDtUltimaAlteracao(entity.getDtUltimaAlteracao());
            to.setIdOfertafixa(entity.getIdOfertafixa());
            to.setInConvergenteCobre(entity.getInConvergenteCobre());
            to.setInConvergenteFibra(entity.getInConvergenteFibra());
            to.setInFWT(entity.getInFWT());
            to.setInPortab(entity.getInPortab());
            to.setInSpeedySoloCobre(entity.getInSpeedySoloCobre());
            to.setInSpeedySoloFibra(entity.getInSpeedySoloFibra());
            to.setInBasePontual(entity.getInBasePontual());
            to.setInOfertaClienteInadimplente(entity.getInOfertaClienteInadimplente());
            to.setInFATB(entity.getInFATB());
            to.setNmUsuarioAlteracao(entity.getNmUsuarioAlteracao());
            to.setNmUsuarioCriacao(entity.getNmUsuarioCriacao());
            
            Date hoje = DateUtils.truncate(new Date(), Calendar.DATE);
            if (entity.getDtInicial().compareTo(hoje) <= 0 && (entity.getDtFinal() == null || entity.getDtFinal().compareTo(hoje) >= 0)) {
                to.setStatus(OfertafixaTO.VIGENTE);
            } else if (entity.getDtInicial().compareTo(hoje) > 0) {
                to.setStatus(OfertafixaTO.NAO_INICIADO);
            } else if (entity.getDtFinal() != null && entity.getDtFinal().compareTo(hoje) < 0) {
                to.setStatus(OfertafixaTO.FINALIZADO);
            } else {
                to.setStatus("");
            }
            to.setInStatusConfiguracao(entity.getInStatusConfiguracao());
        }
        return to;
    }

    public Ofertafixa buildEntityRecordable(Ofertafixa entity, OfertafixaTO to) {
    	entity.setCdOfertafixa(to.getCdOfertafixa());
    	entity.setDsOfertafixa(to.getDsOfertafixa());
        entity.setDtFinal(to.getDtFinal());
        entity.setDtInicial(to.getDtInicial());
        /*nao me orgulho dessa Yoda's Validation, mas devido as circunstancias, eh o que temos para hj*/
        entity.setInConvergenteCobre("S".equals(to.getInConvergenteCobre()) ? "S" : "N");
        entity.setInConvergenteFibra("S".equals(to.getInConvergenteFibra()) ? "S" : "N");
        entity.setInFWT("S".equals(to.getInFWT()) ? "S" : "N");
        entity.setInPortab("S".equals(to.getInPortab()) ? "S" : "N");
        entity.setInSpeedySoloCobre("S".equals(to.getInSpeedySoloCobre()) ? "S" : "N");
        entity.setInSpeedySoloFibra("S".equals(to.getInSpeedySoloFibra()) ? "S" : "N");
        entity.setInBasePontual("S".equals(to.getInBasePontual()) ? "S" : "N");
        entity.setInOfertaClienteInadimplente("S".equals(to.getInOfertaClienteInadimplente()) ? "S" : "N");
        entity.setInFATB("S".equals(to.getInFATB()) ? "S" : "N");
        if (to.getIdSolicitacaoComercialLinha() == null) {
            entity.setSolicitacaoComercialLinha(null);
        } else { 
            entity.setSolicitacaoComercialLinha(new SolicitacaoComercial(to.getIdSolicitacaoComercialLinha()));
        }
        if (to.getIdSolicitacaoComercialPlano() == null) {
            entity.setSolicitacaoComercialPlano(null);
        } else {
            entity.setSolicitacaoComercialPlano(new SolicitacaoComercial(to.getIdSolicitacaoComercialPlano()));
        }
        if (to.getIdSolicitacaoComercialPreCad() != null) {
            entity.setSolicitacaoComercialPreCad(new SolicitacaoComercial(to.getIdSolicitacaoComercialPreCad()));
        }
        entity.setNmUsuarioAlteracao(to.getNmUsuarioAlteracao());
        entity.setDtUltimaAlteracao(new Date());
        return entity;
    }

    public OfertafixaTO buildTOWithComplementar(Ofertafixa entity) {
        OfertafixaTO to = this.buildTO(entity);
        if (to != null) {
            to.setOfertafixaComplementarTOList(new OfertafixaComplementarTOBuilder().buildTOList(entity.getOfertafixaComplementarList()));
        }
        return to;
    }
}
