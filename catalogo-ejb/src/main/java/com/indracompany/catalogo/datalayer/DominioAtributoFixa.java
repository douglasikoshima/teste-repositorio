package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="DOMINIOATRIBUTOFIXA", schema="CATALOGOPRS_OW")
@SequenceGenerator(name="DOMINIOATRIBUTOFIXA_SQ", sequenceName="CATALOGOPRS_OW.DOMINIOATRIBUTOFIXA_SQ", allocationSize = 1)
public class DominioAtributoFixa {
	
	@Id
	@Column(name = "IDDOMINIOATRIBUTOFIXA")
	private Long idDominioAtributoFixa;
	
	@Column(name = "CDDOMINIOATRIBUTO")
	private String cdDominioAtributo;
	
	@Column(name = "TXVALORDOMINIOQUALIFICADOR")
	private String txValorDominioQualificador;
	
	@Column(name = "DSDOMINIOQUALIFICADOR")
	private String dsDominioQualificador;
	
	@ManyToOne
	@JoinColumn(name = "IDATRIBUTO")
	private Atributo atributo;

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public String getCdDominioAtributo() {
		return cdDominioAtributo;
	}

	public void setCdDominioAtributo(String cdDominioAtributo) {
		this.cdDominioAtributo = cdDominioAtributo;
	}

	public String getDsDominioQualificador() {
		return dsDominioQualificador;
	}

	public void setDsDominioQualificador(String dsDominioQualificador) {
		this.dsDominioQualificador = dsDominioQualificador;
	}

	public Long getIdDominioAtributoFixa() {
		return idDominioAtributoFixa;
	}

	public void setIdDominioAtributoFixa(Long idDominioAtributoFixa) {
		this.idDominioAtributoFixa = idDominioAtributoFixa;
	}

	public String getTxValorDominioQualificador() {
		return txValorDominioQualificador;
	}

	public void setTxValorDominioQualificador(String txValorDominioQualificador) {
		this.txValorDominioQualificador = txValorDominioQualificador;
	} 
}
