package br.com.vivo.fo.ctrls.admsistemas.perfilCRI;

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
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "PerfilCRI", mappedName = "PerfilCRI")
@Local(PerfilCRI.class)
@Session(ejbName = "PerfilCRI", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilCRIImpl implements PerfilCRI {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getPerfilVariaveis(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:getPerfilVariaveis(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTPERFILVAR", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:getPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:getPerfilVariaveis", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:getPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getPerfil(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:getPerfil(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTPERFIL", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:getPerfil(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:getPerfil", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:getPerfil(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getGrupo(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:getGrupo(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTPERFIL", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:getGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:getGrupo", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:getGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PerfilVariaveisVO setPerfilVariaveis(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:setPerfilVariaveis(").append(user).append(", ").append(parametro).append(")").toString());

            String xmlIN = XmlManager.MakeXmlIn(user, "INCLPERFIL", parametro);

            // String xmlOUT = (new ControlTuxedoCall()).execute(this,
            // admSistemasTux, "GETSERVICE", xmlIN);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return PerfilVariaveisVODocument.Factory.parse(xmlOut).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:setPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:setPerfilVariaveis", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:setPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO prazoCRI(String user, WFAcaoRetornoVO parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:prazoCRI(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ADMPRAZOCRI", parametro.xmlText().replaceAll("vo[0-9]*:", ""));
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:prazoCRI(" + user + ", WFAcaoRetornoVO parametro) - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:prazoCRI", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:prazoCRI(" + user + ", WFAcaoRetornoVO parametro) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO excPerfilVariaveis(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:excPerfilVariaveis(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "RMVPERFIL", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:excPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:excPerfilVariaveis", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:excPerfilVariaveis(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO setStatusPerfil(String user, String idPerfil, String statusPerfil) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilCRIImpl:setStatusPerfil(").append(user).append(", ").append(idPerfil).append(", ").append(statusPerfil).append(")").toString());

            String xmlIn = "<idPerfil>" + idPerfil + "</idPerfil><inOperacao>5</inOperacao><status>" + statusPerfil + "</status>";

            String inService = TuxedoServiceBridge.getXMLRequest(user, "INCLPERFIL", xmlIn);
            String xmlOut = "";// admSistemasTux.GETSERVICE(inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilCRIImpl:setStatusPerfil(" + user + ", " + idPerfil + ", " + statusPerfil + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilCRIImpl:setStatusPerfil", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilCRIImpl:setStatusPerfil(" + user + ", " + idPerfil + ", " + statusPerfil + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
