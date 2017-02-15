package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="RESTRICAO", schema = "CATALOGOPRS_OW")
public class Restricao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Restricao() {}
	
	@Id
	@Column(name = "IDRESTRICAO")
	private Integer idRestricao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "INRESTRICAO")
	private String inRestricao;

    @Column(name = "NMRESTRICAO")
	private String nmRestricao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
    
    @Column(name = "SQR_NUMBER")
	private String sqrNumber;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO")
	private Plano plano;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO")
	private Servico servico;

	//bi-directional many-to-one association to Restricaoitem
	@OneToMany(mappedBy="restricao")
	private List<RestricaoItem> restricaoItemList;

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdRestricao() {
		return idRestricao;
	}

	public void setIdRestricao(Integer idRestricao) {
		this.idRestricao = idRestricao;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInRestricao() {
		return inRestricao;
	}

	public void setInRestricao(String inRestricao) {
		this.inRestricao = inRestricao;
	}

	public String getNmRestricao() {
		return nmRestricao;
	}

	public void setNmRestricao(String nmRestricao) {
		this.nmRestricao = nmRestricao;
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

	public List<RestricaoItem> getRestricaoItemList() {
		return restricaoItemList;
	}

	public void setRestricaoItemList(List<RestricaoItem> restricaoItemList) {
		this.restricaoItemList = restricaoItemList;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getSqrNumber() {
		return sqrNumber;
	}

	public void setSqrNumber(String sqrNumber) {
		this.sqrNumber = sqrNumber;
	}

}