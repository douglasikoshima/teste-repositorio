package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.vivo.catalogoPRS.util.VivoNetUtils;

import com.framework.dao.DataAccessObject;

public abstract class VivoNetTradutorDAO extends DataAccessObject {

	protected Connection getConnection() {
		try {
			return VivoNetUtils.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}