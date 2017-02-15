package br.com.indrasistemas.framework.foundation.servicegateway.tuxedo.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weblogic.wtc.gwt.TuxedoConnectionFactory;
import br.com.indrasistemas.framework.foundation.config.ConfigException;
import br.com.indrasistemas.framework.foundation.config.ConfigManager;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;

/**
 * Service locator exclusivo para obtenção da Connection Factory de serviços
 * Tuxedo. É implementada através de um Singleton.
 * 
 * @author C_LBraga
 * 
 */
class TuxedoServiceLocator {

	private static final Log logger = LogFactory
			.getLog(TuxedoServiceLocator.class);

	/**
	 * Singletion instance
	 */
	private static TuxedoServiceLocator _instance;

	static {
		_instance = new TuxedoServiceLocator();
	}

	/**
	 * Default contructor.
	 * 
	 * @throws ServiceLocatorException
	 *             If a exception occours
	 */
	protected TuxedoServiceLocator() {
		super();
	}

	/**
	 * Returns the singleton instance
	 * 
	 * @return TuxedoServiceLocator instance
	 */
	public static TuxedoServiceLocator getInstance() {
		return _instance;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 */
	protected Context getLocalContext() throws NamingException {
		return new InitialContext();
	}

	/**
	 * 
	 * @return
	 * @throws ConfigException
	 */
	private Properties getRemoteServerProperties() throws ConfigException {
		Properties prop = new Properties();
		ConfigManager cfgManager = ConfigManager.getInstance();
		prop.put("java.naming.security.principal", cfgManager
				.getProperty("remote.java.naming.security.principal"));
		prop.put("java.naming.security.credentials", cfgManager
				.getProperty("remote.java.naming.security.credentials"));
		prop.put("java.naming.provider.url", cfgManager
				.getProperty("remote.java.naming.provider.url"));
		prop.put("java.naming.provider.name", cfgManager
				.getProperty("remote.java.naming.provider.name"));
		prop.put("java.naming.factory.initial", cfgManager
				.getProperty("remote.java.naming.factory.initial"));
		return prop;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws ConfigException
	 */
	protected Context getRemoteContext() throws NamingException,
			ConfigException {
		return new InitialContext(getRemoteServerProperties());
	}

	/**
	 * 
	 * @param serviceName
	 * @return
	 * @throws TuxedoServiceCallerException
	 */
	public TuxedoConnectionFactory getTuxedoConnectionFactory(String serviceName)
			throws TuxedoServiceCallerException {
		TuxedoConnectionFactory result = null;

		try {
			result = (TuxedoConnectionFactory) getLocalContext().lookup(
					serviceName);
		} catch (NamingException ex) {
			logger.error("Erro ao obter TuxedoConnectionFactory", ex);
			throw new TuxedoServiceCallerException(ex);
		}
		return result;
	}
}
