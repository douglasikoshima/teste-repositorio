package br.com.vivo.fo.atmi;

import javax.ejb.Local;

@Local
public interface TuxedoServiceCall {

	public String callService(String serviceName, String data) throws TuxedoServiceCallerException;

}