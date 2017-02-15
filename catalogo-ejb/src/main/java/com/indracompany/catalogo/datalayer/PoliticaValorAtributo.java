package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="POLITICAVALORATRIBUTO", schema="CATALOGOPRS_OW")
@SequenceGenerator(name="POLITICAVALORATRIBUTO_SQ", sequenceName="CATALOGOPRS_OW.POLITICAVALORATRIBUTO_SQ", allocationSize = 1)
public class PoliticaValorAtributo {
	
	@Id
	@Column(name = "IDPOLITICAVALORATRIBUTO")
	private Long idPoliticaValorAtributo; 
	
	@ManyToOne
	@JoinColumn(name = "IDDOMINIOATRIBUTOFIXA")
	private DominioAtributoFixa dominioAtributoFixa;
	
	@ManyToOne
	@JoinColumn(name = "IDVALORPOLITICAPRECIFICACAO")
	private ValorPoliticaPrecificacao valorPoliticaPrecificacao;
	
	@ManyToOne
	@JoinColumn(name = "IDATRIBUTO")
	private Atributo atributo;
	
	public DominioAtributoFixa getDominioAtributoFixa() {
		return dominioAtributoFixa;
	}

	public void setDominioAtributoFixa(DominioAtributoFixa dominioAtributoFixa) {
		this.dominioAtributoFixa = dominioAtributoFixa;
	}

	public Long getIdPoliticaValorAtributo() {
		return idPoliticaValorAtributo;
	}

	public void setIdPoliticaValorAtributo(Long idPoliticaValorAtributo) {
		this.idPoliticaValorAtributo = idPoliticaValorAtributo;
	}

	public ValorPoliticaPrecificacao getValorPoliticaPrecificacao() {
		return valorPoliticaPrecificacao;
	}

	public void setValorPoliticaPrecificacao(
			ValorPoliticaPrecificacao valorPoliticaPrecificacao) {
		this.valorPoliticaPrecificacao = valorPoliticaPrecificacao;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	} 
	
	
}
