package com.indracompany.catalogo.to;

import java.util.Date;

public class AtributoTO {
	
	private Integer idAtributo;

	private Date dtCriacao;

	private Date dtUltimaAlteracao;

	private String indObrigatorio;

	private String mascara;

	private String nmUsuarioAlteracao;

	private String nmUsuarioCriacao;

	private String sgTipoAtributo;

	private String svcAttrDesc;

	private String svcAttrNm;

	private String svcAttrSeq;

    private Long qtMaxImaAtivacao;
    
    private Long idMascaraAtributo;
    
    private String cdAtributo;
    
    private String inFixa;
    
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(Integer idAtributo) {
		this.idAtributo = idAtributo;
	}

	public String getIndObrigatorio() {
		return indObrigatorio;
	}

	public void setIndObrigatorio(String indObrigatorio) {
		this.indObrigatorio = indObrigatorio;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getSgTipoAtributo() {
		return sgTipoAtributo;
	}

	public void setSgTipoAtributo(String sgTipoAtributo) {
		this.sgTipoAtributo = sgTipoAtributo;
	}

	public String getSvcAttrDesc() {
		return svcAttrDesc;
	}

	public void setSvcAttrDesc(String svcAttrDesc) {
		this.svcAttrDesc = svcAttrDesc;
	}

	public String getSvcAttrNm() {
		return svcAttrNm;
	}

	public void setSvcAttrNm(String svcAttrNm) {
		this.svcAttrNm = svcAttrNm;
	}

	public String getSvcAttrSeq() {
		return svcAttrSeq;
	}

	public void setSvcAttrSeq(String svcAttrSeq) {
		this.svcAttrSeq = svcAttrSeq;
	}

	public String getCdAtributo() {
		return cdAtributo;
	}

	public void setCdAtributo(String cdAtributo) {
		this.cdAtributo = cdAtributo;
	}

	public Long getIdMascaraAtributo() {
		return idMascaraAtributo;
	}

	public void setIdMascaraAtributo(Long idMascaraAtributo) {
		this.idMascaraAtributo = idMascaraAtributo;
	}

	public String getInFixa() {
		return inFixa;
	}

	public void setInFixa(String inFixa) {
		this.inFixa = inFixa;
	}

	public Long getQtMaxImaAtivacao() {
		return qtMaxImaAtivacao;
	}

	public void setQtMaxImaAtivacao(Long qtMaxImaAtivacao) {
		this.qtMaxImaAtivacao = qtMaxImaAtivacao;
	}
	

}
