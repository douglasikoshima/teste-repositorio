package br.com.vivo.fo.dbclasses;

import java.io.Serializable;
import java.sql.Date;

public class AcessoBannerVOLE implements Serializable {

	private static final long serialVersionUID = 1228447023338L;

	private long idBannerVOLE;
	private long rNum;
	private String nmBannerVOLE;
	private String urlBannerVOLE;
	private long idAreaBannerVOLE;
	private long idTipoBannerVOLE;
	private String dsBannerVOLE;
	private String IPBannerVOLE;
	private Date dtInicial;
	private Date dtFinal;
	private long nrContador;
	private int inContador;

	public long getIdBannerVOLE() {
		return this.idBannerVOLE;
	}

	public void setIdBannerVOLE(long arg) {
		this.idBannerVOLE = arg;
	}

	public long getRNum() {
		return this.rNum;
	}

	public void setRNum(long arg) {
		this.rNum = arg;
	}

	public String getNmBannerVOLE() {
		return this.nmBannerVOLE;
	}

	public void setNmBannerVOLE(String arg) {
		this.nmBannerVOLE = arg;
	}

	public String getUrlBannerVOLE() {
		return this.urlBannerVOLE;
	}

	public void setUrlBannerVOLE(String arg) {
		this.urlBannerVOLE = arg;
	}

	public long getIdAreaBannerVOLE() {
		return this.idAreaBannerVOLE;
	}

	public void setIdAreaBannerVOLE(long arg) {
		this.idAreaBannerVOLE = arg;
	}

	public long getIdTipoBannerVOLE() {
		return this.idTipoBannerVOLE;
	}

	public void setIdTipoBannerVOLE(long arg) {
		this.idTipoBannerVOLE = arg;
	}

	public String getDsBannerVOLE() {
		return this.dsBannerVOLE;
	}

	public void setDsBannerVOLE(String arg) {
		this.dsBannerVOLE = arg;
	}

	public String getIPBannerVOLE() {
		return this.IPBannerVOLE;
	}

	public void setIPBannerVOLE(String arg) {
		this.IPBannerVOLE = arg;
	}

	public Date getDtInicial() {
		return this.dtInicial;
	}

	public void setDtInicial(Date arg) {
		this.dtInicial = arg;
	}

	public Date getDtFinal() {
		return this.dtFinal;
	}

	public void setDtFinal(Date arg) {
		this.dtFinal = arg;
	}

	public long getNrContador() {
		return this.nrContador;
	}

	public void setNrContador(long arg) {
		this.nrContador = arg;
	}

	public int getInContador() {
		return this.inContador;
	}

	public void setInContador(int arg) {
		this.inContador = arg;
	}

}
