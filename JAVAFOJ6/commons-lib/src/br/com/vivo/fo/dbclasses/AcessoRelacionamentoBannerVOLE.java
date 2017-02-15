package br.com.vivo.fo.dbclasses;

import java.io.Serializable;
import java.sql.Date;

/*
 * Espelho da tabela do banco de dados ACESSO.RELACIONAMENTOBANNERVOLE
 */
public class AcessoRelacionamentoBannerVOLE implements Serializable {

	private static final long serialVersionUID = 1945886137023448L;

	private long idRelacionamentoBannerVOLE;
	private long idUF;
	private long idBannerVOLE;
	private long idAreaBannerVOLE;
	private long idUsuarioAlteracao;
	private Date dtUltimaAtualizacao;
	private long rNum;

	public long getIdRelacionamentoBannerVOLE() {
		return this.idRelacionamentoBannerVOLE;
	}

	public void setIdRelacionamentoBannerVOLE(long arg) {
		this.idRelacionamentoBannerVOLE = arg;
	}

	public long getIdUF() {
		return this.idUF;
	}

	public void setIdUF(long arg) {
		this.idUF = arg;
	}

	public long getIdBannerVOLE() {
		return this.idBannerVOLE;
	}

	public void setIdBannerVOLE(long arg) {
		this.idBannerVOLE = arg;
	}

	public long getIdAreaBannerVOLE() {
		return this.idAreaBannerVOLE;
	}

	public void setIdAreaBannerVOLE(long arg) {
		this.idAreaBannerVOLE = arg;
	}

	public long getIdUsuarioAlteracao() {
		return this.idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(long arg) {
		this.idUsuarioAlteracao = arg;
	}

	public Date getDtUltimaAtualizacao() {
		return this.dtUltimaAtualizacao;
	}

	public void setDtUltimaAtualizacao(Date arg) {
		this.dtUltimaAtualizacao = arg;
	}

	public long getRNum() {
		return this.rNum;
	}

	public void setRNum(long arg) {
		this.rNum = arg;
	}

}