package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_SERV", schema = "CATALOGOPRS_OW")
public class VwFixaExportServ implements Serializable {

    private static final long serialVersionUID = -8883343816485076455L;
    
    @Id
    @Column(name = "IDSERVICO", nullable = false)
    private Integer idServico;
    
    @Column(name = "CDSERVICOCATALOGO")
    private String cdServicoCatalogo;
    
    @Column(name = "CDSERVICOORIGEM")
    private String cdServicoOrigem;
    
    @Column(name = "CDTIPOSERVICO")
    private String cdTipoServico;
    
    @Column(name = "NMSISTEMA")
    private String nmSistema;
    
    @Column(name = "NMSERVICOCATALOGO")
    private String nmServicoCatalogo;
    
    @Column(name = "NMSERVICOORIGEM")
    private String nmServicoOrigem;
    
    @Column(name = "NMTIPOSERVICO")
    private String nmTipoServico;
    
    @Column(name = "NMCATEGORIA")
    private String nmCategoria;
    
    @Column(name = "DISPONIBILIDADE")
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

    public String getNmSistema() {
        return nmSistema;
    }

    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
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