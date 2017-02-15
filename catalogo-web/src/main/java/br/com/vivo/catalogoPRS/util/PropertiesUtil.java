package br.com.vivo.catalogoPRS.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private Properties props;   
    private String propertiesFile = "/catalogoprs_config.properties";   
  
    public PropertiesUtil() {   
        
    	props = new Properties();   
        
    	try {

        	InputStream in = this.getClass().getResourceAsStream(propertiesFile);
            props.load(in);   
            in.close();
             
        } catch (IOException e) {
        	e.printStackTrace();
        }   
    }   
  
    public String getString(String chave) {   
    	return (String)props.getProperty(chave);   
    }   
    
    public Integer getInteger(String chave) {   
        return Integer.parseInt(props.getProperty(chave));   
    }   
    
}
