package br.com.vivo.fo.atmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import weblogic.wtc.gwt.TuxedoConnectionFactory;

class TuxedoServiceLocator {

    private static TuxedoServiceLocator _instance;

    static {
        _instance = new TuxedoServiceLocator();
    }

    protected TuxedoServiceLocator() {
        super();
    }

    public static TuxedoServiceLocator getInstance() {
        return _instance;
    }

    protected Context getContext() throws NamingException {
        return new InitialContext();
    }

    public TuxedoConnectionFactory getTuxedoConnectionFactory(String serviceName) throws TuxedoServiceCallerException {
        TuxedoConnectionFactory result = null;
        try {
            result = (TuxedoConnectionFactory) getContext().lookup(serviceName);
        
        } catch (NamingException ex) {
            throw new TuxedoServiceCallerException(ex);
        }
        return result;
    }
}
