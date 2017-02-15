package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TipoSolicitacaoComercialTO implements Serializable {

    private static final long serialVersionUID = 569630740488571300L;
    
    private Long idTipoSolicitacaoComercial;
    private String cdTipoSolicitacaoComercial;
    private String nmTipoSolicitacaoComercial;
    
    public Long getIdTipoSolicitacaoComercial() {
        return idTipoSolicitacaoComercial;
    }
    
    public void setIdTipoSolicitacaoComercial(Long idTipoSolicitacaoComercial) {
        this.idTipoSolicitacaoComercial = idTipoSolicitacaoComercial;
    }
    
    public String getCdTipoSolicitacaoComercial() {
        return cdTipoSolicitacaoComercial;
    }
    
    public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
        this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
    }
    
    public String getNmTipoSolicitacaoComercial() {
        return nmTipoSolicitacaoComercial;
    }
    
    public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
        this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
    }
}
