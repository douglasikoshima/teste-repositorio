package br.com.vivo.fo.xml;

import java.io.Serializable;
import org.apache.xmlbeans.XmlException;

public class XmlIn implements Serializable {

    private static final long serialVersionUID = -889734997981264554L;

    private String user;
    private String service;
    private String body;

    public XmlIn(String user, String service, String body) throws XmlException {
        if ((service == null) || (service.trim().length() == 0)) {
            throw new XmlException("Servico nao informado");
        }

        if ((user == null) || (user.trim().length() == 0)) {
            throw new XmlException("Usuario nao informado");
        }

        try {
            Integer.valueOf(user);
        } catch (NumberFormatException e) {
            throw new XmlException("Usuario deve ser numerico", e);
        }

        this.user = user;
        this.service = service;
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public String getService() {
        return service;
    }

    public String getBody() {
        return body;
    }

    public String toXML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        buffer.append("<msg>");
        buffer.append("<msgHdr>");
        buffer.append("<user>" + user + "</user>");
        buffer.append("<service>" + service + "</service>");
        buffer.append("</msgHdr>");
        buffer.append("<msgBody>");
        buffer.append(body);
        buffer.append("</msgBody>");
        buffer.append("</msg>");

        return (buffer.toString());
    }
}
