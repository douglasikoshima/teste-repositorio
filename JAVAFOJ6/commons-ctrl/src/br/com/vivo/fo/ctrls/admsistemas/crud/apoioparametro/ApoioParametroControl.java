package br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro;

import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface ApoioParametroControl {

	static final long serialVersionUID = 30351200521000461L;

	/**
	 * @jc:sql statement="SELECT IDPARAMETRO, CDPARAMETRO, DSPARAMETRO, DSVALORPARAMETRO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO FROM APOIO.PARAMETRO WHERE IDPARAMETRO = {idParametro}"
	 */
	br.com.vivo.fo.dbclasses.ApoioParametro getParametroById(long idParametro) throws SQLException;

	/**
	 * @jc:sql statement::
	 * SELECT IDPARAMETRO,
	 *        CDPARAMETRO,
	 *        DSPARAMETRO,
	 *        DSVALORPARAMETRO,
	 *        IDUSUARIOALTERACAO,
	 *        DTULTIMAALTERACAO
	 *   FROM APOIO.PARAMETRO
	 *  WHERE UPPER(CDPARAMETRO) LIKE '%{initialLetter}%'
	 * ::
	 */
	br.com.vivo.fo.dbclasses.ApoioParametro[] getParametrosByInicialLetter(String initialLetter) throws SQLException;

	/**
	 * @jc:sql statement="{sql: sqlStatement}"
	 */
	br.com.vivo.fo.dbclasses.ApoioParametro[] getApoioParametroArray(String sqlStatement) throws SQLException;

	/**
	 * @jc:sql statement::
	 * SELECT IDPARAMETRO,
	 *        CDPARAMETRO,
	 *        DSPARAMETRO,
	 *        DSVALORPARAMETRO,
	 *        IDUSUARIOALTERACAO,
	 *        DTULTIMAALTERACAO
	 *   FROM APOIO.PARAMETRO
	 *  WHERE ASCII(UPPER(SUBSTR(CDPARAMETRO,1,1))) < 65
	 *     OR ASCII(UPPER(SUBSTR(CDPARAMETRO,1,1))) > 90
	 * ::
	 */
	br.com.vivo.fo.dbclasses.ApoioParametro[] getParametrosBySpecialCharacters() throws SQLException;

	/**
	 * @jc:sql statement::
	 * INSERT INTO APOIO.PARAMETRO (
	 *        IDPARAMETRO,
	 *        CDPARAMETRO,
	 *        DSPARAMETRO,
	 *        DSVALORPARAMETRO,
	 *        IDUSUARIOALTERACAO,
	 *        DTULTIMAALTERACAO)
	 * VALUES (APOIO.PARAMETROSQ.NEXTVAL,
	 *         {cdParametro},
	 *         {dsParametro},
	 *         {dsValorParametro},
	 *         {idUsuario},
	 *         SYSDATE)
	 * ::
	 */
	void addParametro(String cdParametro, String dsParametro, String dsValorParametro, long idUsuario) throws SQLException;

	/**
	 * @jc:sql statement::
	 * UPDATE APOIO.PARAMETRO
	 *    SET CDPARAMETRO = {cdParametro},
	 *        DSPARAMETRO = {dsParametro},
	 *        DSVALORPARAMETRO = {dsValorParametro},
	 *        IDUSUARIOALTERACAO = {idUsuario},
	 *        DTULTIMAALTERACAO = SYSDATE
	 *  WHERE IDPARAMETRO = {idParametro}
	 * ::
	 */
	void updateParametro(long idParametro, String cdParametro, String dsParametro, String dsValorParametro, long idUsuario) throws SQLException;

	/**
	 * @jc:sql statement="DELETE FROM APOIO.PARAMETRO WHERE IDPARAMETRO = {idParametro}"
	 */
	void deleteParametro(long idParametro) throws SQLException;
}
