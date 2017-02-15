package br.com.vivo.fo.ctrls.admsistemas.camposFormulario;

import java.util.HashMap;
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
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioSimplVODocument.AdmCamposFormularioSimplVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes"})
@Stateless(name = "CamposFormulario", mappedName = "CamposFormulario")
@Local(CamposFormulario.class)
@Session(ejbName = "CamposFormulario", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CamposFormularioImpl implements CamposFormulario {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmCamposFormularioVO removeCamposFormulario(AdmCamposFormularioSimplVO admCamposFormularioSimplVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CamposFormularioImpl:removeCamposFormulario(" + user + ")");

            xmlIN = admCamposFormularioSimplVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CttFrmRelacao", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCamposFormularioVO admCamposFormularioVO = null;
            AdmCamposFormularioVODocument doc = AdmCamposFormularioVODocument.Factory.parse(xmlOUT);
            admCamposFormularioVO = doc.getAdmCamposFormularioVO();

            return admCamposFormularioVO;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:removeCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:removeCamposFormulario", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:removeCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposFormularioVO carregaCamposFormulario(AdmCamposFormularioVO formVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CamposFormularioImpl:carregaCamposFormulario(" + user + ")");

            xmlIN = formVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CttFrmRelacao", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCamposFormularioVO admCamposFormularioVO = null;
            AdmCamposFormularioVODocument doc = AdmCamposFormularioVODocument.Factory.parse(xmlOUT);
            admCamposFormularioVO = doc.getAdmCamposFormularioVO();

            return admCamposFormularioVO;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:carregaCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:carregaCamposFormulario", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:carregaCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaCamposFormulario(AdmCamposFormularioVO admCamposFormularioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CamposFormularioImpl:salvaCamposFormulario(" + user + ")");

            xmlIN = admCamposFormularioVO.xmlText();
            xmlIN = XmlManager.MakeXmlIn(user, "CTTFRMRELACIO", xmlIN.replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll("ns1:", ConstantesCRM.SVAZIO));

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN.replaceAll("vo:", ""));
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:salvaCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:salvaCamposFormulario", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:salvaCamposFormulario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setCamposDependentes(AdmCamposFormularioVO admCamposFormularioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CamposFormularioImpl:setCamposDependentes(" + user + ")");

            xmlIN = admCamposFormularioVO.xmlText();
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMPOSDPD", xmlIN.replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll("ns1:", ConstantesCRM.SVAZIO));
            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN.replaceAll("vo:", ""));
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:setCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:setCamposDependentes", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:setCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposFormularioVO getCamposDependentes(AdmCamposFormularioVO formVO, String user) throws TuxedoException, FacadeException {

        try {
            log.debug("CamposFormularioImpl:getCamposDependentes(" + user + ")");

            xmlIN = formVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMPOSDPD", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVODocument.Factory.parse(xmlOUT).getAdmCamposFormularioVO();

            return admCamposFormularioVO;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:getCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:getCamposDependentes", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:getCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmGrupoCamposDependentesVO getLupaCamposDependentes(HashMap hashMap, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CamposFormularioImpl:getLupaCamposDependentes(" + user + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMPOSDPD", XmlManager.MakeXMLFromHashMap(hashMap));
            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmGrupoCamposDependentesVO admGrupoCamposDependentesVO = AdmGrupoCamposDependentesVODocument.Factory.parse(xmlOUT).getAdmGrupoCamposDependentesVO();

            return admGrupoCamposDependentesVO;

        } catch (XmlException ex) {
            log.error("XmlException - CamposFormularioImpl:getLupaCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CamposFormularioImpl:getLupaCamposDependentes", ex));

        } catch (Exception ex) {
            log.error("Exception - CamposFormularioImpl:getLupaCamposDependentes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
