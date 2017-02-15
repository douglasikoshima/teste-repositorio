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
import br.com.vivo.fo.commons.utils.geral.ControlXMLExceptionLookup;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ScriptFacade", mappedName = "ScriptFacade")
@Local(ScriptFacade.class)
@Session(ejbName = "ScriptFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ScriptFacadeImpl implements ScriptFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log    = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;
    private String[]          retTux = new String[0];

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

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            regionais = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

            return regionais;

        } catch (XmlException ex) {
            log.error("XmlException - ScriptFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:getRegional", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ScriptFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addScript(String user, String[] script, String[] destino) throws FacadeException, XmlException, TuxedoException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde script[0] - idRegional script[1]
         * - idIntecao script[2] - dsIntencao script[3] - idDestino script[4] -
         * dsDestino
         **/
        try {

            xmlIn = "<idUfOperadora>" + script[0] + "</idUfOperadora><idIntencao>" + script[1] + "</idIntencao><idTipoPessoa>" + script[2] + "</idTipoPessoa><destino>";
            for (int i = 0; i < destino.length; i++) {
                xmlIn += "<id>" + destino[i] + "</id>";
            }
            xmlIn += "</destino>";

            xmlIn = XmlManager.MakeXmlIn(user, "INSSCRIPT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {

            log.error("XmlException - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:addScript", xex));

        } catch (Exception ex) {

            log.error("Exception - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String setScript(String user, String[] script, String[] destino) throws TuxedoException, FacadeException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde script[0] - idRegional script[1]
         * - idIntecao script[2] - dsIntencao script[3] - idDestino script[4] -
         * dsDestino
         **/
        try {
            xmlIn = "<operacao>0</operacao><idUfOperadora>" + script[0] + "</idUfOperadora><idIntencao>" + script[1] + "</idIntencao><idTipoPessoa>" + script[2] + "</idTipoPessoa><destino>";
            for (int i = 0; i < destino.length; i++) {
                xmlIn += "<id>" + destino[i] + "</id>";
            }
            xmlIn += "</destino>";

            xmlIn = XmlManager.MakeXmlIn(user, "UPDSCRIPT", xmlIn);

            try {
                xmlOut = ConstantesCRM.SVAZIO;// fidelizaTux.GETSERVICE(xmlIn);
                xmlOut = tuxedo.callService("TuxConnector", xmlIn);
                MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
                xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            } catch (Exception ex) {
                xmlOut = ControlXMLExceptionLookup.getXMLString(ex);
            }

            retTux = XmlManager.ParseXmlOut(xmlOut);

            if (xmlOut.indexOf("Existe Matriz Associada!") > 0) {
                return (xmlOut);
            } else {
                // Lança exceção
                RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);
            }

            return xmlOut;

        } catch (TuxedoException ex) {
            // Monta mensagem de erro
            log.error("TuxedoException - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + ex.getMessage() + "]");

            /**
             * if (ex.getLocalizedMessage().indexOf("Existe Matriz Associada!")
             * >0){ return(ex.getLocalizedMessage()); }else{ //Lança exceção
             * throw(ex); }
             **/
            throw new TuxedoException(ex);
        } catch (XmlException xex) {
            log.error("XmlException - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:addScript", xex));

        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:addScript(" + user + ", " + "[ " + script[0] + " , " + script[1] + " ," + script[2] + " , " + script[3] + ", " + script[4] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ArrayListaGeralVO getScript(String user, String[] dados) throws TuxedoException, FacadeException {
        ArrayListaGeralVO script;
        try {
            xmlIn = "<idUfOperadora>" + dados[0] + "</idUfOperadora><idIntencao>" + dados[1] + "</idIntencao><idTipoPessoa>" + dados[2] + "</idTipoPessoa>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELSCRIPT", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            script = ArrayListaGeralVODocument.Factory.parse(xmlOut).getArrayListaGeralVO();

            return script;

        } catch (XmlException ex) {
            log.error("XmlException - ScriptFacadeImpl:getScript(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:getScript", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ScriptFacadeImpl:getScript(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:getScript(" + user + ") - [" + ex.getMessage() + "]");
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
            // Para buscar todas as intenções, passo o idPerg = 1
            xmlIn = "<idperg>" + idPerg + "</idperg><texto>" + parametro + "</texto><idTipoLinha>" + idTipoLinha + "</idTipoLinha>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - ScriptFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:getIntencaoCancelamento", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ScriptFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getDestino(String user, String idPerg, String parametro, String idTipoLinha) throws TuxedoException, FacadeException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            // Para buscar todas as intenções, passo o idPerg = 1
            xmlIn = "<idperg>" + idPerg + "</idperg><texto>" + parametro + "</texto><idTipoLinha>" + idTipoLinha + "</idTipoLinha>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - ScriptFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:getDestino", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ScriptFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String editaScript(String user, String[] dados) throws FacadeException, TuxedoException {
        try {
            xmlOut = ConstantesCRM.SVAZIO;
            xmlIn = ConstantesCRM.SVAZIO;
            // Monta o log da operação se possível

            log.debug("ScriptFacadeImpl:editaScript(" + user + ", " + dados[0] + dados[1] + ")");

            // Monta XML IN de acordo com o serviço a ser executado

            xmlIn = "<idUfOperadora>" + dados[0] + "</idUfOperadora><idIntencao>" + dados[1] + "</idIntencao>";
            xmlIn = XmlManager.MakeXmlIn(user, "UPDSCRIPT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOut;

        } catch (XmlException ex) {
            log.error("XmlException - ScriptFacadeImpl:editaScript(" + user + ", " + dados[0] + dados[1] + dados[2] + dados[3] + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:editaScript", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ScriptFacadeImpl:editaScript(" + user + ", " + dados[0] + dados[1] + dados[2] + dados[3] + ") - [" + ex.getMessage() + "]");

            if (ex.getLocalizedMessage().indexOf("Existe Matriz Associada!") > 0) {
                return (ex.getLocalizedMessage());
            } else {
                // Lança exceção
                throw new TuxedoException(ex);
            }
        } catch (Exception ex) {
            log.error("Exception - ScriptFacadeImpl:editaScript(" + user + ", " + dados[0] + dados[1] + dados[2] + dados[3] + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setDestino(String user, String[] script, String operacao, String[] id) throws FacadeException, TuxedoException {
        try {

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            xmlIn = "<idUfOperadora>" + script[0] + "</idUfOperadora><idIntencao>" + script[1] + "</idIntencao><operacao>" + operacao + "</operacao><destino>";

            for (int i = 0; i < id.length; i++) {
                xmlIn += "<id>" + id[i] + "</id>";
            }
            xmlIn += "</destino>";

            xmlIn = XmlManager.MakeXmlIn(user, "UPDSCRIPT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux,"GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            // Monta mensagem de erro
            log.error("XmlException - ScriptFacadeImpl:setDestino(" + user + " destinos selecionados , destino excluidos) - [" + ex.getMessage() + "]");

            // Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ScriptFacadeImpl:setDestino", ex));

        } catch (TuxedoServiceCallerException ex) {
            // Monta mensagem de erro
            log.error("TuxedoException - ScriptFacadeImpl:setDestino(" + user + " destinos selecionados , destino excluidos) - [" + ex.getMessage() + "]");

            // Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - ScriptFacadeImpl:setDestino(" + user + " destinos selecionados , destino excluidos) - [" + ex.getMessage() + "]");

            // Lança exceção
            throw (new FacadeException(ex));
        }
    }
}
