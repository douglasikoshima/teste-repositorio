package br.com.vivo.fo.dbclasses;

import java.io.Serializable;

public class ApoioAreaBannerVOLE implements Serializable {

	private static final long serialVersionUID = 17030043328108L;

	private long idAreaBannerVOLE;
	private String dsAreaBannerVOLE;

	public long getIdAreaBannerVOLE() {
		return this.idAreaBannerVOLE;
	}

	public void setIdAreaBannerVOLE(long arg) {
		this.idAreaBannerVOLE = arg;
	}

	public String getDsAreaBannerVOLE() {
		return this.dsAreaBannerVOLE;
	}

	public void setDsAreaBannerVOLE(String arg) {
		this.dsAreaBannerVOLE = arg;
	}

}