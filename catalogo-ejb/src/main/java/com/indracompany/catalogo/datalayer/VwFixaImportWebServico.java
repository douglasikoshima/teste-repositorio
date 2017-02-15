package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VW_FIXA_IMPORT_WEB_SERVICO", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "VwFixaImportWebServico.findByIdArquivo", query = "select vfiws.idArquivo, vfiws.idItem, vfiws.cdServico, vfiws.nmComercial, vfiws.disponibilidade, vfiws.erros from VwFixaImportWebServico vfiws where vfiws.idArquivo = :idArquivo")

})
public class VwFixaImportWebServico implements Serializable {

    private static final long serialVersionUID = -5055998824792013177L;

    @Id
    @Column(name = "ROWNUM")
    private BigDecimal rownum;
    
    @Column(name = "IDARQUIVO")
    private Integer idArquivo;

    @Column(name = "IDITEM")
    private Integer idItem;
    
    @Column(name = "CDSERVICO")
    private String cdServico;

    @Column(name = "NMCOMERCIAL")
    private String nmComercial;

    @Column(name = "DISPONIBILIDADE")
    private String disponibilidade;
    
    @Column(name = "ERROS")
    private String erros;

    public String getCdServico() {
        return cdServico;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public String getErros() {
        return erros;
    }

    public Integer getIdArquivo() {
        return idArquivo;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public String getNmComercial() {
        return nmComercial;
    }

    public BigDecimal getRownum() {
        return rownum;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setErros(String erros) {
        this.erros = erros;
    }

    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
    }

    public void setRownum(BigDecimal rownum) {
        this.rownum = rownum;
    }
    
}
