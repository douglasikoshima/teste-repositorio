package br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem;

import javax.ejb.Local;

import br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.dbClasses.ListaRelBlindagem;

@Local
public interface RelBlindagemDB {

	/**
	 * @jc:sql array-max-length="all" statement="{sql: query}"
	 */
	ListaRelBlindagem[] executeQuery(String query);
}
