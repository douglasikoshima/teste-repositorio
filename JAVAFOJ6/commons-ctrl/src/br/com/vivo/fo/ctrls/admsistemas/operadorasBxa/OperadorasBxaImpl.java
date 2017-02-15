package br.com.vivo.fo.ctrls.admsistemas.operadorasBxa;

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
import br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "OperadorasBxa", mappedName = "OperadorasBxa")
@Local(OperadorasBxa.class)
@Session(ejbName = "OperadorasBxa", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OperadorasBxaImpl implements OperadorasBxa {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmOperadorasVO listaOperadoraApagar(AdmOperadorasVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("OperadorasBxaImpl:listaOperadoraApagar(").append(user).append(", ").append(id).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, "BXAUFOREMOVE", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadoraBxasImpl:listaOperadoraApagar(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadorasBxaImpl:listaOperadoraApagar", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadoraBxasImpl:listaOperadoraApagar(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void listaOperadoraEditada(AdmOperadorasVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("OperadorasBxaImpl:listaOperadoraEditada(").append(user).append(", ").append(id).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, "BXAUFOEDITAR", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - OperadoraBxasImpl:listaOperadoraEditada(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadoraBxasImpl:listaOperadoraEditada", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadoraBxasImpl:listaOperadoraEditada(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmOperadorasVO listaOperadoras(AdmIdBaixaVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("OperadorasBxaImpl:listaOperadoras(").append(user).append(", ").append(id).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, "BXAUFORELACAO", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadoraBxasImpl:listaOperadoras(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadoraBxasImpl:listaOperadoras", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadoraBxasImpl:listaOperadoras(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmOperadorasVO salvaOperadoras(AdmOperadorasVO operadoras, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("OperadorasBxaImpl:salvaOperadoras(").append(user).append(", ").append(operadoras).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, " BXAUFORELACIO", operadoras.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmOperadorasVODocument doc = AdmOperadorasVODocument.Factory.parse(xmlOUT);
            AdmOperadorasVO admOperadorasVO = doc.getAdmOperadorasVO();

            return admOperadorasVO;

        } catch (XmlException ex) {
            log.error("XmlException - OperadoraBxasImpl:salvaOperadoras(" + user + ", " + operadoras + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OperadoraBxasImpl:salvaOperadoras", ex));

        } catch (Exception ex) {
            log.error("Exception - OperadoraBxasImpl:salvaOperadoras(" + user + ", " + operadoras + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
