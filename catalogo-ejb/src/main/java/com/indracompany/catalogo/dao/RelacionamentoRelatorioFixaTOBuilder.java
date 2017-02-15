package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VwFixaExportRelserv;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;

public class RelacionamentoRelatorioFixaTOBuilder {

    public List<RelacionamentoRelatorioFixaTO> buildTOList(List<VwFixaExportRelserv> entityList) {
        List<RelacionamentoRelatorioFixaTO> toList = new ArrayList<RelacionamentoRelatorioFixaTO>();
        for (VwFixaExportRelserv entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public RelacionamentoRelatorioFixaTO buildTO(VwFixaExportRelserv entity) {
        RelacionamentoRelatorioFixaTO to = null;
        if (entity != null) {
            to = new RelacionamentoRelatorioFixaTO();
            to.setCdServicoCatalogoMestre(entity.getCdServicoCatalogoMestre());
            to.setCdServicoOrigemMestre(entity.getCdServicoOrigemMestre());
            to.setCdTipoServicoMestre(entity.getCdTipoServicoMestre());
            to.setNmServicoCatalogoMestre(entity.getNmServicoCatalogoMestre());
            to.setNmServicoOrigemMestre(entity.getNmServicoOrigemMestre());
            to.setNmTipoServicoMestre(entity.getNmTipoServicoMestre());
            
            to.setSgTipoRelacionamento(entity.getSgTipoRelacionamento());
            to.setDscTipoRelacionamento(entity.getDscTipoRelacionamento());
            
            to.setCdServicoCatalogoSubordinado(entity.getCdServicoCatalogoSubordinado());
            to.setCdServicoOrigemSubordinado(entity.getCdServicoOrigemSubordinado());
            to.setCdTipoServicoSubordinado(entity.getCdTipoServicoSubordinado());
            to.setNmServicoCatalogoSubordinado(entity.getNmServicoCatalogoSubordinado());
            to.setNmServicoOrigemSubordinado(entity.getNmServicoOrigemSubordinado());
            to.setNmTipoServicoSubordinado(entity.getNmTipoServicoSubordinado());
        }
        return to;
    }

}
