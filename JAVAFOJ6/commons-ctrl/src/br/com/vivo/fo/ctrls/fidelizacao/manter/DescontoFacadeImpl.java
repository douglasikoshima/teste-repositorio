package br.com.vivo.fo.ctrls.fidelizacao.manter;

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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "DescontoFacade", mappedName = "DescontoFacade")
@Local(DescontoFacade.class)
@Session(ejbName = "DescontoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DescontoFacadeImpl implements DescontoFacade {

    private static Logger     log = new Logger("fidelizacao");
    private String            xmlIN;
    private String            xmlOUT;

    @EJB
    private TuxedoServiceCall tuxedo;

    /**
     * @common:operation
     */
    public FidelizacaoCadastroDescontoVO getListaMatriz(String user) throws TuxedoException, FacadeException {

        FidelizacaoCadastroDescontoVO fidelizacaoCadastroDescontoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("DescontoFacadeImpl:getListaMatriz(" + user + ")");
            xmlIN = XmlManager.MakeXmlIn(user, "LSTMTZDESC", "");

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoCadastroDescontoVO = FidelizacaoCadastroDescontoVODocument.Factory.parse(xmlOUT).getFidelizacaoCadastroDescontoVO();

            return fidelizacaoCadastroDescontoVO;

        } catch (XmlException ex) {
            log.error("XmlException - DescontoFacadeImpl:getListaMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DescontoFacadeImpl:getListaMatriz", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - DescontoFacadeImpl:getListaMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - DescontoFacadeImpl:getListaMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoCadastroDescontoVO buscarListaPorMatriz(String user, FidelizacaoCadastroDescontoVO desconto) throws TuxedoException, FacadeException {

        FidelizacaoCadastroDescontoVO fidelizacaoCadastroDescontoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("DescontoFacadeImpl:buscarListaPorMatriz(" + user + ")");
            xmlIN = desconto.xmlText().replaceAll("vo:", "");
            xmlIN = XmlManager.MakeXmlIn(user, "LSTAPDESC", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoCadastroDescontoVO = FidelizacaoCadastroDescontoVODocument.Factory.parse(xmlOUT).getFidelizacaoCadastroDescontoVO();

            return fidelizacaoCadastroDescontoVO;

        } catch (XmlException ex) {
            log.error("XmlException - DescontoFacadeImpl:buscarListaPorMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DescontoFacadeImpl:buscarListaPorMatriz", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - DescontoFacadeImpl:buscarListaPorMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - DescontoFacadeImpl:buscarListaPorMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoCadastroDescontoVO gravarMatriz(String user, FidelizacaoCadastroDescontoVO desconto) throws TuxedoException, FacadeException {
        FidelizacaoCadastroDescontoVO fidelizacaoCadastroDescontoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("DescontoFacadeImpl:gravarMatriz(" + user + ")");
            xmlIN = desconto.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "INSMTZDESC", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoCadastroDescontoVO = FidelizacaoCadastroDescontoVODocument.Factory.parse(xmlOUT).getFidelizacaoCadastroDescontoVO();

            return fidelizacaoCadastroDescontoVO;

        } catch (XmlException ex) {
            log.error("XmlException - DescontoFacadeImpl:gravarMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DescontoFacadeImpl:gravarMatriz", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - DescontoFacadeImpl:gravarMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - DescontoFacadeImpl:gravarMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
