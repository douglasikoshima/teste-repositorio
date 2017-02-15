package br.com.vivo.fo.ctrls.admsistemas.link;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmListaLinkVODocument.AdmListaLinkVO;
import br.com.vivo.fo.admsistemas.vo.AdmObjetoLinkVODocument.AdmObjetoLinkVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Link", mappedName = "Link")
@Local(Link.class)
@Session(ejbName = "Link", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LinkImpl implements Link {

    @EJB
    private TuxedoServiceCall tuxedo;

    static final long         serialVersionUID = 1L;
    private String            xmlIN;
    private String            xmlOUT;
    private static Logger     log              = new Logger("admsistemas");

    /**
     * @common:operation
     */
    public AdmListaLinkVO montaTelaLink(AdmIdContatoVO idContato, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("LinkImpl:montaTelaLink(").append(idContato.toString()).append(", ").append(user).append(")").toString());

            xmlIN = idContato.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CttInfRelacao", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmListaLinkVO admListaLinkVO = null;
            AdmListaLinkVODocument doc = AdmListaLinkVODocument.Factory.parse(xmlOUT);

            admListaLinkVO = doc.getAdmListaLinkVO();

            return admListaLinkVO;

        } catch (XmlException ex) {
            log.error("XmlException - LinkImpl:montaTelaLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

        } catch (Exception ex) {
            log.error("Exception - LinkImpl:montaTelaLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public AdmListaLinkVO removeLink(AdmObjetoLinkVO link, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("LinkImpl:removeLink(").append(link.toString()).append(", ").append(user).append(")").toString());

            xmlIN = link.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CttInfRemover", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmListaLinkVO admListaLinkVO = null;
            AdmListaLinkVODocument doc = AdmListaLinkVODocument.Factory.parse(xmlOUT);

            admListaLinkVO = doc.getAdmListaLinkVO();

            return admListaLinkVO;

        } catch (XmlException ex) {
            log.error("XmlException - LinkImpl:removeLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: LinkImpl:removeLink", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - LinkImpl:removeLink(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - LinkImpl:removeLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmListaLinkVO salvaLinkEditado(AdmObjetoLinkVO link, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("LinkImpl:salvaLinkEditado(").append(link.toString()).append(", ").append(user).append(")").toString());

            xmlIN = link.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CttInfEditar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmListaLinkVODocument doc = AdmListaLinkVODocument.Factory.parse(xmlOUT);
            AdmListaLinkVO admListaLinkVO = doc.getAdmListaLinkVO();

            return admListaLinkVO;

        } catch (XmlException ex) {
            log.error("XmlException - LinkImpl:salvaLinkEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: LinkImpl:salvaLinkEditado", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - LinkImpl:salvaLinkEditado(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - LinkImpl:salvaLinkEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmListaLinkVO inserirLink(AdmListaLinkVO link, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("LinkImpl:inserirLink(").append(link.toString()).append(", ").append(user).append(")").toString());

            xmlIN = link.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CTTINFRELACIO", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmListaLinkVODocument doc = AdmListaLinkVODocument.Factory.parse(xmlOUT);
            AdmListaLinkVO admListaLinkVO = doc.getAdmListaLinkVO();

            return admListaLinkVO;

        } catch (XmlException ex) {
            log.error("XmlException - LinkImpl:inserirLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: LinkImpl:inserirLink", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - LinkImpl:inserirLink(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - LinkImpl:inserirLink(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {

        if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W") > -1) {
            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
            throw new TuxedoWarningException(xmlHeader);
        } else if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E") > -1) {
            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
            throw new TuxedoWarningException(xmlHeader);
        }

    }
}
