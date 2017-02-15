package br.com.vivo.fo.ctrls.admsistemas.valorDominio;

import javax.ejb.Local;

@Local
public interface ValorDominioFacade {

    public void removeValorDominio(br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO admDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO listaValorDominios(br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO admDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO addValorDominio(br.com.vivo.fo.admsistemas.vo.AdmDominioValorIncluiVODocument.AdmDominioValorIncluiVO admDominioValorIncluiVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO updateValorDominio(br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO admDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO carregaComboDominio(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO carregaComboValorDominio(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument.AdmCampoDominioVO carregaParametroCampoDominio(br.com.vivo.fo.admsistemas.vo.AdmCampoContatoVODocument.AdmCampoContatoVO admCampoContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument.AdmCamposContatoVO associaDominioCampo(br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument.AdmCampoDominioVO admCampoDominioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
