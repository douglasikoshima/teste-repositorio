package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GERENCREGRA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "GERENCREGRA_SQ", sequenceName = "CATALOGOPRS_OW.GERENCREGRA_SQ", allocationSize = 1)
public class GerencRegra implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3198680340387084391L;

	@Id
	@Column(name = "IDGERENCREGRA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GERENCREGRA_SQ")
	private Long idGerencRegra;
	
	@Column(name = "DTINICIAL")
	private Calendar dtInicial;
	
	@Column(name = "DTFINAL")
	private Calendar dtFinal;

	@ManyToOne
	@JoinColumn(name = "IDSERVICO")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "IDCANALATENDIMENTO")
	private CanalAtendimento canalAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "IDREGRAPRIORIDADEALTA")
	private RegraPrioridadeAlta regraPrioridadeAlta;
	
	@ManyToOne
	@JoinColumn(name = "IDINDICADORCOMERCIAL")
	private IndicadorComercial indicadorComercial;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public Calendar getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Calendar dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Calendar getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Long getIdGerencRegra() {
		return idGerencRegra;
	}

	public void setIdGerencRegra(Long idGerencRegra) {
		this.idGerencRegra = idGerencRegra;
	}

	public IndicadorComercial getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(IndicadorComercial indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public RegraPrioridadeAlta getRegraPrioridadeAlta() {
		return regraPrioridadeAlta;
	}

	public void setRegraPrioridadeAlta(RegraPrioridadeAlta regraPrioridadeAlta) {
		this.regraPrioridadeAlta = regraPrioridadeAlta;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}


	
