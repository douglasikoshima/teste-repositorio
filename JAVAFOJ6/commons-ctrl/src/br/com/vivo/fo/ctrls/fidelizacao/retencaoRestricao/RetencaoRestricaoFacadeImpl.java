package br.com.vivo.fo.ctrls.fidelizacao.retencaoRestricao;

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
import br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RetencaoRestricaoFacade", mappedName = "RetencaoRestricaoFacade")
@Local(RetencaoRestricaoFacade.class)
@Session(ejbName = "RetencaoRestricaoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RetencaoRestricaoFacadeImpl implements RetencaoRestricaoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");

    /**
     * @common:operation
     */
    public ConsultaAdimplenciaVO validarAdimplencia(String user, ConsultaAdimplenciaVO consultaAdimplenciaVO) throws FacadeException, XmlException, TuxedoException {
        ConsultaAdimplenciaVO consultaAdimplencia = ConsultaAdimplenciaVO.Factory.newInstance();
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = consultaAdimplenciaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "COMATLYS", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ConsultaAdimplenciaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getConsultaAdimplenciaVO();

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:validarAdimplencia(" + user + ", " + "[" + ex.getMessage() + "]");
            consultaAdimplencia.setInTimeOutAPI("1");
            consultaAdimplenciaVO.setStatusAvaliacao("1");
            consultaAdimplenciaVO.setDtInterrupcao("21/12/2006 15:48:32");
            return consultaAdimplenciaVO;
        }
    }

    /* *
     * @common:operation
     */
    public void executarBaixa(String user, RetencaoSAPVO retensaoSAPVO) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        try {
            xmlIN = retensaoSAPVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "COMSAP", xmlIN));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:executarBaixa(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:executarBaixa(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:executarBaixa", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:executarBaixa(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetencaoAnaliseCreditoVO getDadosAnaliseCredito(String user, RetencaoAnaliseCreditoVO retencaoAnaliseCredito) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {

            xmlIN = retencaoAnaliseCredito.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETANALISE", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetencaoAnaliseCreditoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetencaoAnaliseCreditoVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:getDadosAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:getDadosAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:getDadosAnaliseCredito", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:getDadosAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void finalizarAnaliseCredito(String user, RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        try {

            xmlIN = retencaoAnaliseCreditoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "UPDANALISE", xmlIN));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetencaoAnaliseCreditoVO getDadosAnaliseEndereco(String user, RetencaoAnaliseCreditoVO retencaoAnaliseCredito) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {

            xmlIN = retencaoAnaliseCredito.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETENDERECO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetencaoAnaliseCreditoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetencaoAnaliseCreditoVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:getDadosAnaliseEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:getDadosAnaliseEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:getDadosAnaliseEndereco", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:getDadosAnaliseEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void finalizarAnaliseEndereco(String user, RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        try {

            xmlIN = retencaoAnaliseCreditoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "UPDENDERECO", xmlIN));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:finalizarAnaliseCredito(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String getMotivosAlteracaoEndereco(String user) throws FacadeException, XmlException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "SELMOTALTEND", xmlIN);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RetencaoRestricaoFacadeImpl:getMotivosAlteracaoEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - RetencaoRestricaoFacadeImpl:getMotivosAlteracaoEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RetencaoRestricaoFacadeImpl:getMotivosAlteracaoEndereco", ex));

        } catch (Exception ex) {
            log.error("Exception - RetencaoRestricaoFacadeImpl:getMotivosAlteracaoEndereco(" + user + ", " + "[" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
