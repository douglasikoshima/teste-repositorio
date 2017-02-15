package WebServicesWSDL.UraURTC;

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
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;
import com.indracompany.actions.AbstractAction;

public class UraURTCController extends AbstractAction {

    private static final long serialVersionUID = -2192377190583585574L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURTCController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URTC".equals(mapping.getParameter())) {
			return URTC(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward URTC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><cdNumTelefone>96281981</cdNumTelefone><tipo>00</tipo><status>P</status></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlInput = null;
        String xmlOut   = null;
        String saida    = "";
        XmlManager xmlManager = new XmlManager();
        StringBuffer buffer = new StringBuffer();

        try{
            xmlInput = convertInputXML(input);

        }catch(Exception ex){
            logURA.log("uraURTC::URTC[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURTC::URTC[TuxedoWarningException]", twe);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURTC::URTC[TuxedoErrorException]", tee);
            if(tee.getXmlHeader().getError().equals("8096")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",tee);
            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee);
            }

        }catch(Exception ex){
            logURA.log("uraURTC::URTC[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.MakeXmlOut("ura", "URTC", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");
        }

        if(saida.length()>0){
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        // sucesso
        ByteArrayInputStream bais = new ByteArrayInputStream(xmlOut.getBytes());
        Document document = null;

        try{
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            Node tipoFaturaNode = document.getElementsByTagName("tipoFatura").item(0);
            Node indicadorTarifaNode = document.getElementsByTagName("indicadorTarifa").item(0);
            Node valorTarifaNode = document.getElementsByTagName("valorTarifa").item(0);
            String tipoFatura = (tipoFaturaNode!=null)?document.getElementsByTagName("tipoFatura").item(0).getFirstChild().getNodeValue():null;
            String indicadorTarifa = (indicadorTarifaNode!=null)?document.getElementsByTagName("indicadorTarifa").item(0).getFirstChild().getNodeValue():null;
            String valorTarifa = (valorTarifaNode!=null)?document.getElementsByTagName("valorTarifa").item(0).getFirstChild().getNodeValue():null;
            if(tipoFatura!=null) {
                buffer.append("<tipoFatura>"+tipoFatura+"</tipoFatura>");
            }
            if(indicadorTarifa!=null) {
                buffer.append("<indicadorTarifa>"+indicadorTarifa+"</indicadorTarifa>");
            }
            if(valorTarifa!=null) {
                buffer.append("<valorTarifa>"+valorTarifa+"</valorTarifa>");
            }

            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND, buffer.toString());

        }catch(Exception ex){
            logURA.log("uraURTC::URTC[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, "erro ao fazer parse de saída");

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /**
     * converter XML de entrada para TUXATLYSURA
     */
    private String convertInputXML(String input) throws Exception {
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        // trata tag principal msg
        if (document.getElementsByTagName("msg").item(0) == null) {
            throw new Exception("msg não encontrada");
        }
        // trata Header
        if (document.getElementsByTagName("msgHdr").item(0) == null) {
            throw new Exception("Header não encontrado");
        }
        // obtem XMLVO
        if (document.getElementsByTagName("msgBody").item(0).getChildNodes() == null) {
            throw new Exception("Body não encontrado");
        }

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String ddd = null;
            String fone = null;
            String servico = null;
            String status = null;
            String tipo = null;

            try { ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { fone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { servico = document.getElementsByTagName("servico").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { status = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { tipo = document.getElementsByTagName("tipo").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}

            // zera o documento para retorno
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>1</user><service>TUXATLYSURA</service></msgHdr><msgBody></msgBody></msg>".getBytes()));

            if(ddd!=null && fone!=null){
                Node proxyLinha = document.createElement("ProxyLinha");
                proxyLinha.appendChild(document.createTextNode(ddd.trim() + fone.trim()));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyLinha);
            }

            if(status!=null){
                Node proxyOperacao = document.createElement("ProxyOperacao");
                if(status.equalsIgnoreCase("c")){
                    proxyOperacao.appendChild(document.createTextNode("setTipoFatura"));
                }else if(status.equalsIgnoreCase("p")){
                    proxyOperacao.appendChild(document.createTextNode("getTipoFatura"));
                }
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);
            }

            if(tipo != null){
                Node nodeTipo = document.createElement("tipo");
                nodeTipo.appendChild(document.createTextNode(tipo));
                document.getElementsByTagName("msgBody").item(0).appendChild(nodeTipo);
                if(status.equalsIgnoreCase("c")){
                    if(tipo.equals("00")) {
                        XmlManager.registrarContato(document,"URA_TROCAR_TIPO_FATURA_RESUMIDA",ddd,fone,null);
                    } else if(tipo.equals("01")) {
                        XmlManager.registrarContato(document,"URA_TROCAR_TIPO_FATURA_INTERMEDIARIA",ddd,fone,null);
                    } else if(tipo.equals("02")) {
                        XmlManager.registrarContato(document,"URA_TROCAR_TIPO_FATURA_DETALHADA",ddd,fone,null);
                    }
                }
            }

            Node xmlns = document.createElement("xmlns");
            xmlns.appendChild(document.createTextNode("tuxProxyBE/vo"));
            document.getElementsByTagName("msgBody").item(0).appendChild(xmlns);

            Node usuario = document.createElement("usuario");
            usuario.appendChild(document.createTextNode("URA"));
            document.getElementsByTagName("msgBody").item(0).appendChild(usuario);

            OutputFormat    format  = new OutputFormat( document );     //Serialize DOM
            StringWriter  stringOut = new StringWriter();               //Writer will be a String
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();                                   // As a DOM Serializer
            serial.serialize( document.getDocumentElement() );
            return stringOut.toString() ;

        }catch(Exception e){
            logURA.log("uraURTC::convertInputXML[Exception]", e);
            throw new Exception("Erro de input");
        }
    }
}
