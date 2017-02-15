package br.com.vivo.fo.ctrls.fidelizacao.manter;

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
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterChipFacade", mappedName = "ManterChipFacade")
@Local(ManterChipFacade.class)
@Session(ejbName = "ManterChipFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterChipFacadeImpl implements ManterChipFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("fidelizacao");
    private String            xmlIN;
    private String            xmlOUT;

    /**
     * @common:operation
     */
    public FidelizacaoManutChipVO getListaDDD(String user) throws TuxedoException, FacadeException {

        FidelizacaoManutChipVO fidelizacaoManutChipVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("ManterChipFacadeImpl:getListaDDD(" + user + ")");
            xmlIN = XmlManager.MakeXmlIn(user, "SELDDD", ConstantesCRM.SVAZIO);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoManutChipVO = FidelizacaoManutChipVODocument.Factory.parse(xmlOUT).getFidelizacaoManutChipVO();

            return fidelizacaoManutChipVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterChipFacadeImpl:getListaDDD(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterChipFacadeImpl:getListaDDD", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterChipFacadeImpl:getListaDDD(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - ManterChipFacadeImpl:getListaDDD(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoManutChipVO getChipsCadastrados(String user) throws TuxedoException, FacadeException {

        FidelizacaoManutChipVO fidelizacaoManutChipVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("ManterChipFacadeImpl:getChipsCadastrados(" + user + ")");
            xmlIN = XmlManager.MakeXmlIn(user, "SELCHIP", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoManutChipVO = FidelizacaoManutChipVODocument.Factory.parse(xmlOUT).getFidelizacaoManutChipVO();

            return fidelizacaoManutChipVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterChipFacadeImpl:getChipsCadastrados", ex));
        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);
        } catch (Exception ex) {
            log.error("Exception - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoManutChipVO getChipsCadastradosPorDDD(String user, String DDD) throws TuxedoException, FacadeException {

        FidelizacaoManutChipVO fidelizacaoManutChipVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("ManterChipFacadeImpl:getChipsCadastrados(" + user + ")");
            xmlIN = "<dddPesquisa>" + DDD + "</dddPesquisa>";
            xmlIN = XmlManager.MakeXmlIn(user, "SELCHIP", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoManutChipVO = FidelizacaoManutChipVODocument.Factory.parse(xmlOUT).getFidelizacaoManutChipVO();

            return fidelizacaoManutChipVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterChipFacadeImpl:getChipsCadastrados", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - ManterChipFacadeImpl:getChipsCadastrados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO incluirAlterarChip(String user, FidelizacaoManutChipVO fidelizacaoManutChip) throws TuxedoWarningException, TuxedoException, FacadeException {

        log.debug("ManterChipFacadeImpl:incluirAlterarChip(" + user + ")");
        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;
        RetornoVO retornoVO = RetornoVO.Factory.newInstance();

        try {

            xmlIN = fidelizacaoManutChip.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "INSCHIP", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            retornoVO = RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterChipFacadeImpl:incluirAlterarChip(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterChipFacadeImpl:incluirAlterarChip", ex));
        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterChipFacadeImpl:incluirAlterarChip(" + user + ") - [" + te.getMessage() + "]");
            throw te;
        } catch (TuxedoServiceCallerException ex) {
            TuxedoException tex = new TuxedoException(ex);
            log.error("TuxedoException - ManterChipFacadeImpl:incluirAlterarChip(" + user + ") - [" + tex.getMessage() + "]");
        } catch (Exception ex) {
            log.error("Exception - ManterChipFacadeImpl:incluirAlterarChip(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

        return retornoVO;
    }

    /**
     * @common:operation
     */
    public RetornoVO excluirChip(String user, FidelizacaoManutChipVO fidelizacaoManutChip) throws TuxedoException, FacadeException {

        log.debug("ManterChipFacadeImpl:excluirChip(" + user + ")");
        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;
        RetornoVO retornoVO = RetornoVO.Factory.newInstance();

        try {

            xmlIN = "<idChipExcluir>" + fidelizacaoManutChip.getIdChip() + "</idChipExcluir>";
            xmlIN = XmlManager.MakeXmlIn(user, "INSCHIP", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

            retornoVO = RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterChipFacadeImpl:excluirChip(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterChipFacadeImpl:excluirChip", ex));
        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterChipFacadeImpl:excluirChip(" + user + ") - [" + te.getMessage() + "]");
            throw te;
        } catch (TuxedoServiceCallerException ex) {
            TuxedoException tex = new TuxedoException(ex);
            log.error("TuxedoException - ManterChipFacadeImpl:excluirChip(" + user + ") - [" + tex.getMessage() + "]");
        } catch (Exception ex) {
            log.error("Exception - ManterChipFacadeImpl:excluirChip(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return retornoVO;

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
