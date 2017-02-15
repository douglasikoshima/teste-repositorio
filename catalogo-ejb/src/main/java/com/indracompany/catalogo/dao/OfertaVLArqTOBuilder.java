package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OfertaVLArq;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;

public class OfertaVLArqTOBuilder {

    public List<ImportacaoServicoFixaTO> buildTOList(List<OfertaVLArq> entityList) {
        List<ImportacaoServicoFixaTO> toList = new ArrayList<ImportacaoServicoFixaTO>();
        for (OfertaVLArq entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public ImportacaoServicoFixaTO buildTO(OfertaVLArq entity) {
        ImportacaoServicoFixaTO to = null;
        if (entity != null) {
            to = new ImportacaoServicoFixaTO();
            to.setId(entity.getIdOfertaVLArq());
            to.setDescErro(entity.getDsErro());
            to.setDtImportacao(entity.getDtImportacao());
            to.setDtProcessamento(entity.getDtProcessamento());
            to.setNmArquivo(entity.getNmArquivo());
            to.setNmUsuarioImportacao(entity.getNmUsuarioImportacao());
            to.setStatusArquivoImportacaoTO(new MatrizOfertaStatusImportacaoTOBuilder().createStatusArquivoImportacaoTO(entity.getMatrizOfertaStatusImportacao()));
        }
        return to;
    }	
}
