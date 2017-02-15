package br.com.vivo.fo.ctrls.admsistemas.calendario;

import javax.ejb.Local;

@SuppressWarnings({"rawtypes"})
@Local
public interface CalendarioFacade {

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO carregaTelaInsere(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvaFeriado(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvaFeriadoEditado(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removeFeriado(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.util.ArrayList listaFeriados(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvaFeriadosMoveis(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO montaListaUF(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO carregaMunicipiosPorID(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO carregaUFsPorID(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvaRelacionamentoPorUF(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvaRelacionamentoPorMunicipio(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO copiarCalendario(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO carregarPonte(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO salvarPonte(br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO feriado, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
