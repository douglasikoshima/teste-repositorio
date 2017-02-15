package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_FIXA_GENERICA", schema = "CATALOGOPRS_OW")
public class VwFixaGenerica implements Serializable {

    private static final long serialVersionUID = 3248120357409554598L;

    @Id
    @Column(name = "ROWNUM")
    private BigDecimal rownum;
    
    @Column(name = "IDSERVICOSERVICO")
    private Integer idServicoServico;
    
    @Column(name = "CDCODIGOPAI")
    private String cdCodigoPai;
    
    @Column(name = "CDCODIGOFILHO")
    private String cdCodigoFilho;
    
    @Column(name = "TIPOSERVICOPAI")
    private String tipoServicoPai;
    
    @Column(name = "TIPOSERVICOFILHO")
    private String tipoServicoFilho;
    
    @Column(name = "IDSERVICOPAI")
    private Integer idServicoPai;
    
    @Column(name = "IDSERVICOFILHO")
    private Integer idServicoFilho;
    
    @Column(name = "SGTIPORELACIONAMENTO")
    private String sgTipoRelacionamento;
    
    @Column(name = "IDSISTEMA")
    private Integer idSistema;

    public String getCdCodigoFilho() {
        return cdCodigoFilho;
    }

    public String getCdCodigoPai() {
        return cdCodigoPai;
    }

    public Integer getIdServicoFilho() {
        return idServicoFilho;
    }

    public Integer getIdServicoPai() {
        return idServicoPai;
    }

    public Integer getIdServicoServico() {
        return idServicoServico;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public String getSgTipoRelacionamento() {
        return sgTipoRelacionamento;
    }

    public String getTipoServicoFilho() {
        return tipoServicoFilho;
    }

    public String getTipoServicoPai() {
        return tipoServicoPai;
    }

    public void setCdCodigoFilho(String cdCodigoFilho) {
        this.cdCodigoFilho = cdCodigoFilho;
    }

    public void setCdCodigoPai(String cdCodigoPai) {
        this.cdCodigoPai = cdCodigoPai;
    }

    public void setIdServicoFilho(Integer idServicoFilho) {
        this.idServicoFilho = idServicoFilho;
    }

    public void setIdServicoPai(Integer idServicoPai) {
        this.idServicoPai = idServicoPai;
    }

    public void setIdServicoServico(Integer idServicoServico) {
        this.idServicoServico = idServicoServico;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }

    public void setTipoServicoFilho(String tipoServicoFilho) {
        this.tipoServicoFilho = tipoServicoFilho;
    }

    public void setTipoServicoPai(String tipoServicoPai) {
        this.tipoServicoPai = tipoServicoPai;
    }

    public BigDecimal getRownum() {
        return rownum;
    }

    public void setRownum(BigDecimal rownum) {
        this.rownum = rownum;
    }
}
