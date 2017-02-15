/**
 * 
 */
package br.com.indrasistemas.vivoservices.webservice.to;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;

/**
 * @author a5013566
 * 
 */
public class RequestInfoWSTO extends RequestInfoTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1268325053454740376L;

	/**
	 * 
	 */
	public RequestInfoWSTO() {

	}

	/**
	 * @param ip
	 * @param userName
	 * @param systemModule
	 * 
	 */
	public RequestInfoWSTO(String ip, String userName, String systemModule) {
		// ip, userName, callId, systemModule, userId
		super(ip, userName, null, systemModule, null);

	}

}
