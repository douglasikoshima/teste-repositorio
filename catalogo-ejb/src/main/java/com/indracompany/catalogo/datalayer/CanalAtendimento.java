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
@NamedQuery(name = "CanalAtendimento.findAll", query = "SELECT ca FROM CanalAtendimento ca where ca.inDisponivel = 'S' order by ca.nmCanal")
@Table(name = "CANALATENDIMENTO", schema = "CATALOGOPRS_OW")
public class CanalAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;

    public static final Integer TELEVENDAS = 23611;

    public CanalAtendimento() {}
    
    public CanalAtendimento(Integer idCanalAtendimento) {
    	this.idCanalAtendimento = idCanalAtendimento;    	
    }
	
	@Id
	@Column(name = "IDCANALATENDIMENTO")
	private Integer idCanalAtendimento;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMCANAL")
	private String nmCanal;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGVIVONET")
	private String sgVivoNet;

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

	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}

	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmCanal() {
		return nmCanal;
	}

	public void setNmCanal(String nmCanal) {
		this.nmCanal = nmCanal;
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

	public String getSgVivoNet() {
		return sgVivoNet;
	}

	public void setSgVivoNet(String sgVivoNet) {
		this.sgVivoNet = sgVivoNet;
	}
}