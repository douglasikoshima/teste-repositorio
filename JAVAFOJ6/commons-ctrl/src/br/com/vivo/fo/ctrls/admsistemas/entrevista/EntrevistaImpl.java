package br.com.vivo.fo.ctrls.admsistemas.entrevista;

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
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaPesquisaVODocument.AdmArvoreBaixaPesquisaVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Entrevista", mappedName = "Entrevista")
@Local(Entrevista.class)
@Session(ejbName = "Entrevista", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EntrevistaImpl implements Entrevista {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmArvoreBaixaContainerVO carregaArvoreBaixaAssociada(AdmArvoreBaixaPesquisaVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("EntrevistaImpl:carregaArvoreBaixaAssociada(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", "");
            xmlIN = XmlManager.MakeXmlIn(user, "CttBxaRelacao", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = null;
            AdmArvoreBaixaContainerVODocument doc = AdmArvoreBaixaContainerVODocument.Factory.parse(xmlOUT);
            admArvoreBaixaContainerVO = doc.getAdmArvoreBaixaContainerVO();

            return admArvoreBaixaContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - EntrevistaImpl:carregaArvoreBaixaAssociada(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: EntrevistaImpl:carregaArvoreBaixaAssociada", ex));

        } catch (Exception ex) {
            log.error("Exception - EntrevistaImpl:carregaArvoreBaixaAssociada(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void removerItemArvoreBaixa(AdmArvoreBaixaPesquisaVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("EntrevistaImpl:removerItemArvoreBaixa(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CTTBXARELACIO", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - EntrevistaImpl:removerItemArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: EntrevistaImpl:removerItemArvoreBaixa", ex));

        } catch (Exception ex) {
            log.error("Exception - EntrevistaImpl:removerItemArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void adicionarItemArvoreBaixa(AdmArvoreBaixaPesquisaVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("EntrevistaImpl:adicionarItemArvoreBaixa(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CTTBXARELACIO", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - EntrevistaImpl:adicionarItemArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: EntrevistaImpl:adicionarItemArvoreBaixa", ex));

        } catch (Exception ex) {
            log.error("Exception - EntrevistaImpl:adicionarItemArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
