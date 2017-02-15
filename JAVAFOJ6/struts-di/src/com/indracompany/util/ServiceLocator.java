package com.indracompany.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	@SuppressWarnings("unchecked")
	public synchronized static <T> T getBean(Class<T> cls) throws NamingException {
		String name = "java:comp/env/ejb/"+cls.getName().substring(cls.getName().lastIndexOf(".")+1);
		T bean = (T) jndiLookup(name);
		return bean;
	}

	public synchronized static Object getResource(String name) throws NamingException {
		return jndiLookup(name);
	}

	private synchronized static Object jndiLookup(String name) throws NamingException {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		InitialContext ctx = new InitialContext();
		return ctx.lookup(name);
	}
}