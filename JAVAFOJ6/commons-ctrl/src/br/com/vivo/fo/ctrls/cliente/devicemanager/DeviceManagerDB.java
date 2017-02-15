package br.com.vivo.fo.ctrls.cliente.devicemanager;

import java.sql.SQLException;

import javax.ejb.Local;

import br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses.LogAtualizacaoParametrosAparelho;

/**
 * Defines a new database control.
 * 
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface DeviceManagerDB {

	static final long serialVersionUID = 144854L;

	/**
	 * @jc:sql array-max-length="all" statement="{sql: query}"
	 */
	public LogAtualizacaoParametrosAparelho executeQuery(String query) throws SQLException ;

	/**
	 * @jc:sql statement="{sql: query}" command-type="insert"
	 */
	public void executeCommand(String query) throws SQLException;
}
