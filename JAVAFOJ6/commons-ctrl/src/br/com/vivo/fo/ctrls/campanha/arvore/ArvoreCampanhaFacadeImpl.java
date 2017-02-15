package br.com.vivo.fo.ctrls.campanha.arvore;

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
import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument;
import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ArvoreCampanhaFacade", mappedName = "ArvoreCampanhaFacade")
@Local(ArvoreCampanhaFacade.class)
@Session(ejbName = "ArvoreCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArvoreCampanhaFacadeImpl implements ArvoreCampanhaFacade {

    @EJB
    private TuxedoServiceCall       tuxedo;

    private static transient Logger log              = new Logger("campanha");

    /**
     * @common:operation
     */
    public ItemArvoreVO getArvoreCampanha(String user, String idCampanha, String idSubCampanha, String idCanalCampanha, String xmlParam) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCampanha>").append(idCampanha).append("</idCampanha><idSubCampanha>").append(idSubCampanha);
            xmlINBuffer.append("</idSubCampanha><idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>").append(xmlParam);

            String xmlIN = XmlManager.MakeXmlIn(user, "GETARVCAMP", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return ItemArvoreVODocument.Factory.parse(xmlOUT).getItemArvoreVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:getArvoreCampanha(" + user + ", " + idSubCampanha + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:getArvoreCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:getArvoreCampanha(" + user + ", " + idSubCampanha + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:getArvoreCampanha(" + user + ", " + idSubCampanha + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** DELETE ARVORE SUBCAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delArvoreSubCampanha(String user, String idSubCampanhaFixa, String idSubCampanhaHistorico) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubCampanhaFixa>").append(idSubCampanhaFixa).append("</idSubCampanhaFixa>");
            xmlINBuffer.append("<idSubCampanhaHistorico>").append(idSubCampanhaHistorico).append("</idSubCampanhaHistorico>");

            String xmlIN = XmlManager.MakeXmlIn(user, "DELSUBCAMPANHA", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:delArvoreSubCampanha(" + user + ", " + idSubCampanhaFixa + ", " + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:delArvoreSubCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:delArvoreSubCampanha(" + user + ", " + idSubCampanhaFixa + ", " + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:delArvoreSubCampanha(" + user + ", " + idSubCampanhaFixa + ", " + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** DELETE ARVORE CANAL CAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delArvoreCanalCampanha(String user, String idCanalCampanha) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlInBuffer = new StringBuffer("<idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>");

            String xmlIN = xmlInBuffer.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "DELCANALCAMP", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:delArvoreCanalCampanha(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:delArvoreCanalCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:delArvoreCanalCampanha(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:delArvoreCanalCampanha(" + user + ", " + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** DELETE ARVORE PERGUNTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delArvorePergunta(String user, String idCanalCampanha, String idPergunta) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlInBuffer = new StringBuffer();

            xmlInBuffer.append("<idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>");
            xmlInBuffer.append("<idPergunta>").append(idPergunta).append("</idPergunta>");

            String xmlIN = XmlManager.MakeXmlIn(user, "DELPERGUNTA", xmlInBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:delArvorePergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:delArvorePergunta", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:delArvorePergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:delArvorePergunta(" + user + ", " + idPergunta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** DELETE ARVORE RESPOSTA ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delArvoreResposta(String user, String idResposta) throws TuxedoException, FacadeException {
        try {
            String xmlIN = "<idResposta>" + idResposta + "</idResposta>";
            xmlIN = XmlManager.MakeXmlIn(user, "DELRESPOSTA", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:delArvoreResposta(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:delArvoreResposta", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:delArvoreResposta(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:delArvoreResposta(" + user + ", " + idResposta + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** DELETE ARVORE STATUS MOTIVO E SUBMOTIVO ****************************************************************************/

    /**
     * @common:operation
     */
    public RetornoVO delArvoreMotivoCampanha(String user, String idSubCampanhaHistorico, String idTipoStatusCampanha) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<idSubCampanhaHistorico>").append(idSubCampanhaHistorico);
            xmlInBuffer.append("</idSubCampanhaHistorico><idTipoStatusCampanha>").append(idTipoStatusCampanha).append("</idTipoStatusCampanha>");

            String xmlIN = XmlManager.MakeXmlIn(user, "DELMOTIVOCAMP", xmlInBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreCampanhaFacadeImpl:delArvoreMotivoCampanha(" + user + ", " + idSubCampanhaHistorico + ", " + idTipoStatusCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreCampanhaFacadeImpl:delArvoreMotivoCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ArvoreCampanhaFacadeImpl:delArvoreMotivoCampanha(" + user + ", " + idSubCampanhaHistorico + ", " + idTipoStatusCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ArvoreCampanhaFacadeImpl:delArvoreMotivoCampanha(" + user + ", " + idSubCampanhaHistorico + ", " + idTipoStatusCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
