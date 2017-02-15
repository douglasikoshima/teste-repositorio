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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterAlteraVODocument.FidelizacaoManterAlteraVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterDelVODocument.FidelizacaoManterDelVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterInVODocument.FidelizacaoManterInVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterVODocument.FidelizacaoManterVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterDefinicaoFacade", mappedName = "ManterDefinicaoFacade")
@Local(ManterDefinicaoFacade.class)
@Session(ejbName = "ManterDefinicaoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterDefinicaoFacadeImpl implements ManterDefinicaoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;

    /*
     * PARA AS OPEREAÇÕES DE SELECT E INSERT, É NECESSARIO INFORMAR O ID DA
     * PERGUNTA A QUAL ESTA DEFINIÇÃO ESTARA LIGADA, COMO PADRÃO FICOU DEFINIDO
     * QUE SERÃO USADOS OS SEGUINTES CODIGOS:
     * 
     * 1 => QUANDO FOR DEFINIÇÃO DE INTENÇÃO DE CANSELAMENTO 2 => QUANDO FOR
     * DEFINIÇÃO DE DESTINO
     */

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getDescricao(String user, FidelizacaoManterVO fideVO) throws TuxedoException, FacadeException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            // xmlIn = "<idperg>" + idPerg + "</idperg><texto>"+
            // parametro+"</texto>";
            xmlIn = fideVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "SELDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterDefinicaoFacadeImpl:getDescricao(" + user + ", " + fideVO.getIdperg() + ") - [" + fideVO.getTexto() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterDefinicaoFacadeImpl:getDescricao(" + user + ", " + fideVO.getIdperg() + ") - [" + fideVO.getTexto() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterDefinicaoFacadeImpl:getDescricao", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterDefinicaoFacadeImpl:getDescricao(" + user + ", " + fideVO.getIdperg() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    /*
     * Alterado por PPaula 04/11/2004 Troca do tipo de retorno String para
     * RetornoVO
     */
    public RetornoVO delDescricao(String user, FidelizacaoManterDelVO fidelizacaoDelVO) throws TuxedoException, FacadeException {
        log.debug("ManterDefinicaoFacadeImpl:delDescricao(" + user + ", " + fidelizacaoDelVO.getId() + ")");
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            // Monta o log da operação se possível
            // xmlIn = "<id>"+ id+"</id><operacao>" + operacao + "</operacao>";
            xmlIn = fidelizacaoDelVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "EXCDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (TuxedoServiceCallerException ex) {
            // Monta mensagem de erro
            log.error("TuxedoException - ManterDefinicaoFacadeImpl:delDestinos(" + user + ", " + fidelizacaoDelVO.getId() + ") - [" + ex.getMessage() + "]");
            /*
             * if (ex.getLocalizedMessage().indexOf("Existe Matriz Associada!")
             * >0){ return(ex.getLocalizedMessage()); }else{ //Lança exceção
             * throw(ex); }
             */
            throw new TuxedoException(ex);
        } catch (XmlException xex) {

            log.error("XmlException - ManterDefinicaoFacadeImpl:delDestinos(" + user + ", " + fidelizacaoDelVO.getId() + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterDefinicaoFacadeImpl:delDestinos", xex));

        } catch (Exception ex) {

            log.error("Exception - ManterDefinicaoFacadeImpl:delDestinos(" + user + ", " + fidelizacaoDelVO.getId() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public RetornoVO setDescricao(String user, FidelizacaoManterAlteraVO fideAltera) throws TuxedoException, XmlException, FacadeException {
        try {
            // xmlIn ="<id>"+ bonus[0] +"</id><descricao>"+ bonus[1]
            // +"</descricao>";
            xmlIn = fideAltera.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "UPDDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterDefinicaoFacadeImpl:setDescricao(" + user + ", " + "[ " + fideAltera.getId() + "," + fideAltera.getDescricao() + "] ) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterDefinicaoFacadeImpl:setDescricao(" + user + ", " + "[ " + fideAltera.getId() + "," + fideAltera.getDescricao() + "] )- [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterDefinicaoFacadeImpl:setDescricao", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterDefinicaoFacadeImpl:setDescricao(" + user + ", " + "[ " + fideAltera.getId() + "," + fideAltera.getDescricao() + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO addDescricao(String user, FidelizacaoManterInVO fidManteVO) throws FacadeException, XmlException, TuxedoException {
        try {
            // xmlIn ="<idperg>" + idPerg + "</idperg><descricao>"+ descricao
            // +"</descricao>";
            xmlIn = fidManteVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "INSDSTINT", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterDefinicaoFacadeImpl:addDescricao(" + user + ", " + "[ " + fidManteVO.getDescricao() + "] ) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterDefinicaoFacadeImpl:addDescricao(" + user + ", " + "[ " + fidManteVO.getDescricao() + "] )- [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterDefinicaoFacadeImpl:addDescricao", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterDefinicaoFacadeImpl:addDescricao(" + user + ", " + "[ " + fidManteVO.getDescricao() + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
