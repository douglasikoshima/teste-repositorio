package br.com.vivo.fo.dbclasses;

import java.io.Serializable;

public class GrupamentoAcesso implements Serializable {

	private static final long serialVersionUID = -7393906125979250683L;
	private String idGrupamento;
	private String siglaGrupamento;
	private String nomeGrupamento;
	private String descricaoGrupamento;
	public void setIdGrupamento(String idGrupamento) {
		this.idGrupamento = idGrupamento;
	}
	public String getIdGrupamento() {
		return idGrupamento;
	}
	public void setSiglaGrupamento(String siglaGrupamento) {
		this.siglaGrupamento = siglaGrupamento;
	}
	public String getSiglaGrupamento() {
		return siglaGrupamento;
	}
	public void setNomeGrupamento(String nomeGrupamento) {
		this.nomeGrupamento = nomeGrupamento;
	}
	public String getNomeGrupamento() {
		return nomeGrupamento;
	}
	public void setDescricaoGrupamento(String descricaoGrupamento) {
		this.descricaoGrupamento = descricaoGrupamento;
	}
	public String getDescricaoGrupamento() {
		return descricaoGrupamento;
	}
}
