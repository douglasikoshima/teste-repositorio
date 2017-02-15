package br.com.vivo.fo.atmi;

import javax.ejb.Local;

import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;

@Local
public interface JATMI {
	
	public String executeCommnad(String method, String input) throws TPException, TPReplyException;
}
