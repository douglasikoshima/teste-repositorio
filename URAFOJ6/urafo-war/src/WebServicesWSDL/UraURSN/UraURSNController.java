package WebServicesWSDL.UraURSN;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.actions.AbstractAction;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import noNamespace.MsgDocument;
import org.w3c.dom.Document;
import ura.servicos.vo.SenhaVODocument;
import ura.servicos.vo.SenhaVODocument.SenhaVO;
import weblogic.wtc.jatmi.TPException;

public class UraURSNController extends AbstractAction {

    private static final long serialVersionUID = -787545174385931420L;

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URSN".equals(mapping.getParameter())) {
			return URSN(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURSNController");
    //private String user = "";

    /** @jpf:action */
    public ActionForward URSN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URSN</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><SenhaVO xmlns="servicos.ura/vo"><cdDDD>11</cdDDD><cdNumTelefone>96969152</cdNumTelefone><operacao>01</operacao><cdUsuario></cdUsuario><cdTransacao></cdTransacao><senhaAtual></senhaAtual><senhaNova></senhaNova><tipoPessoa></tipoPessoa></SenhaVO></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String saida   = "";

        MsgDocument msgInput = null;
        SenhaVO inputVO = null;
        BigInteger numTelefone = null;
        BigInteger operacao = null;
        BigInteger cdDDD = null;
        BigInteger tipoPessoa = null;
        String senhaAtual = null;
        String senhaNova = null;

        try{
            msgInput = MsgDocument.Factory.parse(input);
            SenhaVODocument  inputVODoc = SenhaVODocument.Factory.newInstance();
            inputVODoc = SenhaVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            inputVO = inputVODoc.getSenhaVO();
            numTelefone = inputVO.getCdNumTelefone();
            operacao = inputVO.getOperacao();
            cdDDD = inputVO.getCdDDD();
            tipoPessoa = inputVO.getTipoPessoa();
            senhaAtual = inputVO.getSenhaAtual();
            senhaNova = inputVO.getSenhaNova();

            if(numTelefone == null || numTelefone.intValue() == 0) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--Número de telefone inválido-->");
            }

            if(cdDDD == null || cdDDD.intValue() == 0) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--DDD inválido-->");
            }

