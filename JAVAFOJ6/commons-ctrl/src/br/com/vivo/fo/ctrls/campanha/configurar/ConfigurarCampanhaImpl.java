package br.com.vivo.fo.ctrls.campanha.configurar;

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
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ConfigurarCampanha", mappedName = "ConfigurarCampanha")
@Local(ConfigurarCampanha.class)
@Session(ejbName = "ConfigurarCampanha", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConfigurarCampanhaImpl implements ConfigurarCampanha {

    @EJB
    private TuxedoServiceCall       tuxedo;

    private static transient Logger log = new Logger("campanha");

    /********************************************** Lista Status, MNotivo e SubMotivo **********************************************************************************************************************/
    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getGrupoConfigurarCampanhaVO(String user, int iCampanhaID, int iSubCampanhaID, int inNegatividade, int processo) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINini = new StringBuffer("<getreg></getreg>");
            xmlINini.append("<idSubCampanhaHistorico>").append(iSubCampanhaID).append("</idSubCampanhaHistorico>");
            xmlINini.append("<inNegative>").append(inNegatividade).append("</inNegative>");
            xmlINini.append("<processo>").append(processo).append("</processo>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETTPSTATUSCA", xmlINini.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

            // return (grupoCampanhaApoio);
        } catch (XmlException ex) {
            log.error("XmlException - ConfigurarCampanhaImpl:getGrupoConfigurarCampanhaVO(" + user + ", " + iCampanhaID + ", " + iSubCampanhaID + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:getGrupoConfigurarCampanhaVO", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:getGrupoConfigurarCampanhaVO(" + user + ", " + iCampanhaID + ", " + iSubCampanhaID + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:getGrupoConfigurarCampanhaVO(" + user + ", " + iCampanhaID + ", " + iSubCampanhaID + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** GET MOTIVO ID ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getMotivoId(String user, int idCampanha, int idTipoStatusCampanha, int processo) throws TuxedoException, FacadeException {
        try {
            /*
             * operação 0 - Campanha-Configurar 1 - Campanha-Atendimento
             */
            StringBuffer xmlINini = new StringBuffer("<getreg></getreg><idSubCampanha>").append(idCampanha).append("</idSubCampanha><idTipoStatusCampanha>").append(idTipoStatusCampanha).append("</idTipoStatusCampanha><processo>").append(processo)
                    .append("</processo>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETMOTIVO", xmlINini.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConfigurarCampanhaImpl:getMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:getMotivoId", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:getMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:getMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** GET SUBMOTIVO ID ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getSubMotivoId(String user, int idCampanha, int idStatus, int idMotivo, int processo) throws TuxedoException, FacadeException {
        try {
            /*
             * operação 0 - Campanha-Configurar 1 - Campanha-Atendimento
             */
            StringBuffer xmlINini = new StringBuffer("<idSubCampanha>").append(idCampanha).append("</idSubCampanha><idTipoStatusCampanha>").append(idStatus).append("</idTipoStatusCampanha><idTipoMotivoCampanha>").append(idMotivo)
                    .append("</idTipoMotivoCampanha><processo>").append(processo).append("</processo>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETSUBMOTIVO", xmlINini.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConfigurarCampanhaImpl:getSubMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:getSubMotivoId", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:getSubMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:getSubMotivoId(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /************************************************ ADD STATUs MOTIVO, SUBMOTIVO *****************************************************************************************************************/

    /**
     * @common:operation
     */
    public void addGrupoConfigurarCampanhaVO(String user, int iSubCampanhaID, String sStatusID, String sMotivoID, String sSubMotivoID) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINini = new StringBuffer("<idSubCampanhaHistorico>").append(iSubCampanhaID).append("</idSubCampanhaHistorico>").append("<idTipoStatusCampanha>").append(sStatusID).append("</idTipoStatusCampanha>")
                    .append("<idTipoMotivoCampanha>").append(sMotivoID).append("</idTipoMotivoCampanha>").append("<idTipoSubMotivoCampanha>").append(sSubMotivoID).append("</idTipoSubMotivoCampanha>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSMOTIVOCMP", xmlINini.toString());

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (XmlException ex) {
            log.error("XmlException - ConfigurarCampanhaImpl:addGrupoConfigurarCampanhaVO(" + user + ", " + iSubCampanhaID + ", " + sStatusID + ", " + sMotivoID + ", " + sSubMotivoID + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:addGrupoConfigurarCampanhaVO", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:addGrupoConfigurarCampanhaVO(" + user + ", " + iSubCampanhaID + ", " + sStatusID + ", " + sMotivoID + ", " + sSubMotivoID + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:addGrupoConfigurarCampanhaVO(" + user + ", " + iSubCampanhaID + ", " + sStatusID + ", " + sMotivoID + ", " + sSubMotivoID + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getStatus(String user, int idSubCampanhaHistorico, int processo) throws TuxedoException, FacadeException {
        try {
            /*
             * operação 0 - Campanha-Configurar 1 - Campanha-Atendimento
             */
            StringBuffer xmlINBuffer = new StringBuffer();
            switch (processo) {
            case 0:
                xmlINBuffer.append("<getreg>GETTPSTTSCMP</getreg><processo>");
                xmlINBuffer.append(processo);
                xmlINBuffer.append("</processo>");
                break;

            case 1:
                xmlINBuffer.append("<idSubCampanhaHistorico>");
                xmlINBuffer.append(idSubCampanhaHistorico);
                xmlINBuffer.append("</idSubCampanhaHistorico><operacao>");
                xmlINBuffer.append(processo);
                xmlINBuffer.append("</operacao>");
                break;
            }

            String xmlIN = XmlManager.MakeXmlIn(user, "GETTPSTTSCMP", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConfigurarCampanhaImpl:getStatus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:getStatus", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:getStatus(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:getStatus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setPrioridadeCampanha(String user, String[] idSubCampanhaHistorico) throws FacadeException, XmlException, TuxedoException {
        try {
            log.debug(new StringBuffer("ConfigurarCampanhaImpl:setPrioridadeCampanha(").append(user).append(")").toString());
            StringBuffer xmlINin = new StringBuffer();

            for (int i = 0; i < idSubCampanhaHistorico.length; i++) {
                xmlINin.append("<idSubCampanhaHistorico>").append(idSubCampanhaHistorico[i]).append("</idSubCampanhaHistorico>").append("<sqApresentacao>").append(String.valueOf(i + 1)).append("</sqApresentacao>");
            }

            String xmlIN = XmlManager.MakeXmlIn(user, "SETORDEMCAMP", xmlINin.toString());

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ConfigurarCampanhaImpl:setPrioridadeCampanha(" + user + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ConfigurarCampanhaImpl:setPrioridadeCampanha(" + user + " ]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConfigurarCampanhaImpl:setPrioridadeCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - ConfigurarCampanhaImpl:setPrioridadeCampanha(" + user + " ]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
