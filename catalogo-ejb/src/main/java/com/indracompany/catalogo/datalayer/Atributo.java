package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="ATRIBUTO", schema = "CATALOGOPRS_OW")
public class Atributo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Atributo() {}
	
	@Id
	@Column(name = "IDATRIBUTO")
	private Integer idAtributo;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDOBRIGATORIO")
	private String indObrigatorio;

    @Column(name = "MASCARA")
	private String mascara;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGTIPOATRIBUTO")
	private String sgTipoAtributo;

    @Column(name = "SVCATTRDESC")
	private String svcAttrDesc;

    @Column(name = "SVCATTRNM")
	private String svcAttrNm;

    @Column(name = "SVCATTRSEQ")
	private String svcAttrSeq;

    @Column(name = "QTMAXIMAATIVACAO")
    private Long qtMaxImaAtivacao;
    
    @Column(name = "IDMASCARAATRIBUTO")
    private Long idMascaraAtributo;
    
    @Column(name = "CDATRIBUTO")
    private String cdAtributo;
    
    @Column(name = "INFIXA")
    private String inFixa;
    
	//bi-directional many-to-one association to Atributovalor
	@OneToMany(mappedBy="atributo")
	private List<AtributoValor> atributoValorList;

	//bi-directional many-to-one association to Servicoatributo
	@OneToMany(mappedBy="atributo")
	private List<ServicoAtributo> servicoAtributoList;

	public List<AtributoValor> getAtributoValorList() {
		return atributoValorList;
	}

	public void setAtributoValorList(List<AtributoValor> atributoValorList) {
		this.atributoValorList = atributoValorList;
	}

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

	public List<ServicoAtributo> getServicoAtributoList() {
		return servicoAtributoList;
	}

	public void setServicoAtributoList(List<ServicoAtributo> servicoAtributoList) {
		this.servicoAtributoList = servicoAtributoList;
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