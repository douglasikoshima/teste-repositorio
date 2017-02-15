package br.vivo.manutencao;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.vivo.util.BatchFormatter;
import br.vivo.util.LdapUtil;
import br.vivo.util.SQLUtil;
import br.vivo.util.TParamConf;

public class manutencaoLogin
{
    private static final Logger logger = Logger.getLogger(manutencaoLogin.class.getName());
    
    private TParamConf tParamConf = null;
    private LdapUtil   ldap	     = null;	
    private SQLUtil    sql		 = null;
    
    private static String _nomeArquivoRegistro = "";
    
    public static void main(String[] args)
    {
    	logger.log(Level.INFO, ">>>manutenção Login");
        try 
        {
        	logger.setLevel(Level.INFO);
        	for(Handler h : logger.getHandlers())
        	{
        		logger.removeHandler(h);
        	}
        	Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-kkmmss");
			FileHandler fh = new FileHandler("../log/".concat(sdf.format(now)).concat(".log"));
			fh.setFormatter(new BatchFormatter());
			logger.addHandler(fh);
			
	        manutencaoLogin m = new manutencaoLogin();
	        if(m.startProcess() == -1)
	        {
	            logger.log(Level.INFO, "Processo não Finalizado!");
	        }
		}
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        logger.log(Level.INFO, "<<<manutenção Login");
    }
    
