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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RelDescGrupo", mappedName = "RelDescGrupo")
@Local(RelDescGrupo.class)
@Session(ejbName = "RelDescGrupo", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelDescGrupoImpl implements RelDescGrupo {

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIN;
    private String            xmlOUT;

    @EJB
    private TuxedoServiceCall tuxedo;

    /**
     * @common:operation
     */
    public FidelizacaoRelDescGrupoVO getListaGrupoRetencao(String user) throws TuxedoException, FacadeException {

        FidelizacaoRelDescGrupoVO fidelizacaoRelDescGrupoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("RelDescGrupoImpl:getListaGrupoRetencao(" + user + ")");
            xmlIN = XmlManager.MakeXmlIn(user, "LSTGRPRETD", ConstantesCRM.SVAZIO);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoRelDescGrupoVO = FidelizacaoRelDescGrupoVODocument.Factory.parse(xmlOUT).getFidelizacaoRelDescGrupoVO();

            return fidelizacaoRelDescGrupoVO;

        } catch (XmlException ex) {
            log.error("XmlException - RelDescGrupoImpl:getListaGrupoRetencao(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RelDescGrupoImpl:getListaGrupoRetencao", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RelDescGrupoImpl:getListaGrupoRetencao(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - RelDescGrupoImpl:getListaGrupoRetencao(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoRelDescGrupoVO getListasMatrizDesconto(String user, FidelizacaoRelDescGrupoVO grupo) throws TuxedoException, FacadeException {

        FidelizacaoRelDescGrupoVO fidelizacaoRelDescGrupoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("RelDescGrupoImpl:getListasMatrizDesconto(" + user + ")");
            xmlIN = grupo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "LSTMTZDGRP", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            fidelizacaoRelDescGrupoVO = FidelizacaoRelDescGrupoVODocument.Factory.parse(xmlOUT).getFidelizacaoRelDescGrupoVO();

            return fidelizacaoRelDescGrupoVO;

        } catch (XmlException ex) {
            log.error("XmlException - RelDescGrupoImpl:getListasMatrizDesconto(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RelDescGrupoImpl:getListasMatrizDesconto", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RelDescGrupoImpl:getListasMatrizDesconto(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - RelDescGrupoImpl:getListasMatrizDesconto(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void gravarListaDescAssociado(String user, FidelizacaoRelDescGrupoVO matrizDescAssociada) throws TuxedoException, FacadeException {

        FidelizacaoRelDescGrupoVO fidelizacaoRelDescGrupoVO = null;

        xmlOUT = ConstantesCRM.SVAZIO;
        xmlIN = ConstantesCRM.SVAZIO;

        try {

            log.debug("RelDescGrupoImpl:gravarListaDescAssociado(" + user + ")");
            xmlIN = matrizDescAssociada.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "INSMTZDASS", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            // fidelizacaoRelDescGrupoVO =
            // FidelizacaoRelDescGrupoVODocument.Factory.parse(xmlOUT).getFidelizacaoRelDescGrupoVO();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - RelDescGrupoImpl:gravarListaDescAssociado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RelDescGrupoImpl:gravarListaDescAssociado", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - RelDescGrupoImpl:gravarListaDescAssociado(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - RelDescGrupoImpl:gravarListaDescAssociado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
