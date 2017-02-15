package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "TIPOFREQUENCIA_SQ", sequenceName = "CATALOGOPRS_OW.TIPOFREQUENCIA_SQ", allocationSize = 1)
@NamedQuery(name = "TipoFrequencia.findAll", query = "SELECT tf FROM TipoFrequencia tf ")
@Table(name = "TIPOFREQUENCIA", schema = "CATALOGOPRS_OW")
public class TipoFrequencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOFREQUENCIA_SQ")
	@Column(name = "IDTIPOFREQUENCIA")
	private Integer idTipoFrequencia;
	
	@Column(name = "NMTIPOFREQUENCIA")
	private String nmTipoFrequencia;
	
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "QTFREQUENCIA")
	private Integer qtFrequencia;

	public TipoFrequencia() {
    }
    
	public TipoFrequencia(Integer idTipoFrequencia) {
		this.idTipoFrequencia = idTipoFrequencia;
    }
	
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

	public Integer getIdTipoFrequencia() {
		return idTipoFrequencia;
	}

	public void setIdTipoFrequencia(Integer idTipoFrequencia) {
		this.idTipoFrequencia = idTipoFrequencia;
	}

	public String getNmTipoFrequencia() {
		return nmTipoFrequencia;
	}

	public void setNmTipoFrequencia(String nmTipoFrequencia) {
		this.nmTipoFrequencia = nmTipoFrequencia;
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

	public Integer getQtFrequencia() {
		return qtFrequencia;
	}

	public void setQtFrequencia(Integer qtFrequencia) {
		this.qtFrequencia = qtFrequencia;
	}
}