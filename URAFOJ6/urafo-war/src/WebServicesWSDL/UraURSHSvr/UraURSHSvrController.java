package WebServicesWSDL.UraURSHSvr;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import weblogic.apache.xml.serialize.OutputFormat;
import weblogic.apache.xml.serialize.XMLSerializer;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

public class UraURSHSvrController extends AbstractAction {

    private static final long serialVersionUID = 1376772157997133349L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURSHSvrController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("TROCASENHA".equals(mapping.getParameter())) {
			return TROCASENHA(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward TROCASENHA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dados de teste
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URSH</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>88010006</numTelefone><dsSenhaAnteriorCript>5297</dsSenhaAnteriorCript><dsSenhaNovaCript>1111</dsSenhaNovaCript><sgTitularidade>C</sgTitularidade><senhaVazia>S</senhaVazia></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String cdCodigoRetorno = "cdCodigoRetorno";
        String codErroXML = "02";
        String xmlOUT = null;
        boolean gravaSenha = false;
        try{
            gravaSenha = validaXML(input);

        }catch(Exception e){
            logURA.log("uraURSHSvr::TROCASENHA[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "TROCASENHA", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        if(gravaSenha){
            xmlOUT = gravaSenha(input);

        }else{
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
                String xmlTux = XmlManager.MakeXmlIn("30", "TROCASENHA", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

            }catch(Exception ex){
                // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
                // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
                // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
                logURA.log("uraURSHSvr::TROCASENHA[Exception]", ex);
                String msgErro = ex.getMessage();
                if(ex instanceof TPException){
                    try {
                        String exception = ControlXMLExceptionLookup.getXMLString(ex);
                        String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                        msgErro = xmlRetorno[3];
                    }catch(Exception e) {}
                }
                xmlOUT = XmlManager.MakeXmlOut("ura", "TROCASENHA", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");
            }
        }

        xmlOUT = PREFIXO_SAIDA_XML.concat(xmlOUT).concat(SUFIXO_SAIDA_XML);
        
        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /**
     * Grava a senha
     * o Formato do XML de entrada do serviço GRAVASENHA não é o mesmo para TROCASENHA
     */
    private String gravaSenha(String input){

        XmlManager xmlManager = new XmlManager();
        String xmlIn = null;
        String xmlOut = null;
        Document document = null;
        Document documentInput = null;
        String user = null;
        String xmlStruct = null;

        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            documentInput = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            user = documentInput.getElementsByTagName("user").item(0).getFirstChild().getNodeValue();
            xmlStruct = "<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>"+user+"</user><service>GRAVASENHA</service></msgHdr><msgBody></msgBody></msg>";
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlStruct.getBytes()));
            String cdAreaRegistro = documentInput.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue();
            String nrLinha = documentInput.getElementsByTagName("numTelefone").item(0).getFirstChild().getNodeValue();
            String idTipoRelacionamento = documentInput.getElementsByTagName("sgTitularidade").item(0).getFirstChild().getNodeValue();
            String cdSenha = documentInput.getElementsByTagName("dsSenhaNovaCript").item(0).getFirstChild().getNodeValue();
            String idCanal = "9";

            // Usuario = 1, Cliente = 2
            if(idTipoRelacionamento.equalsIgnoreCase("u")){
                idTipoRelacionamento = "1";
            }else if(idTipoRelacionamento.equalsIgnoreCase("c")){
                idTipoRelacionamento = "2";
            }

            Node msgBody = document.getElementsByTagName("msgBody").item(0);
            Node ncdAreaRegistro = document.createElement("cdAreaRegistro");
            Node nnrLinha = document.createElement("nrLinha");
            Node nidTipoRelacionamento = document.createElement("idTipoRelacionamento");
            Node ncdSenha = document.createElement("cdSenha");
            Node nidCanal = document.createElement("idCanal");
            ncdAreaRegistro.appendChild(document.createTextNode(cdAreaRegistro));
            nnrLinha.appendChild(document.createTextNode(nrLinha));
            nidTipoRelacionamento.appendChild(document.createTextNode(idTipoRelacionamento));
            ncdSenha.appendChild(document.createTextNode(cdSenha));
            nidCanal.appendChild(document.createTextNode(idCanal));
            msgBody.appendChild(ncdAreaRegistro);
            msgBody.appendChild(nnrLinha);
            msgBody.appendChild(nidTipoRelacionamento);
            msgBody.appendChild(ncdSenha);
            msgBody.appendChild(nidCanal);
            OutputFormat    format  = new OutputFormat( document );
            StringWriter  stringOut = new StringWriter();
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();
            serial.serialize( document.getDocumentElement() );
            xmlIn = stringOut.toString();

        }catch(Exception e){
            logURA.log("uraURSHSvr::gravaSenha[Exception]", e);
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlIn.substring(xmlIn.indexOf("<msgBody>")+9,xmlIn.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "GRAVASENHA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURSHSvr::gravaSenha[TuxedoWarningException]", twe);
            if(twe.getXmlHeader().getError().equals("0001")){
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);
            }else if(twe.getXmlHeader().getError().equals("0004")){
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"13",twe);
            }
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);

        }catch(Exception e){
            logURA.log("uraURSHSvr::gravaSenha[Exception]", e);
            try {
                String exception = ControlXMLExceptionLookup.getXMLString(e);
                String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                String erroComment = "<!-- " + xmlRetorno[3] + " -->";
                System.out.println( erroComment );
                if (xmlRetorno[2] != null && xmlRetorno[2].trim().equalsIgnoreCase("1403")) {
                    return XmlManager.MakeXmlOut("ura", "TROCASENHA", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>" + erroComment);
                }
                return XmlManager.MakeXmlOut("ura", "TROCASENHA", "<statCom>3</statCom>" + erroComment);

            }catch(Exception ex){
                logURA.log("uraURSHSvr::gravaSenha[Exception]", ex);
                return XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR);
            }
        }
        return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND);
    }

    private boolean validaXML(String input) throws Exception {
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

        if (document.getElementsByTagName("senhaVazia").item(0) == null){// verifica a tag senhaVazia
            throw new Exception("Tag de senha não encontrado");
        }else{
           String senhaVazia = document.getElementsByTagName("senhaVazia").item(0).getFirstChild().getNodeValue();
           if(senhaVazia.equalsIgnoreCase("S")){
                return true;
           }
        }
        return false;
    }

}
