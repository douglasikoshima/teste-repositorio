package br.vivo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TParamConf 
{
	private static Logger logger = null;
	
	protected String server_db = "10.128.192.47";
	protected int port_db = 1523;
	protected String usr_db = "";
	protected String pwd_db = "";
	protected String inst_db = "FOHI1";
	protected String server_ldap = "10.128.5.72";
	protected int port_ldap = 3389;
	protected String usr_ldap = "";
	protected String pwd_ldap = "feyman0402";
	protected String org_ldap = "vivoint";
	protected String unit_ldap = "People";
	protected String masterDn = "cn=Directory Manager";
	protected String data_path = "../data/";
	protected String error_path = "../error/";
	protected String log_path = "../log/";
	
	public TParamConf(Logger log) {
		
		logger = log;
		
		Properties prop = new Properties();
		try 
		{
			prop.load(new FileInputStream(new File("../bin/manutencaoLogin.cfg")));
			
			server_db = prop.getProperty("server_db");
			port_db = Integer.valueOf(prop.getProperty("port_db"));
			usr_db = prop.getProperty("usr_db");
			pwd_db = prop.getProperty("pwd_db");
			inst_db = prop.getProperty("inst_db");
			server_ldap = prop.getProperty("server_ldap");
			port_ldap = Integer.valueOf(prop.getProperty("port_ldap"));
			usr_ldap = prop.getProperty("usr_ldap");
			pwd_ldap = prop.getProperty("pwd_ldap");
			org_ldap = prop.getProperty("org_ldap");
			unit_ldap = prop.getProperty("unit_ldap");
			masterDn = prop.getProperty("masterDn");
			data_path = prop.getProperty("data_path");
			error_path = prop.getProperty("error_path");
			log_path = prop.getProperty("log_path");
		} 
		catch (FileNotFoundException e) 
		{
			logger.log(Level.SEVERE, "Erro ao atualizar Erro na tabela!");
        	logger.log(Level.SEVERE, "TPARAMCONF > ERRO FILE -> errmsg={0}", new Object[] {e.getMessage()});
        	logger.log(Level.INFO, "<<<atualizarErr");
		} 
		catch (IOException e) 
		{
			logger.log(Level.SEVERE, "Erro ao atualizar Erro na tabela!");
        	logger.log(Level.SEVERE, "TPARAMCONF > ERRO FILE -> errmsg={0}", new Object[] {e.getMessage()});
        	logger.log(Level.INFO, "<<<atualizarErr");
		}
	}
	
    public String getServer_db() {
        return server_db;
    }

    public int getPort_db() {
        return port_db;
    }

    public String getUsr_db() {
        return usr_db;
    }

    public String getPwd_db() {
        return pwd_db;
    }

    public String getInst_db() {
        return inst_db;
    }

    public String getServer_ldap() {
        return server_ldap;
    }

    public int getPort_ldap() {
        return port_ldap;
    }

    public String getUsr_ldap() {
        return usr_ldap;
    }

    public String getPwd_ldap() {
        return pwd_ldap;
    }

    public String getOrg_ldap() {
        return org_ldap;
    }

    public String getUnit_ldap() {
        return unit_ldap;
    }

	public String getData_path() {
		return data_path;
	}

	public String getError_path() {
		return error_path;
	}

	public String getLog_path() {
		return log_path;
	}

	public String getMasterDn() {
		return masterDn;
	}
}
