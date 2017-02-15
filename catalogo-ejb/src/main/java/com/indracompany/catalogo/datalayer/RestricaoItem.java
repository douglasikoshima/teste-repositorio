package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="RESTRICAOITEM", schema = "CATALOGOPRS_OW")
public class RestricaoItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public RestricaoItem() {}
	
	@Id
	@Column(name = "IDRESTRICAOITEM")
	private Integer idRestricaoItem;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INRESTRICAOITEM")
	private String inRestricaoItem;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO")
	private Plano plano;

	//bi-directional many-to-one association to Restricao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRESTRICAO", nullable=false)
	private Restricao restricao;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO")
	private Servico servico;

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

	public Integer getIdRestricaoItem() {
		return idRestricaoItem;
	}

	public void setIdRestricaoItem(Integer idRestricaoItem) {
		this.idRestricaoItem = idRestricaoItem;
	}

	public String getInRestricaoItem() {
		return inRestricaoItem;
	}

	public void setInRestricaoItem(String inRestricaoItem) {
		this.inRestricaoItem = inRestricaoItem;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}