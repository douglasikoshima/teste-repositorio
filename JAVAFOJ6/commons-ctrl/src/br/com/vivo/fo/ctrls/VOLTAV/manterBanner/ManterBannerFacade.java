package br.com.vivo.fo.ctrls.VOLTAV.manterBanner;

import javax.ejb.Local;

@Local
public interface ManterBannerFacade {

    public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO getParametroBuscaBanner(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO pesquisarBanner(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void incluirBanner(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO getBanner(java.lang.String xml, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
