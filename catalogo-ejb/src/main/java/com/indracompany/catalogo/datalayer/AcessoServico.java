package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="ACESSOSERVICO", schema = "CATALOGOPRS_OW")
public class AcessoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AcessoServico() {}
	
	@Id
	@Column(name = "IDACESSOSERVICO")
	private Integer idAcessoServico;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "IDPERFILSCA")
	private Integer idPerfilSCA;

    @Column(name = "INRESTRICAOATIVACAO")
	private String inRestricaoAtivacao;

    @Column(name = "INRESTRICAOCONSULTA")
	private String inRestricaoConsulta;

    @Column(name = "INRESTRICAODESATIVACAO")
	private String inRestricaoDesativacao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	//bi-directional many-to-one association to Sistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMA", nullable=false)
	private Sistema sistema;

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

	public Integer getIdAcessoServico() {
		return idAcessoServico;
	}

	public void setIdAcessoServico(Integer idAcessoServico) {
		this.idAcessoServico = idAcessoServico;
	}

	public Integer getIdPerfilSCA() {
		return idPerfilSCA;
	}

	public void setIdPerfilSCA(Integer idPerfilSCA) {
		this.idPerfilSCA = idPerfilSCA;
	}

	public String getInRestricaoAtivacao() {
		return inRestricaoAtivacao;
	}

	public void setInRestricaoAtivacao(String inRestricaoAtivacao) {
		this.inRestricaoAtivacao = inRestricaoAtivacao;
	}

	public String getInRestricaoConsulta() {
		return inRestricaoConsulta;
	}

	public void setInRestricaoConsulta(String inRestricaoConsulta) {
		this.inRestricaoConsulta = inRestricaoConsulta;
	}

	public String getInRestricaoDesativacao() {
		return inRestricaoDesativacao;
	}

	public void setInRestricaoDesativacao(String inRestricaoDesativacao) {
		this.inRestricaoDesativacao = inRestricaoDesativacao;
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
}