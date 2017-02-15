package br.com.vivo.fo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Local;

@Local
public interface DataBaseCall {

	public Connection getConnection();
	
    public ResultSet executeQuery(String query) throws SQLException;

    public int executeUpdate(String query) throws SQLException;

    public void release();
}
