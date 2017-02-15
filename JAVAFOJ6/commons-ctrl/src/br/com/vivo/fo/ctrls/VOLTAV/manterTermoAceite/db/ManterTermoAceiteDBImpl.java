package br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db;

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

@Stateless(name = "ManterTermoAceiteDB", mappedName = "ManterTermoAceiteDB")
@Local(ManterTermoAceiteDB.class)
@Session(ejbName = "ManterTermoAceiteDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterTermoAceiteDBImpl implements ManterTermoAceiteDB {

    @EJB
    private DataBaseCall database;

    @Override
    public ItemMenuTermoAceite[] getListaServico(String query) throws SQLException {

        ArrayList<ItemMenuTermoAceite> list = new ArrayList<ItemMenuTermoAceite>();
        ResultSet rs = database.executeQuery(query);

        while (rs.next()) {
            ItemMenuTermoAceite itemMenuTermoAceite = new ItemMenuTermoAceite();
            itemMenuTermoAceite.setIdItemMenu(rs.getString("idItemMenu"));
            itemMenuTermoAceite.setStatusTermo(rs.getString("statusTermo"));
            itemMenuTermoAceite.setTxtTermo(rs.getString("txtTermo"));
            itemMenuTermoAceite.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));
            itemMenuTermoAceite.setNmItem(rs.getString("nmItem"));
            itemMenuTermoAceite.setDataAlteracaotTermo(rs.getString("dataAlteracaotTermo"));
            list.add(itemMenuTermoAceite);

        }
        rs.close();
        database.release();

        return list.toArray(new ItemMenuTermoAceite[0]);

    }

    @Override
    public ItemMenuTermoAceite getTermoAceiteServico(String query) throws SQLException {

        ResultSet rs = database.executeQuery(query);
        ItemMenuTermoAceite itemMenuTermoAceite = new ItemMenuTermoAceite();

        while (rs.next()) {
            itemMenuTermoAceite.setIdItemMenu(rs.getString("idItemMenu"));
            itemMenuTermoAceite.setStatusTermo(rs.getString("statusTermo"));
            itemMenuTermoAceite.setTxtTermo(rs.getString("txtTermo"));
            itemMenuTermoAceite.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));
            itemMenuTermoAceite.setNmItem(rs.getString("nmItem"));
            itemMenuTermoAceite.setDataAlteracaotTermo(rs.getString("dataAlteracaotTermo"));

        }
        rs.close();
        database.release();

        return itemMenuTermoAceite;
    }

    @Override
    public void setTextoTermo(String query) throws SQLException {

        /*ResultSet rs = database.executeQuery(query);
        ItemMenuTermoAceite itemMenuTermoAceite = new ItemMenuTermoAceite();

        while (rs.next()) {
            itemMenuTermoAceite.setIdItemMenu(rs.getString("idItemMenu"));
            itemMenuTermoAceite.setStatusTermo(rs.getString("statusTermo"));
            itemMenuTermoAceite.setTxtTermo(rs.getString("txtTermo"));
            itemMenuTermoAceite.setIdUsuarioAlteracao(rs.getString("idUsuarioAlteracao"));
            itemMenuTermoAceite.setNmItem(rs.getString("nmItem"));
            itemMenuTermoAceite.setDataAlteracaotTermo(rs.getString("dataAlteracaotTermo"));

        }
        rs.close();*/
        database.executeUpdate(query);
        database.release();
    }

}
