package br.com.vivo.fo.commons.utils.geral;

import java.io.Serializable;
import noNamespace.MsgBodyVO;
import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class TuxedoServiceBridge implements Serializable {

    private static final long serialVersionUID = -4439468410560497691L;

    public static String getXMLRequest(String user, String service, String body) throws XmlException {


        MsgDocument msgDoc = MsgDocument.Factory.newInstance();
        MsgDocument.Msg msg = MsgDocument.Msg.Factory.newInstance();

        XmlOptions opt = new XmlOptions();
        opt.setCharacterEncoding(ConstantesCRM.SISO);


        MsgHeaderVO msgh = msg.addNewMsgHdr();
        msgh.setService(service);
        msgh.setUser(user);
        MsgBodyVO msgb = msg.addNewMsgBody();

        StringBuffer xmlIN = new StringBuffer("<tmpBody>");
        xmlIN.append(body);
        xmlIN.append("</tmpBody>");
        XmlObject tmp = XmlObject.Factory.parse(xmlIN.toString());
        XmlCursor cursor = tmp.newCursor();
        cursor.toStartDoc();
        cursor.push();
        cursor.selectPath("$this/*");

        while (cursor.toNextSelection()) {
            XmlObject xmlObject = cursor.getObject();
            msgb.set(xmlObject);
        }

        cursor.pop();
        cursor.dispose();

        msg.setMsgHdr(msgh);
        msg.setMsgBody(msgb);

        msgDoc.setMsg(msg);

        return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + msgDoc.xmlText(opt);

    }
}
