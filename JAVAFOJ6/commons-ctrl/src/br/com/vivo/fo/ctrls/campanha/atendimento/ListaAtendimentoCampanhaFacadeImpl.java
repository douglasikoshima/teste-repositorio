package br.com.vivo.fo.ctrls.campanha.atendimento;

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
import br.com.vivo.fo.campanha.vo.ContatoAtendimentoCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.ContatoAtendimentoCampanhaVODocument.ContatoAtendimentoCampanhaVO;
import br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "ListaAtendimentoCampanhaFacade", mappedName = "ListaAtendimentoCampanhaFacade")
@Local(ListaAtendimentoCampanhaFacade.class)
@Session(ejbName = "ListaAtendimentoCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ListaAtendimentoCampanhaFacadeImpl implements ListaAtendimentoCampanhaFacade {

    @EJB
    private TuxedoServiceCall       tuxedo;

    private static transient Logger log              = new Logger("campanha");

    /************************************** Carregar Listas *******************************************************************************************************/

    /**
     * @common:operation
     */
    public ListaAtendimentoCampanhaVO getListaAtendimentoCampanha(String user) throws TuxedoException, FacadeException {
        try {

            String xmlOUT = ConstantesCRM.SVAZIO;
            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"GETLISTACONTEU",
            // "<operacao>0</operacao>"));

            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "GETLISTACONTEU", "<operacao>0</operacao>"));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaAtendimentoCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaAtendimentoCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaAtendimentoCampanhaVO getListaAtendimentoCampanhaLinha(String user, String linha) throws TuxedoException, FacadeException {
        try {
            String xmlIN = new StringBuffer("<operacao>2</operacao><nrTelefone>").append(linha).append("</nrTelefone>").toString();

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"GETLISTACONTEU",
                               // xmlIN));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "GETLISTACONTEU", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaAtendimentoCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaAtendimentoCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanhaLinha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanhaLinha", ex));

        } catch (Exception ex) {
            log.error("Exception - ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanhaLinha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ContatoAtendimentoCampanhaVO getContatoAtendimentoCampanha(String user, String idCanalCampanha) throws TuxedoException, FacadeException {
        try {
            String xmlIN = new StringBuffer("<idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>").append("<operacao>1</operacao>").toString();

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"GETLISTACONTEU",
                               // xmlIN));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "GETLISTACONTEU", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ContatoAtendimentoCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getContatoAtendimentoCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaAtendimentoCampanhaFacadeImpl:getContatoAtendimentoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaAtendimentoCampanhaFacadeImpl:getContatoAtendimentoCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - ListaAtendimentoCampanhaFacadeImpl:getContatoAtendimentoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setQuestionarioStatus(String user, String idAtendimentoCampanha, String idTipoStatusCampanha, String idTipoMotivoCampanha, String idTipoSubMotivoCampanha, String idSubCampanhaHistorico) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlIN = new StringBuffer();
            xmlIN.append("<idSubCampanhaHistorico>").append(idSubCampanhaHistorico).append("</idSubCampanhaHistorico>");
            xmlIN.append("<idTipoStatusCampanha>").append(idTipoStatusCampanha).append("</idTipoStatusCampanha>");
            xmlIN.append("<idTipoMotivoCampanha>").append(idTipoMotivoCampanha).append("</idTipoMotivoCampanha>");
            xmlIN.append("<idTipoSubMotivoCampanha>").append(idTipoSubMotivoCampanha).append("</idTipoSubMotivoCampanha>");
            xmlIN.append("<idAtendimentoCampanha>").append(idAtendimentoCampanha).append("</idAtendimentoCampanha>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"UPDSTMTCAMP",
            // xmlIN.toString()));

            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "UPDSTMTCAMP", xmlIN.toString()));

            /*
             * }catch(XmlException ex){ log.error(
             * "XmlException - ListaAtendimentoCampanhaFacadeImpl:setQuestionarioStatus("
             * + user + ")- [" + ex.getMessage() + "]"); throw(new
             * FacadeException(
             * "Erro na montagem do XML de entrada: ListaAtendimentoCampanhaFacadeImpl:setQuestionarioStatus"
             * , ex));
             */
        } catch (Exception ex) {
            log.error("Exception - ListaAtendimentoCampanhaFacadeImpl:setQuestionarioStatus(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaAtendimentoCampanhaVO getListaPriorizacaoCampanha(String user) throws TuxedoException, FacadeException {
        try {
            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"GETLISTACONTEU",
                               // "<operacao>3</operacao>"));

            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "GETLISTACONTEU", "<operacao>3</operacao>"));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaAtendimentoCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaAtendimentoCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaAtendimentoCampanhaFacadeImpl:getListaPriorizacaoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaAtendimentoCampanhaFacadeImpl:getListaPriorizacaoCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - ListaAtendimentoCampanhaFacadeImpl:getListaAtendimentoCampanha(" + user + ")- [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
