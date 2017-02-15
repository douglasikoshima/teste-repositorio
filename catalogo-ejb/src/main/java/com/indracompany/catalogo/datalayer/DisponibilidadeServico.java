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
@Table(name="DISPONIBILIDADESERVICO", schema = "CATALOGOPRS_OW")
public class DisponibilidadeServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DisponibilidadeServico() {}
	
	@Id
	@Column(name = "IDDISPONIBILIDADESERVICO")
	private Integer idDisponibilidadeServico;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTEFETIVACAO")
	private Date dtEfetivacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTEXPIRACAO")
	private Date dtExpiracao;

	@Column(name = "IDTPASSINATURA")
	private Integer idTpAssinatura;

	//bi-directional many-to-one association to Csa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCSA", nullable=false)
	private CSA csa;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA")
	private Plataforma plataforma;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA")
	private Tecnologia tecnologia;

	public CSA getCsa() {
		return csa;
	}

	public void setCsa(CSA csa) {
		this.csa = csa;
	}

	public Date getDtEfetivacao() {
		return dtEfetivacao;
	}

	public void setDtEfetivacao(Date dtEfetivacao) {
		this.dtEfetivacao = dtEfetivacao;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public Integer getIdDisponibilidadeServico() {
		return idDisponibilidadeServico;
	}

	public void setIdDisponibilidadeServico(Integer idDisponibilidadeServico) {
		this.idDisponibilidadeServico = idDisponibilidadeServico;
	}

	public Integer getIdTpAssinatura() {
		return idTpAssinatura;
	}

	public void setIdTpAssinatura(Integer idTpAssinatura) {
		this.idTpAssinatura = idTpAssinatura;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

    
}