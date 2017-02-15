package com.indracompany.catalogo.datalayer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="VALORPOLITICAPRECIFICACAO", schema="CATALOGOPRS_OW")
@SequenceGenerator(name="VALORPOLITICAPRECIFICACAO_SQ", sequenceName="CATALOGOPRS_OW.VALORPOLITICAPRECIFICACAO_SQ", allocationSize = 1)
public class ValorPoliticaPrecificacao {

	@Id
	@Column(name="IDVALORPOLITICAPRECIFICACAO")
	private Long idValorPoliticaPrecificacao;
	
	@ManyToOne
	@JoinColumn(name="IDESPSERVICOPACOTE")
	private EspServicoPacote espServicoPacote;
	
	@ManyToOne
	@JoinColumn(name="IDCLASSESERVICOPRINCIPAL")
	private SistemaServico classeServicoPrincipal;
	
	@ManyToOne
	@JoinColumn(name="IDEMPRESAINSTALADORA")
	private Localidade empresaInstaladora;
	
	@ManyToOne
	@JoinColumn(name="IDPOLITICAPRECIFICACAO")
	private PoliticaPrecificacao politicaPrecificacao;
	
	@OneToMany(mappedBy="valorPoliticaPrecificacao")
	private List<PoliticaValorAtributo> politicaValorAtributoList;
	
	public SistemaServico getClasseServicoPrincipal() {
		return classeServicoPrincipal;
	}

	public void setClasseServicoPrincipal(SistemaServico classeServicoPrincipal) {
		this.classeServicoPrincipal = classeServicoPrincipal;
	}

	public Localidade getEmpresaInstaladora() {
		return empresaInstaladora;
	}

	public void setEmpresaInstaladora(Localidade empresaInstaladora) {
		this.empresaInstaladora = empresaInstaladora;
	}

	public EspServicoPacote getEspServicoPacote() {
		return espServicoPacote;
	}

	public void setEspServicoPacote(EspServicoPacote espServicoPacote) {
		this.espServicoPacote = espServicoPacote;
	}

	public Long getIdValorPoliticaPrecificacao() {
		return idValorPoliticaPrecificacao;
	}

	public void setIdValorPoliticaPrecificacao(Long idValorPoliticaPrecificacao) {
		this.idValorPoliticaPrecificacao = idValorPoliticaPrecificacao;
	}

	public PoliticaPrecificacao getPoliticaPrecificacao() {
		return politicaPrecificacao;
	}

	public void setPoliticaPrecificacao(PoliticaPrecificacao politicaPrecificacao) {
		this.politicaPrecificacao = politicaPrecificacao;
	}

	public List<PoliticaValorAtributo> getPoliticaValorAtributoList() {
		return politicaValorAtributoList;
	}

	public void setPoliticaValorAtributoList(
			List<PoliticaValorAtributo> politicaValorAtributoList) {
		this.politicaValorAtributoList = politicaValorAtributoList;
	}
}
