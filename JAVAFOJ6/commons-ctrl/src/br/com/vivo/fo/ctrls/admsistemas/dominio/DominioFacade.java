package br.com.vivo.fo.ctrls.admsistemas.dominio;

import javax.ejb.Local;

@Local
public interface DominioFacade {

    public void removeDominio(br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO admTabelaDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO listaDominios(br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO admTabelaDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO addDominio(br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO admTabelaDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO updateDominio(br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO admTabelaDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
