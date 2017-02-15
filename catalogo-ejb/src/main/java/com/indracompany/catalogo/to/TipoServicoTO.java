package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TipoServicoTO implements Serializable {

    private static final long serialVersionUID = -5154063059908982617L;
    
    private Integer idTipoServico;
    private String dscTipoServico;
    private String nmTipoServico;
    private String cdTipoServico;
    private String inFixa;
    
    public String getCdTipoServico() {
        return cdTipoServico;
    }
    
    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }
    
    public String getDscTipoServico() {
        return dscTipoServico;
    }
    
    public void setDscTipoServico(String dscTipoServico) {
        this.dscTipoServico = dscTipoServico;
    }
    
    public Integer getIdTipoServico() {
        return idTipoServico;
    }
    
    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }
    
    public String getInFixa() {
        return inFixa;
    }
    
    public void setInFixa(String inFixa) {
        this.inFixa = inFixa;
    }
    
    public String getNmTipoServico() {
        return nmTipoServico;
    }
    
    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }    
}
