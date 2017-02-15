package br.com.vivo.fo.ctrls.VOLE.banner;

import javax.ejb.Local;

@SuppressWarnings({"rawtypes"})
@Local
public interface BannerFacade {

    public java.util.HashMap buscarListaTiposBanner() throws java.lang.Exception;

    public java.util.HashMap buscarListaAreasBanner() throws java.lang.Exception;

    public br.com.vivo.fo.dbclasses.AcessoBannerVOLE buscarDadosBanner(java.lang.String idUsuario, long idBanner) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLE.banner.PaginacaoBannersVOLE buscarListaBanners(java.lang.String idUsuario, java.lang.String[] areas, int nrPagina, int itensPorPagina) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLE.banner.PaginacaoBannersVOLE buscarListaRelacionamentos(java.lang.String idUsuario, java.lang.String[] areas, java.lang.String[] uf, int nrPagina, int itensPorPagina) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirBanner(java.lang.String idUsuario, br.com.vivo.fo.dbclasses.AcessoBannerVOLE banner) throws br.com.vivo.fo.exception.FacadeException;

    public void alterarBanner(java.lang.String idUsuario, br.com.vivo.fo.dbclasses.AcessoBannerVOLE banner) throws br.com.vivo.fo.exception.FacadeException;

    public void atualizarRelacionamentoBannerVOLE(java.lang.String idUsuario, java.lang.String operacao, long[] idRelacionamentoBannerVOLE, long idBanner, java.lang.String urlBanner) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLE.banner.PaginacaoBannersVOLE buscarListaBannersPorArea(java.lang.String idUsuario, int idAreaBanner, int nrPagina, int itensPorPagina) throws br.com.vivo.fo.exception.FacadeException;
}

