package br.com.vivo.fo.ctrls.admsistemas.valorDominio;

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
import br.com.vivo.fo.admsistemas.vo.AdmCampoContatoVODocument.AdmCampoContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument.AdmCampoDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument.AdmCamposContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioValorIncluiVODocument.AdmDominioValorIncluiVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ValorDominioFacade", mappedName = "ValorDominioFacade")
@Local(ValorDominioFacade.class)
@Session(ejbName = "ValorDominioFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ValorDominioFacadeImpl implements ValorDominioFacade {
    
    @EJB
    private TuxedoServiceCall tuxedo;

    static final long         serialVersionUID = 1L;
    private static Logger     log              = new Logger("admsistemas");

    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public void removeValorDominio(AdmDominioVO admDominioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:removeValorDominio(" + user + ")");

            xmlIN = admDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMVLREMOVE", xmlIN);

            // new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            tuxedo.callService("TuxConnector", xmlIN);

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:removeValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:removeValorDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:removeValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmDominiosVO listaValorDominios(AdmDominioVO admDominioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:listaValorDominios(" + user + ")");

            xmlIN = admDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMVLLISTA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmDominiosVODocument.Factory.parse(xmlOUT).getAdmDominiosVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:listaValorDominios(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:listaValorDominios", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:listaValorDominios(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmDominiosVO addValorDominio(AdmDominioValorIncluiVO admDominioValorIncluiVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:addValorDominio(" + user + ")");

            xmlIN = admDominioValorIncluiVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMVLINSERI", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmDominiosVODocument.Factory.parse(xmlOUT).getAdmDominiosVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:addValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:addValorDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:addValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmDominiosVO updateValorDominio(AdmDominioVO admDominioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:updateValorDominio(" + user + ")");

            xmlIN = admDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADVLALTERA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmDominiosVODocument.Factory.parse(xmlOUT).getAdmDominiosVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:updateValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:updateValorDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:updateValorDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmDominioComboVO carregaComboDominio(String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:carregaComboDominio(" + user + ")");

            xmlIN = "<oi>oi</oi>";
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCMBPARDOM", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmDominioComboVODocument.Factory.parse(xmlOUT).getAdmDominioComboVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:carregaComboDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:carregaComboDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:carregaComboDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmDominioComboVO carregaComboValorDominio(String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:carregaComboDominio(" + user + ")");

            xmlIN = "<oi>oi</oi>";
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCMBPARVL", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmDominioComboVODocument.Factory.parse(xmlOUT).getAdmDominioComboVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:carregaComboDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:carregaComboDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:carregaComboDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCampoDominioVO carregaParametroCampoDominio(AdmCampoContatoVO admCampoContatoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:carregaParametroCampoDominio(" + user + ")");

            xmlIN = admCampoContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMPARCAR", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCampoDominioVODocument.Factory.parse(xmlOUT).getAdmCampoDominioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:carregaParametroCampoDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:carregaParametroCampoDominio", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:carregaParametroCampoDominio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCamposContatoVO associaDominioCampo(AdmCampoDominioVO admCampoDominioVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ValorDominioFacadeImpl:associaDominioCampo(" + user + ")");

            xmlIN = admCampoDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMPRAINC", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmCamposContatoVODocument.Factory.parse(xmlOUT).getAdmCamposContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ValorDominioFacadeImpl:associaDominioCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ValorDominioFacadeImpl:associaDominioCampo", ex));

        } catch (Exception ex) {
            log.error("Exception - ValorDominioFacadeImpl:associaDominioCampo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}