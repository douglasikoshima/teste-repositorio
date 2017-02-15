package br.com.vivo.fo.commons.utils.properties;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

//@SuppressWarnings("deprecation")
public class LoadProperties {

	private Properties prop = null;

	public LoadProperties(HttpServletRequest request) throws IOException {
		try {
			prop = new Properties();
			//prop.load(new FileInputStream(request.getRealPath("/WEB-INF/common.properties")));
			if(Thread.currentThread().getContextClassLoader().getResource("/common.properties")!=null)
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/common.properties"));
			else
				throw new IOException();
			
		} catch (IOException e) {
			throw new IOException("Arquivo de Properties não foi encontrado.");
		}
	}

	public Properties getProperties() {
		return prop;
	}

	public String get(String key) throws Exception {
		String value = (String) prop.get(key);
		if (value == null || value.equals(""))
			throw new Exception("Não foi encontrado nenhum valor para a chave: " + key);

		return value;
	}
}
