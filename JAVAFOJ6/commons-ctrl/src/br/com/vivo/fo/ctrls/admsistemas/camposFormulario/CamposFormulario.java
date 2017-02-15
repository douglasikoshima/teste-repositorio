package br.com.vivo.fo.ctrls.admsistemas.camposFormulario;

import javax.ejb.Local;

@SuppressWarnings({"rawtypes"})
@Local
public interface CamposFormulario {

    public void salvaCamposFormulario(br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO admCamposFormularioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO removeCamposFormulario(br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioSimplVODocument.AdmCamposFormularioSimplVO admCamposFormularioSimplVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO carregaCamposFormulario(br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO formVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setCamposDependentes(br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO admCamposFormularioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO getCamposDependentes(br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO formVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO getLupaCamposDependentes(java.util.HashMap hashMap, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
