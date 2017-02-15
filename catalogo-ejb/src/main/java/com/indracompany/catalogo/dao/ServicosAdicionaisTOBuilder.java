package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.OfertaFixaAdicional;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.to.ServicosAdicionaisTO;

public class ServicosAdicionaisTOBuilder {

    private static Logger log = Logger.getLogger(ServicosAdicionaisTOBuilder.class);

    public List<ServicosAdicionaisTO> buildTOList(List<OfertaFixaAdicional> entityList) {
        List<ServicosAdicionaisTO> toList = new ArrayList<ServicosAdicionaisTO>();
        for (OfertaFixaAdicional entity : entityList) {
            log.debug("buildTOList<OfertaFixaAdicional>: " + entity.toString());
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public List<ServicosAdicionaisTO> buildTOServicoList(List<Servico> entityList) {
        List<ServicosAdicionaisTO> toList = new ArrayList<ServicosAdicionaisTO>();

        for (int i = 0; i < entityList.size(); i++) {
            ServicosAdicionaisTO servicosAdicionaisTO = new ServicosAdicionaisTO();
            servicosAdicionaisTO.setIdServico(entityList.get(i).getIdServico());
            servicosAdicionaisTO.setCdServico(entityList.get(i).getCdServico());
            servicosAdicionaisTO.setNomeServico(entityList.get(i).getNmComercial());
            log.debug("Servico entity idServico: " + entityList.get(i).getIdServico());
            log.debug("Servico entity nomeServico: " + entityList.get(i).getNmComercial());
            toList.add(servicosAdicionaisTO);
        }

        return toList;
    }

    public ServicosAdicionaisTO buildTO(OfertaFixaAdicional entity) {

        ServicosAdicionaisTO to = null;
        if (entity != null) {
            to = this.obterDadosSolicitacaoComercial(entity.getSolicitacaoComercial()); 
            to.setIdServicosAdicionais(entity.getIdOfertaFixaAdicional());
            to.setDtInicio(entity.getDtInicio());
            to.setDtFim(entity.getDtFim());
            to.setTempoVigencia(entity.getTempoVigencia());
        }
        return to;
    }

    public ServicosAdicionaisTO buildServicoTO(Servico entity) {
        ServicosAdicionaisTO to = null;
        if (entity != null) {
            to = new ServicosAdicionaisTO();
        }
        return to;
    }
    
    public ServicosAdicionaisTO buildDependenteTO(ServicosAdicionaisTO servicosAdicionaisTOPai, SolicitacaoComercial solicitacaoComercial) {
        ServicosAdicionaisTO to = this.obterDadosSolicitacaoComercial(solicitacaoComercial);
        to.setIdServicosAdicionais(servicosAdicionaisTOPai.getIdServicosAdicionais());
        to.setDtInicio(servicosAdicionaisTOPai.getDtInicio());
        to.setDtFim(servicosAdicionaisTOPai.getDtFim());
        to.setTempoVigencia(servicosAdicionaisTOPai.getTempoVigencia());
        to.setDependente(true);
        return to;
    }

    private ServicosAdicionaisTO obterDadosSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
        ServicosAdicionaisTO to = new ServicosAdicionaisTO();
        SolicitacaoComercial sc = new SolicitacaoComercial();
        sc.setIdSolicitacaoComercial(solicitacaoComercial.getIdSolicitacaoComercial());
        sc.setCdSolicitacaoComercial(solicitacaoComercial.getCdSolicitacaoComercial());
        sc.setNmSolicitacaoComercial(solicitacaoComercial.getNmSolicitacaoComercial());
        to.setIdSolicitacaoComercial(solicitacaoComercial.getIdSolicitacaoComercial());
        to.setNomeSolicitacaoComercial(solicitacaoComercial.getNmSolicitacaoComercial());
        to.setCdServico(solicitacaoComercial.getSistemaServico().getServico().getCdServico());
        to.setCdSolicitacaoComercial(solicitacaoComercial.getCdSolicitacaoComercial());
        to.setNomeServico(solicitacaoComercial.getSistemaServico().getServico().getNmComercial());
        to.setNmTipoServico(solicitacaoComercial.getSistemaServico().getServico().getTipoServico().getNmTipoServico());
        to.setSolicitacaoComercial(sc);
        return to;
    }

}
