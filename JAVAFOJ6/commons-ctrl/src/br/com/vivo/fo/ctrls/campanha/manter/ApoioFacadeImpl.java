package br.com.vivo.fo.ctrls.campanha.manter;

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
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ApoioFacade", mappedName = "ApoioFacade")
@Local(ApoioFacade.class)
@Session(ejbName = "ApoioFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApoioFacadeImpl implements ApoioFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log              = new Logger("campanha");

    /*************************************** APOIO CANALUFOPERADORA ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioCanalUFOper(String user, int idSubCampanha, int inNegative) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<getreg></getreg><idSubCampanha>");
            xmlINBuffer.append(idSubCampanha);
            xmlINBuffer.append("</idSubCampanha><inNegative>");
            xmlINBuffer.append(inNegative);
            xmlINBuffer.append("</inNegative>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETCANALUFOPE", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioCanalUFOper(" + user + ", " + idSubCampanha + ", " + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioCanalUFOper", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioCanalUFOper(" + user + ", " + idSubCampanha + ", " + inNegative + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioCanalUFOper(" + user + ", " + idSubCampanha + ", " + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO CANAL ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioCanal(String user) throws TuxedoException, FacadeException {
        try {
            String xmlIN = "<getreg></getreg>";
            xmlIN = XmlManager.MakeXmlIn(user, "GETCANAL", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioCanal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioCanal", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioCanal(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioCanal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO CANAL CAMPANHA ID ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioCanalCampanha(String user, String idSubCampanha, int inNegative) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<getreg></getreg><idSubCampanha>");
            xmlINBuffer.append(idSubCampanha);
            xmlINBuffer.append("</idSubCampanha><inNegative>");
            xmlINBuffer.append(inNegative);
            xmlINBuffer.append("</inNegative>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETCAMPANHACA", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioCanal(" + user + "," + idSubCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioCanal", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioCanal(" + user + "," + idSubCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioCanal(" + user + "," + idSubCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO GRUPO ACESSO ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioGrupo(String user, int idSubCampanha, int inNegatividade) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubCampanhaFixa>");
            xmlINBuffer.append(idSubCampanha);
            xmlINBuffer.append("</idSubCampanhaFixa><inNegative>");
            xmlINBuffer.append(inNegatividade);
            xmlINBuffer.append("</inNegative>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETGRPMOTIVO", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioGrupo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO TIPO CAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioTipoCampanha(String user) throws TuxedoException, FacadeException {

        try {

            String xmlIN = "<getreg></getreg>";
            xmlIN = XmlManager.MakeXmlIn(user, "GETTIPOCAMPAN", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioTipoCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioTipoCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioTipoCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioTipoCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO CAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO getApoioCampanha(String user) throws TuxedoException, FacadeException {
        try {
            String xmlIN = "<getreg></getreg>";
            xmlIN = XmlManager.MakeXmlIn(user, "GETCAMPANHA", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** APOIO SUBCAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioSubCampanha(String user, String idCampanha) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idCampanha>");
            xmlINBuffer.append(idCampanha);
            xmlINBuffer.append("</idCampanha><operacao>0</operacao>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETCAMPANHSUB", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioSubCampanha(" + user + ", " + idCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioSubCampanha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioSubCampanha(" + user + ", " + idCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioSubCampanha(" + user + ", " + idCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public GrupoCampanhaApoioVO getApoioVersao(String user, String idSubCampanhaFixa) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idSubCampanhaFixa>");
            xmlINBuffer.append(idSubCampanhaFixa);
            xmlINBuffer.append("</idSubCampanhaFixa><operacao>1</operacao>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETCAMPANHSUB", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCampanhaApoioVODocument.Factory.parse(xmlOUT).getGrupoCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ApoioFacadeImpl:getApoioVersao(" + user + ", " + idSubCampanhaFixa + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ApoioFacadeImpl:getApoioVersao", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ApoioFacadeImpl:getApoioVersao(" + user + ", " + idSubCampanhaFixa + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ApoioFacadeImpl:getApoioVersao(" + user + ", " + idSubCampanhaFixa + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
