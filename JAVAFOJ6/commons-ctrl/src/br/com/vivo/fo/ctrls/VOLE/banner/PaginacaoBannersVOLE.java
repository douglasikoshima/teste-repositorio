package br.com.vivo.fo.ctrls.VOLE.banner;

import br.com.vivo.fo.dbclasses.AcessoBannerVOLE;
import br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE;
import java.io.Serializable;

public class PaginacaoBannersVOLE implements Serializable {

    static final long serialVersionUID = 155216598744435L;
    private int paginaAtual;
    private int totalItens;
    private boolean ultimaPagina;
    private AcessoBannerVOLE[] listaBanners;
    private RelacionamentoBannerVOLE[] listaRelacionamentos;

    public PaginacaoBannersVOLE() {
        this.paginaAtual = 1;
        this.ultimaPagina = false;
        this.listaBanners = new AcessoBannerVOLE[0];
        this.listaRelacionamentos = new RelacionamentoBannerVOLE[0];
    }

    ;

    public int getPaginaAtual() {
        return this.paginaAtual;
    }

    public void setPaginaAtual(int arg) {
        this.paginaAtual = arg;
    }

    public int getTotalItens() {
        return this.totalItens;
    }

    public void setTotalItens(int arg) {
        this.totalItens = arg;
    }

    public AcessoBannerVOLE[] getListaBanners() {
        return this.listaBanners;
    }

    public void setListaBanners(AcessoBannerVOLE[] arg) {
        this.listaBanners = arg;
    }

    public RelacionamentoBannerVOLE[] getListaRelacionamentos() {
        return this.listaRelacionamentos;
    }

    public void setListaRelacionamentos(RelacionamentoBannerVOLE[] arg) {
        this.listaRelacionamentos = arg;
    }

    public boolean isUltimaPagina() {
        return this.ultimaPagina;
    }

    public void setUltimaPagina(boolean arg) {
        this.ultimaPagina = arg;
    }
}
