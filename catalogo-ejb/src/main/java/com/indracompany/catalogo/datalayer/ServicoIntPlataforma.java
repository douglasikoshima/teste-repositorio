package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "ServicoIntCanal.findByIdServicoInteratividade", query = "SELECT p FROM ServicoIntPlataforma p where p.servicoInteratividade.idServicoInteratividade = :idServicoInteratividade")
@Table(name = "SERVICOINTPLATAFORMA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "SERVICOINTPLATAFORMA_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOINTPLATAFORMA_SQ", allocationSize = 1)
public class ServicoIntPlataforma implements Serializable {
	private static final long serialVersionUID = 2472222129921051383L;

	public ServicoIntPlataforma() {}
	
	public ServicoIntPlataforma(Integer idServicoIntPlataforma) {
		this.idServicoIntPlataforma = idServicoIntPlataforma;		
	} 
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOINTPLATAFORMA_SQ")
	@Column(name = "IDSERVICOINTPLATAFORMA")
	private Integer idServicoIntPlataforma;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDPLATAFORMA", referencedColumnName = "IDPLATAFORMA", nullable = false)
	private Plataforma plataforma;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "IDSERVICOINTERATIVIDADE", referencedColumnName = "IDSERVICOINTERATIVIDADE", nullable = false)    
	private ServicoInteratividade servicoInteratividade; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

	
	
	
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

	public ServicoInteratividade getServicoInteratividade() {
		return servicoInteratividade;
	}

	public void setServicoInteratividade(ServicoInteratividade servicoInteratividade) {
		this.servicoInteratividade = servicoInteratividade;
	}	

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Integer getIdServicoIntPlataforma() {
		return idServicoIntPlataforma;
	}

	public void setIdServicoIntPlataforma(Integer idServicoIntPlataforma) {
		this.idServicoIntPlataforma = idServicoIntPlataforma;
	}
	
	

}

