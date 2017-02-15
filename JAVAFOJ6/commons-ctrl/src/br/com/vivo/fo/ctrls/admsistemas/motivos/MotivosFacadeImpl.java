package br.com.vivo.fo.ctrls.admsistemas.motivos;

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
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument;
import br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MotivosFacade", mappedName = "MotivosFacade")
@Local(MotivosFacade.class)
@Session(ejbName = "MotivosFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MotivosFacadeImpl implements MotivosFacade {

    @EJB
    private TuxedoServiceCall tuxedo;
    private static Logger     log              = new Logger("admsistemas");

    /**
     * @common:operation
     */
    public WFMotivoVO[] pesquisar(String user, WFManterMotivosVO pesquisa) throws TuxedoException, FacadeException {
        log.debug(new StringBuffer("MotivosFacadeImpl:pesquisar(").append(user).append(",").append(pesquisa).append(")").toString());
        String xmlIn = pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
        try {
            String inService = TuxedoServiceBridge.getXMLRequest(user, "MOTIVOSCRUD", xmlIn);
            String xmlOut = "";// admSistemasTux.GETSERVICE(inService);
            xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFManterMotivosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFManterMotivosVO().getWFMotivoVOArray();

        } catch (XmlException e) {
            log.error("XmlException - MotivosFacadeImpl:pesquisar(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("Exception - MotivosFacadeImpl:pesquisar(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public void crudMotivos(String user, WFManterMotivosVO manterMotivos) throws TuxedoException, FacadeException {
        log.debug(new StringBuffer("MotivosFacadeImpl:crudMotivos(").append(user).append(",").append(manterMotivos).append(")").toString());
        String xmlIn = manterMotivos.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
        try {
            xmlIn = XmlManager.MakeXmlIn(user, "MOTIVOSCRUD", xmlIn);
            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIn);
            tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException e) {
            log.error("XmlException - MotivosFacadeImpl:crudMotivos(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("Exception - MotivosFacadeImpl:crudMotivos(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public WFManterMotivosVO lerMotivoAcoes(String user, WFManterMotivosVO manterMotivos) throws TuxedoException, FacadeException {
        log.debug(new StringBuffer("MotivosFacadeImpl:lerMotivoAcoes(").append(user).append(",").append(manterMotivos).append(")").toString());
        String xmlIn = manterMotivos.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
        try {
            String inService = TuxedoServiceBridge.getXMLRequest(user, "MOTIVOSASSOC", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", inService);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFManterMotivosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFManterMotivosVO();

        } catch (XmlException e) {
            log.error("XmlException - MotivosFacadeImpl:lerMotivoAcoes(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("Exception - MotivosFacadeImpl:lerMotivoAcoes(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public void gravarMotivoAcoes(String user, WFManterMotivosVO manterMotivos) throws TuxedoException, FacadeException {
        log.debug(new StringBuffer("MotivosFacadeImpl:gravarMotivoAcoes(").append(user).append(",").append(manterMotivos).append(")").toString());
        String xmlIn = manterMotivos.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
        try {
            xmlIn = XmlManager.MakeXmlIn(user, "MOTIVOSASSOC", xmlIn);
            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIn);
            tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException e) {
            log.error("XmlException - MotivosFacadeImpl:gravarMotivoAcoes(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("Exception - MotivosFacadeImpl:lerMotivoAcoes(" + user + ") - [" + e.getMessage() + "]");
            throw new FacadeException(e.getMessage(), e);
        }
    }
}
