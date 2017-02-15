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
@NamedQuery(name = "ServicoIntCanal.findById", query = "SELECT ca FROM ServicoIntCanal ca WHERE ca.servicoInteratividade.idServicoInteratividade = :idServicoInteratividade")
@Table(name = "SERVICOINTCANAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "SERVICOINTCANAL_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOINTCANAL_SQ", allocationSize = 1)
public class ServicoIntCanal implements Serializable {
	private static final long serialVersionUID = 7368554897114125030L;
	
	public ServicoIntCanal() {}
	
    public ServicoIntCanal(Integer idServicoIntCanal) {
        this.idServicoIntCanal = idServicoIntCanal;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOINTCANAL_SQ")
	@Column(name = "IDSERVICOINTCANAL")
	private Integer idServicoIntCanal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDCANALATENDIMENTO", referencedColumnName = "IDCANALATENDIMENTO", nullable = false)
	private CanalAtendimento canalAtendimento;

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

	public Integer getIdServicoIntCanal() {
		return idServicoIntCanal;
	}

	public void setIdServicoIntCanal(Integer idServicoIntCanal) {
		this.idServicoIntCanal = idServicoIntCanal;
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

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public ServicoInteratividade getServicoInteratividade() {
		return servicoInteratividade;
	}

	public void setServicoInteratividade(ServicoInteratividade servicoInteratividade) {
		this.servicoInteratividade = servicoInteratividade;
	}
}
