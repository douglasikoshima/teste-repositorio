package br.com.vivo.catalogoPRS.ws.catalogoGeral.sn;

import org.apache.axis.message.MessageElement;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;

public class SecurityHeader implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public SecurityHeader() {
		  }	
	
	public SOAPHeaderElement getSecurityHeader(String user,String password) {
		SOAPHeaderElement securityHeader = null;
		try {
			securityHeader = new MySoapHeaderElement(
					new PrefixedQName(
							"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
							"Security", "wsse"));
			securityHeader.setActor(null);

			// UsernameToken
			MessageElement usernameToken = new MessageElement(
					new javax.xml.namespace.QName(
							"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
							">Security>UsernameToken"));
			// usuario
			MessageElement username = new MessageElement(
					new javax.xml.namespace.QName(
							"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
							">UsernameToken>Username"));
			username.setObjectValue(user);

			// pass
			MessageElement passwordElement = new MessageElement(
					new javax.xml.namespace.QName(
							"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
							">UsernameToken>Password"));
			passwordElement.setObjectValue(password);
			
			usernameToken.addChild(username);
			usernameToken.addChild(passwordElement);
			securityHeader.addChild(usernameToken);
			
		} catch (Exception e) {

		}

		return securityHeader;
	} 

}
