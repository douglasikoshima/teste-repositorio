package com.indracompany.catalogo.datalayer;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "SUBSEGMENTO_SQ", sequenceName = "CATALOGOPRS_OW.SUBSEGMENTO_SQ", allocationSize = 1)
@NamedQuery(name = "Segmento.findAll", query = "select s from Segmento s order by s.dsSegmento ")
@Table(name = "SUBSEGMENTO", schema = "CATALOGOPRS_OW")
public class Segmento {
	@Id
	@Column(name = "IDSUBSEGMENTO") 
	private Long idSegmento;
	
	@Column(name = "SGSUBSEGMENTO")
	private String sgSegmento;
	
	@Column(name = "DSSUBSEGMENTO")
	private String dsSegmento;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAO")
	private Calendar dtCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO")
	private Calendar dtUltimaAlteracao;

	public Segmento() {
		super();
	}

	public Segmento(Long idSegmento) {
		super();
		this.idSegmento = idSegmento;
	}

	public String getDsSegmento() {
		return dsSegmento;
	}

	public void setDsSegmento(String dsSegmento) {
		this.dsSegmento = dsSegmento;
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

	public Long getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(Long idSegmento) {
		this.idSegmento = idSegmento;
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

	public String getSgSegmento() {
		return sgSegmento;
	}

	public void setSgSegmento(String sgSegmento) {
		this.sgSegmento = sgSegmento;
	}
}
