package br.com.vivo.fo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

@Stateless(name = "DataBaseCall", mappedName = "DataBaseCall")
@Local(DataBaseCall.class)
@Session(ejbName = "DataBaseCall", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DataBaseCallBean implements DataBaseCall {

	@Resource(name = "jdbc.OracleDS")
	private DataSource ds;

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement st = null;

	private Logger logger = Logger.getAnonymousLogger();

	@PostConstruct
	private void setConnection() {
		try {
			conn = ds.getConnection();
			while (conn.isClosed()) {
				conn = ds.getConnection();
			}
			conn.setAutoCommit(true);
		} catch (SQLException e) {
		}
	}
	
	public Connection getConnection() {
		try {
			conn = ds.getConnection();
			while (conn.isClosed()) {
				conn = ds.getConnection();
			}
			conn.setAutoCommit(true);
		} catch (SQLException e) {
		}
		return conn;
	}

	@Override
	public void release() {
		if (rs != null) {
			try {
				rs.close();
				rs.getStatement().close();
			} catch (SQLException e) {
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
			st = null;
		}
		/*if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
			conn = null;
		}*/
	}

	public ResultSet executeQuery(String query) throws SQLException {
		if (conn == null) {
			setConnection();
		}
        logger.log(Level.INFO, "Tamanho da Query: "+query.length());
        logger.log(Level.INFO, "Query: "+query);
		st = conn.createStatement();
		return st.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		if (conn == null) {
			setConnection();
		}
		int nAffecteds = 0;
        logger.log(Level.INFO, "Tamanho da Query: "+query.length());
        logger.log(Level.INFO, "Query: "+query);
		st = conn.createStatement();
		nAffecteds = st.executeUpdate(query);
		return nAffecteds;
	}
}
