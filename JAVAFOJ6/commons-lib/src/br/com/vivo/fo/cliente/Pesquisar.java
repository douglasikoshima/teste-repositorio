package br.com.vivo.fo.cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;

public class Pesquisar {

	public Pesquisar() {
	}

	public Resultset executar(String query) {

		Context ctx = null;
		Hashtable<String, String> ht = new Hashtable<String, String>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Resultset resultset = Resultset.Factory.newInstance();

		try {
			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ctx = new InitialContext(ht);
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc.OracleDS");
			conn = ds.getConnection();
            while(conn.isClosed()) {
            	conn = ds.getConnection();
            }
			conn.setAutoCommit(true);

			stmt = conn.createStatement();
			stmt.setFetchSize(1000);
			
			stmt.execute(query);
			conn.commit();

			rs = stmt.getResultSet();

			int colunas = rs.getMetaData().getColumnCount();
			if (colunas > 0) {
				resultset.addNewColunas();
				resultset.addNewLinhas();
			}

			while (rs.next()) {
				Resultset.Linhas.Linha linha = resultset.getLinhas().addNewLinha();
				for (int j = 1; j <= colunas; j++) {
					linha.addValor(rs.getString(j));
				}
			}

			for (int i = 1; i <= colunas; i++) {
				resultset.getColunas().addColuna(rs.getMetaData().getColumnName(i));
			}

			// Close JDBC objects as soon as possible
			/*stmt.close();
			stmt = null;
			conn.close();
			conn = null;*/
		} catch (SQLException e) {
			resultset.setMsg(e.getMessage());

		} catch (Exception e) {
			resultset.setMsg(e.getMessage());

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				/*if (conn != null) {
					conn.close();
				}*/
				if (ctx != null) {
					ctx.close();
				}
			} catch (Exception e) {
			}
		}
		return resultset;
	}
}