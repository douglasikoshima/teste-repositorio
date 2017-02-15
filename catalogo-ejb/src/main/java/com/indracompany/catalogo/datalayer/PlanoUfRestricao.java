package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLANOUFRESTRICAO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "PLANOUFRESTRICAO_SQ", sequenceName = "CATALOGOPRS_OW.PLANOUFRESTRICAO_SQ", allocationSize = 1 )
public class PlanoUfRestricao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANOUFRESTRICAO_SQ")
	@Column(name = "IDPLANOUFRESTRICAO")
	private Long idPlanoUfRestricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPLANO")
	private Plano plano;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUF")
	private Uf uf;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAOALTERACAO")
	private Date dtCriacaoAlteracao;
	
	@Column(name = "NMUSUARIOCRIACAOALTERACAO")
	private String nmUsuarioCriacaoAlteracao;

	public PlanoUfRestricao() {
		super();
	}
	
	public Date getDtCriacaoAlteracao() {
		return dtCriacaoAlteracao;
	}

	public void setDtCriacaoAlteracao(Date dtCriacaoAlteracao) {
		this.dtCriacaoAlteracao = dtCriacaoAlteracao;
	}

	public Long getIdPlanoUfRestricao() {
		return idPlanoUfRestricao;
	}

	public void setIdPlanoUfRestricao(Long idPlanoUfRestricao) {
		this.idPlanoUfRestricao = idPlanoUfRestricao;
	}

	public String getNmUsuarioCriacaoAlteracao() {
		return nmUsuarioCriacaoAlteracao;
	}

	public void setNmUsuarioCriacaoAlteracao(String nmUsuarioCriacaoAlteracao) {
		this.nmUsuarioCriacaoAlteracao = nmUsuarioCriacaoAlteracao;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}
