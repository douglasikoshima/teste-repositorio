package br.com.vivo.fo.dbclasses;

import java.io.Serializable;

public class ApoioTipoBannerVOLE implements Serializable {

    private static final long serialVersionUID = 1082043326610035L;

    private long idTipoBannerVOLE;
    private String dsTipoBannerVOLE;

    public long getIdTipoBannerVOLE() {
        return this.idTipoBannerVOLE;
    }

    public void setIdTipoBannerVOLE(long arg) {
        this.idTipoBannerVOLE = arg;
    }

    public String getDsTipoBannerVOLE() {
        return this.dsTipoBannerVOLE;
    }

    public void setDsTipoBannerVOLE(String arg) {
        this.dsTipoBannerVOLE = arg;
    }

}