package br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db;

import java.io.Serializable;

public class Acao implements Serializable {

	private static final long serialVersionUID = 6634903065820455364L;

	private String cdacaoincentivo;
	private String dsacaoincentivo;
	private String dthorainicio;
	private String dthoratermino;
	private String intipoacaoincentivo;
	private String msgantesinicioacao;
	private String msgaposinicioacao;
	private String idusuarioalteracao;
	private String idusuariocadastro;
	private String dtultimaalteracao;
	private String dtcadastro;
	private String inativo;

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getCdacaoincentivo() {
		return cdacaoincentivo;
	}

	public void setCdacaoincentivo(String cdacaoincentivo) {
		this.cdacaoincentivo = cdacaoincentivo;
	}

	public String getDsacaoincentivo() {
		return dsacaoincentivo;
	}

	public void setDsacaoincentivo(String dsacaoincentivo) {
		this.dsacaoincentivo = dsacaoincentivo;
	}

	public String getDthorainicio() {
		return dthorainicio;
	}

	public void setDthorainicio(String dthorainicio) {
		this.dthorainicio = dthorainicio;
	}

	public String getDthoratermino() {
		return dthoratermino;
	}

	public void setDthoratermino(String dthoratermino) {
		this.dthoratermino = dthoratermino;
	}

	public String getIntipoacaoincentivo() {
		return intipoacaoincentivo;
	}

	public void setIntipoacaoincentivo(String intipoacaoincentivo) {
		this.intipoacaoincentivo = intipoacaoincentivo;
	}

	public String getMsgantesinicioacao() {
		return msgantesinicioacao;
	}

	public void setMsgantesinicioacao(String msgantesinicioacao) {
		this.msgantesinicioacao = msgantesinicioacao;
	}

	public String getMsgaposinicioacao() {
		return msgaposinicioacao;
	}

	public void setMsgaposinicioacao(String msgaposinicioacao) {
		this.msgaposinicioacao = msgaposinicioacao;
	}

	public String getIdusuarioalteracao() {
		return idusuarioalteracao;
	}

	public void setIdusuarioalteracao(String idusuarioalteracao) {
		this.idusuarioalteracao = idusuarioalteracao;
	}

	public String getIdusuariocadastro() {
		return idusuariocadastro;
	}

	public void setIdusuariocadastro(String idusuariocadastro) {
		this.idusuariocadastro = idusuariocadastro;
	}

	public String getDtultimaalteracao() {
		return dtultimaalteracao;
	}

	public void setDtultimaalteracao(String dtultimaalteracao) {
		this.dtultimaalteracao = dtultimaalteracao;
	}

	public String getDtcadastro() {
		return dtcadastro;
	}

	public void setDtcadastro(String dtcadastro) {
		this.dtcadastro = dtcadastro;
	}
}