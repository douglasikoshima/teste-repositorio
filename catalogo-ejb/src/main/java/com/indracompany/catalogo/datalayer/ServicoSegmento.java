package com.indracompany.catalogo.datalayer;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "SERVICOSUBSEGMENTO_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOSUBSEGMENTO_SQ", allocationSize = 1)
@Table(name = "SERVICOSUBSEGMENTO", schema = "CATALOGOPRS_OW")
public class ServicoSegmento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOSUBSEGMENTO_SQ")
	@Column(name = "IDSERVICOSUBSEGMENTO")
	private Long idServicoSegmento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSERVICO")
	private Servico servico;
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO")
	private Calendar dtUltimaAlteracao;
	
	@Column(name = "INEXPORTACAO")
	private String inExportacao;

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

	public Long getIdServicoSegmento() {
		return idServicoSegmento;
	}

	public void setIdServicoSegmento(Long idServicoSegmento) {
		this.idServicoSegmento = idServicoSegmento;
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

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getInExportacao() {
		return inExportacao;
	}

	public void setInExportacao(String inExportacao) {
		this.inExportacao = inExportacao;
	}
}
