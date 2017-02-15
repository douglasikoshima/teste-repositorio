package br.vivo.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLUtil
{    
	private static Logger logger = null;
	
    private String _url = "";
    private String _user = "";
    private String _password = "";
    
    private Connection _conn = null;
    private PreparedStatement _stmt = null;
    private String _sql = "";
    
    private int _usersNumber = 0;
    private int _usersErrNumber = 0;
    
    public SQLUtil(String server, Integer port, String dbname, String user, String password, Logger log)
    {
    	SQLUtil.logger = log;
        _url = "jdbc:oracle:thin:@".concat(server).concat(":").concat(port.toString()).concat(":").concat(dbname);
        _user = user;
        _password = password;
    }
    
    public int testarConexao()
    {
    	logger.log(Level.SEVERE, "Dados p/ conexão [{0}] [{1}] [{2}]", new Object[] {_url, _user, _password});
    	
    	int result = 0;
    	
    	result += startConnection();
    	result += endConnection();
    	
    	return result;
    }
    
    public int mudarProcesso(int cdprocesso, String fileName)
    {
    	logger.log(Level.INFO, ">>>mudarProcesso");
    	
        logger.log(Level.INFO, "Alterando STATUS_CARGA para [{0}] onde NOME_ARQUIVO = [{1}]", new Object[] {cdprocesso, fileName});

        startConnection();
        
        _sql = "UPDATE ACESSO.MANUTENCAOLOGINACESSO SET STATUS_CARGA = ? WHERE UPPER(TRIM(NOME_ARQUIVO)) = UPPER(TRIM(?))";
        
        try 
        {
        	_stmt = _conn.prepareStatement(_sql);
            _stmt.setInt(1, cdprocesso);
            _stmt.setString(2, fileName);
			_stmt.execute();
		} 
        catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao mudar Processo!");
        	logger.log(Level.SEVERE, "MUDARPROCESSO > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<mudarProcesso");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao mudar Processo!");
            logger.log(Level.SEVERE, "MUDARPROCESSO > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<mudarProcesso");
            return -1;
		}
        
        endConnection();
        
        logger.log(Level.INFO, "<<<mudarProcesso");
        
        return 0;
    }
    
    public int atualizarErr(int sqUsuario, String dsErro)
    {
    	logger.log(Level.INFO, ">>>atualizarErr");
    	
    	logger.log(Level.INFO, "Alterando LOG_ERRO para [{0}] onde SQUSUARIO = [{1}]", new Object[] {dsErro, sqUsuario});
    	
    	startConnection();
    	
    	_sql = "UPDATE LOAD.TMP_USUARIO SET LOG_ERRO = ? WHERE SQUSUARIO = ?";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	        _stmt.setString(1, dsErro);
	        _stmt.setInt(2, sqUsuario);
	        _stmt.execute();
    	} 
        catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao atualizar Erro na tabela!");
        	logger.log(Level.SEVERE, "ATUALIZARERR > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<atualizarErr");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao atualizar Erro na tabela!");
            logger.log(Level.SEVERE, "ATUALIZARERR > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<atualizarErr");
            return -1;
		}
        
    	endConnection();
    	
    	logger.log(Level.INFO, "<<<atualizarErr");
    	
    	return 0;
    }
    
    public int atualizarArquivoErr(String fileErr, String fileName)
    {
    	logger.log(Level.INFO, ">>>atualizarArquivoErr");
    	
    	logger.log(Level.INFO, "Alterando ARQUIVO_ERR para [{0}] onde NOME_ARQUIVO = [{1}]", new Object[] {fileErr, fileName});
    	
    	startConnection();
    	
    	_sql = "UPDATE ACESSO.MANUTENCAOLOGINACESSO SET ARQUIVO_ERR = ? WHERE UPPER(TRIM(NOME_ARQUIVO)) = UPPER(TRIM(?))";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	        _stmt.setString(1, fileErr);
	        _stmt.setString(2, fileName);
	        _stmt.execute();
    	} 
        catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao atualizar arquivo de erro na tabela!");
        	logger.log(Level.SEVERE, "ATUALIZARARQUIVOERR > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<atualizarArquivoErr");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao atualizar arquivo de erro na tabela!");
            logger.log(Level.SEVERE, "ATUALIZARARQUIVOERR > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<atualizarArquivoErr");
            return -1;
		}
        
    	endConnection();
    	
    	logger.log(Level.INFO, "<<<atualizarArquivoErr");
    	
    	return 0;
    }
    
    public String[] getUser(int sqUsuario)
    {
    	logger.log(Level.INFO, ">>>getUser");
    	
    	String[] usuario = new String[3];
    	
    	startConnection();
    	
    	_sql = "SELECT LOGIN, DESBLOQUEIO, REINICIALIZACAO FROM LOAD.TMP_USUARIO WHERE SQUSUARIO = ?";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	    	_stmt.setInt(1, sqUsuario);
	    	
	    	ResultSet rs = _stmt.executeQuery();
	
	    	if(rs.next())
	    	{
	    		usuario[0] = rs.getString(1);
	    		usuario[1] = rs.getString(2);
	    		usuario[2] = rs.getString(3);
	    	}
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao selecionar usuário!");
        	logger.log(Level.SEVERE, "GETUSER > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<getUser");
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao selecionar usuário!");
            logger.log(Level.SEVERE, "GETUSER > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<getUser");
		}
    	
    	endConnection();
    	
    	logger.log(Level.INFO, "<<<getUser");
    	
    	return usuario;
    }
    
    public int getLoginID(String login)
    {
    	logger.log(Level.INFO, ">>>getLoginID");
    	
    	int id = -1;
    	
    	startConnection();
    	
    	_sql = "SELECT IDPESSOAUSUARIO FROM ACESSO.USUARIO WHERE UPPER(NMLOGINUSUARIO) = TRIM(?) AND ROWNUM < 2";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	    	_stmt.setString(1, login);
	    	
	    	ResultSet rs = _stmt.executeQuery();
	    	
	    	if(rs.next()) 
	    		id = rs.getInt(1);
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao selecionar id do usuário!");
        	logger.log(Level.SEVERE, "GETLOGINID > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<getLoginID");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao selecionar id do usuário!");
            logger.log(Level.SEVERE, "GETLOGINID > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<getLoginID");
            return -1;
		}
        
        endConnection();
    	
    	logger.log(Level.INFO, "<<<getLoginID");
    	
    	return id;
    }
    
    public String getLogError(int rn)
    {
    	logger.log(Level.INFO, ">>>getLogError");
    	
    	String usuario = "";
    	
    	startConnection();
		
		_sql =  "SELECT SQUSUARIO       || '|' || 	" +
				"		LOGIN           || '|' || 	" +
				"		DESBLOQUEIO     || '|' || 	" +
				"		REINICIALIZACAO || '|' || 	" +
				"		LOG_ERRO                  	" +
				"FROM                               " +
				"(                                  " +
				"		SELECT  US.*, ROWNUM RN     " +
				"		FROM    LOAD.TMP_USUARIO US	" +
				"		WHERE   LOG_ERRO IS NOT NULL" +
				")									" +
				"WHERE RN = ?						";
		
		try
		{
			_stmt = _conn.prepareStatement(_sql);
			_stmt.setInt(1, rn);
			 
			ResultSet rs = _stmt.executeQuery();
			
			if(rs.next())
				usuario = rs.getString(1);
			
			logger.log(Level.INFO, "Linha de Erro retornada! [{0}]", new Object[] {usuario});
		}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao selecionar linha da tabela de usuário!");
        	logger.log(Level.SEVERE, "GETLOGINERROR > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<getLogError");
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao selecionar linha da tabela de usuário!");
            logger.log(Level.SEVERE, "GETLOGINERROR > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<getLogError");
		}
        
		endConnection();
		
		logger.log(Level.INFO, "<<<getLogError");
		
		return usuario;
    }
    
    public int countUsers()
    {
    	logger.log(Level.INFO, ">>>countUsers");
		
    	startConnection();
    	
    	_sql = "SELECT COUNT(1) FROM LOAD.TMP_USUARIO";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	        ResultSet rs = _stmt.executeQuery();
	        
	        if(rs.next()) 
	        	_usersNumber = rs.getInt(1);
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao contar usuários!");
        	logger.log(Level.SEVERE, "COUNTUSERS > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<countUsers");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao contar usuários!");
            logger.log(Level.SEVERE, "COUNTUSERS > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<countUsers");
            return -1;
		}
        
        endConnection();
        
        logger.log(Level.INFO, "<<<countUsers");
        return _usersNumber;
    }
    
    public int countUsersErr()
    {
    	logger.log(Level.INFO, ">>>countUsersErr");
		
    	startConnection();
    	
    	_sql = "SELECT COUNT(1) FROM LOAD.TMP_USUARIO WHERE LOG_ERRO IS NOT NULL";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	        ResultSet rs = _stmt.executeQuery();
	        
	        if(rs.next()) 
	        	_usersErrNumber = rs.getInt(1);
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao contar usuários com erro!");
        	logger.log(Level.SEVERE, "COUNTUSERSERR > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<countUsersErr");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao contar usuários com erro!");
            logger.log(Level.SEVERE, "COUNTUSERSERR > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<countUsersErr");
            return -1;
		}
        
        endConnection();
        
        logger.log(Level.INFO, "<<<countUsersErr");
        
        return _usersErrNumber;
    }
    
    public int finalizaTabela(String dtProc, String fileName)
    {
    	logger.log(Level.INFO, ">>>finalizaTabela");
    	
    	countUsersErr();
		countUsers();
    	
    	startConnection();
    	
    	_sql = 	" UPDATE ACESSO.MANUTENCAOLOGINACESSO                           " +
    			" SET	 DATA_PROC	         = TO_DATE(?, 'YYYYMMDD_HH24MISS'), " +
                "        QTDE_REGISTROS_ERRO = ?,                               " +
                "        QTDE_REGISTROS	     = ?                                " +
    			" WHERE  UPPER(TRIM(NOME_ARQUIVO)) = UPPER(TRIM(?))";
    	
    	try
    	{
	    	_stmt = _conn.prepareStatement(_sql);
	    	_stmt.setString(1, dtProc);
	    	_stmt.setInt(2, _usersErrNumber);
	    	_stmt.setInt(3, _usersNumber);
	    	_stmt.setString(4, fileName);
	    	_stmt.execute();
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao finalizar tabela!");
        	logger.log(Level.SEVERE, "FINALIZATABELA > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	logger.log(Level.INFO, "<<<finalizaTabela");
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao finalizar tabela!");
            logger.log(Level.SEVERE, "FINALIZATABELA > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            logger.log(Level.INFO, "<<<finalizaTabela");
            return -1;
		}
        
    	endConnection();
    	
    	logger.log(Level.INFO, "<<<finalizaTabela");
    	
    	return 0;
    }
    
    public void apagarLog()
    {
    	logger.log(Level.INFO, ">>>apagarLog");
    	try
    	{
    		startConnection();
   
    		_sql =  " UPDATE ACESSO.MANUTENCAOLOGINACESSO 	" + 
    				" SET ARQUIVO_ERR = '<EXPIRADO>' 		" + 
    				" WHERE ARQUIVO_ERR IS NOT NULL 		" + 
    				" AND DATA_PROC < SYSDATE - (SELECT DSVALORPARAMETRO 	" +
    				"							 FROM APOIO.PARAMETRO 		" +
    				"							 WHERE CDPARAMETRO = 'TEMPO_MANUT_LOGIN_ACESSO')";
    		
    		_stmt = _conn.prepareStatement(_sql);
    		
    		logger.log(Level.INFO, "Logs antigos apagados? [{0}]", new Object[] {_stmt.execute()});
    		
    		endConnection();
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão com Banco de Dados!");
        	logger.log(Level.SEVERE, "STARTCONNECTION > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão com Banco de Dados!");
            logger.log(Level.SEVERE, "STARTCONNECTION > ERRO -> errmc={0}", new Object[] {e.getMessage()});
		}
        logger.log(Level.INFO, "<<<apagarLog");
    }
    
    private int startConnection()
    {
        try 
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			_conn = DriverManager.getConnection(_url, _user, _password);
		}  
        catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão com Banco de Dados!");
        	logger.log(Level.SEVERE, "STARTCONNECTION > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao iniciar conexão com Banco de Dados!");
            logger.log(Level.SEVERE, "STARTCONNECTION > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            return -1;
		}
        
        return 0;
    }
    
    private int endConnection()
    {
    	try
    	{
	        if(_stmt != null) _stmt.close();
	        if(!_conn.isClosed()) _conn.close();
    	}
    	catch (SQLException e) 
		{
        	logger.log(Level.SEVERE, "Erro ao finalizar conexão com Banco de Dados!");
        	logger.log(Level.SEVERE, "ENDCONNECTION > ERRO ORACLE -> sqlcode={0},sqlerrmc={1}", new Object[] {e.getErrorCode(), e.getMessage()});
        	return -1;
		}
        catch (Exception e) 
        {
        	logger.log(Level.SEVERE, "Erro ao finalizar conexão com Banco de Dados!");
            logger.log(Level.SEVERE, "ENDCONNECTION > ERRO -> errmc={0}", new Object[] {e.getMessage()});
            return -1;
		}
        return 0;
    }
}