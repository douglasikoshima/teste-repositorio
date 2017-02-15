package br.com.vivo.catalogoPRS.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;

public class VivoNetUtils {
	
	public static DataSource getDataSource() {
		Context ctx = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(Context.INITIAL_CONTEXT_FACTORY, ApplicationConfiguration.getVivoNetDatasourceContextFactory());
		ht.put(Context.PROVIDER_URL, ApplicationConfiguration.getVivoNetDatasourceURL());
		DataSource ds = null;

		try {
			ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup(ApplicationConfiguration.getVivoNetDatasourceJndiName());
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
