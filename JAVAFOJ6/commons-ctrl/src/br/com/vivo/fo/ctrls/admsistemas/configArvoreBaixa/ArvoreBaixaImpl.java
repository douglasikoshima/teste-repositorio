package br.com.vivo.fo.ctrls.admsistemas.configArvoreBaixa;

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
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaConsultaVODocument.AdmArvoreBaixaConsultaVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmAtualizacaoArvoreBaixaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmAtualizacaoArvoreBaixaVODocument.AdmAtualizacaoArvoreBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ArvoreBaixa", mappedName = "ArvoreBaixa")
@Local(ArvoreBaixa.class)
@Session(ejbName = "ArvoreBaixa", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArvoreBaixaImpl implements ArvoreBaixa {

    @EJB
    private TuxedoServiceCall tuxedo;

    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;
    private static Logger     log              = new Logger("admsistemas");

    /**
     * @common:operation
     */
    public AdmArvoreBaixaContainerVO carregaArvoreBaixa(AdmIdBaixaVO admidBaixa, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:carregaArvoreBaixa(").append(user).append(")").toString());

            xmlIN = admidBaixa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaListarCont", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = null;
            AdmArvoreBaixaContainerVODocument doc = AdmArvoreBaixaContainerVODocument.Factory.parse(xmlOUT);
            admArvoreBaixaContainerVO = doc.getAdmArvoreBaixaContainerVO();

            return admArvoreBaixaContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:carregaArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:carregaArvoreBaixa", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:carregaArvoreBaixa(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:carregaArvoreBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void removeItemBaixa(AdmFolhaBaixaVO item, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:removeItemBaixa(").append(user).append(")").toString());

            xmlIN = item.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaRemove", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            if (msgDocRet.getMsg().getMsgHdr().getStatusCode().indexOf("W") >= 0) {
                throw new TuxedoWarningException("[" + msgDocRet.getMsg().getMsgHdr().getStatusCode() + "] " + msgDocRet.getMsg().getMsgHdr().getStatusText());
            }

            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:removeItemBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:removeItemBaixa", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:removeItemBaixa(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:removeItemBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void insereItemBaixa(AdmArvoreBaixaContainerVO item, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:insereItemBaixa(").append(user).append(")").toString());

            xmlIN = item.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaIncluir", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:insereItemBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:insereItemBaixa", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:insereItemBaixa(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:insereItemBaixa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmArvoreBaixaContainerVO carregaDadosTelaInserir(AdmArvoreBaixaConsultaVO dadosConsultas, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:carregaDadosTelaInserir(").append(user).append(")").toString());

            xmlIN = dadosConsultas.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaListarCont", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = null;
            AdmArvoreBaixaContainerVODocument doc = AdmArvoreBaixaContainerVODocument.Factory.parse(xmlOUT);
            admArvoreBaixaContainerVO = doc.getAdmArvoreBaixaContainerVO();

            return admArvoreBaixaContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:carregaDadosTelaInserir(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:carregaDadosTelaInserir", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:carregaDadosTelaInserir(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:carregaDadosTelaInserir(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmArvoreBaixaContainerVO carregaDadosTelaEditar(AdmArvoreBaixaConsultaVO dadosConsultas, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:carregaDadosTelaEditar(").append(user).append(")").toString());

            xmlIN = dadosConsultas.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaListarCont", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = null;
            AdmArvoreBaixaContainerVODocument doc = AdmArvoreBaixaContainerVODocument.Factory.parse(xmlOUT);
            admArvoreBaixaContainerVO = doc.getAdmArvoreBaixaContainerVO();

            return admArvoreBaixaContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:carregaDadosTelaEditar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:carregaDadosTelaEditar", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:carregaDadosTelaEditar(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:carregaDadosTelaEditar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaItemBaixaEditado(AdmArvoreBaixaContainerVO item, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:salvaItemBaixaEditado(").append(user).append(")").toString());

            xmlIN = item.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BxaEditar", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:salvaItemBaixaEditado", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:carregaDadosTelaEditar(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmAtualizacaoArvoreBaixaVO habilitaArvoreBaixa(AdmFolhaBaixaVO item, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:salvaItemBaixaEditado(").append(user).append(")").toString());

            xmlIN = item.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BXAATIVAR", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            AdmAtualizacaoArvoreBaixaVO admAtualizacaoArvoreBaixaVO = null;
            AdmAtualizacaoArvoreBaixaVODocument doc = AdmAtualizacaoArvoreBaixaVODocument.Factory.parse(xmlOUT);
            admAtualizacaoArvoreBaixaVO = doc.getAdmAtualizacaoArvoreBaixaVO();

            return admAtualizacaoArvoreBaixaVO;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:salvaItemBaixaEditado", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void habilitaArvoreBaixaUpdate(AdmAtualizacaoArvoreBaixaVO arrayIdBaixa, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:salvaItemBaixaEditado(").append(user).append(")").toString());

            xmlIN = arrayIdBaixa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BXAATIVARUP", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:salvaItemBaixaEditado", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:salvaItemBaixaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmArvoreBaixaContainerVO getNome(AdmIdBaixaVO admIdBaixaVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("ArvoreBaixaImpl:getNome(").append(user).append(")").toString());

            xmlIN = admIdBaixaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "BXALISTANOME", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVODocument.Factory.parse(xmlOUT).getAdmArvoreBaixaContainerVO();

            return admArvoreBaixaContainerVO;

        } catch (XmlException ex) {
            log.error("XmlException - ArvoreBaixaImpl:getNome(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreBaixaImpl:getNome", ex));

        } catch (Exception ex) {
            log.error("Exception - ArvoreBaixaImpl:getNome(" + user + ") - [" + ex.getMessage() + "]");
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
