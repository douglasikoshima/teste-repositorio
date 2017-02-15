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
@Table(name = "SERVICOINTTECNOLOGIA", schema = "CATALOGOPRS_OW")
@NamedQuery(name = "ServicoIntTecnologia.findByIdServicoInteratividade", query = "SELECT t FROM ServicoIntTecnologia t where t.servicoInteratividade.idServicoInteratividade = :idServicoInteratividade")
@SequenceGenerator(name = "SERVICOINTTECNOLOGIA_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOINTTECNOLOGIA_SQ", allocationSize = 1)
public class ServicoIntTecnologia implements Serializable {
	private static final long serialVersionUID = 6329876178873885715L;

	public ServicoIntTecnologia() {}
	
	public ServicoIntTecnologia(Integer idServicoIntTec) {
		this.idServicoIntTec = idServicoIntTec;		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOINTTECNOLOGIA_SQ")
	@Column(name = "IDSERVICOINTTECNOLOGIA")
	private Integer idServicoIntTec;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDTECNOLOGIA", referencedColumnName = "IDTECNOLOGIA", nullable = false)    
	private Tecnologia tecnologia;
	
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

	public Integer getIdServicoIntTec() {
		return idServicoIntTec;
	}

	public void setIdServicoIntTec(Integer idServicoIntTec) {
		this.idServicoIntTec = idServicoIntTec;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
	
}



