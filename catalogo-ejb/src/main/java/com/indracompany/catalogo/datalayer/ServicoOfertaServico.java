package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SERVICOOFERTASERVICO", schema = "CATALOGOPRS_OW")
public class ServicoOfertaServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoOfertaServico() {}
	
	@Id
	@Column(name = "IDSERVICOOFERTASERVICO")
	private Integer idServicoOfertaServico;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Ofertaservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOFERTASERVICO", nullable=false)
	private OfertaServico ofertaServico;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
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

	public Integer getIdServicoOfertaServico() {
		return idServicoOfertaServico;
	}

	public void setIdServicoOfertaServico(Integer idServicoOfertaServico) {
		this.idServicoOfertaServico = idServicoOfertaServico;
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

	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}