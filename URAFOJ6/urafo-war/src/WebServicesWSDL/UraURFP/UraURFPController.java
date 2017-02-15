package WebServicesWSDL.UraURFP;

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
import tuxProxyBE.vo.PlanoVODocument.PlanoVO;
import weblogic.apache.xml.serialize.OutputFormat;
import weblogic.apache.xml.serialize.XMLSerializer;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.xml.XmlManager;
import com.indracompany.actions.AbstractAction;

public class UraURFPController extends AbstractAction {

    private static final long serialVersionUID = -5507828160394204842L;

    private String statusOperacao;
    private String URA_CODIRET_PLANO_IGUAL = "05";
    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURFPController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URFP".equals(mapping.getParameter())) {
			return URFP(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return null;
    }

    /** @jpf:action */
    public ActionForward URFP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><status>P</status></msgBody></msg>
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><status>C</status><novoPlano>01</novoPlano></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String xmlInput = null;
        String xmlOut   = null;
        String saida    = "";

        XmlManager xmlManager = new XmlManager();
        try{
            xmlInput = this.convertInputXML(input, null);

        }catch(Exception ex){
            logURA.log("uraURFP::URFP[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);

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
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "TUXNGINBE", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

        }catch(TuxedoErrorException tee){
            logURA.log("uraURFP::URFP[TuxedoErrorException]", tee);
            if(tee.getXmlHeader().getError().equals("0710")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO, tee);

            }else if(tee.getXmlHeader().getError().equals("9004")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, URA_CODIRET_PLANO_IGUAL, tee);

            }else if(tee.getXmlHeader().getError().equals("8001") || tee.getXmlHeader().getError().equals("7073")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "07", tee);

            } else if(tee.getXmlHeader().getError().equals("3002")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, tee.getXmlHeader());
            }else if(tee.getXmlHeader().getError().equals("0013") || tee.getXmlHeader().getError().equals("0208")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0013", tee); // erro de timeout
            }else if(tee.getXmlHeader().getError().equals("0010")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0010", tee); // erro de comunicação de tuxedo
            }			
			else{
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tee);
            }
            
            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception ex){
            logURA.log("uraURFP::URFP[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            if(statusOperacao != null && statusOperacao.equalsIgnoreCase("p")){
                PlanoVO planoVO = (PlanoVO)xmlManager.outputInnerBody(xmlOut);
                StringBuffer sb = new StringBuffer();
                sb.append("<planoAtual>"+xmlManager.getTag(planoVO.getPlanoAtual())+"</planoAtual>");
                sb.append("<indTarifa>"+xmlManager.getTag(planoVO.getIndTarifa())+"</indTarifa>");
                sb.append("<valTarifa>"+xmlManager.getTag(planoVO.getValTarifa())+"</valTarifa>");
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND, sb.toString());

            }else{
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND);
            }

        }catch(Exception e){
            logURA.log("uraURFP::URFP[Exception]", e);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA);

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
     * Conversao para XML de entrada do TuxProxyBE
     */
    private String convertInputXML(String input,String cdContato) throws Exception {
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

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String ddd = null;
            String fone = null;
            String servico = null;
            String status = null;
            String cdDDDFavorito = null;
            String cdNumFavorito = null;
            String novplan = null;
            String user = null;

            try { user = document.getElementsByTagName("user").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { fone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { servico = document.getElementsByTagName("servico").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { status = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cdDDDFavorito = document.getElementsByTagName("cdDDDFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cdNumFavorito = document.getElementsByTagName("cdNumFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { novplan = document.getElementsByTagName("novoPlano").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) { }

            // zera o documento para retorno
            String xmlMsg = "<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>"+user+"</user><service>TuxProxyBE</service></msgHdr><msgBody></msgBody></msg>";
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlMsg.getBytes()));

            if(ddd!=null && fone!=null){
                Node proxyLinha = document.createElement("ProxyLinha");
                proxyLinha.appendChild(document.createTextNode(ddd.trim() + fone.trim()));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyLinha);
            }

            if(status!=null){
				String tagNovoPlano = "";
                Node proxyOperacao = document.createElement("ProxyOperacao");
                if(status.equalsIgnoreCase("c")){
					tagNovoPlano = "novoPlano";
                    proxyOperacao.appendChild(document.createTextNode("setPlano"));
                    XmlManager.registrarContato(document,"URFP",ddd,fone,null);
                }else if(status.equalsIgnoreCase("p")){
					tagNovoPlano = "proximoPlano";
                    proxyOperacao.appendChild(document.createTextNode("getPlano"));
                    Node idCanal = document.createElement("idCanal");
                    idCanal.appendChild(document.createTextNode("9"));
                    document.getElementsByTagName("msgBody").item(0).appendChild(idCanal);
                } else {
                    throw new Exception("Status inválido!");
                }
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);
                this.statusOperacao = status;

                if(novplan != null){
                    Node novoPlano = document.createElement(tagNovoPlano);
					novoPlano.appendChild(document.createTextNode(novplan));
                    document.getElementsByTagName("msgBody").item(0).appendChild(novoPlano);
                }else if(novplan == null && status.equalsIgnoreCase("c")){
                    throw new Exception("NovoPlano inexistente!");
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
            logURA.log("uraURFP::URFP[convertInputXML]", e);
            throw new Exception("Erro de input");
        }
    }
}
