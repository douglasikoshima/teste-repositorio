package br.com.vivo.fo.ctrls.admsistemas.retorno;

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
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Retorno", mappedName = "Retorno")
@Local(Retorno.class)
@Session(ejbName = "Retorno", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RetornoImpl implements Retorno {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log    = new Logger("admsistemas");

    private String            xmlIN  = ConstantesCRM.SVAZIO;
    private String            xmlOUT = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmRetornoContainerVO listaTipoRetorno(AdmIdContatoVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoImpl:listaTipoRetorno(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "READRETORNO", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmRetornoContainerVODocument doc = AdmRetornoContainerVODocument.Factory.parse(xmlOUT);
            AdmRetornoContainerVO admRetornoContainerVO = doc.getAdmRetornoContainerVO();

            return admRetornoContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - RetornoImpl:listaTipoRetorno(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetornoImpl:listaTipoRetorno", ex));

        } catch (Exception ex) {
            log.error("Exception - RetornoImpl:listaTipoRetorno(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmRetornoContainerVO listaRetorno(AdmRetornoContainerVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoImpl:listaRetorno(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "READRETORNO", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmRetornoContainerVODocument doc = AdmRetornoContainerVODocument.Factory.parse(xmlOUT);
            AdmRetornoContainerVO admRetornoContainerVO = doc.getAdmRetornoContainerVO();

            return admRetornoContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - RetornoImpl:listaRetorno(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetornoImpl:listaRetorno", ex));

        } catch (Exception ex) {
            log.error("Exception - RetornoImpl:listaRetorno(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO salvaRetorno(AdmRetornoContainerVO salvar, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoImpl:salvaRetorno(" + user + ", " + salvar + ")");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "WRITERETORNO", salvar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RetornoImpl:salvaRetorno(" + user + ", " + salvar + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetornoImpl:salvaRetorno", ex));

        } catch (Exception ex) {
            log.error("Exception - RetornoImpl:salvaRetorno(" + user + ", " + salvar + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
