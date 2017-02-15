package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OfertafixaComplementar;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.ServicoOfertafixaTO;

public class OfertafixaComplementarTOBuilder {

    public List<OfertafixaComplementarTO> buildTOList(List<OfertafixaComplementar> entityList) {
        List<OfertafixaComplementarTO> toList = new ArrayList<OfertafixaComplementarTO>();
        for(OfertafixaComplementar entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public OfertafixaComplementarTO buildTO(OfertafixaComplementar entity) {
        OfertafixaComplementarTO to = null;
        if (entity != null) {
            to = new OfertafixaComplementarTO();
            to.setIdOfertafixaComplementar(entity.getIdOfertafixaComplementar());
            to.setSolicitacaoComercialFixaTO(new SolicitacaoComercialFixaTOBuilder().buildBasicTO(entity.getSolicitacaoComercial()));
            to.setOfertafixaTO(new OfertafixaTOBuilder().buildBasicTO(entity.getOfertafixa()));
            if (to.getSolicitacaoComercialFixaTO() != null) {
                Servico servico = entity.getSolicitacaoComercial().getSistemaServico().getServico();
                to.setServicoOfertaFixaTO(new ServicoOfertafixaTO(servico.getIdServico(), servico.getNmComercial()));
                to.getServicoOfertaFixaTO().setCdServico(servico.getCdServico());
                to.setIdSolicitacaoComercialPai(to.getSolicitacaoComercialFixaTO().getIdSolicitacaoComercial());
            }            
            to.setPzMaximoVigencia(entity.getPzMaximoVigencia());
        }
        return to;
    }

}
