package br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db;

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

@Stateless(name = "ManterAcessoTorpedoDB", mappedName = "ManterAcessoTorpedoDB")
@Local(ManterAcessoTorpedoDB.class)
@Session(ejbName = "ManterAcessoTorpedoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterAcessoTorpedoDBImpl implements ManterAcessoTorpedoDB {

    @EJB
    private DataBaseCall database;

    @Override
    public ItemAcessoTorpedo[] consultarAcessoTorpedoWeb() throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("SELECT IDACESSOTORPEDOWEB idAcessoTorpedo, ");
        query.append("PRIMEIRONIVEL primeiroNivel , ");
        query.append("SEGUNDONIVEL segundoNivel, ");
        query.append("INATIVO inAtivo, ");
        query.append("IDITEMMENU idMenu, ");
        query.append("IDSUBMENU idSubMenu, ");
        query.append("IDUSUARIOALTERACAO idUsuario, ");
        query.append("DTULTIMAALTERACAO dataAlteracao ");
        query.append("FROM VOL.ACESSOTORPEDOWEB ");
        query.append("ORDER BY PRIMEIRONIVEL DESC");

        ArrayList<ItemAcessoTorpedo> list = new ArrayList<ItemAcessoTorpedo>();
        ResultSet rs = database.executeQuery(query.toString());

        while (rs.next()) {
            ItemAcessoTorpedo itemAcessoTorpedo = new ItemAcessoTorpedo();
            itemAcessoTorpedo.setIdAcessoTorpedo(rs.getString("idAcessoTorpedo"));
            itemAcessoTorpedo.setPrimeiroNivel(rs.getString("primeiroNivel"));
            itemAcessoTorpedo.setSegundoNivel(rs.getString("segundoNivel"));
            itemAcessoTorpedo.setInAtivo(rs.getString("inAtivo"));
            itemAcessoTorpedo.setIdMenu(rs.getString("idMenu"));
            itemAcessoTorpedo.setIdSubMenu(rs.getString("idSubMenu"));
            itemAcessoTorpedo.setIdUsuario(rs.getString("idUsuario"));
            itemAcessoTorpedo.setDataAlteracao(rs.getString("dataAlteracao"));

        }
        rs.close();
        database.release();

        return list.toArray(new ItemAcessoTorpedo[0]);
    }

    @Override
    public void ativarAcessoTorpedoWeb(String idAcessoTorpedo) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("UPDATE VOL.ACESSOTORPEDOWEB SET INATIVO = 1 ");
        query.append("WHERE IDACESSOTORPEDOWEB = ").append(idAcessoTorpedo);

        database.executeQuery(query.toString());

    }

    @Override
    public void desativarAcessoTorpedoWeb(String idAcessoTorpedo) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("UPDATE VOL.ACESSOTORPEDOWEB SET INATIVO = 0 ");
        query.append("WHERE IDACESSOTORPEDOWEB = ").append(idAcessoTorpedo);

        database.executeQuery(query.toString());
    }

}
