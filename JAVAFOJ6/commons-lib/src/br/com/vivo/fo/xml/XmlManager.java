package br.com.vivo.fo.xml;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.bea.document.DocumentException;
import com.bea.document.DocumentFactory;
import com.bea.document.IDocument;

public class XmlManager implements Serializable {

	private static final long serialVersionUID = -4623965482838346914L;
	private static Logger log = new Logger("start");
	private static String xPathMsg = "XPath não encontrado:";
	private String xml;
	private IDocument document;
	private String service;
	private String user;
	private String statusCode;
	private String statusText;
	private String xpath;

	public XmlManager() throws XmlException {
	}

	public XmlManager(String xml) throws XmlException {
		this.xml = xml;
		try {
			this.document = DocumentFactory.createDocument(xml);
		} catch (Exception e) {
			throw new XmlException(e);
		}
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
				throw new XmlException("Valor ilegal:statusCode=" + statusCode);
			}
		} catch (DocumentException e) {
			throw new XmlException(e);
		}
	}

	@SuppressWarnings("unused")
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

	private IDocument getXmlBodyDocument() throws XmlException {
		// String me = "XmlManager:getXmlBodyDocument";
		IDocument doc;
		try {
			doc = DocumentFactory.createDocument(xml);
		} catch (Exception e) {
			throw new XmlException(e);
		}

		String xpath = null;
		try {
			// get information from Body part
			xpath = "/msg/msgBody/child::*";
			IDocument[] docArray = doc.getSubDocuments(xpath);
			if (docArray == null) {
				throw new XmlException(xPathMsg + xpath);
			} else if (docArray.length == 0) {
				return null;
			} else {
				return docArray[0];
			}
		} catch (DocumentException e) {
			log.error("DocumentException - XmlManager:getXmlBodyDocument - [" + e.getMessage() + "]");
			throw new XmlException(e);
		}
	}

	public XmlIn getXmlIn() throws XmlException {
		// String me = "XmlManager:getXmlIn";
		parseService();
		parseUser();

		log.debug("XmlManager:getXmlIn // create XmlHeader");
		return new XmlIn(user, service, null);
	}

	public XmlHeader getXmlHeader() throws XmlException {

		parseService();
		parseUser();
		parseStatusCode();
		// parseStatusText();

		// break statusCode into its components
		String system = statusCode.substring(0, 2);
		char severity = statusCode.charAt(2);
		String error = statusCode.substring(3, 7);
		// create XmlHeader
		log.debug("XmlManager:getXmlHeader // create XmlHeader");
		return new XmlHeader(service, user, system, severity, error, statusText);
	}

	public String parseXml() throws XmlException, TuxedoException {

		// String me = "XmlManager:parseXml";

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
			log.error("XmlException - XmlManager:parseXml - [" + e.getMessage() + "]");
			throw e;
		} catch (DocumentException e) {
			log.error("DocumentException - XmlManager:parseXml - [" + e.getMessage() + "]");
			throw new XmlException(e);
		}

		log.debug("XmlManager:parseXml ended");
		return body;

	}

	@SuppressWarnings("rawtypes")
	public XmlObject parseXml(SchemaType schemaType) throws XmlException, TuxedoException {

		log.debug("XmlManager:parseXml started");

		if (schemaType == null) {
			throw new NullPointerException("schemaType is null");
		}
		if (!schemaType.isDocumentType()) {
			throw new XmlException("schemaType must be a DocumentType");
		}

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
		XmlObject vo = null;
		// String me = new String("XmlManager:parseXml");

		try {
			// call internalParse
			IDocument doc = getXmlBodyDocument();
			// check for namespace
			log.debug("XmlManager:parseXml // do some syntatic analysis");
			if (doc.getDocumentElement().getAttribute("xmlns").trim().length() == 0) {
				throw new XmlException("XmlManager:parseXml: Missing namespace");
			}
			// try to recognize XML Document from the Body part
			log.debug("XmlManager:parseXml // try to recognize XML Document from the Body part");
			SchemaTypeLoader xmlLoader = XmlBeans.getContextTypeLoader();
			vo = xmlLoader.parse(doc.getDocumentElement(), schemaType, new XmlOptions().setCharacterEncoding(ConstantesCRM.SISO));

			// validate document type against schema
			log.debug("XmlManager:parseXml // validate document type against schema");
			XmlOptions validateOptions = new XmlOptions();
			ArrayList errorList = new ArrayList();
			validateOptions.setErrorListener(errorList);
			boolean isValid = vo.validate(validateOptions);
			if (!isValid) {
				StringBuffer sb = new StringBuffer();
				sb.append("XmlManager:parseXml: Document should be ").append(schemaType.getFullJavaName());
				for (int i = 0; i < errorList.size(); i++) {
					XmlError error = (XmlError) errorList.get(i);
					sb.append("\n");
					sb.append("Message: ").append(error.getMessage()).append("\n");
					sb.append("Location of invalid XML: ").append(error.getCursorLocation().xmlText()).append("\n");
				}
				throw new XmlException(sb.toString());
			}
		} catch (XmlException e) {
			log.error("XmlException - XmlManager:parseXml - [" + e.getMessage() + "]");
			throw e;
		}

		log.debug("XmlManager:parseXml ended");
		return vo;
	}

	public static String[] ParseXmlOut(String xmlOut) throws SAXException, IOException, ParserConfigurationException, XmlException {
		String[] buffer = new String[5];
		try {
			//log.debug(new StringBuffer("XmlManager:ParseXmlOut(").append(xmlOut).append(")").toString());

			// efetua o parse do documento XML recebido
			ByteArrayInputStream bais = new ByteArrayInputStream(xmlOut.getBytes());
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

			// TrataMsgHdr(document.getElementsByTagName("msgHdr").item(0));
			Node node = document.getElementsByTagName("msgHdr").item(0);

			try {
				log.debug(new StringBuffer("XmlManager:TrataMsgHdr(").append(node).append(")").toString());
			} catch (Exception e) {
				log.warn("Exception - Error al sacar traza 2 - [" + e.getMessage() + "]");
			}

			if (node == null) {
				log.error("XmlManager:TrataMsgHdr NODE ES IGUAL A NULL");
				throw new XmlException("NODE ES IGUAL A NULL");
			}

			// efetua o parse do Header
			XmlObject msgHdr = XmlObject.Factory.parse(node);
			XmlCursor cursor = msgHdr.newCursor();

			// obtem o Status Code composto
			cursor.toFirstChild();
			cursor.push();
			cursor.selectPath("$this//statusCode");
			if (!cursor.toNextSelection()) {
				throw new XmlException("Tag <statusCode> não identificada.");
			}

			log.debug("XmlManager:TrataMsgHdr decompoe o Status Code");
			// decompoe o Status Code
			String statusCode = cursor.getTextValue();
			buffer[0] = statusCode.substring(0, 2);
			buffer[1] = statusCode.substring(2, 3);
			buffer[2] = statusCode.substring(3, 7);
			cursor.pop();

			log.debug("XmlManager:TrataMsgHdr depois de decompoe o Status Code");
			// obtem mensagem de retorno
			cursor.selectPath("$this//statusText");
			if (cursor.toNextSelection()) {
				buffer[3] = cursor.getTextValue();
			} else {
				buffer[3] = "";
			}
			cursor.dispose();

			// obtem XMLVO
			NodeList list = document.getElementsByTagName("msgBody").item(0).getChildNodes();
			if (list.getLength() > 1) {
				buffer[4] = XmlObject.Factory.parse(list.item(1)).newCursor().xmlText();
			} else {
				buffer[4] = "";
			}

			// Caso não tenha erro retorna operação
			// return buffer;
		} catch (SAXException se) {
			log.debug("Error de SAXException");
			throw se;

		} catch (IOException ioe) {
			log.debug("Error de IOException");
			throw ioe;

		} catch (ParserConfigurationException pce) {
			log.debug("Error de ParserConfigurationException");
			throw pce;

		} catch (XmlException xmle) {
			log.debug("Error de XmlException");
			throw xmle;

		} catch (Exception e) {
			log.debug(e.getMessage());
			log.fatal("Esta excepcion no se lanza : " + e.getMessage());
		}
		return buffer;
	}

	@SuppressWarnings("rawtypes")
	public static String MakeXMLFromHashMap(HashMap hm) throws XmlException {

		Set chaves = hm.keySet();
		Collection valores = hm.values();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < hm.size(); i++) {
			chaves = hm.keySet();
			Object[] objCh = chaves.toArray();
			Object[] objVl = valores.toArray();
			sb.append("<").append((String) objCh[i]).append(">").append((String) objVl[i]).append("</").append((String) objCh[i]).append(">");
		}
		return sb.toString();
	}

	public static String MakeXmlIn(String user, String service, String xmlIn) throws XmlException {

		log.debug("XmlManager:MakeXmlIn("+user+", "+service+", "+xmlIn+")");
		if ((service == null) || (service.trim().length() == 0)) {
			throw new XmlException("Serviço não informado");
		}

		if ((user == null) || (user.trim().length() == 0)) {
			throw new XmlException("Usuário não informado");
		}

		try {
			Integer.valueOf(user);
		} catch (NumberFormatException e) {
			throw new XmlException("Usuário deve ser numérico", e);
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		buffer.append("<msg>");
		buffer.append("<msgHdr>");
		buffer.append("<user>").append(user).append("</user>");
		buffer.append("<service>").append(service).append("</service>");
		buffer.append("</msgHdr>");
		buffer.append("<msgBody>");
		buffer.append(xmlIn);
		buffer.append("</msgBody>");
		buffer.append("</msg>");

		return (buffer.toString());
	}

}
