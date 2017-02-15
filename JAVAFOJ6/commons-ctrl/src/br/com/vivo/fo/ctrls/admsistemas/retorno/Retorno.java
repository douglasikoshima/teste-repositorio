package br.com.vivo.fo.ctrls.admsistemas.retorno;

import javax.ejb.Local;

@Local
public interface Retorno {

    public br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO listaRetorno(br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO salvaRetorno(br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO salvar, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO listaTipoRetorno(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
