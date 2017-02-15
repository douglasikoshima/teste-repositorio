package br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock;

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
import br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock.dbClasses.CamposConsultaSimLock;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ConsultaSIMLockDB", mappedName = "ConsultaSIMLockDB")
@Local(ConsultaSIMLockDB.class)
@Session(ejbName = "ConsultaSIMLockDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultaSIMLockDBImpl implements ConsultaSIMLockDB {

    @EJB
    private DataBaseCall database;

    @Override
    public CamposConsultaSimLock[] getRelatorioSimLock(String query) {
        ArrayList<CamposConsultaSimLock> list = new ArrayList<CamposConsultaSimLock>();
        ResultSet rs;

        try {
            rs = database.executeQuery(query);

            while (rs.next()) {
                CamposConsultaSimLock camposConsultaSimLock = new CamposConsultaSimLock();
                camposConsultaSimLock.setDtultimaalteracao(rs.getString("dtultimaalteracao"));
                camposConsultaSimLock.setNrimei(rs.getString("nrimei"));
                camposConsultaSimLock.setNmloginusuario(rs.getString("nmloginusuario"));
                camposConsultaSimLock.setNrip(rs.getString("nrip"));
                camposConsultaSimLock.setEstadoconsulta(rs.getString("estadoconsulta"));
                camposConsultaSimLock.setNrlinha(rs.getString("nrlinha"));
                camposConsultaSimLock.setNmtiporelacionamento(rs.getString("nmtiporelacionamento"));
                camposConsultaSimLock.setSgtipodocumento(rs.getString("sgtipodocumento"));
                camposConsultaSimLock.setNrdocumento(rs.getString("nrdocumento"));
                camposConsultaSimLock.setNmpessoa(rs.getString("nmpessoa"));
                list.add(camposConsultaSimLock);
            }

            rs.close();
            database.release();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new CamposConsultaSimLock[0]);
    }
}