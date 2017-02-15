package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VwFixaExportServ;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;

public class ServicoRelatorioFixaTOBuilder {

    public List<ServicoRelatorioFixaTO> buildTOList(List<VwFixaExportServ> entityList) {
        List<ServicoRelatorioFixaTO> toList = new ArrayList<ServicoRelatorioFixaTO>();
        for(VwFixaExportServ entity : entityList) {
            toList.add(this.buildTOList(entity));
        }
        return toList;
    }

    private ServicoRelatorioFixaTO buildTOList(VwFixaExportServ entity) {
        ServicoRelatorioFixaTO to = null;
        if (entity != null) {
            to = new ServicoRelatorioFixaTO();
            to.setCdServicoCatalogo(entity.getCdServicoCatalogo());
            to.setCdServicoOrigem(entity.getCdServicoOrigem());
            to.setCdTipoServico(entity.getCdTipoServico());
            to.setNmServicoCatalogo(entity.getNmServicoCatalogo());
            to.setNmServicoOrigem(entity.getNmServicoOrigem());
            to.setNmSistemaOrigem(entity.getNmSistema());
            to.setNmTipoServico(entity.getNmTipoServico());
            to.setNmCategoria(entity.getNmCategoria());
            to.setDisponibilidade(entity.getDisponibilidade().equalsIgnoreCase("S") ? "Sim" : "Nao");
        }
        return to;
    }
}
