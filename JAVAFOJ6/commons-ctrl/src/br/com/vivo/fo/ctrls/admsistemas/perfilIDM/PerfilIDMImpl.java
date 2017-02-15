package br.com.vivo.fo.ctrls.admsistemas.perfilIDM;

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
import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument;
import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "PerfilIDM", mappedName = "PerfilIDM")
@Local(PerfilIDM.class)
@Session(ejbName = "PerfilIDM", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilIDMImpl implements PerfilIDM {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");

    private String            xmlIn            = ConstantesCRM.SVAZIO;
    private String            xmlOut           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public IDMPerfilVO getDadosPerfilIDM(String user, IDMPerfilVO xml) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilIDMImpl:getDadosPerfilIDM(").append(user).append(", ").append(xml.xmlText()).append(")").toString());

            xmlIn = xml.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "LERIDM", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, admSistemasTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return IDMPerfilVODocument.Factory.parse(xmlOut).getIDMPerfilVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilIDMImpl:getDadosPerfilIDM(" + user + ", " + xml.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilIDMImpl:getDadosPerfilIDM", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:getDadosPerfilIDM(" + user + ", " + xml.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public IDMPerfilVO setDadosPerfilIDM(String user, IDMPerfilVO xml) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilIDMImpl:getDadosPerfilIDM(").append(user).append(", ").append(xml.xmlText()).append(")").toString());

            xmlIn = xml.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "GRVIDM", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, admSistemasTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return IDMPerfilVODocument.Factory.parse(xmlOut).getIDMPerfilVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilIDMImpl:getDadosPerfilIDM(" + user + ", " + xml.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilIDMImpl:getDadosPerfilIDM", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:getDadosPerfilIDM(" + user + ", " + xml.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
