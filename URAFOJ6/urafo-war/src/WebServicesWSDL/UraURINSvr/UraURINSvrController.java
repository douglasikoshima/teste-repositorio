package WebServicesWSDL.UraURINSvr;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import noNamespace.MsgDocument;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

import tuxProxyBE.vo.URENPrePagoVODocument;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

public class UraURINSvrController extends AbstractAction {

    private static final long serialVersionUID = 2807141967509984200L;

    private String cdCodigoRetorno = "cdCodigoRetorno";
    private String codErroXML = "02";

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURINSvrController");


    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URINsID".equals(mapping.getParameter())) {
			return URINsID(mapping, form, request, response);
		} else if ("URINID".equals(mapping.getParameter())) {
			return URINID(mapping, form, request, response);
		} else if ("URINIDFIXA".equals(mapping.getParameter())) {
			return URINIDFIXA(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return null;
    }


    public ActionForward URINsID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Dado de teste.
        // <msg><msgHdr><user>URA</user><service>URINID</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>99765215</numTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String xmlOUT = null;

        // Valida XML de entrada de acordo com o padrão do FO
        try{
            validaXML(input);


        }catch(Exception e){
            logURA.log("uraURINSvr::URINsID[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHASEMID", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "VALSENHASEMID", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

        }catch (Exception ex){
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            logURA.log("uraURINSvr::URINsID[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHASEMID", "<statCom>3</statCom>"+"<!-- "+msgErro+" -->");

        } finally {
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }


    public ActionForward URINID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <msg><msgHdr><user>URA</user><service>URINID</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>99765215</numTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String xmlOUT = "";

        // Valida XML de entrada de acordo com o padrão do FO
        try {
            validaXML(input);

        } catch (Exception e) {
            logURA.log("uraURINSvr::URINID[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHAID", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "VALSENHAID", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

        } catch (Exception ex) {
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            logURA.log("uraURINSvr::URINID[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHAID", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        } finally {
        	xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }
    
    public ActionForward URINIDFIXA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <msg><msgHdr><user>URA</user><service>URINIDFIXA</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>99765215</numTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String xmlOUT = "";

        // Valida XML de entrada de acordo com o padrão do FO
        try {
            validaXML(input);

        } catch (Exception e) {
            logURA.log("uraURINSvr::URINID[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHAFXID", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "VALSENHAFXID", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

        } catch (Exception ex) {
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            logURA.log("uraURINSvr::URINIDFIXA[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "VALSENHAFXID", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        } finally {
        	xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    private void validaXML(String input) throws Exception {
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        if (document.getElementsByTagName("msg").item(0) == null) {
            throw new Exception("msg não encontrada");
        }

        if (document.getElementsByTagName("msgHdr").item(0) == null) {
            throw new Exception("Header não encontrado");
        }

        if (document.getElementsByTagName("msgBody").item(0).getChildNodes() == null) {
            throw new Exception("Body não encontrado");
        }
    }

    public boolean validaInput(String stringInput){
        try{
            // Verificando a integridade da String enviada pela URA:
            MsgDocument msgInput = MsgDocument.Factory.parse(stringInput);
            URENPrePagoVODocument inputVODoc = URENPrePagoVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            return true; // String OK

        }catch(Exception e){
            logURA.log("uraUREN::validaInput[Exception]", e);
            return false; // String Não OK
        }
    }

}
