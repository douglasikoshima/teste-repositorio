package WebServicesWSDL.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import noNamespace.MsgDocument;

import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import weblogic.apache.xml.serialize.OutputFormat;
import weblogic.apache.xml.serialize.XMLSerializer;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoException;
import WebServicesWSDL.exception.TuxedoWarningException;

import com.bea.document.DocumentException;
import com.bea.document.DocumentFactory;
import com.bea.document.IDocument;

/**
 * Classe responsável pela montagem do XML-IN e desmonta o XML-OUT derivado do tuxedo.
 **/
public class XmlManager {
	//Monta o mecanismo de log
	private static org.apache.commons.logging.Log log = LogFactory.getLog(XmlManager.class);

    //Classe de Buffer do XML
	private static String[] buffer = new String[5];

    private static WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log();

    private static String xPathMsg =  "XPath não encontrado:";

    private String      xml;
    private IDocument   document;

    private   String    service;
    private   String    user;
    private   String    statusCode;
    private   String    statusText;
    private   String    xpath;

    public XmlManager() {
        // default constructor
    }

    public XmlManager(String xml) throws XmlException {
        this.xml = xml;
        // parse XML
        try {
            this.document = DocumentFactory.createDocument(xml);
        } catch (Exception e) {
            throw new XmlException(e);
        }
    }

	/**
	 * @param xmlOut Xml retornado do servico Tuxedo, composto por msg, msgHdr e msgBody
	 * @return String[] contendo: <br>
	 * [0] - Id Sistema<br>
	 * [1] - Codigo de execucao<br>
	 * [2] - Codigo da mensagem de retorno<br>
	 * [3] - Mensagem de retorno<br>
	 * [4] - String contendo o XMLBean retornado
	 *
	 * @throws XmlException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String[] ParseXmlOut(String xmlOut) throws SAXException, IOException, ParserConfigurationException, XmlException {
        //Monta o log da operação se possível
        if( log.isDebugEnabled() ) {
            log.debug("\t\t\tXmlManager:ParseXmlOut(" + xmlOut + ")");
        }

		// efetua o parse do documento XML recebido
		ByteArrayInputStream bais = new ByteArrayInputStream(xmlOut.getBytes());
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

		// trata Header
		TrataMsgHdr(document.getElementsByTagName("msgHdr").item(0));

		// obtem XMLVO
		NodeList list = document.getElementsByTagName("msgBody").item(0).getChildNodes();
		if(list.getLength() > 1) {
            buffer[4] = XmlObject.Factory.parse(list.item(1)).newCursor().xmlText();
        } else {
            buffer[4] = "";
        }

        //Caso não tenha erro retorna operação
		return(buffer);
	}

	/**
	 * @param user Usuario logado que está efetuando a chamada
	 * @param service Servico Tuxedo a ser invocado
	 * @param xmlIn Parametrizacao do servido em formato XML
	 * @return String XML contendo o Xml formatado para execução do servico Tuxedo
	 */
	public static String MakeXmlIn(String user, String service, String xmlIn)  {
        //Monta o log da operação se possível
        if( log.isDebugEnabled() ) {
            log.debug("\t\t\tXmlManager:MakeXmlIn(" + user + ", " + service + ", " + xmlIn + ")");
        }

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		buffer.append("<msg>");
		buffer.append("<msgHdr>");
		buffer.append("<user>" + user + "</user>");
		buffer.append("<service>" + service + "</service>");
		buffer.append("<subSystem>24</subSystem>");
		buffer.append("</msgHdr>");
		buffer.append("<msgBody>");
		buffer.append(xmlIn);
		buffer.append("</msgBody>");
		buffer.append("</msg>");

		return(buffer.toString());
	}

