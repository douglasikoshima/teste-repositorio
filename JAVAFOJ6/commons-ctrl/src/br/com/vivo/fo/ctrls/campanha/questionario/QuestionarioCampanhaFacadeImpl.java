package br.com.vivo.fo.ctrls.campanha.questionario;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.campanha.vo.CadastroPerguntaVODocument;
import br.com.vivo.fo.campanha.vo.CadastroPerguntaVODocument.CadastroPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument.CampanhaExecScriptVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument.CampanhaViewRespostaVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "QuestionarioCampanhaFacade", mappedName = "QuestionarioCampanhaFacade")
@Local(QuestionarioCampanhaFacade.class)
@Session(ejbName = "QuestionarioCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class QuestionarioCampanhaFacadeImpl implements QuestionarioCampanhaFacade {

    @EJB
    private TuxedoServiceCall       tuxedo;

    private static transient Logger log              = new Logger("campanha");

    /*************************************** DELETE RESPOSTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delRespostaCampanha(String user, String idResposta) throws TuxedoException, FacadeException {
        try {
            log.debug("QuestionarioCampanhaFacadeImpl:delRespostaCampanha(" + user + ", " + idResposta + ")");

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idResposta>").append(idResposta).append("</idResposta>");

            String xmlIN = XmlManager.MakeXmlIn(user, "DELRESPOSTA", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - QuestionarioCampanhaFacadeImpl:delRespostaCampanha(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:delRespostaCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:delRespostaCampanha(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - QuestionarioCampanhaFacadeImpl:delRespostaCampanha(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** INSERT RESPOSTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO addRespostaCampanha(String user, CampanhaViewRespostaVO resposta) throws TuxedoException, FacadeException {

        try {

            String xmlIN = XmlManager.MakeXmlIn(user, "INSRESPOSTA", resposta.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {

            log.error("XmlException - QuestionarioCampanhaFacadeImpl:addRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:addRespostaCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:addRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {

            log.error("Exception - QuestionarioCampanhaFacadeImpl:addRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /*************************************** UPDATE RESPOSTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO setRespostaCampanha(String user, CampanhaViewRespostaVO resposta) throws TuxedoException, FacadeException {

        try {

            log.debug("QuestionarioCampanhaFacadeImpl:addRespostaCampanha(" + user + ", " + resposta + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "UPDRESPOSTA", resposta.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {

            log.error("XmlException - QuestionarioCampanhaFacadeImpl:setRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:setRespostaCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:setRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {

            log.error("Exception - QuestionarioCampanhaFacadeImpl:setRespostaCampanha(" + user + ", " + resposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /*************************************** GETRESPOSTA ****************************************************************************/

    /**
     * @common:operation
     */
    public CampanhaViewRespostaVO getRespostaCampanhaId(String user, int idResposta, int idCanalCampanha) throws TuxedoException, FacadeException {

        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idResposta>");
            xmlINBuffer.append(idResposta);
            xmlINBuffer.append("</idResposta>");
            xmlINBuffer.append("<idCanalCampanha>");
            xmlINBuffer.append(idCanalCampanha);
            xmlINBuffer.append("</idCanalCampanha>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETRESPOSTA", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return CampanhaViewRespostaVODocument.Factory.parse(xmlOUT).getCampanhaViewRespostaVO();

        } catch (XmlException ex) {

            log.error("XmlException - QuestionarioCampanhaFacadeImpl:getRespostaCampanhaId(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:getRespostaCampanhaId", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:getRespostaCampanhaId(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {

            log.error("Exception - QuestionarioCampanhaFacadeImpl:getRespostaCampanhaId(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /*************************************** GET PERGUNTA ID ****************************************************************************/

    /**
     * @common:operation
     */
    public CadastroPerguntaVO getPergunta(String user, int idCanalCampanha, long idPergunta) throws TuxedoException, FacadeException {

        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCanalCampanha>");
            xmlINBuffer.append(idCanalCampanha);
            xmlINBuffer.append("</idCanalCampanha>");
            xmlINBuffer.append("<idPergunta>");
            xmlINBuffer.append(idPergunta);
            xmlINBuffer.append("</idPergunta>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETPERGUNTA", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            return CadastroPerguntaVODocument.Factory.parse(xmlOUT).getCadastroPerguntaVO();

        } catch (XmlException ex) {

            log.error("XmlException - QuestionarioCampanhaFacadeImpl:getPergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:getPergunta", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:getPergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {

            log.error("Exception - QuestionarioCampanhaFacadeImpl:getPergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /*************************************** LISTA PERGUNTAS ****************************************************************************/

    /**
     * @common:operation
     */
    public CampanhaExecScriptVO getListaPergunta(String user, int idCanalCampanha, int idPergunta, int inResposta) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCanalCampanha>");
            xmlINBuffer.append(idCanalCampanha);
            xmlINBuffer.append("</idCanalCampanha>");
            xmlINBuffer.append("<idPergunta>");
            xmlINBuffer.append(idPergunta);
            xmlINBuffer.append("</idPergunta>");
            xmlINBuffer.append("<inResposta>");
            xmlINBuffer.append(inResposta);
            xmlINBuffer.append("</inResposta>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETPERGCNLCMP", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return CampanhaExecScriptVODocument.Factory.parse(xmlOUT).getCampanhaExecScriptVO();

        } catch (XmlException ex) {
            log.error("XmlException - QuestionarioCampanhaFacadeImpl:getListaPergunta(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:getListaPergunta", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:getListaPergunta(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - QuestionarioCampanhaFacadeImpl:getListaPergunta(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** ADD PERGUNTAS ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO addPerguntaCampanha(String user, CampanhaPerguntaVO perguntaCampanha) throws FacadeException, XmlException, TuxedoException {
        try {
            String xmlIN = XmlManager.MakeXmlIn(user, "INSPERGUNTA", perguntaCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:addPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:addPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addCampanhaPerguntaVO", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + ex.getMessage() + "]", ex);
            throw (new FacadeException(ex));
        }
    }

    /*************************************** UPD PERGUNTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO setPerguntaCampanha(String user, CampanhaPerguntaVO perguntaCampanha) throws FacadeException, XmlException, TuxedoException {

        try {

            String xmlIN = XmlManager.MakeXmlIn(user, "UPDPERGUNTA", perguntaCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:setPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:setPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setParametrosSubCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setPerguntaCampanha(" + user + ", " + "[ " + perguntaCampanha + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** GET TIPO APRESENTAÇÃO PERGUNTA ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getTipoApresPrg(String user) throws TuxedoException, FacadeException {

        try {

            log.debug("QuestionarioCampanhaFacadeImpl:getTipoApresPerg(" + user + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETTPAPRESPERG", "<getreg></getreg>");
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - QuestionarioCampanhaFacadeImpl:getTipoApresPrg(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:getTipoApresPrg", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - QuestionarioCampanhaFacadeImpl:getTipoApresPrg(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - QuestionarioCampanhaFacadeImpl:getTipoApresPrg(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** CONFIGURA ORDEM APRESENTAÇÃO PERGUNTAS ****************************************************************************/

    /**
     * @common:operation
     */
    public void setPrioridadePerguntas(String user, String[] idPergunta, String[] iPrioridade) throws FacadeException, XmlException, TuxedoException {

        try {

            log.debug("QuestionarioCampanhaFacadeImpl:setPrioridadePerguntas(" + user + ")");

            StringBuffer xmlINBuffer = new StringBuffer();
            for (int i = 0; i < idPergunta.length; i++) {
                xmlINBuffer.append("<idPergunta>");
                xmlINBuffer.append(idPergunta[i]);
                xmlINBuffer.append("</idPergunta>");
                xmlINBuffer.append("<sqApresentacao>");
                xmlINBuffer.append(i + 1);
                xmlINBuffer.append("</sqApresentacao>");
            }

            String xmlIN = XmlManager.MakeXmlIn(user, "SETORDEMAPRE", xmlINBuffer.toString());

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("QuestionarioCampanhaFacadeImpl:setPrioridadePerguntas(" + user + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("QuestionarioCampanhaFacadeImpl:setPrioridadePerguntas(" + user + " ]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioCampanhaFacadeImpl:setPrioridadePerguntas", xex));

        } catch (Exception ex) {
            log.error("QuestionarioCampanhaFacadeImpl:setPrioridadePerguntas(" + user + " ]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