    private int startProcess()
    {
    	tParamConf = new TParamConf(logger);
    	ldap       = new LdapUtil(tParamConf.getServer_ldap(), tParamConf.getPort_ldap(), tParamConf.getOrg_ldap(), tParamConf.getUnit_ldap(), tParamConf.getMasterDn(), tParamConf.getPwd_ldap(), logger);
    	sql        = new SQLUtil(tParamConf.getServer_db(), tParamConf.getPort_db(), tParamConf.getInst_db(), tParamConf.getUsr_db(), tParamConf.getPwd_db(), logger);
    	
        /****************************************************************************************************************/
        logger.log(Level.INFO, "Obtendo parâmetros de configuração...");
        try 
        {
            tParamConf.getClass().getName();
            logger.log(Level.INFO, "Parâmetros obtidos com sucesso!");
        } 
        catch (Exception ex)
        {
        	logger.log(Level.SEVERE, "Erro obtendo parametros de configuracao!");
            logger.log(Level.SEVERE, "MAIN > ERRO ORACLE -> errmsg={0}", new Object[] {ex.getMessage()});
            return -1;
        }
        
        /****************************************************************************************************************/
        logger.log(Level.INFO, "Testando conexão DB...");
        
        if(sql.testarConexao() < 0) 
        {
        	logger.log(Level.SEVERE, "Erro conectando no Banco de Dados!");
        	return -1;
        }
        
        logger.log(Level.INFO, "Conecado com Sucesso a DB!");
        
        /****************************************************************************************************************/
        logger.log(Level.INFO, "Testando conexão LDAP...");
        
        if(ldap.testarConexao() < 0) 
        {
        	logger.log(Level.SEVERE, "Erro conectando no Servidor LDAP!");
        	return -1;
        }
        logger.log(Level.INFO, "Conecado com Sucesso em LDAP!");
        
        /****************************************************************************************************************/
        
        apagarLogErr();
        
        File directory = new File(tParamConf.getData_path());
        String[] files = directory.list(new TxtFileFilter());
        
        for(String f : files)
        {
        	logger.log(Level.INFO, "Arquivos obtidos... [{0}]", new Object[] {f});
        }
        
        if(files.length == 0)
        {
            logger.log(Level.INFO, "Não há documentos para serem processados!");
            return -1;
        }
        
        for(String f : files)
        {
        	_nomeArquivoRegistro = f;
        	String sqlldr = "";
            
            sqlldr = "sqlldr <USER>/<PASSWD>@<DBNAME> DATA=<DATAFILE> CONTROL=./control.ctl ERRORS=999999999";
            sqlldr = sqlldr.replaceAll("<USER>", tParamConf.getUsr_db());
            sqlldr = sqlldr.replaceAll("<PASSWD>", tParamConf.getPwd_db());
            sqlldr = sqlldr.replaceAll("<DBNAME>", tParamConf.getInst_db());
            sqlldr = sqlldr.replaceAll("<DATAFILE>", tParamConf.getData_path().concat(_nomeArquivoRegistro));
            
            logger.log(Level.INFO, "Comando [{0}]", new Object[] {sqlldr});
            
            try 
            {
                Process p = Runtime.getRuntime().exec(sqlldr);
                p.waitFor();
                File errorsDirectory = new File(tParamConf.getError_path());
                String[] fileErr = errorsDirectory.list(new BadFileFilter());

                logger.log(Level.INFO, "Arquivos erros encontrados? QTDE = {0}", new Object[] {fileErr.length});
                
                if(fileErr.length > 0)
                {
                	logger.log(Level.SEVERE, "Arquivo .BAD encontrado!");
                    sql.mudarProcesso(2, _nomeArquivoRegistro);
                    registraErro(-1, "ERRO LAYOUT");
                }
                else
                {
                	logger.log(Level.INFO, "Erros de Layout não detectados!");
                	Boolean errorFound = Boolean.FALSE;
                	
                	for(int i = 1; i <= sql.countUsers(); i++)
                	{
                		try
                		{
	                		logger.log(Level.INFO, ">>>Validação dos dados recebidos");
	                	
	                		String[] tmp_usuario = sql.getUser(i);
	                		
	                		logger.log(Level.INFO, "Dados de Usuário [{0}] [{1}] [{2}]", tmp_usuario);
	                		                		
	                		if("".equals(tmp_usuario[0]) || ldap.validarUsuario(tmp_usuario[0]) < 0)
	                		{
	                			errorFound = Boolean.TRUE;
	                			registraErro(i, "LOGIN NAO ENCONTRADO");
	                			
	                			logger.log(Level.INFO, "<<<Validação dos dados recebidos");
	                		}
	                		else
	                		{
	                			if(!"S".equals(tmp_usuario[1]) && !"N".equals(tmp_usuario[1]))
	                			{
	                				errorFound = Boolean.TRUE;
	                    			registraErro(i, "OPERACAO INVALIDA DE DESBLOQUEIRO");
	                    			logger.log(Level.INFO, "<<<Validação dos dados recebidos");
	                			}
	                			else
	                			{
	                				if(!"S".equals(tmp_usuario[2]) && !"N".equals(tmp_usuario[2]))
	                    			{
	                    				errorFound = Boolean.TRUE;
	                        			registraErro(i, "OPERACAO INVALIDA DE REINICIALIZACAO");
	                        			logger.log(Level.INFO, "<<<Validação dos dados recebidos");
	                    			}
	                				else
	                				{
	                					logger.log(Level.INFO, "<<<Validação dos dados recebidos");         		
	
	                        			logger.log(Level.INFO, ">>>Execucao dos Processos");
	                        			
	                            		if("S".equals(tmp_usuario[1]))
	                        			{
	                            			logger.log(Level.INFO, "Operação de Desbloqueio de Usuário!");
	                        				ldap.desbloqueiaUsuario(tmp_usuario[0]);
	                        			}
	                        			
	                        			if("S".equals(tmp_usuario[2]))
	                        			{
	                        				logger.log(Level.INFO, "Operação de Reinicialização de Senha!");
	                        				ldap.reinicializaSenha(tmp_usuario[0]);
	                        			}
	                        			
	                        			logger.log(Level.INFO, "Operações Concluídas!");
	                        			
	                            		logger.log(Level.INFO, "<<<Execucao dos Processos");
	                				}
	                			}
	                		}
	                	}
	                	catch (Exception e) 
	                	{
	                		logger.log(Level.SEVERE, "Erro durante execução do processo!");
	                		logger.log(Level.SEVERE, "MAIN > ERRO ORACLE -> errmsg={0}", new Object[] {e.getMessage()});
	                		logger.log(Level.INFO, "<<<Execucao dos Processos");
	                		errorFound = Boolean.TRUE;
	                		registraErro(i, "OPERACAO NAO PODE SER COMPLETADA");
						}
                	}
                	
                	if(errorFound)
                	{
                		sql.mudarProcesso(3, _nomeArquivoRegistro);
                		registraErro(0, "FINALIZA ERROS");
                	}
                	else
                	{
                		sql.mudarProcesso(4, _nomeArquivoRegistro);
                	}
            	
                	finalizarArquivo();
                }
            } 
            catch (Exception ex) 
            {
            	logger.log(Level.SEVERE, "Erro geral!");
            	logger.log(Level.SEVERE, "MAIN > ERRO ORACLE -> errmsg={0}", new Object[] {ex.getMessage()});
            	return -1;
            }
        }
        return 0;
    }
    
    private void apagarLogErr()
    {
    	sql.apagarLog();
    }
    
