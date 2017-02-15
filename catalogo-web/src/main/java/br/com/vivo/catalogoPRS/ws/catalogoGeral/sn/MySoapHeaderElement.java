package br.com.vivo.catalogoPRS.ws.catalogoGeral.sn;
import javax.xml.soap.SOAPException;

import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;


public class MySoapHeaderElement extends SOAPHeaderElement {

	private static final long serialVersionUID = 1L;

	private MessageElement me;
	
	public MySoapHeaderElement(PrefixedQName prefixedQName) {
		super(prefixedQName);
		me = new MessageElement(prefixedQName);
	}
	
	protected void outputImpl(SerializationContext arg0) throws Exception {
		me.output(arg0);
	}
	
	public void addChild(MessageElement arg0) {
        try {
            me.addChild(arg0);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
