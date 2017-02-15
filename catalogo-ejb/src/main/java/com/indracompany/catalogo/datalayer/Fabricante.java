package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f order by f.nmFabricante")
@Table(name = "FABRICANTE", schema = "CATALOGOPRS_OW")
public class Fabricante implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "IDFABRICANTE")
	private Integer idFabricante;

	@Column(name = "NMFABRICANTE")
	private String nmFabricante;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    public Fabricante() {}

	public Fabricante(Integer idFabricante) {
		super();
		this.idFabricante = idFabricante;
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

	public Integer getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNmFabricante() {
		return nmFabricante;
	}

	public void setNmFabricante(String nmFabricante) {
		this.nmFabricante = nmFabricante;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	
}