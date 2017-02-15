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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Luciano
 *
 */
@Entity
@SequenceGenerator(name = "MULTIMIDIA_SQ" ,sequenceName = "CATALOGOPRS_OW.MULTIMIDIA_SQ", allocationSize = 1)
@Table(name="MULTIMIDIA", schema = "CATALOGOPRS_OW")
public class Multimidia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MULTIMIDIA_SQ")
	@Column(name="IDMULTIMIDIA")
	private Integer idMultimidia;
	
	@Column(name="DTCRIACAO")
	private Date dataCriacao;
	
	@Column(name="DTULTIMAALTERACAO")
	private Date dataUltimaAlteracao;
	
	@Column(name="NMUSUARIOCRIACAO")
	private String nomeUsuarioCriacao;
	
	@Column(name="NMUSUARIOALTERACAO")
	private String nomeUsuarioUltimaAlteracao;
	
	@Column(name="NMMULTIMIDIA")
	private String nomeMultimidia;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOR")
	private Cor cor;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTO")
	private GrupoProduto grupoProduto;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOMULTIMIDIA")
	private TipoMultimidia tipoMultimidia;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCLASSIFICACAO")
	private ClassificacaoMultimidia classificacao;
	
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
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
	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}
	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}
	public Integer getIdMultimidia() {
		return idMultimidia;
	}
	public void setIdMultimidia(Integer idMultimidia) {
		this.idMultimidia = idMultimidia;
	}
	public String getNomeMultimidia() {
		return nomeMultimidia;
	}
	public void setNomeMultimidia(String nomeMultimidia) {
		this.nomeMultimidia = nomeMultimidia;
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
	public TipoMultimidia getTipoMultimidia() {
		return tipoMultimidia;
	}
	public void setTipoMultimidia(TipoMultimidia tipoMultimidia) {
		this.tipoMultimidia = tipoMultimidia;
	}
	public ClassificacaoMultimidia getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(ClassificacaoMultimidia classificacao) {
		this.classificacao = classificacao;
	}
	
}