    private void registraErro(int sqAcesso, String dsErro) throws IOException, InterruptedException, SQLException, ClassNotFoundException
    {
    	logger.log(Level.INFO, ">>>registraErro");
    	
    	String cmd = "";
    	Process p = null;
    	
    	if(sqAcesso == -1)
    	{
    		Date now = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_kkmmss");
	        
	        String fileErr = _nomeArquivoRegistro.substring(0, _nomeArquivoRegistro.length() - 4).concat("_").concat(sdf.format(now)).concat(".err");
    		
    		cmd = "mv".concat(" ").concat(tParamConf.getError_path().concat("usuariosRejeitados.bad")).concat(" ").concat(tParamConf.getError_path().concat(fileErr));
    		logger.log(Level.INFO, "Comando de renomeação de arquivo [{0}]", new Object[] {cmd});
	        p = Runtime.getRuntime().exec(cmd);
	        p.waitFor();
	        
	        sql.atualizarArquivoErr(fileErr, _nomeArquivoRegistro);
	        
	        cmd = "rm".concat(" ").concat(tParamConf.getError_path().concat("usuariosRejeitados.bad"));
	        logger.log(Level.INFO, "Comando de remoção de arquivo [{0}]", new Object[] {cmd});
	        p = Runtime.getRuntime().exec(cmd);
	        p.waitFor();
	        
	        logger.log(Level.INFO, "<<<registraErro");
    	}
    	else if(sqAcesso == 0)
    	{
    		Date now = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_kkmmss");
	        
	        String fileErr = _nomeArquivoRegistro.substring(0, _nomeArquivoRegistro.length() - 4).concat("_").concat(sdf.format(now)).concat(".err");
    		
	        logger.log(Level.INFO, "Criando arquivo de Erro... [{0}]", new Object[] {tParamConf.getError_path().concat(fileErr)});
	        
	        FileWriter fw = new FileWriter(tParamConf.getError_path().concat(fileErr));
	        logger.log(Level.INFO, "Quantidade de usuários com erro [{0}]", new Object[] {sql.countUsersErr()});
        	for(int row = 1; row <= sql.countUsersErr(); row++)
        	{
        		String s = "";
        		s = sql.getLogError(row);
        		
        		logger.log(Level.INFO, "Linha de Erro Retornada [{0}] [{1}]", new Object[] {row, s});
        		
        		fw.write(s);
	        	fw.write("\r\n");
        	}
        	fw.close();
        	
        	logger.log(Level.INFO, "Arquivo de Erro criado.");
        	
        	sql.atualizarArquivoErr(fileErr, _nomeArquivoRegistro);
        	
        	logger.log(Level.INFO, "<<<registraErro");
    	}
    	else if(sqAcesso > 0)
    	{
    		logger.log(Level.INFO, "Registrado erro em SQUSUARIO = [{0}], MSG = [{1}]", new Object[] {sqAcesso, dsErro});
    		
    		sql.atualizarErr(sqAcesso, dsErro);
    		
    		logger.log(Level.INFO, "<<<registraErro");
    	}
    	
    	logger.log(Level.INFO, "<<<registraErro");
    }
    
    private void finalizarArquivo() throws SQLException, ClassNotFoundException, IOException, InterruptedException
    {
    	logger.log(Level.INFO, ">>>finalizarArquivo");
    	
    	Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_kkmmss");
    	
    	String filePathNew = _nomeArquivoRegistro.substring(0, _nomeArquivoRegistro.length() - 4).concat("_").concat(sdf.format(now)).concat(".prc");
        
        sql.finalizaTabela(sdf.format(now), _nomeArquivoRegistro);
        
        String cmd = "mv".concat(" ").concat(tParamConf.getData_path().concat(_nomeArquivoRegistro)).concat(" ").concat(tParamConf.getData_path().concat(filePathNew));
        logger.log(Level.INFO, "Comando de renomeação de arquivo [{0}]", new Object[] {cmd});
        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
    	
    	logger.log(Level.INFO, "<<<finalizarArquivo");
    }
}

class TxtFileFilter implements FilenameFilter
{	
	public boolean accept(File dir, String name) 
	{
		if (name != null && name.toLowerCase().endsWith(".txt"))
        {
            return true;
        }
        else
        {
            return false;
        }
	}
}

class BadFileFilter implements FilenameFilter
{	
	public boolean accept(File dir, String name) 
	{
		if (name != null && name.toLowerCase().endsWith(".bad"))
        {
            return true;
        }
        else
        {
            return false;
        }
	}
}