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
@Table(name = "SERVICOINTCLIENTE", schema = "CATALOGOPRS_OW")
@NamedQuery(name = "ServicoIntCliente.findByIdServicoInteratividade", query = "SELECT c FROM ServicoIntCliente c where c.servicoInteratividade.idServicoInteratividade = :idServicoInteratividade")
@SequenceGenerator(name = "SERVICOINTCLIENTE_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOINTCLIENTE_SQ", allocationSize = 1)
public class ServicoIntCliente implements Serializable {
	private static final long serialVersionUID = 6329876178873885715L;

	public ServicoIntCliente() {}
	
	public ServicoIntCliente(Integer idServicoIntCliente) {
		this.idServicoIntCliente = idServicoIntCliente;		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOINTCLIENTE_SQ")
	@Column(name = "IDSERVICOINTCLIENTE")
	private Integer idServicoIntCliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE", nullable = false)    
	private TipoCliente tipoCliente;
	
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

	public Integer getIdServicoIntCliente() {
		return idServicoIntCliente;
	}

	public void setIdServicoIntCliente(Integer idServicoIntCliente) {
		this.idServicoIntCliente = idServicoIntCliente;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	

}


