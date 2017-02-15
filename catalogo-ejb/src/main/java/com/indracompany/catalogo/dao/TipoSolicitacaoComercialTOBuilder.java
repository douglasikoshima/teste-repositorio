package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoSolicitacaoComercial;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public class TipoSolicitacaoComercialTOBuilder {

    public List<TipoSolicitacaoComercialTO> buildTOList(List<TipoSolicitacaoComercial> entityList) {
        List<TipoSolicitacaoComercialTO> toList = new ArrayList<TipoSolicitacaoComercialTO>();
        for(TipoSolicitacaoComercial entity: entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public TipoSolicitacaoComercialTO buildTO(TipoSolicitacaoComercial entity) {
        TipoSolicitacaoComercialTO to = null;
        if (entity != null) {
            to = new TipoSolicitacaoComercialTO();
            to.setCdTipoSolicitacaoComercial(entity.getCdTipoSolicitacaoComercial());
            to.setIdTipoSolicitacaoComercial(entity.getIdTipoSolicitacaoComercial());
            to.setNmTipoSolicitacaoComercial(entity.getNmTipoSolicitacaoComercial());
        }
        return to;
    }
}