            if(operacao == null || operacao.intValue() > 1 || operacao.intValue() < 0) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--Operação inválida-->");
            }

            if(tipoPessoa == null || tipoPessoa.intValue() > 1 || tipoPessoa.intValue() < 0) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--Tipo Pessoa inválido-->");
            }

            if(operacao.intValue() == 1 && (senhaAtual == null || senhaAtual.trim().length() == 0 )) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--Senha Atual não preenchida-->");
            }

            if(operacao.intValue() == 1 && (senhaNova == null || senhaNova.trim().length() == 0 )) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,"<!--Senha Nova não preenchida-->");
            }

            //this.user = getUser(input);

            // se a operação for solicitação de senha
            if(operacao.intValue() == 0) {
                saida = this.solicitarSenha(cdDDD.intValue(), numTelefone.intValue(), tipoPessoa.intValue());
            } else {
                saida = this.alteraSenha(cdDDD.intValue(), numTelefone.intValue(), tipoPessoa.intValue(), senhaAtual, senhaNova);
            }

        }catch(Exception ex){
            logURA.log("uraURSN::URSN[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
        }

        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /**
     * @description Solicita a senha e envia por SMS
     * @param int cdDDD numerode DDD int numTelefone número de telefone
     * @return retorna um XML de sucesso ou erro no padrão da URA
     */
    private String solicitarSenha(int cdDDD, int numTelefone, int tipoPessoa){

        String xmlOut = null;
        String msgInput = new String();
        StringBuffer input = new StringBuffer();
        XmlManager xmlManager = new XmlManager();

        input.append("<cdAreaRegistro>").append(cdDDD).append("</cdAreaRegistro>");
        input.append("<nrLinha>").append(numTelefone).append("</nrLinha>");
        input.append("<idCanal>9</idCanal>");
        input.append("<idTipoRelacionamento>");
        input.append((tipoPessoa == 0)?2:1);
        input.append("</idTipoRelacionamento>");

        //msgInput = XmlManager.MakeXmlIn(this.user,"SOLICITARSENHA",input.toString());
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlTux = XmlManager.MakeXmlIn("30", "SOLICITARSENHA", input.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURSN::solicitarSenha[TuxedoErrorException]", tee);
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURSN::solicitarSenha[TuxedoWarningException]", twe);
            String e = twe.getXmlHeader().getError();
            if("0001".equals(e)){
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01"); // linha não encontrada
            }
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(Exception ex){
            logURA.log("uraURSN::solicitarSenha[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            return XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");
        }
        return XmlManager.createRetorno(Constant.URA_STATCOM_OK, "00");
    }

    /**
     * @description Valida e Altera a senha do cliente
     * @param int cdDDD, int numTelefone, int tipoPessoa, String senhaAtual, String senhaNova
     * @return retorna um XML de sucesso ou erro
     */
    private String alteraSenha(int cdDDD, int numTelefone, int tipoPessoa, String senhaAtual, String senhaNova){

        String xmlOut = null;
        XmlManager xmlManager = new XmlManager();

        StringBuffer inputValidaSenha = new StringBuffer();
        StringBuffer inputAlteraSenha = new StringBuffer();

        String msgInputValidaSenha = null;
        String msgInputAlteraSenha = null;

        inputValidaSenha.append("<telefone>").append(cdDDD+""+numTelefone).append("</telefone>");
        inputValidaSenha.append("<cdSenha>").append(senhaAtual).append("</cdSenha>");
        inputValidaSenha.append("<idCanal>9</idCanal>");
        // msg de entrada para alteração de senha
        inputAlteraSenha.append("<telefone>").append(cdDDD+""+numTelefone).append("</telefone>");
        inputAlteraSenha.append("<idPessoa>0</idPessoa>");
        inputAlteraSenha.append("<titularidadeSenha>");
        inputAlteraSenha.append((tipoPessoa == 0)?"C":"U");
        inputAlteraSenha.append("</titularidadeSenha>");
        inputAlteraSenha.append("<cdSenha>").append(senhaNova).append("</cdSenha>");
        inputAlteraSenha.append("<dsLembreteSenha></dsLembreteSenha>");
        inputAlteraSenha.append("<idCanal>9</idCanal>");

        //msgInputValidaSenha =  XmlManager.MakeXmlIn(this.user,"ValidaSenha",inputValidaSenha.toString());
        // validar senha
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlTux = XmlManager.MakeXmlIn("30", "ValidaSenha", inputValidaSenha.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURSN::alteraSenha[TuxedoErrorException]", tee);
            String e = tee.getXmlHeader().getError();
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURSN::alteraSenha[TuxedoWarningException]", twe);
            String e = twe.getXmlHeader().getError();
            if("0000".equals(e) || "0002".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04"); // senha atual não confere - erro de subrotina
            } else  if("0001".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01"); // linha não encontrada
            } else  if("0003".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06"); // usuário bloqueado
            } else if("0006".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04"); // senha não cadastrada
            } else if("0007".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04"); // senha renicializada
            } else if("0004".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05","<qtdeTentativa>1</qtdeTentativa>"); // senha atual não confere
            } else if("0005".equals(e)) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05","<qtdeTentativa>2</qtdeTentativa>"); // senha atual não confere
            } else if("0009".equals(e))
             {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05","<qtdeTentativa>3</qtdeTentativa>"); // usuário bloqueado
            }

            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(Exception ex){
            logURA.log("uraURSN::alteraSenha[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            return XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlTux = XmlManager.MakeXmlIn("30", "AlteraSenha", inputAlteraSenha.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURSN::alteraSenha[TuxedoErrorException]", tee);
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURSN::alteraSenha[TuxedoWarningException]", twe);
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

        }catch(Exception ex){
            logURA.log("uraURSN::alteraSenha[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            return XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");
        }
        return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"00");
    }

    /**
     * @description retorna o usuário do XML de entrada
     * @return usuário
     * @param String input
     */
    private String getUser(String input) throws Exception{
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String user = document.getElementsByTagName("user").item(0).getFirstChild().getNodeValue();
            return user;
        }catch(Exception ex){
            logURA.log("uraURSN::getUser[Exception]", ex);
            return null;
        }
    }

}
