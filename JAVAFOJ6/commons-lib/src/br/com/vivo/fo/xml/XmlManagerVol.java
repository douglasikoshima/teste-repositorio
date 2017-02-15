package br.com.vivo.fo.xml;

import br.com.vivo.fo.log.Logger;
import noNamespace.MsgDocument;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import noNamespace.MsgHeaderVO;
import noNamespace.MsgDocument.Msg;
import org.apache.xmlbeans.XmlException;

public class XmlManagerVol implements Serializable {

    private static final long serialVersionUID = -6809100565766175289L;
    private static Logger log = new Logger("start");

    /*    public String xmlInput(String serviceName, String user, String xmlIn) throws XmlException {
    Msg input = Msg.Factory.newInstance();

    //Cria o header
    MsgHdr header = MsgHdr.Factory.newInstance();
    header.setService(serviceName);
    header.setUser(user);

    //Cria o body
    MsgBody body = MsgBody.Factory.newInstance();
    body.set(XmlObject.Factory.parse(xmlIn));

    //Seta Header e Body
    input.setMsgHdr(header);
    input.setMsgBody(body);

    return input.xmlText().replaceAll("vo:","");
    } */
    /**
     * @param user Usuario logado que está efetuando a chamada
     * @param service Servico Tuxedo a ser invocado
     * @param xmlIn Parametrizacao do servido em formato XML
     * @return String XML contendo o Xml formatado para execução do servico Tuxedo
     */
    public String xmlInput(String service, String user, String xmlIn) {
        //Monta o log da operação se possível
        log.debug("XmlManager:MakeXmlIn(" + user + ", " + service + ", " + xmlIn + ")");

        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        buffer.append("<msg>");
        buffer.append("<msgHdr>");
        buffer.append("<user>" + user + "</user>");
        buffer.append("<service>" + service + "</service>");
        buffer.append("</msgHdr>");
        buffer.append("<msgBody>");
        buffer.append(xmlIn);
        buffer.append("</msgBody>");
        buffer.append("</msg>");

        return (buffer.toString());
    }

    public Object output(String xmlOut) throws XmlException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg().getMsgBody().getInnerBody();
    }

    public Object output(File xmlOut) throws XmlException, IOException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        //MsgBodyVO msgb = doc.getMsg().getMsgBody();
        return doc.getMsg().getMsgBody().getInnerBody();
    }

    public MsgHeaderVO outputHeader(String xmlOut) throws XmlException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg().getMsgHdr();
    }

    public MsgHeaderVO outputHeader(File xmlOut) throws XmlException, IOException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg().getMsgHdr();
    }

    public Msg outputMsg(String xmlOut) throws XmlException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg();
    }

    public Msg outputMsg(File xmlOut) throws XmlException, IOException {
        MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
        return doc.getMsg();
    }
}
