package br.com.vivo.fo.ctrls.fidelizacao.relatorios;

import java.sql.SQLException;
import javax.ejb.Local;

@Local
public interface RelatoriosControl {

	static final long serialVersionUID = 1322254995478061L;

	/**
	 * @jc:sql statement::
	 *   SELECT U.NMLOGINUSUARIO,
	 *          SP.VLIDREFERENCIA NRPEDIDO,
	 *          SP.DSOBSERVACAO,
	 *          SP.DTULTIMAALTERACAO,
	 *          SP.VLLOGXMLIN XML
	 *     FROM RETENCAO.STATUSSAP SP,
	 *          ACESSO.USUARIO U
	 *    WHERE SP.IDUSUARIOALTERACAO = U.IDPESSOAUSUARIO
	 *      AND  VLLOGXMLIN LIKE '%{nrLinha}%'
	 * ORDER BY DTULTIMAALTERACAO DESC
	 * ::
	 */
	public br.com.vivo.fo.dbclasses.RetencaoStatusSAP[] getStatusSAPByNrLinha(String nrLinha) throws SQLException;

	/**
	 * @jc:sql statement="{sql: sqlStatement}"
	 */
	public br.com.vivo.fo.dbclasses.RetencaoStatusSAP[] getStatusSAPArray(String sqlStatement) throws SQLException;

}