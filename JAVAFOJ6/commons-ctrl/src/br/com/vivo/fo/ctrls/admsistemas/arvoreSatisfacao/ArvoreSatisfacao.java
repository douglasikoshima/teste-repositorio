package br.com.vivo.fo.ctrls.admsistemas.arvoreSatisfacao;

import javax.ejb.Local;

@Local
public interface ArvoreSatisfacao {

    public br.com.vivo.fo.admsistemas.vo.AdmArvoreSatisfacaoVODocument.AdmArvoreSatisfacaoVO getListaQuestionarios(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setNovoQuestionario(java.lang.String user, java.lang.String descricao) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
