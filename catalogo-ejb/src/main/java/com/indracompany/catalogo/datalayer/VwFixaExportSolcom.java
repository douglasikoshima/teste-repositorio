package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_SOLCOM", schema = "CATALOGOPRS_OW")
public class VwFixaExportSolcom implements Serializable {

    private static final long serialVersionUID = 659475449145555681L;
    
    @Id
    @Column(name = "IDSOLICITACAOCOMERCIAL")
    private Long idSolicitacaoComercial;
    
    @Column(name = "CDSERVICOCATALOGO")
    private String cdServicoCatalogo;
    
    @Column(name = "CDSERVICOORIGEM")
    private String cdServicoOrigem;
    
    @Column(name = "CDSOLICITACAOCOMERCIAL")
    private String cdSolicitacaoComercial;
    
    @Column(name = "CDTIPOSERVICO")
    private String cdTipoServico;
    
    @Column(name = "CDTIPOSOLICITACAOCOMERCIAL")
    private String cdTipoSolicitacaoComercial;
    
    @Column(name = "DISPONIVEL")
    private String disponivel;
    
    @Column(name = "NMSERVICOCATALOGO")
    private String nmServicoCatalogo;
    
    @Column(name = "NMSERVICOORIGEM")
    private String nmServicoOrigem;
    
    @Column(name = "NMSISTEMA")
    private String nmSistema;
    
    @Column(name = "NMSOLICITACAOCOMERCIAL")
    private String nmSolicitacaoComercial;
    
    @Column(name = "NMTIPOSERVICO")
    private String nmTipoServico;
    
    @Column(name = "NMTIPOSOLICITACAOCOMERCIAL")
    private String nmTipoSolicitacaoComercial;
    
    @Column(name = "PRAZOMAXIMOATENDIMENTO")
    private Integer prazoMaximoAtendimento;
    
    @Column(name = "PRAZOMAXIMOVIGENCIA")
    private Integer prazoMaximoVigencia;

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

    public String getCdSolicitacaoComercial() {
        return cdSolicitacaoComercial;
    }

    public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
        this.cdSolicitacaoComercial = cdSolicitacaoComercial;
    }

    public String getCdTipoServico() {
        return cdTipoServico;
    }

    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }

    public String getCdTipoSolicitacaoComercial() {
        return cdTipoSolicitacaoComercial;
    }

    public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
        this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
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

    public String getNmSistema() {
        return nmSistema;
    }

    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
    }

    public String getNmSolicitacaoComercial() {
        return nmSolicitacaoComercial;
    }

    public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
        this.nmSolicitacaoComercial = nmSolicitacaoComercial;
    }

    public String getNmTipoServico() {
        return nmTipoServico;
    }

    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }

    public String getNmTipoSolicitacaoComercial() {
        return nmTipoSolicitacaoComercial;
    }

    public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
        this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
    }

    public Integer getPrazoMaximoAtendimento() {
        return prazoMaximoAtendimento;
    }

    public void setPrazoMaximoAtendimento(Integer prazoMaximoAtendimento) {
        this.prazoMaximoAtendimento = prazoMaximoAtendimento;
    }

    public Integer getPrazoMaximoVigencia() {
        return prazoMaximoVigencia;
    }

    public void setPrazoMaximoVigencia(Integer prazoMaximoVigencia) {
        this.prazoMaximoVigencia = prazoMaximoVigencia;
    }

    public Long getIdSolicitacaoComercial() {
        return idSolicitacaoComercial;
    }

    public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
        this.idSolicitacaoComercial = idSolicitacaoComercial;
    }
    
}
