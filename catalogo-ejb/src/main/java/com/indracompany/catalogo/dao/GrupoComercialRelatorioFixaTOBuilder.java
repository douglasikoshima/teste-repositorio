package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VwFixaExportGc;
import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;

public class GrupoComercialRelatorioFixaTOBuilder {

    public List<GrupoComercialRelatorioFixaTO> buildTOList(List<VwFixaExportGc> entityList) {
        List<GrupoComercialRelatorioFixaTO> toList = new ArrayList<GrupoComercialRelatorioFixaTO>();
        for(VwFixaExportGc entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public GrupoComercialRelatorioFixaTO buildTO(VwFixaExportGc entity) {
        GrupoComercialRelatorioFixaTO to = null;
        if (entity != null) {
            to = new GrupoComercialRelatorioFixaTO();
            to.setCdGrupoComercial(entity.getCdGrupoComercial());
            to.setDsGrupoComercial(entity.getDsGrupoComercial());
        }
        return to;
    }

}
