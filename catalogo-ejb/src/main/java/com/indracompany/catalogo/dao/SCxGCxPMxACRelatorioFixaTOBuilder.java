package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VwFixaExportScgc;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;

public class SCxGCxPMxACRelatorioFixaTOBuilder {

    public List<SCxGCxPMxACRelatorioFixaTO> buildTOList(List<VwFixaExportScgc> entityList) {
        List<SCxGCxPMxACRelatorioFixaTO> toList = new ArrayList<SCxGCxPMxACRelatorioFixaTO>();
        for (VwFixaExportScgc entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public SCxGCxPMxACRelatorioFixaTO buildTO(VwFixaExportScgc entity) {
        SCxGCxPMxACRelatorioFixaTO to = null;
        if (entity != null) {
            to = new SCxGCxPMxACRelatorioFixaTO();
            to.setCdServico(entity.getCdServico());
            to.setNmServico(entity.getNmServico());
            to.setCdAreaConcorrencia(entity.getCdAreaConcorrencia());
            to.setCdGrupoComercial(entity.getCdGrupoComercial());
            to.setCdPlanoMinutos(entity.getCdPlanoMinutos());
            to.setCdSolicitacaoComercial(entity.getCdSolicitacaoComercial());
            to.setCdTipoSolicitacaoComercial(entity.getCdTipoSolicitacaoComercial());
            to.setNmGrupoComercial(entity.getNmGrupoComercial());
            to.setNmSolicitacaoComercial(entity.getNmSolicitacaoComercial());
            to.setNmTipoSolicitacaoComercial(entity.getNmTipoSolicitacaoComercial());
            to.setNmAreaConcorrencia(entity.getNmAreaConcorrencia());
            to.setNmPlanoMinutos(entity.getNmPlanoMinutos());
        }
        return to;
    }
}
