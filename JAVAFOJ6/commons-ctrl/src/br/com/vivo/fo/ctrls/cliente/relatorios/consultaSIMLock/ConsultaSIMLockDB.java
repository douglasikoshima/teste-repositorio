package br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock;

import javax.ejb.Local;

import br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock.dbClasses.CamposConsultaSimLock;

/**
 * Defines a new database control.
 * 
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface ConsultaSIMLockDB {

	static final long serialVersionUID = 1L;

	/**
	 * @jc:sql array-max-length="all" statement="{sql: query}"
	 */
	CamposConsultaSimLock[] getRelatorioSimLock(String query);
}
