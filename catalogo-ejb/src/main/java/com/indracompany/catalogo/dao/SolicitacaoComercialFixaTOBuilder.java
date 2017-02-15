package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.datalayer.VwFixaExportSolcom;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;

public class SolicitacaoComercialFixaTOBuilder {

    public List<SolicitacaoComercialFixaTO> buildTOList(List<VwFixaExportSolcom> entityList) {
        List<SolicitacaoComercialFixaTO> toList = new ArrayList<SolicitacaoComercialFixaTO>();
        for(VwFixaExportSolcom entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public SolicitacaoComercialFixaTO buildTO(VwFixaExportSolcom entity) {
        SolicitacaoComercialFixaTO to = null;
        if (entity != null) {
            to = new SolicitacaoComercialFixaTO();
            to.setCdServicoCatalogo(entity.getCdServicoCatalogo());
            to.setCdServicoOrigem(entity.getCdServicoOrigem());
            to.setCdSolicitacaoComercial(entity.getCdSolicitacaoComercial());
            to.setCdTipoServico(entity.getCdTipoServico());
            to.setCdTipoSolicitacaoComercial(entity.getCdTipoSolicitacaoComercial());
            if (!StringUtils.isBlank(entity.getDisponivel())) {
                to.setDisponivel(entity.getDisponivel().equalsIgnoreCase("S"));
            } else {
                to.setDisponivel(false);
            }
            to.setNmServicoCatalogo(entity.getNmServicoCatalogo());
            to.setNmServicoOrigem(entity.getNmServicoOrigem());
            to.setNmSistema(entity.getNmSistema());
            to.setNmSolicitacaoComercial(entity.getNmSolicitacaoComercial());
            to.setNmTipoServico(entity.getNmTipoServico());
            to.setNmTipoSolicitacaoComercial(entity.getNmTipoSolicitacaoComercial());
            to.setPrazoMaximoAtendimento(entity.getPrazoMaximoAtendimento());
            to.setPrazoMaximoVigencia(entity.getPrazoMaximoVigencia());
        }
        return to;
    }
    
    public List<SolicitacaoComercialFixaTO> buildBasicTOList(List<SolicitacaoComercial> entityList) {
        List<SolicitacaoComercialFixaTO> toList = new ArrayList<SolicitacaoComercialFixaTO>();
        for(SolicitacaoComercial entity : entityList) {
            toList.add(this.buildBasicTO(entity));
        }
        return toList;
    }
    
    public SolicitacaoComercialFixaTO buildBasicTO(SolicitacaoComercial entity) {
        SolicitacaoComercialFixaTO to = null;
        if (entity != null) {
            to = new SolicitacaoComercialFixaTO();
            to.setIdSolicitacaoComercial(entity.getIdSolicitacaoComercial());
            to.setCdSolicitacaoComercial(entity.getCdSolicitacaoComercial());
            to.setNmSolicitacaoComercial(entity.getNmSolicitacaoComercial());
            to.setCdPS(entity.getSistemaServico().getComplementoLegado().getCdPS());
            to.setCdoperacaocomercial(entity.getOperacaoComercial().getCdOperacaoComercial());
            if (entity.getPzMaximoVigencia() != null) {
                to.setPrazoMaximoVigencia(entity.getPzMaximoVigencia().intValue());
            }
            if (entity.getPzMaximoAtendimento() != null) {
                to.setPrazoMaximoAtendimento(entity.getPzMaximoAtendimento().intValue());
            }
            to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            to.setPrazoMaximoVigencia(entity.getPzMaximoVigencia().intValue());
            if (entity.getSistemaServico().getServico() != null) {
				Servico servico = entity.getSistemaServico().getServico();
				to.setNmServicoCatalogo(servico.getNmComercial());
				to.setCdServicoCatalogo(servico.getCdServico());
			}
        }
        return to;
    }

}
