package br.com.vivo.fo.ctrls.campanha.manter;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.SubCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoCampanhaVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoCampanhaVODocument.RetornoCampanhaVO;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "CampanhaFacade", mappedName = "CampanhaFacade")
@Local(CampanhaFacade.class)
@Session(ejbName = "CampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CampanhaFacadeImpl implements CampanhaFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log              = new Logger("campanha");

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getGrupoCampanhaApoio(String user, String operacao) throws TuxedoException, FacadeException {
        try {
            /*
             * 
             * se 1 então traga campanhas se não se 2 então traga motivos se nãe
             * se 3 então traga sub motivos se não traga tudo como antes
             */
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<getreg></getreg><operacao>");
            xmlINBuffer.append(operacao);
            xmlINBuffer.append("</operacao>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETAPOIOCAMPA", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampanhaFacadeImpl:getGrupoCampanhaApoio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:getGrupoCampanhaApoio", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - CampanhaFacadeImpl:getGrupoCampanhaApoio(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:getGrupoCampanhaApoio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO selApoioCampanha(String user, String descricao, String sigla, String id) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgCampanha><dsCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(descricao.trim()));
            xmlINBuffer.append("</dsCampanha><idCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(id));
            xmlINBuffer.append("</idCampanha>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSCAMPANHAPE",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSCAMPANHAPE", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:selApoioCampanha(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:selApoioCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:selApoioCampanha(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO selApoioCampanhaMotivo(String user, String descricao, String sigla, String id) throws FacadeException, XmlException, TuxedoException {
        /*****
         * idServico indica qual serviço será chamado ***** 0 - Campanha 2 -
         * Motivo 3 - SubMotivo
         *********************************************************/
        try {
            /******************************************* MOTIVO CAMPANHA **************************************************************************************************************************************************/
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgMotivo><nmMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmMotivo>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSMOTIVOPESQ",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSMOTIVOPESQ", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:selApoioCampanhaMotivo(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:selApoioCampanhaMotivo", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:selApoioCampanhaMotivo(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public RetornoVO selApoioCampanhaSubMotivo(String user, String descricao, String sigla, String id) throws FacadeException, XmlException, TuxedoException {
        /*****
         * idServico indica qual serviço será chamado ***** 0 - Campanha 2 -
         * Motivo 3 - SubMotivo
         *********************************************************/
        // RetornoVO retorno = null;
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgSubMotivo><nmSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmSubMotivo>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSSUBMOTIVOP",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSSUBMOTIVOP", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:selApoioCampanhaSubMotivo(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:selApoioCampanhaSubMotivo", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:selApoioCampanhaSubMotivo(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addApoioCampanha(String user, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {
        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgCampanha><dsCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(descricao.trim()));
            xmlINBuffer.append("</dsCampanha>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSCAMPANHA",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSCAMPANHA", xmlINBuffer.toString()));

            // } catch(XmlException xex) {
            // log.error("XmlException - CampanhaFacadeImpl:addApoioCampanha(" +
            // user + ", "+"[ " + descricao + "],[" + sigla + "] ) - [" +
            // xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addApoioCampanha",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addApoioCampanha(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void addApoioCampanhaTipoCampanha(String user, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {
        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgTipoCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgTipoCampanha><nmTipoCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(descricao.trim()));
            xmlINBuffer.append("</nmTipoCampanha>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSTIPOCAMPANHA",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSTIPOCAMPANHA", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:addApoioCampanhaTipoCampanha("
            // + user + ", "+"[ " + descricao + "],[" + sigla + "] ) - [" +
            // xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addApoioCampanhaTipoCampanha",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addApoioCampanhaTipoCampanha(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addApoioCampanhaMotivo(String user, String descricao, String sigla, String aderiu) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();

            xmlINBuffer.append("<inAderiu>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(aderiu.trim()));
            xmlINBuffer.append("</inAderiu><sgMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgMotivo><nmMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmMotivo>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSMOTIVO",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSMOTIVO", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:addApoioCampanhaTipoCampanha("
            // + user + ", "+"[ " + descricao + "],[" + sigla + "] ) - [" +
            // xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addApoioCampanhaTipoCampanha",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addApoioCampanhaTipoCampanha(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addApoioCampanhaSubMotivo(String user, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<sgSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgSubMotivo><nmSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmSubMotivo>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INSSUBMOTIVO",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "INSSUBMOTIVO", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:addApoioCampanhaSubMotivo("
            // + user + ", "+"[ " + descricao + "],[" + sigla + "] ) - [" +
            // xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addApoioCampanhaSubMotivo",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addApoioCampanhaSubMotivo(" + user + ", " + "[ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setApoioCampanha(String user, int iCodigo, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCampanha>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idCampanha><sgCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgCampanha><dsCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(descricao.trim()));
            xmlINBuffer.append("</dsCampanha>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"UPDCAMPANHA",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "UPDCAMPANHA", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:setApoioCampanha(" +
            // user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla +
            // "] ) - [" + xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setApoioCampanha",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setApoioCampanha(" + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setApoioCampanhaTipoCampanha(String user, int iCodigo, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idTipoCampanha>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idTipoCampanha><sgTipoCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgTipoCampanha><nmTipoCampanha>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmTipoCampanha>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"UPDTIPOCAMPANHA",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "UPDTIPOCAMPANHA", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:setApoioCampanhaTipoCampanha("
            // + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla +
            // "] ) - [" + xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setApoioCampanhaTipoCampanha",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setApoioCampanhaTipoCampanha(" + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setApoioCampanhaMotivo(String user, int iCodigo, String descricao, String sigla, String aderiu) throws FacadeException, XmlException, TuxedoException {
        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<inAderiu>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(aderiu.trim()));
            xmlINBuffer.append("</inAderiu><idMotivo>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idMotivo><sgMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgMotivo><nmMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmMotivo>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"UPDMOTIVO",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "UPDMOTIVO", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:setApoioCampanhaMotivo("
            // + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla +
            // "] ) - [" + xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setApoioCampanhaMotivo",
            // xex));

        } catch (Exception ex) {

            log.error("Exception - CampanhaFacadeImpl:setApoioCampanhaMotivo(" + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setApoioCampanhaSubMotivo(String user, int iCodigo, String descricao, String sigla) throws FacadeException, XmlException, TuxedoException {

        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubMotivo>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idSubMotivo><sgSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</sgSubMotivo><nmSubMotivo>");
            xmlINBuffer.append(StringEscapeUtils.escapeXml(sigla.trim()));
            xmlINBuffer.append("</nmSubMotivo>");

            // campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"UPDSUBMOTIVO",
            // xmlINBuffer.toString()));
            tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "UPDSUBMOTIVO", xmlINBuffer.toString()));

            // } catch(XmlException xex) {

            // log.error("XmlException - CampanhaFacadeImpl:setApoioCampanhaSubMotivo("
            // + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla +
            // "] ) - [" + xex.getMessage() + "]");
            // throw(new
            // FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setApoioCampanhaSubMotivo",
            // xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setApoioCampanha(" + user + ", [" + iCodigo + "], [ " + descricao + "],[" + sigla + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO delApoioCampanha(String user, int iCodigo) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCampanha>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idCampanha>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"DELCAMPANHA",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DELCAMPANHA", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:delApoioCampanha(" + user + ", [ " + iCodigo + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:delApoioCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:delApoioCampanha(" + user + ", [ " + iCodigo + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO delApoioCampanhaTipoCampanha(String user, int iCodigo) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idTipoCampanha>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idTipoCampanha>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"DELTIPOCAMPANHA",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DELTIPOCAMPANHA", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:delApoioCampanhaTipoCampanha(" + user + ", [ " + iCodigo + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:delApoioCampanhaTipoCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:delApoioCampanhaTipoCampanha(" + user + ", [ " + iCodigo + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO delApoioCampanhaMotivo(String user, int iCodigo) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idMotivo>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idMotivo>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"DELMOTIVO",
                               // xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DELMOTIVO", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:delApoioCampanhaMotivo(" + user + ", [ " + iCodigo + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:delApoioCampanhaMotivo", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:delApoioCampanhaMotivo(" + user + ", [ " + iCodigo + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO delApoioCampanhaSubMotivo(String user, int iCodigo) throws FacadeException, XmlException, TuxedoException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubMotivo>");
            xmlINBuffer.append(iCodigo);
            xmlINBuffer.append("</idSubMotivo>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
                               // "DELSUBMOTIVO", xmlINBuffer.toString()));
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DELSUBMOTIVO", xmlINBuffer.toString()));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:delApoioCampanhaSubMotivo(" + user + ", [ " + iCodigo + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:delApoioCampanhaSubMotivo", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:delApoioCampanhaSubMotivo(" + user + ", [ " + iCodigo + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public CampanhaParametrosVO getParametrosSubCampanha(String user, String idCanalCampanha) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCanalCampanha>");
            xmlINBuffer.append(idCanalCampanha);
            xmlINBuffer.append("</idCanalCampanha>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETPARAMCMP", xmlINBuffer.toString());

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE( xmlIN );
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = xmlOUT.replaceAll("tns:", ConstantesCRM.SVAZIO);
            xmlOUT = xmlOUT.replace("</CampanhaParametrosVO >", "</CampanhaParametrosVO>");

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return CampanhaParametrosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getCampanhaParametrosVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampanhaFacadeImpl:getParametrosSubCampanha(" + user + "," + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:getParametrosSubCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:getParametrosSubCampanha(" + user + "," + idCanalCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addParametrosCampanha(String user, CampanhaParametrosVO paramCampanha) throws FacadeException, XmlException, TuxedoException {
        try {
            log.debug("CampanhaFacadeImpl:addParametrosCampanha(" + user + ", " + paramCampanha + ")");

            String xmlIN = paramCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "INSPARAMCMP", xmlIN);
            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:addParametrosCampanha(" + user + ", " + "[ " + paramCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:addParametrosCampanha(" + user + ", " + "[ " + paramCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addParametrosCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addParametrosCampanha(" + user + ", " + "[ " + paramCampanha + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /****************************************************** SUB CAMPANHA **************************************************************************************************************************/
    /**
     * @common:operation
     */
    public SubCampanhaVO getSubCampanha(String user, int idSubCampanhaFixa, int idSubCampanhaHistorico) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubCampanhaFixa>");
            xmlINBuffer.append(idSubCampanhaFixa);
            xmlINBuffer.append("</idSubCampanhaFixa><idSubCampanhaHistorico>");
            xmlINBuffer.append(idSubCampanhaHistorico);
            xmlINBuffer.append("</idSubCampanhaHistorico>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETSUBCAMPANH", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return SubCampanhaVODocument.Factory.parse(xmlOUT).getSubCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - CampanhaFacadeImpl:getSubCampanha(" + user + "," + idSubCampanhaFixa + "," + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:getSubCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - CampanhaFacadeImpl:getSubCampanha(" + user + "," + idSubCampanhaFixa + "," + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:getSubCampanha(" + user + "," + idSubCampanhaFixa + "," + idSubCampanhaHistorico + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoCampanhaVO addSubCampanha(String user, SubCampanhaVO subCampanha) throws FacadeException, XmlException, TuxedoException {
        try {
            log.debug("CampanhaFacadeImpl:addSubCampanha(" + user + ", " + subCampanha + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSSUBCAMPANHA", subCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoCampanhaVODocument.Factory.parse(xmlOUT).getRetornoCampanhaVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:addSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:addSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:addSubCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:addSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO criarVersao(String user, String idSubCampanhaHistoricoOrigem) throws FacadeException, XmlException, TuxedoException {
        try {
            log.debug("CampanhaFacadeImpl:criarVersao(" + user + ", " + idSubCampanhaHistoricoOrigem + ")");

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<processo>1</processo><idSubCampanhaHistoricoOrigem>");
            xmlINBuffer.append(idSubCampanhaHistoricoOrigem);
            xmlINBuffer.append("</idSubCampanhaHistoricoOrigem>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSVRSCAMP", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:criarVersao(" + user + ", " + "[ " + idSubCampanhaHistoricoOrigem + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:criarVersao(" + user + ", " + "[ " + idSubCampanhaHistoricoOrigem + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:criarVersao", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:criarVersao(" + user + ", " + "[ " + idSubCampanhaHistoricoOrigem + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setSubCampanha(String user, SubCampanhaVO subCampanha) throws FacadeException, XmlException, TuxedoException {

        try {

            log.debug("CampanhaFacadeImpl:setSubCampanha(" + user + ", " + subCampanha + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "UPDSUBCAMPANHA", subCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:setSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:setSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setSubCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setSubCampanha(" + user + ", " + "[ " + subCampanha + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setSubCampanhaVersao(String user, SubCampanhaVO subCampanha) throws FacadeException, XmlException, TuxedoException {
        try {
            log.debug("CampanhaFacadeImpl:setSubCampanhaVersao(" + user + ", " + subCampanha + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "UPDSBCAMPANHAVR", subCampanha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:setSubCampanhaVersao(" + user + ", " + "[ " + subCampanha + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:setSubCampanhaVersao(" + user + ", " + "[ " + subCampanha + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setSubCampanhaVersao", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setSubCampanhaVersao(" + user + ", " + "[ " + subCampanha + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setParametrosSubCampanha(String user, CampanhaParametrosVO campanhaParam) throws FacadeException, XmlException, TuxedoException {

        try {

            log.debug("CampanhaFacadeImpl:setParametrosSubCampanha(" + user + ", " + campanhaParam + ")");

            String xmlIN = XmlManager.MakeXmlIn(user, "UPDPARAMCMP", campanhaParam.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CampanhaFacadeImpl:setParametrosSubCampanha(" + user + ", " + "[ " + campanhaParam + "]) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CampanhaFacadeImpl:setParametrosSubCampanha(" + user + ", " + "[ " + campanhaParam + "]) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CampanhaFacadeImpl:setParametrosSubCampanha", xex));

        } catch (Exception ex) {
            log.error("Exception - CampanhaFacadeImpl:setParametrosSubCampanha(" + user + ", " + "[ " + campanhaParam + "]) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
