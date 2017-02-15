package br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db;

import java.sql.SQLException;

import javax.ejb.Local;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface AcaoIncentivoDB {
	
	/**
	 * @jc:sql array-max-length="all" statement:: SELECT CDACAOINCENTIVO,
	 *         DSACAOINCENTIVO, TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI:SS')
	 *         DTHORAINICIO, TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI:SS')
	 *         DTHORATERMINO, INTIPOACAOINCENTIVO, MSGANTESINICIOACAO,
	 *         MSGAPOSINICIOACAO, INATIVO FROM CUSTOMER.ACAO_INCENTIVO ::
	 */
	Acao[] getTodasAcoes() throws SQLException;

	/**
	 * @jc:sql statement="{sql: sqlStatement}"
	 */
	Acao[] getPesquisaAcoes(String sqlStatement) throws SQLException;

	/**
	 * @jc:sql array-max-length="all" statement:: SELECT CDACAOINCENTIVO,
	 *         DSACAOINCENTIVO, TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI')
	 *         DTHORAINICIO, TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI')
	 *         DTHORATERMINO, INTIPOACAOINCENTIVO, MSGANTESINICIOACAO,
	 *         MSGAPOSINICIOACAO, INATIVO FROM CUSTOMER.ACAO_INCENTIVO WHERE
	 *         CDACAOINCENTIVO = {id} ::
	 */
	Acao buscarAcaoIncentivo(String id) throws SQLException;

	/**
	 * @jc:sql statement=
	 *         "DELETE FROM CUSTOMER.ACAO_INCENTIVO WHERE CDACAOINCENTIVO = {id}"
	 */
	void excluirAcaoIncentivo(String id) throws SQLException;

	/**
	 * @jc:sql array-max-length="all" statement:: INSERT INTO
	 *         CUSTOMER.ACAO_INCENTIVO ( CDACAOINCENTIVO, DSACAOINCENTIVO,
	 *         DTHORAINICIO, DTHORATERMINO, INTIPOACAOINCENTIVO,
	 *         MSGANTESINICIOACAO, MSGAPOSINICIOACAO, INATIVO) VALUES
	 *         (CUSTOMER.ACAO_INCENTIVOSQ.NEXTVAL, {descricao},
	 *         TO_DATE({dataInicial}, 'dd/mm/yyyy hh24:mi:ss'),
	 *         TO_DATE({dataFinal}, 'dd/mm/yyyy hh24:mi:ss'), {tipo},
	 *         {msgInicial}, {msgFinal}, {inAtivo} ) ::
	 */
	void inserirAcaoIncentivo(String id, String descricao, String dataInicial,
			String dataFinal, String tipo, String msgInicial, String msgFinal,
			String inAtivo, String user) throws SQLException;

	/**
	 * @jc:sql array-max-length="all" statement:: UPDATE CUSTOMER.ACAO_INCENTIVO
	 *         SET DSACAOINCENTIVO = {descricao}, DTHORAINICIO =
	 *         TO_DATE({dataInicial}, 'dd/mm/yyyy hh24:mi:ss'), DTHORATERMINO =
	 *         TO_DATE({dataFinal}, 'dd/mm/yyyy hh24:mi:ss'),
	 *         INTIPOACAOINCENTIVO = {tipo}, MSGANTESINICIOACAO = {msgInicial},
	 *         MSGAPOSINICIOACAO = {msgFinal}, INATIVO = {inAtivo} WHERE
	 *         CDACAOINCENTIVO = {id} ::
	 */
	void atualizarAcaoIncentivo(String id, String descricao,
			String dataInicial, String dataFinal, String tipo,
			String msgInicial, String msgFinal, String inAtivo, String user)
			throws SQLException;

}