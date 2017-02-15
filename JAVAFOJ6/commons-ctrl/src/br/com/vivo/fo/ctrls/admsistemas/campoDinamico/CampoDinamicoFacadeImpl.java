package br.com.vivo.fo.ctrls.admsistemas.campoDinamico;

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
import br.com.vivo.fo.admsistemas.vo.AdmCampoCombosVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCampoCombosVODocument.AdmCampoCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoContatoVODocument.AdmCampoContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument.AdmCamposContatoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "CampoDinamicoFacade", mappedName = "CampoDinamicoFacade")
@Local(CampoDinamicoFacade.class)
@Session(ejbName = "CampoDinamicoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CampoDinamicoFacadeImpl implements CampoDinamicoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public void removeCampo(AdmCampoContatoVO admCampoContatoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CampoDinamicoFacadeImpl:removeCampo(" + user + ")");

            xmlIN = admCampoContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMREMOVE", xmlIN);

            // new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

        } catch (XmlException ex) {
            log.error("XmlException - CampoDinamicoFacadeImpl:removeCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampoDinamicoFacadeImpl:removeCampo", ex));

        } catch (Exception ex) {
            log.error("Exception - CampoDinamicoFacadeImpl:removeCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposContatoVO listaCampos(AdmCampoContatoVO admCampoContatoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CampoDinamicoFacadeImpl:listaCampo(" + user + ")");

            xmlIN = admCampoContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMLISTA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCamposContatoVODocument.Factory.parse(xmlOUT).getAdmCamposContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampoDinamicoFacadeImpl:listaCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampoDinamicoFacadeImpl:listaCampo", ex));

        } catch (Exception ex) {
            log.error("Exception - CampoDinamicoFacadeImpl:listaCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCampoCombosVO carregaCampoCombo(String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CampoDinamicoFacadeImpl:carregaCampoCombo(" + user + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMCMB", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCampoCombosVODocument.Factory.parse(xmlOUT).getAdmCampoCombosVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampoDinamicoFacadeImpl:carregaCampoCombo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampoDinamicoFacadeImpl:carregaCampoCombo", ex));

        } catch (Exception ex) {
            log.error("Exception - CampoDinamicoFacadeImpl:carregaCampoCombo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposContatoVO addCampo(AdmCampoContatoVO admCampoContatoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CampoDinamicoFacadeImpl:addCampo(" + user + ")");

            xmlIN = admCampoContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMINSERI", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCamposContatoVODocument.Factory.parse(xmlOUT).getAdmCamposContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampoDinamicoFacadeImpl:addCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampoDinamicoFacadeImpl:addCampo", ex));

        } catch (Exception ex) {
            log.error("Exception - CampoDinamicoFacadeImpl:addCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposContatoVO updateCampo(AdmCampoContatoVO admCampoContatoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CampoDinamicoFacadeImpl:updateCampo(" + user + ")");

            xmlIN = admCampoContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCAMALTERA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCamposContatoVODocument.Factory.parse(xmlOUT).getAdmCamposContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampoDinamicoFacadeImpl:updateCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampoDinamicoFacadeImpl:updateCampo", ex));

        } catch (Exception ex) {
            log.error("Exception - CampoDinamicoFacadeImpl:updateCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
