package br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.constantes.ConstantesCRM;

@Stateless(name = "ApoioParametro", mappedName = "ApoioParametro")
@Local(ApoioParametro.class)
@Session(ejbName = "ApoioParametro", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApoioParametroImpl implements ApoioParametro {

	static final long serialVersionUID = 260822437359094L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro.ApoioParametroControl apoioParametroControl;

	/**
	 * @common:operation
	 */
	public br.com.vivo.fo.dbclasses.ApoioParametro[] buscarParametrosPorLetra(String letraInicial) throws Exception {

		br.com.vivo.fo.dbclasses.ApoioParametro[] query = new br.com.vivo.fo.dbclasses.ApoioParametro[0];
		try {
			if (letraInicial.matches("[A-Z]")) {
				StringBuffer statement = new StringBuffer().append("SELECT IDPARAMETRO, ").append("       CDPARAMETRO, ").append("       DSPARAMETRO, ").append("       DSVALORPARAMETRO, ").append("       IDUSUARIOALTERACAO, ").append("       DTULTIMAALTERACAO ").append("  FROM APOIO.PARAMETRO ").append(" WHERE UPPER(CDPARAMETRO) LIKE '").append(letraInicial).append("%' ");
				query = apoioParametroControl.getApoioParametroArray(statement.toString());

			} else {
				query = apoioParametroControl.getParametrosBySpecialCharacters();
			}

		} catch (Exception e) {
		}
		return query;
	}

	/**
	 * @common:operation
	 */
	public br.com.vivo.fo.dbclasses.ApoioParametro buscarParametro(long idParametro) throws Exception {
		return apoioParametroControl.getParametroById(idParametro);
	}

	/**
	 * @common:operation
	 */
	public void salvarParametro(br.com.vivo.fo.dbclasses.ApoioParametro parametro, long idUsuario) throws Exception {

		if (parametro == null || parametro.getCdParametro() == null || ConstantesCRM.SVAZIO.equals(parametro.getCdParametro()) || parametro.getDsParametro() == null || ConstantesCRM.SVAZIO.equals(parametro.getDsParametro()) || parametro.getDsValorParametro() == null || ConstantesCRM.SVAZIO.equals(parametro.getDsValorParametro())) {
			throw new Exception("Parâmetros de entrada inválidos");
		}

		try {
			if (parametro.getIdParametro() == 0) {
				apoioParametroControl.addParametro(parametro.getCdParametro(), parametro.getDsParametro(), parametro.getDsValorParametro(), idUsuario);
			} else {
				apoioParametroControl.updateParametro(parametro.getIdParametro(), parametro.getCdParametro(), parametro.getDsParametro(), parametro.getDsValorParametro(), idUsuario);
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return;
	}

	/**
	 * @common:operation
	 */
	public void excluirParametro(long idParametro) throws Exception {
		apoioParametroControl.deleteParametro(idParametro);
	}

}