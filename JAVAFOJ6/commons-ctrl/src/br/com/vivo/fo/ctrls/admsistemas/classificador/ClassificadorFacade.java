package br.com.vivo.fo.ctrls.admsistemas.classificador;

import javax.ejb.Local;

@Local
public interface ClassificadorFacade {

    public void removeClassificador(br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO admClassificadorCampoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO listaClassificador(br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO admClassificadorCampoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO addClassificador(br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO admClassificadorCampoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO updateClassificador(br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO admClassificadorCampoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
