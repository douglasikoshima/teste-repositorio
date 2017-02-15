package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.ServicoServico;
import com.indracompany.catalogo.datalayer.TipoRelacionamento;
import com.indracompany.catalogo.to.ServicoServicoTO;

public class ServicoServicoTOBuilder {

    public ServicoServico buildEntity(ServicoServicoTO to) {
        ServicoServico entity = null;
        if (to != null) {
            entity = new ServicoServico();
            entity.setInFixa("S");
            entity.setDtCriacao(to.getDtCriacao());
            entity.setDtFinal(to.getDtFinal());
            entity.setDtInicial(to.getDtInicial());
            entity.setDtUltimaAlteracao(to.getDtUltimaAlteracao());
            entity.setIdServicoServico(to.getIdServicoServico());
            entity.setInCriacaoCatalogo(to.getInCriacaoCatalogo());
            entity.setInDisponivel(to.getInDisponivel());
            entity.setInMember(to.getInMember());
            entity.setNmUsuarioAlteracao(to.getNmUsuarioAlteracao());
            entity.setNmUsuarioCriacao(to.getNmUsuarioAlteracao());
            entity.setServico1(new Servico(to.getIdServico1()));
            entity.setServico2(new Servico(to.getIdServico2()));
            entity.setTipoRelacionamento(new TipoRelacionamento(to.getIdTipoRelacionamento()));
        }
        
        return entity;
    }
}
