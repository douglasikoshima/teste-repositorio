package com.indracompany.catalogo.datalayer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ESCRITORIOVENDA", schema = "CATALOGOPRS_OW")
public class EscritorioVenda {
	
	@Id
	@Column(name = "IDESCRITORIOVENDA")
	private Long idEscritorioVenda;
	
	@Column(name = "SGESCRITORIOVENDA")
	private String sgEscritorioVenda;
	
	@Column(name = "NMESCRITORIOVENDA")
	private String nmEscritorioVenda;
	
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "NMUSUARIOULTIMAALTERACAO")
	private String nmUsuarioUltimaAlteracao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDCANALATENDIMENTO")
	private CanalAtendimento canalAtendimento;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

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

	public Long getIdEscritorioVenda() {
		return idEscritorioVenda;
	}

	public void setIdEscritorioVenda(Long idEscritorioVenda) {
		this.idEscritorioVenda = idEscritorioVenda;
	}

	public String getNmEscritorioVenda() {
		return nmEscritorioVenda;
	}

	public void setNmEscritorioVenda(String nmEscritorioVenda) {
		this.nmEscritorioVenda = nmEscritorioVenda;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getNmUsuarioUltimaAlteracao() {
		return nmUsuarioUltimaAlteracao;
	}

	public void setNmUsuarioUltimaAlteracao(String nmUsuarioUltimaAlteracao) {
		this.nmUsuarioUltimaAlteracao = nmUsuarioUltimaAlteracao;
	}

	public String getSgEscritorioVenda() {
		return sgEscritorioVenda;
	}

	public void setSgEscritorioVenda(String sgEscritorioVenda) {
		this.sgEscritorioVenda = sgEscritorioVenda;
	}
}
