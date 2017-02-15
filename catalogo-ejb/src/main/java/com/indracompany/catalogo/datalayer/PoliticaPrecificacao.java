package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POLITICAPRECIFICACAO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "POLITICAPRECIFICACAO_SQ", sequenceName = "CATALOGOPRS_OW.POLITICAPRECIFICACAO_SQ", allocationSize = 1)
public class PoliticaPrecificacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4734223932162742109L;

	@Id
	@Column(name = "IDPOLITICAPRECIFICACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLITICAPRECIFICACAO_SQ")
	private Long idPoliticaPrecificao;
	
	@Column(name = "INCLASSESERVICOPRINCIPAL")
	private String inClasseServicoPrincipal;
	
	@Column(name = "INEMPRESAINSTALADORA")
	private String inEmpresaInstaladora;
	
	@Column(name = "INESPSERVICOPACOTE")
	private String inEspServicoPacote;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "POLITICAATRIBUTO", joinColumns = @JoinColumn(name = "IDPOLITICAPRECIFICACAO"), inverseJoinColumns = @JoinColumn(name = "IDATRIBUTO"))
	private List<Atributo> atributoList;
	
	public List<Atributo> getAtributoList() {
		return atributoList;
	}

	public void setAtributoList(List<Atributo> atributoList) {
		this.atributoList = atributoList;
	}

	public Long getIdPoliticaPrecificao() {
		return idPoliticaPrecificao;
	}

	public void setIdPoliticaPrecificao(Long idPoliticaPrecificao) {
		this.idPoliticaPrecificao = idPoliticaPrecificao;
	}

	public String getInClasseServicoPrincipal() {
		return inClasseServicoPrincipal;
	}

	public void setInClasseServicoPrincipal(String inClasseServicoPrincipal) {
		this.inClasseServicoPrincipal = inClasseServicoPrincipal;
	}

	public String getInEmpresaInstaladora() {
		return inEmpresaInstaladora;
	}

	public void setInEmpresaInstaladora(String inEmpresaInstaladora) {
		this.inEmpresaInstaladora = inEmpresaInstaladora;
	}

	public String getInEspServicoPacote() {
		return inEspServicoPacote;
	}

	public void setInEspServicoPacote(String inEspServicoPacote) {
		this.inEspServicoPacote = inEspServicoPacote;
	}	
	
	
}
