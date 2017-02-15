package br.com.vivo.fo.dbclasses;

import java.io.Serializable;

/*
 * Classe referente aos relacionamentos efetuados pela tabela ACESSO.RELACIONAMENTOBANNERVOLE
 */
public class RelacionamentoBannerVOLE implements Serializable {

	static final long serialVersionUID = 11655113702322L;

	private long idRelacionamentoBannerVOLE;
	private String sgUF;
	private long idBannerVOLE;
	private String nmBannerVOLE;
	private String dsBannerVOLE;
	private String urlBannerVOLE;
	private String dsAreaBannerVOLE;
	private long rNum;

	public long getIdRelacionamentoBannerVOLE() {
		return this.idRelacionamentoBannerVOLE;
	}

	public void setIdRelacionamentoBannerVOLE(long arg) {
		this.idRelacionamentoBannerVOLE = arg;
	}

	public String getSgUF() {
		return this.sgUF;
	}

	public void setSgUF(String arg) {
		this.sgUF = arg;
	}

	public long getIdBannerVOLE() {
		return this.idBannerVOLE;
	}

	public void setIdBannerVOLE(long arg) {
		this.idBannerVOLE = arg;
	}

	public String getNmBannerVOLE() {
		return this.nmBannerVOLE;
	}

	public void setNmBannerVOLE(String arg) {
		this.nmBannerVOLE = arg;
	}

	public String getDsBannerVOLE() {
		return this.dsBannerVOLE;
	}

	public void setDsBannerVOLE(String arg) {
		this.dsBannerVOLE = arg;
	}

	public String getUrlBannerVOLE() {
		return this.urlBannerVOLE;
	}

	public void setUrlBannerVOLE(String arg) {
		this.urlBannerVOLE = arg;
	}

	public String getDsAreaBannerVOLE() {
		return this.dsAreaBannerVOLE;
	}

	public void setDsAreaBannerVOLE(String arg) {
		this.dsAreaBannerVOLE = arg;
	}

	public long getRNum() {
		return this.rNum;
	}

	public void setRNum(long arg) {
		this.rNum = arg;
	}

}