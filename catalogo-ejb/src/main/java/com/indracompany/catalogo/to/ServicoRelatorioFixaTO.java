package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ServicoRelatorioFixaTO implements Serializable {

    private static final long serialVersionUID = 1153980487368112680L;
    
    private String cdServicoCatalogo;
    private String cdServicoOrigem;
    private String cdTipoServico;
    private String nmSistemaOrigem;
    private String nmServicoCatalogo;
    private String nmServicoOrigem;
    private String nmTipoServico;
    private String nmCategoria;
    private String disponibilidade;
    
    public String getCdServicoCatalogo() {
        return cdServicoCatalogo;
    }
    
    public void setCdServicoCatalogo(String cdServicoCatalogo) {
        this.cdServicoCatalogo = cdServicoCatalogo;
    }
    
    public String getCdServicoOrigem() {
        return cdServicoOrigem;
    }
    
    public void setCdServicoOrigem(String cdServicoOrigem) {
        this.cdServicoOrigem = cdServicoOrigem;
    }
    
    public String getCdTipoServico() {
        return cdTipoServico;
    }
    
    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }
    
    public String getNmServicoCatalogo() {
        return nmServicoCatalogo;
    }
    
    public void setNmServicoCatalogo(String nmServicoCatalogo) {
        this.nmServicoCatalogo = nmServicoCatalogo;
    }
    
    public String getNmServicoOrigem() {
        return nmServicoOrigem;
    }
    
    public void setNmServicoOrigem(String nmServicoOrigem) {
        this.nmServicoOrigem = nmServicoOrigem;
    }
    
    public String getNmTipoServico() {
        return nmTipoServico;
    }
    
    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }

    public String getNmSistemaOrigem() {
        return nmSistemaOrigem;
    }

    public void setNmSistemaOrigem(String nmSistemaOrigem) {
        this.nmSistemaOrigem = nmSistemaOrigem;
    }

    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
