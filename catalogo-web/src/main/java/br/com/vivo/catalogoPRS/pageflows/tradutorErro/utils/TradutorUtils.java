package br.com.vivo.catalogoPRS.pageflows.tradutorErro.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;

public class TradutorUtils {

	
	public static DataSource getDataSource() {
		Context ctx = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(Context.INITIAL_CONTEXT_FACTORY, ApplicationConfiguration.getTradutorDatasourceContextFactory());
		ht.put(Context.PROVIDER_URL, ApplicationConfiguration.getTradutorDatasourceURL());
		DataSource ds = null;

		try {
			ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup(ApplicationConfiguration.getTradutorDatasourceJndiName());
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
