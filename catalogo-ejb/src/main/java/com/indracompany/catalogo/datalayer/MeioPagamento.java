package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "MeioPagamento.findAll", query = "SELECT mp FROM MeioPagamento mp order by mp.nmMeioPagamento")
@Table(name="MEIOPAGAMENTO", schema = "CATALOGOPRS_OW")
public class MeioPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MeioPagamento() {}
	
	@Id
	@Column(name = "IDMEIOPAGAMENTO")
	private Integer idMeioPagamento;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMMEIOPAGAMENTO")
	private String nmMeioPagamento;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGMEIOPAGAMENTO")
	private String sgMeioPagamento;

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

	public Integer getIdMeioPagamento() {
		return idMeioPagamento;
	}

	public void setIdMeioPagamento(Integer idMeioPagamento) {
		this.idMeioPagamento = idMeioPagamento;
	}

	public String getNmMeioPagamento() {
		return nmMeioPagamento;
	}

	public void setNmMeioPagamento(String nmMeioPagamento) {
		this.nmMeioPagamento = nmMeioPagamento;
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

	public String getSgMeioPagamento() {
		return sgMeioPagamento;
	}

	public void setSgMeioPagamento(String sgMeioPagamento) {
		this.sgMeioPagamento = sgMeioPagamento;
	}
}