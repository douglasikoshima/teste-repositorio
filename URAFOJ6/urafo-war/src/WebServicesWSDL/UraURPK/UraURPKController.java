package WebServicesWSDL.UraURPK;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import noNamespace.MsgDocument;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

import tuxProxyBE.vo.LINHADocument;
import tuxProxyBE.vo.LINHADocument.LINHA;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;
import br.com.vivo.fo.cliente.vo.DadosChipVODocument;

import com.indracompany.actions.AbstractAction;

public class UraURPKController extends AbstractAction {

    private static final long serialVersionUID = 4871977863247415869L;
    private String cdDDD = "";
    private String cdNumTelefone = "";

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURPKController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URPK".equals(mapping.getParameter())) {
			return URPK(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward URPK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><cdNumTelefone>96127098</cdNumTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String saida = "";
        XmlManager xmlManager = new XmlManager();
        StringBuffer xml = new StringBuffer();
        try{
            if(!validaXML(input)) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
            }

        }catch(Exception ex){
            logURA.log("uraURPK::URPK[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
        }

        if(saida.length()>0){
        	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "GETTIPOLINHA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);
            MsgDocument msgInput = MsgDocument.Factory.parse(xmlOut);
            LINHADocument linhaDoc = LINHADocument.Factory.newInstance();
            linhaDoc = LINHADocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            LINHA linhaVO = linhaDoc.getLINHA();

            StringBuffer strInput = new StringBuffer();
            strInput.append("<nrLinha>"+cdDDD+cdNumTelefone+"</nrLinha>");
            strInput.append("<idLinha>"+linhaVO.getIdLinhaTelefonica()+"</idLinha>");
            strInput.append("<nrIP>"+this.getIP()+"</nrIP>");
            //String xmlIn = XmlManager.MakeXmlIn("1","GETDADOSCHIP",strInput.toString());

            xmlTux = XmlManager.MakeXmlIn("30", "GETDADOSCHIP", strInput.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOutChip = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOutChip+"<<");

            xmlManager.parseXmlOut(xmlOutChip);
            MsgDocument msgOut = MsgDocument.Factory.parse(xmlOutChip);
            DadosChipVODocument dadosChipDoc = DadosChipVODocument.Factory.parse(msgOut.getMsg().getMsgBody().toString());

            if(dadosChipDoc.getDadosChipVO().getErrorCode() != null) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,"<!--erro:"+dadosChipDoc.getDadosChipVO().getErrorCode()+"-->");
            }

            xml.append("<puk1>"+dadosChipDoc.getDadosChipVO().getNrPUK1()+"</puk1>");

        }catch(TuxedoWarningException twe){
            logURA.log("uraURPK::URPK[TuxedoWarningException]", twe);
            if(twe.getXmlHeader().getError().equals("0001")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_CONTA_INEXISTE);
            }

        }catch(TuxedoErrorException tee){
            logURA.log("uraURPK::URPK[TuxedoErrorException]", tee);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee.getMessage());

        }catch(Exception ex){
            logURA.log("uraURPK::URPK[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");
        }

        if(saida.length()>0){
        	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            StringBuffer str = new StringBuffer();
            str.append("<cdAreaRegistro>"+cdDDD+"</cdAreaRegistro>");
            str.append("<nrLinha>"+cdNumTelefone+"</nrLinha>");
            str.append("<idTipoRelacionamento>2</idTipoRelacionamento>");
            str.append("<cdContato>ContatoConsultaPUKURA</cdContato>");
            str.append("<inRegistrarContato>1</inRegistrarContato>");
            str.append("<idCanal>9</idCanal>");

            String xmlTux = XmlManager.MakeXmlIn("30", "INCRGCONTATO", str.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOutContato = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOutContato+"<<");

            xmlManager.parseXmlOut(xmlOutContato);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND,xml.toString());

        }catch(TuxedoWarningException twe){
            logURA.log("uraURPK::URPK[TuxedoWarningException]", twe);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,"palitagem não parametrizada");

        }catch(TuxedoErrorException tee){
            logURA.log("uraURPK::URPK[TuxedoErrorException]", tee);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee.getMessage());

        }catch(Exception ex){
            logURA.log("uraURPK::URPK[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");

        }finally{
        	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /**
     * Validar o XML de entrada
     */
    public boolean validaXML(String input) throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
        String user = null;
        String transacao = null;
        String ddd = null;
        String telefone = null;

        if (document.getElementsByTagName("msg").item(0) == null) {
            throw new Exception("msg não encontrada");
        }

        if (document.getElementsByTagName("msgHdr").item(0) == null) {
            throw new Exception("Header não encontrado");
        }

        if (document.getElementsByTagName("msgBody").item(0).getChildNodes() == null) {
            throw new Exception("Body não encontrado");
        }

        user = document.getElementsByTagName("cdUsuario").item(0).getFirstChild().getNodeValue();
        transacao = document.getElementsByTagName("cdTransacao").item(0).getFirstChild().getNodeValue();
        ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue();
        telefone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue();

        if(user == null || user.trim().equals("") || user.trim().length() > 2 || !user.trim().equals("07")) {
            return false;
        }
        if(transacao == null || transacao.trim().equals("") || transacao.trim().length() > 4 || !transacao.equals("URPK")) {
            return false;
        }
        if(ddd == null || ddd.trim().length() != 2) {
            return false;
        }
        if(telefone == null || telefone.trim().equals("") || telefone.length() > 9 || telefone.length() < 8) {
            return false;
        }

        this.cdDDD = ddd;
        this.cdNumTelefone = telefone;
        return true;
    }

    private String getIP(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch(Exception ex){
            logURA.log("uraURPK::getIP[Exception]", ex);
            return "null";
        }
    }

}
