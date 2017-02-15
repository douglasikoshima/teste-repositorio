package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author gmuniz
 *
 */
@Entity
@SequenceGenerator(name = "CLASSIFICACAOMULTIMIDIA_SQ" ,sequenceName = "CATALOGOPRS_OW.CLASSIFICACAOMULTIMIDIA_SQ", allocationSize = 1)
@Table(name="CLASSIFICACAOMULTIMIDIA", schema = "CATALOGOPRS_OW")
public class ClassificacaoMultimidia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSIFICACAOMULTIMIDIA_SQ")
	@Column(name="IDCLASSIFICACAO")
	private Integer idClassificacao;
	
	@Column(name="SGCLASSIFICACAO")
	private String siglaClassificacao;
	
	@Column(name="NMCLASSIFICACAO")
	private String nomeClassificacao;
	
	@Column(name="DTCRIACAO")
	private Date dataCriacao;
	
	@Column(name="DTALTERACAO")
	private Date dataUltimaAlteracao;
	
	@Column(name="NMUSUARIOCRIACAO")
	private String nomeUsuarioCriacao;
	
	@Column(name="NMUSUARIOALTERACAO")
	private String nomeUsuarioUltimaAlteracao;
	
	public ClassificacaoMultimidia() {
	}
	
	public ClassificacaoMultimidia(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Integer getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
	public String getNomeClassificacao() {
		return nomeClassificacao;
	}
	public void setNomeClassificacao(String nomeClassificacao) {
		this.nomeClassificacao = nomeClassificacao;
	}
	public String getNomeUsuarioCriacao() {
		return nomeUsuarioCriacao;
	}
	public void setNomeUsuarioCriacao(String nomeUsuarioCriacao) {
		this.nomeUsuarioCriacao = nomeUsuarioCriacao;
	}
	public String getNomeUsuarioUltimaAlteracao() {
		return nomeUsuarioUltimaAlteracao;
	}
	public void setNomeUsuarioUltimaAlteracao(String nomeUsuarioUltimaAlteracao) {
		this.nomeUsuarioUltimaAlteracao = nomeUsuarioUltimaAlteracao;
	}
	public String getSiglaClassificacao() {
		return siglaClassificacao;
	}
	public void setSiglaClassificacao(String siglaClassificacao) {
		this.siglaClassificacao = siglaClassificacao;
	}
	
}