package com.indracompany.catalogo.to;

import java.io.Serializable;

/**
 * @author Luciano
 *
 */

public class PaginacaoDataTableTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer registroAtual;
	private Integer registrosPorPagina;
	private Integer totalRegistros;
	private String nomeCampoOrdenacao;
	private Boolean ordemCrescente;
	public String getNomeCampoOrdenacao() {
		return nomeCampoOrdenacao;
	}
	public void setNomeCampoOrdenacao(String nomeCampoOrdenacao) {
		this.nomeCampoOrdenacao = nomeCampoOrdenacao;
	}
	public Boolean getOrdemCrescente() {
		return ordemCrescente;
	}
	public void setOrdemCrescente(Boolean ordemCrescente) {
		this.ordemCrescente = ordemCrescente;
	}
	public Integer getRegistroAtual() {
		return registroAtual;
	}
	public void setRegistroAtual(Integer registroAtual) {
		this.registroAtual = registroAtual;
	}
	public Integer getRegistrosPorPagina() {
		return registrosPorPagina;
	}
	public void setRegistrosPorPagina(Integer registrosPorPagina) {
		this.registrosPorPagina = registrosPorPagina;
	}
	public Integer getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
}
