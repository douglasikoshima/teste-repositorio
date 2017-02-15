package br.com.vivo.fo.ctrls.admsistemas.operadoras;

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
import br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Operadoras", mappedName = "Operadoras")
@Local(Operadoras.class)
@Session(ejbName = "Operadoras", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OperadorasImpl implements Operadoras {

    @EJB
    private TuxedoServiceCall tuxedo;

    static final long         serialVersionUID = 1L;
    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmOperadorasVO listaOperadoraApagar(AdmOperadorasVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("OperadorasImpl:listaOperadoraApagar(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CttUfoRemove", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadorasImpl:listaOperadoraApagar(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadorasImpl:listaOperadoraApagar", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadorasImpl:listaOperadoraApagar(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void listaOperadoraEditada(AdmOperadorasVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("OperadorasImpl:listaOperadoraEditada(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CttUfoEditar", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - OperadorasImpl:listaOperadoraEditada(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadorasImpl:listaOperadoraEditada", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadorasImpl:listaOperadoraEditada(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmOperadorasVO listaOperadoras(AdmIdContatoVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("OperadorasImpl:listaOperadoras(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CttUfoRelacao", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadorasImpl:listaOperadoras(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadorasImpl:listaOperadoras", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadorasImpl:listaOperadoras(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmOperadorasVO salvaOperadoras(AdmOperadorasVO operadoras, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("OperadorasImpl:salvaOperadoras(" + user + ", " + operadoras + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CTTUFORELACIO", operadoras.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadorasImpl:salvaOperadoras(" + user + ", " + operadoras + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadorasImpl:salvaOperadoras", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadorasImpl:salvaOperadoras(" + user + ", " + operadoras + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
