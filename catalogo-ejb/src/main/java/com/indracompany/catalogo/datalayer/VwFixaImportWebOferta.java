package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VW_FIXA_IMPORT_WEB_OFERTA", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "VwFixaImportWebOferta.findByIdArquivo", query = "select vfiwo.idArquivo, vfiwo.idItem, vfiwo.cdOferta, vfiwo.nmOferta, vfiwo.dtInicioVigencia, vfiwo.dtFimVigencia, vfiwo.cdPsLinha, " +
    		"vfiwo.cdOpcomLinha, vfiwo.cdOpComPreCadastro, vfiwo.cdPsPlano, vfiwo.cdOpComPlano, vfiwo.portabilidade, vfiwo.fwt, vfiwo.basePontual, vfiwo.inadimplente, vfiwo.fatb, " +
    		"vfiwo.produtoObrigatorio, vfiwo.speedySolo, vfiwo.erros from VwFixaImportWebOferta vfiwo where vfiwo.idArquivo = :idArquivo")

})
public class VwFixaImportWebOferta implements Serializable {
	
	private static final long serialVersionUID = -5255730250598064116L;

	@Id
    @Column(name = "ROWNUM")
    private BigDecimal rownum;
    
    @Column(name = "IDARQUIVO")
    private Integer idArquivo;

    @Column(name = "IDITEM")
    private Integer idItem;
    
    @Column(name = "CDOFERTA")
    private String cdOferta;

    @Column(name = "NMOFERTA")
    private String nmOferta;
    
    @Column(name = "DTINICIOVIGENCIA")
    private Calendar dtInicioVigencia;
    
    @Column(name = "DTFIMVIGENCIA")
    private Calendar dtFimVigencia;
    
    @Column(name = "CDPSLINHA")
    private String cdPsLinha;
    
    @Column(name = "CDOPCOMLINHA")
    private String cdOpcomLinha;
    
    @Column(name = "CDOPCOMPRECADASTRO")
    private String cdOpComPreCadastro;
    
    @Column(name = "CDPSPLANO")
    private String cdPsPlano;
    
    @Column(name = "CDOPCOMPLANO")
    private String cdOpComPlano;
    
    @Column(name = "INPORTABILIDADE")
    private String portabilidade;
    
    @Column(name = "INFWT")
    private String fwt;
    
    @Column(name = "INBASEPONTUAL")
    private String basePontual;
    
    @Column(name = "ININADIMPLENTE")
    private String inadimplente;
    
    @Column(name = "INFATB")
    private String fatb;
    
    @Column(name = "INPRODUTOOBRIGATORIO")
    private String produtoObrigatorio;
    
    @Column(name = "INSPEEDYSOLO")
    private String speedySolo;    
    
    @Column(name = "ERROS")
    private String erros;

	public String getCdOferta() {
		return cdOferta;
	}

	public void setCdOferta(String cdOferta) {
		this.cdOferta = cdOferta;
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

	public String getNmOferta() {
		return nmOferta;
	}

	public void setNmOferta(String nmOferta) {
		this.nmOferta = nmOferta;
	}

	public BigDecimal getRownum() {
		return rownum;
	}

	public void setRownum(BigDecimal rownum) {
		this.rownum = rownum;
	}

	public String getBasePontual() {
		return basePontual;
	}

	public void setBasePontual(String basePontual) {
		this.basePontual = basePontual;
	}

	public String getCdOpcomLinha() {
		return cdOpcomLinha;
	}

	public void setCdOpcomLinha(String cdOpcomLinha) {
		this.cdOpcomLinha = cdOpcomLinha;
	}

	public String getCdOpComPlano() {
		return cdOpComPlano;
	}

	public void setCdOpComPlano(String cdOpComPlano) {
		this.cdOpComPlano = cdOpComPlano;
	}

	public String getCdOpComPreCadastro() {
		return cdOpComPreCadastro;
	}

	public void setCdOpComPreCadastro(String cdOpComPreCadastro) {
		this.cdOpComPreCadastro = cdOpComPreCadastro;
	}

	public String getCdPsLinha() {
		return cdPsLinha;
	}

	public void setCdPsLinha(String cdPsLinha) {
		this.cdPsLinha = cdPsLinha;
	}

	public String getCdPsPlano() {
		return cdPsPlano;
	}

	public void setCdPsPlano(String cdPsPlano) {
		this.cdPsPlano = cdPsPlano;
	}

	public Calendar getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Calendar dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public Calendar getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Calendar dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public String getFatb() {
		return fatb;
	}

	public void setFatb(String fatb) {
		this.fatb = fatb;
	}

	public String getFwt() {
		return fwt;
	}

	public void setFwt(String fwt) {
		this.fwt = fwt;
	}

	public String getInadimplente() {
		return inadimplente;
	}

	public void setInadimplente(String inadimplente) {
		this.inadimplente = inadimplente;
	}

	public String getPortabilidade() {
		return portabilidade;
	}

	public void setPortabilidade(String portabilidade) {
		this.portabilidade = portabilidade;
	}

	public String getProdutoObrigatorio() {
		return produtoObrigatorio;
	}

	public void setProdutoObrigatorio(String produtoObrigatorio) {
		this.produtoObrigatorio = produtoObrigatorio;
	}

	public String getSpeedySolo() {
		return speedySolo;
	}

	public void setSpeedySolo(String speedySolo) {
		this.speedySolo = speedySolo;
	}

}
