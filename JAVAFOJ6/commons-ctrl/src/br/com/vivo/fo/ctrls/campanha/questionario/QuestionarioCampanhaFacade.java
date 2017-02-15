package br.com.vivo.fo.ctrls.campanha.questionario;

import javax.ejb.Local;

@Local
public interface QuestionarioCampanhaFacade {

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addPerguntaCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO perguntaCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public void setPrioridadePerguntas(java.lang.String user, java.lang.String[] idPergunta, java.lang.String[] iPrioridade) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO setPerguntaCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO perguntaCampanha) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delRespostaCampanha(java.lang.String user, java.lang.String idResposta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addRespostaCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument.CampanhaViewRespostaVO resposta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO setRespostaCampanha(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument.CampanhaViewRespostaVO resposta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument.CampanhaViewRespostaVO getRespostaCampanhaId(java.lang.String user, int idResposta, int idCanalCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.CadastroPerguntaVODocument.CadastroPerguntaVO getPergunta(java.lang.String user, int idCanalCampanha, long idPergunta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument.CampanhaExecScriptVO getListaPergunta(java.lang.String user, int idCanalCampanha, int idPergunta, int inResposta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getTipoApresPrg(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
