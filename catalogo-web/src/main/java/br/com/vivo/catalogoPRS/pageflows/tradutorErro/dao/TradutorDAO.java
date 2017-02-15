package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.utils.TradutorUtils;

import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;

public abstract class TradutorDAO extends DataAccessObject {

	/*	
	private static ComboPooledDataSource pooled;
	static {
		pooled = TradutorUtils.getDataSource();
	}
	static {
		try {
			// PropertiesConfigFile prop = new
			// PropertiesConfigFile("conn.properties");
			PropertiesConfigFile prop = new PropertiesConfigFile("catalogoprs_config.properties");
			Class.forName(prop.getProperty("database.driver"));
			pooled = new ComboPooledDataSource();
			pooled.setDriverClass(prop.getProperty("database.driver"));
			pooled.setJdbcUrl(prop.getProperty("database.url"));
			pooled.setPassword(prop.getProperty("database.password"));
			pooled.setUser(prop.getProperty("database.user"));
			pooled.setInitialPoolSize(Integer.parseInt(prop.getProperty("database.cache.min")));
			pooled.setMinPoolSize(Integer.parseInt(prop.getProperty("database.cache.min")));
			pooled.setMaxPoolSize(Integer.parseInt(prop.getProperty("database.cache.max")));
			pooled.setIdleConnectionTestPeriod(3600000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */

	protected Connection getConnection() {
		try {
			return TradutorUtils.getDataSource().getConnection();
//			return pooled.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see DataAcessObject#getDatabaseLogicalName()
	 */
	public String getDatabaseLogicalName() {
		return "Tradutor";
	}

	/**
	 * Returns the next ID that can be used to insert a new register.
	 * 
	 * @param SQL_MAX
	 *            The query that selects the max ID
	 * @param partialId
	 *            The partial ID of the Value Object
	 * @param conn
	 *            The <code>Connection</code> to be used
	 * @return An ID that can be used to insert a new Value Object
	 * @throws DAOException
	 *             On erros
	 */
	protected synchronized String getNextId(String SQL_MAX) throws DAOException {
		long seq = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL_COLUMN = "CD_" + SQL_MAX.substring(5);

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("SELECT MAX( " + SQL_COLUMN
					+ ") + 1 FROM " + SQL_MAX);
			pstmt.execute();
			rs = pstmt.getResultSet();

			if (rs.next()) {
				seq = rs.getLong(1);
			} else {
				throw new DAOException("Erro em getNextID()");
			}

		} catch (Exception e) {
			throw new DAOException("Erro em getNextID()", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return "" + seq;
	}

	/**
	 * Returns the next ID that can be used to insert a new register.
	 * 
	 * @param SQL_MAX
	 *            The query that selects the max ID
	 * @param conn
	 *            The <code>Connection</code> to be used
	 * @return An ID that can be used to insert a new Value Object
	 * @throws DAOException
	 *             On erros
	 */
	protected String getNextId(String SQL_MAX, String partial_id,
			Connection conn) throws DAOException {
		throw new DAOException("Method not yet implemented");
	}

	protected String convertColumnName(String p) {
		if (p == null || p.trim().equals("") || p.indexOf("_") == -1) {
			return p.substring(0, 1).toUpperCase() + p.substring(1, p.length());
		}

		int i = 0;

		String aux = p.substring(0, 1).toUpperCase()
				+ p.substring(1, p.length());

		while ((i = aux.indexOf("_")) != -1) {
			aux = aux.substring(0, i)
					+ aux.substring(i + 1, i + 2).toUpperCase()
					+ aux.substring(i + 2, aux.length());
			return convertColumnName(aux);
		}
		return (aux);
	}
}