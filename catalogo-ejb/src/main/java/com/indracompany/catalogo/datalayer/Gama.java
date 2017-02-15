package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author gmuniz
 *
 */
@Entity
@Table(name = "GAMA", schema = "CATALOGOPRS_OW")
public class Gama implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Gama() {
	}
	
	@Id
	@Column(name = "IDGAMA")
	private Integer idGama;
	
	@Column(name = "NMGAMA")
	private String nmGama;
	
	@Column(name = "SGGAMA")
	private String sgGama;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	// bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="tecnologia")
	private List<Produto> produtoList;
	
	public Integer getIdGama() {
		return idGama;
	}
	
	public void setIdGama(Integer idGama) {
		this.idGama = idGama;
	}
	
	public String getNmGama() {
		return nmGama;
	}
	
	public void setNmGama(String nmGama) {
		this.nmGama = nmGama;
	}
	
	public String getSgGama() {
		return sgGama;
	}
	
	public void setSgGama(String sgGama) {
		this.sgGama = sgGama;
	}
	
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	
	public Date getDtCriacao() {
		return dtCriacao;
	}
	
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}
	
	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}
	
	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	
	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}
	
	public List<Produto> getProdutoList() {
		return produtoList;
	}
	
	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}
	
}