	/**
	 * @param user Usuario logado que está efetuando a chamada
	 * @param service Servico Tuxedo a ser invocado
	 * @param xmlIn Parametrizacao do servido em formato XML
	 * @return String XML contendo o Xml formatado para execução do servico Tuxedo
	 */
	public static String MakeXmlOut(String user, String service, String xmlIn)  {
        //Monta o log da operação se possível
        if( log.isDebugEnabled() ) {
            log.debug("\t\t\tXmlManager:MakeXmlOut(" + user + ", " + service + ", " + xmlIn + ")");
        }

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		buffer.append("<msg>");
		buffer.append("<msgHdr>");
		buffer.append("<user>" + user + "</user>");
		buffer.append("<service>" + service + "</service>");
		buffer.append("<subSystem>24</subSystem>");
		buffer.append("</msgHdr>");
		buffer.append("<msgBody>");
		buffer.append(xmlIn);
		buffer.append("</msgBody>");
		buffer.append("</msg>");

        String logBuffer = buffer.toString();
        if(logBuffer!=null) {
            logBuffer = logBuffer.replaceAll("\n","");
        }

        logURA.log("[XMLOUT]"+logBuffer);

		return(buffer.toString());
	}

	private static void TrataMsgHdr(Node node) throws XmlException {
        //Monta o log da operação se possível
        if( log.isDebugEnabled() ) {
            log.debug("\t\t\tXmlManager:TrataMsgHdr(" + node + ")");
        }

		String statusCode = "";

		// efetua o parse do Header
		XmlObject msgHdr = XmlObject.Factory.parse(node);
		XmlCursor cursor = msgHdr.newCursor();

		// obtem o Status Code composto
		cursor.toFirstChild();
		cursor.push();
		cursor.selectPath("$this//statusCode");
		if(!cursor.toNextSelection()) {
			throw new XmlException("Tag <statusCode> não identificada.");
		}

		// decompoe o Status Code
		statusCode = cursor.getTextValue();
		buffer[0] = statusCode.substring(0, 2);
		buffer[1] = statusCode.substring(2, 3);
		buffer[2] = statusCode.substring(3, 7);
		cursor.pop();

		// obtem mensagem de retorno
		cursor.selectPath("$this//statusText");
		if(cursor.toNextSelection()) {
            buffer[3] = cursor.getTextValue();
        } else {
            buffer[3] = "";
        }
		cursor.dispose();
	}

