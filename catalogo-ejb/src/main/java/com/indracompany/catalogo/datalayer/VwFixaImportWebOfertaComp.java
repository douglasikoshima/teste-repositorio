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
@Table(name = "VW_FIXA_IMPORT_WEB_OFERTACOMP", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "VwFixaImportWebOfertaComp.findByIdArquivo", query = "select vfiwoc.idArquivo, vfiwoc.idItem, vfiwoc.cdOferta, vfiwoc.cdPsServico, vfiwoc.cdOpComServico, vfiwoc.pzVigencia, vfiwoc.erros " +
    		"from VwFixaImportWebOfertaComp vfiwoc where vfiwoc.idArquivo = :idArquivo")

})
public class VwFixaImportWebOfertaComp implements Serializable {
	
	private static final long serialVersionUID = -4986596612879507740L;

	@Id
    @Column(name = "ROWNUM")
    private BigDecimal rownum;
    
    @Column(name = "IDARQUIVO")
    private Integer idArquivo;

    @Column(name = "IDITEM")
    private Integer idItem;
    
    @Column(name = "CDOFERTA")
    private String cdOferta;
    
    @Column(name = "CDPSSERVICO")
    private String cdPsServico;    

    @Column(name = "CDOPCOMSERVICO")
    private String cdOpComServico;
    
    @Column(name = "PZVIGENCIA")
    private String pzVigencia;    
    
    @Column(name = "ERROS")
    private String erros;

	public String getCdOferta() {
		return cdOferta;
	}

	public void setCdOferta(String cdOferta) {
		this.cdOferta = cdOferta;
	}

	public String getCdOpComServico() {
		return cdOpComServico;
	}

	public void setCdOpComServico(String cdOpComServico) {
		this.cdOpComServico = cdOpComServico;
	}

	public String getErros() {
		return erros;
	}

	public void setErros(String erros) {
		this.erros = erros;
	}

	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public BigDecimal getRownum() {
		return rownum;
	}

	public void setRownum(BigDecimal rownum) {
		this.rownum = rownum;
	}

	public String getCdPsServico() {
		return cdPsServico;
	}

	public void setCdPsServico(String cdPsServico) {
		this.cdPsServico = cdPsServico;
	}

	public String getPzVigencia() {
		return pzVigencia;
	}

	public void setPzVigencia(String pzVigencia) {
		this.pzVigencia = pzVigencia;
	}

}
