package br.com.vivo.fo.ctrls.admsistemas.questionario;

import javax.ejb.Local;

@Local
public interface Questionario {

    public br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument.AdmVincularQuestionarioVO listaQuestionario(br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument.AdmVincularQuestionarioVO vincularQuestionarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvaOperadoras(br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument.AdmVincularQuestionarioVO admListaVincularQuestionarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removerQuestionario(br.com.vivo.fo.admsistemas.vo.AdmListaVincularQuestionarioVODocument.AdmListaVincularQuestionarioVO admListaVincularQuestionarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument.AdmQuestionarioContainerVO listaQuestionarioPesquisado(br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO admQuestionarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument.AdmQuestionarioContainerVO listaTodosQuestionarios(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void inserirQuestionario(br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO questionario, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void removerQuestionarioId(br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO questionario, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarQuestionarioEditado(br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO questionario, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
