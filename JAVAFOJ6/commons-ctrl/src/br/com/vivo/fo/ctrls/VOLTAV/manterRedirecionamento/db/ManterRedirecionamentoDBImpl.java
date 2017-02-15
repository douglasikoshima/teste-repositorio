package br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ManterRedirecionamentoDB", mappedName = "ManterRedirecionamentoDB")
@Local(ManterRedirecionamentoDB.class)
@Session(ejbName = "ManterRedirecionamentoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterRedirecionamentoDBImpl implements ManterRedirecionamentoDB {

	@EJB
	private DataBaseCall database;

	@Override
	public ItemRedirecionamento[] getListaRedirecionamento(String query) {

		ArrayList<ItemRedirecionamento> list = new ArrayList<ItemRedirecionamento>();
		ResultSet rs;
		try {
			rs = database.executeQuery(query);
			while (rs.next()) {
				ItemRedirecionamento itemRedirecionamento = new ItemRedirecionamento();
				itemRedirecionamento.setIdRedirecionamento(rs.getString("idRedirecionamento"));
				itemRedirecionamento.setPrimeiroNivel(rs.getString("primeiroNivel"));
				itemRedirecionamento.setSegundoNivel(rs.getString("segundoNivel"));
				;
				itemRedirecionamento.setStatus(rs.getString("status"));
				itemRedirecionamento.setIdSistema(rs.getString("idSistema"));
				itemRedirecionamento.setIdCampanha(rs.getString("idCampanha"));
				itemRedirecionamento.setUrlDestino(rs.getString("urlDestino"));
				itemRedirecionamento.setIdMenu(rs.getString("idMenu"));
				itemRedirecionamento.setIdSubMenu(rs.getString("idSubMenu"));
				itemRedirecionamento.setIdUsuario(rs.getString("idUsuario"));
				itemRedirecionamento.setDataAlteracao(rs.getString("dataAlteracao"));
				list.add(itemRedirecionamento);
			}
			rs.close();
			database.release();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.toArray(new ItemRedirecionamento[0]);
	}
}
