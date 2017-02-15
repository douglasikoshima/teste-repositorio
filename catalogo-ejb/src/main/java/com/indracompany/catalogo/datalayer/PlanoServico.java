package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANOSERVICO", schema = "CATALOGOPRS_OW")
public class PlanoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoServico() {}
	
	@Id
	@Column(name = "IDPLANOSERVICO")
	private Integer idPlanoServico;

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

    @Column(name = "INMEMBER")
	private String inmMember;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Classificacaoplservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCLASSIFICACAOPLSERVICO")
	private ClassificacaoPlServico classificacaoPlServico;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	//bi-directional many-to-one association to Tiporelacionamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPORELACIONAMENTO")
	private TipoRelacionamento tipoRelacionamento;

	public ClassificacaoPlServico getClassificacaoPlServico() {
		return classificacaoPlServico;
	}

	public void setClassificacaoPlServico(
			ClassificacaoPlServico classificacaoPlServico) {
		this.classificacaoPlServico = classificacaoPlServico;
	}

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

	public Integer getIdPlanoServico() {
		return idPlanoServico;
	}

	public void setIdPlanoServico(Integer idPlanoServico) {
		this.idPlanoServico = idPlanoServico;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInmMember() {
		return inmMember;
	}

	public void setInmMember(String inmMember) {
		this.inmMember = inmMember;
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public TipoRelacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}
}