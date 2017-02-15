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
@Table(name = "SERVICOUFRESTRICAO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "SERVICOUFRESTRICAO_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOUFRESTRICAO_SQ", allocationSize = 1 )
public class ServicoUfRestricao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOUFRESTRICAO_SQ")
	@Column(name = "IDSERVICOUFRESTRICAO")
	private Long idServicoUfRestricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSERVICO")
	private Servico servico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUF")
	private Uf uf;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAOALTERACAO")
	private Date dtCriacaoAlteracao;
	
	@Column(name = "NMUSUARIOCRIACAOALTERACAO")
	private String nmUsuarioCriacaoAlteracao;

	public ServicoUfRestricao() {
		super();
	}
	
	public Date getDtCriacaoAlteracao() {
		return dtCriacaoAlteracao;
	}

	public void setDtCriacaoAlteracao(Date dtCriacaoAlteracao) {
		this.dtCriacaoAlteracao = dtCriacaoAlteracao;
	}

	public Long getIdServicoUfRestricao() {
		return idServicoUfRestricao;
	}

	public void setIdServicoUfRestricao(Long idServicoUfRestricao) {
		this.idServicoUfRestricao = idServicoUfRestricao;
	}

	public String getNmUsuarioCriacaoAlteracao() {
		return nmUsuarioCriacaoAlteracao;
	}

	public void setNmUsuarioCriacaoAlteracao(String nmUsuarioCriacaoAlteracao) {
		this.nmUsuarioCriacaoAlteracao = nmUsuarioCriacaoAlteracao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	

}
