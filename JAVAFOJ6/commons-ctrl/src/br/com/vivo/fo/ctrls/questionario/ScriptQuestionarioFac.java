package br.com.vivo.fo.ctrls.questionario;

import javax.ejb.Local;

@Local
public interface ScriptQuestionarioFac {

    public java.lang.String startQuestionario(java.lang.String user, java.lang.String[] dadosCamanha) throws java.lang.Exception, br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void stopQuestionario(java.lang.String user, java.lang.String idAtendimentocampanha, long qtTempoAtendimento, br.com.vivo.fo.questionario.vo.PerguntaRespondidaVODocument.PerguntaRespondidaVO[] sqRespostas, java.lang.String idPessaoUsuario, java.lang.String operacao, java.lang.String dataAniversario) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.questionario.vo.ScriptQuestionarioVODocument.ScriptQuestionarioVO getPergunta(java.lang.String user, java.lang.String idPergunta, java.lang.String idCanalCampanha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
