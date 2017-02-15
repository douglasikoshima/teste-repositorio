package br.com.vivo.fo.ctrls.cliente.devicemanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses.LogAtualizacaoParametrosAparelho;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "DeviceManagerDB", mappedName = "DeviceManagerDB")
@Local(DeviceManagerDB.class)
@Session(ejbName = "DeviceManagerDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DeviceManagerDBImpl implements DeviceManagerDB {

    @EJB
    private DataBaseCall database;

    @Override
    public LogAtualizacaoParametrosAparelho executeQuery(String query) throws SQLException {
        LogAtualizacaoParametrosAparelho logAtualizacaoParametrosAparelho = new LogAtualizacaoParametrosAparelho();
        ResultSet rs = null;
		Statement st = null;
        try {
            //rs = database.executeQuery(query);
       		st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			
            if (rs.next()) {
                logAtualizacaoParametrosAparelho.setIdLogDeviceManager(rs.getInt("idLogDeviceManager"));
                logAtualizacaoParametrosAparelho.setNrLinha(rs.getLong("nrLinha"));
                logAtualizacaoParametrosAparelho.setIdUsuarioAlteracao(rs.getInt("idUsuarioAlteracao"));
                logAtualizacaoParametrosAparelho.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (rs != null) {
				rs.close();
        }
			if (st != null) {
				st.close();
			}
			database.release();
		}
		

        return logAtualizacaoParametrosAparelho;
    }

    @Override
    public void executeCommand(String query) throws SQLException {
    	Statement st = null;
        try {
        	st = database.getConnection().createStatement();
 			st.executeUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
           
		} finally {
			if (st != null) {
				st.close();
			}
			database.release();
        }
    }

}
