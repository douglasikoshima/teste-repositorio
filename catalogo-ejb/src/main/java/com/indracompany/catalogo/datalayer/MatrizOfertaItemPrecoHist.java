package com.indracompany.catalogo.datalayer;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MATRIZOFERTAITEMPRECOHIST", schema = "CATALOGOPRS_OW")
public class MatrizOfertaItemPrecoHist {
	
	@Id
	@Column(name = "IDMATRIZOFERTAITEMPRECO") 
	private Long idMatrizOfertaItemPreco;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTINICIAL")
	private Calendar dtInicial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTFINAL") 
	private Calendar dtFinal;
	
	@Column(name = "NMUSUARIOCRIACAO") 
	private String nmUsuarioCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAO") 
	private Calendar dtCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO") 
	private String nmUsuarioAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO") 
	private Calendar dtUltimaAlteracao;

	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Calendar getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Calendar dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Calendar getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Calendar getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Calendar dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}

	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
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


}
