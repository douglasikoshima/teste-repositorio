package br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem;

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
import br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.dbClasses.ListaRelBlindagem;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "RelBlindagemDB", mappedName = "RelBlindagemDB")
@Local(RelBlindagemDB.class)
@Session(ejbName = "RelBlindagemDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelBlindagemDBImpl implements RelBlindagemDB {

	@EJB
	private DataBaseCall database;

	public ListaRelBlindagem[] executeQuery(String query) {
		ArrayList<ListaRelBlindagem> lista = new ArrayList<ListaRelBlindagem>();
		ResultSet rs;
		try {
			rs = database.executeQuery(query);
			while(rs.next()){
				ListaRelBlindagem listaRelBlindagem = new ListaRelBlindagem();
				listaRelBlindagem.setDtBlindagem(rs.getString("dtBlindagem"));
				listaRelBlindagem.setQtCliFid(rs.getInt("qtCliFid"));
				listaRelBlindagem.setQtCliInb(rs.getInt("qtCliInb"));
				lista.add(listaRelBlindagem);
			}
			rs.close();
			database.release();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista.toArray(new ListaRelBlindagem[0]);
	}

}
