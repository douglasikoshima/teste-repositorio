package WebServicesWSDL.UraURBP_URVC_URSVSvr;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.xml.XmlManager;
import org.apache.struts.action.ActionForward;
import com.indracompany.actions.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import weblogic.apache.xml.serialize.OutputFormat;
import weblogic.apache.xml.serialize.XMLSerializer;
import weblogic.wtc.jatmi.TPException;


@SuppressWarnings("deprecation")
public class UraURBP_URVC_URSVSvrController extends AbstractAction {

    private static final long serialVersionUID = -4355557025271115923L;

    private final String cdCodigoRetorno = "cdCodigoRetorno";
    //private final String codErroTux = "04";
    private final String codErroXML = "02";

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURBP_URVC_URSVSvrController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URVC".equals(mapping.getParameter())) {
			return URVC(mapping, form, request, response);
		}else if ("URBP".equals(mapping.getParameter())) {
			return URBP(mapping, form, request, response);
		}else if ("URSV".equals(mapping.getParameter())) {
			return URSV(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward URVC(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URSV</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>48</cdDDD><numTelefone>91466917</numTelefone></msgBody></msg>
    	this.request=request;

        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOUT = null;// XML de saída
        String xmlIN  = null;// XML de entrada alterado

        // Valida XML de entrada de acordo com o padrão do FO
        try{
            xmlIN = validaXML(input, "getURAConta","URVC");

        }catch(Exception e){
            logURA.log("uraURBP_URVC_URSVSvr::URVC[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlIN.substring(xmlIN.indexOf("<msgBody>")+9,xmlIN.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

            XmlManager xmlManager = new XmlManager();
            xmlManager.parseXmlOut(xmlOUT);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURBP_URVC_URSVSvr::URVC[TuxedoErrorException]", tee);
            String error = tee.getXmlHeader().getError();
            if(error.equals("5001") || error.equals("0102") || error.equals("1032")){
                xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>04</cdCodigoRetorno>");
            }else{
                xmlOUT = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);
            }

        }catch(Exception ex){
            logURA.log("uraURBP_URVC_URSVSvr::URVC[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        }finally{
        	xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
   }

    /** @jpf:action */
    public ActionForward URBP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URSV</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>48</cdDDD><numTelefone>91466917</numTelefone><mesRef>052004</mesRef></msgBody></msg>
    	this.request=request;
    	String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOUT = null;// XML de saída
        String xmlIN  = null;// XML de entrada alterado

        // Valida XML de entrada de acordo com o padrão do FO
        try{
            xmlIN = validaXML(input, "getURABoletoFax","URBP");

        }catch (Exception e){
            logURA.log("uraURBP_URVC_URSVSvr::URBP[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlIN.substring(xmlIN.indexOf("<msgBody>")+9,xmlIN.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

            XmlManager xmlManager = new XmlManager();
            xmlManager.parseXmlOut(xmlOUT);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURBP_URVC_URSVSvr::URBP[TuxedoErrorException]", tee);
            String error = tee.getXmlHeader().getError();
            if(error.equals("5001") || error.equals("0102") || error.equals("1032")){
                xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>");
            }else{
                xmlOUT = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA);
            }

        }catch(Exception ex){
            logURA.log("uraURBP_URVC_URSVSvr::URBP[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /** @jpf:action */
    public ActionForward URSV(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>1</user><service>DispURASV</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>48</cdDDD><numTelefone>91466917</numTelefone></msgBody></msg>
    	this.request=request;
    	String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOUT = null;
        String xmlIN  = null;

        // Valida XML de entrada de acordo com o padrão do FO
        try{
            xmlIN = validaXML(input,"getURASegundaViaConta","URSV");

        }catch (Exception e){
            logURA.log("uraURBP_URVC_URSVSvr::URSV[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

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
            String xmlEnt = xmlIN.substring(xmlIN.indexOf("<msgBody>")+9,xmlIN.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

            XmlManager xmlManager = new XmlManager();
            xmlManager.parseXmlOut(xmlOUT);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURBP_URVC_URSVSvr::URSV[TuxedoErrorException]", tee);
            String error = tee.getXmlHeader().getError();
            if(error.equals("5001") || error.equals("0102") || error.equals("1032")){
                xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>");
            } else if (error.equals("5999")) {				
				xmlOUT = XmlManager.createRetorno(Constant.URA_STATCOM_OK, tee.getXmlHeader());
            }else{
                xmlOUT = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);
            }

        }catch(Exception ex){
            logURA.log("uraURBP_URVC_URSVSvr::URSV[Exception]", ex);
			String msgErro = ex.getMessage();
            /*  
			if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
				xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");
            } */
			xmlOUT = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>08</cdCodigoRetorno>");			
        }finally{
        	xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    private String validaXML(String input, String operacao,String cdContato) throws Exception {
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

        String mesRef = null;

        try{
            mesRef = document.getElementsByTagName("mesRef").item(0).getFirstChild().getNodeValue();
        }catch(Exception e){}

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue();
            String fone = document.getElementsByTagName("numTelefone").item(0).getFirstChild().getNodeValue();
            Node proxyLinha = document.createElement("ProxyLinha");


            // Faz parse so pra saber se são valores válidos.
            //int iddd = Integer.parseInt(ddd);
            //int ifone = Integer.parseInt(fone);

            // Verifica tamanho.
            if (ddd.length() != 2 || (fone.length() < 8 || fone.length() > 9)) {
                throw new Exception();
            } else {
                XmlManager.registrarContato(document,cdContato,ddd,fone,null);
            }

            if(operacao.equals("getURASegundaViaConta") || operacao.equals("getURABoletoFax")) {
                Node nodeMesRef = document.createElement("mesRef");
                Node nodeIdTipoRelacionamento = document.createElement("idTipoRelacionamento");
                nodeIdTipoRelacionamento.appendChild(document.createTextNode("2"));
                nodeMesRef.appendChild(document.createTextNode(mesRef));
                document.getElementsByTagName("msgBody").item(0).appendChild(nodeMesRef);
                document.getElementsByTagName("msgBody").item(0).appendChild(nodeIdTipoRelacionamento);
            }

            // Monta XML do Proxy.
            proxyLinha.appendChild(document.createTextNode(ddd.trim() + fone.trim()));
            Node proxyOperacao = document.createElement("ProxyOperacao");
            proxyOperacao.appendChild(document.createTextNode(operacao));
            document.getElementsByTagName("msgBody").item(0).appendChild(proxyLinha);
            document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);
            OutputFormat    format  = new OutputFormat( document );   //Serialize DOM
            StringWriter  stringOut = new StringWriter();        //Writer will be a String
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();                            // As a DOM Serializer
            serial.serialize( document.getDocumentElement() );
            return stringOut.toString() ;

        }catch(Exception e){
            // XML não tem as tags de cdDDD e numTelefone
            logURA.log("uraURBP_URVC_URSVSvr::validaXML[Exception]", e);
            throw new Exception("TAGs cdDDD e/ou numTelefone não encontradas");
        }
    }

}
