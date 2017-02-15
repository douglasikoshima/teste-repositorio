package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoServico;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;

public class RelacionamentoServicoFixaTOBuilder {

    public RelacionamentoServicoFixaTO buildTO(ServicoServico entity) {
        RelacionamentoServicoFixaTO to = null;
        if (entity != null) {
            to = new RelacionamentoServicoFixaTO();
            to.setInDisponivel(entity.getInDisponivel() != null && entity.getInDisponivel().equalsIgnoreCase("S"));
            if (entity.getServico2() != null) {
                to.setCdServico(entity.getServico2().getCdServico());
                to.setNmServicoCatalogo(entity.getServico2().getNmComercial());
                to.setNmServicoSistemaOrigem(entity.getServico2().getDescricao());
            }
            to.setTipoRelacionamentoTO(new TipoRelacionamentoTOBuilder().buildTO(entity.getTipoRelacionamento()));
            to.setIdRelacionamento(entity.getIdServicoServico());
            to.setInCriacaoCatalogo(entity.getInCriacaoCatalogo() != null && entity.getInCriacaoCatalogo().equalsIgnoreCase("S"));
            to.setIdServico1(entity.getServico1().getIdServico());
        }
        return to;
    }
    
    public RelacionamentoServicoFixaTO buildTO(Object[] entity) {
        RelacionamentoServicoFixaTO to = null;
        if (entity != null) {
            to = new RelacionamentoServicoFixaTO();
            to.setInDisponivel(entity[0] != null && ((String)entity[0]).equalsIgnoreCase("S"));
            to.setCdServico((String)entity[1]);
            to.setNmServicoCatalogo((String)entity[2]);
            to.setNmServicoSistemaOrigem((String)entity[3]);
            to.setIdRelacionamento((Integer)entity[4]);
            to.setInCriacaoCatalogo(entity[5] != null && ((String)entity[5]).equalsIgnoreCase("S"));
            to.setIdServico1((Integer)entity[6]);
            TipoRelacionamentoTO tr = new TipoRelacionamentoTO();
            tr.setIdTipoRelacionamento((Integer)entity[7]);
            tr.setDscTipoRelacionamento((String) entity[8]);
            tr.setSgTipoRelacionamento((String)entity[9]);
            to.setTipoRelacionamentoTO(tr);
        }
        /*select ss.indisponivel 0, ss.servico2.cdServico 1, ss.servico2.nmComercial 2, ss.servico2.descricao 3, 
         * ss.idServicoServico 4, ss.inCriacaoCatalogo 5, ss.servico1.idServico 6, 
         * ss.tipoRelacionamento.idTipoRelacionamento 7, ss.tipoRelacionamento.dscTipoRelacionamento 8, ss.tipoRelacionamento.sgTipoRelacionamento 9*/
        return to;
    }
    
    public List<RelacionamentoServicoFixaTO> buildTOList(List<Object[]> entityList) {
        List<RelacionamentoServicoFixaTO> toList = new ArrayList<RelacionamentoServicoFixaTO>();
        for (Object[] entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }
}
