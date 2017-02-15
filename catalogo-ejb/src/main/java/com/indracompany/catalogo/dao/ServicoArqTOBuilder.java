package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoArq;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;

public class ServicoArqTOBuilder {

    public List<ImportacaoServicoFixaTO> buildTOList(List<ServicoArq> entityList) {
        List<ImportacaoServicoFixaTO> toList = new ArrayList<ImportacaoServicoFixaTO>();
        for (ServicoArq entity : entityList) {
            toList.add(this.buildTO(entity));
        }
        return toList;
    }

    public ImportacaoServicoFixaTO buildTO(ServicoArq entity) {
        ImportacaoServicoFixaTO to = null;
        if (entity != null) {
            to = new ImportacaoServicoFixaTO();
            to.setId(entity.getIdServicoArq());
            to.setDescErro(entity.getDscErro());
            to.setDtImportacao(entity.getDtImportacao());
            to.setDtProcessamento(entity.getDtProcessamento());
            to.setNmArquivo(entity.getNmArquivo());
            to.setNmUsuarioImportacao(entity.getNmUsuarioImportacao());
            to.setStatusArquivoImportacaoTO(new MatrizOfertaStatusImportacaoTOBuilder().createStatusArquivoImportacaoTO(entity.getMatrizOfertaStatusImportacao()));
        }
        return to;
    }

}
