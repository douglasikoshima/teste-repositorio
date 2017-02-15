package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_RELSERV", schema = "CATALOGOPRS_OW")
public class VwFixaExportRelserv implements Serializable {

    private static final long serialVersionUID = 9028844938321863459L;

    @Id
    @Column(name = "IDSERVICOSERVICO")
    private Integer idServicoServico;
    
    @Column(name = "CDTIPOSERVICOMESTRE")
    private String cdTipoServicoMestre;
    
    @Column(name = "NMTIPOSERVICOMESTRE")
    private String nmTipoServicoMestre;
    
    @Column(name = "NMSERVICOORIGEMMESTRE")
    private String nmServicoOrigemMestre;
    
    @Column(name = "NMSERVICOCATALOGOMESTRE")
    private String nmServicoCatalogoMestre;
    
    @Column(name = "CDSERVICOCATALOGOMESTRE")
    private String cdServicoCatalogoMestre;
    
    @Column(name = "CDSERVICOORIGEMMESTRE")
    private String cdServicoOrigemMestre;
    
    @Column(name = "SGTIPORELACIONAMENTO")
    private String sgTipoRelacionamento;
    
    @Column(name = "DSCTIPORELACIONAMENTO")
    private String dscTipoRelacionamento;
    
    @Column(name = "CDTIPOSERVICOSUBORDINADO")
    private String cdTipoServicoSubordinado;
    
    @Column(name = "NMTIPOSERVICOSUBORDINADO")
    private String nmTipoServicoSubordinado;
    
    @Column(name = "NMSERVICOORIGEMSUBORDINADO")
    private String nmServicoOrigemSubordinado;
    
    @Column(name = "NMSERVICOCATALOGOSUBORDINADO")
    private String nmServicoCatalogoSubordinado;
    
    @Column(name = "CDSERVICOCATALOGOSUBORDINADO")
    private String cdServicoCatalogoSubordinado;
    
    @Column(name = "CDSERVICOORIGEMSUBORDINADO")
    private String cdServicoOrigemSubordinado;

    public String getCdServicoCatalogoMestre() {
        return cdServicoCatalogoMestre;
    }

    public void setCdServicoCatalogoMestre(String cdServicoCatalogoMestre) {
        this.cdServicoCatalogoMestre = cdServicoCatalogoMestre;
    }

    public String getCdServicoCatalogoSubordinado() {
        return cdServicoCatalogoSubordinado;
    }

    public void setCdServicoCatalogoSubordinado(String cdServicoCatalogoSubordinado) {
        this.cdServicoCatalogoSubordinado = cdServicoCatalogoSubordinado;
    }

    public String getCdServicoOrigemMestre() {
        return cdServicoOrigemMestre;
    }

    public void setCdServicoOrigemMestre(String cdServicoOrigemMestre) {
        this.cdServicoOrigemMestre = cdServicoOrigemMestre;
    }

    public String getCdServicoOrigemSubordinado() {
        return cdServicoOrigemSubordinado;
    }

    public void setCdServicoOrigemSubordinado(String cdServicoOrigemSubordinado) {
        this.cdServicoOrigemSubordinado = cdServicoOrigemSubordinado;
    }

    public String getCdTipoServicoMestre() {
        return cdTipoServicoMestre;
    }

    public void setCdTipoServicoMestre(String cdTipoServicoMestre) {
        this.cdTipoServicoMestre = cdTipoServicoMestre;
    }

    public String getCdTipoServicoSubordinado() {
        return cdTipoServicoSubordinado;
    }

    public void setCdTipoServicoSubordinado(String cdTipoServicoSubordinado) {
        this.cdTipoServicoSubordinado = cdTipoServicoSubordinado;
    }

    public String getDscTipoRelacionamento() {
        return dscTipoRelacionamento;
    }

    public void setDscTipoRelacionamento(String dscTipoRelacionamento) {
        this.dscTipoRelacionamento = dscTipoRelacionamento;
    }

    public String getNmServicoCatalogoMestre() {
        return nmServicoCatalogoMestre;
    }

    public void setNmServicoCatalogoMestre(String nmServicoCatalogoMestre) {
        this.nmServicoCatalogoMestre = nmServicoCatalogoMestre;
    }

    public String getNmServicoCatalogoSubordinado() {
        return nmServicoCatalogoSubordinado;
    }

    public void setNmServicoCatalogoSubordinado(String nmServicoCatalogoSubordinado) {
        this.nmServicoCatalogoSubordinado = nmServicoCatalogoSubordinado;
    }

    public String getNmServicoOrigemMestre() {
        return nmServicoOrigemMestre;
    }

    public void setNmServicoOrigemMestre(String nmServicoOrigemMestre) {
        this.nmServicoOrigemMestre = nmServicoOrigemMestre;
    }

    public String getNmServicoOrigemSubordinado() {
        return nmServicoOrigemSubordinado;
    }

    public void setNmServicoOrigemSubordinado(String nmServicoOrigemSubordinado) {
        this.nmServicoOrigemSubordinado = nmServicoOrigemSubordinado;
    }

    public String getNmTipoServicoMestre() {
        return nmTipoServicoMestre;
    }

    public void setNmTipoServicoMestre(String nmTipoServicoMestre) {
        this.nmTipoServicoMestre = nmTipoServicoMestre;
    }

    public String getNmTipoServicoSubordinado() {
        return nmTipoServicoSubordinado;
    }

    public void setNmTipoServicoSubordinado(String nmTipoServicoSubordinado) {
        this.nmTipoServicoSubordinado = nmTipoServicoSubordinado;
    }

    public String getSgTipoRelacionamento() {
        return sgTipoRelacionamento;
    }

    public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }

    public Integer getIdServicoServico() {
        return idServicoServico;
    }

    public void setIdServicoServico(Integer idServicoServico) {
        this.idServicoServico = idServicoServico;
    }
}
