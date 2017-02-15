package br.com.vivo.fo.ctrls.fidelizacao.configurar;

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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ListaMensagemResultadoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaMensagemResultadoVODocument.ListaMensagemResultadoVO;
import br.com.vivo.fo.fidelizacao.vo.MensagemResultadoVODocument.MensagemResultadoVO;
import br.com.vivo.fo.fidelizacao.vo.MensajePesquisaInVODocument.MensajePesquisaInVO;
import br.com.vivo.fo.fidelizacao.vo.MensajePesquisaVODocument.MensajePesquisaVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MensagemResultadoFacade", mappedName = "MensagemResultadoFacade")
@Local(MensagemResultadoFacade.class)
@Session(ejbName = "MensagemResultadoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MensagemResultadoFacadeImpl implements MensagemResultadoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getRegional(String user) throws TuxedoException, FacadeException {
        FidelizacaoListaGeralVO regionais = null;

        xmlOut = ConstantesCRM.SVAZIO;
        xmlIn = ConstantesCRM.SVAZIO;

        try {
            xmlIn = "<getreg>GETREGIONAL</getreg>";

            xmlIn = XmlManager.MakeXmlIn(user, "GETREGIONAL", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);

            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            regionais = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

            return regionais;

        } catch (XmlException ex) {
            log.error("XmlException - MensgemResultadoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensgemResultadoFacadeImpl:getRegional", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MensgemResultadoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - MensgemResultadoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            // Lança exceção
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public RetornoVO addMensagemResultado(String user, MensajePesquisaInVO msgPesqVO) throws FacadeException, XmlException, TuxedoException {
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde bonus[0] - Regional bonus[1] -
         * descricao do bonus bonus[2] - validade do bonus
         **/
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde bonus[0] - Regional bonus[1] -
         * descricao do bonus bonus[2] - validade do bonus
         **/
        RetornoVO retorno = null;
        try {

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;
            // xmlIn =
            // "<idUfOperadora>"+mensagem[0]+"</idUfOperadora><idTipoEncerramento>"+
            // mensagem[1]+"</idTipoEncerramento><dsMsgEncerramento>" +
            // mensagem[2] + "</dsMsgEncerramento>";

            xmlIn = msgPesqVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "INSMSGENCERRAR", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            retorno = RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

            return retorno;

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - MensagemResultadoFacadeImpl:addMensagemResultado(" + user + ", " + "[ " + msgPesqVO.getIdUfOperadora() + " , " + msgPesqVO.getIdTipoEncerramento() + " ," + msgPesqVO.getDsMsgEncerramento() + "] ) - ["
                    + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {

            log.error("XmlException - MensagemResultadoFacadeImpl:addMensagemResultado(" + user + ", " + "[ " + msgPesqVO.getIdUfOperadora() + " , " + msgPesqVO.getIdTipoEncerramento() + " ," + msgPesqVO.getDsMsgEncerramento() + "] ) - ["
                    + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensagemResultadoFacadeImpl:addMensagemResultado", xex));

        } catch (Exception ex) {

            log.error("Exception - MensagemResultadoFacadeImpl:addMensagemResultado(" + user + ", " + "[ " + msgPesqVO.getIdUfOperadora() + " , " + msgPesqVO.getIdTipoEncerramento() + " ," + msgPesqVO.getDsMsgEncerramento() + "] ) - ["
                    + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void delMensagemEncerramento(String user, String id) throws FacadeException, TuxedoException {

        try {
            // Monta o log da operação se possível

            log.debug("MensagemEncerramentoFacadeImpl:delMensagemEncerramento(" + user + ", " + id + ")");

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            xmlIn = "<idMsgEncerramento>" + id + "</idMsgEncerramento>";
            xmlIn = XmlManager.MakeXmlIn(user, "EXCMSGENCERRAR", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException tex) {

            log.error("TuxedoException - MensagemEncerramentoFacadeImpl:delMensagemEncerramento(" + user + ", " + id + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - MensagemEncerramentoFacadeImpl:delMensagemEncerramento(" + user + ", " + id + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensagemEncerramentoFacadeImpl:delMensagemEncerramento", xex));

        } catch (Exception ex) {

            log.error("Exception - MensagemEncerramentoFacadeImpl:delMensagemEncerramento(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public ListaMensagemResultadoVO getMensagemResultado(String user, MensajePesquisaVO msjPesVO) throws TuxedoException, FacadeException {

        ListaMensagemResultadoVO lista = null;

        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;

        try {
            // xmlIn = "<idUfOperadora>" + parametro[0]+
            // "</idUfOperadora><dsMsgEncerramento>"+ parametro[1] +
            // "</dsMsgEncerramento><idTpEncerramento>" + parametro[2] +
            // "</idTpEncerramento>";;
            xmlIn = msjPesVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "SELMSGENCERRAR", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            lista = ListaMensagemResultadoVODocument.Factory.parse(xmlOut).getListaMensagemResultadoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - MensagemResultadoFacadeImpl:getMensagemResultado(" + user + ", " + msjPesVO.getIdUfOperadora() + " ," + msjPesVO.getDsMsgEncerramento() + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {

            log.error("XmlException - MensagemResultadoFacadeImpl:getMensagemResultado(" + user + ", " + msjPesVO.getIdUfOperadora() + "," + msjPesVO.getDsMsgEncerramento() + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensagemResultadoFacadeImpl:getMensagemResultado", xex));

        } catch (Exception ex) {

            log.error("Exception - MensagemResultadoFacadeImpl:getMensagemResultado(" + user + ", " + msjPesVO.getIdUfOperadora() + "," + msjPesVO.getDsMsgEncerramento() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

        return lista;
    }

    /**
     * @common:operation
     */
    public void setMensagemResultado(String user, MensagemResultadoVO mensagemResultado) throws TuxedoException, XmlException, FacadeException {
        try {

            xmlIn = mensagemResultado.xmlText().replaceAll("vo:", "");
            xmlIn = XmlManager.MakeXmlIn(user, "UPDMSGENCERRAR", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            log.error("XmlException - MensagemResultadoFacadeImpl:setMensagemResultado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensagemResultadoFacadeImpl:setMensagemResultado", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MensagemResultadoFacadeImpl:setMensagemResultado(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - MensagemResultadoFacadeImpl:setMensagemResultado(" + user + ") - [" + ex.getMessage() + "]");
            // Lança exceção
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getIntencaoCancelamento(String user, String idPerg, String parametro, String idTipoLinha) throws TuxedoException, FacadeException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<idperg>" + idPerg + "</idperg><texto>" + parametro + "</texto><idTipoLinha>" + idTipoLinha + "</idTipoLinha>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - MensgemResultadoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensgemResultadoFacadeImpl:getIntencaoCancelamento", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MensgemResultadoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MensgemResultadoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getTpEncerramento(String user) throws TuxedoException, FacadeException {
        FidelizacaoListaGeralVO lista = null;
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<getreg>SELTPENCERRAR</getreg>";
            // xmlIn = "<idperg>" + idPerg + "</idperg><texto>"+
            // parametro+"</texto>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELTPENCERRAR", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            lista = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

            return lista;

        } catch (XmlException ex) {
            log.error("XmlException - MensgemResultadoFacadeImpl:getTpEncerramento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MensgemResultadoFacadeImpl:getTpEncerramento", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MensgemResultadoFacadeImpl:getTpEncerramento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - MensgemResultadoFacadeImpl:getTpEncerramento(" + user + ") - [" + ex.getMessage() + "]");
            // Lança exceção
            throw (new FacadeException(ex));
        }
    }
}