    public Object outputInnerBody(String xmlOut) throws XmlException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg().getMsgBody().getInnerBody();
    }

    public Object outputInnerBody(File xmlOut) throws XmlException, IOException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg().getMsgBody().getInnerBody();
    }

    public Object output(String xmlOut) throws XmlException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg();
    }

    public Object output(File xmlOut) throws XmlException, IOException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg();
    }

    /**
     * Coloca as tags de contato para registrar
     */
    public static String registrarContato(String input,String operacao){
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            if(document.getElementsByTagName("msgBody").item(0).getChildNodes() != null){
                String cdDDD = "";
                String numTelefone = "";
                cdDDD = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue();
                try{
                    numTelefone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue();
                }catch(NullPointerException npe){
                    numTelefone = document.getElementsByTagName("numTelefone").item(0).getFirstChild().getNodeValue();
                }
                Node regContato = document.createElement("regContato");
                Node inRegistrarContato = document.createElement("inRegistrarContato");
                Node cdContato = document.createElement("cdContato");
                Node idCanal = document.createElement("idCanal");
                Node cdAreaRegistro = document.createElement("cdAreaRegistro");
                Node nrLinha = document.createElement("nrLinha");
				Node idTipoRelacionamento = document.createElement("idTipoRelacionamento");
				Node idGrupoAbertura = document.createElement("idGrupoAbertura");
                Node nodeIndPossuiFavoritos = document.createElement("IND_POSSUIFAVORITOS");
                Node nodeQtMaxInclusao = document.createElement("qtMaxInclusao");
                Node nodeOrigem = document.createElement("ORIGEM");

                inRegistrarContato.appendChild(document.createTextNode("1"));
                cdContato.appendChild(document.createTextNode(operacao));
                idCanal.appendChild(document.createTextNode("9")); // canal URA
                cdAreaRegistro.appendChild(document.createTextNode(cdDDD));
                nrLinha.appendChild(document.createTextNode(numTelefone));
				idTipoRelacionamento.appendChild(document.createTextNode("2"));
				idGrupoAbertura.appendChild(document.createTextNode("1534"));
                nodeIndPossuiFavoritos.appendChild(document.createTextNode("0"));
                nodeQtMaxInclusao.appendChild(document.createTextNode(""));
                nodeOrigem.appendChild(document.createTextNode("CCCCC"));

                regContato.appendChild(inRegistrarContato);
                regContato.appendChild(cdContato);
                regContato.appendChild(idCanal);
                regContato.appendChild(nrLinha);
                regContato.appendChild(cdAreaRegistro);
				regContato.appendChild(idTipoRelacionamento);
				regContato.appendChild(idGrupoAbertura);
                regContato.appendChild(nodeIndPossuiFavoritos);
                regContato.appendChild(nodeQtMaxInclusao);
                regContato.appendChild(nodeOrigem);

                document.getElementsByTagName("msgBody").item(0).appendChild(regContato);
                OutputFormat    format  = new OutputFormat( document );
                StringWriter  stringOut = new StringWriter();
                XMLSerializer    serial = new XMLSerializer( stringOut, format );
                serial.asDOMSerializer();
                serial.serialize( document.getDocumentElement() );
                return stringOut.toString();
            }else{
                return input;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return input;
        }
    }

    public static void registrarContato(Document document,String operacaoURA, String ddd, String fone, String qtMaxInclusao){
        try{
            // registra o contato se a operação for válida
            if(operacaoURA != null){
                Node regContato = document.createElement("regContato");
                Node inRegistrarContato = document.createElement("inRegistrarContato");
                Node cdContato = document.createElement("cdContato");
                Node idCanal = document.createElement("idCanal");
                Node cdAreaRegistro = document.createElement("cdAreaRegistro");
                Node nrLinha = document.createElement("nrLinha");
                inRegistrarContato.appendChild(document.createTextNode("1"));
				Node idGrupoAbertura = document.createElement("idGrupoAbertura");
				Node idTipoRelacionamento = document.createElement("idTipoRelacionamento");
                Node nodeIndPossuiFavoritos = document.createElement("IND_POSSUIFAVORITOS");
                Node nodeQtMaxInclusao = document.createElement("qtMaxInclusao");
                Node nodeOrigem = document.createElement("ORIGEM");

                cdContato.appendChild(document.createTextNode(operacaoURA));
                idCanal.appendChild(document.createTextNode("9")); // canal URA
                cdAreaRegistro.appendChild(document.createTextNode(ddd));
                nrLinha.appendChild(document.createTextNode(fone));
				idGrupoAbertura.appendChild(document.createTextNode("1534"));
				idTipoRelacionamento.appendChild(document.createTextNode("2"));
                nodeIndPossuiFavoritos.appendChild(document.createTextNode("0"));
                nodeQtMaxInclusao.appendChild(document.createTextNode(qtMaxInclusao));
                nodeOrigem.appendChild(document.createTextNode("BBBBB"));


                regContato.appendChild(inRegistrarContato);
                regContato.appendChild(cdContato);
                regContato.appendChild(idCanal);
                regContato.appendChild(nrLinha);
                regContato.appendChild(cdAreaRegistro);
				regContato.appendChild(idGrupoAbertura);
				regContato.appendChild(idTipoRelacionamento);
                regContato.appendChild(nodeIndPossuiFavoritos);
                regContato.appendChild(nodeQtMaxInclusao);
                regContato.appendChild(nodeOrigem);

                document.getElementsByTagName("msgBody").item(0).appendChild(regContato);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


 /* public static void registrarContato(Document document,String operacaoURA, String ddd, String fone, String indPossuiFavoritos, String qtMaxInclusao){
        try{
            // registra o contato se a operação for válida
            if(operacaoURA != null){
                Node regContato = document.createElement("regContato");
                Node inRegistrarContato = document.createElement("inRegistrarContato");
                Node cdContato = document.createElement("cdContato");
                Node idCanal = document.createElement("idCanal");
                Node cdAreaRegistro = document.createElement("cdAreaRegistro");
                Node nrLinha = document.createElement("nrLinha");
                inRegistrarContato.appendChild(document.createTextNode("1"));
				Node idGrupoAbertura = document.createElement("idGrupoAbertura");
				Node idTipoRelacionamento = document.createElement("idTipoRelacionamento");
                Node nodeIndPossuiFavoritos = document.createElement("IND_POSSUIFAVORITOS");
                Node nodeQtMaxInclusao = document.createElement("qtMaxInclusao");
                Node nodeOrigem = document.createElement("ORIGEM");


                cdContato.appendChild(document.createTextNode(operacaoURA));
                idCanal.appendChild(document.createTextNode("9")); // canal URA
                cdAreaRegistro.appendChild(document.createTextNode(ddd));
                nrLinha.appendChild(document.createTextNode(fone));
				idGrupoAbertura.appendChild(document.createTextNode("1534"));
				idTipoRelacionamento.appendChild(document.createTextNode("2"));
                nodeIndPossuiFavoritos.appendChild(document.createTextNode(indPossuiFavoritos));
                nodeQtMaxInclusao.appendChild(document.createTextNode(qtMaxInclusao));
                nodeOrigem.appendChild(document.createTextNode("AAAAAA"));


                regContato.appendChild(inRegistrarContato);
                regContato.appendChild(cdContato);
                regContato.appendChild(idCanal);
                regContato.appendChild(nrLinha);
                regContato.appendChild(cdAreaRegistro);
				regContato.appendChild(idGrupoAbertura);
				regContato.appendChild(idTipoRelacionamento);
                regContato.appendChild(nodeOrigem);

                regContato.appendChild(nodeIndPossuiFavoritos);
                regContato.appendChild(nodeQtMaxInclusao);

                document.getElementsByTagName("msgBody").item(0).appendChild(regContato);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    */


    /**
     * Faz o parse do XML retornado pelo serviço Tuxedo
     */
    public XmlHeader parseXmlOut(String xmlOut) throws XmlException,TuxedoException,ParserConfigurationException,SAXException,IOException{
       Document document = null;
       String statusCode = null;
       String statusText = null;
       String service = null;
       String user = null;
       String system = null;
       String error = null;
       Node msgHdr = null;
       char severity = 0;
       XmlHeader xmlHeader = null;
       ByteArrayInputStream bais = new ByteArrayInputStream(xmlOut.getBytes());
       document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
       msgHdr = document.getElementsByTagName("msgHdr").item(0);
       if(msgHdr!=null){
            NodeList list = msgHdr.getChildNodes();
            statusCode = getTagHeader(list,"statusCode");
            statusText = getTagHeader(list,"statusText");
            service = getTagHeader(list,"service");
            user = getTagHeader(list,"user");
            severity = statusCode.charAt(2);
            error = statusCode.substring(3,statusCode.length());
            system = statusCode.substring(0,2);
       }
       try{
            xmlHeader = new XmlHeader(service,user,system,severity,error,statusText);
       }catch(IllegalArgumentException iae){
            throw new XmlException(iae.getCause());
       }
       if(xmlHeader.getSeverity() == 'E'){
            throw new TuxedoErrorException(xmlHeader);
       }
       else
       if(xmlHeader.getSeverity() == 'W'){
            throw new TuxedoWarningException(xmlHeader);
       }
       return xmlHeader;
    }

    /**
     * retorna o Node especifico
     */
    private String getTagHeader(NodeList list,String name){
        for(int i=0;i<list.getLength();i++){
            if(list.item(i).getNodeName().equalsIgnoreCase(name) && list.item(i).getFirstChild() != null) {
                return list.item(i).getFirstChild().getNodeValue();
            }
        }
        return null;
    }

    // cria retorno de erro
    public static String createRetorno( String statCom) {
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>" + statCom + "</statCom>");
    }

    public static String createRetorno( String statCom, String codiRet ){
        return createRetorno ( statCom, codiRet, "" );
    }

    public static String createRetorno( String statCom, String codiRet, String append ){
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE",
            "<statCom>" + statCom + "</statCom><cdCodigoRetorno>" + codiRet + "</cdCodigoRetorno>" + append );
    }

    public static String createRetorno( String statCom, XmlHeader header ){    	    	
    	String codigo = null;
    	String descricao = null;
    	try {
    		String statusText = header.getStatusText();
	    	if (statusText != null) {
	    		if (statusText.indexOf("|") != -1) {
	    			String array[] = statusText.split("\\|");
	    			if (array.length > 1) {
	    				codigo = array[0];
	    				descricao = array[1];
	    			} else {
	    				codigo = array[0];
	    				descricao = "";
	    			}
	    		} else {
	    			codigo = "04";
	        		descricao = "Erro de subrotina";
	    		}
	    	} else {
	    		codigo = "04";
	    		descricao = "Erro de subrotina";
	    	}
    	} catch (Exception ex) {
    		codigo = "04";
    		descricao = "Erro de subrotina";    		
    	}
        return createRetorno ( statCom, codigo, "<!--" + descricao + "-->" );
    }    

    public static String createRetorno( String statCom, String codiRet, TuxedoException te ){
        StringBuffer sb = new StringBuffer();
        try{
            sb.append("<!-- ");
            sb.append(te.getXmlHeader().getStatusText());
            sb.append(" -->");
        }catch(Exception ex){
            sb.append("");
        }
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE",
            "<statCom>" + statCom + "</statCom><cdCodigoRetorno>" + codiRet + "</cdCodigoRetorno>" + sb.toString() );
    }
	
    public static String createRetornoURAD( String statCom, String codiRet, TuxedoException te ){
        StringBuffer sb = new StringBuffer();
        try{
			if ("1036".equals(te.getXmlHeader().getError())) {
				int index = te.getXmlHeader().getStatusText().indexOf("-");
				String codigo = te.getXmlHeader().getStatusText().substring(0, index-1);
				String mensagem = te.getXmlHeader().getStatusText().substring(index+2,te.getXmlHeader().getStatusText().length());				
				return XmlManager.MakeXmlOut("ura", "TuxProxyBE",
				"<statCom>" + statCom + "</statCom><cdCodigoRetorno>" + codigo + "</cdCodigoRetorno>" + "<!-- " + mensagem + " -->");
			} else {
				sb.append("<!-- ");
				sb.append(te.getXmlHeader().getStatusText());
				sb.append(" -->");
			}
        }catch(Exception ex){
            sb.append("");
        }
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE",
            "<statCom>" + statCom + "</statCom><cdCodigoRetorno>" + codiRet + "</cdCodigoRetorno>" + sb.toString() );
    }	

    public static String getTag(String str){
        if(str == null) {
            return "";
        } else {
            return str;
        }
    }

    public XmlIn getXmlIn() throws XmlException {

        //String me = "XmlManager:getXmlIn";

        parseService();
        parseUser();

        // create XmlHeader
        log.debug("XmlManager:getXmlIn // create XmlHeader");
        return new XmlIn(user, service, null);
    }

    private void parseService() throws XmlException {
        try {
            xpath = "/msg/msgHdr/service";
            service = document.getStringFrom(xpath);
            if (service.trim().length() == 0) {
                throw new XmlException(xPathMsg + xpath);
            }
        } catch (DocumentException e) {
            throw new XmlException(e);
        }

    }

    private void parseUser() throws XmlException {
        try {
            xpath = "/msg/msgHdr/user";
            user = document.getStringFrom(xpath);
            if (user.trim().length() == 0) {
                throw new XmlException(xPathMsg + xpath);
            }
        } catch (DocumentException e) {
            throw new XmlException(e);
        }
    }

    private void parseStatusCode() throws XmlException {
        try {
            xpath = "/msg/msgHdr/statusCode";
            statusCode = document.getStringFrom(xpath);
            if (statusCode.trim().length() == 0) {
                throw new XmlException(xPathMsg + xpath);
            }
            if ((statusCode == null) || (statusCode.length() != 7)) {
                throw new XmlException("Valor ilegal:statusCode="+statusCode);
            }
        } catch (DocumentException e) {
            throw new XmlException(e);
        }
    }

    private void parseStatusText() throws XmlException {
        try {
            xpath = "/msg/msgHdr/statusText";
            statusText = document.getStringFrom(xpath);
            if (statusText.trim().length() == 0) {
                throw new XmlException(xPathMsg + xpath);
            }
        } catch (DocumentException e) {
            throw new XmlException(e);
        }
    }

	public String parseXml() throws XmlException, TuxedoException {

        //String me = "XmlManager:parseXml";
        log.debug("XmlManager:parseXml started");

        XmlHeader header = getXmlHeader();
        char severity = header.getSeverity();
        if ("EW".indexOf(severity) != -1) {
            if (severity == 'E') {
                throw new TuxedoErrorException(header);
            }
            if (severity == 'W') {
                throw new TuxedoWarningException(header);
            }
        }

        // declare variables
        String body = "";
        try {
            // call internalParse
            IDocument doc = getXmlBodyDocument();
            if (doc != null) {
                body = doc.toXML();
            }

        } catch (XmlException e) {
            log.error("XmlException - XmlManager:parseXml - ["+ e.getMessage()+ "]");
            throw e;

        } catch (DocumentException e) {
            log.error("DocumentException - XmlManager:parseXml - ["+ e.getMessage()+ "]");
            throw new XmlException(e);
        }

        log.debug("XmlManager:parseXml ended");
        return body;
    }

    public XmlHeader getXmlHeader() throws XmlException {
        // String me = "XmlManager:getXmlHeader";
        parseService();
        parseUser();
        parseStatusCode();
        parseStatusText();

        // break statusCode into its components
        String  system      = statusCode.substring(0, 2);
        char    severity    = statusCode.charAt(2);
        String  error       = statusCode.substring(3, 7);
        // create XmlHeader
        log.debug("XmlManager:getXmlHeader // create XmlHeader");
        return new XmlHeader(service, user, system, severity, error, statusText);
    }

    private IDocument getXmlBodyDocument() throws XmlException {

        //String me = "XmlManager:getXmlBodyDocument";
        IDocument     doc;

        // parse XML
        try {
            doc = DocumentFactory.createDocument(xml);
        } catch (Exception e) {
            throw new XmlException(e);
        }

        String xpath = null;
        try {
            // get information from Body part
            xpath = "/msg/msgBody/*";
            IDocument[] docArray = doc.getSubDocuments(xpath);
            if (docArray == null) {
                throw new XmlException(xPathMsg + xpath);
            } else if (docArray.length == 0) {
                return null;
            } else {
                return docArray[0];
            }
        } catch (DocumentException e) {
            log.error("DocumentException - XmlManager:getXmlBodyDocument - ["+ e.getMessage()+ "]");
            throw new XmlException(e);
        }
    }
    
    public static String encodingChars(String xml) {
    	if (xml != null) {
    		xml = xml.replaceAll(">", "&gt;");
    		xml = xml.replaceAll("<", "&lt;");
    	}
    	return xml;
    }
       

}
