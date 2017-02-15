package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TIPOPLANO", schema = "CATALOGOPRS_OW")
public class TipoPlano implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TipoPlano() {}
	
	@Id
	@Column(name = "IDTIPOPLANO")
	private Integer idTipoPlano;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "IDCATEGORIATIPOPLANO")
	private Integer idCategoriaTipoPlano;

    @Column(name = "NMTIPOPLANO")
	private String nmTipoPlano;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGTIPOPLANO")
	private String sgTipoPlano;

	//bi-directional many-to-one association to Plano
	@OneToMany(mappedBy="tipoPlano")
	private List<Plano> planoList;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA", nullable=false)
	private Plataforma plataforma;

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

	public Integer getIdCategoriaTipoPlano() {
		return idCategoriaTipoPlano;
	}

	public void setIdCategoriaTipoPlano(Integer idCategoriaTipoPlano) {
		this.idCategoriaTipoPlano = idCategoriaTipoPlano;
	}

	public Integer getIdTipoPlano() {
		return idTipoPlano;
	}

	public void setIdTipoPlano(Integer idTipoPlano) {
		this.idTipoPlano = idTipoPlano;
	}

	public String getNmTipoPlano() {
		return nmTipoPlano;
	}

	public void setNmTipoPlano(String nmTipoPlano) {
		this.nmTipoPlano = nmTipoPlano;
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

	public List<Plano> getPlanoList() {
		return planoList;
	}

	public void setPlanoList(List<Plano> planoList) {
		this.planoList = planoList;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public String getSgTipoPlano() {
		return sgTipoPlano;
	}

	public void setSgTipoPlano(String sgTipoPlano) {
		this.sgTipoPlano = sgTipoPlano;
	}
}