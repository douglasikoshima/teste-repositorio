package br.com.vivo.fo.ctrls.admsistemas.arquivos;

import javax.ejb.Local;

import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.ArquivosRS;
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.RelatorioUpload;

@Local
public interface ArquivosDB {

	static final long serialVersionUID = 1L;

	/**
	 * @jc:sql array-max-length="all" statement="{sql: query}"
	 */
	ArquivosRS[] executeQuery(String query);

	/**
	 * @jc:sql statement="{sql: query}"
	 */
	int getInt(String query);

	/**
	 * @jc:sql statement="{sql: query}" command-type="insert"
	 */
	void executeCommand(String query);

	/**
	 * @jc:sql statement="{sql: query}"
	 */
	RelatorioUpload buscarRelatorioUpload(String query);

}