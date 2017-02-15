package com.indracompany.catalogo.datalayer;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@SequenceGenerator(name = "PLANOSUBSEGMENTO_SQ", sequenceName = "CATALOGOPRS_OW.PLANOSUBSEGMENTO_SQ", allocationSize = 1)
@Table(name = "PLANOSUBSEGMENTO", schema = "CATALOGOPRS_OW")
public class PlanoSegmento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANOSUBSEGMENTO_SQ")
	@Column(name = "IDPLANOSUBSEGMENTO")
	private Long idPlanoSegmento;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPLANO")
	private Plano plano;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSUBSEGMENTO")
	private Segmento segmento;
	
	@Column(name = "ININFANCIA")
	private String inInfancia;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAO")
	private Calendar dtCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "INEXPORTACAO")
	private String inExportacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO")
	private Calendar dtUltimaAlteracao;
	
	public PlanoSegmento() {
		super();
	}

	public PlanoSegmento(Plano plano, Segmento segmento, String inInfancia, String nmUsuarioCriacao, Calendar dtCriacao, String inExportacao) {
		super();
		this.plano = plano;
		this.segmento = segmento;
		this.inInfancia = inInfancia;
		this.nmUsuarioCriacao = nmUsuarioCriacao;
		this.dtCriacao = dtCriacao;
		this.inExportacao = inExportacao;
	}

	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Calendar getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Calendar dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Long getIdPlanoSegmento() {
		return idPlanoSegmento;
	}

	public void setIdPlanoSegmento(Long idPlanoSegmento) {
		this.idPlanoSegmento = idPlanoSegmento;
	}

	public String getInInfancia() {
		return inInfancia;
	}

	public void setInInfancia(String inInfancia) {
		this.inInfancia = inInfancia;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public String getInExportacao() {
		return inExportacao;
	}

	public void setInExportacao(String inExportacao) {
		this.inExportacao = inExportacao;
	}
}