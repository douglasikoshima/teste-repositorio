package br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento;

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
import br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db.ManterRedirecionamentoDB.ItemRedirecionamento;

@Stateless(name = "ManterRedirecionamento", mappedName = "ManterRedirecionamento")
@Local(ManterRedirecionamento.class)
@Session(ejbName = "ManterRedirecionamento", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterRedirecionamentoImpl implements ManterRedirecionamento {

	@EJB
	private br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db.ManterRedirecionamentoDB manterRedirecionamentoDB;

	/**
	 * @common:operation
	 */
	public ItemRedirecionamento[] consultarRedirecionamento(Short idSistema) throws Exception {
		return manterRedirecionamentoDB.getListaRedirecionamento(montarConsultaSQL(idSistema));
	}

	private String montarConsultaSQL(Short idSistema) {

		StringBuffer sb = new StringBuffer();

		sb.append("SELECT   IDREDIRECIONAMENTO idRedirecionamento,              ");
		sb.append("         PRIMEIRONIVEL primeiroNivel,                        ");
		sb.append("         SEGUNDONIVEL segundoNivel,                          ");
		sb.append("         INATIVO status, IDSISTEMAORIGEM idSistema,          ");
		sb.append("         IDCAMPANHA idCampanha, URLDESTINO urlDestino,       ");
		sb.append("         IDITEMMENU idMenu, IDSUBMENU idSubMenu,             ");
		sb.append("         IDUSUARIOALTERACAO idUsuario,                       ");
		sb.append("         DTULTIMAALTERACAO dataAlteracao                     ");
		sb.append("         FROM   VOL.REDIRECIONAMENTO                         ");
		if (idSistema != null) {
			sb.append("         WHERE  IDSISTEMAORIGEM = ").append(idSistema);
		}

		return sb.toString();
	}

}
